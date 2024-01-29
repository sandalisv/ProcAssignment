package com.example.assignment.part1;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    public PaymentServiceResponse check() throws PaymentServiceException {
        // Simulate a check to determine the health of the payment service
        boolean isPaymentServiceUp = performHealthCheck();
        if (isPaymentServiceUp) {
            return PaymentServiceResponse.ok();
        } else {
            return PaymentServiceResponse.failure("maintenance", LocalDateTime.now());
        }

    }

    private boolean performHealthCheck() {
        return true;
    }
}
