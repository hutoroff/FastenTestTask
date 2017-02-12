package ru.hutoroff.fasten.testtask.service.security.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hutoroff.fasten.testtask.jpa.dao.entity.TokenDao;
import ru.hutoroff.fasten.testtask.jpa.model.TokenEntity;
import ru.hutoroff.fasten.testtask.jpa.model.UserEntity;
import ru.hutoroff.fasten.testtask.service.UserService;
import ru.hutoroff.fasten.testtask.service.security.AuthResult;
import ru.hutoroff.fasten.testtask.service.security.AuthenticationService;
import ru.hutoroff.fasten.testtask.service.security.ConstSecurity;
import ru.hutoroff.fasten.testtask.service.security.TokenProviderService;
import ru.hutoroff.fasten.testtask.service.security.exception.AuthenticationException;

import java.util.Date;

/**
 * Created by hutoroff on 12.02.17.
 */
@Service
@Transactional
public class AuthenticationServiceJWT implements AuthenticationService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationServiceJWT.class);

    @Autowired
    UserService userService;
    @Autowired
    TokenDao tokenDao;
    @Autowired
    TokenProviderService tokenProviderService;

    @Override
    public AuthResult authenticate(String email, String password) {
        if(email == null || password == null) {
            LOG.warn("Incorrect data received to authentication service: email {}, password {}", email, password);
            return AuthResult.ERROR_USER_NOT_FOUND;
        }

        String token;
        try {
            token = tokenProviderService.getToken(email, password);
        } catch (AuthenticationException e) {
            LOG.error("Authentication error on getting token for email {}. Caused by: ", e);
            return AuthResult.ERROR_USER_NOT_FOUND;
        }

        UserEntity user = userService.getUserByEmail(email);
        Date now = new Date();
        user.getTokens().stream()
                .filter(tokenEntity -> tokenEntity.getExpirationDate().after(now))
                .forEach(tokenEntity -> tokenEntity.setIsRevoked(1));
        TokenEntity newToken = prepareTokenEntity(token);
        newToken.setUser(user);
        user.getTokens().add(newToken);
        tokenDao.save(newToken);
        userService.saveUser(user);

        return AuthResult.SUCCESS;
    }

    private TokenEntity prepareTokenEntity(String token) {
        if(token == null)
            return null;


        Claims claims;
        try {
            Jwt parse = Jwts.parser().setSigningKey(ConstSecurity.TOKEN_KEY).parse(token);
            claims = (Claims) parse.getBody();
        } catch (Exception e) {
            LOG.error("Error on parsing token. Caused by: ", e);
            return null;
        }


        TokenEntity result = new TokenEntity();
        result.setToken(token);
        result.setExpirationDate(claims.get(ConstSecurity.TOKEN_BODY_EXPIRATION_DATE, Date.class));

        return result;
    }
}
