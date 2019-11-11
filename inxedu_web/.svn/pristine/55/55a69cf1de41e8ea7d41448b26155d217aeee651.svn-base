<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>个人中心</title>
    <script>
        $(function () {
            /*sellType为取的课程类型，根据sellType给导航加选中状态*/
            var sellType = '${queryCourse.sellType}';
            var isoverdue = '${queryCourse.isoverdue}';
            /*是否查询的过期课程*/
            if (isoverdue=="true"){
                $("#selectOVER").addClass("current");
                $("#mobileSelectOVER").addClass("m-current");
                $(".mTitle").text("过期课程");
            }else if (sellType=="") {
               /* 查询总课程没有sellType状态*/
                $("#selectAll").addClass("current");
                $("#mobileSelectAll").addClass("m-current");
                /*移动端筛选标题*/
                $(".mTitle").text("全部课程");
            } else if (sellType=="COURSE"){
                $("#select"+sellType).addClass("current");
                $("#mobileSelect"+sellType).addClass("m-current");
                /*移动端筛选标题*/
                $(".mTitle").text("录播课程");
            } else if (sellType=="LIVE"){
                $("#select"+sellType).addClass("current");
                $("#mobileSelect"+sellType).addClass("m-current");
                /*移动端筛选标题*/
                $(".mTitle").text("直播课程");
            } else if (sellType=="PACKAGE"){
                $("#select"+sellType).addClass("current");
                $("#mobileSelect"+sellType).addClass("m-current");
                /*移动端筛选标题*/
                $(".mTitle").text("直播课程");
            }

        });
        /*导航按钮点击事件*/
        function selectSelfCourse(type) {
            if (type=="COURSE"){
                $("#sellType").val("COURSE");
                $("#over").val("")
            }
            if (type=="LIVE"){
                $("#sellType").val("LIVE");
                $("#over").val("")
            }
            if (type=="PACKAGE"){
                $("#sellType").val("PACKAGE");
                $("#over").val("")
            }
            if (type=="OVER"){
                $("#sellType").val("");
                $("#over").val("true")
            }
            if (type=="All"){
                $("#sellType").val("");
                $("#over").val("")
            }
            $("#searchForm").submit();
        }
        /*获取章节列表*/
        function getCoursePackageList(courseId,obj) {
            if($(obj).parent().find(".lh-menu-wrap").html()==null){
                $.ajax({
                    url : baselocation + "/uc/ajax/coursePackageList/"+courseId,
                    data:{},
                    type : 'post',
                    dataType : 'text',
                    success : function(result) {
                        $(obj).parent().append(result);
                        showTable(obj);
                        $(obj).parent().find(".lh-menu-wrap").find(".endTime").html($(obj).prev().html())
                    }
                });
            }else{
                showTable(obj);
            }

        }
        /**
         * 获得直播章节目录
         * @param num
         */
        function getLiveKpointList(courseId,obj) {
            if($(obj).parent().find("table.classIsEmpty").html()==null){
                $.ajax({
                    url : baselocation + "/front/ajax/liveKpointList/"+courseId,
                    data:{},
                    type : 'post',
                    dataType : 'text',
                    success : function(result) {
                        $(obj).parent().append(result);
                        showTable(obj);
                    }
                });
            }else{
                showTable(obj);
            }
        }
        /*直播列表展开隐藏*/
        function showTable(obj){
            if(!$(obj).hasClass("sch-open")){
                $(obj).siblings("#coursePackageList").hide();
                $(obj).addClass("sch-open");
                $(obj).parent().find("table").attr("style","display:none");
                $(obj).text("课程表展开∨");
            }else{
                $(obj).siblings("#coursePackageList").show();
                $(obj).removeClass("sch-open");
                $(obj).parent().find("table").attr("style","display:table");
                $(obj).text("课程表收起∧");
            }
        }
        /**
         * 获得课程章节目录
         * @param num
         */
        function getCourseKpointList(courseId,obj) {
            if($(obj).next().html()==""){
                $(obj).html("直播章节收起∧");
                goBack = "true";
                $.ajax({
                    url : baselocation + "/front/ajax/courseKpointList/"+courseId+"/"+"2",
                    data:{},
                    type : 'post',
                    dataType : 'text',
                    success : function(result) {
                        $(obj).next().html(result);
                        treeMenu(); //课程树
                    }
                });
            }else {
                $(obj).html("直播章节展开∨");
                $(obj).next().html("")
            }
        }
    </script>
