package ru.hutoroff.fasten.testtask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.hutoroff.fasten.testtask.config.ApplicationConfiguration;
import ru.hutoroff.fasten.testtask.jpa.model.UserEntity;

import static org.junit.Assert.*;

/**
 * Created by hutoroff on 06.02.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void saveUser() throws Exception {
        UserEntity user = new UserEntity();
        user.setEmail("test@dummy.com");
        user.setPassword("pass");

        Integer id = userService.saveUser(user);
        assertNotNull(id);
    }

    @Test
    public void getUserByEmail() throws Exception {
        String email = "testByEmail@dummy.com";
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPassword("pass");

        Integer id = userService.saveUser(user);
        UserEntity userFromDb = userService.getUserByEmail(email);
        assertNotNull(userFromDb);
        assertEquals(email, userFromDb.getEmail());
        assertEquals(id, userFromDb.getId());
    }

    @Test
    public void getUserByEmailNull() throws Exception {
        UserEntity userByEmail = userService.getUserByEmail(null);
        assertNull(userByEmail);
    }

}