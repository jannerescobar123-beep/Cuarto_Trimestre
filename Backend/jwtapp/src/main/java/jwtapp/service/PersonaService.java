package jwtapp.service;






import jwtapp.dto.PersonaDTO;
import  jwtapp.model.Persona;
import jwtapp.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class PersonaService {

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    private PersonaRepository personaRepository;
    public List<PersonaDTO> listar() {
        List<Persona> personas = personaRepository.findAll();
        List<PersonaDTO> personasDTO = new ArrayList<>();
        for (Persona persona : personas) {
            personasDTO.add(convertirADTO(persona));
        }
        return personasDTO;
    }
    /*
    public List<PersonaDTO> listar() {
    return personaRepository.findAll()
    .stream()
    .map(this::convertirADTO)
    .toList();
   }
    */
    public PersonaDTO guardar(PersonaDTO dto) {
        Persona persona = convertirAEntidad(dto);
        persona.setPassword(passwordEncoder.encode(dto.getPassword())); // ← falta esto
        Persona guardada = personaRepository.save(persona);
        return convertirADTO(guardada);
    }
    public Optional<PersonaDTO> obtener(Long id) {
        Optional<Persona> personaEncontrada = personaRepository.findById(id);
        return personaEncontrada.map(this::convertirADTO);
    }
    public PersonaDTO actualizar(Long id, PersonaDTO dto) {
        Persona persona = personaRepository.findById(id).orElseThrow();
        persona.setNombre(dto.getNombre());
        persona.setDireccion(dto.getDireccion());
        persona.setTelefono(dto.getTelefono());
        persona.setCorreo(dto.getCorreo());
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            persona.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        persona.setPassword(dto.getPassword());
        persona.setRol(dto.getRol());
        Persona actualizada = personaRepository.save(persona);
        return convertirADTO(actualizada);
    }
    public void eliminar(Long id) {
        personaRepository.deleteById(id);
    }
    // Métodos auxiliares
    public PersonaDTO convertirADTO(Persona persona) {
        PersonaDTO dto = new PersonaDTO();
        dto.setId(persona.getId());
        dto.setNombre(persona.getNombre());
        dto.setDireccion(persona.getDireccion());
        dto.setTelefono(persona.getTelefono());
        dto.setCorreo(persona.getCorreo());
        dto.setPassword(persona.getPassword());
        dto.setRol(persona.getRol());
        return dto;
    }
    public Persona convertirAEntidad(PersonaDTO dto) {
        Persona persona = new Persona();
        persona.setId(dto.getId());
        persona.setNombre(dto.getNombre());
        persona.setDireccion(dto.getDireccion());
        persona.setTelefono(dto.getTelefono());
        persona.setCorreo(dto.getCorreo());
        persona.setPassword(dto.getPassword());
        persona.setRol(dto.getRol());
        return persona;
    }
    public Optional<Persona> autenticar(String correo, String password) {
        Optional<Persona> personaOpt = personaRepository.findByCorreo(correo);
        if (personaOpt.isPresent()) {
            Persona persona = personaOpt.get();
            if (passwordEncoder.matches(password, persona.getPassword())) {
                return Optional.of(persona);
            }
        }
        return Optional.empty();
    }
}
