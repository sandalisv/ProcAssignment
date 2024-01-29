package com.example.assignment.part2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.assignment.part2", "com.example.assignment.part2.external"})
public class AssignmentPart2Main {
    public static void main(String[] args) {
        SpringApplication.run(AssignmentPart2Main.class, args);
    }
}
