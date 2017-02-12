package ru.hutoroff.fasten.testtask.service.security;

import org.springframework.stereotype.Service;
import ru.hutoroff.fasten.testtask.service.security.exception.AuthenticationException;

/**
 * Created by hutoroff on 10.02.17.
 */
@Service("tokenProviderService")
public interface TokenProviderService {
    String getToken(String email, String password) throws AuthenticationException;
}
