package br.com.fiap.adjt.parkmeter.consumer;

import br.com.fiap.adjt.parkmeter.dto.request.ParkingDTO;

public interface ConsumerService {
	void action(ParkingDTO message) throws Exception;
}
