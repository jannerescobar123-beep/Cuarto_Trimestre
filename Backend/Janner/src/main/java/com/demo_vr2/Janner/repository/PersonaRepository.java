package com.demo_vr2.Janner.repository;

import com.demo_vr2.Janner.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}