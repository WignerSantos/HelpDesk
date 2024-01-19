package com.wigner.helpdesk.resources;

import com.wigner.helpdesk.domain.dtos.CredenciaisDto;
import com.wigner.helpdesk.domain.dtos.JwtResponseDto;
import com.wigner.helpdesk.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

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
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken(credenciaisDto.getEmail());

        return ResponseEntity.ok(new JwtResponseDto(token));
    }

}
