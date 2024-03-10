package com.example.parking_meter.controller;

import com.example.parking_meter.dto.CreatePriceDto;
import com.example.parking_meter.service.impl.PriceServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/price")
public class PriceController {
    @Autowired
    private PriceServiceImpl priceServiceImpl;

    @PostMapping
    public ResponseEntity startRegister(@RequestBody @Valid CreatePriceDto createPriceDto) {
        Long price = createPriceDto.price().longValue();
        return this.priceServiceImpl.create(price);
    }
}
