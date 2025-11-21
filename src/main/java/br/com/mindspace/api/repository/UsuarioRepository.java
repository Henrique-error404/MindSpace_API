package br.com.mindspace.api.repository;

import br.com.mindspace.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // O Hibernate usa o @Column(name="EMAIL") para resolver esta query.
    Optional<Usuario> findByEmail(String email);
}