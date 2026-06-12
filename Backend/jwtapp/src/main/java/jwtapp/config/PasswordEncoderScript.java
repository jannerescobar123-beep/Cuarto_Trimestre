package jwtapp.config;

import jwtapp.model.Persona;
import jwtapp.repository.PersonaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import  org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
@Configuration
public class PasswordEncoderScript {
    private final PersonaRepository personaRepository;
    private final PasswordEncoder passwordEncoder;
    // Constructor para inyectar las dependencias
    public PasswordEncoderScript(PersonaRepository personaRepository, PasswordEncoder passwordEncoder) {
        this.personaRepository = personaRepository;
        this.passwordEncoder = passwordEncoder;
    }
    // Método que ejecuta la lógica para encriptar contraseñas
    public void encodePasswords() {
        List<Persona> personas = personaRepository.findAll();
        for (Persona persona : personas) {
            String password = persona.getPassword();
            // Si la contraseña no está encriptada (no empieza con $2a$)
            if (!password.startsWith("$2a$")) {
                String passwordEncriptada = passwordEncoder.encode(password);
                persona.setPassword(passwordEncriptada);
                personaRepository.save(persona);
            }
        }
        System.out.println("**********************************************");
        System.out.println(">>> Contraseñas encriptadas correctamente.");
        System.out.println("**********************************************");
    }
    // Ejecutar encodePasswords() al iniciar la app
    //@Bean
    public CommandLineRunner runOnStartup() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                encodePasswords();
            }
        };
    }
}
