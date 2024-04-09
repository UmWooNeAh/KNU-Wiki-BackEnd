package uwna.knuwiki.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;
import uwna.knuwiki.dto.LoginDto;
import uwna.knuwiki.dto.MemberDto;
import uwna.knuwiki.exception.LoginException;
import uwna.knuwiki.exception.UserException;
import uwna.knuwiki.auth.JwtFilter;
import uwna.knuwiki.response.ErrorResponse;
import uwna.knuwiki.response.SuccessResponse;
import uwna.knuwiki.service.MemberService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/auth")
public class MemberController {

    @Autowired
    private final MemberService memberService;
    @Autowired
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @ExceptionHandler(UserException.class)
    public ErrorResponse userExHandle(UserException e) {
        log.error("[userException] ex", e);
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST", e.getMessage());
    }

    @ExceptionHandler(LoginException.class)
    public ErrorResponse loginExHandle(LoginException e) {
        log.error("[loginException] ex", e);
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED", e.getMessage());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public SuccessResponse create(@RequestBody MemberJoinForm memberJoinForm) {
        memberService.join(memberJoinForm);
        return new SuccessResponse(HttpStatus.CREATED.value(), "CREATED", "Id Saved");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/verify")
    public SuccessResponse sendVerifyMail(@RequestParam String username) {
        memberService.sendVerificationEmail(username);
        return new SuccessResponse(HttpStatus.CREATED.value(), "CREATED", "인증 메일을 전송하였습니다.");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/verify/{key}")
    public SuccessResponse getVerify(@PathVariable String key) {
        memberService.verifyEmail(key);
        return new SuccessResponse(HttpStatus.OK.value(), "OK", "유저 인증이 완료되었습니다.");
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public SuccessResponse login(@RequestBody MemberJoinForm memberJoinForm) {

//        //SecurityContext에 Member에 대한 인증 정보를 저장하는 과정, 추후 authorization 과정에 해당 정보를 이용하게 됨
//        UsernamePasswordAuthenticationToken token =
//                new UsernamePasswordAuthenticationToken(memberJoinForm.getUsername(), memberJoinForm.getPassword());
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        String createdJwt = memberService.login(memberJoinForm);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + createdJwt);

        return new SuccessResponse(HttpStatus.OK.value(), "ACCEPTED", createdJwt);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/profile")
    public MemberDto profile(@RequestHeader("Authorization") String jwt) {
        //log.info("토큰: {}", jwt);
        String memberId = memberService.getMemberIdFromJwt(jwt.substring("Bearer ".length()));
        //log.info("가져온 member_id={}", memberId);

        return memberService.getProfile(memberId);
    }

}
