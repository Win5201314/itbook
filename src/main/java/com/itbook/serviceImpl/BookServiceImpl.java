package com.itbook.serviceImpl;

import com.itbook.entity.Book;
import com.itbook.mapper.SearchBookMapper;
import com.itbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    SearchBookMapper searchBookMapper;

    @Override
    public List<Book> searchBookByName(String name, Integer page) {
        return searchBookMapper.searchBookByName(name, page);
    }
}
