package com.user.crud.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.impl.JWTParser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtUtil{



    public String generateToken(UserDetails userDetails) {
         Algorithm algorithm = Algorithm.HMAC256("/MortandelacomQueijo123/");
        return JWT.create()
                .withIssuer("WEG")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() + 300000))
//                Senha geral para assinar o token
                .withSubject(userDetails.getUsername())
                .sign(algorithm);
    }
    public String getUsername(String token) {
        return JWT.decode(token).getSubject();
    }
}
