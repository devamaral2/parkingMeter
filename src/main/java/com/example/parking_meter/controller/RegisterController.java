package com.example.parking_meter.controller;

import com.example.parking_meter.dtos.CreateRegisterDto;
import com.example.parking_meter.model.Register;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/register")
public class RegisterController {
    @PostMapping
    public Register startRegister(@RequestBody CreateRegisterDto createRegisterDto) {
         return null;
    }

    @PatchMapping("/{registerId}")
    public Register endRegister(@PathVariable String registerId) {
        return null;
    }
}
