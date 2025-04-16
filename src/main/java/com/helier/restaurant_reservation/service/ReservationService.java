package com.helier.restaurant_reservation.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helier.restaurant_reservation.domain.entity.Reservation;
import com.helier.restaurant_reservation.domain.entity.Restaurant;
import com.helier.restaurant_reservation.domain.entity.User;
import com.helier.restaurant_reservation.domain.enums.ReservationStatus;
import com.helier.restaurant_reservation.dto.request.ReservationRequestDTO;
import com.helier.restaurant_reservation.dto.response.ReservationResponseDTO;
import com.helier.restaurant_reservation.exception.ResourceNotFoundException;
import com.helier.restaurant_reservation.mapper.ReservationMapper;
import com.helier.restaurant_reservation.repository.ReservationRepository;
import com.helier.restaurant_reservation.repository.RestaurantRepository;
import com.helier.restaurant_reservation.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final ReservationMapper reservationMapper;

    @Transactional
    public ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findOneByEmail(authentication.getName())
            .orElseThrow(ResourceNotFoundException::new);

        Restaurant restaurant = restaurantRepository.findById(reservationRequestDTO.getRestaurantId())
        .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        Reservation reservation = reservationMapper.toEntity(reservationRequestDTO);
        reservation.setRestaurant(restaurant);
        reservation.setClient(user);
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.calculateTotalAmount();

        reservation = reservationRepository.save(reservation);

        return reservationMapper.toResponseDto(reservation);
    }

    @Transactional(readOnly = true)
    public List<ReservationResponseDTO> getReservationsByClientId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findOneByEmail(authentication.getName())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Reservation> reservations = reservationRepository.findByClientId(user.getId());
        return reservationMapper.toResponseDtoList(reservations);
    }

    @Transactional(readOnly = true)
    public ReservationResponseDTO getReservationById(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));

        return reservationMapper.toResponseDto(reservation);
    }


    @Transactional
    public Reservation confirmReservationPayment(Long reservationId) {
        Reservation reservation = reservationRepository
                .findById(reservationId)
                .orElseThrow(ResourceNotFoundException::new);

        reservation.setStatus(ReservationStatus.PAID);
        return reservationRepository.save(reservation);
    }
}
