package spring1.ref;

/**
 * User: chenshun131 <p />
 * Time: 18/3/3 10:02  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Service {

    private Dao dao;

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public Dao getDao() {
        return dao;
    }

    public void save() {
        System.out.println("Service's save");
        dao.save();
    }

}
