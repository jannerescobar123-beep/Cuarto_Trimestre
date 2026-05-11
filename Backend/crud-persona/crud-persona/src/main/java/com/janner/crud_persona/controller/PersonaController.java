package com.janner.crud_persona.controller;

import com.janner.crud_persona.dto.PersonaDto;
import com.janner.crud_persona.service.PersonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public List<PersonaDto> getAllPersonas() {
        return personaService.getAllPersonas();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonaDto> getPersonaById(@PathVariable Long id) {
        PersonaDto personaDto = personaService.getPersonaById(id);
        if (personaDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personaDto);
    }

    @PostMapping
    public PersonaDto createPersona(@RequestBody PersonaDto personaDto) {
        return personaService.savePersona(personaDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PersonaDto> updatePersona(@PathVariable Long id, @RequestBody PersonaDto personaDto) {
        PersonaDto existingPersona = personaService.getPersonaById(id);
        if (existingPersona == null) {
            return ResponseEntity.notFound().build();
        }
        existingPersona.setNombre(personaDto.getNombre());
        existingPersona.setDireccion(personaDto.getDireccion());
        existingPersona.setTelefono(personaDto.getTelefono());

        PersonaDto updatePersona = personaService.savePersona(existingPersona);
        return  ResponseEntity.ok(updatePersona);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        PersonaDto personaDto = personaService.getPersonaById(id);
        if (personaDto == null) {
            return ResponseEntity.notFound().build();
        }
        personaService.deletePersona(id);
        return ResponseEntity.noContent().build();
    }

}
