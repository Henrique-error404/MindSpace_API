package br.com.mindspace.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


public class SinalEstresseRequest {

    // O Nível de Estresse deve ser validado (ex: escala de 1 a 10)
    @NotNull(message = "O nível de estresse é obrigatório.")
    @Min(value = 1, message = "O nível mínimo de estresse é 1.")
    @Max(value = 10, message = "O nível máximo de estresse é 10.")
    private Integer nivelEstresse;

    @NotBlank(message = "O humor é obrigatório.")
    private String humor;

    public @NotNull(message = "O nível de estresse é obrigatório.") @Min(value = 1, message = "O nível mínimo de estresse é 1.") @Max(value = 10, message = "O nível máximo de estresse é 10.") Integer getNivelEstresse() {
        return nivelEstresse;
    }

    public void setNivelEstresse(@NotNull(message = "O nível de estresse é obrigatório.") @Min(value = 1, message = "O nível mínimo de estresse é 1.") @Max(value = 10, message = "O nível máximo de estresse é 10.") Integer nivelEstresse) {
        this.nivelEstresse = nivelEstresse;
    }

    public @NotBlank(message = "O humor é obrigatório.") String getHumor() {
        return humor;
    }

    public void setHumor(@NotBlank(message = "O humor é obrigatório.") String humor) {
        this.humor = humor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public @NotNull(message = "O ID da Sessão de Trabalho é obrigatório.") Long getIdSessaoTrabalho() {
        return idSessaoTrabalho;
    }

    public void setIdSessaoTrabalho(@NotNull(message = "O ID da Sessão de Trabalho é obrigatório.") Long idSessaoTrabalho) {
        this.idSessaoTrabalho = idSessaoTrabalho;
    }

    private String observacao;

    // ID da Sessão de Trabalho: Necessário para a FK na tabela SINAL_ESTRESSE
    @NotNull(message = "O ID da Sessão de Trabalho é obrigatório.")
    private Long idSessaoTrabalho;
}