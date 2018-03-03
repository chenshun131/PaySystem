package spring1.annotation.generic;

import org.springframework.stereotype.Service;

/**
 * User: chenshun131 <p />
 * Time: 18/3/3 10:24  <p />
 * Version: V1.0  <p />
 * Description: 若注解没有指定 bean 的 id, 则类名第一个字母小写即为 bean 的 id <p />
 */
@Service
public class UserService extends BaseService<User> {

}
