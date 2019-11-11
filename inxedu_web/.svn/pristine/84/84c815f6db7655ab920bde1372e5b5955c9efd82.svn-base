<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${!empty lastTimeStudyhistory}">
<section class="">
	<c:if test="${playFromType==2}">
		<c:if test="${!empty lastTimeStudyhistory.userId && !empty lastTimeStudyhistory.kpointName}">
			<h6 class="c-g-content c-infor-title">
				<span>近期播放</span>
			</h6>
			<article class="dqkc-list mb30">
				<!-- /无数据提示 开始-->
			<c:if test="${empty lastTimeStudyhistory.userId && empty lastTimeStudyhistory.kpointName} ">
				<section class="no-data-wrap">
					<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">您还没有开始学习课程，快去<a href="${ctx}/front/showcoulist" class="c-master f-fM fsize14">选择要学习的课程</a>吧！</span>
				</section>
			</c:if>
				<!-- /无数据提示 结束-->
				<div class="clearfix cous-new-late">
					<span class="fl vam">
						<em class="icon20 c-n-la-ico">&nbsp;</em>
						<tt class="vam fsize16 c-master f-fM">${lastTimeStudyhistory.kpointName}</tt>
					</span>
					<span class="fr vam">
						<c:if test="${playFromType==2}">
							<a target="_blank" id="playKpointId${lastTimeStudyhistory.kpointId }" <c:if test="${playFromType==2}">href="${ctx}/uc/play/${courseId}/${lastTimeStudyhistory.kpointId }"</c:if>
							   <c:if test="${playFromType!=3}">href="javascript:void(0)"</c:if>
							   <c:if test="${playFromType!=3}">onclick="getPlayerHtml(${lastTimeStudyhistory.kpointId },this,'${lastTimeStudyhistory.kpointName }')"</c:if> class="cou-kscp" title="继续学习">继续学习
							</a>
						</c:if>
							<a  style="display: none"class="cou-kscp ml20" href="" title="考试测评">考试测评</a>
					</span>
					<%--<span class="fr vam mr20">
						<tt class="vam fsize12 c-333 f-fM">观看至：</tt>
						<tt class="vam fsize13 c-333 f-fM">01:02:33</tt>
					</span>--%>
				</div>
		</article>
		</c:if>
	</c:if>
