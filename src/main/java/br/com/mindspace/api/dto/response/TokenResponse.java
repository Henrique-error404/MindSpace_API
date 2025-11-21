package br.com.mindspace.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Gera Getters e Setters
@AllArgsConstructor // Gera construtor com todos os campos (token e tipo)
@NoArgsConstructor // Gera construtor vazio para desserialização
public class TokenResponse {

    private String token;
    private String tipo; // Ex: "Bearer"
}