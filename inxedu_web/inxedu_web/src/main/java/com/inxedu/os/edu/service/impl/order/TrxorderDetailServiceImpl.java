package com.inxedu.os.edu.service.impl.order;

import com.inxedu.os.edu.constants.enums.MsgRemindStatus;
import com.inxedu.os.edu.dao.course.CourseTeacherDao;
import com.inxedu.os.edu.dao.order.TrxorderDetailDao;
import com.inxedu.os.edu.entity.order.QueryTrxorderDetail;
import com.inxedu.os.edu.entity.order.TrxorderDetail;
import com.inxedu.os.edu.entity.teacher.Teacher;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.letter.MsgReceiveService;
import com.inxedu.os.edu.service.order.TrxorderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.StringUtils;
import org.springframework.stereotype.Service;
import com.inxedu.os.common.util.ObjectUtils;

import java.util.*;

/**
 * TrxorderDetail管理接口
 * @author www.inxedu.com
 */
@Service("trxorderDetailService")
public class TrxorderDetailServiceImpl implements TrxorderDetailService {

 	@Autowired
    private TrxorderDetailDao trxorderDetailDao;
 	@Autowired
    private CourseTeacherDao courseTeacherDao;

    /**
     * 添加TrxorderDetail
     * @param trxorderDetail 要添加的TrxorderDetail
     */
    public Long addTrxorderDetail(TrxorderDetail trxorderDetail){
    	return trxorderDetailDao.addTrxorderDetail(trxorderDetail);
    }
    
    /**
     * 批量添加TrxorderDetail
     */
    public void addBatchTrxorderDetail(List<TrxorderDetail> trxorderDetail){
        trxorderDetailDao.addBatchTrxorderDetail(trxorderDetail);
    }

    /**
     * 根据id删除一个TrxorderDetail
     */
    public void deleteTrxorderDetailById(Long id){
    	 trxorderDetailDao.deleteTrxorderDetailById(id);
    }

    /**
     * 修改TrxorderDetail
     * @param trxorderDetail 要修改的TrxorderDetail
     */
    public void updateTrxorderDetail(TrxorderDetail trxorderDetail){
     	trxorderDetailDao.updateTrxorderDetail(trxorderDetail);
    }

    /**
     * 根据id获取单个TrxorderDetail对象
     */
    public TrxorderDetail getTrxorderDetailById(Long id){
    	return trxorderDetailDao.getTrxorderDetailById( id);
    }

    /**
     * 根据条件获取TrxorderDetail列表
     */
    public List<TrxorderDetail> getTrxorderDetailList(TrxorderDetail trxorderDetail){
    	List<TrxorderDetail> orderList=trxorderDetailDao.getTrxorderDetailList(trxorderDetail);
    	 List<Long> listParam = new ArrayList<Long>();//课程id集合
         for (TrxorderDetail orderDetail  : orderList) {
             listParam.add(orderDetail.getCourseId());
         }
    	 // 获取讲师的list
        Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(listParam);
        // 将讲师的list放到旧的list中
        for (TrxorderDetail orderDetail  : orderList) {
        	orderDetail.setTeacherList(map.get(orderDetail.getCourseId()));
        }
    	return orderList;
    }
    /**
     * 查询该用户购买过的课程
     * @return List<TrxorderDetail>
     */
    public List<TrxorderDetail> getTrxorderDetailListBuy(Long userId){
        return trxorderDetailDao.getTrxorderDetailListBuy(userId);
    }
    
    /**
     * 后台根据 条件查询 分页  
     *@return List<QueryTrxorderDetail>
     */
    public List<QueryTrxorderDetail> queryTrxorderDetailByOrder(QueryTrxorderDetail trxorderDetail, PageEntity page){
    	return  trxorderDetailDao.queryTrxorderDetailByOrder(trxorderDetail, page);
    }
    
    
    /**
     * 根据流水id关联用户表查询流水详情
     */
    public QueryTrxorderDetail queryQueryTrxorderDetailById(Long id){
    	return trxorderDetailDao.queryQueryTrxorderDetailById(id);
    }

    /**
     * 判断已购买直播
     */
    public int queryOrderByLive(Long userId,Long courseId){
        return trxorderDetailDao.queryOrderByLive(userId, courseId);
    }

    /**
     * 课程过期提醒
     */
    public void queryCourseOutOfDate() {
        try {
            Map<String,Object> map=new HashMap<>();
            map.put("day",3);//提前三天提醒用户课程过期
            map.put("remindStatus", MsgRemindStatus.INIT);//查询未提醒的订单
            List<QueryTrxorderDetail> trxorderDetailList=trxorderDetailDao.queryCourseOutOfDate(map);
            if(ObjectUtils.isNotNull(trxorderDetailList)){
                String ids="";
                for(QueryTrxorderDetail trxorderDetail:trxorderDetailList){
                        ids+=trxorderDetail.getId()+",";
                }
                //修改消息发送的状态
                if(StringUtils.isNotEmpty(ids)){
                    ids=ids.substring(0,ids.length()-1);
                    trxorderDetailDao.updateRemindStatus(ids);
                }
            }
        }catch (Exception e){
            System.out.println("课程过期消息发送失败！");
        }
    }

    /**
     * 直播30分钟提醒
     */
    public void queryLiveCourseOutOfDate() {
        try {
            Map<String,Object> map=new HashMap<>();
            map.put("minute",30);//提前30分钟提醒
            map.put("remindStatus", MsgRemindStatus.INIT);//查询未提醒的订单
            map.put("nowTime",new Date());//当前时间
            List<QueryTrxorderDetail> trxorderDetailList=trxorderDetailDao.queryLiveCourseOutOfDate(map);
            if(ObjectUtils.isNotNull(trxorderDetailList)){
                String ids="";
                for(QueryTrxorderDetail trxorderDetail:trxorderDetailList){
                        //msgReceiveService.sendMessage(trxorderDetail.getUserId(),trxorderDetail.getEmail(),"直播开课提醒",trxorderDetail.getMobile(),MsgType.live.toString(),true,trxorderDetail.getCourseName());
                        ids+=trxorderDetail.getId()+",";
                }
                //修改消息发送的状态
                if(StringUtils.isNotEmpty(ids)){
                    ids=ids.substring(0,ids.length()-1);
                    trxorderDetailDao.updateRemindStatus(ids);
                }
            }
        }catch (Exception e){
            System.out.println("直播课程开课提醒消息发送失败！");
        }
    }

    /**
     * 更新课程过期通知状态为已通知
     */
    public void updateRemindStatus(String ids) {
        trxorderDetailDao.updateRemindStatus(ids);
    }

    @Override
    public List<QueryTrxorderDetail> getTrxorderDetailCourseList(QueryTrxorderDetail queryTrxorderDetail) {
        return trxorderDetailDao.getTrxorderDetailCourseList(queryTrxorderDetail);
    }

}