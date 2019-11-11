package com.inxedu.os.edu.dao.impl.order;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.edu.dao.order.TrxorderDetailDao;
import com.inxedu.os.edu.entity.order.QueryTrxorderDetail;
import com.inxedu.os.edu.entity.order.TrxorderDetail;
import com.inxedu.os.common.entity.PageEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * TrxorderDetail
 * User: qinggang.liu voo@163.com
 * Date: 2014-05-27
 */
 @Repository("trxorderDetailDao")
public class TrxorderDetailDaoImpl extends GenericDaoImpl implements TrxorderDetailDao {

    public Long addTrxorderDetail(TrxorderDetail trxorderDetail) {
        return this.insert("TrxorderDetailMapper.createTrxorderDetail",trxorderDetail);
    }
    /**
     * 批量添加TrxorderDetail
     * @param trxorderDetailList 要添加的TrxorderDetail
     * @return id
     */
    public void addBatchTrxorderDetail(List<TrxorderDetail> trxorderDetailList){
        this.insert("TrxorderDetailMapper.addBatchTrxorderDetail",trxorderDetailList);
    }

    public void deleteTrxorderDetailById(Long id){
        this.delete("TrxorderDetailMapper.deleteTrxorderDetailById", id);
    }

    public void updateTrxorderDetail(TrxorderDetail trxorderDetail) {
        this.update("TrxorderDetailMapper.updateTrxorderDetail", trxorderDetail);
    }

    public TrxorderDetail getTrxorderDetailById(Long id) {
        return this.selectOne("TrxorderDetailMapper.getTrxorderDetailById",id);
    }

    public List<TrxorderDetail> getTrxorderDetailList(TrxorderDetail trxorderDetail) {
        return this.selectList("TrxorderDetailMapper.getTrxorderDetailList", trxorderDetail);
    }
    /**
     * 查询该用户购买过的课程
     * @param userId 查询条件
     * @return List<TrxorderDetail>
     */
    public List<TrxorderDetail> getTrxorderDetailListBuy(Long userId){
        return this.selectList("TrxorderDetailMapper.getTrxorderDetailListBuy",userId);
    }
    
    /**
     * 更新流水状态为成功，网银回调用
     * @param trxorderDetail
     */
    public Long updateTrxorderDetailStatusSuccess(TrxorderDetail trxorderDetail){
        return this.update("TrxorderDetailMapper.updateTrxorderDetailStatusSuccess", trxorderDetail);
    }
    
    
    /**
     *根据 订单条件查询 流水分页
     *@param trxorderDetail,page
     *@return List<TrxorderDetail>
     *@Date 2014-09-28
     */
    public List<QueryTrxorderDetail> queryTrxorderDetailByOrder(QueryTrxorderDetail trxorderDetail, PageEntity page){
    	return this.queryForListPage("TrxorderDetailMapper.getTrxorderDetailListByCondition", trxorderDetail, page);
    }
    
    
    /**
     * 根据流水id关联用户表查询流水详情
     * @param id
     * return QueryTrxorderDetail
     * @Date 2014-09-28
     */
    public QueryTrxorderDetail queryQueryTrxorderDetailById(Long id){
    	
    	return this.selectOne("TrxorderDetailMapper.getTrxorderDetailInfoById", id);
    }

    @Override
    public List<QueryTrxorderDetail> getTimeOverOrder() {
        return this.selectList("TrxorderDetailMapper.getTimeOverOrder",null);
    }

    /**
     * 判断已购买直播
     * @param userId
     * @param courseId
     * @return
     */
    public int queryOrderByLive(Long userId,Long courseId){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("courseId", courseId);
        return this.selectOne("TrxorderDetailMapper.queryOrderByLive",map );
    }

    /**
     * 查询即将过期的课程
     *
     * @param map
     * @return
     */
    public List<QueryTrxorderDetail> queryCourseOutOfDate(Map<String, Object> map) {
        return this.selectList("TrxorderDetailMapper.queryCourseOutOfDate",map);
    }

    /**
     * 查询距直播开始差30分钟的直播
     *
     * @param map
     * @return
     */
    public List<QueryTrxorderDetail> queryLiveCourseOutOfDate(Map<String, Object> map) {
        return this.selectList("TrxorderDetailMapper.queryLiveCourseOutOfDate",map);
    }

    /**
     * 更新课程过期通知状态为已通知
     *
     * @param ids
     */
    public void updateRemindStatus(String ids) {
        String[] id = ids.replaceAll(",", " ").trim().split(" ");
        this.update("TrxorderDetailMapper.updateRemindStatus",id);
    }

    @Override
    public List<QueryTrxorderDetail> getTrxorderDetailCourseList(QueryTrxorderDetail queryTrxorderDetail) {
        return this.selectList("TrxorderDetailMapper.getTrxorderDetailCourseList",queryTrxorderDetail);
    }

}
