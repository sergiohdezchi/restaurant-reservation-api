package com.helier.restaurant_reservation.mapper;

import java.util.List;

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

    public List<RestaurantResponseDTO> toResponseDTOList(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
