package com.inxedu.os.edu.service.card;

import com.inxedu.os.edu.entity.card.CardCode;
import com.inxedu.os.edu.entity.card.QueryCardCode;

import java.util.List;

/**
 *CardCode管理接口
 * author www.inxedu.com
 */
public interface CardCodeService {

    /**
     * 添加CardCode
     * @param cardCode 要添加的CardCode
     * @return id
     */
    Long addCardCode(CardCode cardCode);

    /**
     * 根据id删除一个CardCode
     * @param id 要删除的id
     */
    void deleteCardCodeById(Long id);

    /**
     * 修改CardCode
     * @param cardCode 要修改的CardCode
     */
    void updateCardCode(CardCode cardCode);

    /**
     * 根据id获取单个CardCode对象
     * @param id 要查询的id
     * @return CardCode
     */
    CardCode getCardCodeById(Long id);

    /**
     * 根据条件获取CardCode列表
     * @param cardCode 查询条件
     * @return List<CardCode>
     */
    List<CardCode> getCardCodeList(CardCode cardCode);

    /**
     *
     * @param queryCardCode
     * @return
     */
    List<CardCode> getHistoryByQueryCardCode(QueryCardCode queryCardCode);

    
    /**
     * 激活课程卡/充值卡
     * @param cardCode
     * @param userId
     * @return
     */
    String activationCard(CardCode cardCode, Long userId, int type) throws Exception;
    
    
    /**
     * 修改课程卡信息
     * @param cardId
     */
    void closeMainCard(long cardId);
    
    
    /**
     * 定时器
     * 课程卡编码过期操作
     */
    void updateCardStatus();
    
    /**
     * 根据卡编码  修改   未使用  为  已使用  状态
     * @param cardCode
     */
    void updateCardStatusByCode(String cardCode);
    
    /**
     * 通过编码查询，学员卡信息
     * @param cardCode
     * @return
     */
    CardCode queryCardCodeByCode(String cardCode);
    
    /**
     * 生成学员卡用户登录的订单
     * @param loginAccount
     * @param userId
     */
    void createLoginUserCouserOrder(String loginAccount, long userId)  throws Exception;
    
    /**
     * 通过学员卡ID，查询对应的课程
     * @param codeId
     * @return
     */
    List<Long> queryCardCodeCourseIdsByCodeId(long codeId);
    /*定时更新课程卡过期状态*/
    void updCardStatus();
}