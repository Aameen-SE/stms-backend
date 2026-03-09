package com.aameen.stms.controller;

import com.aameen.stms.dto.LoginRequestDto;
import com.aameen.stms.dto.LoginResponseDto;
import com.aameen.stms.dto.UserRequestDto;
import com.aameen.stms.dto.UserResponseDto;
import com.aameen.stms.entity.User;
import com.aameen.stms.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service=service;
    }

    //create user

    @PostMapping("/register")
    public UserResponseDto create(@Valid @RequestBody UserRequestDto request){
        return service.createUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto request){
        return ResponseEntity.ok(service.login(request));
    }


}
