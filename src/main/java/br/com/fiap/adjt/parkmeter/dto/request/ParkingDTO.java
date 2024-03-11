package br.com.fiap.adjt.parkmeter.dto.request;

import java.time.LocalDateTime;

import br.com.fiap.adjt.parkmeter.model.Parking;
import br.com.fiap.adjt.parkmeter.model.ParkingMeter;
import br.com.fiap.adjt.parkmeter.model.Veichle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingDTO {

	private ParkingMeter parkingMeter;

	private Veichle veichle;

	public static Parking to(ParkingDTO dto) {
		Parking parking = Parking.builder()
					.parkingMeter(dto.getParkingMeter())
					.veichle(dto.getVeichle())
					.parking(LocalDateTime.now())
					.closed(false)
					.build();
		
		return parking;
	}

}