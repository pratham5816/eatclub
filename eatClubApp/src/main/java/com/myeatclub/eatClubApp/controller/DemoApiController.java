package com.myeatclub.eatClubApp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/demo")
@CrossOrigin("http://localhost:3000")
public class DemoApiController {

    // Read custom.mysid from application.yaml with default value
    @Value("${custom.mysid:NOT_CONFIGURED}")
    private String customSid;

    @GetMapping("/get-mysid")
    public Map<String, Object> getMySid() {
        Map<String, Object> response = new HashMap<>();
        
        if (customSid != null && !customSid.isEmpty()) {
            response.put("success", true);
            response.put("mysid", customSid);
            response.put("message", "✅ Successfully retrieved custom.mysid");
        } else {
            response.put("success", false);
            response.put("mysid", null);
            response.put("message", "❌ custom.mysid is not set or empty");
        }
        
        return response;
    }

    @GetMapping("/test-env")
    public Map<String, Object> testEnvironment() {
        Map<String, Object> response = new HashMap<>();
        
        response.put("custom_mysid", customSid != null ? customSid : "NOT SET");
        response.put("twillio_sid_env", System.getenv("TWILLIO_SID"));
        response.put("message", "Environment variable test");
        
        return response;
    }
}
