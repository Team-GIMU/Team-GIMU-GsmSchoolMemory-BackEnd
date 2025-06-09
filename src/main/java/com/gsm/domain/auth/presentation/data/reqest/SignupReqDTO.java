package com.gsm.domain.auth.presentation.data.reqest;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignupReqDTO {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private int grade;

    @NotNull
    private int classNum;

    @NotNull
    private int stuNum;


}
