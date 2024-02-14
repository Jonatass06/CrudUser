package com.user.crud.service;

import com.user.crud.models.MyUserDetailsEntity;
import com.user.crud.models.User;
import com.user.crud.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private UserRepository  userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByMyUserDetailsEntity_Username(username);
        if(userOptional.isPresent()){
            return new MyUserDetailsEntity(userOptional.get());
        }
        throw new UsernameNotFoundException("User not found");
    }
}
