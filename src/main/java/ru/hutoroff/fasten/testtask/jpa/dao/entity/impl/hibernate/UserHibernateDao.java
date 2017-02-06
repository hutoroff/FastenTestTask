package ru.hutoroff.fasten.testtask.jpa.dao.entity.impl.hibernate;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.hutoroff.fasten.testtask.jpa.dao.entity.UserDao;
import ru.hutoroff.fasten.testtask.jpa.dao.impl.GenericHibernateDao;
import ru.hutoroff.fasten.testtask.jpa.model.UserEntity;

/**
 * Created by hutoroff on 06.02.17.
 */
@Repository
public class UserHibernateDao extends GenericHibernateDao<UserEntity, Integer> implements UserDao {

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserEntity findUserByEmail(String email) {
        if(email == null)
            return null;
        Criteria criteria = sessionFactory.openSession().createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("email", email));
        return (UserEntity) criteria.uniqueResult();
    }
}
