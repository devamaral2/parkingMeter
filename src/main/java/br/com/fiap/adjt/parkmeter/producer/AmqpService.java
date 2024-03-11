package br.com.fiap.adjt.parkmeter.producer;

import br.com.fiap.adjt.parkmeter.dto.request.ParkingDTO;

public interface AmqpService {
    void sendToConsumer(ParkingDTO message);
}
