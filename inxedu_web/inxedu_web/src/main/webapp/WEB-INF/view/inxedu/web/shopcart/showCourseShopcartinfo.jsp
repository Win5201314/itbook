<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<dt>
<ul class="c-o-head clearfix of">
	<li class="c-head-li-16 tac"><span>课程</span></li>
	<li class="c-head-li-26 tac"><span>标题</span></li>
	<li class="c-head-li-4 tac show-teach-li"><span>讲师</span></li>
	<li class="c-head-li-4 tac"><span>价格</span></li>
	<li class="c-head-li-4 tac"><span>操作</span></li>
</ul>
</dt>
<%--<dd class="zf-tc-list">
	<ul class="c-o-tbody clearfix of">
		<li class="c-head-li-16">
			<div class="c-o-t-img">
				<img class="img-responsive" src="${ctx}/images/upload/course/20160630/1467281442192.jpg">
			</div>
		</li>
		<li class="c-head-li-36">
			<div class="c-head-title">
				<h6 class="unFw txtOf hLh20">
					<a class="c-666 fsize14" href="javascript:void(0)">会计从业资格考试：初级会计电算化</a>
				</h6>
				<div class="u-order-desc">
					<p class="c-999"> 此学科提供必要的计算机基础知识，是现代化会计人员办公必不可少的技能。通过该课程的学习，可以全面提升专业技能，让你轻松通过考试。</p>
				</div>
				<div class="u-order-m-jg hLh20 mt5">
					<span class="c-red">￥450</span>
				</div>
			</div>
		</li>
		<li class="c-head-li-2">
			<div class="c-t-wz tac">
				<span class="line-vam">&nbsp;</span>
				<span class="c-999 vam">李立 / 李小梅 / 陈晓薇 / 李诚 / </span>
			</div>
		</li>
		<li class="c-head-li-3">
			<div class="c-t-wz tac">
				<span class="line-vam">&nbsp;</span>
				<span class="c-666 vam">￥0.01</span>
			</div>
		</li>
		<li class="c-head-li-4">
			<div class="c-t-wz tac">
				<span class="line-vam">&nbsp;</span>
				<a class="c-orange vam" href="javascript:deleteid(56,42,1)">删除</a>
			</div>
		</li>
	</ul>
</dd>
<dd class="zf-tc-list">
	<ul class="c-o-tbody-ts">
		<li>
			<div class="fsize12 f-fM tar c-o-ts-zj">
				<span class="vam">
					<tt class="vam f-fM c-orange">此</tt>
					<tt class="vam f-fM c-orange">3</tt>
					<tt class="vam f-fM c-orange">门课程，满足</tt>
					<tt class="vam f-fM c-master">某某某套餐</tt>
					<tt class="vam f-fM c-orange">,可享受套餐价。</tt>
				</span>
				<span>
					<tt class="vam f-fM c-orange">共计</tt>
					<tt class="vam f-fM c-master">￥450</tt>
					<tt class="vam f-fM c-orange">元，已优惠</tt>
					<tt class="vam f-fM c-master">￥100</tt>
					<tt class="vam f-fM c-orange">元</tt>
				</span>
			</div>
		</li>
	</ul>
