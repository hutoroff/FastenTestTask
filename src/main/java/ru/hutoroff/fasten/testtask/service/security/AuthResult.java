package ru.hutoroff.fasten.testtask.service.security;

/**
 * Created by hutoroff on 12.02.17.
 */
public enum AuthResult {
    ERROR_USER_NOT_FOUND("customer.notFound"),
    SUCCESS("success");

    private final String errorCode;

    AuthResult(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return errorCode;
    }
}
