package com.jjanelle.portfolio.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    PingController() {
    }

    @CrossOrigin
    @GetMapping("/ping")
    public void ping() {
    }

}
