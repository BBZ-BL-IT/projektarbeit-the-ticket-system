package ch.m321.ticketsystem.service;
import ch.m321.ticketsystem.config.RabbitMQConfig;
import ch.m321.ticketsystem.dto.TicketDTO;
import ch.m321.ticketsystem.factory.TicketFactory;
import ch.m321.ticketsystem.model.Benutzer;
import ch.m321.ticketsystem.model.Ticket;
import ch.m321.ticketsystem.model.repo.BenutzerRepo;
import ch.m321.ticketsystem.model.repo.TicketRepo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {


    private TicketFactory ticketFactory;

    @Autowired
    private TicketRepo ticketRepository;

    @Autowired
    private BenutzerRepo benutzerRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate; // Inject RabbitTemplate for sending messages


    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Optional<Benutzer> benutzerOptional = benutzerRepository.findById(ticketDTO.getBenutzerId());
        if (benutzerOptional.isEmpty()) {
            throw new RuntimeException("Benutzer nicht gefunden");
        }

        Benutzer benutzer = benutzerOptional.get();

        // Call the static method directly
        Ticket ticket = TicketFactory.createTicket(ticketDTO, benutzer);
        Ticket savedTicket = ticketRepository.save(ticket);

        return convertToDTO(savedTicket);
    }

    public TicketDTO updateTicket(Long id, TicketDTO updatedTicketDTO) {
        return ticketRepository.findById(id).map(ticket -> {
            Benutzer benutzer = benutzerRepository.findById(updatedTicketDTO.getBenutzerId())
                    .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden"));

            Ticket updatedTicket = ticketFactory.createTicket(updatedTicketDTO, benutzer);
            updatedTicket.setId(ticket.getId()); // Set the ID of the existing ticket

            Ticket savedTicket = ticketRepository.save(updatedTicket);

            // Publish the ticket update event to RabbitMQ
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, savedTicket);

            return convertToDTO(savedTicket);
        }).orElseThrow(() -> new RuntimeException("Ticket nicht gefunden"));
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
        // Optionally, publish a delete event to RabbitMQ if needed
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
