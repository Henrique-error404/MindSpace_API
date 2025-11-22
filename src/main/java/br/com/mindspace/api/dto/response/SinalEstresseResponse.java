package br.com.mindspace.api.dto.response;

import br.com.mindspace.api.model.SinalEstresse;
import lombok.Getter;
import java.time.LocalDateTime;


public class SinalEstresseResponse {

    private final Long id;
    private final LocalDateTime dtHora;
    private final Integer nivelEstresse;
    private final String humor;
    private final String observacao;
    private final Long idSessaoTrabalho;

    public SinalEstresseResponse(SinalEstresse sinal) {
        this.id = sinal.getId();
        this.dtHora = sinal.getDtHora();
        this.nivelEstresse = sinal.getNivelEstresse();
        this.humor = sinal.getHumor();
        this.observacao = sinal.getObservacao();
        this.idSessaoTrabalho = sinal.getSessaoTrabalho().getId();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDtHora() {
        return dtHora;
    }

    public Integer getNivelEstresse() {
        return nivelEstresse;
    }

    public String getHumor() {
        return humor;
    }

    public String getObservacao() {
        return observacao;
    }

    public Long getIdSessaoTrabalho() {
        return idSessaoTrabalho;
    }
}