package br.com.mindspace.api.controller;

import br.com.mindspace.api.dto.request.LoginRequest;
import br.com.mindspace.api.dto.request.UsuarioCadastroRequest;
import br.com.mindspace.api.dto.response.TokenResponse;
import br.com.mindspace.api.model.Usuario;
import br.com.mindspace.api.repository.UsuarioRepository;
import br.com.mindspace.api.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager; // Reintroduzido
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder; // Reintroduzido
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired private AuthenticationManager manager;
    @Autowired private TokenService tokenService;
    @Autowired private UsuarioRepository repository;
    @Autowired private PasswordEncoder passwordEncoder; // BCrypt para hash!

    // Endpoint de LOGIN
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest dados) {
        // Usa o AuthenticationManager (que usa o PasswordEncoder)
        var token = new UsernamePasswordAuthenticationToken(dados.getEmail(), dados.getSenha());
        Authentication authentication = manager.authenticate(token);

        // Gera o token JWT (Auth0)
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenResponse(tokenJWT, "Bearer"));
    }

    // Endpoint de CADASTRO
    @PostMapping("/register")
    @Transactional
    public ResponseEntity<Void> register(@RequestBody @Valid UsuarioCadastroRequest dados) {

        if (repository.findByEmail(dados.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Usuario novoUsuario = new Usuario();

        // AÇÃO CRÍTICA: Criptografa a senha antes de salvar!
        String senhaCriptografada = passwordEncoder.encode(dados.getSenha());

        novoUsuario.setNome(dados.getNome());
        novoUsuario.setEmail(dados.getEmail());
        novoUsuario.setCargo(dados.getCargo());
        novoUsuario.setModoTrabalho(dados.getModoTrabalho());
        novoUsuario.setSenha(senhaCriptografada); // Salva o HASH
        novoUsuario.setDataCadastro(LocalDate.now());

        repository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }
}