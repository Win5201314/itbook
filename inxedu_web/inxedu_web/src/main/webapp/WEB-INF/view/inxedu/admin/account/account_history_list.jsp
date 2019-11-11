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
        });

        function clean() {

            $("#startDate").val('');
            $("#endDate").val('');
            $("#actHistoryType").val("");
            $("#bizType").val("");
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>学员账户管理</span> &gt; <span>账户记录</span>
    </legend>
    <div class="layui-field-box">
        <form action="${ctx}/admin/account/detailed_list" name="searchForm" id="searchForm" method="post" class="layui-form">
            <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="1"/>
            <input type="hidden" name="queryUserAccounthistory.userId" value="${queryUserAccounthistory.userId}"/>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">交易类型</label>
                    <div class="layui-input-inline">
                        <select name="queryUserAccounthistory.actHistoryType" id="actHistoryType">
                            <option value="">--请选择--</option>
                            <option value="">全部</option>
                            <option value="SALES">消费</option>
                            <option value="REFUND">退款</option>
                            <option value="VMLOAD">充值卡充值</option>
                            <option value="CASHLOAD">现金充值</option>
                            <option value="ADMINLOAD">后台充值</option>
                            <option value="ADMINREFUND">后台扣款</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">业务类型</label>
                    <div class="layui-input-inline">
                        <select name="queryUserAccounthistory.bizType" id="bizType">
                            <option value="">--请选择--</option>
                            <option value="">全部</option>
                            <option value="COURSE">课程订单</option>
                            <option value="MEMBER">会员订单</option>
                            <option value="BOOK">图书订单</option>
                            <option value="CARDLOAD">充值卡订单</option>
                            <option value="ADMIN">后台操作</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">操作时间</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="queryUserAccounthistory.startTime" placeholder="开始时间" value="<fmt:formatDate value="${queryUserAccounthistory.startTime}" pattern="yyyy-MM-dd "/>" onclick="layui.laydate({elem: this, festival: true})">
                    </div>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="queryUserAccounthistory.endTime" placeholder="结束时间" value="<fmt:formatDate value="${queryUserAccounthistory.endTime}" pattern="yyyy-MM-dd "/>" onclick="layui.laydate({elem: this, festival: true})">
                    </div>
                </div>
                <div class="layui-inline">
                    <input type="submit" class="layui-btn layui-btn-small layui-btn-danger" value="查询" name=""/>
                    <input type="button" class="layui-btn layui-btn-small layui-btn-danger" value="清空" name="" onclick="clean()"/>
                </div>
            </div>
            <table cellspacing="0" cellpadding="0" border="0" width="100%" class="layui-table">
                <thead>
                <tr>
                    <td align="center"><span>当前余额</span></td>
                    <td align="center"><span>现金余额</span></td>
                    <td align="center"><span>课程卡余额</span></td>
                    <td align="center"><span>交易金额</span></td>
                    <td align="center"><span>交易类型</span></td>
                    <td align="center"><span>业务类型</span></td>
                    <td align="center"><span>操作时间</span></td>
                </tr>
                </thead>
                <tbody id="tabS_02" align="center">
                <c:if test="${userHisoty.size()>0}">
                    <c:forEach items="${userHisoty}" var="user">
                        <tr>
                            <td>${user.balance}</td>
                            <td>${user.cashAmount}</td>
                            <td>${user.vmAmount}</td>
                            <td>${user.trxAmount}</td>
                            <td>
                                <c:if test="${user.actHistoryType =='SALES'}">
                                    消费
                                </c:if>
                                <c:if test="${user.actHistoryType =='REFUND'}">
                                    退款
                                </c:if>
                                <c:if test="${user.actHistoryType =='VMLOAD'}">
                                    充值卡充值
                                </c:if>
                                <c:if test="${user.actHistoryType =='CASHLOAD'}">
                                    现金充值
                                </c:if>
                                <c:if test="${user.actHistoryType =='ADMINLOAD'}">
                                    后台充值
                                </c:if>
                                <c:if test="${user.actHistoryType =='ADMINREFUND'}">
                                    后台扣款
                                </c:if>
                                <c:if test="${user.actHistoryType =='BACKMONEY'}">
                                    返现
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${user.bizType=='COURSE'}">
                                    课程订单
                                </c:if>
                                <c:if test="${user.bizType=='MEMBER'}">
                                    会员订单
                                </c:if>
                                <c:if test="${user.bizType=='BOOK'}">
                                    图书订单
                                </c:if>
                                <c:if test="${user.bizType=='CARDLOAD'}">
                                    充值卡订单
                                </c:if>
                                <c:if test="${user.bizType=='ADMIN'}">
                                    后台操作
                                </c:if>
                            </td>
                            <td><fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss" type="both"/></td>
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
