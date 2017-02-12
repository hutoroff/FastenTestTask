package ru.hutoroff.fasten.testtask.service.security;

import ru.hutoroff.fasten.testtask.jpa.model.TokenEntity;

/**
 * Created by hutoroff on 12.02.17.
 */
public class AuthenticationResponse {
    private final AuthResult result;
    private final TokenEntity token;

    public AuthenticationResponse(AuthResult result, TokenEntity token) {
        this.result = result;
        this.token = token;
    }

    public AuthResult getResult() {
        return result;
    }

    public TokenEntity getToken() {
        return token;
    }
}
