package spring1.ref;

/**
 * User: chenshun131 <p />
 * Time: 18/3/3 10:13  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Action {

    private Service service;

    public void setService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    public void execute() {
        System.out.println("Action's execute...");
        service.save();
    }

}
