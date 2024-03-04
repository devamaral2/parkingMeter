package com.example.parking_meter.service.impl;


import com.example.parking_meter.dto.EndRegisterDto;
import com.example.parking_meter.model.Price;
import com.example.parking_meter.model.Register;
import com.example.parking_meter.repository.RegisterRepository;
import com.example.parking_meter.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final MongoTemplate mongoTemplate;
    @Autowired
    RegisterRepository registerRepository;

    public RegisterServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Register startRegister(String plate) {
        Query query = new Query(Criteria.where("end_date").exists(false));
        Price actualPrice = this.mongoTemplate.findOne(query, Price.class);
        Register newRegister = Register
                .builder()
                .start_date(LocalDateTime.now())
                .veichle_plate(plate)
                .price(actualPrice)
                .build();
        return this.registerRepository.insert(newRegister);
    }

    @Override
    public EndRegisterDto endRegister(String registerId) {
        Register register = this.registerRepository.findById(registerId)
                .orElseThrow(() -> new IllegalArgumentException("Este registro não existe"));
//        this.registerRepository.save();
        return null;

    }

    public Page<Register> findAll(Pageable pageable) {
        Sort sort = Sort.by("start_date").descending();
        Pageable pageableWithSort = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort);
        Page<Register> response = this.registerRepository.findAll(pageableWithSort);
        return response;
    }
}