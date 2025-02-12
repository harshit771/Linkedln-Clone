package com.practice.linkedln.userService.service;

import com.practice.linkedln.userService.dto.LoginRequestDto;
import com.practice.linkedln.userService.dto.SignUpRequestDto;
import com.practice.linkedln.userService.dto.UserDto;

public interface AuthService {

    UserDto signUp(SignUpRequestDto signupRequestDto);

    String login(LoginRequestDto loginRequestDto);

}
