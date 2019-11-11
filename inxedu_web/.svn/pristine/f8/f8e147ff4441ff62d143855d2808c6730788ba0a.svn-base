\
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>用户账户信息</title>
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript">
        function submitSearch() {
            $("#pageCurrentPage").val(1);
            $("#searchForm").submit();
        }
        $(function () {
            $("#startDate,#endDate").datetimepicker({
                regional: "zh-CN",
                changeYear: true,
                changeMonth: true,
                dateFormat: "yy-mm-dd ",
                timeFormat: "HH:mm:ss"
            });
        });
        /* function editAccount(userId,status,obj){
         var judge="";
         if(status=="ACTIVE"){//正常
         judge="确定恢复该账户吗？";
         }else{
         judge="确定冻结该账户吗？";
         }
         if(confirm(judge)){
         $.ajax({
         url:"${ctx}/admin/account/update/"+userId,
         data:{"status":status},
         type:"post",
         dataType:"json",
         success:function(result){
         if(result.success){
         if(status=="ACTIVE"){
         $(obj).html("冻结");
         $(obj).attr("onclick","editAccount("+userId+",'FROZEN',this)");
         $("#status"+userId).html("正常");
         layer.msg(result.message, {icon: 1, shift: 6});
         }else{
         $(obj).html("正常");
         $(obj).attr("onclick","editAccount("+userId+",'ACTIVE',this)");
         $("#status"+userId).html("冻结");
         layer.msg(result.message, {icon: 1, shift: 6});
         }
         }
         }
         });
         }
         } */
        function clean() {
            $("#nickname,#useremail,#startDate,#endDate,#mobile").val("");
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>学员管理</span> &gt; <span>学员账户</span>
    </legend>
    <div class="layui-field-box">

        <form action="${ctx}/admin/account/list" name="searchForm" id="searchForm" method="post" class="layui-form">
            <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="1"/>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">手机号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="user.mobile" value="${user.mobile}" id="mobile" class="layui-input"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">邮箱</label>
                    <div class="layui-input-inline">
                        <input type="text" name="user.email" value="${user.email}" id="useremail" class="layui-input"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <input type="button" class="layui-btn layui-btn-small layui-btn-danger" value="查询" name="" onclick="submitSearch()"/>
                    <input type="button" class="layui-btn layui-btn-small layui-btn-danger" value="清空" name="" onclick="clean()"/>
                </div>
            </div>
            <table cellspacing="0" cellpadding="0" border="0" class="layui-table" width="100%">
                <thead>
                <tr>
                    <td align="center">学员ID</td>
                    <td align="center">账号</td>
                    <td align="center">邮箱</td>
                    <td align="center">手机号</td>
                    <td align="center">创建时间</td>
                    <td align="center">更新时间</td>
                    <td align="center">账户余额</td>
                    <td align="center">冻结金额</td>
                    <!-- <th><span>银行入账</span></th>
                    <th><span>课程卡入账</span></th> -->
                   <%-- <td align="center">账户状态</td>--%>
                    <td align="center">操作</td>
                </tr>
                </thead>
                <tbody id="tabS_02" align="center">
                <c:if test="${userAccountList.size()>0}">
                    <c:forEach items="${userAccountList}" var="user">
                        <tr>
                            <td>${user.userId}</td>
                            <td>${user.userName}</td>
                            <td>${user.email}</td>
                            <td>${user.mobile}</td>
                            <td>
                                <fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>
                                <fmt:formatDate value="${user.lastUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>${user.balance}</td>
                            <td>${user.forzenAmount}</td>
                                <%-- <td>${user.cashAmount}</td>
                                <td>${user.vmAmount}</td> --%>
                                <%-- <td id="status${user.userId}">
                                <c:if test="${user.accountStatus=='ACTIVE'}">
                                    正常
                                </c:if>
                                <c:if test="${user.accountStatus=='FROZEN'}">
                                    冻结
                                </c:if>
                                </td> --%>
                            <td>
                                    <%-- <c:if test="${user.accountStatus=='ACTIVE'}">
                                    <a class="ml10 btn smallbtn btn-y attr762" href="javascript:void(0)" onclick="editAccount(${user.userId},'FROZEN',this)">冻结</a>
                                    </c:if> --%>
                                    <%-- <c:if test="${user.accountStatus=='FROZEN'}">
                                    <a class="ml10 btn smallbtn btn-y attr762" href="javascript:void(0)" onclick="editAccount(${user.userId},'ACTIVE',this)">正常</a>
                                    </c:if> --%>
                                <a class="layui-btn layui-btn-small" href="${ctx}/admin/account/info/${user.userId}/credit">充值</a>
                                <a class="layui-btn layui-btn-small" href="${ctx}/admin/account/info/${user.userId}/debit">扣款</a>
                                <a class="layui-btn layui-btn-small" href="${ctx}/admin/account/detailed_list?queryUserAccounthistory.userId=${user.userId}">账户记录</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${userAccountList.size()==0||userAccountList==null}">
                    <tr>
                        <td align="center" colspan="16">
                            <div class="tips">
                                <span>还没有用户账户信息！</span>
                            </div>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </form>
        <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
    </div>

</fieldset>
</body>
</html>

