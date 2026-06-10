package jwtapp.dto;

import jakarta.validation.constraints.Email;
import  jakarta.validation.constraints.NotBlank;
import  jakarta.validation.constraints.Size;

public class PersonaDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no debe tener más de 100 caracteres")
    private String nombre;


    @Size(max = 255, message = "La dirección no debe tener más de 255 caracteres")
    private String direccion;

    @Size(max = 15, message = "El teléfono no debe tener más de 15 caracteres")
    private String telefono;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe ser válido")
    @Size(max = 100, message = "El correo no debe tener más de 100 caracteres")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(max = 255, message = "La contraseña no debe tener más de 255 caracteres")
    private String password;

    @NotBlank(message = "El rol es obligatorio")
    @Size(max = 50, message = "El rol no debe tener más de 50 caracteres")
    private String rol;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}

