package spring1.annotation.generic;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: chenshun131 <p />
 * Time: 18/3/3 10:23  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class BaseService<T> {

    @Autowired
    private BaseDao<T> dao;

    public void addNew(T entity) {
        System.out.println("addNew by " + dao);
        dao.save(entity);
    }

}