</head>
<body>
    <article class="col-80 fl">
        <div class="u-r-cont-top-w">
            <section>
                <section class="u-r-tit-all clearfix">
                    <h2 class="clearfix fl">
                        <span class="f-fH unFw current">近期学习</span>
                    </h2>
                </section>
                <section class="">
                    <div class="u-r-all-box">
                        <c:if test="${empty recentStudyCourse}">
                            <section class="no-data-wrap">
                                <em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">还没有开始学习哦！~<a href="${ctx}/front/showcoulist" class="c-master">马上去学习</a></span>
                            </section>
                        </c:if>
                        <c:if test="${!empty recentStudyCourse}">
                        <section class="u-cous-inf-warp u-cous-inf-warp-index">
                            <article class="u-c-inf-pic">
                                <c:choose>
                                    <c:when test="${not empty recentStudyCourse.logo}">
                                        <img src="${staticServer}${recentStudyCourse.logo}" class="img-responsive" alt="${recentStudyCourse.courseName}" />
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${recentStudyCourse.courseName}" />
                                    </c:otherwise>
                                </c:choose>
                            </article>
                            <section class="hLh20 txtOf name">
                                <span class="fsize14 c-333 f-fM">
                                     <c:if test="${recentStudyCourse.sellType=='COURSE'}">
                                         <tt class="c-master fsize14 f-fM">[ 录播课 ]&nbsp;</tt>
                                     </c:if>
                                    <c:if test="${recentStudyCourse.sellType=='LIVE'}">
                                        <tt class="c-master fsize14 f-fM">[ 直播 ]&nbsp;</tt>
                                    </c:if>
                                    <c:if test="${recentStudyCourse.sellType=='PACKAGE'}">
                                        <tt class="c-master fsize14 f-fM">[ 套餐 ]&nbsp;</tt>
                                    </c:if>
                                    ${recentStudyCourse.courseName}
                                </span>
                            </section>
                            <section class="hLh20 mt5 of">
                                 <span class="">
                                      <tt class="fsize14 c-666 f-fM vam">主讲：</tt>
                                     <c:forEach items="${recentStudyCourse.teacherList}" var="teacher">
                                         <tt class="fsize14 c-666 f-fM vam ">${teacher.name}</tt>
                                     </c:forEach>
                                 </span>
                            </section>
                            <section class="hLh20 mt10 of">
                                 <span class="fl u-tc-teach">
                                      <tt class="fsize14 c-666 f-fM vam">价格：</tt>
                                      <tt class="fsize18 c-master f-fM vam ">￥${recentStudyCourse.currentPrice}</tt>
                                 </span>

                            </section>
                            <section class="of hLh30 uc-c-infor-jd">
                                <div class="jdt-i-r clearfix">
                                    <div class="fl fsize14 c-666 f-fM">学习进度：</div>
                                    <div class="time-bar-wrap fl">
                                        <div class="lev-num-wrap" title="已学${recentStudyCourse.studyPercent}%">
                                            <aside class='lev-num-bar <c:if test="${recentStudyCourse.studyPercent>=100}">bg-orange</c:if> <c:if test="${recentStudyCourse.studyPercent<100}">bg-green</c:if>' style="width: ${recentStudyCourse.studyPercent}%;"></aside>
                                            <span class="lev-num"><big>${recentStudyCourse.studyPercent}%</big>/<small>100%</small></span>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </section>
                        <div class="">
                            <div class="u-r-all-box u-r-all-box-in">
                                <section class="mt20 mb40">
                                    <section class="">
                                        <h6 class="c-g-content c-infor-title">
                                            <span>章节进度</span>
                                        </h6>
                                        <article class="dqkc-list mb30">
                                            <div class="clearfix cous-new-late">
                                                <span class="fl vam">
                                                    <em class="icon20 c-n-la-ico"> </em>
                                                    <tt class="vam fsize16 c-master f-fM">${recentStudyCourse.courseStudyhistory.kpointName}</tt>
                                                </span>
                                                <span class="fr vam mr20">
                                                  <%--  <c:if test="${courseKpoint.videoType=='INXEDUCLOUD'}">
                                                                <tt class="vam fsize12 f-fM c-666 mr30">

                                                                    <c:set var="videotime" value="${fn:split(recentStudyCourse.courseStudyhistory.databack, ',')[0]}"/>
                                                                    <c:set var="number" value="${fn:split(videotime, '|')[0]}"/>
                                                                    <c:if test="${number.matches('[0-9]+')}">
                                                                        <c:set var="videotime" value="${fn:split(videotime, '|')[0]*1000}"/>
                                                                        <fmt:formatNumber type="number" value="${((videotime/1000/60-videotime/1000/60%60)/60)}"/>:<c:if test="${((videotime/1000-videotime/1000%60)/60) - ((videotime/1000/60-videotime/1000/60%60)/60)*60<10}">0</c:if>
                                                                        <fmt:formatNumber type="number" value="${((videotime/1000-videotime/1000%60)/60) - ((videotime/1000/60-videotime/1000/60%60)/60)*60}"/>:<c:if test="${((videotime-videotime%1000)/1000) -( ((videotime/1000-videotime/1000%60)/60)*60 )<10}">0</c:if><fmt:formatNumber type="number" value="${ ((videotime-videotime%1000)/1000) -( ((videotime/1000-videotime/1000%60)/60)*60 )}"/>
                                                                    </c:if>

                                                                </tt>
                                                    </c:if>--%>
                                                    <a id="playKpointId402" class="cou-kscp" target="_blank" href="${ctx}/uc/play/${recentStudyCourse.courseStudyhistory.courseId}/${recentStudyCourse.courseStudyhistory.kpointId}" title="继续学习">继续学习 </a>
                                                   <%-- <a class="cou-kscp ml20" style="display: none" href="" title="考试测评">考试测评</a>--%>
                                                </span>
                                            </div>
                                        </article>
                                    </section>
                                </section>
                            </div>
                        </div>
                        </c:if>
                    </div>
                </section>
            </section>
        </div>
        <div class="u-r-cont">
            <section class="">
                <form id="searchForm" method="post" action="${ctx}/uc/index">
                    <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
                    <input type="hidden" id="sellType" name="sellType" value="${type}">
                    <input type="hidden" id="over" name="ifOver" value="${ifOver}">
                </form>
                <div>
                    <section class="u-r-tit-all clearfix">
                        <h2 class="fl unFw m-xl-ashow">
                            <a  href="javascript:void (0)" id="selectAll" onclick="selectSelfCourse('All')" title="全部课程" class="f-fH  more">全部课程</a>
                            <a href="javascript:void (0)" id="selectCOURSE"onclick="selectSelfCourse('COURSE')" title="录播课程"  class="f-fH ">录播课程</a>
                            <c:if test="${serviceSwitch.live=='ON'}">
                                <a href="javascript:void (0)" id="selectLIVE" onclick="selectSelfCourse('LIVE')" title="直播课程"  class="f-fH">直播课程</a>
                            </c:if>
                            <a href="javascript:void (0)" id="selectPACKAGE"title="套餐课程" onclick="selectSelfCourse('PACKAGE')" class="f-fH">套餐课程</a>
                            <a href="javascript:void (0)" id="selectOVER" title="过期课程" onclick="selectSelfCourse('OVER')" class="f-fH">过期课程</a>
                        </h2>
                        <section class="u-m-tit-abox fl">
                            <h2 class="unFw u-m-n-box">
                                <a href="javascript:void(0)" title="" class="f-fH mTitle current more">全部课程</a>
                                <em class="icon16 u-i-tbsj-ico">&nbsp;</em>
                            </h2>
                            <div class="u-m-tit-a-warp">
                                <div class="u-m-t-ab-in">
                                    <ul>
                                        <li  id="mobileSelectAll">
                                            <a href="javascript:void(0)" onclick="selectSelfCourse('All')"  class="">全部课程</a>
                                            <em class="u-i-m-ab-xz">&nbsp;</em>
                                        </li>
                                        <li id="mobileSelectCOURSE" class="">
                                            <a href="javascript:void(0)" onclick="selectSelfCourse('COURSE')" class="">录播课程</a>
                                            <em class="u-i-m-ab-xz">&nbsp;</em>
                                        </li>
                                        <li id="mobileSelectLIVE" class="">
                                            <a href="javascript:void(0)" onclick="selectSelfCourse('LIVE')" class="">直播课程</a>
                                            <em class="u-i-m-ab-xz">&nbsp;</em>
                                        </li>
                                        <li id="mobileSelectPACKAGE" class="">
                                            <a href="javascript:void(0)" onclick="selectSelfCourse('PACKAGE')" class="">套餐课程</a>
                                            <em class="u-i-m-ab-xz">&nbsp;</em>
                                        </li>
                                        <li id="mobileSelectOVER" class="">
                                            <a href="javascript:void(0)" onclick="selectSelfCourse('OVER')" class="">过期课程</a>
                                            <em class="u-i-m-ab-xz">&nbsp;</em>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </section>
                        <c:if test="${serviceSwitch.cardServer=='ON'}">
                            <section class="u-o-right fr tar mr20 mt13">
                                <a class="cou-kscp" href="javascript:void (0)" onclick="dialog('课程卡激活','',6)" title="课程卡兑换">课程卡兑换</a>
                            </section>
                        </c:if>
                    </section>
                </div>
                <div class="uc-index-warp" id="aCoursesList">
                    <c:if test="${courseList==null || courseList.size()<=0 }">
                        <!-- /无数据提示 开始-->
                        <section class="no-data-wrap">
                            <c:if test="${queryCourse.isoverdue=='true'}">
                                <em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有过期课程！</span>
                            </c:if>
                            <c:if test="${queryCourse.isoverdue!='true'}">
                                <em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">您还没有开始学习课程，快去<a href="${ctx}/front/showcoulist" class="c-master f-fM fsize14">选择要学习的课程</a>吧！</span>
                            </c:if>
                        </section>

                        <!-- /无数据提示 结束-->
                    </c:if>
                    <c:if test="${not empty courseList }">
                        <div class="u-i-cou-warp">
                            <c:if test="${queryCourse.sellType!='PACKAGE'&&queryCourse.sellType!='LIVE'}">
                                <c:forEach items="${courseList}" var="course">
                                    <dl>
                                        <dd class="u-order-list">
                                            <section class="w50pre disIb vam u-o-left">
                                                <div class="u-o-l-infor">
                                                    <div class="c-cou-item">
                                                        <div class="">
                                                            <div>
                                                                    <%-- 跳转课程详情页面--%>
                                                                <a class="u-ol-pic u-ol-pic-new"
                                                                   <%--如果不是过期课程正常进入个人中心课程详情--%>
                                                                   <c:if test="${ifOver!='true'}">
                                                                        href="${ctx}/uc/courseInfo/${course.courseId}"
                                                                   </c:if>
                                                                    <%--如果是过期课程进入前台课程详情--%>
                                                                        <c:if test="${ifOver=='true'}">
                                                                            href="${ctx}/front/couinfo/${course.courseId}"
                                                                        </c:if>
                                                                   title="">
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
                                                                    <a
                                                                        <%--如果不是过期课程正常进入个人中心课程详情--%>
                                                                            <c:if test="${ifOver!='true'}">
                                                                                href="${ctx}/uc/courseInfo/${course.courseId}"
                                                                            </c:if>
                                                                        <%--如果是过期课程进入前台课程详情--%>
                                                                            <c:if test="${ifOver=='true'}">
                                                                                href="${ctx}/front/couinfo/${course.courseId}"
                                                                            </c:if>
                                                                            title="${course.courseName}" class="fsize16 c-333">

                                                                        <c:if test="${course.sellType=='COURSE'}">
                                                                            <tt class="c-master fsize16 f-fM">[ 录播 ]&nbsp;</tt>
                                                                        </c:if>
                                                                        <c:if test="${course.sellType=='LIVE'}">
                                                                            <tt class="c-master fsize16 f-fM">[ 直播 ]&nbsp;</tt>
                                                                        </c:if>
                                                                        <c:if test="${course.sellType=='PACKAGE'}">
                                                                            <tt class="c-master fsize16 f-fM">[ 套餐 ]&nbsp;</tt>
                                                                        </c:if>
                                                                            ${course.courseName}
                                                                    </a>
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
                                                                        <c:if test="${course.endTime>=nowDate}">
                                                                            <tt class="fsize13 c-999 f-fM vam "><fmt:formatDate value="${course.endTime}" pattern="yyyy-MM-dd"/></tt>
                                                                        </c:if>
                                                                        <c:if test="${course.endTime<nowDate}">
                                                                            <tt class="fsize13 c-blue f-fM vam">已过期</tt>
                                                                        </c:if>
                                                                    </span>
                                                                </section>
                                                                <section class="hLh20 mt10 of">
                                                                    <c:if test="${not empty course.courseStudyhistory.kpointName}">
                                                                         <span class="">
                                                                            <tt class="fsize13 c-999 f-fM vam">已学至：</tt>
                                                                            <tt class="fsize13 c-999 f-fM vam">${course.courseStudyhistory.kpointName}</tt>
                                                                        </span>
                                                                    </c:if>
                                                                    <c:if test="${ empty course.courseStudyhistory.kpointName}">
                                                                        <span>
                                                                             <tt class="fsize13 c-999 f-fM vam">还没开始学习</tt>
                                                                        </span>
                                                                    </c:if>
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
                                                            <c:set var="studyPercent">
                                                                <fmt:formatNumber value="${course.studyPercent}" pattern="0"/>
                                                            </c:set>
                                                            <div class="lev-num-wrap" title="已学${studyPercent}%">
                                                                <aside class='lev-num-bar <c:if test="${studyPercent>=100}">bg-orange</c:if> <c:if test="${studyPercent<100}">bg-green</c:if>' style="width: ${studyPercent}%;"></aside>
                                                                <span class="lev-num"><big>${studyPercent}%</big>/<small>100%</small></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </section>
                                            <section class="col-2 disIb vam u-o-right">
                                                <div class="tar u-o-r-btn-infor">
                                                    <p class="mb20">
                                                        <a href="${ctx}/uc/courseInfo/${course.courseId}" class="cou-kscp" title="查看详情">查看详情</a>
                                                            <%--<c:if test="${not empty course.courseStudyhistory.kpointId&&queryCourse.isoverdue=='false'}">
                                                                <a class="cou-kscp" target="_blank" href="${ctx}/uc/play/${course.courseId}/${course.courseStudyhistory.kpointId}" title="继续学习">继续学习</a>
                                                            </c:if>
                                                            <c:if test="${ empty course.courseStudyhistory.kpointId&&queryCourse.isoverdue=='false'}">
                                                                <a class="cou-kscp" href="${ctx}/uc/play/${course.courseId}/0" title="开始学习">开始学习</a>
                                                            </c:if>--%>
                                                    </p>
                                                </div>
                                            </section>
                                            <div class="clear"></div>
                                        </dd>
                                    </dl>

                                </c:forEach>
                            </c:if>
                                <%--如果是套餐课--%>
                            <c:if test="${queryCourse.sellType=='PACKAGE'}">
                                <c:forEach items="${courseList}" var="course">
                                    <section class="u-mycou-tc-list">
                                        <div class="u-tc-l-top">
                                            <div class="clearfix">
                                                <aside class="u-tc-pic">
                                                    <c:choose>
                                                        <c:when test="${not empty course.logo}">
                                                            <img src="${staticServer}${course.logo}" class="img-responsive" alt="${course.courseName}" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <img src="${ctx}/static/inxweb/img/default-img.gif" class="img-responsive" alt="${course.courseName}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </aside>
                                                <h3 class="hLh30 txtOf f-fM name"> <a href="${ctx}/front/couinfo/${course.courseId }" title="" class="fsize18 c-333 f-fM"> <tt class="c-master fsize18 f-fM">[ 套餐 ]&nbsp;</tt>${course.courseName}</a> </h3>
                                                <section class="hLh30 mt10 of nr">
                                            <span class="fl">
                                                <tt class="fsize14 c-999 f-fM vam">共</tt>
                                                <tt class="fsize14 c-999 f-fM vam">${fn:length(course.courseList)}</tt>
                                                <tt class="fsize14 c-999 f-fM vam">门课程</tt>
                                            </span>
                                            <span class="fl ml30">
                                                <tt class="fsize14 c-999 f-fM vam">价格：</tt>
                                                <tt class="fsize14 c-master f-fM vam">￥${course.currentPrice }</tt>
                                            </span>
                                                </section>
                                                <section class="hLh30 mt10 of nr">
                                             <span class="fl u-tc-teach">
                                                <tt class="fsize14 c-999 f-fM vam">主讲：</tt>
                                                 <c:forEach items="${course.teacherList}" var="teacher">
                                                     <tt class="fsize14 c-999 f-fM vam ">${teacher.name}</tt>
                                                 </c:forEach>
                                            </span>
                                            <span class="fl ml30">
                                                <tt class="fsize14 c-999 f-fM vam">有效期：</tt>
                                                <tt class="fsize14 c-999 f-fM vam ">从购买之日起</tt>
                                                <tt class="fsize14 c-master f-fM vam ">${course.loseTime}</tt>
                                                <tt class="fsize14 c-999 f-fM vam ">天</tt>
                                            </span>
                                                </section>
                                            </div>
                                        </div>
                                        <div class="mt20 u-live-sch">
                                    <span class="endTime undis">
                                    <c:set var="nowDate">
                                        <fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd " type="date"/>
                                    </c:set>
                                    <c:if test="${course.endTime>=nowDate}">
                                        <tt class="fsize13 c-999 f-fM vam"><fmt:formatDate value="${course.endTime}" pattern="yyyy-MM-dd"/></tt>
                                    </c:if>
                                    <c:if test="${course.endTime<nowDate}">
                                        <tt class="fsize13 c-blue f-fM vam">已过期</tt>
                                    </c:if>
                                    </span>
                                            <a href="javascript:void(0);" onclick="getCoursePackageList(${course.courseId},this)" class="sch-btn sch-open">课程表展开∨</a>
                                        </div>
                                    </section>
                                </c:forEach>
                            </c:if>
                                <%--如果是直播课--%>
                            <c:if test="${queryCourse.sellType=='LIVE'}">
                                <%--<c:forEach items="${courseList}" var="course">
                                    <section class="u-mycou-tc-list">
                                        <div class="u-tc-l-top">
                                            <div class="clearfix">
                                                <aside class="u-tc-pic">
                                                    <c:choose>
                                                        <c:when test="${not empty course.logo}">
                                                            <img src="${staticServer}${course.logo}" class="img-responsive" alt="${course.courseName}" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <img src="${ctx}/static/inxweb/img/default-img.gif" class="img-responsive" alt="${course.courseName}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </aside>
                                                <h3 class="hLh30 txtOf f-fM name"> <a href="${ctx}/front/couinfo/${course.courseId }" title="" class="fsize18 c-333 f-fM"> <tt class="c-master fsize18 f-fM">[ 直播 ]&nbsp;</tt>${course.courseName}</a> </h3>
                                                <section class="hLh30 of nr">
                                                    <span class="fl">
                                                        <tt class="fsize14 c-999 f-fM vam">课时：</tt>
                                                        <tt class="fsize14 c-999 f-fM vam">${course.lessionNum }</tt>
                                                    </span>
                                                    <span class="fl u-tc-teach-live ml30">
                                                        <tt class="fsize14 c-999 f-fM vam">主讲：</tt>
                                                         <c:forEach items="${course.teacherList}" var="teacher">
                                                             <tt class="fsize14 c-999 f-fM vam ">${teacher.name}</tt>
                                                         </c:forEach>
                                                    </span>
                                                </section>
                                                <section class="hLh30 of nr">
                                                     <span class="fl u-tc-time-live">
                                                        <tt class="fsize14 c-999 f-fM vam">直播时间：</tt>
                                                        <tt class="fsize14 c-333 f-fM vam ">
                                                               <fmt:formatDate value="${course.liveBeginTime}" pattern="yyyy-MM-dd" type="both"/>
                                                            ~
                                                                <fmt:formatDate value="${course.liveEndTime}" pattern="yyyy-MM-dd" type="both"/>
                                                        </tt>
                                                    </span>
                                                    <span class="fl ml30 u-tc-yxq-live">
                                                        <tt class="fsize14 c-999 f-fM vam">有效期：</tt>
                                                        <tt class="fsize14 c-999 f-fM vam ">从购买之日起</tt>
                                                        <tt class="fsize14 c-master f-fM vam ">${course.loseTime}</tt>
                                                        <tt class="fsize14 c-999 f-fM vam ">天</tt>
                                                    </span>
                                                </section>
                                                <section class="hLh30 of nr">
                                                     <span class="">
                                                        <tt class="fsize14 c-999 f-fM vam">状态：</tt>
                                                        <tt class="fsize14 c-999 f-fM vam "><img src="${ctx}/static/inxweb/img/live-wks.png" class="disIb vam"></tt>
                                                        <a href="/front/liveIndex" class="fsize14 c-master f-fM vam ml30">查看详情</a>
                                                    </span>
                                                </section>
                                            </div>
                                            <div class="mobile-u-live-time">

                                            </div>
                                        </div>
                                        <div class="mt20 u-live-sch">
                                            <span class="endTime undis">
                                            <c:set var="nowDate">
                                                <fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd " type="date"/>
                                            </c:set>
                                            <c:if test="${course.endTime>=nowDate}">
                                                <tt class="fsize13 c-999 f-fM vam"><fmt:formatDate value="${course.endTime}" pattern="yyyy-MM-dd"/></tt>
                                            </c:if>
                                            <c:if test="${course.endTime<nowDate}">
                                                <tt class="fsize13 c-blue f-fM vam">已过期</tt>
                                            </c:if>
                                            </span>
                                            <a href="javascript:void(0);" onclick="getCourseKpointList(${course.courseId},this)" class="sch-btn sch-open">直播章节展开∨</a>
                                            <div class="lh-menu-wrap mt20"></div>
                                        </div>
                                    </section>
                                </c:forEach>--%>
                                <c:forEach items="${courseList}" var="course">
                                    <dl>
                                        <dd class="u-order-list">
                                            <section class="w50pre disIb vam u-o-left">
                                                <div class="u-o-l-infor">
                                                    <div class="c-cou-item">
                                                        <div class="">
                                                            <div>
                                                                    <%-- 跳转课程详情页面--%>
                                                                <a class="u-ol-pic u-ol-pic-new"
                                                                    <%--如果不是过期课程正常进入个人中心课程详情--%>
                                                                        <c:if test="${ifOver!='true'}">
                                                                            href="${ctx}/uc/courseInfo/${course.courseId}"
                                                                        </c:if>
                                                                    <%--如果是过期课程进入前台课程详情--%>
                                                                        <c:if test="${ifOver=='true'}">
                                                                            href="${ctx}/front/liveinfo/${course.courseId}"
                                                                        </c:if>
                                                                   title="">
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
                                                                    <a
                                                                        <%--如果不是过期课程正常进入个人中心课程详情--%>
                                                                            <c:if test="${ifOver!='true'}">
                                                                                href="${ctx}/uc/courseInfo/${course.courseId}"
                                                                            </c:if>
                                                                        <%--如果是过期课程进入前台课程详情--%>
                                                                            <c:if test="${ifOver=='true'}">
                                                                                href="${ctx}/front/liveinfo/${course.courseId}"
                                                                            </c:if>
                                                                            title="${course.courseName}" class="fsize16 c-333">

                                                                        <c:if test="${course.sellType=='COURSE'}">
                                                                            <tt class="c-master fsize16 f-fM">[ 录播课 ]&nbsp;</tt>
                                                                        </c:if>
                                                                        <c:if test="${course.sellType=='LIVE'}">
                                                                            <tt class="c-master fsize16 f-fM">[ 直播 ]&nbsp;</tt>
                                                                        </c:if>
                                                                        <c:if test="${course.sellType=='PACKAGE'}">
                                                                            <tt class="c-master fsize16 f-fM">[ 套餐 ]&nbsp;</tt>
                                                                        </c:if>
                                                                            ${course.courseName}
                                                                    </a>
                                                                </h6>
                                                                <section class="hLh20 mt10 of">
                                                                <span class="fr">
                                                                    <tt class="fsize13 c-999 f-fM vam">课时：</tt>
                                                                    <tt class="fsize13 c-999 f-fM vam">${course.lessionNum}</tt>
                                                                </span>
                                                                <span class="fl">
                                                                    <tt class="fsize13 c-999 f-fM vam">价格：</tt>
                                                                    <tt class="fsize16 c-master f-fM vam">￥${course.currentPrice}</tt>
                                                                </span>
                                                                </section>
                                                                <section class="hLh20 mt10 of">
                                                                <span class="u-tc-time-live">
                                                                    <tt class="fsize13 c-999 f-fM vam">直播日期：</tt>
                                                                    <tt class="fsize13 c-333 f-fM vam ">
                                                                        <fmt:formatDate value="${course.liveBeginTime}" pattern="yyyy-MM-dd" type="both"/>
                                                                        ~
                                                                        <fmt:formatDate value="${course.liveEndTime}" pattern="yyyy-MM-dd" type="both"/>
                                                                    </tt>
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
                                                            <c:set var="studyPercent">
                                                                <fmt:formatNumber value="${course.studyPercent}" pattern="00"/>
                                                            </c:set>
                                                            <div class="lev-num-wrap" title="已学${studyPercent}%">
                                                                <aside class='lev-num-bar <c:if test="${studyPercent>=100}">bg-orange</c:if> <c:if test="${studyPercent<100}">bg-green</c:if>' style="width: ${studyPercent}%;"></aside>
                                                                <span class="lev-num"><big>${studyPercent}%</big>/<small>100%</small></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </section>
                                            <section class="col-2 disIb vam u-o-right">
                                                <div class="tar u-o-r-btn-infor">
                                                    <p class="mb20">
                                                        <a href="${ctx}/uc/courseInfo/${course.courseId}" class="cou-kscp" title="查看详情">查看详情</a>
                                                            <%--<c:if test="${not empty course.courseStudyhistory.kpointId&&queryCourse.isoverdue=='false'}">
                                                                <a class="cou-kscp" target="_blank" href="${ctx}/uc/play/${course.courseId}/${course.courseStudyhistory.kpointId}" title="继续学习">继续学习</a>
                                                            </c:if>
                                                            <c:if test="${ empty course.courseStudyhistory.kpointId&&queryCourse.isoverdue=='false'}">
                                                                <a class="cou-kscp" href="${ctx}/uc/play/${course.courseId}/0" title="开始学习">开始学习</a>
                                                            </c:if>--%>
                                                    </p>
                                                </div>
                                            </section>
                                            <div class="clear"></div>
                                        </dd>
                                    </dl>

                                </c:forEach>
                            </c:if>
                        </div>
                        <jsp:include page="/WEB-INF/view/common/front_page.jsp" />
                    </c:if>
                    <%--<c:if test="${not empty courseList }">
                        <div class="u-course-list">
                            <article class="comm-course-list">
                                <ul class="clearfix">
                                    <c:forEach items="${courseList}" var="course">
                                        <li>
                                            <div class="cc-l-wrap">
                                                <section class="course-img">
                                                    <c:choose>
                                                        <c:when test="${not empty course.logo}">
                                                            <img src="${staticServer}${course.logo}" class="img-responsive" alt="${course.courseName}" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <img src="${ctx}/static/inxweb/img/coures.gif" class="img-responsive" alt="${course.courseName}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <div class="cc-mask">
                                                        <a href="${ctx}/uc/courseInfo/${course.courseId}" title="" class="comm-btn c-btn-1">继续学习</a>
                                                    </div>
                                                </section>
                                                <h3 class="hLh30 txtOf mt10">
                                                    <a href="${ctx}/uc/courseInfo/${course.courseId}" title="${course.courseName}" class="course-title fsize18 c-333">${course.courseName}</a>
                                                </h3>
                                                <section class="mt10 of">
                                                    <div class="time-bar-wrap">
                                                        <div class="lev-num-wrap" title="已学${course.studyPercent}%">
                                                            <aside class='lev-num-bar <c:if test="${course.studyPercent>=100}">bg-orange</c:if> <c:if test="${course.studyPercent<100}">bg-green</c:if>' style="width: ${course.studyPercent}%;"></aside>
                                                            <span class="lev-num"><big>${course.studyPercent}%</big>/<small>100%</small></span>
                                                        </div>
                                                    </div>
                                                </section>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                                <div class="clear"></div>
                            </article>
                            <!-- 公共分页 开始 -->
                            <jsp:include page="/WEB-INF/view/common/front_page.jsp" />
                            <!-- 公共分页 结束 -->
                            <form method="post" id="searchForm" action="${ctx}/uc/index">
                                <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
                            </form>
                        </div>
                    </c:if>--%>

                </div>
            </section>
            <!-- /我的课程 -->
        </div>
    </article>
<script type="text/javascript" src="${ctx}/static/inxweb/course/uc_index.js"></script>
</body>
</html>