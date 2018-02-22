package com.chenshun.lucene.dao;

import com.chenshun.lucene.po.Book;

import java.util.List;

/**
 * User: chenshun131 <p />
 * Time: 18/2/22 23:04  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface BookDao {

    List<Book> queryBooks();

}
