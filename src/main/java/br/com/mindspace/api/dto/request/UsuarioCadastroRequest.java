package br.com.mindspace.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
// Removemos o Lombok para evitar erros de compilação

public class UsuarioCadastroRequest {

    // --- CAMPOS (Declaração Única) ---
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O formato do e-mail é inválido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

    @NotNull(message = "O cargo é obrigatório.")
    private String cargo;

    @NotNull(message = "O modo de trabalho é obrigatório.")
    private String modoTrabalho;

    // --- CONSTRUTOR VAZIO (Obrigatório para o Spring/Jackson) ---
    public UsuarioCadastroRequest() {
    }

    // --- GETTERS E SETTERS MANUAIS (Para corrigir o erro de compilação) ---

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
}