package com.user.crud.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
    public void validateToken(String token){

        getParser().parseClaimsJws(token);
    }

    public String getUsername(String token) {
        return getParser().parseClaimsJws(token).getPayload().getSubject();
    }
}