</dd>--%>
<c:set var="totalMoney" value="0"></c:set>
<c:choose>
	<c:when test="${not empty  shopcartList}">
		<c:forEach items="${shopcartList }" var="sc">

			<dd>
				<ul class="c-o-tbody clearfix of">
					<li class="c-head-li-16"><div class="c-o-t-img">
						<c:choose>
							<c:when test="${not empty sc.course.logo}">
								<img src="${staticServer}${sc.course.logo}" class="img-responsive">
							</c:when>
							<c:otherwise>
								<img src="/static/inxweb/img/coures.gif" class="img-responsive">
							</c:otherwise>
						</c:choose>
					</div></li>
					<li class="c-head-li-26">
						<div class="c-head-title">
							<h6 class="unFw txtOf hLh20"><a href="javascript:void(0)" class="c-666 fsize14">${sc.course.courseName}</a></h6>
							<div class="u-order-desc">
								<p class="c-999"> ${sc.course.title}</p>
							</div>
							<div class="u-order-m-jg hLh20 mt5">
								<span class="c-red">￥${sc.course.currentPrice }</span>
							</div>
						</div>
					</li>
					<li class="c-head-li-4 show-teach-li">
						<div class="c-t-wz tac">
							<span class="c-999 vam">
								<c:forEach items="${sc.course.teacherList }" var="teacher">
									${teacher.name} /
								</c:forEach>
							</span>
						</div>
					</li>
					<li class="c-head-li-4">
						<div class="c-t-wz tac">
							<span class="c-666 vam">￥${sc.course.currentPrice }</span>
						</div>
					</li>
					<li class="c-head-li-4">
						<div class="c-t-wz tac">
							<a href="javascript:deleteid(${sc.id},${sc.goodsid },${sc.type})" class="c-orange vam">删除</a>
						</div>
						<c:set var="totalMoney" value="${totalMoney+sc.course.currentPrice }"></c:set>
					</li>
				</ul>
			</dd>

			<%--<c:if test="${sc.course.sellType=='PACKAGE'}">
				<c:forEach items="${sc.course.courseList}" var="course">
				<dd class="zf-tc-list">
					<ul class="c-o-tbody clearfix of">
						<li class="c-head-li-16">
							<div class="c-o-t-img">
								<img class="img-responsive" src="${ctx}${course.logo}">
							</div>
						</li>
						<li class="c-head-li-36">
							<div class="c-head-title">
								<h6 class="unFw txtOf hLh20">
									<a class="c-666 fsize14" href="">${course.courseName}</a>
								</h6>
								<div class="u-order-desc">
									<p class="c-999"> ${course.title}</p>
								</div>
								<div class="u-order-m-jg hLh20 mt5">
									<span class="c-red">￥${course.currentPrice}</span>
								</div>
							</div>
						</li>
						<li class="c-head-li-2">
							<div class="c-t-wz tac">
								<span class="line-vam">&nbsp;</span>
								<span class="c-999 vam">
									<c:forEach items="${course.teacherList}" var="teacher">
									${teacher}/
									</c:forEach>
								</span>
							</div>
						</li>
						<li class="c-head-li-3">
							<div class="c-t-wz tac">
								<span class="line-vam">&nbsp;</span>
								<span class="c-666 vam">￥${course.currentPrice}</span>
							</div>
						</li>


					</ul>
				</dd>
				</c:forEach>

				<dd class="zf-tc-list">
					<ul class="c-o-tbody-ts">
						<li>
							<div class="fsize12 f-fM tar c-o-ts-zj">
				<span class="vam">
					<tt class="vam f-fM c-orange">此</tt>
					<tt class="vam f-fM c-orange">${fn:length(sc.course.courseList)}</tt>
					<tt class="vam f-fM c-orange">门课程，满足</tt>
					<tt class="vam f-fM c-master">${sc.course.courseName}</tt>
					<tt class="vam f-fM c-orange">,可享受套餐价。</tt>
				</span>
								<span>
					<tt class="vam f-fM c-orange">共计</tt>
					<tt class="vam f-fM c-master">￥${sc.course.currentPrice }</tt>
					<tt class="vam f-fM c-orange">元，已优惠</tt>
					<tt class="vam f-fM c-master">￥100</tt>
					<tt class="vam f-fM c-orange">元</tt>
				</span>
							</div>
						</li>
						<li class="c-head-li-4">
							<div class="c-t-wz tac">
								<span class="line-vam">&nbsp;</span>
								<a class="c-orange vam" href="javascript:deleteid(${sc.id},${sc.goodsid },${sc.type})">删除</a>
							</div>
						</li>
					</ul>
					<c:set var="totalMoney" value="${totalMoney+sc.course.currentPrice }"></c:set>
				</dd>
			</c:if>--%>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<dd>
			<section class="no-data-wrap"><em class="icon30 no-data-ico">&nbsp;</em><span class="c-666 fsize14 ml10 vam">购物车为空,请先选购<a target="" href="${ctx}/front/showcoulist" title="课程" class="c-master ml5">课程</a>...</span></section>
		</dd>
	</c:otherwise>
</c:choose>
<div class="buyCom_price c-666 fr tar mt10 pr10">
	<span>课程数量：<span class="fsize14 c-master mr5" id="buyCount">${shopcartList.size()}</span>&nbsp;&nbsp;</span>
	课程金额：<span class="fsize14 c-master mr5" id="div_totalMoney">￥${totalMoney }</span>
</div>
	
			
