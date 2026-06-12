package jwtapp.security;


import jwtapp.model.Persona;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {
    private Persona persona;
    public SecurityUser(Persona persona) {
        this.persona=persona;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + persona.getRol()));
    }
    @Override
    public String getPassword() {
        return persona.getPassword();
    }
    @Override
    public String getUsername() {
        return persona.getCorreo();
    }
}
