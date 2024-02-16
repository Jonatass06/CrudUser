package com.user.crud.configuration;

import com.user.crud.models.Authorization;
import com.user.crud.models.MyUserDetailsEntity;
import com.user.crud.models.User;
import com.user.crud.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor
public class DatabaseConfig {
    private final UserRepository userRepository;

    @PostConstruct
    public void init(){

        User user = new User();
        user.setName("mi72");
        user.setMyUserDetailsEntity(
                MyUserDetailsEntity.builder()
                        .username("teste")
                        .password("teste123")
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
