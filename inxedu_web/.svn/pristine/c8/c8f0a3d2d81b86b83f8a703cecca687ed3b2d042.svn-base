<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>${course.courseName}详情</title>
<script type="text/javascript" src="${ctx}/static/common/jquery-1.11.1.min.js"></script>
<%--<script src="http://vod.baofengcloud.com/html/script/bfcloud.js?v=2"></script>--%>
<script src='http://player.polyv.net/script/polyvplayer.min.js'></script>
	<script type="text/javascript" src="http://cdn.aodianyun.com/mps/v1/hlsplayer.js"></script>
</head>
<body>
<div id="sourceqrcode" class="QRcode-lt-ie9" style="display: none;"></div>
<div id="qrcode" class="QRcode-lt-ie9"></div>
	<div id="aCoursesList" class="bg-f8 of cours-in-bigbox">
		<!-- /课程详情 开始 -->
		<section class="container">
			<section class="path-wrap txtOf hLh30">
				<a href="${ctx }" title="" class="c-999 fsize14">首页</a>
				\
				<a href="${ctx }/front/showcoulist" title="" class="c-999 fsize14">课程列表</a>
				\ <span class="c-333 fsize14">${course.courseName}</span>
			</section>
			<div class="cours-big-box">
				<article class="c-v-pic-wrap">
					<section class="p-h-video-box" id="videoPlay">
						<c:choose>
							<c:when test="${course.logo!=null &&course.logo!=''}">
								<img src="${staticServer}${course.logo}" alt="${course.courseName}" class="dis c-v-pic" />
							</c:when>
							<c:otherwise>
								<img src="${ctx}/static/inxweb/img/coures.gif" alt="${course.courseName}" class="dis c-v-pic" />
							</c:otherwise>
						</c:choose>
	
						<a href="javascript:void(0)" onclick="vedioClick(${freeVideoId},'${kpointListIsNull}')" title="${course.courseName}" class="v-play-btn">
							<em class="icon30">&nbsp;</em>
						</a>
						<section class="zhz-bg"></section>
					</section>
					<section class="p-h-video-tip">
						<img src="${ctx }/static/inxweb/img/v-loading.gif">
						<p class="hLh20"><span class="c-999">加载中...</span></p>
					</section>

				</article>
				<aside class="c-attr-wrap">
					<section class="c-attr-wrap-infor">
						<h2 class="hLh30 txtOf">
							<span class="c-333 fsize24">${course.courseName}</span>
						</h2>
						<section class="hLh30 mt15 of c-th-box">
							<ol class="thr-attr-ol clearfix">
								<li>
									<aside>
										<em class="icon16 gms-ico">&nbsp;</em>
										<tt class="c-666 f-fM fsize14 mr5 vam">购买数</tt>
										<tt class="c-333 f-fM fsize14 vam">${course.pageBuycount }</tt>
									</aside>
								</li>
								<li>
									<aside>
										<em class="icon16 kss-ico">&nbsp;</em>
										<tt class="c-666 f-fM vam fsize14 mr5">课时数</tt>
										<tt class="c-333 f-fM vam fsize14">${course.lessionNum }</tt>
									</aside>
								</li>
								<li>
									<aside>
										<em class="icon16 lls-ico">&nbsp;</em>
										<tt class="c-666 f-fM vam fsize14 mr5">浏览数</tt>
										<tt class="c-333 f-fM vam fsize14">${course.pageViewcount}</tt>
									</aside>
								</li>
							</ol>
						</section>
						<section class="c-attr-mt hLh30 of mt10">
							<tt class="c-999 fsize14 f-fM vam">主讲：</tt>
							<c:forEach items="${teacherList }" var="tea">
								<tt class="c-999 fsize14 f-fM vam">
								<%-- <a href="${ctx}/front/teacher/${tea.id }">${tea.name }</a>&nbsp;&nbsp;&nbsp; --%>
								${tea.name }
								</tt>
							</c:forEach>
						</section>
						<section class="c-attr-mt mt10">
							<tt class="c-999 fsize14 f-fM vam">课程有效期：</tt>
							<tt class="c-999 fsize14 f-fM vam">
								<c:if test="${course.loseType==0 }">
									<fmt:formatDate pattern="yyyy/MM/dd HH:mm"  value="${course.endTime}" />
								</c:if>
								<c:if test="${course.loseType==1 }">
									从购买之日起${course.loseTime }天
								</c:if>
							</tt>
						</section>
						<section class="c-attr-jg mt15 c-attr-jg-vip">
							<tt class="c-999 fsize14 f-fM vam">价格：</tt>
							<big class="c-red">￥${course.currentPrice }</big>
							<tt class="c-999 fsize12 f-fM ml10 vam">原价：<s>￥${course.sourcePrice }</s></tt>
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
						<section class="c-attr-mt of mt15">
							<c:if test="${isFavorites==true }">
								<a title="取消收藏" href="javascript:void(0)" class="vam fl cou-add-fav sc-end f-fM mr20" onclick="deleteFaveorite(${course.courseId},this)"><em class="icon18 scIcon mr5">&nbsp;</em><tt class="c-master fsize14 vam f-fM">取消收藏</tt></a>
							</c:if>
							<c:if test="${isFavorites!=true }">
								<a title="收藏" href="javascript:void(0)" class="vam fl cou-add-fav f-fM mr20" onclick="favorites(${course.courseId},this)"><em class="icon18 mr5 scIcon">&nbsp;</em><tt class="c-666 fsize14 vam f-fM">收藏</tt></a>
							</c:if>
							<section class="kcShare pr fl hand vam">
								<span>
									<em class="icon18 shareIcon"></em><tt class="vam c-666 fsize14 f-fM">分享</tt>
								</span>
								<div id="bdshare" class="bdsharebuttonbox"><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a href="#" class="bds_douban" data-cmd="douban" title="分享到豆瓣网"></a><a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a><a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a></div>
								<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
							</section>
						</section>
						<section class="c-attr-mt new-cours-btnbox mt30">
							<c:choose>
								<c:when test="${isok==false && course.currentPrice>0}">
									<a href="javascript:void(0)" id="cou-shopcar" title="购买" class="comm-btn c-btn-3 cou-buy-btn" onclick="buyNow('${course.courseId}')">购买课程</a>
									<section class="ml15 c-shop-car-wrap disIb">
										<a class=" c-fff f-fM btnCart" id="cou-shopcar" href="javascript:void(0)" onclick="addShoppingCart('${course.courseId}')">加入购物车</a>
										<div class="fly_item" id="flyItem">
											<img width="50" height="50" src="/static/inxweb/img/avatar-boy.gif">
										</div>
									</section>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0)" id="cou-shopcar" title="立即观看" onclick="if(isLogin()){ window.location.href='${ctx}/uc/play/${course.courseId}/0';} else{lrFun();} " class="comm-btn c-btn-3 cou-buy-btn">立即观看</a>
								</c:otherwise>
							</c:choose>
						</section>
					</section>
				</aside>
				<div class="clear"></div>
			</div>
			<!-- /课程封面介绍 -->
			<div class="mt25 c-infor-box">
				<article class="fl col-7">
					<section class="mr25">
						<c:if test="${not empty packageCourses}">
						<div class="i-box coursePackge ">
							<div class="clearfix c-infor-tabTitle c-tab-title">
								<section  class=" fl">
									<c:forEach items="${packageCourses}" var="packageCourse" varStatus="index">
										<a name="c-g" <c:if test="${index.index==0}">class="current"</c:if> title="${packageCourse.courseName}" onclick="packageCourse('${index.index}',this)" href="javascript: void(0)">${packageCourse.courseName}</a>
									</c:forEach>
								</section>
							</div>

							<c:forEach items="${packageCourses}" var="courseDtoList" varStatus="index">
									<div id="packageCourse${index.index}"<c:if test="${index.index!=0}">style="display: none"</c:if> class="packageCourse">

										<section class="tctj-warp" id="live-box-in">
									<article class="comm-course-list of" id="i-live-cou-list">
										<ul class="of">
											<c:forEach items="${courseDtoList.courseList}" var="courseDto">
												<li>
													<input class="courseId" type="hidden" value="${courseDto.courseId}">
													<input class="currentPrice" type="hidden" value="${courseDto.currentPrice}">
													<input class="endTime" type="hidden" value="<c:if test="${!empty courseDto.endTime}"><fmt:formatDate value="${courseDto.endTime}" pattern="yyyy-MM-dd"/></c:if><c:if test="${empty courseDto.endTime}">0000-00-00</c:if>">
													<div class="cc-l-wrap">
														<section class="course-img">
															<img class="img-responsive" xsrc="${staticServer}${courseDto.logo}" src="${staticServer}${courseDto.logo}" alt="">
														</section>
														<div class="bg-f8 coure-bg-g">
															<h3 class="hLh30 txtOf mt5">
																<a class="course-title fsize16 c-333" href="${ctx}/front/couinfo/${courseDto.courseId}" title="${courseDto.title}">${courseDto.title}</a>
															</h3>
															<section class="mt10 hLh20 of">
															<span class="fr">
																<tt class="c-master fsize14 f-fM">￥${courseDto.currentPrice}</tt>
															</span>
																<span class="fl jgAttr c-ccc f-fA">
																<tt class="c-999 f-fA mr10">${courseDto.pageBuycount}人购买</tt>
																<tt class="c-999 f-fA span-2">${courseDto.pageViewcount}浏览</tt>
															</span>
															</section>
														</div>
													</div>
												</li>
											</c:forEach>
										</ul>
									</article>
									<c:if test="${fn:length(courseDtoList.courseList)>3}">
										<a class="lv-prev lv-contrl" href="javascript: void(0)">prev</a>
										<a class="lv-next lv-contrl" href="javascript: void(0)">next</a>
									</c:if>
								</section>
										<section class="c-in-tc-jg clearfix">
									<div class="fl jg-box mt10">
										<span class="disIb vam fsize14 c-666 f-fM"><tt class="fsize16 c-666 f-fM">共</tt><tt class="fsize18 c-red f-fM ml5 mr5">${fn:length(courseDtoList.courseList)}</tt><tt class="fsize16 c-666 f-fM">门课程</tt></span>
										<span class="disIb vam ml40"><tt class="fsize16 c-666 f-fM">套餐价：￥</tt><tt class="fsize24 c-red f-fM">${courseDtoList.currentPrice}</tt></span>
									</div>
									<div class="fr jg-btn">
										<a href="javascript:void (0)" onclick="buyNow(${courseDtoList.courseId})">购买套餐</a>
									</div>
								</section>

							</div>
							</c:forEach>

						</div>
						</c:if>
						<div class="i-box cou-in-boc <c:if test="${not empty packageCourses}">mt20</c:if>">
							<div class="clearfix c-infor-tabTitle c-tab-title">
								<section id="c-i-tabTitle" class=" fl">
									<a name="c-i" class="current"onclick="selectCourseInfo('courseContext')"  title="课程详情" href="javascript: void(0)">课程详情</a>
									<a name="c-g" title="课程大纲" onclick="selectCourseInfo('courseList')" href="javascript: void(0)">课程大纲</a>
									<a name="c-c" title="课程评论"onclick="selectCourseInfo('courseComment')" href="javascript: void(0)">课程评论</a>
								</section>

							</div>
							<article class="ml10 mr10 new-c-i-b-warp">
								<div id="courseContext" class="c-i-content">
									<h6 class="c-i-content c-infor-title">
										<span>简介</span>
									</h6>
									<!-- /无数据提示 开始-->
									<c:if test="${empty course.context}">
										<section class="no-data-wrap">
											<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
										</section>
									</c:if>
									<!-- /无数据提示 结束-->
									<c:if test="${not empty course.context}">
										<div class="course-txt-body-wrap">
											<section class="course-txt-body">
												<p>${course.context}</p>
											</section>
											<%--<section class="ctb-btn"><a href="javascript: void(0)" class="comm-btn c-btn-6" title="查看更多">查看更多∨</a></section>--%>
										</div>
									</c:if>
								</div>
								<!-- /课程介绍 -->
								<div class="c-g-content" style="display: none" id="courseList">
									<div class="lh-menu-wrap" id="courseKpointMenu"></div>
								</div>
								<!-- /课程大纲 -->
								<div id="courseComment" style="display: none" class="c-c-content">
									<div class="commentHtml" ></div>
								</div>

								<!-- /课程评论 -->
							</article>
						</div>
					</section>
				</article>
				<aside class="fl col-3">
					<div class="i-box">
						<div>
							<section class="c-infor-tabTitle c-tab-title">
								<span title="" href="javascript:void(0)">主讲讲师</span>
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
					<%--<div class="i-box mt25">
						<div  id="courseLearnedUserDiv">
							<section class="c-infor-tabTitle c-tab-title">
								<span title="" href="javascript:void(0)">学过此课的人（0）</span>
							</section>
							<section class="no-data-wrap"><em class="icon30 no-data-ico">&nbsp;</em><span class="c-666 fsize14 ml10 vam">还没有人学过此课程，快去学习吧...</span></section>
						</div>
					</div>--%>
					<div class="i-box mt25">
						<div>
							<section class="c-infor-tabTitle c-tab-title">
								<span title="" href="javascript:void(0)">推荐课程</span>
							</section>
							<c:if test="${empty courseList}">
								<section class="no-data-wrap">
									<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在整理中...</span>
								</section>
							</c:if>
							<c:if test="${not empty courseList}">
								<section class="comm-course-list of cou-in-r-list bg-fff">
									<ol>
										<c:forEach items="${courseList}" var="interfixCourse">
											<li>
												<div class="cc-l-wrap">
													<section class="course-img">
														<a href="${ctx}/front/couinfo/${interfixCourse.courseId}">
															<img class="img-responsive" xsrc="${staticServer}${interfixCourse.logo}" src="${staticServer}${interfixCourse.logo}" alt="">
														</a>
													</section>
													<div class="coure-bg-g">
														<h3 class="hLh30 txtOf mt5">
															<a class="course-title fsize16 c-333" href="${ctx}/front/couinfo/${interfixCourse.courseId}" title="${interfixCourse.title}">${interfixCourse.title}</a>
														</h3>
														<section class="mt10 hLh20 of">
															<span class="fr">
															<tt class="c-master fsize14 f-fM">￥${interfixCourse.currentPrice}</tt>
															</span>
															<span class="fl jgAttr c-ccc f-fA">
															<tt class="c-999 f-fA mr10">${interfixCourse.pageBuycount}人购买</tt>
															<tt class="c-999 f-fA span-2">${interfixCourse.pageViewcount}浏览</tt>
															</span>
														</section>
													</div>
												</div>
											</li>
										</c:forEach>

									</ol>
								</section>
							</c:if>
						</div>
					</div>
				</aside>
				<div class="clear"></div>
			</div>
		</section>
		<!-- /课程详情 结束 -->
	</div>
	<input type="hidden" id="isok" value="${isok}"/>
	<input type="hidden" id="currentprice" value="${course.currentPrice}"/>
	<%--评论课程id--%>
	<input type="hidden" id="otherId" value="${course.courseId}"/>
	<%--课程有效期到期时间--%>
	<input type="hidden" id="loseTimeTime" value="<fmt:formatDate value="${course.endTime}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/>"/>
	<%--有效期类型，0：到期时间，1：按天数--%>
	<input type="hidden" id="loseType" value="${course.loseType}"/>
	<%--//评论类型,类型2为课程--%>
	<input type="hidden" id="type" value="2"/>
	<%-- <script type="text/javascript" src="${ctx}/static/common/jquery-1.11.1.min.js"></script> --%>
	<script type="text/javascript" src="${ctx}/static/common/qrcode/jquery.qrcode.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/inxweb/front/courseInfo.js"></script>
	<script type="text/javascript" src="${ctx}/static/inxweb/comment/comment.js"></script>
	<script type="text/javascript" src="${ctx}/static/inxweb/js/parabola.js"></script>
	<script>
		$(function(){
			slideScroll("#live-box-in", "#live-box-in .lv-prev", "#live-box-in .lv-next", "#i-live-cou-list", 5, true); //套餐推荐滚动
			/*给导航加选中*/
			$("a[href$='/front/showcoulist']").parent().addClass("current");
		})
	</script>
</body>
</html>