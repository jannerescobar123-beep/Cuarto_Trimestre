package com.demo_vr2.Janner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.demo_vr2.Janner.dto.PersonaDto;
import com.demo_vr2.Janner.service.PersonaService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/servicio")
public class PersonaController {

    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    //http://localhost:8080/servicio/hola
    @GetMapping("hola")
    public String saludo() {
        return "Este es un saludo desde el proyecto Prueba CRUD";
    }

    //http://localhost:8080/servicio/personas?id=111
    @GetMapping("personas")
    public ResponseEntity<?> getPersona(@RequestParam(value = "id", required = false) String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El parámetro 'id' es obligatorio.");
        }
        PersonaDto persona = personaService.obtenerPersonaPorDocumento(documento);
        if (persona == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada para el documento: " + documento);
        }
        return ResponseEntity.ok(persona);
    }

    //http://localhost:8080/servicio/profesion?id=111&profesion=Ingeniero
    @GetMapping("profesion")
    public ResponseEntity<?> getPersonaIdProfesion(
            @RequestParam(value = "id") String documento,
            @RequestParam String profesion) {

        PersonaDto persona = personaService.consultarPersonaIdProfesion(documento, profesion);
        if (persona == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Persona no encontrada para el documento: " + documento + " y profesión: " + profesion);
        }
        return ResponseEntity.ok(persona);
    }
    //http://localhost:8080/servicio/personas/111
    @GetMapping("personas/{id}")
    public ResponseEntity<?> getPersonaPath(
            @org.springframework.web.bind.annotation.PathVariable("id")
            String documento) {

        PersonaDto persona =
                personaService
                        .obtenerPersonaPorDocumento(
                                documento);

        if (persona == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Persona no encontrada");
        }

        return ResponseEntity.ok(persona);
    }
    //http://localhost:8080/servicio/personas-list
    @GetMapping("personas-list")
    public ResponseEntity<?> getListaPersonas() {

        return ResponseEntity.ok(
                personaService.consultarListaPersonas());
    }
    //http://localhost:8080/servicio/guardar
    @PostMapping("guardar")
    public ResponseEntity<?> guardarPersona(
            @RequestBody PersonaDto persona) {

        if (persona == null) {

            return ResponseEntity
                    .badRequest()
                    .body("La persona es obligatoria");
        }

        PersonaDto personaGuardada =
                personaService
                        .guardarPersona(persona);

        return ResponseEntity.ok(
                personaGuardada);
    }
    //http://localhost:8080/servicio/actualizar
    @PutMapping("actualizar")
    public ResponseEntity<?> actualizarPersona(
            @org.springframework.web.bind.annotation.RequestBody
            PersonaDto persona) {

        PersonaDto personaActualizada =
                personaService
                        .actualizarPersona(persona);

        if (personaActualizada == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Persona no encontrada");
        }

        return ResponseEntity.ok(
                personaActualizada);
    }
    //http://localhost:8080/servicio/eliminar/555
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarPersona(
            @PathVariable("id")
            String documento) {

        boolean eliminado =
                personaService
                        .eliminarPersona(documento);

        if (!eliminado) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Persona no encontrada");
        }

        return ResponseEntity.ok(
                "Persona eliminada correctamente");
    }
}