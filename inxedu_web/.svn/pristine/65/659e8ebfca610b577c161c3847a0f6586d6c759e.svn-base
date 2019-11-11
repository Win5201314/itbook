<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>我的笔记</title>
</head>
<body>
<article class="col-80 fl">
    <form id="searchForm" action="${ctx}/uc/courseNote">
        <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
    </form>
    <div class="u-r-cont">
        <section>
            <div class="">
                <section class="u-r-tit-all">
                    <h2 class="clearfix">
                        <span class="f-fH unFw current">我的笔记</span>
                    </h2>
                </section>
               <div class="">
                   <section class="no-data-wrap" <c:if test="${fn:length(courseNoteList)>0}">style="display: none" </c:if>>
                       <em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">还没有任何笔记！~<a href="${ctx}/uc/index" class="c-master">马上去学习</a></span>
                   </section>
                   <section class="u-all-bj-list">
                       <ul>
                           <c:forEach items="${courseNoteList}" var="courseNote">
                               <li>
                                   <div class="u-bj-warp">
                                       <span class="bj-ico">&nbsp;</span>
                                       <div class="u-bj-time">
                                           <p class="hLh30 f-fM c-666 fsize16 tac">
                                               <fmt:formatDate value="${courseNote.updateTime}" pattern="yyyy-MM-dd" />
                                           </p>
                                          <%-- <p class="hLh20 f-fM c-666 fsize12 tar"> <fmt:formatDate value="${courseNote.updateTime}" pattern="HH:mm:ss" /></p>--%>
                                       </div>
                                       <div class="u-bj-item">
                                           <div class="u-bj-it-time">
                                               <span class="pc-time"><fmt:formatDate value="${courseNote.updateTime}" pattern="HH:mm:ss" /></span>
                                               <span class="mobil-time"><fmt:formatDate value="${courseNote.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
                                           </div>
                                           <section class="u-bj-i-txt mt10">
                                               <p class="c-333 fsize14">
                                                   <tt class="c-master vam f-fM">笔记内容：</tt>
                                                   <tt class="vam f-fM">${courseNote.content}</tt>
                                               </p>
                                           </section>
                                           <section class="u-bj-b-warp mt20">
                                                <div class="clearfix">
                                                    <div class="box-bj">
                                                        <p class="hLh30 fsize14 c-999 f-fM txtOf l-box-bj">
                                                            <tt class="c-999 fsize14 f-fM vam">课程：</tt>
                                                            <a href="${ctx}/uc/courseInfo/${courseNote.courseId}" class="c-333 fsize14 f-fM vam">${courseNote.courseName}</a>
                                                        </p>
                                                    </div>
                                                    <div class="box-bj">
                                                        <p class="hLh30 fsize14 c-999 f-fM txtOf l-box-bj">
                                                            <tt class="c-999 fsize14 f-fM vam">章节：</tt>
                                                            <a href="${ctx}/uc/play/${courseNote.courseId}/${courseNote.kpointId}" class="c-333 fsize14 f-fM vam">${courseNote.kpointName}</a>
                                                        </p>
                                                    </div>
                                                    <div class="tar u-o-right box-bj">
                                                        <a class="cou-kscp mr20" target="_blank" title="编辑笔记" href="${ctx}/uc/play/${courseNote.courseId}/${courseNote.kpointId}?isCourseNote='true'">编辑笔记</a>
                                                        <a class="cou-kscp gmkc" href="javascript:void(0)" onclick="delConfirm('${courseNote.id}',this)">删除笔记</a>
                                                    </div>
                                                </div>
                                           </section>
                                       </div>
                                   </div>
                               </li>
                           </c:forEach>
                       </ul>
                   </section>
                   <!-- 公共分页 开始 -->
                   <jsp:include page="/WEB-INF/view/common/front_page.jsp" />
                   <!-- 公共分页 结束 -->
               </div>
            </div>
        </section>
    </div>
</article>
<script>
    var tagObj="";
    function delConfirm(courseNoteId,obj){
        tagObj=obj;
        dialog("删除提示", "确认要删除这个笔记吗？", 2, "javascript:void(0)' onclick='deleteCourseNote("+courseNoteId+")");
    }

    function deleteCourseNote(courseNoteId) {
        $("#tisbutt,#dClose,#qujiao").click();
        $.ajax({
            url:'/courseNote/ajax/delNote',
            type:'post',
            data:{"courseNoteId":courseNoteId},
            dataType: 'json',
            success: function (result) {
                if (result.success==true){
                    $(tagObj).parent().parent().parent().parent().parent().parent().remove();
                    if ($(".u-all-bj-list ul li").size()==0){
                        $(".no-data-wrap").show()
                    }
                    msgshow("删除成功","true","3000");
                }else{
                    msgshow(result.message,"false","3000");
                }
            }
        })
    }
</script>
</body>
</html>
