package br.com.mindspace.api.config;

import br.com.mindspace.api.security.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF para REST APIs
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // ESSENCIAL para JWT
                .authorizeHttpRequests(req -> {
                    // Rotas de Autenticação e Swagger LIBERADAS
                    req.requestMatchers("/auth/**").permitAll();
                    req.requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll();

                    // --- NOVA REGRA: LIBERAÇÃO DO IOT ---
                    // Permite que o Node-RED e o Mobile acessem /registros sem login
                    req.requestMatchers("/registros/**").permitAll();

                    // Todas as outras rotas EXIGEM autenticação
                    req.anyRequest().authenticated();
                })
                // INJEÇÃO CRÍTICA: Aplica o filtro JWT antes do filtro padrão
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // Expõe o AuthenticationManager (necessário para o POST /login)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // Expõe o PasswordEncoder (BCrypt) para criptografar senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}