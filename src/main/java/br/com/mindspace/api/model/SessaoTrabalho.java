package br.com.mindspace.api.model;

import jakarta.persistence.*;
import lombok.Data; // Mantemos para compatibilidade se funcionar
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "SESSAO_TRABALHO")
public class SessaoTrabalho {

    @Id
    @Column(name = "ID_SESSAO")
    // Nota: Não usamos @GeneratedValue aqui para podermos forçar o ID 1 no Seeder
    // Se quiser automático depois, pode adicionar, mas para o Seeder fixo é melhor assim.
    private Long id;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "DATA_INICIO")
    private LocalDateTime dataInicio;

    // --- CONSTRUTOR VAZIO (Obrigatório para JPA) ---
    public SessaoTrabalho() {
    }

    // --- CONSTRUTOR CHEIO ---
    public SessaoTrabalho(Long id, String descricao, LocalDateTime dataInicio) {
        this.id = id;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
    }

    // --- GETTERS E SETTERS MANUAIS (Garantia de Compilação) ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }
}