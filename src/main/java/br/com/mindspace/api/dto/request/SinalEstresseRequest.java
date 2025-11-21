package br.com.mindspace.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SinalEstresseRequest {

    // O Nível de Estresse deve ser validado (ex: escala de 1 a 10)
    @NotNull(message = "O nível de estresse é obrigatório.")
    @Min(value = 1, message = "O nível mínimo de estresse é 1.")
    @Max(value = 10, message = "O nível máximo de estresse é 10.")
    private Integer nivelEstresse;

    @NotBlank(message = "O humor é obrigatório.")
    private String humor;

    private String observacao;

    // ID da Sessão de Trabalho: Necessário para a FK na tabela SINAL_ESTRESSE
    @NotNull(message = "O ID da Sessão de Trabalho é obrigatório.")
    private Long idSessaoTrabalho;
}