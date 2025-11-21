package br.com.mindspace.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SinalEstresseService {

    // Injeta o componente para comunicação direta com o BD
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Adicione este método para buscar o próximo ID da Sequence
    public Long getNextSequenceId(String sequenceName) {
        String sql = "SELECT " + sequenceName + ".NEXTVAL FROM DUAL";
        // Usa o JdbcTemplate para buscar o próximo valor da Sequence
        return jdbcTemplate.queryForObject(sql, Long.class);
    }
    // Método que chama a Stored Procedure PR_SINAL_ESTRESSE_INS
    public void registrarSinalComProcedure(Long idSinal, LocalDateTime dtHora,
                                           Integer nivelEstresse, String humor,
                                           String observacao, Long idSessaoTrabalho) {

        // Assinatura da chamada SQL
        String sql = "{call PR_SINAL_ESTRESSE_INS(?, ?, ?, ?, ?, ?)}";

        // Execução da Stored Procedure
        jdbcTemplate.update(sql,
                idSinal,
                dtHora,
                nivelEstresse,
                humor,
                observacao,
                idSessaoTrabalho);
    }
}