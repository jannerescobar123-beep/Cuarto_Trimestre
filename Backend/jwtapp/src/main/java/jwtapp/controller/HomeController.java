package jwtapp.controller;

import jwtapp.dto.LoginRequestDTO;
import jwtapp.dto.LoginResponseDTO;
import jwtapp.model.Persona;
import jwtapp.security.jwt.JwtUtil;
import jwtapp.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/public")
    public String mensajePublico() {
        return "Bienvenido a la API pública de la veterinaria";
    }

    @GetMapping("/info")
    public String info() {
        return "Esta es una API pública para fines de prueba";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        Optional<Persona> personaOpt = personaService.autenticar(
                loginRequest.getCorreo(),
                loginRequest.getPassword()
        );

        if (personaOpt.isPresent()) {
            Persona persona = personaOpt.get();
            String token = jwtUtil.generarToken(persona.getCorreo());
            LoginResponseDTO response = new LoginResponseDTO(token, persona.getCorreo(), persona.getRol());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Correo o contraseña incorrectos");
        }
    }
}