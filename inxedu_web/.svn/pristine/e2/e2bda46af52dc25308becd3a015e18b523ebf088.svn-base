<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>用户账户历史信息</title>
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-timepicker-addon.css?v=${v}"/>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript">
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });
            //各种基于事件的操作，下面会有进一步介绍
        });

        //日历空间
        $(function () {
            $("#startDate,#endDate").datetimepicker({
                regional: "zh-CN",
                changeYear: true,
                changeMonth: true,
                dateFormat: "yy-mm-dd",
                timeFormat: 'HH:mm:ss',
            });
            $("#actHistoryType").val("${queryUserAccounthistory.actHistoryType}");
            $("#bizType").val("${queryUserAccounthistory.bizType}");
            /*显示当前状态查询条件*/
            $("#status").val("${queryUserAccounthistory.status}");

        });

        function clean() {

            $("#startDate").val('');
            $("#endDate").val('');
            $("#actHistoryType").val("");
            $("#bizType").val("");
        }
        /*提现*/
        function examineDrawMoney(accountHistoryId, status) {
            if (status == 'SUCCESS') {
                if (!confirm("请确认是否已提现！")) {
                    return;
                } else {
                    var description = "提现成功";
                }
            }
            if (status == 'FAIL') {
                if (confirm("请确认是否提现失败！")) {
                    var description = prompt("请输入备注：");
                    if (!description) {
                        return;
                    }
                }
            }
            $.ajax({
                url: baselocation + "/admin/account/examineDrawMoney",
                type: "post",
                dataType: "json",
                data: {"accountHistoryId": accountHistoryId, "status": status, "description": description},
                success: function (result) {
                    if (result.success == true) {
                        window.location.reload();
                    }
                }
            })
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>学员账户管理</span> &gt; <span>学员提现记录</span>
    </legend>
    <div class="layui-field-box">

        <form action="${ctx}/admin/account/drawMoney_list" name="searchForm" id="searchForm" method="post" class="layui-form">
            <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="1"/>
            <input type="hidden" name="queryUserAccounthistory.userId" value="${queryUserAccounthistory.userId}">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-inline">
                        <select id="status" name="queryUserAccounthistory.status">
                            <option value="">全部</option>
                            <option value="">全部</option>
                            <option value="SUCCESS">通过</option>
                            <option value="FAIL">失败</option>
                            <option value="NOTVIEWED">未审批</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">提现申请</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="queryUserAccounthistory.startTime" placeholder="开始时间" value="<fmt:formatDate value="${queryUserAccounthistory.startTime}" pattern="yyyy-MM-dd HH:mm:ss "/>" onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})">
                    </div>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="queryUserAccounthistory.endTime" placeholder="结束时间" value="<fmt:formatDate value="${queryUserAccounthistory.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})">
                    </div>
                </div>
                <div class="layui-inline">
                    <input type="submit" class="layui-btn layui-btn-small layui-btn-danger" value="查询" name="" />
                    <input type="button" class="layui-btn layui-btn-small layui-btn-danger" value="清空" name="" onclick="clean()" />
                </div>
            </div>
            <table cellspacing="0" cellpadding="0" border="0" width="100%" class="layui-table">
                <thead>
                <tr>
                    <td align="center"><span>姓名</span></td>
                    <td align="center"><span>邮箱</span></td>
                    <td align="center"><span>手机</span></td>
                    <td align="center"><span>提现金额</span></td>
                    <td align="center"><span>提现银行</span></td>
                    <td align="center"><span>卡号</span></td>
                    <td align="center"><span>申请时间</span></td>
                    <td align="center"><span>状态</span></td>
                    <td align="center"><span>操作</span></td>
                </tr>
                </thead>
                <tbody id="tabS_02" align="center">
                <c:if test="${userHisoty.size()>0}">
                    <c:forEach items="${userHisoty}" var="user">
                        <tr>
                            <td>${user.userName}</td>
                            <td>${user.email}</td>
                            <td>${user.mobile}</td>
                            <td>${user.trxAmount}</td>
                            <td>${user.bank}</td>
                            <td>${user.card}</td>
                            <td><fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss" type="both"/></td>
                            <td>
                                <c:if test="${user.status=='NOTVIEWED'}">
                                    未审批
                                </c:if>
                                <c:if test="${user.status=='SUCCESS'}">
                                    通过
                                </c:if>
                                <c:if test="${user.status=='FAIL'}">
                                    失败
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${user.status=='NOTVIEWED'}">
                                    <button onclick="examineDrawMoney(${user.id},'SUCCESS')" class="layui-btn layui-btn-small" type="button">
                                        通过
                                    </button>
                                    <button onclick="examineDrawMoney(${user.id},'FAIL')" class="layui-btn layui-btn-small" type="button">
                                        失败
                                    </button>
                                </c:if>
                                <c:if test="${user.status!='NOTVIEWED'}">
                                    <button onclick="window.location.href='${ctx}/admin/account/drawMoneyInfo/'+${user.id}" class="layui-btn layui-btn-small" type="button">
                                        详情
                                    </button>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>

                <c:if test="${userHisoty.size()==0||userHisoty==null}">
                    <tr>
                        <td align="center" colspan="16">
                            <div class="tips">
                                <span>还没有用户账户历史信息！</span>
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
