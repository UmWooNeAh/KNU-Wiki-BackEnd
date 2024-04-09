package uwna.knuwiki.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenProvider tokenProvider;

    //http request가 올때마다 항상 filter가 먼저 request를 가로채 정보를 검사한다.
    //Authentication(인증): jwt에 정보를 기반으로 UserDetails와 비교 검증 수행,
    // 검증이 완료되면 권한 등의 정보를 Authentication 객체에 담고 SecurityContext에 저장하고 다음 필터를 호출
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwt = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("Security Context에 {} 인증 정보를 저장하였습니다, uri: {}", authentication.getName(), requestURI);
        }
        else {
            log.debug("유효한 JWT 토큰이 존재하지 않습니다, uri={}", requestURI);
        }

        chain.doFilter(httpServletRequest, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {

            //System.out.println("token : " + bearerToken);

            return bearerToken.substring(7);
        }
        return null;
    }
}
