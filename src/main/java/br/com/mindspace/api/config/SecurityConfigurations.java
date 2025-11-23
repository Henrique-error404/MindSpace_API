package br.com.mindspace.api.config;

import br.com.mindspace.api.security.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; // <--- IMPORT NOVO
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    // Rotas de Autenticação e Swagger
                    req.requestMatchers("/auth/**").permitAll();
                    req.requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll();

                    // --- LIBERAÇÃO TOTAL DO CRUD (IOT E MOBILE) ---
                    // Regra geral para leitura e criação
                    req.requestMatchers("/registros/**").permitAll();

                    // Regras EXPLICITAS para garantir que Update (PUT) e Delete (DELETE) passem sem 403
                    req.requestMatchers(HttpMethod.PUT, "/registros/**").permitAll();
                    req.requestMatchers(HttpMethod.DELETE, "/registros/**").permitAll();

                    // Qualquer outra coisa exige login
                    req.anyRequest().authenticated();
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}