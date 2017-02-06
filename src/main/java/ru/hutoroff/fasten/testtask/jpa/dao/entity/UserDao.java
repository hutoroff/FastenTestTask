package ru.hutoroff.fasten.testtask.jpa.dao.entity;

import org.springframework.stereotype.Repository;
import ru.hutoroff.fasten.testtask.jpa.dao.GenericDao;
import ru.hutoroff.fasten.testtask.jpa.model.UserEntity;

/**
 * Created by hutoroff on 06.02.17.
 */
@Repository("userDao")
public interface UserDao extends GenericDao<UserEntity, Integer> {

    UserEntity findUserByEmail(String email);
}
