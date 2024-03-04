package com.example.parking_meter.service;

import com.example.parking_meter.dto.EndRegisterDto;
import com.example.parking_meter.model.Register;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RegisterService {
    Register startRegister(String plate);

    EndRegisterDto endRegister(String registerId);

    Page<Register> findAll(Pageable pageable);
}
