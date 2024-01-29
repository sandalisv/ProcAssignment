package com.example.assignment.part2.controller;

import com.example.assignment.part2.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/{username}/items/total")
    @ResponseBody
    public Map<String, Integer> totalItemsBought(@PathVariable String username) {
        int totalItemsBought = usersService.getNumberOfItemsBought(username);
        return Collections.singletonMap("totalItemsBought", totalItemsBought);
    }
}
