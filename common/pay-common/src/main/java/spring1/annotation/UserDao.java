package spring1.annotation;

import org.springframework.stereotype.Service;

/**
 * User: chenshun131 <p />
 * Time: 18/3/3 10:23  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Service
public class UserDao {

    public void save() {
        System.out.println("添加新用户");
    }

}
