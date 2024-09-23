package ch.m321.ticketsystem.service;

import ch.m321.ticketsystem.model.Ticket;
import ch.m321.ticketsystem.model.Benutzer;
import ch.m321.ticketsystem.model.repo.TicketRepo;
import ch.m321.ticketsystem.model.repo.BenutzerRepo;
import ch.m321.ticketsystem.events.TicketEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepo ticketRepository;

    @Autowired
    private BenutzerRepo benutzerRepository;

    @Autowired
    private TicketEventPublisher ticketEventPublisher;

    public Ticket createTicket(Ticket ticket, Long benutzerId) {
        Benutzer benutzer = benutzerRepository.findById(benutzerId)
                .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden"));

        ticket.setBenutzer(benutzer);
        ticket.setAbgeschlossen(true);

        Ticket savedTicket = ticketRepository.save(ticket);
        ticketEventPublisher.publishTicketCreatedEvent(savedTicket);

        return savedTicket;
    }

    public Ticket updateTicket(Long id, Ticket updatedTicket) {
        return ticketRepository.findById(id).map(ticket -> {
            ticket.setTitel(updatedTicket.getTitel());
            ticket.setBeschreibung(updatedTicket.getBeschreibung());
            ticket.setAbgeschlossen(updatedTicket.isAbgeschlossen());
            Ticket savedTicket = ticketRepository.save(ticket);

            ticketEventPublisher.publishTicketUpdatedEvent(savedTicket);

            return savedTicket;
        }).orElseThrow(() -> new RuntimeException("Ticket nicht gefunden"));
    }

    public Ticket solveTicket(Long id) {
        return ticketRepository.findById(id).map(ticket -> {
            ticket.setAbgeschlossen(false);
            Ticket savedTicket = ticketRepository.save(ticket);

            ticketEventPublisher.publishTicketSolvedEvent(savedTicket);

            return savedTicket;
        }).orElseThrow(() -> new RuntimeException("Ticket nicht gefunden"));
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }
}
