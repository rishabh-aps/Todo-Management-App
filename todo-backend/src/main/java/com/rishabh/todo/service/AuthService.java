package com.rishabh.todo.service;

import com.rishabh.todo.dto.JwtAuthResponse;
import com.rishabh.todo.dto.LoginDto;
import com.rishabh.todo.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginDto loginDto);
}
