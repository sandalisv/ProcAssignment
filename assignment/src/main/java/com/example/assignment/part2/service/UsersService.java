package com.example.assignment.part2.service;

import com.example.assignment.part2.entity.Item;
import com.example.assignment.part2.external.OrdersService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final OrdersService ordersService;

    @Autowired
    public UsersService(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Transactional
    public int getNumberOfItemsBought(String username) {
        List<Item> items = ordersService.itemsBought(username);
        System.out.println("Items from OrdersService: " + items);
        return items.size();
    }

}
