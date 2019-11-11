<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<script type="text/javascript" src="${ctx}/kindeditor/kindeditor-all.js"></script>
<menu class="col-20 fl uMenu">
	<section class="of u-left-top">
		<div class="u-face-pic">
			<img src="${ctx }/static/inxweb/img/avatar-boy.gif" alt="" class="userImgPhoto">
			<a href="javascript:void (0)" onclick="dialog('修改头像','',7)"title="" class="c-fff">修改头像</a>
		</div>
		<div class="hLh20 txtOf mt10">
			<span class="c-master f-fM fsize16">Hi~</span>
		</div>
		<h4 class="hLh30 txtOf c-master mt5">
			<a href="${ctx}/uc/initUpdateUser/0" class="fsize14 c-333 userNameClass"><!-- 用户名 --></a>
		</h4>
<c:if test="${serviceSwitch.member=='ON'}">
		<div class="mt5 of">

				<span class="u-tit-vip" style="display: none">
					<em class="vip-pic">
						<img src="${ctx}/static/inxweb/img/vip.png">
					</em>
					<tt class="vam c-fff fsize12 f-fM">已开通vip</tt>
				</span>
				<a href="${ctx}/uc/associator" class="u-tit-vip-gq" style="display: none" title="立即开通vip">
					<em class="vip-pic">
						<img src="${ctx}/static/inxweb/img/vip.png">
					</em>
					<tt class="vam c-fff fsize12 f-fM">未开通vip</tt>
				</a>

		</div>
</c:if>
		<div class="clear"></div>
	</section>
	<div>
		<ul id="navList" class="uM-list">
			<li>
				<a href="${ctx}/uc/index" title="我的课程">
					<em class="icon24 uM-kcb-ico uM-list-ico mr5">&nbsp;</em><tt class="vam">我的课程</tt>
				</a>
			</li>
			<li>
				<a href="${ctx}/uc/courseNote" title="我的笔记">
					<em class="icon24 uM-wdbj-ico uM-list-ico mr5">&nbsp;</em><tt class="vam">我的笔记</tt>
				</a>
			</li>
			<li>
				<a href="${ctx}/uc/myFavorites" title="课程收藏">
					<em class="icon24 uM-kcsc-ico uM-list-ico mr5">&nbsp;</em><tt class="vam">课程收藏</tt>
				</a>
			</li>

			<c:if test="${serviceSwitch.coupon=='ON'}">
			<li>
				<a href="${ctx}/uc/myCouPon" title="优惠劵">
					<em class="icon24 uM-yhj-ico uM-list-ico mr5">&nbsp;</em><tt class="vam">优惠劵</tt>
				</a>
			</li>
			</c:if>
			<c:if test="${serviceSwitch.member=='ON'}">
				<li>
					<a href="${ctx}/uc/associator" title="我的会员">
						<em class="icon24 uM-ddzx-ico uM-member-ico mr5">&nbsp;</em><tt class="vam">我的会员</tt>
					</a>
				</li>
			</c:if>
			<li>
				<a href="${ctx}/uc/order/myOrderList/ALL" title="订单中心">
					<em class="icon24 uM-ddzx-ico uM-list-ico mr5">&nbsp;</em><tt class="vam">订单中心</tt>
				</a>
			</li>
			<c:if test="${serviceSwitch.account=='ON'}">
				<li>
					<a href="/uc/myAccount" title="我的账户">
						<em class="icon24 uM-wdzh-ico uM-list-ico mr5">&nbsp;</em><tt class="vam">我的账户</tt>
					</a>
				</li>
			</c:if>
			<c:if test="${serviceSwitch.invite=='ON'}">
				<li>
					<a href="/uc/myInvite" title="邀请好友">
						<em class="icon24 uM-yqhy-ico uM-list-ico mr5">&nbsp;</em><tt class="vam">邀请好友</tt>
					</a>
				</li>
			</c:if>
			<li>
				<a href="${ctx}/uc/initUpdateUser/0" title="个人资料">
					<em class="icon24 uM-grzl-ico uM-list-ico mr5">&nbsp;</em><tt class="vam">个人资料</tt>
				</a>
			</li>
			<li>
				<a href="${ctx}/uc/initUpdateUser/1" title="密码设置">
					<em class="icon24 uM-mmsz-ico uM-list-ico mr5">&nbsp;</em><tt class="vam">密码设置</tt>
				</a>
			</li>
			<li>
				<a href="/uc/letter" title="消息中心">
					<em class="icon24 uM-xxzx-ico uM-list-ico mr5">&nbsp;</em><tt class="vam">消息中心</tt>
				</a>
			</li>
		</ul>
	</div>
</menu>
<!-- /公共左侧菜单 结束 -->