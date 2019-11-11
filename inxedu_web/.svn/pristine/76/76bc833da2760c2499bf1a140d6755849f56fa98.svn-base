<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}"  type="date" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" var="now"/>
	<section class="">
		<h6 class="c-g-content c-infor-title">
			<span>近期播放</span>
		</h6>
		<article class="dqkc-list mb30">
			<!-- /无数据提示 开始-->
			<%--如果没有最近直播时间--%>
			<c:if test="${empty liveTime}">
			<section class="no-data-wrap liveNoData"><%--直播已经开始--%>
				<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">最近没有直播<a href="${ctx}/front/showcoulist" class="c-master f-fM fsize14"></a>！</span>
			</section>
			</c:if>
		<%--如果有最近直播时间--%>
		<c:if test="${not empty liveTime}">
			<%--若果直播时间到现在时间大于0 说明直播还没开始--%>
			<c:if test="${liveTime>0}">
			<section class="no-data-wrap liveCoutTime"><%--直播未开始 倒计时 最近开始的一节课--%>
				<em class="icon30 no-data-ico">&nbsp;</em>
				<span class="ml10 vam disIb">
					<p class="hLh30 c-666 fsize14 f-fM syD mt5" id="showTimes">直播倒计时：<tt class="f-fM">0</tt>天<tt class="f-fM">0</tt>时<tt class="f-fM">0</tt>分<tt class="f-fM">0</tt>秒</p>
					<p class="hLh30 c-red fsize12 f-fM mt5">提示：直播开始前15分钟才能进入直播大厅哦！！！</p>
				</span>
			</section>
			<div class="clearfix cous-new-late">
				<span class="fl vam">
					<em class="icon20 lh-p-icon-live-xz">&nbsp;</em>
					<tt class="vam fsize16 c-master f-fM">${courseKpoint.name}</tt>
				</span>
				<span class="fr mr20">
					<tt class="vam fsize12 f-fM c-666">
						<fmt:formatDate value="${courseKpoint.liveBeginTime}" pattern="MM-dd HH:mm" type="date"/>~
						<fmt:formatDate value="${courseKpoint.liveEndTime}" pattern="HH:mm" type="date"/>
						直播
					</tt>
				</span>
			</div>
			</c:if>
			<!-- /无数据提示 结束-->
			<%--若果直播时间到现在时间小于0 说明正在直播--%>
			<c:if test="${liveTime<0}">
			<div class="clearfix cous-new-late">
				<span class="fl vam">
					<em class="icon20 lh-p-icon-live-xz">&nbsp;</em>
					<tt class="vam fsize16 c-master f-fM">${courseKpoint.name}</tt>
				</span>
				<span class="fr vam">
					<a href="javascript:livePlay(${courseKpoint.kpointId})" target="_blank"  class="cou-kscp" title="进入直播">进入直播</a>
				</span>
				<span class="fr mr20">
					<img src="${ctx}/static/inxweb/img/live-ing.gif" width="100%" height="100%" class="vam disIb">
				</span>
			</div>
			</c:if>
		</c:if>
		</article>
	</section>
	<section class="">
		<h6 class="c-g-content c-infor-title">
			<span>直播目录</span>
		</h6>
		<menu id="lh-menu" class="lh-menu">
			<ul>
				<c:forEach items="${parentKpointList}" var="parentLivePoint">
					<c:if test="${parentLivePoint.kpointType==0}">
					<li class="lh-menu-stair">
						<a class="l-m-stitle current-1" href="javascript:livePlay(${childKpoint.kpointId})" title="${parentLivePoint.name}">

							<span class="fr">
								<em class="icon16 m-tree-icon">&nbsp;</em>
							</span>


							${parentLivePoint.name}
						</a>
						<ol class="lh-menu-ol" >
							<%--父级节点下的子节点--%>
							<c:forEach items="${parentLivePoint.kpointList}" var="childKpoint">

								<c:set var="liveEndTime">
									<fmt:formatDate value="${childKpoint.liveEndTime}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/>
								</c:set>
								<c:set var="liveBeginTime">
									<fmt:formatDate value="${childKpoint.liveBeginTime}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/>
								</c:set>
								<%--开始时间小于当前时间结束时间大于当前时间 为正在直播--%>
								<c:if test="${liveBeginTime<now &&liveEndTime>now}">

								<li class="lh-menu-second">
									<div class="l-m-stitle clearfix">
								<span class="fr">
										<a class="cou-kscp" href="javascript:livePlay(${childKpoint.kpointId})"  title="进入直播">进入直播</a>
								</span>
										<span class="fr mr20">
									<img src="${ctx}/static/inxweb/img/live-ing.gif" width="100%" height="100%" class="vam disIb">
								</span>
										<a class="cou-tit-txt" href="javascript:livePlay(${childKpoint.kpointId})" title="${childKpoint.name}" >${childKpoint.name}</a>
									</div>
								</li>
								</c:if>
								<%--开始时间大于当前时间 为未开始直播--%>
								<c:if test="${liveBeginTime > now }">
								<li class="lh-menu-second">
									<div class="l-m-stitle clearfix">
								<span class="fr">
								<c:set var="after15min" value="<%=new Date(System.currentTimeMillis()+900000)%>"></c:set>


								<%--	<c:set var="beginTime">
										<fmt:formatDate value="${liveBeginTime}" type="long"/>
										<fmt:formatNumber var=""/>
									</c:set>--%>

									<c:if test="${after15min > childKpoint.liveBeginTime}">
									<a  class="cou-kscp gmkc" href="javascript:void(0)" title="未开始">未开始</a><%--点击弹出提示 开播之前15分钟可以进入--%>
									</c:if>
									<c:if test="${after15min < childKpoint.liveBeginTime}">
											<a  class="cou-kscp" href="javascript:livePlay(${childKpoint.kpointId})" title="马上开始">马上开始</a><%--开播之前15分钟后按钮变色--%>
									</c:if>
									</span>
										<span class="fr mr20">
									<tt class="vam fsize12 f-fM c-master">
										<fmt:formatDate value="${childKpoint.liveBeginTime}" pattern="yyyy-MM-dd HH:mm" type="date"/>~
										<fmt:formatDate value="${childKpoint.liveEndTime}" pattern="HH:mm" type="date"/>
										</tt>
								</span>
										<a class="cou-tit-txt" title="${childKpoint.name}" onclick="" href="javascript:livePlay(${childKpoint.kpointId})">${childKpoint.name}</a>
									</div>
								</li>
								</c:if>
								<%--结束时间大于当前时间 为结束直播--%>

								<c:if test="${liveEndTime < now }">
								<li class="lh-menu-second">
									<div class="l-m-stitle clearfix">
								<span class="fr">
									<a  class="cou-kscp" href="javascript:livePlay(${childKpoint.kpointId})"  title="查看回放">查看回放</a>
								</span>
										<span class="fr mr20">
									<tt class="vam fsize12 f-fM c-666">
										<fmt:formatDate value="${childKpoint.liveBeginTime}" pattern="yyyy-MM-dd HH:mm" type="date"/>~
										<fmt:formatDate value="${childKpoint.liveEndTime}" pattern="HH:mm" type="date"/>
										</tt>
								</span>
										<a class="cou-tit-txt" title="${childKpoint.name}"  href="javascript:livePlay(${childKpoint.kpointId})">${childKpoint.name}</a>
									</div>
								</li>
								</c:if>
							</c:forEach>
						</ol>
					</li>
					</c:if>
					<%--如果父节点不是目录--%>
					<c:set var="liveBeginTime">
						<fmt:formatDate value="${parentLivePoint.liveBeginTime}" pattern="yyyy-MM-dd" type="date"/>~
					</c:set>
					<c:set var="liveEndTime">
						<fmt:formatDate value="${parentLivePoint.liveEndTime}" pattern="yyyy-MM-dd" type="date"/>~
					</c:set>
					<%--set直播结束时间的时间格式--%>
					<c:set var="time">
						<%--如果开始和结束时间的年月日不同 则显示年月日时分--%>
						<c:if test="${liveBeginTime!=liveEndTime }">
							yyyy-MM-dd HH:mm
						</c:if>
						<%--如果开始和结束时间的年月日相同 则只显示时分--%>
						<c:if test="${liveBeginTime==liveEndTime }">
							HH:mm
						</c:if>
					</c:set>
					<c:if test="${parentLivePoint.kpointType!=0}">
						<%--开始时间小于当前时间结束时间大于当前时间 为正在直播--%>
						<c:if test="${liveBeginTime<now &&liveEndTime>now}">

							<li class="lh-menu-second">
								<div class="l-m-stitle clearfix">
								<span class="fr">
										<a class="cou-kscp" href="javascript:livePlay(${parentLivePoint.kpointId})"  title="进入直播">进入直播</a>
								</span>
									<span class="fr mr20">
									<img src="${ctx}/static/inxweb/img/live-ing.gif" width="100%" height="100%" class="vam disIb">
								</span>
									<a class="cou-tit-txt" href="javascript:livePlay(${parentLivePoint.kpointId})" title="${parentLivePoint.name}" >${parentLivePoint.name}</a>
								</div>
							</li>
						</c:if>
						<%--开始时间大于当前时间 为未开始直播--%>
						<c:if test="${liveBeginTime > now }">
							<li class="lh-menu-second">
								<div class="l-m-stitle clearfix">
								<span class="fr">
								<c:set var="after15min" value="<%=new Date(System.currentTimeMillis()+900000)%>"></c:set>


								<%--	<c:set var="beginTime">
										<fmt:formatDate value="${liveBeginTime}" type="long"/>
										<fmt:formatNumber var=""/>
									</c:set>--%>

									<c:if test="${after15min > parentLivePoint.liveBeginTime}">
										<a  class="cou-kscp gmkc" href="javascript:void(0)" title="未开始">未开始</a><%--点击弹出提示 开播之前15分钟可以进入--%>
									</c:if>
									<c:if test="${after15min < parentLivePoint.liveBeginTime}">
										<a  class="cou-kscp" href="javascript:livePlay(${parentLivePoint.kpointId})" title="马上开始">马上开始</a><%--开播之前15分钟后按钮变色--%>
									</c:if>
									</span>
									<span class="fr mr20">

									<tt class="vam fsize12 f-fM c-master">

										<fmt:formatDate value="${parentLivePoint.liveBeginTime}" pattern="yyyy-MM-dd HH:mm" type="date"/>~
										<fmt:formatDate value="${parentLivePoint.liveEndTime}" pattern="${time}" type="date"/>
										</tt>
								</span>
									<a class="cou-tit-txt" title="${parentLivePoint.name}" onclick="" href="javascript:livePlay(${parentLivePoint.kpointId})">${parentLivePoint.name}</a>
								</div>
							</li>
						</c:if>
						<%--结束时间大于当前时间 为结束直播--%>

						<c:if test="${liveEndTime < now }">
							<li class="lh-menu-second">
								<div class="l-m-stitle clearfix">
								<span class="fr">
									<a  class="cou-kscp" href="javascript:livePlay(${parentLivePoint.kpointId})"  title="查看回放">查看回放</a>
								</span>
									<span class="fr mr20">
									<tt class="vam fsize12 f-fM c-666">
										<fmt:formatDate value="${parentLivePoint.liveBeginTime}" pattern="yyyy-MM-dd HH:mm" type="date"/>~
										<fmt:formatDate value="${parentLivePoint.liveEndTime}" pattern="yyyy-MM-dd HH:mm" type="date"/>
										</tt>
								</span>
									<a class="cou-tit-txt" title="${parentLivePoint.name}"  href="javascript:livePlay(${parentLivePoint.kpointId})">${parentLivePoint.name}</a>
								</div>
							</li>
						</c:if>
					</c:if>
				</c:forEach>
				<%--<li class="lh-menu-stair">
					<a class="l-m-stitle current-1" href="javascript: void(0)" title="第一章">
						<span class="fr">
							<em class="icon16 m-tree-icon">&nbsp;</em>
						</span>
						第一章
					</a>
					<ol class="lh-menu-ol" style="display: block;">
						<li class="lh-menu-second">
							<div class="l-m-stitle clearfix">
								<span class="fr">
									<a class="cou-kscp" href="javascript:livePlay(${courseKpoint.kpointId})"  title="进入直播">进入直播</a>
								</span>
								<span class="fr mr20">
									<img src="${ctx}/static/inxweb/img/live-ing.gif" width="100%" height="100%" class="vam disIb">
								</span>
								<a class="cou-tit-txt" title="第二节" onclick="" href="javascript:void (0)">第二节</a>
							</div>
						</li>
						<li class="lh-menu-second">
							<div class="l-m-stitle clearfix">
								<span class="fr">
									<a id="playKpointId268" class="cou-kscp gmkc" href="javascript:void(0)" title="未开始">未开始</a>&lt;%&ndash;点击弹出提示 开播之前15分钟可以进入&ndash;%&gt;
									<a id="playKpointId268" class="cou-kscp" href="javascript:void(0)" title="马上开始">马上开始</a>&lt;%&ndash;开播之前15分钟后按钮变色&ndash;%&gt;
									<a class="cou-kscp ml20" style="display: none" href="" title="考试测评">考试测评</a>
								</span>
								<span class="fr mr20">
									<tt class="vam fsize12 f-fM c-master">08月07日 10:30~12:20</tt>
								</span>
								<a class="cou-tit-txt" title="第二节" onclick="" href="javascript:void (0)">第二节</a>
							</div>
						</li>
						<li class="lh-menu-second">
							<div class="l-m-stitle clearfix">
								<span class="fr">
									<a id="playKpointId268" class="cou-kscp" href="javascript:void(0)" onclick="toVideoPlayer('${ctx}/uc/play/39/268')" title="查看回放">查看回放</a>
								</span>
								<span class="fr mr20">
									<tt class="vam fsize12 f-fM c-666">08月07日 10:30~12:20</tt>
								</span>
								<a class="cou-tit-txt" title="第二节" onclick="" href="javascript:void (0)">第二节</a>
							</div>
						</li>
					</ol>
				</li>--%>
			</ul>
		</menu>
	</section>

