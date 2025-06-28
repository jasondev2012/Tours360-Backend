package com.hs.tours360.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "📚 Bienvenido a tours360 API - Plataforma de Agencias de Turismo REST (Spring Boot)";
    }
}