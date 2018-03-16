package com.chenshun.test.jdbc;

import java.sql.*;
import java.util.Random;

/**
 * User: chenshun131 <p />
 * Time: 18/3/16 22:14  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DBTest {


    public static final String url = "jdbc:mysql://ci-server/test";

    public static final String name = "com.mysql.jdbc.Driver";

    public static final String user = "root";

    public static final String password = "123456";

    public Connection conn = null;

    public PreparedStatement pst = null;

    public DBTest() {
        try {
            Class.forName(name); // 指定连接类型
            conn = DriverManager.getConnection(url, user, password); // 获取连接
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                pst = conn.prepareStatement(
                        "INSERT INTO article(pid,rootid,title,cont,pdate,isleaf) VALUES("
                                + random.nextInt(9999999) + ","
                                + random.nextInt(9999999) + ",'"
                                + getRandomString(50) + "','"
                                + getRandomString(50) + "','2018-03-16',"
                                + random.nextInt(50) + ")"
                );
                pst.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getRandomString(int length) {
        // 定义一个字符串（A-Z，a-z，0-9）即62位；
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        // 由Random生成随机数
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        // 长度为几就循环几次
        for (int i = 0; i < length; ++i) {
            // 产生0-61的数字
            int number = random.nextInt(62);
            // 将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        // 将承载的字符转换成字符串
        return sb.toString();
    }

    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DBTest dbTest = new DBTest();
        dbTest.close();
    }

}
