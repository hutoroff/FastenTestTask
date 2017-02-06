package ru.hutoroff.fasten.testtask.jpa.dao.entity.impl.hibernate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.hutoroff.fasten.testtask.config.ApplicationConfiguration;
import ru.hutoroff.fasten.testtask.jpa.dao.entity.TokenDao;
import ru.hutoroff.fasten.testtask.jpa.dao.entity.UserDao;
import ru.hutoroff.fasten.testtask.jpa.model.TokenEntity;
import ru.hutoroff.fasten.testtask.jpa.model.UserEntity;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by hutoroff on 06.02.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class TokenHibernateDaoTest {

    private static final Logger LOG = LoggerFactory.getLogger(TokenHibernateDaoTest.class);

    @Autowired
    TokenDao tokenDao;
    @Autowired
    UserDao userDao;

    @Test
    public void testPersist() throws Exception {
        UserEntity user = new UserEntity();
        user.setLogin("test");
        user.setPassword("token");
        user.setEmail("test@token.com");
        Integer userId = userDao.save(user);
        assertNotNull(userId);
        LOG.info("User inserted. ID: {}", userId);

        TokenEntity token = new TokenEntity();
        token.setUser(userDao.findById(userId, false));
        token.setExpirationDate(new Date());
        token.setToken("fakeTokenId");
        String tokenId = tokenDao.save(token);
        assertNotNull(tokenId);
        LOG.info("Token inserted. ID: {}", tokenId);

        List<TokenEntity> allTokens = tokenDao.findAll();
        assertNotNull(allTokens);
        assertTrue(allTokens.size() > 0);
    }
}