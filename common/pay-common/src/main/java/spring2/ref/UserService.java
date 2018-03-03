package spring2.ref;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: chenshun131 <p />
 * Time: 18/3/3 14:02  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addNew() {
        System.out.println("addNew...");
        userDao.save();
    }

}
