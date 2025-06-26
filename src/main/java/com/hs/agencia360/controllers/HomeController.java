package com.hs.agencia360.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "📚 Bienvenido a Agencia360 API - Plataforma de Agencias de Turismo REST (Spring Boot)";
    }
}