package com.example.parking_meter.service.impl;

import com.example.parking_meter.model.Price;
import com.example.parking_meter.repository.PriceRepository;
import com.example.parking_meter.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private final MongoTemplate mongoTemplate;
    @Autowired
    private PriceRepository priceRepository;

    public PriceServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Price create(Number price) {
        Query query = new Query(Criteria.where("end_date").exists(false));
        List<Price> prices = this.mongoTemplate.find(query, Price.class);
        if (!prices.isEmpty()) {
            for (Price priceInUse : prices) {
                priceInUse.setEnd_date(LocalDateTime.now());
                this.priceRepository.save(priceInUse);
            }
        }
        Price newPrice = Price.builder().price_per_hour(price).start_date(LocalDateTime.now()).build();
        return this.priceRepository.save(newPrice);
    }
}
