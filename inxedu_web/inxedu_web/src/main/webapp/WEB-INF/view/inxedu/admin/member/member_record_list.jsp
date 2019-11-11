<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>会员开通记录</title>
    <script type="text/javascript">
        var form;
        layui.use(['form'], function () {
            form = layui.form();
        });
        function cancel() {
            $("#email").val("");
            $("#memberType").val(0);
            form.render('select'); //刷新select选择框渲染
        }
        function updateMemberStatus(memId, type) {
            var message = "";
            if (type == 1) {//关闭
                message = "确定关闭该用户会员吗?";
            } else {
                message = "确定开启该用户会员吗";
            }
            if (confirm(message)) {
                $.ajax({
                    url: "${ctx}/admin/memberrecord/close",
                    type: "post",
                    data: {"trxorderId": memId, "authStatus": type},
                    dataType: "json",
                    success: function (result) {
                        if (result.success) {
                            msgshow("会员状态已更新!", "true", "500");
                            window.location.reload();
                        } else {
                            msgshow(result.message, "false", "1000")

                        }
                    }
                })
            }
        }
        /*按时间升序或降序查询*/
        function orderSortByTime(obj) {
            var orderType = $("#orderByTime").val();
            /*如果是升序或者没有时间排序*/
            if (orderType == "asc" || orderType == "" || orderType == null) {
                $("#orderByTime").val("desc")
            }
            /*如果是降序*/
            if (orderType == "desc") {
                $("#orderByTime").val("asc")
            }
            $("#searchForm").submit();
        }
    </script>

</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>会员开通管理</span>
        &gt;
        <span>会员开通列表</span>
    </legend>
    <div class="layui-field-box">
        <form class="layui-form" action="${ctx}/admin/memberrecord/memberrecords" name="searchForm" id="searchForm"
              method="post"  class="layui-form">
            <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="1"/>
            <input id="orderByTime" type="hidden" name="memberRecordDTO.orderByTime"
                   value="${memberRecordDTO.orderByTime}"/>


            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">帐号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="memberRecordDTO.keyWord" id="email" placeholder="邮箱/手机号/登录帐号"
                               autocomplete="off" class="layui-input" value="${memberRecordDTO.keyWord}">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">类型</label>
                    <div class="layui-input-inline">
                        <select name="memberRecordDTO.memberType" id="memberType">
                            <option value="0">--请选择--</option>
                            <c:forEach items="${memberTypes }" var="memberType">
                                <option value="${memberType.id }"
                                        <c:if test="${memberType.id==memberRecordDTO.memberType }">selected="selected"</c:if>>${memberType.title }</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="orderSortByTime(this);"><c:if test="${memberRecordDTO.orderByTime=='asc'|| empty memberRecordDTO.orderByTime}">到期时间降序</c:if><c:if test="${memberRecordDTO.orderByTime=='desc'}">到期时间升序</c:if></button>
                    <button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="goPage(1);">查询</button>
                    <button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="cancel();" type="button">清空</button>
                </div>
            </div>


            <table class="layui-table">
                <thead>
                <tr>
                    <th width="10%"><span>ID</span></th>
                    <th><span>用户邮箱</span></th>
                    <th><span>用户手机</span></th>
                    <th><span>登录帐号</span></th>
                    <th><span>类型</span></th>
                    <th><span>到期时间</span></th>
                    <th><span>首次开通时间</span></th>
                    <th><span>状态</span></th>
                    <th><span>操作</span></th>
                </tr>
                </thead>
                <tbody id="tabS_02" align="center">
                <c:if test="${memberRecordDTOs.size()>0}">
                    <c:forEach items="${memberRecordDTOs}" var="memberRecord" varStatus="status">
                        <tr>
                            <td>${status.count }</td>
                            <td>
                                    ${memberRecord.user.email }
                                <c:if test="${memberRecord.user.email == null || memeberRecord.user.email == ''}}">--</c:if>
                            </td>
                            <td>
                                    ${memberRecord.user.mobile}
                                <c:if test="${memberRecord.user.mobile == null || memeberRecord.user.mobile == ''}}">--</c:if>
                            </td>
                            <td>
                                    ${memberRecord.user.loginAccount}
                                <c:if test="${memberRecord.user.loginAccount == null || memeberRecord.user.loginAccount == ''}">--</c:if>
                            </td>
                            <td>${memberRecord.memberTitle}</td>
                            <td><fmt:formatDate value="${memberRecord.endDate}" type="both"
                                                pattern="yyyy-MM-dd HH:mm"/></td>
                            <td><fmt:formatDate value="${memberRecord.beginDate}" type="both"
                                                pattern="yyyy-MM-dd HH:mm"/></td>
                            <td>
                                <c:if test="${memberRecord.status=='SUCCESS'}">
                                    正常
                                </c:if>
                                <c:if test="${memberRecord.status=='CLOSED'}">
                                    关闭
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${memberRecord.status=='SUCCESS'}">
                                <button onclick="updateMemberStatus(${memberRecord.id},'CLOSED')"
                                        class="layui-btn layui-btn-small" type="button">关闭
                                </button>
                                </c:if>
                                <c:if test="${memberRecord.status=='CLOSED'}">
                                <button onclick="updateMemberStatus(${memberRecord.id},'SUCCESS')"
                                        class="layui-btn layui-btn-small" type="button">开启
                                </button>
                                </c:if>
                                <button onclick="window.location.href=${cxt}'/admin/member/mrecordinfo/${memberRecord.id}'"
                                        class="layui-btn layui-btn-small" type="button">查看详情
                                </button>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${memberRecordDTOs.size()==0||memberRecordDTOs==null}">
                    <tr>
                        <td align="center" colspan="16">
                            <div class="tips">
                                <span>还没有会员开通！</span>
                            </div>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </form>
        <!-- /pageBar begin -->
        <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
    </div>
</body>
</html>
