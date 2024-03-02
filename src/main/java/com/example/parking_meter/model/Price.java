package com.example.parking_meter.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class Price {
    private String price_per_hour;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
}
