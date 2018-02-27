package com.chenshun.test.hadoop.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * User: chenshun131 <p />
 * Time: 16/11/17 23:20  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class HiveJdbcClient {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    public static void main(String[] args) throws Exception {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Connection con = DriverManager.getConnection("jdbc:hive2://hadoop-senior01:10000/db_emp", "hive", "hive");
        Statement stmt = con.createStatement();
        String tableName = "emp";
        String sql = "SELECT empno, ename, sal, deptno from " + tableName;
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString(1) + "\t" + res.getString(2) + "\t" + res.getString(3) + "\t" + res.getString(4) + "\n");
        }
        res.close();
        stmt.close();
        con.close();
    }

}
