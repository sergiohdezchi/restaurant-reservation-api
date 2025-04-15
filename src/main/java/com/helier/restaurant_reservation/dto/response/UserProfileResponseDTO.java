package com.helier.restaurant_reservation.dto.response;

import com.helier.restaurant_reservation.domain.enums.Role;

import lombok.Data;

@Data
public class UserProfileResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
