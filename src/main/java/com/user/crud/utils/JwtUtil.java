package com.user.crud.utils;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Date;

public class JwtUtil{

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().
                issuer("WEG")
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 300000))
//                Senha geral para assinar o token
                .signWith(SignatureAlgorithm.NONE, "/#mortandelaComQueijo#/")
                .subject(userDetails.getUsername())
                .compact();
    }


    private JwtParser getParser(){
        return Jwts.parser().setSigningKey("/#mortandelaComQueijo#/").build();
    }
    private Jws<Claims> validateToken(String token){
        return getParser().parseClaimsJws(token);
    }

    public String getUsername(String token) {
        return validateToken(token).getPayload().getSubject();
    }
}
