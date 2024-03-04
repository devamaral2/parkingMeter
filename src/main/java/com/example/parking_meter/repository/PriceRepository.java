package com.example.parking_meter.repository;

import com.example.parking_meter.model.Price;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PriceRepository extends MongoRepository<Price, String> {
}
