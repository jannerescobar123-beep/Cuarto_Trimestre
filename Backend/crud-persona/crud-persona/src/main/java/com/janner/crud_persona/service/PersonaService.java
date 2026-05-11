package com.janner.crud_persona.service;
import com.janner.crud_persona.dto.PersonaDto;
import com.janner.crud_persona.model.Persona;
import com.janner.crud_persona.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import  java.util.stream.Collector;

@Service
public class PersonaService {
    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }
    public List<PersonaDto> getAllPersonas() {
        List<Persona> personas =  personaRepository.findAll();
        List<PersonaDto> personaDtos = new ArrayList<>();

        for (Persona persona : personas) {
            PersonaDto dto = convertToDto(persona); // Método que convierte de Persona a PersonaDto
            personaDtos.add(dto);
        }
        return personaDtos;
    }
    public PersonaDto getPersonaById(Long id) {
        Persona persona = personaRepository.findById(id).orElse(null);
        if (persona == null) {
            return null;
        }
        return convertToDto(persona);
    }

    public PersonaDto savePersona(PersonaDto personaDto) {
        Persona persona = convertToEntity(personaDto);
        Persona savedPersona = personaRepository.save(persona);
        return convertToDto(savedPersona);
    }
    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }
    // Métodos de conversión
    private PersonaDto convertToDto(Persona persona) {
        PersonaDto dto = new PersonaDto();
        dto.setNombre(persona.getNombre());
        dto.setDireccion(persona.getDireccion());
        dto.setTelefono(persona.getTelefono());
        return dto;
    }
    private Persona convertToEntity(PersonaDto dto) {
        Persona persona = new Persona();
        persona.setNombre(dto.getNombre());
        persona.setDireccion(dto.getDireccion());
        persona.setTelefono(dto.getTelefono());
        return persona;
    }


}

