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
import ru.hutoroff.fasten.testtask.jpa.dao.entity.UserDao;
import ru.hutoroff.fasten.testtask.jpa.model.UserEntity;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by hutoroff on 06.02.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class UserHibernateDaoTest {

    public static final Logger LOG = LoggerFactory.getLogger(UserHibernateDaoTest.class);

    @Autowired
    UserDao userDao;

    @Test
    public void testPersist() throws Exception {
        UserEntity user = new UserEntity();
        user.setEmail("dummy@test.com");
        user.setLogin("dummy");
        user.setPassword("pass");

        Integer userId = userDao.save(user);
        LOG.info("User inserted. ID: {}", userId);


        List<UserEntity> allUsers = userDao.findAll();
        assertNotNull(allUsers);
        assertTrue(allUsers.size() > 0);
    }
}