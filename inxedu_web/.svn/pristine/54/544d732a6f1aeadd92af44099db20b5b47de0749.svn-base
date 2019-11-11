package com.inxedu.os.edu.service.impl.member;


import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.dao.member.MemberOrderOptRecordDao;
import com.inxedu.os.edu.dao.member.MemberRecordDao;
import com.inxedu.os.edu.entity.member.*;
import com.inxedu.os.edu.entity.system.SysUser;
import com.inxedu.os.edu.service.member.MemberRecordService;
import com.inxedu.os.edu.service.member.MemberTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * MemberRecord管理接口
 * User: qinggang.liu
 * Date: 2014-09-26
 */
@Service("memberRecordService")
public class MemberRecordServiceImpl implements MemberRecordService {

 	@Autowired
    private MemberRecordDao memberRecordDao;
	@Autowired
 	private MemberOrderOptRecordDao memberOrderOptRecordDao;
	@Autowired
	private MemberTypeService memberTypeService;
    /**
     * 添加MemberRecord
     * @param memberRecord 要添加的MemberRecord
     * @return id
     */
    public void addMemberRecord(MemberRecord memberRecord){
    	/*//插入会员信息表
        MemberSale memberSale =memberSaleService.getMemberSaleById(memberOrder.getMemberId());//开通的会员类型
       
        MemberRecord memberRecord=getMemberRecordByCondition(memberOrder.getUserId(),memberOrder.getMemberType());
        Calendar now = Calendar.getInstance();
        if(memberRecord!=null)//该用户开通过该类型的会员
        {
        	if(memberRecord.getEndDate().getTime()>new Date().getTime()){//之前开通的会员未过期
        		now.setTime(memberRecord.getEndDate());
        	}
        	now.add(Calendar.DAY_OF_MONTH, memberSale.getDays());
    		memberRecord.setEndDate(now.getTime());
    		updateMemberRecord(memberRecord);
        }else{//该用户未开通过该类型的会员
        	memberRecord = new MemberRecord();
            memberRecord.setUserId(memberOrder.getUserId());
			if(memberOrder.getCreateTime().equals(memberOrder.getPayTime())){
				memberRecord.setDescription("系统赠送");
			}else{
				memberRecord.setDescription("购买会员");
			}
            now.add(Calendar.DAY_OF_MONTH, memberSale.getDays());
            memberRecord.setEndDate(now.getTime());
            memberRecord.setBeginDate(new Date());
            memberRecord.setMemberType(memberSale.getType());
            memberRecord.setStatus(0L);
            memberRecordDao.addMemberRecord(memberRecord);
        }*/
		memberRecordDao.addMemberRecord(memberRecord);
    }
    
    /**
     * 修改MemberRecord
     * @param memberRecord 要修改的MemberRecord
     */
    public void updateMemberRecord(MemberRecord memberRecord){
     	memberRecordDao.updateMemberRecord(memberRecord);
    }

    /**
     * 根据id获取单个MemberRecord对象
     * @param id 要查询的id
     * @return MemberRecord
     */
    public MemberRecord getMemberRecordById(Long id){
    	return memberRecordDao.getMemberRecordById( id);
    }
    /**
     * 根据用户id、会员类型查询MemberRecord对象
     * @param userId
     * @param type
     * @return
     */
    public MemberRecord getMemberRecordByCondition(Long userId,Long type){
    	return memberRecordDao.getMemberRecordByCondition(userId,type);
    }
    /**
     * 查询用户的全部会员开通记录
     * @param userId
     * @return
     */
    public List<MemberRecordDTO> getMemberRecordByUser(Long userId,PageEntity page){
    	return memberRecordDao.getMemberRecordByUser(userId,page);
    }
    /**
     * 根据条件获取MemberRecord列表
     * @param memberRecordDTO 查询条件
     * @return List<MemberRecord>
     */
    public List<MemberRecordDTO> getMemberRecordPage(MemberRecordDTO memberRecordDTO,PageEntity page){
    	return memberRecordDao.getMemberRecordPage(memberRecordDTO,page);
    }

