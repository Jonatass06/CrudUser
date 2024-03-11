package com.user.crud.configuration;

import com.user.crud.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@AllArgsConstructor
@Configuration
public class Beans {

    private final AuthenticationService authenticationService;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper =  new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    @Bean
    public AuthenticationManager authenticationManager( )  {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
//        dao.setPasswordEncoder(new BCryptPasswordEncoder());
        dao.setUserDetailsService(authenticationService);
        return new ProviderManager(dao);

    }

    @Bean
    public CorsConfigurationSource corsConfig(){
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOrigins(List.of("http://localhost:3000"));
        cors.setAllowedMethods(List.of("POST", "PUT", "GET", "PATCH", "DELETE"));
        cors.setAllowCredentials(true);

    }


//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth,
//                                AuthenticationService authenticationService) throws Exception {
//        auth.userDetailsService(authenticationService)
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return authenticationService;
//    }

}
