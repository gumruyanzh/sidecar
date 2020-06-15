package com.gumruyan.sidecar.controller;

import com.gumruyan.sidecar.auth.AuthResponseObject;
import com.gumruyan.sidecar.entity.User;
import com.gumruyan.sidecar.model.UserAuthModel;
import com.gumruyan.sidecar.service.UserService;
import com.gumruyan.sidecar.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("CustomUserDetailServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;


    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserAuthModel userAuthModel) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userAuthModel.getUsername(), userAuthModel.getPassword()));
        } catch (AuthenticationException e) {
            log.warn("invalid username or password", e);
            throw new RuntimeException("Incorrect username or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(userAuthModel.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponseObject(jwt));
    }
}
