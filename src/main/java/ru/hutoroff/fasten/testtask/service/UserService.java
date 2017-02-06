package ru.hutoroff.fasten.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hutoroff.fasten.testtask.jpa.dao.entity.UserDao;
import ru.hutoroff.fasten.testtask.jpa.model.UserEntity;

/**
 * Created by hutoroff on 06.02.17.
 */
@Service
@Transactional
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Integer saveUser(UserEntity user) {
        return userDao.save(user);
    }

    public UserEntity getUserByEmail(String email) {
        if(email == null)
            return null;

        return userDao.findUserByEmail(email);
    }
}
