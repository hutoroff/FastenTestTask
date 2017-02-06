package ru.hutoroff.fasten.testtask.jpa;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.hutoroff.fasten.testtask.jpa.dao.entity.impl.hibernate.TokenHibernateDaoTest;
import ru.hutoroff.fasten.testtask.jpa.dao.entity.impl.hibernate.UserHibernateDaoTest;

/**
 * Created by hutoroff on 07.02.17.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TokenHibernateDaoTest.class, UserHibernateDaoTest.class})
public class JpaTestSuite {
}
