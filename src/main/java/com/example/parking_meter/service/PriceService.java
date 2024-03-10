package com.example.parking_meter.service;

import org.springframework.http.ResponseEntity;

public interface PriceService {
    ResponseEntity create(Long price);
}
