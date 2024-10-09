package ch.m321.ticketsystem.publisher;

import ch.m321.ticketsystem.config.RabbitMQConfig;
import ch.m321.ticketsystem.dto.TicketDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketEventPublisherrr {

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public TicketEventPublisherrr(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void publishTicketCreatedEvent(TicketDTO ticketDTO) {
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, ticketDTO);
    }

    public void publishTicketUpdatedEvent(TicketDTO ticketDTO) {
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, ticketDTO);
    }

    public void publishTicketDeletedEvent(Long ticketId) {
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, ticketId);
    }
}
