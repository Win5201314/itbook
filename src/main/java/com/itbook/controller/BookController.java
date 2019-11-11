package com.itbook.controller;

import com.itbook.entity.Book;
import com.itbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/searchBook")
    @ResponseBody
    public List<Book> searchBookByName(@RequestParam("name") String name, @RequestParam("page") Integer page) {
        return bookService.searchBookByName(name, page);
    }

}
