package com.user.crud.security.configs;

import com.user.crud.security.filter.FilterAuth;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;

import java.util.ArrayList;

@AllArgsConstructor
@Configuration
public class Security{

    private final FilterAuth filter;
    private final SecurityContextRepository repo;
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
        http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
//                              .requestMatchers(HttpMethod.GET, "/user").hasAuthority("Get")
//                              .requestMatchers(HttpMethod.GET, "/user").permitAll()
                                .requestMatchers(HttpMethod.GET, "/user/**").hasAuthority("GET")
//                                .requestMatchers(HttpMethod.GET, "/user/**").hasAnyAuthority("GET", "POST", "PUT", "DELETE")
                                .requestMatchers(HttpMethod.POST, "/login").permitAll()
//                              .requestMatchers(HttpMethod.POST, "/user/**").authenticated()
//                              .requestMatchers( "/user/**").permitAll()
                                .anyRequest().authenticated()
        );
        http.securityContext(context -> context.securityContextRepository(repo));
        http.sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        http.formLogin(AbstractHttpConfigurer::disable);
        http.logout(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
//      http.httpBasic(Customizer.withDefaults());]
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
