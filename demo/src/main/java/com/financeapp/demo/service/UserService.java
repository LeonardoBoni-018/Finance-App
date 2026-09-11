package com.financeapp.demo.service;

import com.financeapp.demo.dto.request.UserRequestDTO;
import com.financeapp.demo.dto.response.UserResponseDTO;

public interface UserService {

    UserResponseDTO register(UserRequestDTO dto);

    UserResponseDTO findById(Long id);
}

