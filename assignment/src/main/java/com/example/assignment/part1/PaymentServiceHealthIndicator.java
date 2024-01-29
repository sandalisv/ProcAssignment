package com.example.assignment.part1;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component("payment-service")
public class PaymentServiceHealthIndicator implements HealthIndicator {

    private final PaymentService paymentService;

    public PaymentServiceHealthIndicator(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public Health health() {
        try {
            PaymentServiceResponse response = paymentService.check();
            if (response != null) {
                if ("UP".equals(response.getStatus())) {
                    return Health.up()
                            .build();
                } else {
                    return Health.down()
                            .withDetail("status", response.getStatus())
                            .withDetail("reason", response.getReason())
                            .withDetail("eta", formatEta(response.getEta()))
                            .build();
                }
            }
        } catch (PaymentServiceException e) {
            return Health.down(e)
                    .withDetail("status", "DOWN")
                    .build();
        }

        // Default to DOWN if there's an issue or unknown response
        return Health.down()
                .withDetail("status", "DOWN")
                .build();
    }

    private String formatEta(LocalDateTime eta) {
        if (eta != null) {
            return eta.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        return null;
    }
}
