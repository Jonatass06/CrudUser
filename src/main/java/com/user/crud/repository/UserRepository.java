package com.user.crud.repository;

import com.user.crud.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findUserByMyUserDetailsEntity_Username(String username);

}
