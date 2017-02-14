package ru.hutoroff.fasten.testtask.service;

import org.springframework.stereotype.Service;
import ru.hutoroff.fasten.testtask.jpa.model.UserEntity;
import ru.hutoroff.fasten.testtask.service.data.UserData;

/**
 * Created by hutoroff on 15.02.17.
 */
@Service
public class MapperService {

    public UserEntity toUserEntity(UserData userData) {
        UserEntity result = new UserEntity();
        result.setEmail(userData.getEmail());
        result.setPassword(userData.getPassword());
        result.setLogin(prepareUserLogin(userData.getEmail()));

        return result;
    }

    private String prepareUserLogin(String email) {
        if(email == null)
            return null;

        int separatorIdx = email.indexOf('@');
        if(separatorIdx == -1)
            return email;
        return email.substring(0, separatorIdx);
    }
}
