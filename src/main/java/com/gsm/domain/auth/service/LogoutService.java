package com.gsm.domain.auth.service;

import com.gsm.domain.auth.repository.RefreshTokenRepository;
import com.gsm.domain.user.entity.User.User;
import com.gsm.domain.user.util.UserUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class LogoutService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserUtil userUtil;

    public void logout() {
        User user = userUtil.getCurrentUser();
        refreshTokenRepository.deleteById(user.getEmail());
    }
}