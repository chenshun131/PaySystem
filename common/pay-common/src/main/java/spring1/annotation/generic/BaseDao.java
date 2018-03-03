package spring1.annotation.generic;

/**
 * User: chenshun131 <p />
 * Time: 18/3/3 10:23  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class BaseDao<T> {

    public void save(T entity) {
        System.out.println("Save:" + entity);
    }

}
