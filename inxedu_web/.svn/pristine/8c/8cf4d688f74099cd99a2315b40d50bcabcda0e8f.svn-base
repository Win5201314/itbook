<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>个人中心</title>
</head>
<body>
<article class="col-80 fl">
    <div class="u-r-cont">
        <section class="u-r-tit-all clearfix">
            <h2 class="clearfix fl">
                <span class="f-fH unFw current">课程详情</span>
            </h2>
            <a href="javascript:history.go(-1)" class="u-i-cou-back fr" title="返回课程列表">
                <em class="icon18 u-i-c-bico">&nbsp;</em>
                <tt class="fsize14 f-fM c-master vam">返回列表</tt>
            </a>
        </section>
        <section class="">
            <div class="u-r-all-box">
                <c:if test="${empty courseList}">
                    <section class="no-data-wrap">
                        <em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">还没有开始直播哦！~<a href="${ctx}/front/showcoulist" class="c-master">学习其他课程</a></span>
                    </section>
                </c:if>
                <c:if test="${!empty courseList}">
                    <section class="u-cous-inf-warp">
                        <article class="u-c-inf-pic">
                            <c:choose>
                                <c:when test="${not empty course.logo}">
                                    <img src="${staticServer}${course.logo}" class="img-responsive" alt="${course.courseName}" />
                                </c:when>
                                <c:otherwise>
                                    <img src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${course.courseName}" />
                                </c:otherwise>
                            </c:choose>
                        </article>
                        <section class="hLh30 txtOf name">
                            <span class="fsize18 c-333 f-fM">
                                 <c:if test="${course.sellType=='COURSE'}">
                                     <tt class="c-master fsize18 f-fM">[ 录播课 ]&nbsp;</tt>
                                 </c:if>
                                <c:if test="${course.sellType=='LIVE'}">
                                    <tt class="c-master fsize18 f-fM">[ 直播 ]&nbsp;</tt>
                                </c:if>
                                <c:if test="${course.sellType=='PACKAGE'}">
                                    <tt class="c-master fsize18 f-fM">[ 套餐 ]&nbsp;</tt>
                                </c:if>
                                ${course.courseName}
                            </span>
                        </section>
                        <section class="hLh30 mt5 of">
                             <span class="">
                                  <tt class="fsize14 c-666 f-fM vam">主讲：</tt>
                                 <c:forEach items="${teacherList}" var="teacher">
                                     <tt class="fsize14 c-666 f-fM vam ">${teacher.name}</tt>
                                 </c:forEach>
                             </span>
                        </section>
                        <section class="hLh30 mt5 of">
                             <span class="u-live-jg">
                                  <tt class="fsize14 c-666 f-fM vam">价格：</tt>
                                  <tt class="fsize18 c-master f-fM vam ">￥${course.currentPrice}</tt>
                             </span>
                        </section>
                        <section class="hLh30 mt5 of">
                            <span class="u-tc-time-live">
                                <tt class="fsize14 c-666 f-fM vam">直播日期：</tt>
                                <tt class="fsize14 c-333 f-fM vam ">
                                    <fmt:formatDate value="${course.liveBeginTime}" pattern="yyyy-MM-dd" type="both"/>
                                    ~
                                    <fmt:formatDate value="${course.liveEndTime}" pattern="yyyy-MM-dd" type="both"/>
                                </tt>
                            </span>
                        </section>
                        <section class="hLh30 mt5 of">
                            <span class="">
                                <tt class="fsize14 c-666 f-fM vam">课时：</tt>
                                <tt class="fsize14 c-666 f-fM vam parentKopintCount "></tt>
                                <tt class="fsize14 c-666 f-fM vam ">章，共</tt>
                                <tt class="fsize14 c-666 f-fM vam childKopintCount"></tt>
                                <tt class="fsize14 c-666 f-fM vam ">节</tt>
                            </span>
                        </section>
                        <section class="of hLh30 uc-c-infor-jd">
                            <div class="jdt-i-r clearfix">
                                <div class="fl fsize14 c-666 f-fM">直播进度：</div>
                                <div class="fl fsize14 c-green f-fM undis">未开始</div>
                                <div class="fl fsize14 c-333 f-fM undis">已结束</div>
                                <div class="time-bar-wrap fl">
                                    <div class="lev-num-wrap " title="已播${course.studyPercent}节">
                                        <aside class='lev-num-bar livePercent ' style="width: 0;"></aside>
                                        <span class="lev-num"><big class="livePercentCount"></big><small class="childKopint">共8节</small></span>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </section>
                    <div class="">
                        <div class="u-r-all-box">
                            <section class="mt20 mb40">
                                <div class="lh-menu-wrap" id="liveKpointMenu">
                                </div>
                            </section>
                        </div>
                    </div>
                </c:if>
            </div>
        </section>
    </div>
</article>
	<script type="text/javascript" src="${ctx}/static/inxweb/course/uc_index.js"></script>
	<script>
		$(function(){
            /*获取直播的章节列表*/
            getLiveKpointList();
		});
        /*获取直播的章节列表*/
        function getLiveKpointList() {
            $.ajax({
                url : baselocation + "/front/ajax/liveKpointList/"+${courseId},
                data:{},
                type : 'post',
                dataType : 'text',
                success : function(result) {
                    $(liveKpointMenu).append(result);
                }
            });
        }

	</script>
</body>
</html>