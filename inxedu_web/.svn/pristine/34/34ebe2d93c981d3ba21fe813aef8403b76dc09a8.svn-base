package com.inxedu.os.edu.service.order;


import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.order.QueryTrxorderDetail;
import com.inxedu.os.edu.entity.order.TrxorderDetail;

import java.util.List;

/**
 * TrxorderDetail管理接口
 * @author www.inxedu.com
 */
public interface TrxorderDetailService {

    /**
     * 添加TrxorderDetail
     * @param trxorderDetail 要添加的TrxorderDetail
     * @return id
     */
    Long addTrxorderDetail(TrxorderDetail trxorderDetail);

    /**
     * 批量添加TrxorderDetail
     * @return id
     */
    void addBatchTrxorderDetail(List<TrxorderDetail> trxorderDetailList);

    /**
     * 根据id删除一个TrxorderDetail
     * @param id 要删除的id
     */
    void deleteTrxorderDetailById(Long id);

    /**
     * 修改TrxorderDetail
     * @param trxorderDetail 要修改的TrxorderDetail
     */
    void updateTrxorderDetail(TrxorderDetail trxorderDetail);

    /**
     * 根据id获取单个TrxorderDetail对象
     * @param id 要查询的id
     * @return TrxorderDetail
     */
    TrxorderDetail getTrxorderDetailById(Long id);

    /**
     * 查询该用户购买过的课程
     * @return List<TrxorderDetail>
     */
    List<TrxorderDetail> getTrxorderDetailListBuy(Long userId);

    /**
     * 根据条件获取TrxorderDetail列表
     * @param trxorderDetail 查询条件
     * @return List<TrxorderDetail>
     */
    List<TrxorderDetail> getTrxorderDetailList(TrxorderDetail trxorderDetail);
    	
    /**
     * 根据条件获取TrxorderDetail 流水列表分页
     * @param trxorderDetail 查询条件
     * @return List<QueryTrxorderDetail>
     */
    List<QueryTrxorderDetail> queryTrxorderDetailByOrder(QueryTrxorderDetail trxorderDetail, PageEntity page);

    /**
     * 根据流水id关联用户表查询流水详情
     * @param id
     * return QueryTrxorderDetail
     * @Date  2014-09-28
     */
    QueryTrxorderDetail queryQueryTrxorderDetailById(Long id);

    /**
     * 判断已购买直播
     * @param userId
     * @param courseId
     * @return
     */
    int queryOrderByLive(Long userId, Long courseId);

    /**
     * 课程过期提醒
     */
    void queryCourseOutOfDate();

    /**
     * 直播30分钟提醒
     */
    void queryLiveCourseOutOfDate();

    /**
     * 更新课程过期通知状态为已通知
     * @param ids
     */
    void updateRemindStatus(String ids);

    /**
     * 根据条件获取QueryTrxorderDetail列表
     * @param queryTrxorderDetail 查询条件
     * @return List<QueryTrxorderDetail>
     */
    List<QueryTrxorderDetail> getTrxorderDetailCourseList(QueryTrxorderDetail queryTrxorderDetail);
}