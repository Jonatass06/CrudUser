package com.user.crud.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
public class MyUserDetailsEntity implements UserDetails{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "myUserDetailsEntity")
    @JsonIgnore
    @ToString.Exclude
    private User user;
    private Collection<Authorization> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
    @Column(nullable = false)
    @Length(min = 6)
    private String password;
    @Column(unique = true, updatable = false, nullable = false)
    private String username;

}
