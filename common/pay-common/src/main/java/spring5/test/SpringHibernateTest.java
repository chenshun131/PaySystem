package spring5.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring5.service.BookShopService;
import spring5.service.Cashier;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

public class SpringHibernateTest {

    private ApplicationContext ctx = null;

    private BookShopService bookShopService = null;

    private Cashier cashier = null;

    {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        bookShopService = ctx.getBean(BookShopService.class);
        cashier = ctx.getBean(Cashier.class);
    }

    @Test
    public void testCashier() {
        cashier.checkout("aa", Arrays.asList("1001", "1002"));
    }

    @Test
    public void testBookShopService() {
        bookShopService.purchase("aa", "1001");
    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

}
