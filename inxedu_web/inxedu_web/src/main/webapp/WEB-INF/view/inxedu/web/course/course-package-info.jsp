<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>${course.courseName}详情</title>
<%--<script src="http://vod.baofengcloud.com/html/script/bfcloud.js?v=2"></script>--%>
</head>
<body>
	<div id="aCoursesList" class="bg-f8 of">
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
	
						<%--<a href="javascript:void(0)" onclick="vedioClick(${freeVideoId})" title="${course.courseName}" class="v-play-btn">
							<em class="icon30">&nbsp;</em>
						</a>--%>
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
										<tt class="c-666 f-fM fsize14 mr5 vam">课时数</tt>
										<tt class="c-333 f-fM fsize14 vam">${course.lessionNum }</tt>
									</aside>
								</li>
								<li>
									<aside>
										<em class="icon16 lls-ico">&nbsp;</em>
										<tt class="c-666 f-fM fsize14 mr5 vam">浏览数</tt>
										<tt class="c-333 f-fM fsize14 vam">${course.pageViewcount}</tt>
									</aside>
								</li>
							</ol>
						</section>
						<section class="c-attr-mt hLh30 of mt10">
							<tt class="c-999 fsize14 f-fM vam">主讲：</tt>
							<c:forEach items="${teacherList }" var="tea">
								<tt class="c-999 fsize14 f-fM vam">
								<%-- <a href="${ctx}/front/teacher/${tea.id }">${tea.name }</a>&nbsp;&nbsp;&nbsp; --%>
								${tea.name}
								</tt>
							</c:forEach>
						</section>
						<section class="c-attr-mt mt10">
							<tt class="c-999 fsize14 f-fM vam">套餐课有效期：</tt>
							<tt class="c-999 fsize14 f-fM vam">
								<c:if test="${course.loseType==0 }">
									<fmt:formatDate pattern="yyyy/MM/dd HH:mm"  value="${course.endTime}" />
								</c:if>
							</tt>
							<span class="c-999 fsize14 f-fM vam">
								<c:if test="${course.loseType==1 }">
									从购买之日起<tt class="c-master fsize16"> ${course.loseTime}</tt> 天
								</c:if>
							</span>
						</section>
						<section class="c-attr-jg mt15">
							<span class="c-666">价格：</span><big class="c-red">￥${course.currentPrice}</big> <span class="c-ccc ml10">原价：<s>￥${course.sourcePrice }</s></span>
						</section>
						<section class="c-attr-mt of mt15">
							<c:if test="${isFavorites==true }">
								<a title="取消收藏" href="javascript:void(0)" class="vam fl cou-add-fav sc-end f-fM" onclick="deleteFaveorite(${course.courseId},this)"><em class="icon18 scIcon mr5">&nbsp;</em><tt class="c-master fsize14 vam f-fM">取消收藏</tt></a>
							</c:if>
							<c:if test="${isFavorites!=true }">
								<a title="收藏" href="javascript:void(0)" class="vam fl cou-add-fav f-fM" onclick="favorites(${course.courseId},this)"><em class="icon18 mr5 scIcon">&nbsp;</em><tt class="c-666 fsize14 vam f-fM">收藏</tt></a>
							</c:if>
							<section class="kcShare pr fl hand vam ml20">
								<tt>
									<em class="icon18 shareIcon"></em><span class="vam c-666 fsize14 f-fM">分享</span>
								</tt>
								<div id="bdshare" class="bdsharebuttonbox"><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a href="#" class="bds_douban" data-cmd="douban" title="分享到豆瓣网"></a><a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a><a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a></div>
								<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
							</section>
						</section>
						<section class="c-attr-mt new-cours-btnbox mt30">
							<c:choose>
								<c:when test="${isok==false && course.currentPrice>0}">
									<a href="javascript:void(0)" id="cou-shopcar" title="购买" class="comm-btn c-btn-3 cou-buy-btn" onclick="buyNow('${course.courseId}')">购买套餐课</a>
									<section class="ml15 c-shop-car-wrap disIb">
										<a class=" c-fff f-fM btnCart" id="cou-shopcar" href="javascript:void(0)" onclick="addShoppingCart('${course.courseId}')">加入购物车</a>
										<div class="fly_item" id="flyItem">
											<img width="50" height="50" src="/static/inxweb/img/avatar-boy.gif">
										</div>
									</section>
								</c:when>
								<c:otherwise>
									<c:if test="${firstCourseId==0}">
										<a href="javascript:void(0)" id="cou-shopcar" title="暂无课程" class="comm-btn c-btn-3 cou-nocou-btn">暂无课程</a>
									</c:if>
									<c:if test="${firstCourseId!=0}">
										<a href="javascript:void(0)" id="cou-shopcar" title="立即观看" onclick="if(isLogin()){ window.location.href='/uc/play/${firstCourseId }/0'} else{lrFun();} " class="comm-btn c-btn-3 cou-buy-btn">立即观看</a>
									</c:if>
								</c:otherwise>
							</c:choose>
						</section>
					</section>
				</aside>
				<div class="clear"></div>
				<div class="coures-jbicon c-jb-xq">
					<img src="${ctx}/static/inxweb/img/c-tc-jb-big.png">
				</div>
			</div>
			<!-- /课程封面介绍 -->
			<div class="mt20 c-infor-box">
				<article class="fl col-7">
					<section class="mr30">
						<%--<div class="i-box coursePackge ">
							<div class="clearfix c-infor-tabTitle c-tab-title">
								<section id="c-i-tabTitle" class=" fl">
									<span title="" href="javascript:void(0)">课程套餐推荐</span>
								</section>
							</div>
							<div>
								<section class="tctj-warp" id="live-box-in">
									<article class="comm-course-list of" id="i-live-cou-list">
										<ul class="of">
											<c:forEach items="${courseDtoList}" var="courseDto">
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
																<tt class="c-999 f-fA">${courseDto.pageViewcount}浏览</tt>
															</span>
															</section>
														</div>
													</div>
												</li>
											</c:forEach>
										</ul>
									</article>
									<c:if test="${fn:length(courseDtoList)>3}">
									<a class="lv-prev lv-contrl" href="javascript: void(0)">prev</a>
									<a class="lv-next lv-contrl" href="javascript: void(0)">next</a>
									</c:if>
								</section>
								<section class="c-in-tc-jg clearfix">
									<div class="fl jg-box mt10">
										<span class="disIb vam fsize14 c-666 f-fM"><tt class="fsize16 c-666 f-fM">共</tt><tt class="fsize18 c-red f-fM ml5 mr5">${fn:length(courseDtoList)}</tt><tt class="fsize16 c-666 f-fM">门课程</tt></span>
										<span class="disIb vam ml40"><tt class="fsize16 c-666 f-fM">套餐价：￥</tt><tt class="fsize24 c-red f-fM">${course.currentPrice}</tt></span>
									</div>
									<div class="fr jg-btn">
										<a href="javascript:void (0)" onclick="buyPackge()">购买套餐</a>
									</div>
								</section>
							</div>
						</div>--%>
						<div class="i-box cou-in-boc ">
							<div class="clearfix c-infor-tabTitle c-tab-title">
								<section id="c-i-tabTitle" class="fl">
									<a  class="current" onclick="selectCourseInfo('courseContext')" title="套餐课详情" href="javascript: void(0)">套餐课详情</a>
									<a  title="套餐课列表" onclick="selectCourseInfo('courseList')" href="javascript: void(0)">套餐课列表</a>
									<a  title="套餐课评论" onclick="selectCourseInfo('courseComment')"href="javascript: void(0)">套餐课评论</a>
								</section>
								<%--<section class="fr m-check-box">
									<a href="javascript:;"><span class="fsize16 vam">手机查看</span><img width="18" height="18" class="vam ml5" src="/static/exam/images/weixin.jpg"><em class="icon14 ml5 phone-check"> </em></a>
									<div id="output" class="m-ewm"></div>
								</section>--%>
							</div>
							<article class="ml10 mr10 new-c-i-b-warp">
									<div id="courseContext">
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
								<div class=""style="display: none" id="courseList">
									<%--<h6 class="c-g-content c-infor-title">
										<span>课程列表</span>
									</h6>--%>
									<section class="">
										<div class="lh-menu-wrap" >
											<section class="c-pk-list-bx">
												<ul class="c-c-l">
													<c:if test="${empty courseDtoList}">
														<!-- /无数据提示 开始-->
														<section class="no-data-wrap">
															<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
														</section>
														<!-- /无数据提示 结束-->
													</c:if>
													<c:forEach items="${courseDtoList}" var="course">
														<li>
															<section class="c-c-img">
																<a target="_blank" href="${ctx}/front/couinfo/${course.courseId}">
																	<c:choose>
																		<c:when test="${not empty course.logo }">
																			<img width="154" height="116" xSrc="${staticServer}${course.logo}" src="${staticServer}${course.logo}" alt="">
																		</c:when>
																		<c:otherwise>
																			<img width="154" height="116" xSrc="/static/inxweb/img/coures.gif" src="${ctx }/static/inxweb/img/coures.gif" alt="">
																		</c:otherwise>
																	</c:choose>
																</a>
															</section>
															<h4 class="hLh20 txtOf unFw">
																<a href="${ctx}/front/couinfo/${course.courseId}" target="_blank" title="${course.courseName}" class="c-333 fsize18 f-fM">${course.courseName}</a>
															</h4>
															<div class="s-c-desc">
																<p class="c-999 f-fM fsize12">${course.title}</p>
															</div>
															<div class="pac-cou-js">
																<span class="vam">
																	<tt class="vam fsize12 c-666 f-fM">课时：</tt>
																	<tt class="vam fsize12 c-666 f-fM">${course.lessionNum}节</tt>
																</span>
																<span class="vam ml30">
																	<tt class="vam fsize12 c-666 f-fM">单价：</tt>
																	<tt class="vam fsize16 c-master f-fM">￥${course.currentPrice}</tt>
																</span>
															</div>
															<section class="c-pk-st tar">
																<c:if test="${isok==true or course.currentPrice<0}">
																	<a class="goSt" title="查看详情" href="javascript:void(0)" onclick="if(isLogin()){ window.location.href='/uc/play/${course.courseId}'} else{lrFun();} ">查看详情</a>
																</c:if>
															</section>
														</li>
													</c:forEach>
												</ul>
											</section>
										</div>
									</section>
								</div>
								<!-- /课程大纲 -->
								<div id="courseComment" style="display: none">
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
								<span>主讲讲师</span>
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
					<%--<div class="i-box mt20">
						<div>
							<section class="c-infor-tabTitle c-tab-title">
								<span>购买此套餐课的人（${queryTrxorderDetailList.size()}）</span>
							</section>
							<!-- /无数据提示 开始-->
							<c:if test="${empty queryTrxorderDetailList}">
								<section class="no-data-wrap">
									<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据...</span>
								</section>
							</c:if>
							<!-- /无数据提示 结束-->
							<c:if test="${not empty queryTrxorderDetailList}">
								<section class="buy-cin-list">
									<c:forEach items="${queryTrxorderDetailList}" var="queryTrxorderDetail">
										<span title="">
											<c:if test="${empty queryTrxorderDetail.userPicImg}">
												<img src="${ctx }/static/inxweb/img/avatar-boy.gif" alt="">
											</c:if>
											<c:if test="${!empty queryTrxorderDetail.userPicImg}">
												<img src="${staticServer}${queryTrxorderDetail.userPicImg }" alt="">
											</c:if>
											<tt class="c-999">
												<c:if test="${!empty queryTrxorderDetail.displayName}">${queryTrxorderDetail.displayName}</c:if>
											</tt>
										</span>
									</c:forEach>
								</section>
							</c:if>
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
					<%--<div class="i-box mt20">
						<div>
							<section class="c-infor-tabTitle c-tab-title">
								<span>推荐课程</span>
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
													<span class="c-999">讲师： <c:if test="${not empty interfixCourse.teacherListMap}">
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
					</div>--%>
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
			slideScroll("#live-box-in", "#live-box-in .lv-prev", "#live-box-in .lv-next", "#i-live-cou-list", 5, true); //套餐课推荐滚动

			/*给导航加选中*/
            $("a[href$='/front/showcoulist']").parent().addClass("current");
		})
	</script>
