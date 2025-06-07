package com.gsm.domain.auth.service;

import com.gsm.domain.auth.exception.TokenNotFoundException;
import com.gsm.domain.auth.presentation.data.response.TokenDto;
import com.gsm.domain.auth.repository.RefreshTokenRepository;
import com.gsm.global.security.jwt.TokenProvider;
import com.gsm.global.security.util.RefreshToken;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReissueTokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    public TokenDto reissueToken(String refreshToken) {
        String parseRefreshToken = tokenProvider.parseRefreshToken(refreshToken);

        RefreshToken findRefreshToken = refreshTokenRepository.findByToken(parseRefreshToken)
                .orElseThrow(TokenNotFoundException::new);

        TokenDto tokenDto = tokenProvider.generateTokenDto(findRefreshToken.getEmail());

        saveRefreshToken(tokenDto.getRefreshToken(), findRefreshToken.getEmail());

        return tokenDto;
    }

    @Transactional
    public void saveRefreshToken(String token, String email) {
        RefreshToken refreshToken = RefreshToken.builder()
                .token(token)
                .email(email)
                .build();

        refreshTokenRepository.save(refreshToken);
    }
}
