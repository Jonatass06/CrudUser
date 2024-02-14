package com.user.crud.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Data

public class MyUserDetailsEntity implements UserDetails{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @NonNull
    private User user;
    private GrantedAuthority authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
    @Column(nullable = false)
    @Length(min = 6)
    private String password;
    @Email
    @Column(unique = true, updatable = false)
    private String username;

}
