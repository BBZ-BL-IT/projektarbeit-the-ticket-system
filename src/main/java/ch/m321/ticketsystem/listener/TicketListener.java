package ch.m321.ticketsystem.listener;

import ch.m321.ticketsystem.config.RabbitMQConfig;
import ch.m321.ticketsystem.model.Ticket;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TicketListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void handleTicketMessage(Ticket ticket) {
        // Process the ticket message
        System.out.println("Received ticket: " + ticket.getTitel());
        // Add logic for processing the ticket, like updating a dashboard
    }
}
