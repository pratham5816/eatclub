package com.myeatclub.eatClubApp.controller;

import com.myeatclub.eatClubApp.entity.UserData;
import com.myeatclub.eatClubApp.service.TwillioService;

// import static com.myeatclub.eatClubApp.service.TwillioService.sendTwillioMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class DetailsFetcher {
    // this fuctionality is to receive user data from frontend and forward it to twillio;


    @Autowired
    TwillioService twillioService;

    @PostMapping("/send-user-data")
    public ResponseEntity<String> checkData(@RequestBody UserData userData) {
        
        // Run in background thread - don't wait
        new Thread(() -> {
            try {
                twillioService.sendTwillioMessage(userData);
            } catch (Exception e) {
                // System.err.println("Error in background thread: " + e.getMessage());
                // e.printStackTrace();
            }
        }).start();
        
        // Return immediately to frontend
        return ResponseEntity.ok("Request received! We'll send you an SMS when the restaurant queue clears.");
    }
}
