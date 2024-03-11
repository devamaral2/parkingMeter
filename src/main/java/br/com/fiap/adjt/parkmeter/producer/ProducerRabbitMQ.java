package br.com.fiap.adjt.parkmeter.producer;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.fiap.adjt.parkmeter.dto.request.ParkingDTO;

@Component
public class ProducerRabbitMQ implements AmqpProducer<ParkingDTO> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange.app}")
    private String exchange;

    @Value("${spring.rabbitmq.queue.app}")
    private String queue;

    @Override
    public void producer(ParkingDTO message) {
        try {
            rabbitTemplate.convertAndSend(exchange, queue, message);
        } catch (Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
