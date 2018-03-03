package spring1.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: chenshun131 <p />
 * Time: 18/3/3 10:22  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Service
public class UsreService {

    @Autowired
    private UserDao userDao;

    public void addNew() {
        System.out.println("添加新用户");
        userDao.save();
    }

}
