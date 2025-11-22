package br.com.mindspace.api.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenResponse {

    private String token;
    private String tipo;

    // --- CONSTRUTOR MANUAL (A Solução) ---
    public TokenResponse(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    // --- GETTERS E SETTERS MANUAIS (Garantia extra) ---
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}