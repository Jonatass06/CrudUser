package com.user.crud.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private MyUserDetailsEntity myUserDetailsEntity;
    private String name;
    private Integer age;
    private String email;
    private Boolean isActive = true;
    @OneToOne(cascade = CascadeType.ALL)
    private Archive picture;


}
