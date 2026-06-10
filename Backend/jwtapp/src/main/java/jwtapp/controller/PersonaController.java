package jwtapp.controller;

import jwtapp.dto.PersonaDTO;
import jwtapp.model.Persona;
import jwtapp.service.PersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    @Autowired
    private PersonaService personaService;
    // Listar todas las personas
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<PersonaDTO> listar() {
        return personaService.listar();
    }

    @GetMapping("/mensaje")
    @PreAuthorize("hasRole('ADMIN','ASESOR')")
    public String mensajePrivado(){
        return "este es un mensaje privadooo desde admin Y ASESORRRR siuuuuuuuuuuuuu ";
    }





    // Crear una nueva persona
    @PostMapping
    public ResponseEntity<PersonaDTO> crear(@RequestBody @Valid PersonaDTO dto) {
        PersonaDTO nueva = personaService.guardar(dto);
        return ResponseEntity.ok(nueva);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> obtener(@PathVariable Long id) {
        // Buscar la persona por su ID
        Optional<PersonaDTO> personaEncontrada = personaService.obtener(id);
        // Verificar si se encontró una persona
        if (personaEncontrada.isPresent()) {
            PersonaDTO dto = personaEncontrada.get();
            // Devolver respuesta 200 OK con la información
            return ResponseEntity.ok(dto);
        }

        // Si no se encuentra la persona, devolver 404 Not Found
        return ResponseEntity.notFound().build();
    }
    /*
     @GetMapping("/{id}")
     public ResponseEntity<PersonaDTO> obtener(@PathVariable Long id) {
     return personaService.obtener(id)
     .map(ResponseEntity::ok)
     .orElse(ResponseEntity.notFound().build());
     }
    */
    // Actualizar una persona por ID
    @PutMapping("/{id}")
    public ResponseEntity<PersonaDTO> actualizar(@PathVariable Long id, @RequestBody @Valid PersonaDTO dto) {
        PersonaDTO actualizada = personaService.actualizar(id, dto);
        return ResponseEntity.ok(actualizada);
    }
    // Eliminar una persona por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        personaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}