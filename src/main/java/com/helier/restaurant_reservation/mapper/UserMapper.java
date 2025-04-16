package com.helier.restaurant_reservation.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.helier.restaurant_reservation.domain.entity.User;
import com.helier.restaurant_reservation.dto.request.SignupRequestDTO;
import com.helier.restaurant_reservation.dto.response.AuthResponseDTO;
import com.helier.restaurant_reservation.dto.response.UserProfileResponseDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserProfileResponseDTO toUserProfileResponseDTO(User user) {
        return modelMapper.map(user, UserProfileResponseDTO.class);
    }

    public AuthResponseDTO toAuthResponseDTO(String token, UserProfileResponseDTO userProfile) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token);
        authResponseDTO.setUser(userProfile);
        return authResponseDTO;
    }

    public User toUser(SignupRequestDTO signupRequestDTO) {
        return modelMapper.map(signupRequestDTO, User.class);
    }
}
