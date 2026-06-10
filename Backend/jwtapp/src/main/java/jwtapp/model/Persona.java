package jwtapp.model;

import jakarta.persistence.*;


@Entity
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(length = 255)
    private String direccion;
    @Column(length = 15)
    private String telefono;
    @Column(nullable = false, length = 100, unique = true)
    private String correo;
    @Column(nullable = false, length = 255)
    private String password;
    @Column(nullable = false, length = 50)
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
    public void setPassword(String contraseña) { this.password = contraseña; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}