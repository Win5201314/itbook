<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}"  type="date" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" var="now"/>
<!DOCTYPE html>
<html>
<head>
<title>直播详情</title>
</head>
<body>
	<div id="aCoursesList" class="bg-fa of">
		<section class="live-info-banner mt25">
			<div class="container">
				<div class="clearfix ">
					<div class="fl col-7">
						<div class="">
							<article class="l-info-pic-wrap">
								<img src="${ctx}/static/inxweb/img/live-infor-bg.jpg" class="pic">
								<section class="new-liv-infor-top">
									<div class="n-l-in-t-tit tac">
										<span class="vam mr30">
											<tt class="vam fsize14 f-fM c-fff">正在学习：</tt>
											<tt class="vam fsize14 f-fM light-yellow">${started}课时</tt>
											<tt class="vam fsize14 f-fM c-fff">/${videoKpointSize}课时</tt>
										</span>
										<span class="vam">
											<em class="vam icon16 time">&nbsp;</em>
											<c:set var="beginDate">
												<fmt:formatDate value="${course.liveBeginTime}" pattern="MM-dd" type="date"/>
											</c:set>
											<c:set var="endDate">
												<fmt:formatDate value="${course.liveEndTime}" pattern="MM-dd" type="date"/>
											</c:set>
											<c:if test="${beginDate == endDate}">
												<tt class="c-fff fsize14 f-fM vam">
													<fmt:formatDate value="${course.liveBeginTime}" pattern="MM-dd" type="date"/>
												</tt>
												<tt class="c-fff fsize14 f-fM vam">
													<fmt:formatDate value="${course.liveBeginTime}" pattern="HH:mm" type="date"/> ~
													<fmt:formatDate value="${course.liveEndTime}" pattern="HH:mm" type="date"/>
												</tt>
											</c:if>
											<c:if test="${beginDate != endDate}">
												<tt class="c-fff fsize14 f-fM vam"><fmt:formatDate value="${course.liveBeginTime}" pattern="MM-dd" type="date"/></tt>
												<tt class="c-fff fsize14 f-fM vam ml5"><fmt:formatDate value="${course.liveBeginTime}" pattern="HH:mm" type="date"/></tt>
												<tt class="c-fff fsize14 f-fM vam">~</tt>
												<tt class="c-fff fsize14 f-fM vam ml5"><fmt:formatDate value="${course.liveEndTime}" pattern="MM-dd" type="date"/></tt>
												<tt class="c-fff fsize14 f-fM vam ml5"><fmt:formatDate value="${course.liveEndTime}" pattern="HH:mm" type="date"/></tt>
											</c:if>
										</span>
									</div>
									<div class="n-l-in-t-boy" id="live">
										<p class="name">${course.courseName}</p>
										<a href="javascript:void(0)" onclick="nowLivePlay(${course.courseId})" title="${course.courseName}" class="btn tac">
											<img src="${ctx}/static/inxweb/img/l-in-l.gif" class="disIb">
										</a>
									</div>
								</section>
							</article>
						</div>
					</div>
					<div class="fr col-3">
						<section class="l-i-desc">
							<div class="l-i-box live-box-topr">
								<h2 class="l-i-title"> <span class="c-fff fsize20">${course.courseName}</span> </h2>
								<section class="mt20 hLh30">
									<span class="vam mr30 c-fff fsize14">价格：<tt class="c-red-live f-fG fsize24">￥${course.currentPrice }</tt></span>
									<span class="vam">
										<tt class="fsize14 c-fff vam f-fM">课时数：</tt>
										<tt class="fsize14 c-fff vam f-fM">${course.lessionNum}</tt>
									</span>
								</section>
								<section class="mt20">
									<c:if test="${serviceSwitch.member=='ON'&&isok==false &&fn:length(courseMemberList)>0}">
										<span class="c-in-vip ml10">
											<a href="${ctx}/uc/associator" class="vam">
												<img src="${ctx}/static/inxweb/img/cou-xq-vip.png">
											</a>
											<div class="c-i-vip-br">
												<tt class="vam f-fM fsize12 c-999">该课程属于</tt>
												<tt class="vam f-fM fsize12 c-master">
													<c:forEach items="${courseMemberList}" var="courseMember">
														${memberTypes[courseMember.memberTypeId-1].title}&nbsp;
													</c:forEach>
												</tt>
												<tt class="vam f-fM fsize12 c-999">免费课程,了解更多会员信息，请点击下方立即加入VIP。</tt>
											</div>
											<div class="DT-arrow">
												<em>◆</em>
												<span>◆</span>
											</div>
										</span>
									</c:if>
								</section>
								<section class="mt20 hLh30">
									<span class="c-fff fsize14">讲师：
									<c:forEach items="${teacherList }" var="tea">
										<%-- <a href="${ctx}/front/teacher/${tea.id }">${tea.name }</a>&nbsp;&nbsp;&nbsp; --%>
										${tea.name }&nbsp;&nbsp;&nbsp;
									</c:forEach></span>
								</section>
								<section class="mt20 hLh30">
									<span class="c-fff fsize14">有效期：
										<c:if test="${course.loseType==0 }">
											<fmt:formatDate pattern="yyyy/MM/dd HH:mm"  value="${course.endTime}" />
										</c:if>
										<c:if test="${course.loseType==1 }">
												从购买之日起${course.loseTime }天
											</span>
										</c:if>
									</span>
								</section>
								<section class="mt20 hLh30">
									<span class="vam mr30">
										<em class="icon18 l-gms-ico">&nbsp;</em>
										<tt class="fsize14 c-fff vam f-fM">购买数：</tt>
										<tt class="fsize14 c-fff vam f-fM">${course.pageBuycount }</tt>
									</span>
									<span class="vam mr30">
										<em class="icon18 l-lls-ico">&nbsp;</em>
										<tt class="fsize14 c-fff vam f-fM">浏览数：</tt>
										<tt class="fsize14 c-fff vam f-fM">${course.pageViewcount }</tt>
									</span>
								</section>
								<section class="c-attr-mt of mt30">
									<c:if test="${isFavorites==true }">
										<a title="取消收藏" href="javascript:void(0)" class="vam sc-end" onclick="deleteFaveorite(${course.courseId},this)"><em class="icon18 l-scIcon"></em><tt class="c-fff f-fM fsize14 ml5">取消收藏</tt></a>
									</c:if>
									<c:if test="${isFavorites!=true }">
										<a title="收藏" href="javascript:void(0)" class="vam" onclick="favorites(${course.courseId},this)"><em class="icon18 l-scIcon"></em><tt class="c-fff f-fM fsize14 ml5">收藏</tt></a>
									</c:if>
									<section class="kcShare pr hand vam ml20 disIb">
										<tt>
											<em class="icon18 l-shareIcon"></em><span class="vam c-fff f-fM fsize14">分享</span>
										</tt>
										<div id="bdshare" class="bdsharebuttonbox"><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a href="#" class="bds_douban" data-cmd="douban" title="分享到豆瓣网"></a><a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a><a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a></div>
										<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
									</section>
								</section>
								<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
								<div class="mt30 live-btn tac">
									<c:choose>
										<c:when test="${isok==false && course.currentPrice>0}">
											<a href="javascript:void(0)" id="cou-shopcar" title="购买" class="l-i-play l-i-play-jr" onclick="buyNow('${course.courseId}')">购买课程</a>
											<section class="ml15 disIb">
												<a class=" c-fff f-fM l-i-play l-i-play-ku" id="cou-shopcar" href="javascript:void(0)" onclick="addShoppingCart('${course.courseId}')">加入购物车</a>
												<div class="fly_item" id="flyItem">
													<img width="50" height="50" src="/static/inxweb/img/avatar-boy.gif">
												</div>
											</section>
										</c:when>
										<c:otherwise>
											<c:if test="${course.liveEndTime.time<nowDate}">
												<a href="javascript:void(0)" onclick="nowLivePlay(${course.courseId})" id="cou-shopcar" title="" class="l-i-play">
													已结束
												</a>
											</c:if>
											<c:if test="${course.liveBeginTime.time<nowDate and course.liveEndTime.time>nowDate}">
												<a href="javascript:void(0)" onclick="nowLivePlay(${course.courseId})" id="cou-shopcar" title="进入直播" class="l-i-play l-i-play-jr">
													进入直播
												</a>
											</c:if>
											<c:if test="${course.liveBeginTime.time>nowDate and course.liveEndTime.time>nowDate}">
												<a href="javascript:void(0)" onclick="nowLivePlay(${course.courseId})" id="cou-shopcar" title="即将开始" class="l-i-play-wks">
													即将开始
												</a>
											</c:if>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</section>
					</div>
				</div>
			</div>
		</section>
		<div class="mt25 c-infor-box">
			<div class="container">
				<article class="fl col-7">
					<section class="mr25">
						<div class="i-box cou-in-boc">
							<div  class="clearfix c-infor-tabTitle c-tab-title">
								<section class="fl" id="c-i-tabTitle">
									<a href="javascript: void(0)" onclick="selectCourseInfo('liveContext')" title="直播详情" class="current" >直播介绍</a>
									<a href="javascript: void(0)" onclick="selectCourseInfo('liveList')" title="直播课表" >直播课表</a>
									<a href="javascript: void(0)" onclick="selectCourseInfo('liveComment')" title="直播评论" >直播评论</a>
								</section>
								<%--<section class="fr m-check-box">
									<a href="javascript:;"><span class="fsize16 vam">手机查看</span><img width="18" height="18" class="vam ml5" src="/static/exam/images/weixin.jpg"><em class="icon14 ml5 phone-check"> </em></a>
									<div id="output" class="m-ewm"></div>
								</section> --%>
							</div>
							<article class="ml10 mr10 live-infor-flbox">
								<div id="liveContext">
									<h6 class="c-i-content c-infor-title"> <span>简介</span> </h6>
									<!-- /无数据提示 开始-->
									<c:if test="${empty course.context}">
										<section class="no-data-wrap">
											<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
										</section>
									</c:if>
									<!-- /无数据提示 结束-->
									<c:if test="${not empty course.context}">
										<div class="course-txt-body-wrap"> <section class="course-txt-body" >
											<p>${course.context}</p> </section>
											<%--<section class="ctb-btn"><a title="查看更多" class="comm-btn c-btn-6" href="javascript: void(0)">查看更多∨</a></section>--%>
										</div>
									</c:if>
								</div>
								<div class="ml10 liveList-coures" id="liveList" style="display: none;">
									<h6 class="c-g-content c-infor-title"> <span>直播课表</span> </h6>
									<c:if test="${empty parentKpointList}">
										<section class="no-data-wrap">
											<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
										</section>
									</c:if>
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

                                                                            <c:if test="${after15min < childKpoint.liveBeginTime}">
                                                                                <a  class="cou-kscp gmkc" href="javascript:void(0)" title="未开始">&nbsp;未&nbsp;开&nbsp;始</a><%--点击弹出提示 开播之前15分钟可以进入--%>
                                                                            </c:if>
                                                                            <c:if test="${after15min > childKpoint.liveBeginTime}">
                                                                                <a  class="cou-kscp" href="javascript:livePlay(${childKpoint.kpointId})" title="马上开始">马上开始</a><%--开播之前15分钟后按钮变色--%>
                                                                            </c:if>
                                                                            </span>
																		<span class="fr mr20">
                                                                            <tt class="vam fsize12 f-fM c-master">
                                                                                <fmt:formatDate value="${childKpoint.liveBeginTime}" pattern="yyyy-MM-dd HH:mm" type="date"/>&nbsp;~&nbsp;<fmt:formatDate value="${childKpoint.liveEndTime}" pattern="HH:mm" type="date"/>
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
                                                                                <fmt:formatDate value="${childKpoint.liveBeginTime}" pattern="yyyy-MM-dd HH:mm" type="date"/>&nbsp;~&nbsp;<fmt:formatDate value="${childKpoint.liveEndTime}" pattern="HH:mm" type="date"/>
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
											<c:if test="${parentLivePoint.kpointType!=0}">
                                                <c:set var="liveBeginTime">
                                                    <fmt:formatDate value="${parentLivePoint.liveBeginTime}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/>~
                                                </c:set>
                                                <c:set var="liveEndTime">
                                                    <fmt:formatDate value="${parentLivePoint.liveEndTime}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/>~
                                                </c:set>
                                                <%--set直播结束时间的时间格式--%>
                                                <c:set var="time">
                                                    <%--如果开始和结束时间的年月日不同 则显示年月日时分--%>
                                                    <c:if test="${liveBeginTime!=endTime }">
                                                        yyyy-MM-dd HH:mm
                                                    </c:if>
                                                    <%--如果开始和结束时间的年月日相同 则只显示时分--%>
                                                    <c:if test="${liveEndTime==endTime }">
                                                        HH:mm
                                                    </c:if>
                                                </c:set>
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


                                                            <c:if test="${after15min < parentLivePoint.liveBeginTime}">
                                                                <a  class="cou-kscp gmkc" href="javascript:void(0)" title="未开始">&nbsp;未&nbsp;开&nbsp;始</a><%--点击弹出提示 开播之前15分钟可以进入--%>
                                                            </c:if>
                                                            <c:if test="${after15min >parentLivePoint.liveBeginTime}">
                                                                <a  class="cou-kscp" href="javascript:livePlay(${parentLivePoint.kpointId})" title="马上开始">马上开始</a><%--开播之前15分钟后按钮变色--%>
                                                            </c:if>
                                                            </span>
                                                            <span class="fr mr20">

                                                                <tt class="vam fsize12 f-fM c-master">
                                                                	<fmt:formatDate value="${parentLivePoint.liveBeginTime}" pattern="yyyy-MM-dd HH:mm" type="date"/>&nbsp;~&nbsp;<fmt:formatDate value="${parentLivePoint.liveEndTime}" pattern="${time}" type="date"/>
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
                                                                    <fmt:formatDate value="${parentLivePoint.liveBeginTime}" pattern="yyyy-MM-dd HH:mm" type="date"/>&nbsp;~&nbsp;<fmt:formatDate value="${parentLivePoint.liveEndTime}" pattern="${time}" type="date"/>
                                                                </tt>
                                                            </span>
															<a class="cou-tit-txt" title="${parentLivePoint.name}"  href="javascript:livePlay(${parentLivePoint.kpointId})">${parentLivePoint.name}</a>
														</div>
													</li>
												</c:if>
											</c:if>
										</c:forEach>
									</ul>
                                    </menu>
								</div>
								<div id="liveComment" style="display:none">
								<div class="mt20 commentHtml" >
									<%--<h6 id="i-art-comment" class="c-c-content c-infor-title">
										<span class="commentTitle">课程评论</span>
									</h6>--%>
								</div>
								</div>
							</article>
						</div>
					</section>
				</article>
				<aside class="fl col-3 liveInfoRight">
					<div class="i-box">
						<div>
							<section class="c-infor-tabTitle c-tab-title">
								<span>直播主讲讲师</span>
							</section>
							<section class="stud-act-list">
								<c:forEach items="${teacherList }" var="tea">
									<ul style="height: auto;">
										<li>
											<div class="u-face">
												<a href="${ctx }/front/teacher/${tea.id }">
													<c:choose>
														<c:when test="${not empty tea.picPath }">
															<img src="${staticServer}${tea.picPath }" width="50" height="50" alt="">
														</c:when>
														<c:otherwise>
															<img src="${ctx }/static/inxweb/img/avatar-boy.gif" width="50" height="50" alt="">
														</c:otherwise>
													</c:choose>
												</a>
											</div>
											<section class="hLh30 txtOf">
												<a class="c-333 fsize16 fl" href="${ctx }/front/teacher/${tea.id }">${tea.name }</a>
											</section>
											<section class="hLh20 txtOf">
												<span class="c-999">${tea.education }</span>
											</section>
										</li>
									</ul>
									<%-- <div class="c-teacher-txt-show">
										<p>${tea.career }</p>
									</div> --%>
								</c:forEach>
							</section>
						</div>
					</div>
					<div class="i-box mt20">
						<div >
							<section class="c-infor-tabTitle c-tab-title">
								<span>学过此课的人（${couStudyhistorysLearned.size()}）</span>
							</section>
							<section class="no-data-wrap">
								<c:if test="${empty couStudyhistorysLearned}">
										<section class="no-data-wrap">
											<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在整理中...</span>
										</section>
								</c:if>
								<c:if test="${not empty couStudyhistorysLearned}">
									<section class="buy-cin-list">
										<c:forEach items="${couStudyhistorysLearned}" var="CourseStudyhistory">
												<span title="${CourseStudyhistory.userShowName}"><img alt="" src="${staticServer}${CourseStudyhistory.userImg}"><tt class="c-999">${CourseStudyhistory.userShowName}</tt></span>
										</c:forEach>
									</section>
								</c:if>

							</section>
						</div>
					</div>
					<div class="i-box mt20">
						<div>
							<section class="c-infor-tabTitle c-tab-title">
								<span>猜你想学</span>
							</section>
							<c:if test="${empty courseList}">
								<section class="no-data-wrap">
									<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在整理中...</span>
								</section>
							</c:if>
							<c:if test="${not empty courseList}">
								<section class="course-r-list">
									<ol>
										<c:forEach items="${courseList}" var="interfixCourse">
											<li>
												<aside class="course-r-pic">
													<a href="${ctx }/front/couinfo/${interfixCourse.courseId}" title="">
														<c:choose>
															<c:when test="${interfixCourse.logo!=null && interfixCourse.logo!=''}">
																<img alt="" src="${staticServer}${interfixCourse.logo}" />
															</c:when>
															<c:otherwise>
																<img alt="" src="${ctx }/static/inxweb/img/coures.gif" />
															</c:otherwise>
														</c:choose>
													</a>
												</aside>
												<section class="hLh20 txtOf">
													<a href="${ctx }/front/couinfo/${interfixCourse.courseId}" class="c-333 fsize16">${interfixCourse.courseName}</a>
												</section>
												<section class="hLh20 mt5 txtOf">
													<span class="c-999">讲师： <c:if test="${interfixCourse.teacherListMap!=null && interfixCourse.teacherListMap.size()>0}">
														<c:forEach items="${interfixCourse.teacherListMap}" var="teacher">
															${teacher.name}&nbsp;&nbsp;
														</c:forEach>
													</c:if>
													</span>
												</section>
												<section class="hLh20 txtOf">
													<span class="c-master">${interfixCourse.pageBuycount }人</span>
												</section>
											</li>
										</c:forEach>
									</ol>
								</section>
							</c:if>
						</div>
					</div>
				</aside>
			</div>
		</div>
	</div>
	<input type="hidden" id="otherId" value="${course.courseId}"/>
	<input type="hidden" id="loseTimeTime" value="<fmt:formatDate value="${course.endTime}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/>"/>
	<input type="hidden" id="loseType" value="${course.loseType}"/>
	<script type="text/javascript" src="${ctx}/static/common/qrcode/jquery.qrcode.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/inxweb/front/liveInfo.js"></script>
	<script type="text/javascript" src="${ctx}/static/inxweb/comment/comment.js"></script>
	<script type="text/javascript" src="${ctx}/static/inxweb/js/parabola.js"></script>
	<script type="text/javascript" src="${ctx}/static/inxweb/live/live-info.js"></script>
</body>
</html>