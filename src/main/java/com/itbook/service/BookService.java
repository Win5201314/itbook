package com.itbook.service;

import com.itbook.entity.Book;

import java.util.List;

public interface BookService {

    /**
     * 搜索书籍通过名字
     * @param name 书记名字 模糊查询
     * @return 返回类似名字的书籍
     */
    List<Book> searchBookByName(String name, Integer page);
}
