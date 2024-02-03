package com.ssafy.joblog.domain.user.config;

import com.ssafy.joblog.domain.user.oauth.Filter.JwtAuthFilter;
import com.ssafy.joblog.domain.user.oauth.Filter.JwtExceptionFilter;
import com.ssafy.joblog.domain.user.oauth.handler.OAuth2LoginFailureHandler;
import com.ssafy.joblog.domain.user.oauth.handler.OAuth2LoginSuccessHandler;
import com.ssafy.joblog.domain.user.service.PrincipalOauth2UserService;
import com.ssafy.joblog.domain.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final PrincipalOauth2UserService principalOauth2UserService;
    private final JwtUtil jwtUtil;
    private final JwtAuthFilter jwtAuthFilter;
    private final JwtExceptionFilter jwtExceptionFilter;

    @Bean
    public SimpleUrlAuthenticationSuccessHandler oAuth2LoginSuccessHandler(){
        return new OAuth2LoginSuccessHandler(jwtUtil);
    }
    @Bean
    public AuthenticationFailureHandler oAuth2LoginFailureHandler(){
        return new OAuth2LoginFailureHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(CsrfConfigurer::disable)
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/applicant/**").authenticated()
                                .requestMatchers("/worker/**").hasAnyRole("WORKER")
                                .requestMatchers("/chatter/**").hasAnyRole("CHATTER")
                                .anyRequest().permitAll())
                .formLogin(AbstractHttpConfigurer::disable)
                .oauth2Login(oauth -> oauth.loginPage("/")
                        .userInfoEndpoint(userInfo -> userInfo.userService(principalOauth2UserService))
                        .successHandler(oAuth2LoginSuccessHandler())
                        .failureHandler(oAuth2LoginFailureHandler()));
        return http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

}
