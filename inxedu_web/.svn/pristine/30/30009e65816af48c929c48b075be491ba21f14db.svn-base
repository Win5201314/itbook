<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html> 
<html>
<head>
<title>直播列表</title>
	<script type="text/javascript" src="${ctx}/static/inxweb/live/live-list.js"></script>
</head>
<body>
	<div id="aCoursesList" class="bg-fa of">
		<section class="container">
			<section class="path-wrap txtOf hLh30">
				<a class="c-999 fsize14" href="${ctx}/front/liveIndex" title="">直播首页</a>
				\
				<span class="c-333 fsize14">直播列表</span>
			</section>
			<section class="c-sort-box">
				<section class="c-s-dl coures-option">
					<dl> 
						<dt> <span class="c-333 fsize16">按专业</span> </dt>
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
					<dl class="teach-yjdl">
						<dt> <span class="c-333 fsize16">按讲师</span> </dt>
						<dd class="c-s-dl-li"> 
							<ul class="clearfix"> 
								<li <c:if test="${queryCourse.teacherId==0}">class="current"</c:if>><a onclick="submitForm(2,0)" title="全部" href="javascript:void(0)">全部</a></li>
								<c:forEach items="${teacherList}" var="teacher">
									<li <c:if test="${teacher.id==queryCourse.teacherId}">class="current"</c:if>><a title="${teacher.name}" onclick="submitForm(2,${teacher.id})" href="javascript:void(0)">${teacher.name}</a></li>
								</c:forEach>
							</ul> 
						    <%--<aside class="c-s-more"> <a class="fsize14 c-master" title="" href="javascript: void(0)" >[展开]</a> </aside> --%>
						</dd> 
					</dl>
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
				<div class="clearfix">
					<div class="c-sort-box c-sort-box-live">
						<section class="mobile-option-box bg-fff">
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
											<c:if test="${not empty currentTeacher.name}">${currentTeacher.name}</c:if>
											<c:if test="${empty currentTeacher.name}">讲师</c:if>
										</tt>
										<em class="icon16 m-o-t-fl-ico">&nbsp;</em>
									</a>
								</li>
								<li class="sxtj-li">
									<a href="javascript:void (0)">
										<tt>
											筛选条件
											<%--<c:if test="${empty queryCourse.order}">筛选条件</c:if>
											<c:if test="${queryCourse.order=='BYGONE'}">综合排序</c:if>
											<c:if test="${queryCourse.order=='FOLLOW'}">关注度</c:if>
											<c:if test="${queryCourse.order=='NEW'}">最新</c:if>
											<c:if test="${queryCourse.order=='FREE'}">免费</c:if>
											<c:if test="${queryCourse.order=='ASCENDING'||queryCourse.order=='DESCENDING'}">价格</c:if>
											<c:if test="${queryCourse.order=='ASCENDING' }">↑</c:if>
											<c:if test="${queryCourse.order=='DESCENDING' }">↓</c:if>--%>
										</tt>
										<em class="icon16 m-o-t-fl-ico">&nbsp;</em>
									</a>
								</li>
							</ul>
							<section class="kczy-box m-o-b-se pr">
								<ul class="kczy-list-a" id="kczy-a">
									<li
									<%--queryCourse的专业判断选中的专业 加current --%>
											class="<c:if test="${queryCourse.subjectId==0}">current</c:if> allSubject">
										<a
												onclick="mobileSubmitForm(0)"
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
								<ul class="kczy-list-b" id="kczy-b">
									<c:forEach items="${mobileSubjecs}" var="subject">
										<%--如果没有父级id  为2级专业--%>
										<c:if test="${subject.parentId!=0}">
											<li class="childSubject subject${subject.parentId} <c:if test='${queryCourse.subjectId==subject.subjectId}'> current</c:if> "
												<%--根据queryCourse的专业的父节点判断2级节点的显示隐藏--%>
													<c:if test="${subject.parentId != parrentSubject.parentId}"> style="display:none"</c:if>>
												<a onclick="mobileSubmitForm(${subject.subjectId})" title="${subject.subjectName}" href="javascript:void(0)">${subject.subjectName}</a>
											</li>
										</c:if>
									</c:forEach>

								</ul>
							</section>
							<section class="zhpx-box m-o-b-se">
								<ul class="zhpx-list" id="zhpx-list">
									<li <c:if test="${queryCourse.teacherId==0}">class="current"</c:if>><a onclick="submitForm(2,0)" title="全部" href="javascript:void(0)">全部</a></li>
									<c:forEach items="${teacherList}" var="teacher">
										<li <c:if test="${teacher.id==queryCourse.teacherId}">class="current"</c:if>><a title="${teacher.name}" onclick="submitForm(2,${teacher.id})" href="javascript:void(0)">${teacher.name}</a></li>
									</c:forEach>
								</ul>
							</section>
							<section class="sxtj-box m-o-b-se">
								<div class="fg-line fg-line-fist">
									<p class="fg-txt">状态</p>
									<ul class="sxtj-list clearfix">
										<li class="check condition2 <c:if test="${queryCourse.isLiveing=='true'}">current</c:if>" conditionVal="true" onclick="checkboxChecked(this,'isLiveing')">
											<a href="javascript:void (0)">正在直播</a>
										</li>
										<li class="check condition2 <c:if test="${queryCourse.isFree=='true'}">current</c:if>" conditionVal="true" onclick="checkboxChecked(this,'isFree')">
											<a href="javascript:void (0)">免费直播</a>
										</li>
									</ul>
								</div>
								<div class="fg-line">
									<p class="fg-txt">价格与排序</p>
									<ul class="sxtj-list clearfix">
										<li class="check condition3 <c:if test="${queryCourse.order=='BYGONE'}">current</c:if>" conditionVal="BYGONE" onclick="checkboxChecked(this,'queryOrder','condition3')">
											<a href="javascript:void (0)">综合排序</a>
										</li>
										<li class="check condition3 <c:if test="${queryCourse.order=='NEW'}">current</c:if>" conditionVal="NEW" onclick="checkboxChecked(this,'queryOrder','condition3')">
											<a href="javascript:void (0)">最新</a>
										</li>
										<li class="check condition3 <c:if test="${queryCourse.order=='FOLLOW'}">current</c:if>" conditionVal="FOLLOW" onclick="checkboxChecked(this,'queryOrder','condition3')">
											<a href="javascript:void (0)">热门</a>
										</li>
										<li class="check condition3 <c:if test="${queryCourse.order=='DESCENDING'}">current</c:if>" conditionVal="DESCENDING" onclick="checkboxChecked(this,'queryOrder','condition3')">
											<a href="javascript:void (0)">价格从高到低</a>
										</li>
										<li class="check condition3 <c:if test="${queryCourse.order=='ASCENDING'}">current</c:if>" conditionVal="ASCENDING" onclick="checkboxChecked(this,'queryOrder','condition3')">
											<a href="javascript:void (0)">价格从低到高</a>
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
									<a href='javascript:searchReset();' class="cz-btn">重 置</a>
									<a href='javascript:$("#searchForm").submit();' class="qd-btn">确 定</a>
								</div>
							</section>
							<div class="mo-op-body"></div>
						</section>
						<section class="live-l-new-cx">
							<div class="js-wrap clearfix">
								<%--<section class="fr">
									<span class="c-ccc"> <tt class="c-master f-fM">${page.currentPage}</tt>/<tt class="c-666 f-fM">${page.totalPageSize}</tt> </span>
								</section> --%>
								<section class="fl"> 
									<ol class="js-tap clearfix">
										<li <c:if test="${queryCourse.order=='BYGONE'}">class="current bg-orange"</c:if>><a title="综合排序" onclick="submitForm(3,'BYGONE')" href="javascript:void(0)">综合排序</a></li>
											<li <c:if test="${queryCourse.order=='NEW'}">class="current bg-orange"</c:if>><a title="最新" onclick="submitForm(3,'NEW')" href="javascript:void(0)">最新</a></li>
											<li <c:if test="${queryCourse.order=='FOLLOW'}">class="current bg-orange"</c:if>><a title="热门" onclick="submitForm(3,'FOLLOW')" href="javascript:void(0)">热门</a></li>
											<%--<li <c:if test="${queryCourse.order=='FREE'}">class="current bg-orange"</c:if>><a title="免费" onclick="submitForm(3,'FREE')" href="javascript:void(0)">免费</a></li>--%>
											<li <c:if test="${queryCourse.order=='ASCENDING'||queryCourse.order=='DESCENDING'}">class="current bg-orange"</c:if>><a title="价格" onclick="submitForm(4,'<c:if test="${not empty queryCourse.order}">${queryCourse.order }</c:if><c:if test="${empty queryCourse.order}">ONE</c:if>')" href="javascript:void(0)">价格<span><c:if test="${queryCourse.order=='ASCENDING' }">↑</c:if><c:if test="${queryCourse.order=='DESCENDING' }">↓</c:if></span></a></li>
									</ol>
								</section>
								<article class="fr">
									<label onclick="selectByCheckbox(this,'isLiveing')" class="c-kcfl <c:if test="${queryCourse.isLiveing=='true'}">check</c:if> ml20">
										<em class="icon14 c-chb-ico">
											<input type="checkbox" class="chen-c-fl">
										</em>
										<tt class="vam fsize14 c-666 f-fM">正在直播</tt>
									</label>
									<label onclick="selectByCheckbox(this,'isFree')" class="c-kcfl <c:if test="${queryCourse.isFree=='true'}">check</c:if>  ml20">
										<em class="icon14 c-chb-ico">
											<input value="true" type="checkbox" class="chen-c-fl">
										</em>
										<tt class="vam fsize14 c-666 f-fM">免费直播</tt>
									</label>
								</article>
							</div>
							<div class="couresnew-big bg-fff">
								<div class="comm-course-list of co-lv-list">
									<c:if test="${empty courseList}">
										<!-- /无数据提示 开始-->
										<section class="no-data-wrap">
											<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
										</section>
										<!-- /无数据提示 结束-->
									</c:if>
									<c:if test="${not empty courseList}">
										<ul class="of">
											<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
											<c:forEach items="${courseList}" var="course" varStatus="index">
												<c:set var="beginDate">
													<fmt:formatDate value="${course.liveBeginTime}" pattern="yyyy-MM-dd" type="date"/>
												</c:set>
												<c:set var="endDate">
													<fmt:formatDate value="${course.liveEndTime}" pattern="yyyy-MM-dd" type="date"/>
												</c:set>
												<li <c:if test="${nowDate-course.liveEndTime.time < 0}">class="current"</c:if> >
													<div class="cc-l-wrap">
														<section class="course-img">
															<a href="${ctx}/front/couinfo/${course.courseId}">
																<c:if test="${not empty course.logo }">
																	<img xsrc="${staticServer}${course.logo }" src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${course.courseName }">
																</c:if>
																<c:if test="${empty course.logo }">
																	<img xsrc="${ctx}/static/inxweb/img/coures.gif" src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${course.courseName }">
																</c:if>
															</a>
															<div class="l-pic-name">
																<h3 class="hLh30 txtOf c-fff">
																	<a href="${ctx}/front/couinfo/${course.courseId}" title="${course.courseName }" class="course-title fsize16 c-fff">${course.courseName }</a>
																</h3>
															</div>
																<%-- <div class="cc-mask">
                                                                    <a href="${ctx}/front/couinfo/${course.courseId}" title="开始学习" class="comm-btn c-btn-1">开始学习</a>
                                                                </div> --%>
														</section>
														<div class="coure-bg-g">
															<section class="hLh20 of mt5">
																<span class="f-fM c-666 fsize14">
																	<c:if test="${not empty course.courseKpointList[0].liveBeginTime}">
																		<fmt:formatDate value="${course.courseKpointList[0].liveBeginTime}" pattern="yyyy-MM-dd HH:mm" type="date"/> 开播
																	</c:if>
																	<c:if test="${empty course.courseKpointList[0].liveBeginTime}">
																		没有直播
																	</c:if>
																</span>
															</section>
															<section class="mt10 hLh20 of">
																<c:if test="${course.currentPrice=='0.00' }">
																	<span class="fr"><tt class="c-green fsize12 f-fA">免费</tt></span>
																</c:if>
																<c:if test="${course.currentPrice!='0.00' }">
																	<span class="fr"><tt class="c-master fsize14 f-fM">￥${course.currentPrice }</tt></span>
																</c:if>
																<span class="fl lv-img-pic">
																	<a class="live-zt-ing" href="${ctx}/front/couinfo/${course.courseId}">
																		<c:if test="${course.courseKpointList[0].liveBeginTime.time<nowDate and course.courseKpointList[0].liveEndTime.time>nowDate }"><img src="${ctx}/static/inxweb/img/live-ing.gif"></c:if>
																		<c:if test="${course.courseKpointList[0].liveBeginTime.time>nowDate and course.courseKpointList[0].liveEndTime.time>nowDate }"><img src="${ctx}/static/inxweb/img/live-wks.png"></c:if>
																		<c:if test="${course.courseKpointList[0].liveEndTime.time <nowDate}"><img src="${ctx}/static/inxweb/img/live-over.png"></c:if>
																	</a>
																</span>
															</section>
														</div>
													</div>
												</li>
											</c:forEach>
										</ul>
										<%--<ul>
                                            <c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
                                            <c:forEach items="${courseList}" var="course" varStatus="index">
                                                <c:set var="beginDate">
                                                    <fmt:formatDate value="${course.liveBeginTime}" pattern="yyyy-MM-dd" type="date"/>
                                                </c:set>
                                                <c:set var="endDate">
                                                    <fmt:formatDate value="${course.liveEndTime}" pattern="yyyy-MM-dd" type="date"/>
                                                </c:set>
                                                <li <c:if test="${nowDate-course.liveEndTime.time < 0}">class="current"</c:if> >
                                                    <div class="live-cb-wrap">
                                                        <div class="clearfix live-cb-box">
                                                            <aside class="live-pic">
                                                                <c:choose>
                                                                    <c:when test="${not empty course.logo }">
                                                                        <img xSrc="${staticServer}${course.logo}" src="${ctx }/static/inxweb/img/coures.gif" class="img-responsive" alt="${course.courseName}">
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <img xSrc="/static/inxweb/img/coures.gif" src="${ctx }/static/inxweb/img/coures.gif" class="img-responsive" alt="${course.courseName}">
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </aside>
                                                            <h3 class="hLh30 txtOf"> <a class="live-course-title" title="" href="${ctx}/front/couinfo/${course.courseId}">${course.courseName}</a> </h3>
                                                            <div class="mt15"><span class="c-666 fsize14">购买数：${course.pageBuycount }</span><span class="c-master fsize14 ml30">￥${course.currentPrice }</span></div>
                                                            <section class="i-q-txt mt15"> <p> <span class="c-999 f-fA">${course.title }</span> </p> </section>
                                                        </div>
                                                        <div class="live-cou-time clearfix">
                                                            <div class="fl">
                                                                <c:if test="${beginDate == endDate}">
                                                                    <span class="fsize28"><fmt:formatDate value="${course.liveBeginTime}" pattern="MM月dd日" type="date"/></span>
                                                                    &lt;%&ndash;<span class="ml20 fsize16">
                                                                        <fmt:formatDate value="${course.liveBeginTime}" pattern="HH:mm" type="date"/>&nbsp;~&nbsp;<fmt:formatDate value="${course.liveEndTime}" pattern="HH:mm" type="date"/>
                                                                    </span>&ndash;%&gt;
                                                                </c:if>
                                                                <c:if test="${beginDate != endDate}">
                                                                    <span class="fsize28"><fmt:formatDate value="${course.liveBeginTime}" pattern="MM月dd日" type="date"/></span>
                                                                    &lt;%&ndash;<span class="ml20 fsize16">
                                                                        <fmt:formatDate value="${course.liveBeginTime}" pattern="HH:mm" type="date"/>
                                                                    </span>&ndash;%&gt;
                                                                    <span class="ml20 fsize16">~</span>
                                                                    <span class="ml20 fsize28"><fmt:formatDate value="${course.liveEndTime}" pattern="MM月dd日" type="date"/></span>
                                                                    &lt;%&ndash;<span class="ml20 fsize16">
                                                                        <fmt:formatDate value="${course.liveEndTime}" pattern="HH:mm" type="date"/>
                                                                    </span>&ndash;%&gt;
                                                                </c:if>
                                                            </div>
                                                            <div class="fr"><a href="${ctx}/front/couinfo/${course.courseId}">
                                                                <c:if test="${nowDate-course.liveEndTime.time < 0 and nowDate-course.liveBeginTime.time > 0 }">加入直播</c:if>
                                                                <c:if test="${nowDate-course.liveEndTime.time < 0 and nowDate-course.liveBeginTime.time < 0 }">加入直播</c:if>
                                                                <c:if test="${nowDate-course.liveEndTime.time > 0}">已结束</c:if>
                                                            </a></div>
                                                        </div>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>--%>
										<!-- 公共分页 开始 -->
										<jsp:include page="/WEB-INF/view/common/front_page.jsp" />
										<!-- 公共分页 结束 -->
									</c:if>
									<form action="${ctx}/front/showLivelist" id="searchForm" method="post">
										<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
										<input type="hidden" name="queryCourse.teacherId" value="${queryCourse.teacherId}" />
										<input type="hidden" name="queryCourse.subjectId" value="${queryCourse.subjectId}" />
										<input type="hidden" id="queryOrder" name="queryCourse.order" value="${queryCourse.order}" />
										<input type="hidden" name="queryCourse.memberTypeId" value="${queryCourse.memberTypeId}" />
										<input type="hidden" id="isFree" name="queryCourse.isFree" value="${queryCourse.isFree}" />
										<input type="hidden" id="isLiveing" name="queryCourse.isLiveing" value="${queryCourse.isLiveing}" />
										<input type="hidden" name="queryCourse.courseName" id="hiddenCourseName" value="${queryCourse.courseName}" />
									</form>
								</div>
							</div>
						</section>
					</div>
					<%--<div class="fl col-3">
						<div class="i-box ml20">
							<section class="c-infor-tabTitle c-tab-title">
								<span title="" href="javascript:void(0)">推荐直播</span>
							</section>
							<c:if test="${empty mapCourseList}">
								<!-- /无数据提示 开始-->
								<section class="no-data-wrap">
									<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
								</section>
								<!-- /无数据提示 结束-->
							</c:if>
							<c:if test="${not empty mapCourseList}">
								<ul class="live-cou-rec1">
									<c:forEach items="${mapCourseList.recommen_33}" var="course">
										<li>
											<div class="live-cou-wrap1">
												<a href="${ctx}/front/couinfo/${course.courseId}">
													<c:choose>
														<c:when test="${not empty course.logo }">
															<img xSrc="${staticServer}${course.logo}" src="${ctx }/static/inxweb/img/coures.gif" class="img-responsive" alt="${course.courseName}">
														</c:when>
														<c:otherwise>
															<img xSrc="/static/inxweb/img/coures.gif" src="${ctx }/static/inxweb/img/coures.gif" class="img-responsive" alt="${course.courseName}">
														</c:otherwise>
													</c:choose>
												</a>
												<h3 class="mt10"><a class="hLh30 fsize14 txtOf c-666" href="${ctx}/front/couinfo/${course.courseId}">${course.courseName}</a></h3>
											</div>
										</li>
									</c:forEach>
								</ul>
							</c:if>
						</div>
					</div>--%>
				</div>
			</section>
		</section>
	</div>
</body>
</html>