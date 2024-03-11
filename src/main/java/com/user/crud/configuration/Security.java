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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import java.util.ArrayList;

@AllArgsConstructor
@Configuration
public class Security{

    private final SecurityContextRepository repo;
    private final FilterAuth filter;
    //@Bean
    //public UserDetailsManager inMemoryUser(){
    //    UserDetails user = User.withDefaultPasswordEncoder()
    //            .username("mi72")
    //            .password("M!7dois")
    //            .build();
    //     return new InMemoryUserDetailsManager(user);
    //}


//    @Bean
//    public UserDetailsService userDetailsService() {
//        return authenticationService;
//    }

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
//      CSRF garante que realmente a requisição veio do cliente que está fazendo a requisição e não um invasor
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
//                              .requestMatchers(HttpMethod.GET, "/user").hasAuthority("Get")
//                              .requestMatchers(HttpMethod.GET, "/user").permitAll()
                                .requestMatchers(HttpMethod.GET, "/user").hasAuthority("GET")
//                              .requestMatchers(HttpMethod.POST, "/user/**").authenticated()
//                              .requestMatchers( "/user/**").permitAll()
                                .anyRequest().authenticated()
        );
//        http.securityContext(context -> context.securityContextRepository(repo));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//      http.httpBasic(Customizer.withDefaults());
        return http.build();
    }


//O Contexto serve para manter o contexto do usuario para manter a sessão do usuario


    //Context: Usuario autenticado
    //Filter: Filtrar as requisições
    //Manager: Gerenciar os providers
    //Provider: Provedor de autenticação
    //UserDetailsService: Serviço de detalhes do usuário
    //PasswordEncoder: Codificador de senha
}
