package ch.m321.ticketsystem.events;

import ch.m321.ticketsystem.model.Ticket;

import ch.m321.ticketsystem.service.TicketService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TicketEventPublisher {

    private final ApplicationEventPublisher eventPublisher;
    private final TicketService ticketService;

    public TicketEventPublisher(ApplicationEventPublisher eventPublisher, TicketService ticketService) {
        this.eventPublisher = eventPublisher;
        this.ticketService = ticketService;
    }

    public void publishTicketCreatedEvent(Long ticketId) {
        Ticket ticket = ticketService.getTicketById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        TicketCreatedEvent event = new TicketCreatedEvent(this, ticket);
        eventPublisher.publishEvent(event);
    }

    public void publishTicketUpdatedEvent(Long ticketId) {
        Ticket ticket = ticketService.getTicketById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        TicketUpdatedEvent event = new TicketUpdatedEvent(this, ticket);
        eventPublisher.publishEvent(event);
    }
}
