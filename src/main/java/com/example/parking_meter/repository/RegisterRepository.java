package com.example.parking_meter.repository;

import com.example.parking_meter.model.Register;
import org.springframework.data.mongodb.repository.MongoRepository;

public class RegisterRepository extends MongoRepository<Register, String> {

}
