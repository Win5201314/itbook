package com.inxedu.os.edu.service.qcloudsmstemplate;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.qcloudsmstemplate.QcloudSmsTemplate;

import java.util.List;

/**
 * @author www.inxedu.com
 * @description  QcloudSmsTemplateService接口
 */
public interface QcloudSmsTemplateService{
	/**
     * 添加
     */
    Long addQcloudSmsTemplate(QcloudSmsTemplate qcloudSmsTemplate);
    
    /**
     * 删除
     */
    void delQcloudSmsTemplateById(int id);
    
    /**
     * 修改
     */
    void updateQcloudSmsTemplate(QcloudSmsTemplate qcloudSmsTemplate);
    
    /**
     * 通过id，查询
     */
    QcloudSmsTemplate getQcloudSmsTemplateById(int id);
    
    /**
     * 分页查询列表
     */
    List<QcloudSmsTemplate> queryQcloudSmsTemplateListPage(QcloudSmsTemplate qcloudSmsTemplate, PageEntity page);
    
    /**
     * 条件查询列表
     */
    List<QcloudSmsTemplate> queryQcloudSmsTemplateList(QcloudSmsTemplate qcloudSmsTemplate);
    /*查询审查状态 qcloudSmsTemplateList为模板签名集合*/
    List<QcloudSmsTemplate> checkStatus(List<QcloudSmsTemplate> qcloudSmsTemplateList);
}



