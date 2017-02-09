package ru.hutoroff.fasten.testtask.security;

import org.springframework.stereotype.Service;

/**
 * Created by hutoroff on 10.02.17.
 */
@Service("tokenProviderService")
public interface TokenProvider {
    String getToken(String login, String password) throws Exception;
}
