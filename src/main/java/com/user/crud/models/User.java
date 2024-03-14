package com.user.crud.models;

import com.user.crud.security.model.entity.MyUserDetailsEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private MyUserDetailsEntity myUserDetailsEntity;
    private String name;
    private Integer age;
    private String email;
    private Boolean isActive = true;
    @OneToOne(cascade = CascadeType.ALL)
    private Archive picture;
}