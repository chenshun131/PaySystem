package com.chenshun.springdata.commonrepositorymethod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * User: chenshun131 <p />
 * Time: 18/2/28 11:05  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@NoRepositoryBean
public interface CommonMethodTest<T, ID extends Serializable> extends JpaRepository<T, ID> {

    void method();

}
