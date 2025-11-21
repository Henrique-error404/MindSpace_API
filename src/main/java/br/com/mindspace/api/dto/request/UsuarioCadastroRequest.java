package br.com.mindspace.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
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

    @NotNull(message = "O modo de trabalho é obrigatório.")
    private String modoTrabalho;
}