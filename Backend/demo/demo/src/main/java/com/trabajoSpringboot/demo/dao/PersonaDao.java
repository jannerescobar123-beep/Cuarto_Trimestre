package com.trabajoSpringboot.demo.dao;
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
}