</section>
</c:if>
<section <c:if test="${!empty lastTimeStudyhistory.userId}">class=""</c:if>>
	<h6 class="c-g-content c-infor-title">
		<span>课程目录</span>
	</h6>
	<c:if test="${not empty parentKpointList }">
		<menu id="lh-menu" class="lh-menu">
			<ul >
				<c:set var="folderIndex" value="1"/>
					<%--如果是进入播放大厅则显示上次观看位置（个人中心课程列表有用到）--%>
				<c:forEach items="${parentKpointList }" var="parentKpoint" varStatus="index">
					<c:if test="${parentKpoint.kpointType==0 }"><!-- 文件目录 -->
						<li  class="lh-menu-stair">
							<a href="javascript: void(0)"  title="${parentKpoint.name }" class="l-m-stitle"
							   <c:if test="${index.first==true}">class="current-1 l-m-stitle"</c:if>>
								<span class="fr"><em class="icon16 m-tree-icon">&nbsp;</em></span>
									${parentKpoint.name }
							</a>
							<ol class="lh-menu-ol"
								<c:if test="${index.first==true}">style="display: block;"</c:if>
								<c:if test="${index.first==false}">style="display: none;"</c:if>
							>
								<c:forEach items="${parentKpoint.kpointList}" var="sonKpoint">
									<li class="lh-menu-second">
										<div class="l-m-stitle clearfix">
											<span class="fr">
												<%--课程详情且为免费章节显示 --%>
												<c:if test="${playFromType==1}">
													<%--免费课程 并且用户没有登陆--%>
													<c:if test="${sonKpoint.free==1&&userId==0}">
														<a  id="playKpointId${sonKpoint.kpointId }"
														   href="javascript:void(0)"
														   onclick="toVideoPlayer('${ctx}/uc/play/${courseId}/${sonKpoint.kpointId }')"
														   class="cou-kscp" title="免费试听">
															免费课程
														</a>
													</c:if>
													<%--有用户登陆--%>
													<c:if test="${userId!=0}">
														<a  id="playKpointId${sonKpoint.kpointId }"
															href="javascript:void(0)"
															onclick="toVideoPlayer('${ctx}/uc/play/${courseId}/${sonKpoint.kpointId }')"
															class="cou-kscp" title="开始学习">
															开始学习
														</a>
													</c:if>
												</c:if>
													<%--若果是个人中心--%>
													<c:if test="${playFromType==2}">
														<a  id="playKpointId${sonKpoint.kpointId }"
															href="javascript:void(0)"
															onclick="toVideoPlayer('${ctx}/uc/play/${courseId}/${sonKpoint.kpointId }')"
															class="cou-kscp" title="进入课程">
																<c:choose>
																	<%--如果章节id等于最近学习的章节id--%>
																	<c:when test="${not empty lastTimeStudyhistory &&sonKpoint.kpointId==lastTimeStudyhistory.kpointId }">
																		继续学习
																	</c:when>
																	<c:otherwise>
																		<c:if test="${sonKpoint.kpointId!=lastTimeStudyhistory.kpointId && sonKpoint.isStudy=='true'}">
																			进入课程
																		</c:if>
																		<c:if test="${sonKpoint.kpointId!=lastTimeStudyhistory.kpointId && sonKpoint.isStudy=='false'}">
																			开始学习
																		</c:if>
																	</c:otherwise>
																</c:choose>
														</a>
													</c:if>
												   <a style="display: none" class="cou-kscp ml20" href="" title="考试测评">考试测评</a>
											</span>
											<c:if test="${playFromType!=1}">
													<%--<span class="fr mr20">
														<tt class="vam fsize14 f-fM c-master">直播中ing...</tt>
														<tt class="vam fsize14 f-fM c-666">已结束</tt>
														<tt class="vam fsize14 f-fM c-green">01小时12分20秒后开始直播！</tt>
													</span>--%>
												<c:choose>
													<%--如果章节id等于最近学习的章节id--%>
													<c:when test="${not empty lastTimeStudyhistory &&sonKpoint.kpointId==lastTimeStudyhistory.kpointId }">
													<span class="fr mr20">
														<em class="icon14 u-c-jxzico">&nbsp;</em>
														<tt class="vam fsize12 f-fM c-master TxT">近期学习</tt>
													</span>
													</c:when>
												<c:otherwise>
												<c:if test="${sonKpoint.kpointId!=lastTimeStudyhistory.kpointId &&sonKpoint.isStudy=='true'}">
												<span class="fr mr20">
														<em class="icon14 u-c-overico">&nbsp;</em>
														<tt class="vam fsize12 f-fM c-green TxT">已学习</tt>
													</span>
												</c:if>
												<c:if test="${sonKpoint.kpointId!=lastTimeStudyhistory.kpointId &&sonKpoint.isStudy=='false'}">
												<span class="fr mr20">
														<em class="icon14 u-c-wksioc">&nbsp;</em>
														<tt class="vam fsize12 f-fM c-666 TxT">未学习</tt>
													</span>
												</c:if>
												</c:otherwise>
												</c:choose>

											</c:if>
											<span class="vam">
												<c:if test="${sonKpoint.fileType=='AUDIO' }">
													<em class="lh-p-icon-yp icon20">&nbsp;</em>
												</c:if>
												<c:if test="${sonKpoint.fileType=='VIDEO' }">
													<em class="lh-p-icon-sp icon20">&nbsp;</em>
												</c:if>
												<c:if test="${sonKpoint.fileType=='TXT' }">
													<em class="lh-p-icon-wd icon20">&nbsp;</em>
												</c:if>
												<c:if test="${sonKpoint.fileType=='PDF' }">
													<em class="lh-p-icon-wd icon20">&nbsp;</em>
												</c:if>
												<c:if test="${sonKpoint.fileType=='ATLAS' }">
													<em class="lh-p-icon-tp icon20">&nbsp;</em>
												</c:if>
												<c:if test="${sonKpoint.fileType=='EXERCISES' }">
													<em class="lh-p-icon-wd icon20">&nbsp;</em>
												</c:if>
											</span>
										<%--	<c:if test="${parentKpoint.free==1}">
											<span class="fr">
										<tt href="" class="free-icon vam">免费试听</tt>
											</span>
											</c:if>--%>
											<c:choose>
												<%--若果是个人中心或课程详情且已登录用户并且为免费章节可点击跳转播放大厅--%>
												<c:when test="${(playFromType==1&&sonKpoint.free==1)||playFromType==2}">
													<a  title="${sonKpoint.name }"
														onclick="toVideoPlayer('${ctx}/uc/play/${courseId}/${sonKpoint.kpointId }')"
														href="javascript:void (0)" class="cou-tit-txt">${sonKpoint.name }
													</a>
												</c:when>
												<c:otherwise>
													<span class="cou-tit-txt">${sonKpoint.name }</span>
												</c:otherwise>
                                            </c:choose>

									</li>
								</c:forEach>
							</ol>
						</li>
						<c:set var="folderIndex" value="${folderIndex+1 }"/>
					</c:if>
					<c:if test="${parentKpoint.kpointType==1 && parentKpoint.parentId==0 }"><!-- 视频 而且没有父级-->
						<li class="lh-menu-stair">
							<ul class="lh-menu-ol no-parent-node">
								<li class="lh-menu-second">
									<div class="l-m-stitle clearfix">
									<span class="fr">
										<%--如果是前台课程详情--%>
										<c:if test="${playFromType==1}">
											<%--如果是免费课程 并且没有用户登陆--%>
											<c:if test="${parentKpoint.free==1 &&userId==0}">
												<a  id="playKpointId${parentKpoint.kpointId }" href="javascript:void(0)"
														onclick="toVideoPlayer('${ctx}/uc/play/${courseId}/${parentKpoint.kpointId }')" class="cou-kscp" title="进入课程">
													免费观看
												</a>
											</c:if>
											<%-- 有用户登陆--%>
											<c:if test="${userId!=0}">
												<a  id="playKpointId${parentKpoint.kpointId }" href="javascript:void(0)"
													onclick="toVideoPlayer('${ctx}/uc/play/${courseId}/${parentKpoint.kpointId }')" class="cou-kscp" title="进入课程">
													开始学习
												</a>
											</c:if>
										</c:if>
											<%--如果是个人中心课程详情--%>
											<c:if test="${playFromType==2}">
											<a  id="playKpointId${parentKpoint.kpointId }" href="javascript:void(0)"
												onclick="toVideoPlayer('${ctx}/uc/play/${courseId}/${parentKpoint.kpointId }')" class="cou-kscp" title="进入课程">
												<c:choose>
													<%--如果章节id等于最近学习的章节id--%>
													<c:when test="${not empty lastTimeStudyhistory &&parentKpoint.kpointId==lastTimeStudyhistory.kpointId }">
														继续学习
													</c:when>
													<c:otherwise>
														<c:if test="${parentKpoint.kpointId!=lastTimeStudyhistory.kpointId && parentKpoint.isStudy=='true'}">
															进入课程
														</c:if>
														<c:if test="${parentKpoint.kpointId!=lastTimeStudyhistory.kpointId && parentKpoint.isStudy=='false'}">
															开始学习
														</c:if>
													</c:otherwise>
												</c:choose>
											</a>
											</c:if>
											<a style="display: none" class="cou-kscp" href="" title="考试测评">考试测评</a>
									</span>

								<c:if test="${playFromType!=1}">
										<c:choose>
											<%--如果章节id等于最近学习的章节id--%>
											<c:when test="${not empty lastTimeStudyhistory && parentKpoint.kpointId==lastTimeStudyhistory.kpointId }">
												<span class="fr mr20">
													<em class="icon14 u-c-jxzico">&nbsp;</em>
													<tt class="vam fsize12 f-fM c-master TxT">近期学习</tt>
												</span>
											</c:when>
											<c:otherwise>
												<c:if test="${parentKpoint.kpointId!=lastTimeStudyhistory.kpointId && parentKpoint.isStudy=='true'}">
											<span class="fr mr20">
													<em class="icon14 u-c-overico">&nbsp;</em>
													<tt class="vam fsize12 f-fM c-green TxT">已学习</tt>
												</span>
												</c:if>
												<c:if test="${parentKpoint.kpointId!=lastTimeStudyhistory.kpointId && parentKpoint.isStudy=='false'}">
											<span class="fr mr20">
													<em class="icon14 u-c-wksioc">&nbsp;</em>
													<tt class="vam fsize12 f-fM c-666 TxT">未学习</tt>
												</span>
												</c:if>
											</c:otherwise>
										</c:choose>
								</c:if>
									<span class="vam">
										<c:if test="${parentKpoint.fileType=='AUDIO' }">
											<em class="lh-p-icon-yp icon20">&nbsp;</em>
										</c:if>
										<c:if test="${parentKpoint.fileType=='VIDEO' }">
											<em class="lh-p-icon-yp icon20">&nbsp;</em>
										</c:if>
										<c:if test="${parentKpoint.fileType=='TXT' }">
											<em class="lh-p-icon-wd icon20">&nbsp;</em>
										</c:if>
										<c:if test="${parentKpoint.fileType=='PDF' }">
											<em class="lh-p-icon-wd icon20">&nbsp;</em>
										</c:if>
										<c:if test="${parentKpoint.fileType=='ATLAS' }">
											<em class="lh-p-icon-tp icon20">&nbsp;</em>
										</c:if>
										<c:if test="${parentKpoint.fileType=='EXERCISES' }">
											<em class="lh-p-icon-hk icon20">&nbsp;</em>
										</c:if>
									</span>
										<%--<c:if test="${parentKpoint.free==1}">
							<span class="fr">
										<tt href="" class="free-icon vam">免费试听</tt>
											</span>
										</c:if>--%>
									<%--只有子集标题--%>
										<a id="playKpointId${parentKpoint.kpointId }"
											 onclick="toVideoPlayer('${ctx}/uc/play/${courseId}/${parentKpoint.kpointId }')"
											 href="javascript:void (0)"
											 title="${parentKpoint.name }"  class="cou-tit-txt">${parentKpoint.name }
										</a>


									</div>
								</li>
							</ul>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</menu>
	</c:if>
</section>
<!-- /无数据提示 开始-->
<c:if test="${empty parentKpointList}">
	<section class="no-data-wrap">
		<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
	</section>
</c:if>
<%--播放来源 1 课程详情 2 播放大厅--%>
<input type="hidden" id="playFromType" value="${playFromType}"/>
<input type="hidden" id="courseId" value="${courseId}"/>
<!-- /无数据提示 结束-->
<script type="text/javascript" src="${ctx}/static/inxweb/front/course-kpoint-list.js"></script>