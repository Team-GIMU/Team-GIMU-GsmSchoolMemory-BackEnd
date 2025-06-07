package com.gsm.domain.auth.service;

import com.gsm.domain.auth.exception.PasswordNotMatchException;
import com.gsm.domain.auth.exception.UserNotFoundException;
import com.gsm.domain.auth.presentation.data.reqest.LoginReqDTO;
import com.gsm.domain.auth.presentation.data.response.TokenDto;
import com.gsm.domain.auth.repository.RefreshTokenRepository;
import com.gsm.domain.user.entity.User.User;
import com.gsm.domain.user.repository.UserRepository;
import com.gsm.global.security.jwt.TokenProvider;
import com.gsm.global.security.util.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public TokenDto login(LoginReqDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword()))
            throw new PasswordNotMatchException();

        TokenDto tokenDto = tokenProvider.generateTokenDto(user.getEmail());
        saveRefreshToken(tokenDto.getRefreshToken(), user.getEmail());

        return tokenDto;
    }

    private void saveRefreshToken(String token, String email) {
        RefreshToken refreshToken = RefreshToken.builder()
                .email(email)
                .token(token)
                .build();

        refreshTokenRepository.save(refreshToken);
    }
}