package com.helier.restaurant_reservation.service;

import java.util.Base64;
import java.util.Collections;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import com.helier.restaurant_reservation.domain.entity.Reservation;
import com.helier.restaurant_reservation.dto.paypal.Amount;
import com.helier.restaurant_reservation.dto.paypal.ApplicationContext;
import com.helier.restaurant_reservation.dto.paypal.OrderCaptureResponse;
import com.helier.restaurant_reservation.dto.paypal.OrderRequest;
import com.helier.restaurant_reservation.dto.paypal.OrderResponse;
import com.helier.restaurant_reservation.dto.paypal.PurchaseUnit;
import com.helier.restaurant_reservation.dto.paypal.TokenResponse;
import com.helier.restaurant_reservation.exception.ResourceNotFoundException;
import com.helier.restaurant_reservation.repository.ReservationRepository;
import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PaypalService {
    @Value("${paypal.client-id}")
    private String clientId;

    @Value("${paypal.client-secret}")
    private String clientSecret;

    @Value("${paypal.api-base}")
    private String apiBase;

    @NonNull
    private ReservationRepository reservationRepository;

    private RestClient paypalClient;

    @PostConstruct
    public void init() {
        paypalClient = RestClient
                .builder()
                .baseUrl(apiBase)
                .build();
    }

    private String getAccessToken() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        return Objects.requireNonNull(paypalClient.post()
                        .uri("/v1/oauth2/token")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()))
                        .body(body)
                        .retrieve()
                        .toEntity(TokenResponse.class)
                        .getBody())
                .getAccessToken();
    }

    public OrderResponse createOrder(Long reservationId, String returnUrl, String cancelUrl) {
        Reservation reservation = reservationRepository
                .findById(reservationId)
                .orElseThrow(ResourceNotFoundException::new);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setIntent("CAPTURE");

        Amount amount = new Amount();
        amount.setCurrencyCode("USD");
        amount.setValue(reservation.getTotalAmount().toString());

        PurchaseUnit purchaseUnit = new PurchaseUnit();
        purchaseUnit.setReferenceId(reservation.getId().toString());
        purchaseUnit.setAmount(amount);

        orderRequest.setPurchaseUnits(Collections.singletonList(purchaseUnit));

        ApplicationContext applicationContext = ApplicationContext
                .builder()
                .brandName("EDteamReserve")
                .returnURL(returnUrl)
                .cancelURL(cancelUrl)
                .build();

        orderRequest.setApplicationContext(applicationContext);

        return paypalClient.post()
                .uri("/v2/checkout/orders")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .body(orderRequest)
                .retrieve()
                .toEntity(OrderResponse.class)
                .getBody();
    }

    public OrderCaptureResponse captureOrder(String orderId) {
        return paypalClient.post()
                .uri("v2/checkout/orders/{order_id}/capture", orderId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(OrderCaptureResponse.class)
                .getBody();
    }
}
