package spring2.helloworld;

/**
 * User: chenshun131 <p />
 * Time: 18/3/3 14:02  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class HelloWorld {

    /** 字段 */
    private String user;

    public HelloWorld() {
        System.out.println("HelloWorld's constructor...");
    }

    /**
     * JavaBean 使用 setter 和 getter 来定义属性
     *
     * @param user
     */
    public void setUserName(String user) {
        System.out.println("setUserName:" + user);
        this.user = user;
    }

    public void hello() {
        System.out.println("Hello:" + user);
    }

    public void init() {
        System.out.println("init method...");
    }

    public void destroy() {
        System.out.println("destroy method...");
    }

}
