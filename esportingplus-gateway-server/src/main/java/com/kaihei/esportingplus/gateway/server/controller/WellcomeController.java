package com.kaihei.esportingplus.gateway.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class WellcomeController {

    @GetMapping("/status")
    public String welcome() {
        return "Welcome!";
    }

}