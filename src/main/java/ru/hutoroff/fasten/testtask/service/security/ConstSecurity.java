package ru.hutoroff.fasten.testtask.service.security;

import java.util.HashMap;

/**
 * Created by hutoroff on 12.02.17.
 */
public class ConstSecurity {
    public static final String TOKEN_KEY = "fasten";

    public static final String TOKEN_BODY_USER_ID = "userId";
    public static final String TOKEN_BODY_USER_LOGIN = "login";
    public static final String TOKEN_BODY_EMAIL = "email";
    public static final String TOKEN_BODY_CREATION_DATE = "token_creation_date";
    public static final String TOKEN_BODY_EXPIRATION_DATE = "token_expiration_date";

    public static final HashMap<AuthResult, String> messagesForCodes = new HashMap<AuthResult, String>() {
        {
            put(AuthResult.SUCCESS, "");
            put(AuthResult.ERROR_USER_NOT_FOUND, "Customer not found");
        }
    };
}
