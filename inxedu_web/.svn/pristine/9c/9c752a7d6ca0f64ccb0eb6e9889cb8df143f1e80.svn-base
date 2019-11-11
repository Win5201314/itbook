package com.inxedu.os.edu.dao.card.impl.card;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.card.CardCodeDao;
import com.inxedu.os.edu.entity.card.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * author www.inxedu.com
 */
@Repository("cardCodeDao")
public class CardCodeDaoImpl extends GenericDaoImpl implements CardCodeDao {

	public Long addCardCode(CardCode cardCode) {
		return this.insert("CardCodeMapper.createCardCode", cardCode);
	}

	public void deleteCardCodeById(Long id) {
		this.delete("CardCodeMapper.deleteCardCodeById", id);
	}

	public void updateCardCode(CardCode cardCode) {
		this.update("CardCodeMapper.updateCardCode", cardCode);
	}

	public CardCode getCardCodeById(Long id) {
		return this.selectOne("CardCodeMapper.getCardCodeById", id);
	}

	public List<CardCode> getCardCodeList(CardCode cardCode) {
		return this.selectList("CardCodeMapper.getCardCodeList", cardCode);
	}

	public void addCardCodeList(List<CardCode> cardCodeList) {
		this.insert("CardCodeMapper.addCardCodeList", cardCodeList);
	}

	@Override
	public Long getLastCardCodeId() {
		return this.selectOne("CardCodeMapper.getCardCodeLastId", "");
	}

	@Override
	public List<CardCode> getHistoryByQueryCardCode(QueryCardCode QueryCardCode) {
		return this.selectList("CardCodeMapper.getCardHistory", QueryCardCode);
	}

	@Override
	public List<CourseCardDTO> getCardListByCondtion(QueryCardCode queryCardCode, PageEntity page) {
		return this.queryForListPage("CardCodeMapper.getCardHistoryCondition", queryCardCode, page);
	}

	@Override
	public List<CourseCardDTO> getAllCardHistoryCondition(QueryCardCode queryCardCode) {
		return this.selectList("CardCodeMapper.getAllCardHistoryCondition", queryCardCode);
	}

	@Override
	public List<MainCardDTO> getMainCardListCondition(QueryMainCard queryMainCard, PageEntity page) {
		return this.queryForListPage("CardCodeMapper.getMainCardCondition", queryMainCard, page);
	}

	@Override
	public void closeCardCodeStatus(List<CardCode> listCardCode) {
		this.update("CardCodeMapper.closeCardCodeStatus", listCardCode);
	}

	@Override
	public List<CourseCardDTO> getCardCodeStatus() {
		return this.selectList("CardCodeMapper.getCardCodeStatus", null);
	}

	@Override
	public void updateCardCodeStatus(String cardIds) {
		this.update("CardCodeMapper.updateCardCodeStatus", cardIds);
	}

	@Override
	public void updateCardStatusByCode(String cardCode) {
		this.update("CardCodeMapper.updateCardStatusByCode", cardCode);
	}

	@Override
	public CardCode queryCardCodeByCode(String cardCode) {
		return this.selectOne("CardCodeMapper.queryCardCodeByCode", cardCode);
	}

	@Override
	public List<Long> queryCardCodeCourseIdsByCodeId(long codeId) {
		return this.selectList("CardCodeMapper.queryCardCodeCourseIdsByCodeId", codeId);
	}
	@Override
	public void updateCardStatus() {
		this.update("CardCodeMapper.updateCardStatus", null);
	}
}
