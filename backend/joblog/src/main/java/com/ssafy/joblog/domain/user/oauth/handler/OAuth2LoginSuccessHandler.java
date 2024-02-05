package com.ssafy.joblog.domain.user.oauth.handler;

import com.ssafy.joblog.domain.user.entity.Token;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import com.ssafy.joblog.domain.user.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String username = null;
        if (attributes.get("sub") != null) {
            username = "google_" + attributes.get("sub");
        } else if (attributes.get("response") != null) {
            attributes = (Map) attributes.get("response");
            username = "naver_" + attributes.get("id");
        } else {
            username = "kakao_" + String.valueOf(attributes.get("id"));
        }
        User user = userRepository.findByUsername(username);
        String role = oAuth2User.getAuthorities().stream().findFirst().orElseThrow(IllegalAccessError::new).getAuthority();

        Token token = jwtUtil.generateToken(user.getId(), role);
        System.out.println("JWT : " + token.getAccessToken());
        String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:5173/sign-in") //로그인 성공 시 호출할 url
                .queryParam("accessToken", token.getAccessToken())
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUriString();
        System.out.println("targetUrl : " + targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

}
