package com.wigner.helpdesk.resources;

import com.wigner.helpdesk.domain.dtos.CredenciaisDto;
import com.wigner.helpdesk.domain.dtos.JwtResponseDto;
import com.wigner.helpdesk.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody @Valid CredenciaisDto credenciaisDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(credenciaisDto.getEmail(), credenciaisDto.getSenha());
        System.out.println("usernamePassword -> " + usernamePassword);
        System.out.println("Email -> " + credenciaisDto.getEmail());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken(credenciaisDto.getEmail());
        System.out.println("token -> " + token);

        System.out.println(tokenService.validateToken(token));

        return ResponseEntity.ok(new JwtResponseDto(token));
    }

}
