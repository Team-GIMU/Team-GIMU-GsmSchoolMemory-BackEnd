package com.gsm.domain.user.serivce;

import com.gsm.domain.auth.exception.UserNotFoundException;
import com.gsm.domain.user.entity.User.User;
import com.gsm.domain.user.enums.Role;
import com.gsm.domain.user.presentation.dto.request.RoleChangeRequest;
import com.gsm.domain.user.repository.UserRepository;
import com.gsm.global.annotation.RollbackService;
import lombok.RequiredArgsConstructor;

@RollbackService
@RequiredArgsConstructor
public class AdminRoleChangeService {

    private final UserRepository userRepository;

    public void execute(RoleChangeRequest roleChangeRequest) {

        String email = roleChangeRequest.getEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        user.updateRole(Role.ROLE_ADMIN);
    }
}
