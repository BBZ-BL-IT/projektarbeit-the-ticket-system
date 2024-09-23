package ch.m321.ticketsystem.events;

import ch.m321.ticketsystem.model.Ticket;
import org.springframework.context.ApplicationEvent;

public class TicketSolvedEvent extends ApplicationEvent {

    private final Ticket ticket;

    public TicketSolvedEvent(Object source, Ticket ticket) {
        super(source);
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
