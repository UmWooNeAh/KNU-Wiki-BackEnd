package uwna.knuwiki.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uwna.knuwiki.auth.TokenProvider;
import uwna.knuwiki.dto.MemberDto;
import uwna.knuwiki.dto.LoginDto;
import uwna.knuwiki.entity.Sanctions;
import uwna.knuwiki.exception.LoginException;
import uwna.knuwiki.exception.UserException;
import uwna.knuwiki.controller.MemberJoinForm;
import uwna.knuwiki.email.EmailContentBuilder;
import uwna.knuwiki.email.EmailService;
import uwna.knuwiki.email.RedisUtil;
import uwna.knuwiki.entity.Member;
import uwna.knuwiki.entity.Role;
import uwna.knuwiki.repository.MemberRepository;
import uwna.knuwiki.repository.SanctionsRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final SanctionsRepository sanctionsRepository;
    @Autowired
    private final RedisUtil redisUtil;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final EmailService emailService;
    @Autowired
    private final EmailContentBuilder emailContentBuilder;
    @Autowired
    private final TokenProvider tokenProvider;

    public void join(MemberJoinForm joinForm) {
        Member member = new Member(joinForm);
        validateDuplicateMember(member.getUsername());
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setRole(Role.ROLE_NOT_PERMITTED);
        memberRepository.save(member);
    }

    public void sendVerificationEmail(String username) {
        Member findMember = getMemberByUsername(username);
        if(findMember == null) {
            throw new UserException("No Exist User");
        }

        UUID uuid = UUID.randomUUID();
        String content = emailContentBuilder.signupBuild(uuid.toString());
        redisUtil.setValueWithExpire(uuid.toString(),findMember.getUsername(), 60 * 30L);
        emailService.sendEmail(findMember.getUsername(), "KNUWiki 회원가입 인증메일 입니다.", content);
    }

    public void verifyEmail(String key) {
        String username = redisUtil.getValue(key);
        log.info("유저 인증 링크 클릭, 이메일={}", username);
        Member findMember = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UserException("No Exist User"));
        modifyUserRole(findMember, Role.ROLE_USER);
        redisUtil.deleteValue(key);
    }

    public String login(MemberJoinForm form) {

        Member findMember = getMemberByUsername(form.getUsername());
        if(!passwordEncoder.matches(form.getPassword(), findMember.getPassword())) {
            throw new LoginException("Passwords do not match");
        }

        LoginDto loginDto = new LoginDto(findMember.getId(), findMember.getUsername(), findMember.getRole());
        return tokenProvider.createToken(loginDto);
    }

    public void modifyUserRole(Member member, Role role) {
        member.setRole(role);
        memberRepository.save(member);
    }

    public MemberDto getProfile(String memberId) {
        Member findMember = getMemberDtoByMemberId(memberId);
        return new MemberDto(findMember.getId(), findMember.getUsername(), findMember.getRole());
    }

    public String getMemberIdFromJwt(String jwt) {
        return tokenProvider.getMemberId(jwt);
    }

    public String getUsernameFromAuthentication() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            throw new UserException("Security Context에 인증 정보가 없습니다.");
        }

        String username = authentication.getName();
        if(username == null) {
            throw new UserException("No Exist User");
        }
        //log.info("Claims 추출 정보={}",username);
        return username;
    }

    public Member getMemberByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new UserException("No Exist User"));
    }

    public Member getMemberDtoByMemberId(String memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new UserException("No Exist User"));
    }

//    public String convertUuidToUsername(String uuid) {
//        return sessionManager.getId(uuid);
//    }

    private void validateDuplicateMember(String username) {
        Optional<Member> findMember = memberRepository.findByUsername(username);
        if(findMember.isPresent()) {
            throw new UserException("Id Duplicate Error");
        }
    }

}
