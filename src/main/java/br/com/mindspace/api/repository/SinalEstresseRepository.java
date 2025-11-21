package br.com.mindspace.api.repository;

import br.com.mindspace.api.model.SinalEstresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

// JpaSpecificationExecutor é CRUCIAL para a implementação de FILTROS dinâmicos.
public interface SinalEstresseRepository extends JpaRepository<SinalEstresse, Long>, JpaSpecificationExecutor<SinalEstresse> {

    // O JpaRepository já fornece:
    // 1. Paginação (findAll(Pageable pageable))
    // 2. Ordenação (ordenar pela coluna especificada no Pageable)
}