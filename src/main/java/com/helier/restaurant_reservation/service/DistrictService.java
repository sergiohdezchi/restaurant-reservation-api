package com.helier.restaurant_reservation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helier.restaurant_reservation.domain.entity.District;
import com.helier.restaurant_reservation.dto.response.DistrictResponseDTO;
import com.helier.restaurant_reservation.mapper.DistrictMapper;
import com.helier.restaurant_reservation.repository.DistrictRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DistrictService {
    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;
    
    @Transactional(readOnly = true)
    public List<DistrictResponseDTO> getAllDistricts() {
        List<District> districts = districtRepository.findAll();
        return districtMapper.toResponseDTOList(districts);
    }
}
