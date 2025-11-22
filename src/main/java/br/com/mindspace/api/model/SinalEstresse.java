package br.com.mindspace.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@Table(name = "SINAL_ESTRESSE")
public class SinalEstresse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SINAL_ESTRESSE_SEQ")
    @SequenceGenerator(name = "SINAL_ESTRESSE_SEQ", sequenceName = "SINAL_ESTRESSE_SEQ", allocationSize = 1)
    @Column(name = "ID_SINAL")
    private Long id;

    public LocalDateTime getDtHora() {
        return dtHora;
    }

    public void setDtHora(LocalDateTime dtHora) {
        this.dtHora = dtHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNivelEstresse() {
        return nivelEstresse;
    }

    public void setNivelEstresse(Integer nivelEstresse) {
        this.nivelEstresse = nivelEstresse;
    }

    public String getHumor() {
        return humor;
    }

    public void setHumor(String humor) {
        this.humor = humor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public SessaoTrabalho getSessaoTrabalho() {
        return sessaoTrabalho;
    }

    public void setSessaoTrabalho(SessaoTrabalho sessaoTrabalho) {
        this.sessaoTrabalho = sessaoTrabalho;
    }

    @Column(name = "DT_HORA", nullable = false)
    private LocalDateTime dtHora;

    @Column(name = "NIVEL_ESTRESSE", nullable = false)
    private Integer nivelEstresse; // NUMBER(2)

    @Column(name = "HUMOR", nullable = false, length = 20)
    private String humor;

    @Column(name = "OBSERVACAO", length = 200)
    private String observacao;

    // Relacionamento ManyToOne com SESSAO_TRABALHO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SESSAO_TRABALHO_ID_SESSAO", nullable = false)
    private SessaoTrabalho sessaoTrabalho;
}