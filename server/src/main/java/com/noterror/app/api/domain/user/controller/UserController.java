package com.noterror.app.api.domain.user.controller;

import com.noterror.app.api.domain.user.oauth.PrincipalDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/oauth/google")
    public ResponseEntity index(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        String email = principalDetails.getAttribute("email");

        return new ResponseEntity(email, HttpStatus.OK);
    }
}
