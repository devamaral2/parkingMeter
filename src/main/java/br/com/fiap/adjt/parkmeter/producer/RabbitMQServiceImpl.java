package br.com.fiap.adjt.parkmeter.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.adjt.parkmeter.dto.request.ParkingDTO;

@Service
public class RabbitMQServiceImpl implements AmqpService {

    @Autowired
    private AmqpProducer<ParkingDTO> amqp;

    @Override
    public void sendToConsumer(ParkingDTO message) {
        amqp.producer(message);
    }
}
