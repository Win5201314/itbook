package com.itbook.mapper;

import com.itbook.entity.Book;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SearchBookMapper {

    @Select("select * from book where name like '%#{name}%' limit 10 * #{page}")
    List<Book> searchBookByName(@Param("name") String name, @Param("page") Integer page);

}
