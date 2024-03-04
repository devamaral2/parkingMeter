package com.example.parking_meter.repository;

import com.example.parking_meter.model.Register;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegisterRepository extends MongoRepository<Register, String> {
    Page<Register> findAll(Pageable pageable);
}
