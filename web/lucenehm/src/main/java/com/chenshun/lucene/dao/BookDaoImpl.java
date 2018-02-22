package com.chenshun.lucene.dao;

import com.chenshun.lucene.po.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * User: chenshun131 <p />
 * Time: 18/2/22 23:05  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class BookDaoImpl implements BookDao {

    @Override
    public List<Book> queryBooks() {
        // 数据库链接
        Connection connection = null;

        // 预编译statement
        PreparedStatement preparedStatement = null;

        // 结果集
        ResultSet resultSet = null;

        // 图书列表
        List<Book> list = new ArrayList<Book>();

        try {
            // 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 连接数据库
            connection = DriverManager.getConnection("jdbc:mysql://ci-server:3306/solr", "root", "123456");

            // SQL语句
            String sql = "SELECT * FROM book";
            // 创建preparedStatement
            preparedStatement = connection.prepareStatement(sql);

            // 获取结果集
            resultSet = preparedStatement.executeQuery();

            // 结果集解析
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setPrice(resultSet.getFloat("price"));
                book.setPic(resultSet.getString("pic"));
                book.setDescription(resultSet.getString("description"));
                list.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}