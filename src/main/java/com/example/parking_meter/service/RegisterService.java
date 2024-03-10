package com.example.parking_meter.service;

import com.example.parking_meter.model.Register;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RegisterService {
    ResponseEntity startRegister(String plate);

    ResponseEntity endRegister(String registerId);

    Page<Register> findAll(Pageable pageable);
}
