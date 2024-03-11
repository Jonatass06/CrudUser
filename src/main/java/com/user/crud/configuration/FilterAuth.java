package com.user.crud.configuration;

import com.user.crud.utils.CookieUtil;
import com.user.crud.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class FilterAuth extends OncePerRequestFilter {

    private JwtUtil jwtUtil = new JwtUtil();
    private CookieUtil cookieUtil = new CookieUtil();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
    Cookie cookie = cookieUtil.getCookie(request, "JWT");
    String token = cookie.getValue();
    jwtUtil.validateToken(token);
    filterChain.doFilter(request, response);
    }
}
