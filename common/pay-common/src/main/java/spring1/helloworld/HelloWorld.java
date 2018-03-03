package spring1.helloworld;

/**
 * User: chenshun131 <p />
 * Time: 18/3/3 10:02  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class HelloWorld {

    private String user;

    public HelloWorld() {
        System.out.println("HelloWorld's constructor...");
    }

    public HelloWorld(String user) {
        this.user = user;
    }

    public void setUser(String user) {
        System.out.println("setUser:" + user);
        this.user = user;
    }

    public void hello() {
        System.out.println("Hello: " + user);
    }

}
