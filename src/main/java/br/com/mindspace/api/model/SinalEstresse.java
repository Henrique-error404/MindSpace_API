package br.com.mindspace.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "SINAL_ESTRESSE")
public class SinalEstresse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SINAL_ESTRESSE_SEQ")
    @SequenceGenerator(name = "SINAL_ESTRESSE_SEQ", sequenceName = "SINAL_ESTRESSE_SEQ", allocationSize = 1)
    @Column(name = "ID_SINAL")
    private Long id;

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