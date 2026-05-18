package com.demo_vr2.Janner.dao;

import com.demo_vr2.Janner.model.Persona;
import com.demo_vr2.Janner.repository.PersonaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonaDao {
    private final PersonaRepository personaRepository;

    public PersonaDao(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    public Persona findById(Long id) {
        return personaRepository.findById(id).orElse(null);
    }

    public Persona save(Persona persona) {
        return personaRepository.save(persona);
    }

    public void deleteById(Long id) {
        personaRepository.deleteById(id);
    }
}