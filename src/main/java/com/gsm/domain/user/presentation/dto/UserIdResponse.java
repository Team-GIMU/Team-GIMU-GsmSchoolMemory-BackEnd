package com.gsm.domain.user.presentation.dto;

import com.gsm.domain.user.entity.User.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserIdResponse {
    private UUID id;

    public static UserIdResponse from(User user) {
        return new UserIdResponse(user.getId());
    }
}
