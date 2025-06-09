package com.gsm.domain.auth.service;

import com.gsm.domain.auth.presentation.data.reqest.SignupReqDTO;
import com.gsm.domain.user.entity.User.User;
import com.gsm.domain.user.enums.Role;
import com.gsm.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UUID signup(SignupReqDTO dto) {
        return userRepository.findByEmail(dto.getEmail())
                .map(User::getId)
                .orElseGet(() -> newUser(dto));
    }

    private UUID newUser(SignupReqDTO dto) {
        User user = User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .grade(dto.getGrade())
                .classNum(dto.getClassNum())
                .stuNum(dto.getStuNum())
                .role(Role.ROLE_STUDENT)
                .build();

        return userRepository.save(user).getId();
    }
}

