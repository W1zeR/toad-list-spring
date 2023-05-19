package com.w1zer.controller;

import com.w1zer.model.AuthRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/authenticate")
    public String authenticate(@Valid @RequestBody AuthRequest authRequest) {
        // TODO Check login and password
        return Jwts.builder()
                .setIssuer(authRequest.getLogin())
                .addClaims(Map.of("amm_role", "ROLE_USER"))
                .setExpiration(new Date(System.currentTimeMillis() + 300000))
                .signWith(SignatureAlgorithm.HS256, "secretKey")
                .compact();
    }

    @PostMapping("/validate")
    public Boolean validate(@RequestBody String token) {
        Jwts.parser().setSigningKey("secretKey").parse(token).getBody();
        return true;
    }
}
