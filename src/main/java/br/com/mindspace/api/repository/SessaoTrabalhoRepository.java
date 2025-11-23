package br.com.mindspace.api.repository;

import br.com.mindspace.api.model.SessaoTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoTrabalhoRepository extends JpaRepository<SessaoTrabalho, Long> {
    // O JpaRepository já traz o método save(), findById(), existsById() prontos!
}