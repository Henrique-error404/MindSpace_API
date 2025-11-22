package br.com.mindspace.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@Table(name = "SESSAO_TRABALHO")
public class SessaoTrabalho {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SESSAO_TRABALHO_SEQ")
    @SequenceGenerator(name = "SESSAO_TRABALHO_SEQ", sequenceName = "SESSAO_TRABALHO_SEQ", allocationSize = 1)
    @Column(name = "ID_SESSAO")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(LocalDateTime dtInicio) {
        this.dtInicio = dtInicio;
    }

    public LocalDateTime getDtFim() {
        return dtFim;
    }

    public void setDtFim(LocalDateTime dtFim) {
        this.dtFim = dtFim;
    }

    public String getModoTrabalho() {
        return modoTrabalho;
    }

    public void setModoTrabalho(String modoTrabalho) {
        this.modoTrabalho = modoTrabalho;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Column(name = "DT_INICIO", nullable = false)
    private LocalDateTime dtInicio;

    @Column(name = "DT_FIM", nullable = false)
    private LocalDateTime dtFim;

    @Column(name = "MODO_TRABALHO", nullable = false, length = 20)
    private String modoTrabalho;

    @Column(name = "DESCRICAO", length = 200)
    private String descricao;

    // Chave estrangeira para USUARIOS
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIOS_ID_USUARIOS", nullable = false)
    private Usuario usuario;
}