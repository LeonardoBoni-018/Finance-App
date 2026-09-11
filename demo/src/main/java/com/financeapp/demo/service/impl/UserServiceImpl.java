package com.financeapp.demo.service.impl;

import com.financeapp.demo.dto.request.UserRequestDTO;
import com.financeapp.demo.dto.response.UserResponseDTO;
import com.financeapp.demo.entity.User;
import com.financeapp.demo.exception.BusinessException;
import com.financeapp.demo.exception.ResourceNotFoundException;
import com.financeapp.demo.mapper.UserMapper;
import com.financeapp.demo.repository.UserRepository;
import com.financeapp.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponseDTO register(UserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new BusinessException("Já existe um usuário cadastrado com este e-mail");
        }

        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.password())); // nunca salvar em texto puro

        User saved = userRepository.save(user);
        return userMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + id));
        return userMapper.toResponseDTO(user);
    }
}
