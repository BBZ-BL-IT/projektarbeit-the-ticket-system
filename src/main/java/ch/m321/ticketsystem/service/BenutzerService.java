package ch.m321.ticketsystem.service;

import ch.m321.ticketsystem.model.Benutzer;
import ch.m321.ticketsystem.model.repo.BenutzerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BenutzerService {

    @Autowired
    private BenutzerRepo benutzerRepository;

    public List<Benutzer> getAllBenutzer() {
        return benutzerRepository.findAll();
    }

    public Optional<Benutzer> getBenutzerById(Long id) {
        return benutzerRepository.findById(id);
    }

    public Benutzer createBenutzer(Benutzer benutzer) {
        return benutzerRepository.save(benutzer);
    }

    public void deleteBenutzer(Long id) {
        benutzerRepository.deleteById(id);
    }
}
