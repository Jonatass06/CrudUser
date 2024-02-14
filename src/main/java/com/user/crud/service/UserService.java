package com.user.crud.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.crud.models.Archive;
import com.user.crud.models.User;
import com.user.crud.models.dtos.UserRegisterDTO;
import com.user.crud.models.dtos.UserUpdateDTO;
import com.user.crud.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public User saveUser(String userDTOString, MultipartFile picture){
        ObjectMapper objectMapper = new ObjectMapper();
        UserRegisterDTO userDTO = objectMapper.convertValue(userDTOString, UserRegisterDTO.class);
        Archive archive = new Archive(picture);
        User user = new User();
        modelMapper.map(userDTO, user);
        user.setPicture(archive);
        return userRepository.save(user);
    }

    public Collection<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public User updateUser(UserUpdateDTO userDTO){
        User user = userRepository.findById(userDTO.getId()).get();
        modelMapper.map(userDTO, user);
        return userRepository.save(user);
    }

    public User updatePassword(Long id, String password){
        User user = userRepository.findById(id).get();
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User updateEnable(Long id, Boolean isEnable){
        User user = userRepository.findById(id).get();
        user.setIsActive(isEnable);
        return userRepository.save(user);
    }

    public User updatePicture(Long id, MultipartFile picture){
        User user = userRepository.findById(id).get();
        Archive archive = new Archive(picture);
        user.setPicture(archive);
        return userRepository.save(user);
    }
}
