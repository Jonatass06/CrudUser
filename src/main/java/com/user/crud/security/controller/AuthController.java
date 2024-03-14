package com.user.crud.security.controller;


import com.user.crud.security.model.dto.UserLogin;
import com.user.crud.security.utils.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@AllArgsConstructor
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final CookieUtil cookieUtil = new CookieUtil();

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody UserLogin userLogin, HttpServletRequest request,
                                               HttpServletResponse response) {

        try {
            // Cria um token de autenticação
            Authentication authenticationToke =
                    new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword());
            // Autentica o usuario usando os aitenticação providers
            Authentication authentication = authenticationManager.authenticate(authenticationToke);

            // Criando um contexto novo
//            SecurityContext context = SecurityContextHolder.createEmptyContext();
            // Adiciona a autenticação ao contexto
//            context.setAuthentication(authentication);
            // Salva o contexto na repository
//            securityContextRepository.saveContext(context, request, response);

            UserDetails user = (UserDetails) authentication.getPrincipal();
            Cookie cookie = cookieUtil.generateCookieJwt(user);
            response.addCookie(cookie);
            return ResponseEntity.ok("User authenticated");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @PostMapping("/logout")
    public void logout (HttpServletRequest request, HttpServletResponse response){
        try {
            Cookie cookie = cookieUtil.getCookie(request, "JWT");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        } catch (Exception e) {
            response.setStatus(401);
        }
    }
}
