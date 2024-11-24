package fukuoka.soongsil_carries_love.domain.jwt;

import fukuoka.soongsil_carries_love.domain.user.entity.CustomUserDetails;
import fukuoka.soongsil_carries_love.domain.user.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //request에서 Authorization 헤더를 찾음
        String authorization= request.getHeader("Authorization");

        System.out.println("Authorization Header: " + authorization);

        //Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {

            System.out.println("Authorization header is invalid or missing.");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }
        //Bearer 부분 제거 후 순수 토큰만 획득
        String token = authorization.split(" ")[1];
        System.out.println("Extracted Token: " + token);

        //토큰 소멸 시간 검증
        if (jwtUtil.isExpired(token)) {
            System.out.println("token expired");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수임)
            return;
        }

        System.out.println("Token is valid. Proceeding with parsing.");

        //토큰에서 username과 role 획득
        String email;
        String role;
        try {
            email = jwtUtil.getUsername(token);
            role = jwtUtil.getRole(token);
        } catch (Exception e) {
            System.out.println("Error while parsing token: " + e.getMessage());
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("Token parsed successfully. Email: " + email + ", Role: " + role);

        //userEntity를 생성하여 값 set
        User userEntity = new User();
        userEntity.setEmail(email);
        userEntity.setPassword("temppassword");
        userEntity.setRole(role);

        //UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

        System.out.println("UserDetails created: " + customUserDetails.getUsername() + ", Role: " + role);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        System.out.println("Authentication Token set in SecurityContext.");

        filterChain.doFilter(request, response);

        System.out.println("FilterChain executed successfully.");
    }
}
