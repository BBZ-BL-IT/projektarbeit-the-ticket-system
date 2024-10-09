package ch.m321.ticketsystem.service;

import ch.m321.ticketsystem.dto.TicketDTO;
import ch.m321.ticketsystem.factory.TicketFactory;
import ch.m321.ticketsystem.model.Benutzer;
import ch.m321.ticketsystem.model.Ticket;
import ch.m321.ticketsystem.model.repo.BenutzerRepo;
import ch.m321.ticketsystem.model.repo.TicketRepo;
import ch.m321.ticketsystem.publisher.TicketEventPublisherrr;
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

    @Autowired
    private TicketEventPublisherrr ticketEventPublisher;

    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Optional<Benutzer> benutzerOptional = benutzerRepository.findById(ticketDTO.getBenutzerId());
        if (benutzerOptional.isEmpty()) {
            throw new RuntimeException("Benutzer nicht gefunden");
        }

        Benutzer benutzer = benutzerOptional.get();
        Ticket ticket = TicketFactory.createTicket(ticketDTO, benutzer);
        Ticket savedTicket = ticketRepository.save(ticket);

        TicketDTO savedTicketDTO = convertToDTO(savedTicket);
        ticketEventPublisher.publishTicketCreatedEvent(savedTicketDTO);

        return savedTicketDTO;
    }

    public TicketDTO updateTicket(Long id, TicketDTO updatedTicketDTO) {
        return ticketRepository.findById(id).map(ticket -> {
            Benutzer benutzer = benutzerRepository.findById(updatedTicketDTO.getBenutzerId())
                    .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden"));

            Ticket updatedTicket = TicketFactory.createTicket(updatedTicketDTO, benutzer);
            updatedTicket.setId(ticket.getId());

            Ticket savedTicket = ticketRepository.save(updatedTicket);

            TicketDTO savedTicketDTO = convertToDTO(savedTicket);
            ticketEventPublisher.publishTicketUpdatedEvent(savedTicketDTO);

            return savedTicketDTO;
        }).orElseThrow(() -> new RuntimeException("Ticket nicht gefunden"));
    }

    public void deleteTicket(Long id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);

            ticketEventPublisher.publishTicketDeletedEvent(id);
        } else {
            throw new RuntimeException("Ticket nicht gefunden");
        }
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