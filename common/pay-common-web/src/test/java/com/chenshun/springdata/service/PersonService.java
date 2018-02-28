package com.chenshun.springdata.service;

import com.chenshun.springdata.dao.PersonRepsotory;
import com.chenshun.springdata.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: chenshun131 <p />
 * Time: 18/2/28 10:58  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepsotory personRepsotory;

    @Transactional
    public void savePersons(List<Person> persons) {
        personRepsotory.saveAll(persons);
    }

    @Transactional
    public void updatePersonEmail(String email, Integer id) {
        personRepsotory.updatePersonEmail(id, email);
    }

}
