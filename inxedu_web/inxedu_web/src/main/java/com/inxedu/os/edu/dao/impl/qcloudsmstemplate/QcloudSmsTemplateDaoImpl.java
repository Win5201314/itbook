package com.inxedu.os.edu.dao.impl.qcloudsmstemplate;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.qcloudsmstemplate.QcloudSmsTemplateDao;
import com.inxedu.os.edu.entity.qcloudsmstemplate.QcloudSmsTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author www.inxedu.com
 * @description  QcloudSmsTemplateDao接口实现
 */
@Repository("qcloudSmsTemplateDao")
public class QcloudSmsTemplateDaoImpl extends GenericDaoImpl implements QcloudSmsTemplateDao {
	/**
     * 添加
     */
    public Long addQcloudSmsTemplate(QcloudSmsTemplate qcloudSmsTemplate){
    	this.insert("QcloudSmsTemplateMapper.addQcloudSmsTemplate", qcloudSmsTemplate);
		return qcloudSmsTemplate.getId();
    }
    
    /**
     * 删除
     * @param id
     */
    public void delQcloudSmsTemplateById(int id){
    	this.update("QcloudSmsTemplateMapper.delQcloudSmsTemplateById", id);
    }
    
    /**
     * 修改
     * @param qcloudSmsTemplate
     */
    public void updateQcloudSmsTemplate(QcloudSmsTemplate qcloudSmsTemplate){
    	this.update("QcloudSmsTemplateMapper.updateQcloudSmsTemplate", qcloudSmsTemplate);
    }
    
    /**
     * 通过id，查询
     * @param id
     * @return
     */
    public QcloudSmsTemplate getQcloudSmsTemplateById(int id){
    	return this.selectOne("QcloudSmsTemplateMapper.getQcloudSmsTemplateById", id);
    }
    
    /**
     * 分页查询列表
     * @param qcloudSmsTemplate 查询条件
     * @param page 分页条件
     * @return List<QcloudSmsTemplate>
     */
    public List<QcloudSmsTemplate> queryQcloudSmsTemplateListPage(QcloudSmsTemplate qcloudSmsTemplate,PageEntity page){
    	return this.queryForListPage("QcloudSmsTemplateMapper.queryQcloudSmsTemplateListPage", qcloudSmsTemplate, page);
    }
    
    /**
     * 条件查询列表
     * @param qcloudSmsTemplate 查询条件
     * @return List<QcloudSmsTemplate>
     */
    public List<QcloudSmsTemplate> queryQcloudSmsTemplateList(QcloudSmsTemplate qcloudSmsTemplate){
    	return this.selectList("QcloudSmsTemplateMapper.queryQcloudSmsTemplateList", qcloudSmsTemplate);
    }
}



