package com.trabajoSpringboot.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trabajoSpringboot.demo.dao.PersonaDao;

@Service
public class PersonaService {

 private final PersonaDao personaDao;

    @Autowired
    public PersonaService(PersonaDao personaDao) {
     this.personaDao = personaDao;
 }
  //La anotación @Autowired en Spring se utiliza para realizar la inyección de dependencias automáticamente
}
