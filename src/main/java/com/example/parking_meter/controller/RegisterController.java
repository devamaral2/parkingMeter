package com.example.parking_meter.controller;

import com.example.parking_meter.dto.CreateRegisterDto;
import com.example.parking_meter.model.Register;
import com.example.parking_meter.service.impl.RegisterServiceImpl;
import jakarta.validation.Valid;
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
    public ResponseEntity startRegister(@RequestBody @Valid CreateRegisterDto createRegisterDto) {
        String plate = createRegisterDto.vehiclePlate();
        String parkingMeterId = createRegisterDto.parkingMeterId();
        return this.registerServiceImpl.startRegister(plate, parkingMeterId);
    }

    @PatchMapping("/{registerId}")
    public ResponseEntity endRegister(@PathVariable String registerId) {
        return this.registerServiceImpl.endRegister(registerId);
    }

    @GetMapping
    public ResponseEntity<Page<Register>> findAll(Pageable pageable) {
        Page<Register> registers = this.registerServiceImpl.findAll(pageable);
        return ResponseEntity.ok(registers);
    }
}
