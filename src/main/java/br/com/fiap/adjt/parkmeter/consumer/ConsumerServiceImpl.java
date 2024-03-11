package br.com.fiap.adjt.parkmeter.consumer;

import org.springframework.stereotype.Service;

import br.com.fiap.adjt.parkmeter.dto.request.ParkingDTO;
import br.com.fiap.adjt.parkmeter.service.ParkingService;

@Service
public class ConsumerServiceImpl implements ConsumerService {
	
	private ParkingService parkingService;

	public ConsumerServiceImpl(ParkingService parkingService) {
		this.parkingService = parkingService;
	}

	@Override
	public void action(ParkingDTO message) throws Exception {
		parkingService.save(message);
	}
}
