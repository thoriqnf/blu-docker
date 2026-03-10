package com.devops.todo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class InfoController {

    @Value("${app.name:Todo API}")
    private String appName;

    @Value("${app.version:1.0.0}")
    private String appVersion;

    @Value("${app.environment:development}")
    private String environment;

    // GET / — App info (useful for verifying the container is running)
    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> info() {
        return ResponseEntity.ok(Map.of(
                "app", appName,
                "version", appVersion,
                "environment", environment,
                "timestamp", LocalDateTime.now().toString(),
                "status", "running"
        ));
    }
}
