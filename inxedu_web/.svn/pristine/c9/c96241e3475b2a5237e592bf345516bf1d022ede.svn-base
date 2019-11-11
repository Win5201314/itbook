package com.inxedu.os.edu.service.member;




import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.member.MemberOrder;
import com.inxedu.os.edu.entity.member.MemberOrderDTO;
import com.inxedu.os.edu.entity.member.MemberOrderReqData;
import com.inxedu.os.edu.entity.member.QueryMemberOrder;

import java.util.List;
import java.util.Map;

/**
 * MemberOrder管理接口
 * User: qinggang.liu
 * Date: 2014-09-26
 */
public interface MemberOrderService {
    /**
     * 添加MemberOrder
     *
     * @param sourceMap 要添加的MemberOrder
     * @return id
     */
    Map<String, Object> addMemberOrder(Map<String, String> sourceMap) throws Exception;

    /**
     * 添加MemberOrder
     *
     * @param memberOrder 要添加的MemberOrder
     * @return id
     */
    Long addMemberOrder(MemberOrder memberOrder);

    /**
     * 根据id删除一个MemberOrder
     *
     * @param id 要删除的id
     */
    void deleteMemberOrderById(Long id);

    /**
     * 修改MemberOrder
     *
     * @param memberOrder 要修改的MemberOrder
     */
    void updateMemberOrder(MemberOrder memberOrder);

    /**
     * 根据id获取单个MemberOrder对象
     *
     * @param id 要查询的id
     * @return MemberOrder
     */
    MemberOrder getMemberOrderById(Long id);

    /**
     * 根据id获取单个MemberOrderDTO对象
     *
     * @param id 要查询的id
     * @return MemberOrderDTO
     */
    MemberOrderDTO getMemberOrderDTOById(Long id);

    /**
     * 根据requestId获取单个MemberOrder对象
     *
     * @param
     * @return
     */
    MemberOrder getMemberOrderByRequestId(String requestId);

    /**
     * 根据条件获取MemberOrder列表
     *
     * @param memberOrder 查询条件
     * @return List<MemberOrder>
     */
    List<MemberOrder> getMemberOrderList(MemberOrder memberOrder);

    /**
     * 会员订单列表
     *
     * @param queryMemberOrder
     * @param page
     * @return
     */
    List<MemberOrderDTO> getMemberOrderPage(QueryMemberOrder queryMemberOrder, PageEntity page);

    /**
     * 会员订单
     *
     * @param memberReqData
     * @return
     */
    Map<String, String> updateCompleteMemberOrder(MemberOrderReqData memberReqData) throws Exception;

    /**
     * 免费赠送会员
     * @param sourceMap
     * @return
     * @throws Exception
     */
    Map<String, Object> addFreeMemberOrder(Map<String, String> sourceMap) throws Exception;
}