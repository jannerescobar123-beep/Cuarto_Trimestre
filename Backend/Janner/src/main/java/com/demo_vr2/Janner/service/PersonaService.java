package com.demo_vr2.Janner.service;

import com.demo_vr2.Janner.dao.PersonaDao;
import com.demo_vr2.Janner.model.Persona;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {
    private final PersonaDao personaDao;

    public PersonaService(PersonaDao personaDao) {
        this.personaDao = personaDao;
    }

    public List<Persona> getAllPersonas() {
        return personaDao.findAll();
    }

    public Persona getPersonaById(Long id) {
        return personaDao.findById(id);
    }

    public Persona savePersona(Persona persona) {
        return personaDao.save(persona);
    }

    public void deletePersona(Long id) {
        personaDao.deleteById(id);
    }
}