package com.user.crud.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtUtil{

    private final SecretKey key;

    public JwtUtil (){
        PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("/mortandelaComQueijo/");
        this.key = Keys.hmacShaKeyFor( password.getBytes());
    }
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().
        issuer("WEG")
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 300000))
//                Senha geral para assinar o token
                .signWith(this.key, Jwts.SIG.HS256 )
                .subject(userDetails.getUsername())
                .compact();
    }
    private JwtParser getParser(){

        return Jwts.parser().verifyWith(this.key).build();
    }
    private Jws<Claims> validateToken(String token){
        return getParser().parseSignedClaims(token);
    }
    public String getUsername(String token) {
        return validateToken(token).getPayload().getSubject();
    }
}
