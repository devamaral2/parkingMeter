package com.example.parking_meter.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class Register {
    private String veichle_plate;

    private String price_id;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
}
