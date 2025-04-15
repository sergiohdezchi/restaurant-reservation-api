package com.helier.restaurant_reservation.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.helier.restaurant_reservation.domain.entity.Restaurant;
import com.helier.restaurant_reservation.dto.response.RestaurantResponseDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RestaurantMapper {
    private final ModelMapper modelMapper;

    public RestaurantResponseDTO toResponseDTO(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantResponseDTO.class);
    }
}
