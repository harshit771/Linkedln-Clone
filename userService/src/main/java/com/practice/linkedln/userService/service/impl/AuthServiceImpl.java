package com.practice.linkedln.userService.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.practice.linkedln.userService.dto.LoginRequestDto;
import com.practice.linkedln.userService.dto.SignUpRequestDto;
import com.practice.linkedln.userService.dto.UserDto;
import com.practice.linkedln.userService.entity.User;
import com.practice.linkedln.userService.event.UserCreatedEvent;
import com.practice.linkedln.userService.exception.BadRequestException;
import com.practice.linkedln.userService.repository.UserRepository;
import com.practice.linkedln.userService.service.AuthService;
import com.practice.linkedln.userService.service.JwtService;
import com.practice.linkedln.userService.util.BCrypt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final KafkaTemplate kafkaTemplate;

    @Override
    public UserDto signUp(SignUpRequestDto signupRequestDto) {
        log.info("User signup with email " + signupRequestDto.getEmail());
        boolean exist = userRepository.existsByEmail(signupRequestDto.getEmail());

        if (exist) {
            throw new BadRequestException("User already sign up with email " + signupRequestDto.getEmail());
        }

        User user = modelMapper.map(signupRequestDto, User.class);
        user.setPassword(BCrypt.hash(signupRequestDto.getPassword()));

        user = userRepository.save(user);

        UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder().name(user.getName())
                                                .userId(user.getId()).build();

        
        kafkaTemplate.send("user_created_topic",userCreatedEvent);
        
        return modelMapper.map(user, UserDto.class);

    }

    @Override
    public String login(LoginRequestDto loginRequestDto) {
        log.info("Login request with email id "+loginRequestDto.getEmail());

        User user = 
                userRepository.findByEmail(loginRequestDto.getEmail()).
                orElseThrow(() -> new BadRequestException("Invalid email or password"));

        boolean isPasswordMatch = BCrypt.match(loginRequestDto.getPassword(), user.getPassword());

        if(!isPasswordMatch){
            throw new BadRequestException("Invalid email or password");
        }

        return jwtService.generateAccessToken(user);

    }

}
