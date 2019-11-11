package com.inxedu.os.edu.service.impl.member;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.member.MemberSaleDao;
import com.inxedu.os.edu.entity.member.MemberSale;
import com.inxedu.os.edu.entity.member.MemberSaleDTO;
import com.inxedu.os.edu.entity.member.QueryMemberSale;
import com.inxedu.os.edu.service.member.MemberSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MemberSale管理接口
 * User: qinggang.liu
 * Date: 2014-09-26
 */
@Service("memberSaleService")
public class MemberSaleServiceImpl implements MemberSaleService {

 	@Autowired
    private MemberSaleDao memberSaleDao;
 	/**
     * 修改MemberSale(价格，开通天数)
     * @param memberSale 要修改的MemberSale
     */
    public void updateMemberSale(MemberSale memberSale){
     	memberSaleDao.updateMemberSale(memberSale);
    }

    /**
     * 根据id获取单个MemberSale对象
     * @param id 要查询的id
     * @return MemberSale
     */
    public MemberSale getMemberSaleById(Long id){
    	return memberSaleDao.getMemberSaleById( id);
    }

    /**
     * 添加会员商品
     * @param memberSale
     */
    public void addMemberSale(MemberSale memberSale){
    	memberSaleDao.addMemberSale(memberSale);
    }
    /**
     * 删除会员商品
     * @param ids
     */
    public void delMemberSale(String ids){
    	memberSaleDao.delMemberSale(ids);
    }
    /**
     * 获取MemberSale分页
     * @param queryMemberSale
     * @param page
     * @return
     */
    public List<MemberSaleDTO> getMemberSalePage(QueryMemberSale queryMemberSale, PageEntity page){
    	return memberSaleDao.getMemberSalePage(queryMemberSale,page);
    }
    /**
     * type获取MemberSale集合
     * @param type
     * @return
     */
    public List<MemberSale> getMemberSaleListByType(Long type){
    	return memberSaleDao.getMemberSaleListByType(type);
    }
    /**
     * 会员服务级联删除会员商品
     * @param id
     */
	public void delMemberSaleByType(Long id){
		memberSaleDao.delMemberSaleByType(id);
	}
    /**
     * 查询会员商品所有
     */
    public List<MemberSale> getMemberSaleAll(){
        return memberSaleDao.getMemberSaleAll();
    }

	@Override
	public List<MemberSaleDTO> queryMemberSaleByIds(String ids) {
		return memberSaleDao.queryMemberSaleByIds(ids);
	}

	@Override
	public List<MemberSaleDTO> queryMemberSaleByIdList(List<Long> ids) {
		return memberSaleDao.queryMemberSaleByIdList(ids);
	}
	
	@Override
	public Map<String,MemberSaleDTO> queryMemberSaleMapByIdList(List<Long> ids){
		Map<String,MemberSaleDTO> memberMap = null;
		List<MemberSaleDTO> memberList = this.queryMemberSaleByIdList(ids);
		if(memberList!=null && memberList.size()>0){
			memberMap = new HashMap<>();
			for(MemberSaleDTO ms : memberList){
				memberMap.put(String.valueOf(ms.getId()), ms);
			}
		}
		return memberMap;
	}

	@Override
	public MemberSale getMemberSale(MemberSaleDTO memberSaleDTO) {
		return memberSaleDao.getMemberSale(memberSaleDTO);
	}
}