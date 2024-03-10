package com.example.parking_meter.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
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
public class Price {
    @Id
    private String id;
    private Long price_per_hour;
    private LocalDateTime start_date;
    private LocalDateTime end_date;

    @Version
    private Long version;
}
