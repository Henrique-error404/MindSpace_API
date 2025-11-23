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

@Data // Mantido por documentação
@Entity
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

    // --- CONSTRUTOR VAZIO MANUAL (CORREÇÃO DE JPA/LOMBOK) ---
    public Usuario() {
    }

    // --- MÉTODOS MANUAIS E IMPLEMENTAÇÕES (Garantia de Compilação) ---

    public String getEmail() {
        return this.email;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String getUsername() {
        return email; // O email é a credencial de login
    }

    // --- GETTERS E SETTERS COMPLETOS (Mantidos por robustez) ---

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return senha; // Retorna o hash da senha
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getModoTrabalho() {
        return modoTrabalho;
    }

    public void setModoTrabalho(String modoTrabalho) {
        this.modoTrabalho = modoTrabalho;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    // --- Implementação UserDetails (Spring Security) ---
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}