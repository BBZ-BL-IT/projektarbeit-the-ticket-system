package ch.m321.ticketsystem.model.repo;

import ch.m321.ticketsystem.model.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenutzerRepo extends JpaRepository<Benutzer, Long> {
}
