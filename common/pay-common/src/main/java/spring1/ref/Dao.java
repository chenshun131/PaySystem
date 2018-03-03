package spring1.ref;

/**
 * User: chenshun131 <p />
 * Time: 18/3/3 10:13  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Dao {

    private String dataSource = "dbcp";

    public Dao() {
        System.out.println("Dao's Constructor...");
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public void save() {
        System.out.println("save by " + dataSource);
    }

}
