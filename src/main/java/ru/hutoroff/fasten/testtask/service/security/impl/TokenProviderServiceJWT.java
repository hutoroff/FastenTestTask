package ru.hutoroff.fasten.testtask.service.security.impl;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hutoroff.fasten.testtask.jpa.dao.entity.UserDao;
import ru.hutoroff.fasten.testtask.jpa.model.TokenEntity;
import ru.hutoroff.fasten.testtask.jpa.model.UserEntity;
import ru.hutoroff.fasten.testtask.service.security.exception.AuthenticationException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by hutoroff on 10.02.17.
 */
@Service
public class TokenProviderServiceJWT implements ru.hutoroff.fasten.testtask.service.security.TokenProviderService {

    private static final String TOKEN_KEY = "fasten";

    @Autowired
    private UserDao userDao;

    @Override
    public String getToken(String email, String password) throws AuthenticationException {
        if(email == null || password == null)
            return null;

        UserEntity userEntity = userDao.findUserByEmail(email);
        if(!password.equals(userEntity.getPassword()))
            throw new AuthenticationException("User password does not match to received password");

        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setUser(userEntity);
        tokenEntity.setCreationDate(new Date());
        tokenEntity.setExpirationDate(calculateExpirationDate(tokenEntity.getCreationDate()));

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setExpiration(tokenEntity.getExpirationDate());
        jwtBuilder.setClaims(prepareTokenData(tokenEntity));
        return jwtBuilder.signWith(SignatureAlgorithm.HS256, TOKEN_KEY).compact();
    }

    private Date calculateExpirationDate(Date creationDate) {
        if(creationDate == null)
            return null;

        LocalDateTime localDateTime = creationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(localDateTime.plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
    }

    private HashMap<String, Object> prepareTokenData(TokenEntity tokenEntity) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userId", tokenEntity.getUser().getId());
        result.put("login", tokenEntity.getUser().getLogin());
        result.put("email", tokenEntity.getUser().getEmail());
        result.put("token_creation_date", tokenEntity.getCreationDate());
        result.put("token_expiration_date", tokenEntity.getExpirationDate());

        return result;
    }
}
