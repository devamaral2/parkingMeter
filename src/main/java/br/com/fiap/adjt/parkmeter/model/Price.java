package br.com.fiap.adjt.parkmeter.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class Price {

	private final static BigDecimal valueHour = BigDecimal.ONE;

	public static BigDecimal calculatePrice(LocalDateTime parking, LocalDateTime unParking ) {
		long hours = Duration.between(parking, unParking).toHours();
		
		if (Duration.between(parking, unParking).toMinutes() > 1) {
			hours++;
		}
		
		BigDecimal amount = valueHour.multiply(new BigDecimal(hours));
		
		return amount;
	}
	
}
