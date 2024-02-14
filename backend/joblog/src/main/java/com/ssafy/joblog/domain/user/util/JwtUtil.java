package com.ssafy.joblog.domain.user.util;

import com.ssafy.joblog.domain.user.entity.Token;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.oauth.JwtProperties;
import com.ssafy.joblog.domain.user.repository.TokenRepository;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProperties jwtProperties;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(jwtProperties.getSecret().getBytes());
    }

    public Token generateToken(int userId, String role) {
        String refreshToken = generateRefreshToken(userId, role);
        String accessToken = generateAccessToken(userId, role);
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        System.out.println("username : " + user.getUsername());
        System.out.println("id       : " + user.getId());
//        Token token = Token.create(user, accessToken, refreshToken);
        Token token = Token.builder().user(user).accessToken(accessToken).refreshToken(refreshToken).build();
        System.out.println("token_username : "+token.getUser().getUsername());
        //토큰을 DB에 저장
//        tokenRepository.save(token);
        return token;
    }

    public String generateRefreshToken(int userId, String role) {
        // 토큰의 유효 기간을 밀리초 단위로 설정.
        long refreshPeriod = 1000L * 60L * 60L * 24L * 14; // 2주
        Claims claims = Jwts.claims().setSubject(String.valueOf(userId));
        claims.put("role", role);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshPeriod))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String generateAccessToken(int userId, String role) {
        long tokenPeriod = 1000L * 60L * 60L * 2L; // 2시간
        Claims claims = Jwts.claims().setSubject(String.valueOf(userId));
        claims.put("role", role);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenPeriod))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey) // 비밀키를 설정하여 파싱한다.
                    .parseClaimsJws(token);  // 주어진 토큰을 파싱하여 Claims 객체를 얻는다.
            // 토큰의 만료 시간과 현재 시간비교
            return claims.getBody()
                    .getExpiration()
                    .after(new Date());  // 만료 시간이 현재 시간 이후인지 확인하여 유효성 검사 결과를 반환
        } catch (Exception e) {
            return false;
        }
    }

    public int getUserId(String token) {
        return Integer.valueOf(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());
    }

    public String getRole(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("role", String.class);
    }

}
