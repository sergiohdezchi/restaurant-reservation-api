package com.helier.restaurant_reservation.dto.paypal;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderResponse {
    private String id;
    private String status;

    @JsonProperty("payment_source")
    private PaymentSource paymentSource;

    private List<Link> links;
}
