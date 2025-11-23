package br.com.mindspace.api.config;

import br.com.mindspace.api.model.SessaoTrabalho;
import br.com.mindspace.api.repository.SessaoTrabalhoRepository; // Verifique o nome do repo
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner initDatabase(SessaoTrabalhoRepository repository) {
        return args -> {
            // Verifica se a Sessão ID 1 já existe
            if (!repository.existsById(1L)) {
                System.out.println("Criando SessaoTrabalho padrão (ID 1)...");

                SessaoTrabalho sessao = new SessaoTrabalho();
                sessao.setId(1L); // Força o ID 1
                sessao.setDescricao("Sessao Padrao Mobile/IoT");
                // Defina outros campos obrigatórios aqui (ex: data, usuario, etc)
                // sessao.setDataInicio(LocalDateTime.now());

                repository.save(sessao);

                System.out.println("SessaoTrabalho ID 1 criada com sucesso!");
            } else {
                System.out.println("SessaoTrabalho ID 1 ja existe. Pulando criacao.");
            }
        };
    }
}