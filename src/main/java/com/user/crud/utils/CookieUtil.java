package com.user.crud.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.util.WebUtils;

public class CookieUtil {

    public Cookie generateCookieJwt(UserDetails userDetails) {
        String token = new JwtUtil().generateToken(userDetails);
        Cookie cookie = new Cookie("JWT", token);
        cookie.setPath("/");
        cookie.setMaxAge(300);
        return cookie;
    }

    public Cookie getCookie(HttpServletRequest request, String name) throws Exception {
        Cookie cookie =  WebUtils.getCookie(request, name);
        if(cookie != null){
            return cookie;
        }
        throw new Exception("Cookie not found");
    }


}
