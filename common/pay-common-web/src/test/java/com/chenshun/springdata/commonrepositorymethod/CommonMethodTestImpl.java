package com.chenshun.springdata.commonrepositorymethod;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * User: chenshun131 <p />
 * Time: 18/2/28 11:06  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@NoRepositoryBean
public class CommonMethodTestImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements CommonMethodTest<T, ID> {

    public CommonMethodTestImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    public CommonMethodTestImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public void method() {
        System.out.println("...METHOD TEST...");
    }

}
