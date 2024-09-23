package ch.m321.ticketsystem.model.repo;

import ch.m321.ticketsystem.model.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenutzerRepo extends JpaRepository<Benutzer, Long> {
}
