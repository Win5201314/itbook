package com.inxedu.os.edu.service.impl.sensitivewords;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.sensitivewords.SensitiveWordsDao;
import com.inxedu.os.edu.entity.sensitivewords.SensitiveWords;
import com.inxedu.os.edu.service.sensitivewords.SensitiveWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.inxedu.com
 * @description 敏感词 SensitiveWordsService接口实现
 */
@Service("sensitiveWordsService")
public class SensitiveWordsServiceImpl implements SensitiveWordsService {
	@Autowired
	private SensitiveWordsDao sensitiveWordsDao;
	
	/**
     * 添加敏感词
     */
    public Long addSensitiveWords(SensitiveWords sensitiveWords){
		return sensitiveWordsDao.addSensitiveWords(sensitiveWords);
    }
    
    /**
     * 删除敏感词
     * @param id
     */
    public void delSensitiveWordsById(Long id){
    	sensitiveWordsDao.delSensitiveWordsById(id);
    }
    
    /**
     * 修改敏感词
     * @param sensitiveWords
     */
    public void updateSensitiveWords(SensitiveWords sensitiveWords){
    	sensitiveWordsDao.updateSensitiveWords(sensitiveWords);
    }
    
    /**
     * 通过id，查询敏感词
     * @param id
     * @return
     */
    public SensitiveWords getSensitiveWordsById(Long id){
    	return sensitiveWordsDao.getSensitiveWordsById(id);
    }
    
    /**
     * 分页查询敏感词列表
     * @param sensitiveWords 查询条件
     * @param page 分页条件
     * @return List<SensitiveWords>
     */
    public List<SensitiveWords> querySensitiveWordsListPage(SensitiveWords sensitiveWords,PageEntity page){
    	return sensitiveWordsDao.querySensitiveWordsListPage(sensitiveWords, page);
    }
    
    /**
     * 条件查询敏感词列表
     * @param sensitiveWords 查询条件
     * @return List<SensitiveWords>
     */
    public List<SensitiveWords> querySensitiveWordsList(SensitiveWords sensitiveWords){
    	return sensitiveWordsDao.querySensitiveWordsList(sensitiveWords);
    }
}



