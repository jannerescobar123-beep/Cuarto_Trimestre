package jwtapp.controller;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/home")
public class HomeController {

    // Ruta pública GET
    @GetMapping("/public")
    public String mensajePublico() {
        return "Bienvenido a la API pública de la veterinaria 🐾";
    }
    @GetMapping("/info")
    public String info() {
        return "Esta es una API pública para fines de prueba";
    }
    // Ruta pública POST simulando login (sin seguridad real aún)
    @PostMapping("/login")
    public String loginSimulado(@RequestParam String username, @RequestParam String password)
    {
        if (username.equals("chenao") && password.equals("1234")) {
            return "Login exitoso: Bienvenido, " + username;
        } else {
            return "Credenciales inválidas";
        }
    }
}
