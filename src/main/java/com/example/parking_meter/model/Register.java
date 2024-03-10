package com.example.parking_meter.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document
@Data
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Register {
    @Id
    private String id;
    private String veichle_plate;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    @DBRef
    private Price price;
    @Version
    private Long version;
}
