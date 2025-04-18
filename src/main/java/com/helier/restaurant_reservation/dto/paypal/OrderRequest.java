package com.helier.restaurant_reservation.dto.paypal;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderRequest {
    private String intent;

    @JsonProperty("purchase_units")
    private List<PurchaseUnit> purchaseUnits;

    @JsonProperty("application_context")
    private ApplicationContext applicationContext;
}
