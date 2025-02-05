package rubenbros.register.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        // Rutas públicas: login, registro y recursos estáticos
                        .requestMatchers("/", "/index.html", "/register.html", "/login.html", "/api/auth/**", "/css/**", "/js/**").permitAll()
                        // El resto requiere autenticación
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        // Página de login personalizada (se muestra cuando el usuario no está autenticado)
                        .loginPage("/login.html")
                        // URL a la que se envían las credenciales (debe coincidir con el atributo action del formulario)
                        .loginProcessingUrl("/login")
                        // Si el login es exitoso, redirige a bienvenido.html
                        .defaultSuccessUrl("/bienvenido.html", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/index.html")
                        .permitAll()
                );
        return http.build();
    }
}
