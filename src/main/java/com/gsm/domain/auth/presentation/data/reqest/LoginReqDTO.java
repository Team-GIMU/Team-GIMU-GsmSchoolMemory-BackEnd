package com.gsm.domain.auth.presentation.data.reqest;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginReqDTO {

    private String email;
    private String password;

}
