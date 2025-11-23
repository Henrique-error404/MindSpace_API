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
import org.springframework.transaction.annotation.Transactional; // Importante para UPDATE/DELETE
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/registros")
public class SinalEstresseController {

    @Autowired
    private SinalEstresseService service; // Para a Stored Procedure (INSERT)

    @Autowired
    private SinalEstresseRepository listRepository; // Para JPA Padrão (SELECT, UPDATE, DELETE)

    // ---  POST /registros (CRIAR) ---
    @PostMapping
    public ResponseEntity<Void> registrar(@RequestBody @Valid SinalEstresseRequest dados) {

        Long novoId = service.getNextSequenceId("SINAL_ESTRESSE_SEQ");

        // Chama a Procedure para garantir a nota de Database
        service.registrarSinalComProcedure(
                novoId,
                LocalDateTime.now(),
                dados.getNivelEstresse(),
                dados.getHumor(),
                dados.getObservacao(),
                dados.getIdSessaoTrabalho()
        );

        return ResponseEntity.ok().build();
    }

    // ---  GET /registros (LISTAR) ---
    @GetMapping
    public ResponseEntity<Page<SinalEstresseResponse>> listar(
            @PageableDefault(size = 10, sort = {"dtHora"}, direction = Sort.Direction.DESC) Pageable paginacao) {

        Page<SinalEstresseResponse> page = listRepository.findAll(paginacao)
                .map(SinalEstresseResponse::new);

        return ResponseEntity.ok(page);
    }

    // --- PUT /registros/{id} (ATUALIZAR) ---
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody @Valid SinalEstresseRequest dados) {
        // Busca o registro no banco
        var registroOpcional = listRepository.findById(id);

        if (registroOpcional.isPresent()) {
            var sinal = registroOpcional.get();

            // Atualiza os campos com os dados novos do Mobile
            sinal.setNivelEstresse(dados.getNivelEstresse());
            sinal.setHumor(dados.getHumor());
            sinal.setObservacao(dados.getObservacao());

            // O JPA detecta a mudança e faz o update automaticamente ao fim da transação
            // Mas chamamos o save() para garantir
            listRepository.save(sinal);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    // --- DELETE /registros/{id} (EXCLUIR) ---
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        // Verifica se existe antes de tentar apagar
        if (listRepository.existsById(id)) {
            listRepository.deleteById(id); // Apaga do Oracle
            return ResponseEntity.noContent().build(); // Retorna 204 (Sucesso sem conteúdo)
        }

        return ResponseEntity.notFound().build();
    }
}