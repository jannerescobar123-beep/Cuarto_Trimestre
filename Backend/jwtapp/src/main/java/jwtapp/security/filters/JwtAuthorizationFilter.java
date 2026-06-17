package jwtapp.security.filters;

import jwtapp.security.UserDetailsServiceImpl;
import jwtapp.security.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Leer el header "Authorization"
        String authorizationHeader = request.getHeader("Authorization");

        // 2. Verificar que venga y empiece con "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            // 3. Extraer solo el token (quitar "Bearer ")
            String token = authorizationHeader.substring(7);

            // 4. Validar el token
            if (jwtUtil.validarToken(token)) {

                // 5. Extraer el correo del token
                String correo = jwtUtil.obtenerUsername(token);

                // 6. Cargar los datos del usuario desde la BD
                UserDetails userDetails = userDetailsService.loadUserByUsername(correo);

                // 7. Crear el objeto de autenticación con sus roles
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()
                        );

                // 8. Registrar la autenticación en el contexto de Spring Security
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 9. Continuar con la petición (pase o no el token)
        filterChain.doFilter(request, response);
    }
}