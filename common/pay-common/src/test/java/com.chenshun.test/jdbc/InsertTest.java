package com.chenshun.test.jdbc;

import org.junit.Test;
import wusc.edu.pay.common.utils.SnowflakeIdWorker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

/**
 * User: chenshun131 <p />
 * Time: 18/4/15 13:19  <p />
 * Version: V1.0  <p />
 * Description: 插入 1000万条数据
 * SQL :
 * <pre>
 * CREATE TABLE t_teacher(id VARCHAR(18) PRIMARY KEY,
 *                        t_name VARCHAR(100) COMMENT '教师名称',
 *                        t_password VARCHAR(50) COMMENT '密码',
 *                        sex VARCHAR(1) COMMENT '教师性别 m:难 f:女 n:未知',
 *                        description VARCHAR(200) COMMENT '描述信息',
 *                        pic_url VARCHAR(100) COMMENT '头像图片地址',
 *                        school_name VARCHAR(50) COMMENT '学校名称',
 *                        regist_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
 *                        remark VARCHAR(200) COMMENT '评论信息')
 * </pre>
 * <p/>
 */
public class InsertTest {

    private CountDownLatch countDownLatch;

    @Test
    public void test() throws ClassNotFoundException, InterruptedException {
        final String url = "jdbc:mysql://ci-server/test";
        final String name = "com.mysql.jdbc.Driver";
        final String user = "root";
        final String password = "123456";
        Class.forName(name); // 指定连接类型

        // 开始时间
        Long begin = System.currentTimeMillis();
        countDownLatch = new CountDownLatch(5);

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                try {
                    Connection conn = DriverManager.getConnection(url, user, password); // 获取连接
                    if (conn != null) {
                        System.out.println("获取连接[" + Thread.currentThread().getName() + "]成功");
                        insert(conn);
                    } else {
                        System.out.println("获取连接[" + Thread.currentThread().getName() + "]失败");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }, "Thread" + i).start();
        }

        countDownLatch.await();
        // 结束时间
        Long end = System.currentTimeMillis();
        // 耗时
        System.out.println("1000万条数据插入花费时间 : " + (end - begin) / 1000 + " s");
        System.out.println("插入完成");
    }

    public void insert(Connection conn) {
        // sql前缀
        String prefix = "INSERT INTO t_teacher (id,t_name,t_password,sex,description,pic_url,school_name,regist_date,remark) VALUES ";
        // 使用 Snow 算法创建主键
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, Thread.currentThread().getId() % 32);
        try {
            // 保存sql后缀
            StringBuffer suffix;
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // 比起st，pst会更好些
            PreparedStatement pst = conn.prepareStatement(""); // 准备执行语句
            long id = Thread.currentThread().getId();
            // 外层循环，总提交事务次数
            for (int i = 1; i <= 20; i++) {
                suffix = new StringBuffer();
                // 第j次提交步长
                for (int j = 1; j <= 100000; j++) {
                    // 构建SQL后缀
                    suffix.append("('").append(idWorker.nextId()).append("','").append(i * j).append("','123456'").append(",'男'").append(",'教师'")
                            .append(",'www.bbk.com'").append(",'XX大学'").append(",'").append("2018-04-15 13:22:26").append("','备注信息").append(id)
                            .append("'").append("),");
                }
                // 构建完整SQL
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                // 添加执行SQL
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();
                // 提交事务
                conn.commit();
            }
            // 头等连接
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("插入数据失败");
        } finally {
            countDownLatch.countDown();
        }
    }

}
