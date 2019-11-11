package com.itbook.entity;

import lombok.Data;

/**
 * 书籍
 */
@Data
public class Book {

    private Integer id;//关键字
    private String name;//书籍名字
    private String author;//书籍作者
    private String publicAddress;//出版社
    private String describe;//书籍描述
    private User user;//属于谁的书籍
}
