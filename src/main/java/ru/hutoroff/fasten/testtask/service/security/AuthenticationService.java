package ru.hutoroff.fasten.testtask.service.security;

import org.springframework.stereotype.Service;

/**
 * Created by hutoroff on 12.02.17.
 */
@Service("authService")
public interface AuthenticationService {
    AuthenticationResponse authenticate(String email, String password);
}
