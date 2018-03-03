package spring2.ref;

import org.springframework.stereotype.Repository;

/**
 * User: chenshun131 <p />
 * Time: 18/3/3 14:02  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Repository
public class UserDao {

    public void save() {
        System.out.println("UserDao's save...");
    }

}
