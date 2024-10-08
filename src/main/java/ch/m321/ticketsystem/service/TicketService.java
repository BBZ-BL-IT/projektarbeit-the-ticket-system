package ch.m321.ticketsystem.service;

import ch.m321.ticketsystem.factory.TicketFactory;
import ch.m321.ticketsystem.model.Benutzer;
import ch.m321.ticketsystem.model.Ticket;
import ch.m321.ticketsystem.model.repo.BenutzerRepo;
import ch.m321.ticketsystem.model.repo.TicketRepo;
import ch.m321.ticketsystem.dto.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepo ticketRepository;

    @Autowired
    private BenutzerRepo benutzerRepository;

    private TicketFactory ticketFactory;

    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Optional<Benutzer> benutzerOptional = benutzerRepository.findById(ticketDTO.getBenutzerId());
        if (benutzerOptional.isEmpty()) {
            throw new RuntimeException("Benutzer nicht gefunden");
        }

        Benutzer benutzer = benutzerOptional.get();

        Ticket ticket = TicketFactory.createTicket(ticketDTO, benutzer);
        Ticket savedTicket = ticketRepository.save(ticket);

        return convertToDTO(savedTicket);
    }

    public TicketDTO updateTicket(Long id, TicketDTO updatedTicketDTO) {
        return ticketRepository.findById(id).map(ticket -> {
            Benutzer benutzer = benutzerRepository.findById(updatedTicketDTO.getBenutzerId())
                    .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden"));

            Ticket updatedTicket = ticketFactory.createTicket(updatedTicketDTO, benutzer);
            updatedTicket.setId(ticket.getId()); // Setze die ID des alten Tickets

            Ticket savedTicket = ticketRepository.save(updatedTicket);

            return convertToDTO(savedTicket);
        }).orElseThrow(() -> new RuntimeException("Ticket nicht gefunden"));
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private TicketDTO convertToDTO(Ticket ticket) {
        TicketDTO dto = new TicketDTO();
        dto.setId(ticket.getId());
        dto.setTitel(ticket.getTitel());
        dto.setBeschreibung(ticket.getBeschreibung());
        dto.setAbgeschlossen(ticket.isAbgeschlossen());
        dto.setBenutzerId(ticket.getBenutzer().getId());
        return dto;
    }
}