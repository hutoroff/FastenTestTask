package ru.hutoroff.fasten.testtask.service.security.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.hutoroff.fasten.testtask.config.ApplicationConfiguration;
import ru.hutoroff.fasten.testtask.jpa.model.TokenEntity;
import ru.hutoroff.fasten.testtask.jpa.model.UserEntity;
import ru.hutoroff.fasten.testtask.service.UserService;
import ru.hutoroff.fasten.testtask.service.security.AuthResult;
import ru.hutoroff.fasten.testtask.service.security.AuthenticationResponse;
import ru.hutoroff.fasten.testtask.service.security.AuthenticationService;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by hutoroff on 12.02.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthenticationServiceJWTTest {

    @Autowired
    AuthenticationService authService;
    @Autowired
    UserService userService;

    @Test
    public void authenticateTest() throws Exception {
        AuthenticationResponse response = authService.authenticate("fpi@bk.ru", "123123");
        assertNotNull(response);
        assertNotNull(response.getResult());
        assertTrue(response.getResult().equals(AuthResult.SUCCESS));
        assertNotNull(response.getToken());

        UserEntity userEntity = userService.getUserByEmail("fpi@bk.ru");
        assertNotNull(userEntity.getTokens());
        assertTrue(userEntity.getTokens().size() == 1);
    }

    @Test
    public void authenticateTest_revoke() throws Exception {
        AuthenticationResponse response = authService.authenticate("fpi@bk.ru", "123123");
        assertNotNull(response);
        assertNotNull(response.getResult());
        assertTrue(response.getResult().equals(AuthResult.SUCCESS));

        UserEntity userEntity = userService.getUserByEmail("fpi@bk.ru");
        assertNotNull(userEntity.getTokens());
        assertTrue(userEntity.getTokens().size() == 1);

        response = authService.authenticate("fpi@bk.ru", "123123");
        assertNotNull(response);
        assertNotNull(response.getResult());
        assertTrue(response.getResult().equals(AuthResult.SUCCESS));

        userEntity = userService.getUserByEmail("fpi@bk.ru");
        assertNotNull(userEntity.getTokens());
        assertTrue(userEntity.getTokens().size() == 2);
        assertTrue(userEntity.getTokens().stream().map(TokenEntity::getIsRevoked)
                .reduce((integer, integer2) -> integer + integer2).orElse(-1).equals(1));
    }
}