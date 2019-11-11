package com.inxedu.os.edu.service.card;


import com.inxedu.os.edu.entity.card.CardCourse;

import java.util.List;

/**
 *CardCourse管理接口
 * author www.inxedu.com
 */
public interface CardCourseService {



    /**
     * 根据id删除一个CardCourse
     * @param id 要删除的id
     */
    void deleteCardCourseById(Long id);

    /**
     * 修改CardCourse
     * @param cardCourse 要修改的CardCourse
     */
    void updateCardCourse(CardCourse cardCourse);

    /**
     * 根据id获取单个CardCourse对象
     * @param id 要查询的id
     * @return CardCourse
     */
    CardCourse getCardCourseById(Long id);

    /**
     * 根据条件获取CardCourse列表
     * @param cardCourse 查询条件
     * @return List<CardCourse>
     */
    List<CardCourse> getCardCourseList(CardCourse cardCourse);
}