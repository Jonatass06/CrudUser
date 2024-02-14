package com.user.crud.configuration;

import com.user.crud.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@AllArgsConstructor
@Configuration
public class ApiConfig {

   //@Bean
   //public UserDetailsManager inMemoryUser(){
   //    UserDetails user = User.withDefaultPasswordEncoder()
   //            .username("mi72")
   //            .password("M!7dois")
   //            .build();
   //     return new InMemoryUserDetailsManager(user);
   //}

    private final AuthenticationService authenticationService;

    @Bean
    public UserDetailsService userDetailsService() {
        return authenticationService;
    }
}
