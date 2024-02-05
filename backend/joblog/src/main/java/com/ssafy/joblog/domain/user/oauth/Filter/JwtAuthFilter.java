package com.ssafy.joblog.domain.user.oauth.Filter;

import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import com.ssafy.joblog.domain.user.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String atc = request.getHeader("Authorization"); //request Header의 AccessToken

        if (!StringUtils.hasText(atc)) { //토큰 검사 생략(모두 허용 URL의 경우 토큰 검사 통과)
            System.out.println("토큰 검사 생략");
            doFilter(request, response, filterChain);
            return;
        }
        if (!jwtUtil.verifyToken(atc)) { //AccessToken을 검증하고, 만료되었을 경우 예외 발생.
            throw new JwtException("Expired Access Token");
        } else { //AccessToken이 유효한 경우
            User user = userRepository.findById(jwtUtil.getUserId(atc)).orElseThrow(()-> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));
            if (user != null) {
                Authentication authentication = getAuthentication(user); //SecurityContext에 인증 객체를 등록
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    public Authentication getAuthentication(User user) {
        return new UsernamePasswordAuthenticationToken(user, "",
                List.of(new SimpleGrantedAuthority(user.getRole().toString())));
    }

    public static User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
