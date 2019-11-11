package com.inxedu.os.edu.dao.impl.member;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.member.MemberSaleDao;
import com.inxedu.os.edu.entity.member.MemberSale;
import com.inxedu.os.edu.entity.member.MemberSaleDTO;
import com.inxedu.os.edu.entity.member.QueryMemberSale;
import org.springframework.stereotype.Repository;

import java.util.List;



/**
 *
 * MemberSale
 * User: qinggang.liu bis@foxmail.com
 * Date: 2014-09-26
 */
 @Repository("memberSaleDao")
public class MemberSaleDaoImpl extends GenericDaoImpl implements MemberSaleDao {


    public void updateMemberSale(MemberSale memberSale) {
        this.update("MemberSaleMapper.updateMemberSale",memberSale);
    }

    public MemberSale getMemberSaleById(Long id) {
        return this.selectOne("MemberSaleMapper.getMemberSaleById",id);
    }

    /**
     * 添加会员商品
     * @param memberSale
     */
    public void addMemberSale(MemberSale memberSale){
    	this.insert("MemberSaleMapper.addMemberSale",memberSale);
    }
    /**
     * 删除会员商品
     * @param ids
     */
    public void delMemberSale(String ids){
    	this.update("MemberSaleMapper.delMemberSale", ids);
    }
    /**
     * 获取MemberSale分页
     * @param queryMemberSale
     * @param page
     * @return
     */
    public List<MemberSaleDTO> getMemberSalePage(QueryMemberSale queryMemberSale, PageEntity page){
    	return this.queryForListPage("MemberSaleMapper.getMemberSalePage", queryMemberSale, page);
    }
    /**
     * type获取MemberSale集合
     * @param type
     * @return
     */
    public List<MemberSale> getMemberSaleListByType(Long type){
    	return this.selectList("MemberSaleMapper.getMemberSaleByType", type);
    }
    /**
     * 会员服务级联删除会员商品
     * @param id
     */
	public void delMemberSaleByType(Long id){
		this.update("MemberSaleMapper.delMemberSaleByType", id);
	}
    /**
     * 查询会员商品所有
     */
    public List<MemberSale> getMemberSaleAll(){
        return this.selectList("MemberSaleMapper.getMemberSaleAll",null);
    }

	@Override
	public List<MemberSaleDTO> queryMemberSaleByIds(String ids) {
		return this.selectList("MemberSaleMapper.queryMemberSaleByIds", ids);
	}

	@Override
	public List<MemberSaleDTO> queryMemberSaleByIdList(List<Long> ids) {
		return this.selectList("MemberSaleMapper.queryMemberSaleByIdList", ids);
	}

    @Override
    public MemberSale getMemberSale(MemberSaleDTO memberSaleDTO) {
        return this.selectOne("MemberSaleMapper.getMemberSale",memberSaleDTO);
    }
}
