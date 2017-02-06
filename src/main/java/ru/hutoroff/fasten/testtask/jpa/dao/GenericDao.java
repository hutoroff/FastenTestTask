package ru.hutoroff.fasten.testtask.jpa.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hutoroff on 16.07.16.
 */
public interface GenericDao<T, Id extends Serializable> {

    Id save(T entity);

    void update(T entity);

    T findById(Id id, boolean lock);

    void delete(T entity);

    List<T> findAll();

    //List<T> findByExample(T exampleInstance);

    T makePersistent(T entity);

    void makeTransient(T entity);
}
