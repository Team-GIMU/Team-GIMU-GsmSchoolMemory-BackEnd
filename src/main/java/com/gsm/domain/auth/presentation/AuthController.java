package com.gsm.domain.auth.presentation;

import com.gsm.domain.auth.presentation.data.reqest.LoginReqDTO;
import com.gsm.domain.auth.presentation.data.reqest.SignupReqDTO;
import com.gsm.domain.auth.presentation.data.response.TokenDto;
import com.gsm.domain.auth.service.LoginService;
import com.gsm.domain.auth.service.LogoutService;
import com.gsm.domain.auth.service.ReissueTokenService;
import com.gsm.domain.auth.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final SignupService signupService;
    private final LoginService loginService;
    private final LogoutService logoutService;
    private final ReissueTokenService reissueTokenService;

    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody SignupReqDTO dto) {
        signupService.signup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> signIn(@RequestBody LoginReqDTO dto) {
        TokenDto result = loginService.login(dto);
        return ResponseEntity.ok(result);
    }

    @PatchMapping
    public ResponseEntity<TokenDto> reissueToken(@RequestHeader String refreshToken) {
        return ResponseEntity.ok(reissueTokenService.reissueToken(refreshToken));
    }

    @DeleteMapping
    public ResponseEntity<Void> logout() {
        logoutService.logout();
        return ResponseEntity.noContent().build();
    }


}