package com.helier.restaurant_reservation.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.helier.restaurant_reservation.domain.entity.District;
import com.helier.restaurant_reservation.dto.response.DistrictResponseDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DistrictMapper {

    private final ModelMapper modelMapper;

    public DistrictResponseDTO toResponseDTO(District district) {
        return modelMapper.map(district, DistrictResponseDTO.class);
    }

    public List<DistrictResponseDTO> toResponseDTOList(List<District> districts) {
        return districts.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