	/**
	 * 获得记录详情
	 * 
	 * @param id
	 * @return
	 */
	public MemberRecordDTO getMemberRecordInfo(Long id) {
		return memberRecordDao.getMemberRecordInfo(id);
	}
	/**
	 * 延期操作
	 *
	 * @param memberRecord
	 * @param sysUser
	 */
	public void updateMemberRecordEndDate(MemberRecord memberRecord,SysUser sysUser) {
		//更新操作 延期
		memberRecordDao.updateMemberRecord(memberRecord);
		//添加操作记录
		MemberOrderOptRecord memberOrderOptRecord = new MemberOrderOptRecord();
		memberOrderOptRecord.setUserId(memberRecord.getUserId());//用户id
		memberOrderOptRecord.setMemberRecordId(memberRecord.getId());//记录id
		memberOrderOptRecord.setOptuser(new Long(sysUser.getUserId()));//操作人id
		memberOrderOptRecord.setOptusername(sysUser.getLoginName());//操作者名称
		memberOrderOptRecord.setCreateTime(new Date());
		memberOrderOptRecord.setType(MemberOptType.DELAY.toString());//类型
		memberOrderOptRecord.setDescription("延期操作");
		memberOrderOptRecordDao.addMemberOrderOptRecord(memberOrderOptRecord);
		//添加用户总操作记录
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("memberType", memberRecord.getMemberType()+"--会员类型");
		map.put("type", MemberOptType.DELAY.toString()+"--延期操作");
		map.put("memberRecordId", memberRecord.getId()+"--会员记录id");

	}
	/**
	 * 会员关闭 更新状态
	 * 
	 * @param memberRecord
	 */
	public void updateMemberStatus(MemberRecord memberRecord) {
		memberRecordDao.updateMemberStatus(memberRecord);
	}
	
	/*@Override
    public void addMemberRecord(Long userId,MemberSaleDTO memberSale,String description){
    	MemberRecord memberRecord=getMemberRecordByCondition(userId,memberSale.getType());
        Calendar now = Calendar.getInstance();
        if(memberRecord!=null)//该用户开通过该类型的会员
        {
        	if(memberRecord.getEndDate().getTime()>new Date().getTime()){//之前开通的会员未过期
        		now.setTime(memberRecord.getEndDate());
        	}
        	now.add(Calendar.DAY_OF_MONTH, memberSale.getDays());
    		memberRecord.setEndDate(now.getTime());
    		updateMemberRecord(memberRecord);
        }else{//该用户未开通过该类型的会员
        	memberRecord = new MemberRecord();
            memberRecord.setUserId(userId);
            memberRecord.setDescription(description);
            now.add(Calendar.DAY_OF_MONTH, memberSale.getDays());
            memberRecord.setEndDate(now.getTime());
            memberRecord.setBeginDate(new Date());
            memberRecord.setMemberType(memberSale.getType());
            memberRecord.setStatus(0L);
            memberRecordDao.addMemberRecord(memberRecord);
        }
    }*/

	@Override
	public MemberRecordDTO getUserMember(MemberRecordDTO memberRecordDTO) {
		return memberRecordDao.getUserMember(memberRecordDTO);
	}
	@Override
	public List<MemberRecordDTO> userMemberInfo(Long userId) {
		/*查询所有类型会员*/
		List<MemberType> memberTypes = memberTypeService.getMemberTypes();

		List<MemberRecordDTO> memberRecordDTOList = new ArrayList<>();
		if (ObjectUtils.isNotNull(memberTypes)){
				/*循环查出所有种类会员是否开通*/
			for (int i=0;i<memberTypes.size();i++){
				MemberRecordDTO memberRecordDTO = new MemberRecordDTO();
				memberRecordDTO.setUserId(userId);
				memberRecordDTO.setMemberType(memberTypes.get(i).getId());
				MemberRecordDTO memberRecordDTO1 =  this.getUserMember(memberRecordDTO);
					/*如果能查出数据说明开通了该类型会员*/
				if (ObjectUtils.isNotNull(memberRecordDTO1)){
					MemberType memberType = memberTypeService.getMemberTypeById(memberRecordDTO1.getMemberType());
						/*给记录set对应的会员图片*/
					memberRecordDTO1.setImageUrl(memberType.getImageUrl());
					memberRecordDTOList.add(memberRecordDTO1);
				}
			}
		}
		return memberRecordDTOList;
	}
	@Override
	public MemberRecordDTO getUserNowDateMember(MemberRecordDTO memberRecordDTO) {
		return memberRecordDao.getUserNowDateMember(memberRecordDTO);
	}

	@Override
	public List<MemberRecordDTO> userNowMemberInfo(Long userId) {
		/*查询所有类型会员*/
		List<MemberType> memberTypes = memberTypeService.getMemberTypes();

		List<MemberRecordDTO> memberRecordDTOList = new ArrayList<>();
		if (ObjectUtils.isNotNull(memberTypes)){
				/*循环查出所有种类会员是否开通*/
			for (int i=0;i<memberTypes.size();i++){
				MemberRecordDTO memberRecordDTO = new MemberRecordDTO();
				memberRecordDTO.setUserId(userId);
				memberRecordDTO.setMemberType(memberTypes.get(i).getId());
				MemberRecordDTO memberRecordDTO1 =  this.getUserNowDateMember(memberRecordDTO);
				/*如果能查出数据说明开通了该类型会员*/
				if (ObjectUtils.isNotNull(memberRecordDTO1)){
					MemberType memberType = memberTypeService.getMemberTypeById(memberRecordDTO1.getMemberType());
						/*给记录set对应的会员图片*/
					memberRecordDTO1.setImageUrl(memberType.getImageUrl());
					memberRecordDTOList.add(memberRecordDTO1);
				}
			}
		}
		return memberRecordDTOList;
	}
}