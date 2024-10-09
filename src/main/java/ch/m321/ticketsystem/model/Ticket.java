package ch.m321.ticketsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titel;
    private String beschreibung;
    private boolean abgeschlossen;

    @ManyToOne
    @JoinColumn(name = "benutzer_id", nullable = false)
    @JsonIgnore
    private Benutzer benutzer;
}
