//package com.user.crud.models;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//@AllArgsConstructor
//@Entity
//@NoArgsConstructor
//@Data
//public class MyUserDetailsImpl implements UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//    private Integer age;
//    private String email;
//
//    private Boolean isActive = true;
//    @OneToOne(cascade = CascadeType.ALL)
//    private Archive picture;
//
//    private boolean accountNonExpired = true;
//    private boolean accountNonLocked = true;
//    private boolean credentialsNonExpired = true;
//    private boolean enabled = true;
//    private String password;
//    private String username;
//    private GrantedAuthority authorities;
//
//
//}
