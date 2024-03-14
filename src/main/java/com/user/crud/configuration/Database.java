package com.user.crud.configuration;

import com.user.crud.security.model.enums.Authorization;
import com.user.crud.security.model.entity.MyUserDetailsEntity;
import com.user.crud.models.User;
import com.user.crud.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor
public class Database {
    private final UserRepository userRepository;

    @PostConstruct
    public void init(){

        User user = new User();
        user.setName("mi72");
        user.setName(("Teste"));
        user.setMyUserDetailsEntity(
                MyUserDetailsEntity.builder()
                        .username("teste")
                        .password(new BCryptPasswordEncoder().encode("teste123"))
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .enabled(true)
                        .authorities(List.of(Authorization.POST, Authorization.GET))
                        .user(user)
                        .build()
        );
        userRepository.save(user);
    }
}
