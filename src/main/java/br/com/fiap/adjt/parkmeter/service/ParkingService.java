package br.com.fiap.adjt.parkmeter.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiap.adjt.parkmeter.dto.request.ParkingDTO;
import br.com.fiap.adjt.parkmeter.model.Parking;
import br.com.fiap.adjt.parkmeter.model.Price;
import br.com.fiap.adjt.parkmeter.repository.ParkingRepository;

@Service
public class ParkingService {

	private ParkingRepository parkingRepository;

	public ParkingService(final ParkingRepository parkingRepository) {
		this.parkingRepository = parkingRepository;
	}

	public Parking save(ParkingDTO parkingDTO) {
		Parking parking = ParkingDTO.to(parkingDTO);
		return parkingRepository.save(parking);
	}

	public Parking unparking(Optional<Parking> oParking) {
		if (oParking.isPresent()) {
			Parking parking = oParking.get();

			parking.setUnParking(LocalDateTime.now());
			parking.setClosed(true);
			
			BigDecimal parkingPrice = Price.calculatePrice(parking.getParking(), parking.getUnParking());
			
			parking.setPrice(parkingPrice);

			return parkingRepository.save(parking);
		}

		return null;

	}

}
