package ru.hutoroff.fasten.testtask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.hutoroff.fasten.testtask.config.ApplicationConfiguration;
import ru.hutoroff.fasten.testtask.jpa.dao.entity.TokenDao;
import ru.hutoroff.fasten.testtask.jpa.dao.entity.UserDao;
import ru.hutoroff.fasten.testtask.jpa.model.TokenEntity;
import ru.hutoroff.fasten.testtask.jpa.model.UserEntity;
import ru.hutoroff.fasten.testtask.service.security.impl.TokenProviderServiceJWT;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by hutoroff on 10.02.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class TokenProviderServiceTest {

    @Autowired
    TokenProviderServiceJWT tokenProviderService;

    @Autowired
    UserDao userDao;

    @Autowired
    TokenDao tokenDao;

    @Test
    public void getToken() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("dummy@user.com");
        userEntity.setLogin("dummy");
        userEntity.setPassword("1234567890");
        Integer userId = userDao.save(userEntity);
        assertNotNull(userId);

        String token = tokenProviderService.getToken(userEntity.getEmail(), userEntity.getPassword());
        assertNotNull(token);
        assertFalse(token.isEmpty());

        TokenEntity tokenFromDb = tokenDao.findById(token, false);
        assertNotNull(tokenFromDb);
    }

}