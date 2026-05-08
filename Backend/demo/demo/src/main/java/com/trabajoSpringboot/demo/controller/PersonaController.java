package com.trabajoSpringboot.demo.controller;

import com.trabajoSpringboot.demo.dto.PersonaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trabajoSpringboot.demo.service.PersonaService;

@RestController
@RequestMapping ("/servicio")
public class PersonaController {
    private  final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService){
        this.personaService = personaService;
    }
    //http://localhost:8080/servicio/personas?id=111
    @GetMapping("personas")
    public ResponseEntity<?> getPersona(
            @RequestParam(value = "id", required = false )String documento) {

                if (documento == null || documento.trim().isEmpty()){
                    return ResponseEntity.badRequest().body("El  ID es obligatorio, tontito");
                }
                PersonaDto persona = personaService.obtenerPersonaPorDocumento(documento);
                if (persona == null){
                    return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("la personita no ha sido encontrata, que mal... :(" + documento);
                }
                return  ResponseEntity.ok(persona);

    }


}