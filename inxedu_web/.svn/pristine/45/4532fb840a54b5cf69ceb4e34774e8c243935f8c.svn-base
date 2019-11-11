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
            <a href="${ctx}/uc/index" class="u-i-cou-back fr" title="返回课程列表">
                <em class="icon18 u-i-c-bico">&nbsp;</em>
                <tt class="fsize14 f-fM c-master vam">返回列表</tt>
            </a>
        </section>
        <section class="">
            <div class="u-r-all-box">
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
                    <section class="u-c-if-nr mt10">
                        <p class="f-fM c-999 fsize14">
                            <tt class="c-666 f-fM fsize14">简介：</tt>
                            <tt class="c-999 f-fM fsize14">内容</tt>
                        </p>
                    </section>
                    <section class="hLh30 mt5 of">
                         <span class="">
                              <tt class="fsize14 c-666 f-fM vam">主讲：</tt>
                             <c:forEach items="${teacherList}" var="teacher">
                                 <tt class="fsize14 c-666 f-fM vam ">${teacher.name}</tt>
                             </c:forEach>
                         </span>
                    </section>
                    <section class="hLh30 mt10 of">
                         <span class="fl u-tc-teach">
                              <tt class="fsize14 c-666 f-fM vam">价格：</tt>
                              <tt class="fsize18 c-master f-fM vam ">￥${course.currentPrice}</tt>
                         </span>
                        <span class="fl ml30">
                            <tt class="fsize14 c-666 f-fM vam">有效期：</tt>
                           <c:if test="${course.loseTime >0}">
                                <tt class="fsize14 c-666 f-fM vam ">剩余</tt>
                                <tt class="fsize14 c-master f-fM vam ">
                                    ${course.loseTime}
                                </tt>
                               <tt class="fsize14 c-666 f-fM vam ">天</tt>
                            </c:if>
                            <c:if test="${course.loseTime<0}">
                                 <tt class="fsize14 c-master f-fM vam ">
                                    已结束
                                 </tt>
                            </c:if>

                        </span>
                    </section>
                    <section class="of hLh30 uc-c-infor-jd">
                        <div class="jdt-i-r clearfix">
                            <div class="fl fsize14 c-666 f-fM">学习进度：</div>
                            <div class="time-bar-wrap fl">
                                <div class="lev-num-wrap" title="已学${course.studyPercent}%">
                                    <aside class='lev-num-bar <c:if test="${course.studyPercent>=100}">bg-orange</c:if> <c:if test="${course.studyPercent<100}">bg-green</c:if>' style="width: ${course.studyPercent}%;"></aside>
                                    <span class="lev-num"><big>${course.studyPercent}%</big>/<small>100%</small></span>
                                </div>
                            </div>
                        </div>
                    </section>
                </section>
                <div class="">
                    <div class="u-r-all-box">
                        <section class="mt20 mb40">
                            <div class="lh-menu-wrap" id="courseKpointMenu">
                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </section>
    </div>
</article>
	<script type="text/javascript" src="${ctx}/static/inxweb/course/uc_index.js"></script>
	<script>
		$(function(){
			slideScroll("#live-box-in", "#live-box-in .lv-prev", "#live-box-in .lv-next", "#i-live-cou-list", 5, false); //套餐推荐滚动
			treeMenu();
			/*个人中心第一个课程选中状态*/
			//$("#i-live-cou-list ul li a").first().click()
			getCourseKpointList('${course.courseId}',2);
            /*给导航加选中*/
            $("a[href$='/uc/index']").parent().addClass("current");
		});
	</script>
</body>
</html>