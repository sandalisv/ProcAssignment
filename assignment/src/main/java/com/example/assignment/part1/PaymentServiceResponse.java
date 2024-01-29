package com.example.assignment.part1;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PaymentServiceResponse {
    private final String status;
    private String reason;
    private LocalDateTime eta;

    PaymentServiceResponse(String status) {
        this.status = status;
    }

    static PaymentServiceResponse ok() {
        return new PaymentServiceResponse("UP");
    }

    static PaymentServiceResponse failure(String reason, LocalDateTime eta) {
        PaymentServiceResponse response = new PaymentServiceResponse("DOWN");
        response.reason = reason;

        response.eta = eta;
        return response;
    }
}