<script>
	/*购买套餐课程*/
	function buyPackge() {
		var courseId = [];
		var currentPrice =[];
		var endTime = [];
		/*判断套餐是否都为免费课程*/
		if ('${count}'==0){
			dialog('提示信息',"套餐课程都为免费课程！",1);
			return;
		}
		$(".courseId").each(function () {
			courseId.push($(this).val())
		});
		$(".endTime").each(function () {
			endTime.push($(this).val())
		});
		$(".currentPrice").each(function () {
			currentPrice.push($(this).val());
		});
		/*判断套餐课程是否都为过期课程*/
		var ifTime = false;
		for (var k=0;k<endTime.length;k++){
			var date = endTime[k].split("-");

			if (new Date(date[0],parseInt(date[1])-1,date[2])>new Date().getDate()){
				ifTime = true;
			}
		}
		/*把课程放入购物车*/
		for (var i=0;i<courseId.length;i++){
			var date = endTime[i].split("-");
			if (currentPrice[i]!=0&&new Date(date[0],parseInt(date[1])-1,date[2])>new Date().getDate()){
				$.ajax({//验证课程金额
					url:baselocation+"/shopcart/ajax/add",
					data:{
						"goodsid":courseId[i],
						"type":"1"
					},
					type:"post",
					dataType:"json",
					success:function(result){

					}
				})
			}
		}

		var time = setInterval(function () {
			clearInterval(time);
			if (ifTime==false){
				dialog('提示信息',"套餐课所有课程已过期！",1);

			}else {
				/*跳转到结账页面*/
				window.location.href="/shopcart?command=queryShopCart";
			}
		},100)

	}
</script>
</body>
</html>