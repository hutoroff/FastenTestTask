package ru.hutoroff.fasten.testtask.jpa.dao.impl;

import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.hutoroff.fasten.testtask.jpa.dao.GenericDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by hutoroff on 19.07.16.
 */
@Transactional
public abstract class GenericHibernateDao<T, Id extends Serializable> implements GenericDao<T, Id> {

    @Autowired
    protected SessionFactory sessionFactory;

    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public GenericHibernateDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    public Id save(T entity) {
        return (Id) this.sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void update(T entity) {
        this.sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public T findById(Id id, boolean lock) {
        T entity;
        if (lock)
            entity = this.sessionFactory.getCurrentSession().load(getPersistentClass(), id, LockMode.UPGRADE_NOWAIT); //TODO: nowait?
        else
            entity = this.sessionFactory.getCurrentSession().load(getPersistentClass(), id);
        return entity;
    }

    @Override
    public void delete(T entity) {
        this.sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public List<T> findAll() {
        return sessionFactory.getCurrentSession().createCriteria(getPersistentClass()).list();
    }

    /*@SuppressWarnings("unchecked")
    @Override
    public List<T> findByExample(T exampleInstance) {
        //return new HibernateTemplate(sessionFactory).findByExample(exampleInstance)
        //FIXME: return real value
        return null;
    }*/

    @Override
    public T makePersistent(T entity) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void makeTransient(T entity) {
        this.sessionFactory.getCurrentSession().delete(entity);
    }

    public void flush() {
        this.sessionFactory.getCurrentSession().flush();
    }

    public void clear() {
        this.sessionFactory.getCurrentSession().clear();
    }

    /*@SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion) {
        //FIXME: return real value
        return null;
    }*/
}
