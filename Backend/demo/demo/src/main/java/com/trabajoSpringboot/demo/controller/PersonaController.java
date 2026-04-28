package com.trabajoSpringboot.demo.controller;

import com.trabajoSpringboot.demo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/servicio")
public class PersonaController {
    private  final PersonaService personaService;
    @Autowired
    public PersonaController(PersonaService personaService){
        this.personaService = personaService;
    }
    @GetMapping ("hola")
    public String saludo(){
        return "hola mamahuevos, este es un saludo desde el CRUD";
    }
}
