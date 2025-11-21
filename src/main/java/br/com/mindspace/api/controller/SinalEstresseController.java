package br.com.mindspace.api.controller;

import br.com.mindspace.api.dto.request.SinalEstresseRequest;
import br.com.mindspace.api.dto.response.SinalEstresseResponse;
import br.com.mindspace.api.service.SinalEstresseService;
import br.com.mindspace.api.repository.SinalEstresseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/registros")
public class SinalEstresseController {

    @Autowired
    private SinalEstresseService service; // Para a Stored Procedure

    @Autowired
    private SinalEstresseRepository listRepository; // Para Paginação e Filtros

    // ---  POST /registros (Integração Crítica com Stored Procedure) ---
    @PostMapping
    public ResponseEntity<Void> registrar(@RequestBody @Valid SinalEstresseRequest dados) {

        Long novoId = service.getNextSequenceId("SINAL_ESTRESSE_SEQ");

        //  Chama o Serviço que executa a Stored Procedure (PR_SINAL_ESTRESSE_INS)
        // A SP é agora a ÚNICA responsável pela inserção no BD.
        service.registrarSinalComProcedure(
                novoId,
                LocalDateTime.now(),
                dados.getNivelEstresse(),
                dados.getHumor(),
                dados.getObservacao(),
                dados.getIdSessaoTrabalho() // FK
        );

        return ResponseEntity.ok().build();
    }

    // ---  GET /registros (Paginação, Ordenação e Filtros) ---

    @GetMapping
    public ResponseEntity<Page<SinalEstresseResponse>> listar(
            // @PageableDefault configura a paginação padrão: 10 itens, ordenado por dtHora, decrescente.
            @PageableDefault(size = 10, sort = {"dtHora"}, direction = Sort.Direction.DESC) Pageable paginacao) {

        //  Busca os registros com Paginação e Ordenação aplicadas pelo Spring Data JPA
        Page<SinalEstresseResponse> page = listRepository.findAll(paginacao)
                //  Converte a lista de entidades (SinalEstresse) para o DTO de Saída
                .map(SinalEstresseResponse::new);

        return ResponseEntity.ok(page);
    }
}