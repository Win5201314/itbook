package com.inxedu.os.edu.service.member;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.member.MemberSale;
import com.inxedu.os.edu.entity.member.MemberSaleDTO;
import com.inxedu.os.edu.entity.member.QueryMemberSale;

import java.util.List;
import java.util.Map;



/**
 * MemberSale管理接口
 * User: qinggang.liu
 * Date: 2014-09-26
 */
public interface MemberSaleService {

    /**
     * 修改MemberSale(价格，开通天数)
     *
     * @param memberSale 要修改的MemberSale
     */
    void updateMemberSale(MemberSale memberSale);

    /**
     * 根据id获取单个MemberSale对象
     *
     * @param id 要查询的id
     * @return MemberSale
     */
    MemberSale getMemberSaleById(Long id);

    /**
     * 添加会员商品
     *
     * @param memberSale
     */
    void addMemberSale(MemberSale memberSale);

    /**
     * 删除会员商品
     *
     * @param ids
     */
    void delMemberSale(String ids);

    /**
     * 获取MemberSale分页
     *
     * @param queryMemberSale
     * @param page
     * @return
     */
    List<MemberSaleDTO> getMemberSalePage(QueryMemberSale queryMemberSale, PageEntity page);

    /**
     * type获取MemberSale集合
     *
     * @param type
     * @return
     */
    List<MemberSale> getMemberSaleListByType(Long type);

    /**
     * 会员服务级联删除会员商品
     *
     * @param id
     */
    void delMemberSaleByType(Long id);

    /**
     * 查询会员商品所有
     */
    List<MemberSale> getMemberSaleAll();
    
    /**
     * 通过ID串，查询会员端口列表
     * @param ids
     * @return
     */
    List<MemberSaleDTO> queryMemberSaleByIds(String ids);
    
    /**
     * 通过会员商品ID列表，查询会员商品数据
     * @param ids
     * @return
     */
    List<MemberSaleDTO> queryMemberSaleByIdList(List<Long> ids);

    /**
     * 通过会员商品ID列表，查询会员商品Map
     * @param ids
     * @return
     */
    Map<String, MemberSaleDTO> queryMemberSaleMapByIdList(List<Long> ids);
    /**
     * 根据条件获取单个MemberSale对象
     * 时间和类型
     * @return MemberSale
     */
    MemberSale getMemberSale(MemberSaleDTO memberSaleDTO);
}