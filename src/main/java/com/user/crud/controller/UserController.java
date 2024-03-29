package com.user.crud.controller;

import com.user.crud.models.User;
import com.user.crud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @PostMapping("/image")
    public User saveUser(@RequestParam String user, @RequestParam MultipartFile picture){
        return userService.saveUser(user, picture);
    }

    @PostMapping
    public User saveUser( @RequestBody User user){
        return null;
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @GetMapping
    public Collection<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PatchMapping("/{id}/password")
    public User updatePassword(@PathVariable Long id, @RequestBody String password){
        return userService.updatePassword(id, password);
    }

    @PatchMapping("/{id}/active")
    public User updateEnable(@PathVariable Long id, @RequestBody Boolean isEnable){
        return userService.updateEnable(id, isEnable);
    }

    @PatchMapping("/{id}/picture")
    public User updatePicture(@PathVariable Long id, @RequestParam MultipartFile picture){
        return userService.updatePicture(id, picture);
    }




}
