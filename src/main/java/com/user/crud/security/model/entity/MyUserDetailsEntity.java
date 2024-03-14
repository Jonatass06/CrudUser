package com.user.crud.security.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.user.crud.security.model.enums.Authorization;
import com.user.crud.models.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
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
