package ru.hutoroff.fasten.testtask.service.security;

import ru.hutoroff.fasten.testtask.jpa.model.TokenEntity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthenticationResponse)) return false;
        AuthenticationResponse that = (AuthenticationResponse) o;
        return getResult() == that.getResult() &&
                Objects.equals(getToken(), that.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResult(), getToken());
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "result=" + result +
                ", token=" + token +
                '}';
    }
}
