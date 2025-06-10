package com.gsm.domain.user.presentation;

import com.gsm.domain.user.presentation.dto.request.RoleChangeRequest;
import com.gsm.domain.user.serivce.AdminRoleChangeService;
import com.gsm.domain.user.serivce.UserRoleChangeService;
import com.gsm.global.annotation.RestRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestRequestService("/role")
public class RoleController {

    private final AdminRoleChangeService adminRoleChangeService;

    private final UserRoleChangeService userRoleChangeService;

    @PatchMapping("/grant")
    public ResponseEntity<Void> grant(@RequestBody RoleChangeRequest roleChangeRequest) {

        adminRoleChangeService.execute(roleChangeRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/revoke")
    public ResponseEntity<Void> revoke(@RequestBody RoleChangeRequest roleChangeRequest) {

        userRoleChangeService.execute(roleChangeRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
