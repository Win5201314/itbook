package com.inxedu.os.edu.service.card.impl.card;

import com.inxedu.os.edu.dao.card.CardCourseDao;
import com.inxedu.os.edu.entity.card.CardCourse;
import com.inxedu.os.edu.service.card.CardCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *CardCourse管理接口
 * author www.inxedu.com
 */
@Service("cardCourseService")
public class CardCourseServiceImpl implements CardCourseService {

 	@Autowired
    private CardCourseDao cardCourseDao;

    /**
     * 根据id删除一个CardCourse
     * @param id 要删除的id
     */
    public void deleteCardCourseById(Long id){
    	 cardCourseDao.deleteCardCourseById(id);
    }

    /**
     * 修改CardCourse
     * @param cardCourse 要修改的CardCourse
     */
    public void updateCardCourse(CardCourse cardCourse){
     	cardCourseDao.updateCardCourse(cardCourse);
    }

    /**
     * 根据id获取单个CardCourse对象
     * @param id 要查询的id
     * @return CardCourse
     */
    public CardCourse getCardCourseById(Long id){
    	return cardCourseDao.getCardCourseById( id);
    }

    /**
     * 根据条件获取CardCourse列表
     * @param cardCourse 查询条件
     * @return List<CardCourse>
     */
    public List<CardCourse> getCardCourseList(CardCourse cardCourse){
    	return cardCourseDao.getCardCourseList(cardCourse);
    }
}