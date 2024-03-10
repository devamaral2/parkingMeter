package com.example.parking_meter.service.impl;


import com.example.parking_meter.controller.exception.StandardError;
import com.example.parking_meter.model.Price;
import com.example.parking_meter.model.Register;
import com.example.parking_meter.repository.RegisterRepository;
import com.example.parking_meter.service.RegisterService;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final MongoTemplate mongoTemplate;
    @Autowired
    RegisterRepository registerRepository;

    public RegisterServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    @Transactional
    public ResponseEntity startRegister(String plate) {
        Query query = new Query(Criteria.where("end_date").exists(false));

        Price actualPrice = this.mongoTemplate.findOne(query, Price.class);
        if (actualPrice == null) {
            StandardError err = StandardError.builder()
                    .timestamp(Instant.now())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .error("Não existe um price atual para vincular a um novo register")
                    .path("/register")
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }
        Register newRegister = Register
                .builder()
                .start_date(LocalDateTime.now())
                .veichle_plate(plate)
                .price(actualPrice)
                .build();
        try {

            Register createdRegister = this.registerRepository.insert(newRegister);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRegister);
        } catch (Exception e) {
            StandardError err = StandardError.builder()
                    .timestamp(Instant.now())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .error("Ocorreu algum erro ao criar o novo register")
                    .message(e.getMessage())
                    .path("/register")
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }
    }

    @Override
    @Transactional
    public ResponseEntity endRegister(String registerId) {
        Optional<Register> register = this.registerRepository.findById(registerId);
        if (register.isEmpty()) {
            StandardError err = StandardError.builder()
                    .timestamp(Instant.now())
                    .status(HttpStatus.NOT_FOUND.value())
                    .error("Não existe um register com esta id")
                    .path("/register")
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
        }
        System.out.println(register.get().getId());
        try {

            Query query = new Query(Criteria.where("id").is(registerId));
            Update update = new Update().set("end_date", LocalDateTime.now());
            UpdateResult reg = this.mongoTemplate.updateFirst(query, update, Register.class);
            Duration duration = Duration.between(register.get().getStart_date(), LocalDateTime.now().plusHours(2));
            System.out.println(duration.getUnits());
            System.out.println(duration);
            Long cost = duration.toHours() * register.get().getPrice().getPrice_per_hour();
            return ResponseEntity.status(HttpStatus.OK).body("Valor = R$ " + cost.doubleValue());
        } catch (Exception e) {
            StandardError err = StandardError.builder()
                    .timestamp(Instant.now())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .error("Ocorreu algum erro ao atualizar o novo register")
                    .message(e.getMessage())
                    .path("/register")
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }

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