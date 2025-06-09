package com.gsm.domain.user.util;

import com.gsm.domain.auth.exception.UserNotFoundException;
import com.gsm.domain.user.entity.User.User;
import com.gsm.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }
}