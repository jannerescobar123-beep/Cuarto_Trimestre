package com.janner.crud_persona.controller;
import com.janner.crud_persona.model.Persona;
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
    public List<Persona> getAllPersonas() {
        return personaService.getAllPersonas();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        Persona persona = personaService.getPersonaById(id);
        if (persona == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(persona);
    }
    @PostMapping
    public Persona createPersona(@RequestBody Persona persona) {
        return personaService.savePersona(persona);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona
            personaDetails) {
        Persona persona = personaService.getPersonaById(id);
        if (persona == null) {
            return ResponseEntity.notFound().build();
        }
        persona.setNombre(personaDetails.getNombre());
        persona.setDireccion(personaDetails.getDireccion());
        persona.setTelefono(personaDetails.getTelefono());
        Persona updatedPersona = personaService.savePersona(persona);
        return ResponseEntity.ok(updatedPersona);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        Persona persona = personaService.getPersonaById(id);
        if (persona == null) {
            return ResponseEntity.notFound().build();
        }
        personaService.deletePersona(id);
        return ResponseEntity.noContent().build();
    }

}
