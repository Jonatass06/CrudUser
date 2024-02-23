package com.user.crud.controller;


import com.user.crud.models.dtos.UserLogin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private SecurityContextRepository securityContextRepository;
    @PatchMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody UserLogin userLogin, HttpServletRequest request, HttpServletResponse response) {
        try {
            // Cria um token de autenticação
            UsernamePasswordAuthenticationToken authenticationToke =
                    new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword());
            // Autentica o usuario usando os aitenticação providers
            Authentication authentication = authenticationManager.authenticate(authenticationToke);
            // Criando um contexto novo
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            // Adiciona a autenticação ao contexto
            context.setAuthentication(authentication);
//            SecurityContextHolder.setContext(context);
            // Salva o contexto na repository
            securityContextRepository.saveContext(context, request, response);
            return ResponseEntity.ok("User authenticated");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }


    }
}
