package spring2.ref;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * User: chenshun131 <p />
 * Time: 18/3/3 14:02  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Controller
public class UserAction {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void execute() {
        System.out.println("execute...");
        userService.addNew();
    }

}
