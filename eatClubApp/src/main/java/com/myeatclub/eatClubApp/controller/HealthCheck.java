package com.myeatclub.eatClubApp.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class HealthCheck {
    @GetMapping("/health-check")
    public String healthCheck() {
        return "EatClubApp is up and running! YESSH";
    }
}
