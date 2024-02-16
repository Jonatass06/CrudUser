package com.user.crud.configuration;

import com.user.crud.models.Authorization;
import com.user.crud.models.MyUserDetailsEntity;
import com.user.crud.models.User;
import com.user.crud.repository.UserRepository;
import com.user.crud.service.AuthenticationService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;

@AllArgsConstructor
@Configuration
public class SecurityConfig {

   //@Bean
   //public UserDetailsManager inMemoryUser(){
   //    UserDetails user = User.withDefaultPasswordEncoder()
   //            .username("mi72")
   //            .password("M!7dois")
   //            .build();
   //     return new InMemoryUserDetailsManager(user);
   //}

    private final AuthenticationService authenticationService;


//    @Bean
//    public UserDetailsService userDetailsService() {
//        return authenticationService;
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers(HttpMethod.GET, "/user").hasAuthority("GET")
                        .requestMatchers(HttpMethod.POST, "/user").hasAuthority("POST")
//                        .requestMatchers(HttpMethod.POST, "/user/**").authenticated()
//                        .requestMatchers( "/user/**").permitAll()
                        .anyRequest().authenticated()
        );
//        http.formLogin(Customizer.withDefaults());
//        http.logout(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    //Context: Usuario autenticado
    //Filter: Filtrar as requisições
    //Manager: Gerenciar os providers
    //Provider: Provedor de autenticação
    //UserDetailsService: Serviço de detalhes do usuário
    //PasswordEncoder: Codificador de senha
}
