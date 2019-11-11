package com.inxedu.os.edu.dao.sensitivewords;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.sensitivewords.SensitiveWords;

import java.util.List;

/**
 * @author www.inxedu.com
 * @description 敏感词 SensitiveWordsDao接口
 */
public interface SensitiveWordsDao{
	/**
     * 添加敏感词
     */
    Long addSensitiveWords(SensitiveWords sensitiveWords);
    
    /**
     * 删除敏感词
     * @param id
     */
    void delSensitiveWordsById(Long id);
    
    /**
     * 修改敏感词
     * @param sensitiveWords
     */
    void updateSensitiveWords(SensitiveWords sensitiveWords);
    
    /**
     * 通过id，查询敏感词
     * @param id
     * @return
     */
    SensitiveWords getSensitiveWordsById(Long id);
    
    /**
     * 分页查询敏感词列表
     * @param sensitiveWords 查询条件
     * @param page 分页条件
     * @return List<SensitiveWords>
     */
    List<SensitiveWords> querySensitiveWordsListPage(SensitiveWords sensitiveWords, PageEntity page);
    
    /**
     * 条件查询敏感词列表
     * @param sensitiveWords 查询条件
     * @return List<SensitiveWords>
     */
    List<SensitiveWords> querySensitiveWordsList(SensitiveWords sensitiveWords);
}



