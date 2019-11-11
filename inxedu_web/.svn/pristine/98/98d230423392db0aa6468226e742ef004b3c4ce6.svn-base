package com.inxedu.os.edu.dao.impl.sensitivewords;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.sensitivewords.SensitiveWordsDao;
import com.inxedu.os.edu.entity.sensitivewords.SensitiveWords;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author www.inxedu.com
 * @description 敏感词 SensitiveWordsDao接口实现
 */
@Repository("sensitiveWordsDao")
public class SensitiveWordsDaoImpl extends GenericDaoImpl implements SensitiveWordsDao{
	/**
     * 添加敏感词
     */
    public Long addSensitiveWords(SensitiveWords sensitiveWords){
    	this.insert("SensitiveWordsMapper.addSensitiveWords", sensitiveWords);
		return sensitiveWords.getId();
    }
    
    /**
     * 删除敏感词
     * @param id
     */
    public void delSensitiveWordsById(Long id){
    	this.update("SensitiveWordsMapper.delSensitiveWordsById", id);
    }
    
    /**
     * 修改敏感词
     * @param sensitiveWords
     */
    public void updateSensitiveWords(SensitiveWords sensitiveWords){
    	this.update("SensitiveWordsMapper.updateSensitiveWords", sensitiveWords);
    }
    
    /**
     * 通过id，查询敏感词
     * @param id
     * @return
     */
    public SensitiveWords getSensitiveWordsById(Long id){
    	return this.selectOne("SensitiveWordsMapper.getSensitiveWordsById", id);
    }
    
    /**
     * 分页查询敏感词列表
     * @param sensitiveWords 查询条件
     * @param page 分页条件
     * @return List<SensitiveWords>
     */
    public List<SensitiveWords> querySensitiveWordsListPage(SensitiveWords sensitiveWords,PageEntity page){
    	return this.queryForListPage("SensitiveWordsMapper.querySensitiveWordsListPage", sensitiveWords, page);
    }
    
    /**
     * 条件查询敏感词列表
     * @param sensitiveWords 查询条件
     * @return List<SensitiveWords>
     */
    public List<SensitiveWords> querySensitiveWordsList(SensitiveWords sensitiveWords){
    	return this.selectList("SensitiveWordsMapper.querySensitiveWordsList", sensitiveWords);
    }
}



