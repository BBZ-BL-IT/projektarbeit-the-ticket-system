package ch.m321.ticketsystem.factory;

import ch.m321.ticketsystem.model.Benutzer;
import ch.m321.ticketsystem.model.Ticket;
import ch.m321.ticketsystem.dto.TicketDTO;
import org.springframework.stereotype.Component;

@Component
public class TicketFactory {

    public static Ticket createTicket(TicketDTO ticketDTO, Benutzer benutzer) {
        Ticket ticket = new Ticket();
        ticket.setTitel(ticketDTO.getTitel());
        ticket.setBeschreibung(ticketDTO.getBeschreibung());
        ticket.setAbgeschlossen(ticketDTO.isAbgeschlossen());
        ticket.setBenutzer(benutzer);
        return ticket;
    }
}