package ru.hutoroff.fasten.testtask.jpa.dao.entity.impl.hibernate;

import org.springframework.stereotype.Repository;
import ru.hutoroff.fasten.testtask.jpa.dao.entity.TokenDao;
import ru.hutoroff.fasten.testtask.jpa.dao.impl.GenericHibernateDao;
import ru.hutoroff.fasten.testtask.jpa.model.TokenEntity;

/**
 * Created by hutoroff on 06.02.17.
 */
@Repository
public class TokenHibernateDao extends GenericHibernateDao<TokenEntity, String> implements TokenDao {
}
