package com.example.assignment.part2.config;

import com.example.assignment.part2.service.UsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.example.assignment.part2.external.OrdersService;

@Configuration
@ComponentScan(basePackages = {"com.example.assignment.part2", "com.example.assignment.part2.external"})
public class AppConfiguration {

    @Bean
    public UsersService usersService(OrdersService ordersService) {
        return new UsersService(ordersService);
    }
}
