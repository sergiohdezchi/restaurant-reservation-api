package com.helier.restaurant_reservation.dto.request;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String email;
    private String password;
}
