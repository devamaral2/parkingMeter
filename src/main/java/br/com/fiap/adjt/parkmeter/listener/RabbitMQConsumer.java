package br.com.fiap.adjt.parkmeter.listener;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.adjt.parkmeter.consumer.ConsumerService;
import br.com.fiap.adjt.parkmeter.dto.request.ParkingDTO;

@Component
public class RabbitMQConsumer implements AmqpConsumer<ParkingDTO> {

	@Autowired
	private ConsumerService consumerService;

	@Override
	@RabbitListener(queues = "${spring.rabbitmq.queue.app}")
	public void consumer(ParkingDTO message) {
		try {
			consumerService.action(message);
		} catch (Exception ex) {
			throw new AmqpRejectAndDontRequeueException(ex);
		}
	}

}
