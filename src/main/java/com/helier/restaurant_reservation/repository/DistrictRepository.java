package com.helier.restaurant_reservation.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.helier.restaurant_reservation.domain.entity.District;

public interface DistrictRepository extends JpaRepository<District, Long> {
    Optional<District> findByName(String name);
}
 