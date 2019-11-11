<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${empty courseDtoList}">
	<table class="table_sche" cellspacing="0" cellpadding="0" border="0" width="100%">
		<tbody>
		<tr>
			<td class="col-4">
				<section class="no-data-wrap">
					<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
				</section>
			</td>
		</tr>
		</tbody>
	</table>
</c:if>
<c:if test="${not empty courseDtoList}">
	<section class="mt20" id="coursePackageList">
		<div class="lh-menu-wrap" >
			<section class="c-pk-list-bx">
				<ul class="c-c-l">
					<c:forEach items="${courseDtoList}" var="course">
						<dl>
						<dd class="u-order-list">
							<%--<section class="fl c-c-img">
								<a target="_blank" href="${ctx}/uc/play/${course.courseId}">
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
								<a href="${ctx}/uc/play/${course.courseId}" target="_blank" title="${course.courseName}" class="c-666 fsize18 f-fM">${course.courseName}</a>
							</h4>
							<div class="s-c-desc pt10 pb10">
								<p class="c-999">${course.title}</p>
							</div>
							<div class="pac-cou-js c-999 fsize14"> <span class="vam">课时：</span> <span class="ml5 vam">${course.lessionNum}节</span> </div>
							<aside class="c-pk-attr clearfix">
								<section class="c-pk-st tar">
									<a class="goSt" title="开始学习" target="_blank" href="${ctx}/uc/play/${course.courseId}/0">开始学习</a>
								</section>
								<section class="tar mr10 pak-cou-price">
									<label>
										&lt;%&ndash;<span class="inpCb unable hand">
											<input type="checkbox" courseprice61="0.01" value="89" name="singleCourse61" disabled="disabled" checked="checked" class="c-icon"> </span>&ndash;%&gt;
										<span class="vam ml10"> <small class="c-red fsize12">￥</small> <small class="c-red fsize18">${course.currentPrice}</small> </span>
									</label>
								</section>
							</aside>--%>
							<section class="w50pre disIb vam u-o-left">
								<div class="u-o-l-infor">
									<div class="c-cou-item">
										<div class="">
											<div>
												<a class="u-ol-pic u-ol-pic-new" href="${ctx}/uc/courseInfo/${course.courseId}" title="">
													<c:choose>
														<c:when test="${not empty course.logo}">
															<img src="${staticServer}${course.logo}" class="img-responsive" alt="${course.courseName}" />
														</c:when>
														<c:otherwise>
															<img src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${course.courseName}" />
														</c:otherwise>
													</c:choose>
												</a>
												<h6 class="hLh30 txtOf name">
													<a href="${ctx}/uc/play/${course.courseId}/0" target="_blank" title="${course.courseName}" class="fsize16 c-333">
														<c:if test="${course.sellType=='COURSE'}">
															<tt class="c-master fsize16 f-fM">[ 录播课 ]&nbsp;</tt>
														</c:if>
														<c:if test="${course.sellType=='LIVE'}">
															<tt class="c-green fsize14 f-fM ">[ 直播课 ]&nbsp;</tt>
														</c:if>
															${course.courseName}</a>
												</h6>
												<section class="hLh20 mt10 of">
                                                        <span class="fl">
                                                            <tt class="fsize13 c-999 f-fM vam">课时：</tt>
                                                            <tt class="fsize13 c-999 f-fM vam">${course.lessionNum}</tt>
                                                        </span>
													<span class="fr">
                                                               <c:set var="nowDate">
																   <fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd " type="date"/>
															   </c:set>
                                                            <tt class="fsize13 c-999 f-fM vam">有效期：</tt>
                                                            <span class="endTime"></span>
                                                        </span>
												</section>

												<section class="hLh20 mt10 of">
                                                         <span class="">
                                                            <tt class="fsize13 c-999 f-fM vam">已学至：</tt>
                                                            <c:if test="${not empty course.courseStudyhistory.kpointName}">
																<tt class="fsize13 c-999 f-fM vam">${course.courseStudyhistory.kpointName}</tt>
															</c:if>
                                                             <c:if test="${ empty course.courseStudyhistory.kpointName}">
																 <tt class="fsize13 c-999 f-fM vam">还没开始学习</tt>
															 </c:if>
                                                        </span>
												</section>

											</div>
										</div>
									</div>
								</div>
							</section>
							<section class="disIb vam u-i-jdbox col-3 tac mb15">
								<div class="clearfix">
									<div class="disIb vam ml30 new-u-i-jdt-name">
										<span class="c-999 f-fM fsize14">总进度：</span>
									</div>
									<div class="disIb vam new-u-i-jdt">
										<div class="time-bar-wrap">
											<div class="lev-num-wrap" title="已学${course.studyPercent}%">
												<aside class='lev-num-bar <c:if test="${course.studyPercent>=100}">bg-orange</c:if> <c:if test="${course.studyPercent<100}">bg-green</c:if>' style="width: ${course.studyPercent}%;"></aside>
												<span class="lev-num"><big>${course.studyPercent}%</big>/<small>100%</small></span>
											</div>
										</div>
									</div>
								</div>
							</section>
							<section class="col-2 disIb vam u-o-right">
								<div class="tar u-o-r-btn-infor">
									<p class="mb20">
										<c:if test="${not empty course.courseStudyhistory.kpointId}">
											<a class="cou-kscp" target="_blank" href="${ctx}/uc/play/${course.courseId}/${course.courseStudyhistory.kpointId}" title="继续学习">继续学习</a>
										</c:if>
										<c:if test="${ empty course.courseStudyhistory.kpointId}">
											<a class="cou-kscp" href="${ctx}/uc/play/${course.courseId}/0" title="开始学习">开始学习</a>
										</c:if>
									</p>
								</div>
							</section>
							<div class="clear"></div>
						</dd>
						</dl>
					</c:forEach>
					</ul>
				</section>
			</div>
		</section>
</c:if>