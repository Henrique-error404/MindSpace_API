package br.com.mindspace.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "USUARIOS")
public class Usuario implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
    @SequenceGenerator(name = "USUARIO_SEQ", sequenceName = "USUARIOS_SEQ", allocationSize = 1)
    @Column(name = "ID_USUARIOS")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "EMAIL", length = 100, unique = true)
    private String email;

    @Column(name = "SENHA", nullable = false)
    private String senha;

    @Column(name = "DT_NASCIMENTO")
    private LocalDate dtNascimento;

    @Column(name = "CARGO", nullable = false, length = 60)
    private String cargo;

    @Column(name = "MODO_TRABALHO", nullable = false, length = 20)
    private String modoTrabalho;

    @Column(name = "DATA_CADASTRO")
    private LocalDate dataCadastro;

    // --- Implementação UserDetails (Necessário para JWT) ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Define o papel/permissão do usuário
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha; // Retorna o hash da senha
    }

    @Override
    public String getUsername() {
        return email; // O email é a credencial de login
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}