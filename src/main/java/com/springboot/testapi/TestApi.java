package com.springboot.testapi;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {

    @GetMapping("/test")
    @RateLimiter(name = "externalApiLimiter", fallbackMethod = "testFallback")
    public String test() {
        return "Welcome to my API";
    }
    public String fallback(Throwable t) {
        // Here you can handle the penalty mechanism
        try {
            // Wait for an additional minute as penalty
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Rate limit exceeded. Waiting for penalty period.";
    }

    
}
