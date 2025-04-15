package com.helier.restaurant_reservation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helier.restaurant_reservation.domain.entity.Restaurant;
import com.helier.restaurant_reservation.dto.response.RestaurantResponseDTO;
import com.helier.restaurant_reservation.exception.ResourceNotFoundException;
import com.helier.restaurant_reservation.mapper.RestaurantMapper;
import com.helier.restaurant_reservation.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Transactional(readOnly = true)
    public Page<RestaurantResponseDTO> getAllRestaurants(Pageable pageable) {
        Page<Restaurant> restaurants = restaurantRepository.findAll(pageable);
        return restaurants.map(restaurantMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public Page<RestaurantResponseDTO> getRestaurantsByDistrictName(String districtName, Pageable pageable) {
        Page<Restaurant> restaurants = restaurantRepository.findByDistrictName(districtName, pageable);
        return restaurants.map(restaurantMapper::toResponseDTO);
    }
    

    public RestaurantResponseDTO getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));
        return restaurantMapper.toResponseDTO(restaurant);
    }
}
