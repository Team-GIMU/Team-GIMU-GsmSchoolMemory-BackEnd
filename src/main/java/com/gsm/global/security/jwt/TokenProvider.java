package com.gsm.global.security.jwt;

import com.gsm.domain.auth.presentation.data.response.TokenDto;
import com.gsm.global.security.exception.TokenExpirationException;
import com.gsm.global.security.exception.TokenNotValidException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class TokenProvider {

    private final UserDetailsService userDetailsService;
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer ";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30; // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7; // 7일

    private final Key key;


    public TokenProvider(@Value("${jwt.secret}") String secret, UserDetailsService userDetailsService) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.userDetailsService = userDetailsService;
    }

    public TokenDto generateTokenDto(String email){
        return TokenDto.builder()
                .grantType(BEARER_TYPE)
                .accessToken(generateAccessToken(email))
                .refreshToken(generateRefreshToken(email))
                .accessTokenExpiresIn(ACCESS_TOKEN_EXPIRE_TIME)
                .refreshTokenExpiresIn(REFRESH_TOKEN_EXPIRE_TIME)
                .build();
    }


    private String generateAccessToken(String email) {
        long now = (new Date()).getTime();

        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);

        return Jwts.builder()
                .setSubject(email)
                .claim(AUTHORITIES_KEY, "JWT")
                .setIssuedAt(new Date())
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    private String generateRefreshToken(String email) {

        long now = (new Date()).getTime();

        Date refreshTokenExpiresIn = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Authentication getAuthentication(String accessToken){
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);
        if(claims.get(AUTHORITIES_KEY) == null){
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
        String email = claims.getSubject();
        UserDetails principal = userDetailsService.loadUserByUsername(email);

        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());

    }

    public String parseRefreshToken(String refreshToken){
        if (refreshToken.startsWith(BEARER_TYPE)) {
            return refreshToken.replace(BEARER_TYPE, "");

        } else {
            return null;
        }
    }

    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new TokenExpirationException();
        } catch (JwtException e) {
            throw new TokenNotValidException();
        }
    }
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
