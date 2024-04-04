package uwna.knuwiki.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uwna.knuwiki.exception.UserException;
import uwna.knuwiki.controller.MemberJoinForm;
import uwna.knuwiki.email.EmailContentBuilder;
import uwna.knuwiki.email.EmailService;
import uwna.knuwiki.email.RedisUtil;
import uwna.knuwiki.entity.Member;
import uwna.knuwiki.entity.Role;
import uwna.knuwiki.repository.MemberRepository;

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
    private final RedisUtil redisUtil;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final EmailService emailService;
    @Autowired
    private final EmailContentBuilder emailContentBuilder;

    public void join(MemberJoinForm joinForm) {
        Member member = new Member(joinForm);
        validateDuplicateMember(member.getUsername());
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setRole(Role.ROLE_NOT_PERMITTED);
        memberRepository.save(member);
    }

    public void sendVerificationEmail(Member member) {

        if(member == null) {
            throw new UserException("No Exist User");
        }
        UUID uuid = UUID.randomUUID();
        String content = emailContentBuilder.signupBuild(uuid.toString());
        redisUtil.setValueWithExpire(uuid.toString(),member.getUsername(), 60 * 30L);
        emailService.sendEmail(member.getUsername(), "KNUWiki 회원가입 인증메일 입니다.", content);
    }

    public void verifyEmail(String key) {
        String username = redisUtil.getValue(key);
        log.info("유저 인증 링크 클릭, 이메일={}", username);
        Member findMember = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UserException("No Exist User"));
        modifyUserRole(findMember, Role.ROLE_USER);
        redisUtil.deleteValue(key);
    }

    public void modifyUserRole(Member member, Role role) {
        member.setRole(role);
        memberRepository.save(member);
    }

//    public String loginUuid(String username, String password) {
//        String uuid = UUID.randomUUID().toString();
//        sessionManager.putId( uuid, username);
//
//        return uuid;
//    }

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
