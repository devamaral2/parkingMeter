package com.example.parking_meter.service.impl;

import com.example.parking_meter.model.Price;
import com.example.parking_meter.repository.PriceRepository;
import com.example.parking_meter.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    private final MongoTemplate mongoTemplate;
    @Autowired
    private PriceRepository priceRepository;

    public PriceServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    @Transactional
    public ResponseEntity create(Long price) {
        Query query = new Query(Criteria.where("end_date").exists(false));
        List<Price> prices = this.mongoTemplate.find(query, Price.class);
        if (!prices.isEmpty()) {
            for (Price priceInUse : prices) {
                priceInUse.setEnd_date(LocalDateTime.now());
                try {
                    this.priceRepository.save(priceInUse);
                } catch (OptimisticLockingFailureException e) {
                    Optional<Price> priceLastUpdate = this.priceRepository.findById(priceInUse.getId());
                    if (priceLastUpdate.isPresent()) {
                        priceLastUpdate.get().setVersion(priceLastUpdate.get().getVersion() + 1);
                        this.priceRepository.save(priceLastUpdate.get());
                    } else {
                        throw new RuntimeException("O price:" + priceInUse.getId() + "não foi encontrado");
                    }
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Erro ao fazer o update do price " + priceInUse.getId() + ": " + e.getMessage());
                }
            }
        }

        Price newPrice = Price.builder().price_per_hour(price).start_date(LocalDateTime.now()).build();
        try {
            Price createdPrice = this.priceRepository.save(newPrice);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPrice);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar o novo price: " + e.getMessage());
        }

    }
}
