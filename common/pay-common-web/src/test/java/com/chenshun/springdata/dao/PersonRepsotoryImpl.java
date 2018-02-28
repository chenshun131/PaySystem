package com.chenshun.springdata.dao;

import com.chenshun.springdata.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: chenshun131 <p />
 * Time: 18/2/28 10:54  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class PersonRepsotoryImpl implements PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void test() {
        Person person = entityManager.find(Person.class, 11);
        System.out.println("-->" + person);
    }

}
