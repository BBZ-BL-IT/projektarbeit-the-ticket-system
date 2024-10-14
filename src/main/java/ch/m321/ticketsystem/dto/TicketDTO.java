package ch.m321.ticketsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDTO {
    private Long id;
    private String titel;
    private String beschreibung;
    private boolean abgeschlossen;
    private Long benutzerId;
}