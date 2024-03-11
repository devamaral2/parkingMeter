package br.com.fiap.adjt.parkmeter.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document("parking")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Parking {

    @Id
    private String id;
    
    private ParkingMeter parkingMeter;
    
    private Veichle veichle;

    private LocalDateTime parking;

    private LocalDateTime unParking;
    
    private Boolean closed;
    
    private BigDecimal price;
	
}
