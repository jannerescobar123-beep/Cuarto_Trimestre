package com.janner.crud_persona.service;
import com.janner.crud_persona.model.Persona;
import com.janner.crud_persona.repository.PersonaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonaService {
    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }
    public Persona getPersonaById(Long id) {
        return personaRepository.findById(id).orElse(null);
    }
    public Persona savePersona(Persona persona) {
        return personaRepository.save(persona);
    }
    public void deletePersona(Long id) {
        personaRepository.deleteById(id);

    }}

