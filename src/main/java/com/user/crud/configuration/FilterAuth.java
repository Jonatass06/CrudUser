package com.user.crud.configuration;

import com.user.crud.service.AuthenticationService;
import com.user.crud.utils.CookieUtil;
import com.user.crud.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class FilterAuth extends OncePerRequestFilter {

//    Depende do atributo usado no subject do token
    private AuthenticationService authenticationService;
    private final SecurityContextRepository securityContextRepository;
    private final JwtUtil jwtUtil = new JwtUtil();
    private final CookieUtil cookieUtil = new CookieUtil();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //Busca e valida o token
        Cookie cookie = cookieUtil.getCookie(request, "JWT");
        String token = cookie.getValue();
        String username = jwtUtil.getUsername(token);

        //Cria usuario autenticado
        UserDetails userDetails = authenticationService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        // Criando um contexto novo
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        // Adiciona a autenticação ao contexto
        context.setAuthentication(authentication);
        // Salva o contexto na repository
        securityContextRepository.saveContext(context, request, response);

        //Continua a requisição
        filterChain.doFilter(request, response);
    }

    private Boolean piblicRequest(HttpServletRequest request){
        return request.getRequestURI().equals("/login") && request.getMethod().equals("POST");

    }
}
