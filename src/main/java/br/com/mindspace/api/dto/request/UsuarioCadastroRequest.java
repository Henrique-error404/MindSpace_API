package br.com.mindspace.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


public class UsuarioCadastroRequest {

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O formato do e-mail é inválido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

    @NotNull(message = "O cargo é obrigatório.")
    private String cargo;

    public @NotBlank(message = "O nome é obrigatório.") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome é obrigatório.") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "O e-mail é obrigatório.") @Email(message = "O formato do e-mail é inválido.") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "O e-mail é obrigatório.") @Email(message = "O formato do e-mail é inválido.") String email) {
        this.email = email;
    }

    public @NotBlank(message = "A senha é obrigatória.") String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank(message = "A senha é obrigatória.") String senha) {
        this.senha = senha;
    }

    public @NotNull(message = "O cargo é obrigatório.") String getCargo() {
        return cargo;
    }

    public void setCargo(@NotNull(message = "O cargo é obrigatório.") String cargo) {
        this.cargo = cargo;
    }

    public @NotNull(message = "O modo de trabalho é obrigatório.") String getModoTrabalho() {
        return modoTrabalho;
    }

    public void setModoTrabalho(@NotNull(message = "O modo de trabalho é obrigatório.") String modoTrabalho) {
        this.modoTrabalho = modoTrabalho;
    }

    @NotNull(message = "O modo de trabalho é obrigatório.")
    private String modoTrabalho;
}