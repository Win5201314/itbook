package com.inxedu.os.edu.dao.card;


import com.inxedu.os.edu.entity.card.Card;

import java.util.List;

/**
 *Card管理接口
 * author www.inxedu.com
 */
public interface CardDao {

    /**
     * 添加Card
     * @param card 要添加的Card
     * @return id
     */
    Long addCard(Card card);

    /**
     * 根据id删除一个Card
     * @param id 要删除的id
     */
    void deleteCardById(Long id);

    /**
     * 修改Card
     * @param card 要修改的Card
     */
    void updateCard(Card card);

    /**
     * 根据id获取单个Card对象
     * @param id 要查询的id
     * @return Card
     */
    Card getCardById(Long id);

    /**
     * 根据条件获取Card列表
     * @param card 查询条件
     * @return List<Card>
     */
    List<Card> getCardList(Card card);
    
    /**
     * 添加课程和学生卡的中间表
     * @param value
     */
    void batchAddCardUserCourse(String value);
}