<script>
	$(function () {
		conDowFun();//直播倒计时
		treeMenu();
		checkLivePercent();
	});
	/*判断直播进度*/
	function checkLivePercent() {
		var parentSize = ${parentKpointsize}+"";
		var childSize = ${childKpointSize}+"";
		var overkpoint = ${overkpoint}+"";
		$(".parentKopintCount").html(parentSize);
		$(".childKopintCount").html(childSize);
		$(".childKopint").html(overkpoint+"/共"+childSize+"节");
		$(".livePercent").css("width",overkpoint/childSize*100+"%");
		if (overkpoint/childSize<1){
			$(".livePercent").addClass("bg-green")
		}else if (overkpoint/childSize==1){
			$(".livePercent").addClass("bg-orange")
		}
	}
	//直播倒计时
	function conDowFun() {
		var second = ${not empty liveTime?liveTime:0}; // 剩余秒数

		ss = second%1000;
		second = (second -ss)/1000;
		//写一个方法，将秒数专为天数
		if (second > 0) {

			var toDays = function () {
				var s = second % 60; // 秒
				if (s < 10) {
					s = "0" + s;
				}
				var mi = (second - s) / 60 % 60; // 分钟
				var h = ((second - s) / 60 - mi ) / 60 % 24; // 小时
				if (h < 10) {
					h = "0" + h;
				}
				var d = (((second - s) / 60 - mi ) / 60 - h ) / 24; // 天
				return "直播倒计时：<tt>" + d + "</tt>天<tt>" + h + "</tt>时<tt>" + mi + "</tt>分<tt>" + s + "</tt>秒";
			};
			//然后写一个定时器
			window.setInterval(function () {
				if (second > 0) {
					second--;
				}
				document.getElementById("showTimes").innerHTML = toDays();
			}, 1000);
		}
	}


</script>