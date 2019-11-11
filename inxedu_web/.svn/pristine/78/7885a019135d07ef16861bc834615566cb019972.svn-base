package com.inxedu.os.edu.service.impl.qcloudsmstemplate;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.QCloud.QCloudSMS;
import com.inxedu.os.common.util.QCloud.QCloudSMSDate;
import com.inxedu.os.common.util.QCloud.QCloudSMSSignUtil;
import com.inxedu.os.common.util.QCloud.QCloudSMSTemplateUtil;

import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.dao.qcloudsmstemplate.QcloudSmsTemplateDao;
import com.inxedu.os.edu.entity.qcloudsmstemplate.QcloudSmsTemplate;
import com.inxedu.os.edu.service.qcloudsmstemplate.QcloudSmsTemplateService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author www.inxedu.com
 * @description  QcloudSmsTemplateService接口实现
 */
@Service("qcloudSmsTemplateService")
public class QcloudSmsTemplateServiceImpl implements QcloudSmsTemplateService {
	@Autowired
	private QcloudSmsTemplateDao qcloudSmsTemplateDao;
    @Autowired
    private WebsiteProfileService websiteProfileService;
	/**
     * 添加
     */
    public Long addQcloudSmsTemplate(QcloudSmsTemplate qcloudSmsTemplate){
        qcloudSmsTemplate.setCreateTime(new Date());
		return qcloudSmsTemplateDao.addQcloudSmsTemplate(qcloudSmsTemplate);
    }
    
    /**
     * 删除
     */
    public void delQcloudSmsTemplateById(int id){
    	qcloudSmsTemplateDao.delQcloudSmsTemplateById(id);
    }
    
    /**
     * 修改
     */
    public void updateQcloudSmsTemplate(QcloudSmsTemplate qcloudSmsTemplate){
    	qcloudSmsTemplateDao.updateQcloudSmsTemplate(qcloudSmsTemplate);
    }
    
    /**
     * 通过id，查询
     */
    public QcloudSmsTemplate getQcloudSmsTemplateById(int id){
    	return qcloudSmsTemplateDao.getQcloudSmsTemplateById(id);
    }
    /**
     * 分页查询列表
     */
    public List<QcloudSmsTemplate> queryQcloudSmsTemplateListPage(QcloudSmsTemplate qcloudSmsTemplate,PageEntity page){
    	return qcloudSmsTemplateDao.queryQcloudSmsTemplateListPage(qcloudSmsTemplate, page);
    }
    /**
     * 条件查询列表
     */
    public List<QcloudSmsTemplate> queryQcloudSmsTemplateList(QcloudSmsTemplate qcloudSmsTemplate){
    	return qcloudSmsTemplateDao.queryQcloudSmsTemplateList(qcloudSmsTemplate);
    }

    @Override
    public List<QcloudSmsTemplate> checkStatus(List<QcloudSmsTemplate> qcloudSmsTemplateList) {
        Map<String,Object> websitemap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sms.toString());
        Map<String,Object> smsMap=(Map<String,Object>)websitemap.get("sms");
        QCloudSMSSignUtil qCloudSMSSignUtil = new QCloudSMSSignUtil(smsMap.get("sdkappid").toString(),smsMap.get("strAppkey").toString());
        QCloudSMSTemplateUtil qCloudSMSTemplateUtil = new QCloudSMSTemplateUtil(smsMap.get("sdkappid").toString(),smsMap.get("strAppkey").toString());
            if (ObjectUtils.isNotNull(qcloudSmsTemplateList)) {
                    /*如果不为空循环取出模板和签名*/
                for (int i = 0; i < qcloudSmsTemplateList.size(); i++) {
                    QcloudSmsTemplate qcloudSmsTemplate1 = qcloudSmsTemplateList.get(i);
                        /*如果是签名*/
                    if (qcloudSmsTemplate1.getType().equals("sign")) {
                        int otherId = Integer.valueOf(qcloudSmsTemplate1.getOtherId());
                            /*otherid 签名在腾讯云的id*/
                        int[] otherid = {otherId};
                            /*到腾讯云取该签名 （用于更改审查状态）*/
                        String getDate = qCloudSMSSignUtil.querySign(otherid);
                        QCloudSMS resultMap = FastJsonUtil.obj2Object(getDate,QCloudSMS.class);
                        List list = resultMap.getData();
                            /*更改审查状态*/
                        if (ObjectUtils.isNotNull(list)){
                            QCloudSMSDate qCloudSMSDate = (QCloudSMSDate) list.get(0);
                            qcloudSmsTemplateList.get(i).setStatus(qCloudSMSDate.getStatus());
                        }else {
                            qcloudSmsTemplateList.get(i).setStatus(1);
                        }
                    } else {
                        int otherId = Integer.valueOf(qcloudSmsTemplate1.getOtherId());
                           /* 模板在腾讯云的id*/
                        int[] otherid = {otherId};
                             /*到腾讯云取该模板 （用于更改审查状态）*/
                        String getDate = qCloudSMSTemplateUtil.queryTemplate(otherid);
                        QCloudSMS resultMap = FastJsonUtil.obj2Object(getDate,QCloudSMS.class);
                        List list = resultMap.getData();
                        //qcloudSmsTemplate1.setType(qcloudSmsTemplate1.getType()+qCloudSMSDate.getType());
                            /*更改审查状态*/
                        if (ObjectUtils.isNotNull(list)){
                            QCloudSMSDate qCloudSMSDate = (QCloudSMSDate) list.get(0);
                            qcloudSmsTemplateList.get(i).setStatus(qCloudSMSDate.getStatus());
                        }else {
                            qcloudSmsTemplateList.get(i).setStatus(1);
                        }
                    }
                }
            }
            return qcloudSmsTemplateList;
    }
}



