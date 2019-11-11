package com.inxedu.os.edu.dao.card.impl.card;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.edu.dao.card.CardCourseDao;
import com.inxedu.os.edu.entity.card.CardCourse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * author www.inxedu.com
 */
 @Repository("cardCourseDao")
public class CardCourseDaoImpl extends GenericDaoImpl implements CardCourseDao {

    public void deleteCardCourseById(Long id){
        this.delete("CardCourseMapper.deleteCardCourseById",id);
    }

    public void updateCardCourse(CardCourse cardCourse) {
        this.update("CardCourseMapper.updateCardCourse",cardCourse);
    }

    public CardCourse getCardCourseById(Long id) {
        return this.selectOne("CardCourseMapper.getCardCourseById",id);
    }

    public List<CardCourse> getCardCourseList(CardCourse cardCourse) {
        return this.selectList("CardCourseMapper.getCardCourseList",cardCourse);
    }

	@Override
	public Long addCardCourse(List<CardCourse> cardCourseList) {
		this.insert("CardCourseMapper.createCardCourse", cardCourseList);
		return null;
	}
}
