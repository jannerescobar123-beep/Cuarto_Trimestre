package com.demo_vr2.Janner.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.demo_vr2.Janner.dto.PersonaDto;
import com.demo_vr2.Janner.util.PersonasUtilidades;

@Repository
public class PersonaDao {

    public PersonaDao() {
        PersonasUtilidades.iniciarLista();
    }

    public PersonaDto consultarPersonaIndividual(String documento) {
        PersonaDto personaVo = null;

        for (PersonaDto p : PersonasUtilidades.listaPersonas) {
            if (p.getDocumento().equals(documento)) {
                personaVo = new PersonaDto();
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

    public PersonaDto consultarPersonaIdProfesion(String documento, String profesion) {
        for (PersonaDto p : PersonasUtilidades.listaPersonas) {
            if (p.getDocumento().equals(documento) && p.getProfesion().equals(profesion)) {
                PersonaDto personaVo = new PersonaDto(p.getDocumento(), p.getNombre(), p.getTelefono(),
                        p.getEdad(), p.getProfesion(), p.getPassword(), p.getTipo());
                return personaVo;
            }
        }

        // Retornar null si no se encuentra coincidencia
        return null;
    }
    // AQUI VA 🔥
    public List<PersonaDto> consultarListaPersonas() {

        return PersonasUtilidades.listaPersonas;
    }
    public PersonaDto guardarPersona(
            PersonaDto persona) {

        PersonasUtilidades
                .listaPersonas
                .add(persona);

        return persona;
    }
    public PersonaDto actualizarPersona(
            PersonaDto persona) {

        for (PersonaDto p :
                PersonasUtilidades.listaPersonas) {

            if (p.getDocumento()
                    .equals(persona.getDocumento())) {

                p.setNombre(persona.getNombre());
                p.setEdad(persona.getEdad());
                p.setTipo(persona.getTipo());

                return p;
            }
        }

        return null;
    }
    public boolean eliminarPersona(
            String documento) {

        for (PersonaDto p :
                PersonasUtilidades.listaPersonas) {

            if (p.getDocumento()
                    .equals(documento)) {

                PersonasUtilidades
                        .listaPersonas
                        .remove(p);

                return true;
            }
        }

        return false;
    }
}