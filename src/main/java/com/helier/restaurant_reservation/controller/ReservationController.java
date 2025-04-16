package com.helier.restaurant_reservation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helier.restaurant_reservation.dto.request.ReservationRequestDTO;
import com.helier.restaurant_reservation.dto.response.ReservationResponseDTO;
import com.helier.restaurant_reservation.service.ReservationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(@RequestBody @Validated ReservationRequestDTO reservationRequestDTO) {
        ReservationResponseDTO reservationResponse = reservationService.createReservation(reservationRequestDTO);
        return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Long id) {
        ReservationResponseDTO reservationResponse = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservationResponse);
    }

    @GetMapping("/my-reservations")
    public ResponseEntity<List<ReservationResponseDTO>> getMyReservations() {
        List<ReservationResponseDTO> reservations = reservationService.getReservationsByClientId();
        return ResponseEntity.ok(reservations);
    }
}
