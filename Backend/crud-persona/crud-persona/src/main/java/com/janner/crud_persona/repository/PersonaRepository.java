package com.janner.crud_persona.repository;

import com.janner.crud_persona.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;




public interface PersonaRepository extends  JpaRepository<Persona,Long> {

}
