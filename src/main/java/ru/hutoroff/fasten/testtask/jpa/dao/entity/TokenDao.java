package ru.hutoroff.fasten.testtask.jpa.dao.entity;

import org.springframework.stereotype.Repository;
import ru.hutoroff.fasten.testtask.jpa.dao.GenericDao;
import ru.hutoroff.fasten.testtask.jpa.model.TokenEntity;

/**
 * Created by hutoroff on 06.02.17.
 */
@Repository("tokenDao")
public interface TokenDao extends GenericDao<TokenEntity, String> {
}
