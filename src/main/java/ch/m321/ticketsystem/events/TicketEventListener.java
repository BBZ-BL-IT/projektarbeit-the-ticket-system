package ch.m321.ticketsystem.events;

import ch.m321.ticketsystem.model.Ticket;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TicketEventListener {

    @EventListener
    public void handleTicketCreatedEvent(TicketCreatedEvent event) {
        Ticket ticket = event.getTicket();
        System.out.println("New Ticket Created: " + ticket.getTitel());  // Titel anpassen
    }

    @EventListener
    public void handleTicketUpdatedEvent(TicketUpdatedEvent event) {
        Ticket ticket = event.getTicket();
        System.out.println("Ticket Updated: " + ticket.getTitel());  // Titel anpassen
    }

    @EventListener
    public void handleTicketSolvedEvent(TicketSolvedEvent event) {
        Ticket ticket = event.getTicket();
        System.out.println("Ticket Solved: " + ticket.getTitel());  // Titel anpassen
    }
}
