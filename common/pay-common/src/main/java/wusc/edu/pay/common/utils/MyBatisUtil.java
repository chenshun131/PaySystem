package wusc.edu.pay.common.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * User: chenshun131 <p />
 * Time: 18/3/23 22:39  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MyBatisUtil {

    public static SqlSessionFactory sqlSessionFactory;

    public static ThreadLocal<SqlSession> tl = new ThreadLocal<>();

    static {
        try (InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");) {
            // 创建SqlSessionFactory对象
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取配置文件失败");
        }
    }

    /**
     * 获取SqlSession对象
     *
     * @return
     */
    public static SqlSession openSqlSession() {
        SqlSession sqlSession = tl.get();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            tl.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 关闭SqlSession对象
     */
    public static void closeSqlSession() {
        // 获取SqlSession
        SqlSession sqlSession = openSqlSession();
        sqlSession.close();
        tl.remove();
    }

    /**
     * 事务提交
     */
    public static void commit() {
        SqlSession sqlSession = openSqlSession();
        sqlSession.commit();
        closeSqlSession();
    }

    /**
     * 事务回滚
     */
    public static void rollback() {
        SqlSession sqlSession = openSqlSession();
        sqlSession.rollback();
        closeSqlSession();
    }

    /**
     * 获取DAO实现
     *
     * @param clazz
     * @return
     */
    public static Object getMapper(Class clazz) {
        SqlSession sqlSession = openSqlSession();
        return sqlSession.getMapper(clazz);
    }

}
