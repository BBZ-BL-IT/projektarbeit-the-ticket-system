package ch.m321.ticketsystem.events;

import ch.m321.ticketsystem.model.Ticket;
import ch.m321.ticketsystem.service.TicketService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TicketEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public TicketEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishTicketCreatedEvent(Ticket ticket) {
        TicketCreatedEvent event = new TicketCreatedEvent(this, ticket);
        eventPublisher.publishEvent(event);
    }

    public void publishTicketUpdatedEvent(Ticket ticket) {
        TicketUpdatedEvent event = new TicketUpdatedEvent(this, ticket);
        eventPublisher.publishEvent(event);
    }

    public void publishTicketSolvedEvent(Ticket ticket) {
        TicketSolvedEvent event = new TicketSolvedEvent(this, ticket);
        eventPublisher.publishEvent(event);
    }
}

