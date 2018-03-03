package spring1.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * User: chenshun131 <p />
 * Time: 18/3/3 10:23  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Controller
public class UserAction {

    @Autowired
    private UsreService usreService;

    public void execute() {
        System.out.println("接受请求");
        usreService.addNew();
    }

}
