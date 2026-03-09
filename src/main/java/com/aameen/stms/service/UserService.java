package com.aameen.stms.service;

import com.aameen.stms.dto.LoginRequestDto;
import com.aameen.stms.dto.LoginResponseDto;
import com.aameen.stms.dto.UserRequestDto;
import com.aameen.stms.dto.UserResponseDto;
import com.aameen.stms.entity.User;
import com.aameen.stms.exception.DuplicateResourceException;
import com.aameen.stms.exception.InvalidPasswordException;
import com.aameen.stms.exception.UserNotFoundException;
import com.aameen.stms.repository.UserRepository;
import com.aameen.stms.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil util;



    public UserService(UserRepository repo,PasswordEncoder encoder,JwtUtil util){
        this.repo=repo;
        this.encoder=encoder;
        this.util=util;
    }



    public UserResponseDto createUser(UserRequestDto request){

        if(repo.existsByEmail(request.getEmail())){
            throw new DuplicateResourceException("Email already exists !");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role("ROLE_USER")
                .active(true)
                .build();
        User savedUser = repo.save(user);

        return UserResponseDto.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .active(savedUser.isActive())
                .build();
    }

    public LoginResponseDto login(LoginRequestDto request){

        User user = repo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if(!encoder.matches(request.getPassword(), user.getPassword())){
            throw new InvalidPasswordException("Password is invalid");
        }

        String token = util.generateToken(user.getEmail());

        return new LoginResponseDto(token,user.getEmail(),user.getRole());
    }


}
