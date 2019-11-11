package com.inxedu.os.edu.entity.order;

import com.inxedu.os.common.util.DateUtils;
import com.inxedu.os.common.util.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;


/**
 * 流水查询条件类
 * @author www.inxedu.com
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class QueryTrxorderDetail implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 8823831006166279064L;

	private Long id;
	private Long userId;//用户id
	private Long courseId;//相关联的课程id（前台快照）
	private Long trxorderId;//交易订单ID
	private Long membertype;//会员观看类型（前台快照）
	private int losetype;//有效期类型（前台快照）
	private Date loseAbsTime;//订单过期时间段（前台快照）
	private String orderNo;//订单号
	private String keyWord;//关键字
	private String loseTime;//订单过期时间点（前台快照）
	private Date authTime;//课程过期时间
	private Date createTime;//下单时间
	private Date payTime;//支付成功时间
	private java.math.BigDecimal sourcePrice;//原价格（前台快照）
	private java.math.BigDecimal currentPrice;//销售价格（前台快照）
	private String courseName;//课程名称（前台goods快照）
	private String trxStatus;//订单状态（前台goods快照）
	private String authStatus;//课程状态（INIT，SUCCESS，REFUND，CLOSED，LOSED,Delete）
	private String courseImg;//课程图片
	private String requestId;//订单请求号
	private String description;//描述
	private Long version=1L;//乐观锁版本号
	private Date lastUpdateTime;//最后更新时间
	private String userName;
	private String email;
	private String mobile;
	private String userPicImg;//用户头像
	private String createTimeFormat;//格式化显示 下单时间
	private String sellType;//课程类型：COURSE(课程) LIVE(直播)

	private Date startAuthTime;
	private Date endAuthTime;
	private Date startCreateTime;
	private Date endCreateTime;
	private Date startPayTime;
	private Date endPayTime;
	private int limitNum;//查询限制条数
	private String displayName;//前台展示用户名
	private String trxorderType;//订单类型 COURSE课程 MEMBER会员
	public void setUserName(String userName){
		this.userName = userName;
		if(StringUtils.isNotEmpty(userName)){
			this.displayName = userName;
		}
	}
	public void setEmail(String email){
		this.email = email;
		if (StringUtils.isEmpty(userName)){
			if(StringUtils.isNotEmpty(email)){
				this.displayName = email;
			}
		}
	}
	public void setMobile(String mobile){
		this.mobile = mobile;
		if (StringUtils.isEmpty(userName)&&StringUtils.isEmpty(email)){
			if(StringUtils.isNotEmpty(mobile)){
				this.displayName = mobile;
			}
		}
	}

	public void setCreateTime(Date date){
		this.createTime=date;

		this.createTimeFormat = getCreateTimeFormat(date);
	}


	public String getCreateTimeFormat(Date createTime){
		if (createTime!=null) {
			Date newDate = new Date();
			long second = (newDate.getTime() - createTime.getTime()) / 1000L;
			if (second <= 60L)
				return second + "秒前";
			if ((60L < second) && (second <= 3600L)) {
				second /= 60L;
				return second + "分钟前";
			}
			if ((3600L < second) && (second <= 86400L)) {
				second = second / 60L / 60L;
				return second + "小时前";
			}
			if ((86400L < second) && (second <= 864000L)) {
				String formatDate = DateUtils.formatDate(createTime, "HH:mm");
				second = second / 60L / 60L / 24L;

				return second + "天前";
			}
			return DateUtils.formatDate(createTime, "yyyy-MM-dd HH:mm");
		}

		return "";
	}
}
