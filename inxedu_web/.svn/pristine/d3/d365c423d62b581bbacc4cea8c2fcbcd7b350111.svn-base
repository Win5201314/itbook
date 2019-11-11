package com.inxedu.os.edu.dao.card.impl.card;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.edu.dao.card.CardDao;
import com.inxedu.os.edu.entity.card.Card;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * author www.inxedu.com
 */
 @Repository("cardDao")
public class CardDaoImpl extends GenericDaoImpl implements CardDao {

    public Long addCard(Card card) {
        return this.insert("CardMapper.createCard",card);
    }

    public void deleteCardById(Long id){
        this.delete("CardMapper.deleteCardById",id);
    }

    public void updateCard(Card card) {
        this.update("CardMapper.updateCard",card);
    }

    public Card getCardById(Long id) {
        return this.selectOne("CardMapper.getCardById",id);
    }

    public List<Card> getCardList(Card card) {
        return this.selectList("CardMapper.getCardList",card);
    }
    
    @Override
	public void batchAddCardUserCourse(String value) {
		this.insert("CardMapper.batchAddCardUserCourse", value);
	}
}
