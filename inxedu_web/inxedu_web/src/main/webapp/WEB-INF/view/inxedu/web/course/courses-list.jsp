<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>课程列表</title>
<script type="text/javascript" src="${ctx}/static/inxweb/front/course.js"></script>
</head> 
<body>
	<div id="aCoursesList" class="bg-f8 of">
		<div class="">
			<div class="container">
				<section class="path-wrap txtOf hLh30">
					<a class="c-999 fsize14" href="${ctx}" title="">首页</a>
					\
					<span class="c-333 fsize14">课程列表</span>
				</section>
				<section class="c-s-dl coures-option">
					<dl>
						<dt>
							<span class="c-333 fsize16">按专业</span>
						</dt>
						<dd class="c-s-dl-li">
							<ul class="clearfix">
								<li <c:if test="${queryCourse.subjectId==0}">class="current"</c:if>><a onclick="submitForm(1,0)" title="全部" href="javascript:void(0)">全部</a></li>
								<c:forEach items="${subjectList}" var="subject">
									<li <c:if test="${queryCourse.subjectId==subject.subjectId or subjectParentId==subject.subjectId}">class="current"</c:if>><a onclick="submitForm(1,${subject.subjectId})" title="${subject.subjectName}" href="javascript:void(0)">${subject.subjectName}</a></li>
								</c:forEach>
							</ul>
							<aside class="c-s-more">
								<a href="javascript: void(0)" title="" class="fsize14 c-master icon-gd">&nbsp;</a>
								<div class="m-a-box">
									<section class="m-a-b-inf">
										<div class="DT-arrow">
											<em>◆</em>
											<span>◆</span>
										</div>
										<div class="m-a-b-more">

										</div>
									</section>
								</div>
							</aside>
						</dd>
					</dl>
					<c:if test="${sonSubjectList!=null&&sonSubjectList.size()>0 }">
						<dl class="ejdl-box live-ejdl-b">
							<dt>
								<span class="c-999 fsize14 dis txtOf">${subjectParentName}：</span>
							</dt>
							<dd class="c-s-dl-li">
								<ul class="clearfix">
									<c:forEach items="${sonSubjectList}" var="subject">
										<li <c:if test="${queryCourse.subjectId==subject.subjectId}">class="current"</c:if>><a onclick="submitForm(1,${subject.subjectId})" title="${subject.subjectName}" href="javascript:void(0)">${subject.subjectName}</a></li>
									</c:forEach>
								</ul>
							</dd>
						</dl>
					</c:if>
					<c:if test="${serviceSwitch.member=='ON'}">
						<dl class="teach-yjdl">
							<dt> <span class="c-333 fsize16">按会员</span> </dt>
							<dd class="c-s-dl-li">
								<ul class="clearfix">
									<li <c:if test="${queryCourse.memberTypeId==0||empty queryCourse.memberTypeId}">class="current"</c:if>><a title="全部" onclick="memberType(0)" href="javascript:void(0)">全部</a></li>
									<c:forEach items="${memberTypeList}" var="memberType">
										<li <c:if test="${memberType.id==queryCourse.memberTypeId}">class="current"</c:if>><a title="${memberType.title}" onclick="memberType(${memberType.id})" href="javascript:void(0)">${memberType.title}</a></li>
									</c:forEach>
								</ul>
								<%--<aside class="c-s-more"> <a class="fsize14 c-master" title="" href="javascript: void(0)" >[展开]</a> </aside> --%>
							</dd>
						</dl>
					</c:if>
					<div class="clear"></div>
				</section>
				<%--<section class="new-all-cou-list">
					<ul class="clearfix">
						<li <c:if test="${queryCourse.subjectId==0}">class="current"</c:if>><a onclick="submitForm(1,0)" title="全部" href="javascript:void(0)">全部</a></li>
						<c:forEach items="${subjectList}" var="subject">
							<li <c:if test="${queryCourse.subjectId==subject.subjectId or subjectParentId==subject.subjectId}">class="current"</c:if>><a onclick="submitForm(1,${subject.subjectId})" title="${subject.subjectName}" href="javascript:void(0)">${subject.subjectName}</a></li>
						</c:forEach>
					</ul>
				</section>--%>
			</div>
		</div>
		<!-- /课程列表 开始 -->
		<section class="container">
			<section class="c-sort-box bg-fff c-sort-box-new">
				<div class="js-wrap ">
					<section class="clearfix">
						<ol class="js-tap clearfix fl">
							<li <c:if test="${queryCourse.order=='BYGONE'}">class="current bg-orange"</c:if>><a title="综合排序" onclick="submitForm(3,'BYGONE')" href="javascript:void(0)">综合排序</a></li>
							<li <c:if test="${queryCourse.order=='NEW'}">class="current bg-orange"</c:if>><a title="最新" onclick="submitForm(3,'NEW')" href="javascript:void(0)">最新</a></li>
							<li <c:if test="${queryCourse.order=='FOLLOW'}">class="current bg-orange"</c:if>><a title="最热" onclick="submitForm(3,'FOLLOW')" href="javascript:void(0)">最热</a></li>
							<li class="pr <c:if test="${queryCourse.order=='ASCENDING'||queryCourse.order=='DESCENDING'}">current</c:if>"><a title="价格" onclick="submitForm(4,'${queryCourse.order}')" href="javascript:void(0)">价格
								<%--<c:if test="${queryCourse.order!='ASCENDING'&&queryCourse.order!='DESCENDING' }"><em class="jg-icon icon14">&nbsp;</em></c:if>--%>
								<span class="c-master fsize14 f-fM">
									<c:if test="${queryCourse.order=='ASCENDING' }">↑</c:if>
									<c:if test="${queryCourse.order=='DESCENDING' }">↓</c:if>
								</span>
							</a>
								<%--<div class="c-l-jgpx pa">
									<a href="javascript:void(0)" onclick="submitForm(4,'DESCENDING')">由高到低</a>
									<a href="javascript:void(0)" onclick="submitForm(4,'ASCENDING')">由低到高</a>
								</div>--%>
							</li>
						</ol>
						<article class="fr">
							<c:if test="${serviceSwitch.PackageSwitch=='ON'}">
								<label onclick="selectPackage(this)" class="c-kcfl <c:if test="${queryCourse.sellType=='PACKAGE'}">check</c:if> ml20">
									<em class="icon14 c-chb-ico">
										<input type="checkbox" class="chen-c-fl">
									</em>
									<tt class="vam fsize14 c-666 f-fM">套 餐</tt>
								</label>
							</c:if>
							<label onclick="selectFree(this)" class="c-kcfl <c:if test="${queryCourse.isFree=='true'}">check</c:if>  ml20">
								<em class="icon14 c-chb-ico">
									<input value="true" type="checkbox" class="chen-c-fl">
								</em>
								<tt class="vam fsize14 c-666 f-fM">免 费</tt>
							</label>
						</article>
					</section>
				</div>
				<section class="mobile-option-box">
					<ul class="mo-op-titlist clearfix">
						<li class="kcfl-li">
							<a href="javascript:void (0)">
								<tt>
                                    <c:if test="${not empty currentSubject.subjectName}">${currentSubject.subjectName}</c:if>
                                    <c:if test="${empty currentSubject.subjectName}">课程专业</c:if>
                                </tt>
								<em class="icon16 m-o-t-fl-ico">&nbsp;</em>
							</a>
						</li>
						<li class="zhpx-li">
							<a href="javascript:void (0)">
                                <tt>
                                 <c:if test="${queryCourse.order=='BYGONE'}">综合排序</c:if>
                                 <c:if test="${queryCourse.order=='NEW'}">最新课程</c:if>
                                 <c:if test="${queryCourse.order=='FOLLOW'}">热门课程</c:if>
                                 <c:if test="${queryCourse.order=='DESCENDING'}">价格从高到低</c:if>
                                 <c:if test="${queryCourse.order=='ASCENDING'}">价格从低到高</c:if>
                                </tt>
								<em class="icon16 m-o-t-fl-ico">&nbsp;</em>
							</a>
						</li>
						<li class="sxtj-li">
							<a href="javascript:void (0)">
								<tt>筛选条件</tt>
								<em class="icon16 m-o-t-fl-ico">&nbsp;</em>
							</a>
						</li>
					</ul>
					<section class="kczy-box m-o-b-se pr">
						<ul class="kczy-list-a clearfix" id="kczy-a">
							<li
							<%--queryCourse的专业判断选中的专业 加current --%>
									class="<c:if test="${queryCourse.subjectId==0}">current</c:if> allSubject">
								<a
										onclick="submitForm(1,0)"
										title="全部" href="javascript:void(0)">全部
								</a>
							</li>
							<c:forEach items="${mobileSubjecs}" var="subject">
								<%--如果有父级id为一级专业--%>
								<c:if test="${subject.parentId==0}">
								<li
										<%--queryCourse的专业判断选中的专业 加current --%>
										<c:if test="${queryCourse.subjectId==subject.subjectId || subjectParentId==subject.subjectId}">class="current"</c:if>>
									<a
												onclick="mobileSubmitForm(${subject.subjectId})"
											title="${subject.subjectName}" href="javascript:void(0)">${subject.subjectName}
									</a>
								</li>
								</c:if>
							</c:forEach>
						</ul>
						<ul class="kczy-list-b clearfix" id="kczy-b">
								<c:forEach items="${mobileSubjecs}" var="subject">
									<%--如果没有父级id  为2级专业--%>
									<c:if test="${subject.parentId!=0}">
										<li class="childSubject subject${subject.parentId} <c:if test='${queryCourse.subjectId==subject.subjectId}'> current</c:if> "
												<%--根据queryCourse的专业的父节点判断2级节点的显示隐藏--%>
												<c:if test="${subject.parentId != parrentSubject.subjectId}"> style="display:none"</c:if>>
											<a onclick="mobileSubmitForm(${subject.subjectId})" title="${subject.subjectName}" href="javascript:void(0)">${subject.subjectName}</a>
										</li>
									</c:if>
								</c:forEach>

						</ul>
					</section>
					<section class="zhpx-box m-o-b-se">
						<ul class="zhpx-list clearfix" id="zhpx-list">
							<li <c:if test="${queryCourse.order=='BYGONE'}">class="current bg-orange"</c:if>>
								<a onclick="submitForm(3,'BYGONE')" href="javascript:void (0)">综合排序</a>
							</li>
							<li <c:if test="${queryCourse.order=='NEW'}">class="current bg-orange"</c:if>>
								<a onclick="submitForm(3,'NEW')" href="javascript:void (0)">最新课程</a>
							</li>
							<li <c:if test="${queryCourse.order=='FOLLOW'}">class="current bg-orange"</c:if>>
								<a onclick="submitForm(3,'FOLLOW')" href="javascript:void (0)">热门课程</a>
							</li>
							<li <c:if test="${queryCourse.order=='DESCENDING'}">class="current bg-orange"</c:if>>
								<a onclick="submitForm(4,'ASCENDING')" href="javascript:void (0)">价格从高到低</a>
							</li>
							<li <c:if test="${queryCourse.order=='ASCENDING'}">class="current bg-orange"</c:if>>
								<a onclick="submitForm(4,'DESCENDING')" href="javascript:void (0)">价格从低到高</a>
							</li>
						</ul>
					</section>
					<section class="sxtj-box m-o-b-se">
						<div class="fg-line fg-line-fist">
							<p class="fg-txt">课程类型</p>
							<ul class="sxtj-list clearfix" id="sxtj-list">
								<c:if test="${serviceSwitch.PackageSwitch=='ON'}">
									<li <c:if test="${queryCourse.sellType=='PACKAGE'}">class="check current"</c:if> onclick="selectPackage(this)">
										<a href="javascript:void (0)">套餐课程</a>
									</li>
								</c:if>
								<li <c:if test="${queryCourse.isFree=='true'}">class="check current"</c:if> onclick="selectFree(this)">
									<a href="javascript:void (0)">免费课程</a>
								</li>
							</ul>
						</div>
						<c:if test="${serviceSwitch.member=='ON'}">
							<div class="fg-line">
								<p class="fg-txt">会员分类</p>
								<ul class="sxtj-list clearfix">
									<li <c:if test="${queryCourse.memberTypeId==0||empty queryCourse.memberTypeId}">class="current"</c:if>><a title="全部" onclick="memberType(0,this)" href="javascript:void(0)">全部</a></li>
									<c:forEach items="${memberTypeList}" var="memberType">
										<li <c:if test="${memberType.id==queryCourse.memberTypeId}">class="current"</c:if>><a title="${memberType.title}" onclick="memberType(${memberType.id},this)" href="javascript:void(0)">${memberType.title}</a></li>
									</c:forEach>
								</ul>
							</div>
						</c:if>
						<div class="sxtj-n-box tar">
							<a href="javascript:void(0)" onclick="clearColumn()" class="cz-btn">重 置</a>
							<a href="javascript:void(0)" onclick="submitForm('#searchForm')" class="qd-btn">确 定</a>
						</div>
					</section>
					<div class="mo-op-body"></div>
				</section>
				<div class="couresnew-big">
					<c:if test="${empty courseList}">
						<!-- /无数据提示 开始-->
						<section class="no-data-wrap">
							<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
						</section>
						<!-- /无数据提示 结束-->
					</c:if>
					<c:if test="${not empty courseList}">
						<article class="comm-course-list">
							<ul class="of">
								<c:forEach items="${courseList}" var="course" varStatus="index">
									<%--如果不是套餐课--%>
									<c:if test="${course.sellType!='PACKAGE'}">
									<li>
										<div class="cc-l-wrap">
											<section class="course-img">
												<a href="${ctx}/front/couinfo/${course.courseId}" target="_blank">
													<c:choose>
														<c:when test="${not empty course.logo }">
															<img xSrc="${staticServer}${course.logo}" src="${ctx }/static/inxweb/img/coures.gif" class="img-responsive" alt="">
														</c:when>
														<c:otherwise>
															<img xSrc="${ctx}/static/inxweb/img/coures.gif" src="${ctx }/static/inxweb/img/coures.gif" class="img-responsive" alt="">
														</c:otherwise>
													</c:choose>
												</a>
												<%-- <div class="cc-mask">
													<a href="${ctx}/front/couinfo/${course.courseId}" title="" class="comm-btn c-btn-1">开始学习</a>
												</div>  --%>
											</section>
											<div class="coure-bg-g">
												<h3 class="hLh30 txtOf mt10">
													<a href="${ctx}/front/couinfo/${course.courseId}" target="_blank" title="${course.courseName}" class="course-title fsize18 c-333">${course.courseName}</a>
												</h3>
												<section class="mt10 hLh20 of">
													<c:if test="${course.currentPrice=='0.00' }">
														<span class="fr"><tt class="c-green fsize12 f-fA">免费</tt></span>
													</c:if>
													<c:if test="${course.currentPrice!='0.00' }">
														<span class="fr"><tt class="c-master fsize14 f-fM">￥${course.currentPrice }</tt></span>
													</c:if>
													<span class="fl jgAttr c-ccc f-fA">
														<tt class="c-999 f-fA mr10">${course.pageBuycount }人购买</tt>
														<tt class="c-999 f-fA span-2">${course.pageViewcount }浏览</tt>
													</span>
												</section>
											</div>
										</div>
									</li>
									</c:if>
									<%--如果是套餐课--%>
									<c:if test="${course.sellType=='PACKAGE'}">
									<li>
										<div class="cc-l-wrap">
											<section class="course-img">
												<a href="${ctx}/front/couinfo/${course.courseId}" target="_blank">
													<c:choose>
														<c:when test="${not empty course.logo }">
															<img xSrc="${staticServer}${course.logo}" src="${ctx }/static/inxweb/img/coures.gif" class="img-responsive" alt="">
														</c:when>
														<c:otherwise>
															<img xSrc="${ctx}/static/inxweb/img/coures.gif" src="${ctx }/static/inxweb/img/coures.gif" class="img-responsive" alt="">
														</c:otherwise>
													</c:choose>
												</a>
													<%-- <div class="cc-mask">
                                                        <a href="${ctx}/front/couinfo/${course.courseId}" title="" class="comm-btn c-btn-1">开始学习</a>
                                                    </div>  --%>
											</section>
											<div class="coure-bg-g">
												<h3 class="hLh30 txtOf mt10">
													<a href="${ctx}/front/couinfo/${course.courseId}" target="_blank" title="${course.courseName}" class="course-title fsize18 c-333">${course.courseName}</a>
												</h3>
												<section class="mt10 hLh20 of">
													<c:if test="${course.currentPrice=='0.00' }">
														<span class="fr"><tt class="c-green fsize12 f-fA">免费</tt></span>
													</c:if>
													<c:if test="${course.currentPrice!='0.00' }">
														<span class="fr"><tt class="c-master fsize14 f-fM">￥${course.currentPrice }</tt></span>
													</c:if>
													<span class="fl jgAttr c-ccc f-fA">
														<tt class="c-999 f-fA mr10">${course.pageBuycount }人购买</tt>
														<tt class="c-999 f-fA span-2">${course.pageViewcount }浏览</tt>
													</span>
												</section>
											</div>
											<div class="coures-jbicon">
												<img src="${ctx}/static/inxweb/img/c-tc-jb.png">
											</div>
										</div>
									</li>
									</c:if>
								</c:forEach>



							</ul>
							<div class="clear"></div>
						</article>
					</c:if>
				</div>

				<!-- 公共分页 开始 -->
				<jsp:include page="/WEB-INF/view/common/front_page.jsp" />
				<!-- 公共分页 结束 -->
				<form action="${ctx}/front/showcoulist" id="searchForm" method="post">
					<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
					<input type="hidden" name="queryCourse.teacherId" value="${queryCourse.teacherId}" />
					<input type="hidden" name="queryCourse.subjectId" value="${queryCourse.subjectId}" />
					<input type="hidden" name="queryCourse.order" value="${queryCourse.order}" />
					<input type="hidden" name="queryCourse.memberTypeId" value="${queryCourse.memberTypeId}" />
					<input type="hidden"id="package" name="queryCourse.sellType" value="${queryCourse.sellType}" />
					<input type="hidden" name="queryCourse.courseName" id="hiddenCourseName" value="${queryCourse.courseName}" />
					<input type="hidden" id="isFree" name="queryCourse.isFree" value="${queryCourse.isFree}" />
					<input id="noPackage" type="hidden" name="queryCourse.sellType_cou_pag" value="${queryCourse.sellType_cou_pag}" />
				</form>
			</section>
		</section>
		<!-- /课程列表 结束 -->
	</div>
<script>
   /* 专业父级id 用于判断专业筛选条件是否为2级 用于移动端*/
    var parentSubjectId = ${subjectParentId}+"";
</script>
</body>
</html>