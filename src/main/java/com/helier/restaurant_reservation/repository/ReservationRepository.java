package com.helier.restaurant_reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.helier.restaurant_reservation.domain.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  @Query("SELECT r FROM Reservation r WHERE r.client.id = :clientId")
  List<Reservation> findByClientId(@Param("clientId") Long clientId);
}
