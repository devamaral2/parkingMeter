package com.example.parking_meter.controller;

import com.example.parking_meter.dto.CreateRegisterDto;
import com.example.parking_meter.model.Register;
import com.example.parking_meter.service.impl.RegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private RegisterServiceImpl registerServiceImpl;

    @PostMapping
    public Register startRegister(@RequestBody CreateRegisterDto createRegisterDto) {
        String plate = createRegisterDto.vehiclePlate();
        return this.registerServiceImpl.startRegister(plate);
    }

    @PatchMapping("/{registerId}")
    public Register endRegister(@PathVariable String registerId) {
//        return this.registerServiceImpl.endRegister(registerId);
        return null;
    }

    @GetMapping
    public ResponseEntity<Page<Register>> findAll(Pageable pageable) {
        Page<Register> registers = this.registerServiceImpl.findAll(pageable);
        return ResponseEntity.ok(registers);
    }
}
