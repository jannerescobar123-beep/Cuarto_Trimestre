package com.trabajoSpringboot.demo.dao;

import com.trabajoSpringboot.demo.dto.PersonaDto;
import org.springframework.stereotype.Repository;

import org.springframework.stereotype.Service;

import com.trabajoSpringboot.demo.util.PersonaUtilidades;

@Repository
public class PersonaDao {
    public PersonaDao(){
        PersonaUtilidades.iniciarLista();
    }
    //El constructor del DAO llama al método estático iniciarLista() de la clase PersonasUtilidades
    // Esto permite inicializar o poblar
    //una lista de personas que el DAO gestionará.

    public PersonaDto consultarPersonaIndividual(String documento){
        PersonaDto personaVo=null;

        for (PersonaDto p: PersonaUtilidades.listaPersonas){
            if (p.getDocumento().equals(documento)){
                personaVo= new PersonaDto();
                personaVo.setDocumento(p.getDocumento());
                personaVo.setNombre(p.getNombre());
                personaVo.setTelefono(p.getTelefono());
                personaVo.setEdad(p.getEdad());
                personaVo.setProfesion(p.getProfesion());
                personaVo.setPassword(p.getPassword());
                personaVo.setTipo(p.getTipo());
            }

        }
        return personaVo;
    }
}
