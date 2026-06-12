package jwtapp.security;

import jwtapp.model.Persona;
import jwtapp.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PersonaRepository personaRepository;
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        // Buscar la persona en la base de datos usando el correo electronico
        Optional<Persona> personaOptional = personaRepository.findByCorreo(correo);
        // Si no se encuentra, lanzar una excepción indicando que no existe
        if (personaOptional.isEmpty()) {
            throw new UsernameNotFoundException("No se encontró " +
                    "un usuario con el correo: " + correo);
        }
        // Obtener la persona encontrada
        Persona persona = personaOptional.get();
        // Retornar un objeto de tipo UserDetails con la información del usuario
        return new SecurityUser(persona);
    }
}
