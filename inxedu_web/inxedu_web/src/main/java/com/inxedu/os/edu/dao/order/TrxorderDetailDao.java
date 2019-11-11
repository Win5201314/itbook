package com.inxedu.os.edu.dao.order;

import com.inxedu.os.edu.entity.order.QueryTrxorderDetail;
import com.inxedu.os.edu.entity.order.TrxorderDetail;
import com.inxedu.os.common.entity.PageEntity;

import java.util.List;
import java.util.Map;

/**
 * @author www.inxedu.com
 */
public interface TrxorderDetailDao{

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
     * 根据条件获取TrxorderDetail列表
     * @param trxorderDetail 查询条件
     * @return List<TrxorderDetail>
     */
    List<TrxorderDetail> getTrxorderDetailList(TrxorderDetail trxorderDetail);

    /**
     * 查询该用户购买过的课程
     * @return List<TrxorderDetail>
     */
    List<TrxorderDetail> getTrxorderDetailListBuy(Long userId);
    
    /**
     * 更新流水状态为成功，网银回调用
     * @param trxorderDetail
     */
    Long updateTrxorderDetailStatusSuccess(TrxorderDetail trxorderDetail);
    
    /**
     *根据 订单条件查询分页
     *@param trxorderDetail
     *@return List<QueryTrxorderDetail>
     */
    List<QueryTrxorderDetail> queryTrxorderDetailByOrder(QueryTrxorderDetail trxorderDetail, PageEntity page);
    
    /**
     * 根据流水id关联用户表查询流水详情
     * @param id
     * return QueryTrxorderDetail
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
     * 查询即将过期的课程
     * @param map
     * @return
     */
    List<QueryTrxorderDetail> queryCourseOutOfDate(Map<String, Object> map);

    /**
     * 查询距直播开始差30分钟的直播
     * @param map
     * @return
     */
    List<QueryTrxorderDetail> queryLiveCourseOutOfDate(Map<String, Object> map);

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
    /**
     * 查询明天有课程过期的用户信息
     */
    List<QueryTrxorderDetail> getTimeOverOrder();
}