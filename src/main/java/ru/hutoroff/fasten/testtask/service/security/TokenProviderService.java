package ru.hutoroff.fasten.testtask.service.security;

import org.springframework.stereotype.Service;

/**
 * Created by hutoroff on 10.02.17.
 */
@Service("tokenProviderService")
public interface TokenProviderService {
    String getToken(String login, String password) throws Exception;
}
