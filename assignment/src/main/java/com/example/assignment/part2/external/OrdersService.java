package com.example.assignment.part2.external;

import com.example.assignment.part2.entity.Item;

import java.util.List;

public interface OrdersService {

    List<Item> itemsBought(String username);
}
