<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>订单管理列表</title>
    <script type="text/javascript" src="${ctx}/static/common/jquery-1.11.1.min.js"></script>


    <script type="text/javascript" src="${ctx}/static/admin/order/order.js"></script>
    <script>
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });
        });
        /**
         * 订单列表导出
         */
        function orderExcel() {
            $("#searchForm").prop("action", "/admin/order/orderExport");
            $("#searchForm").submit();
            $("#searchForm").prop("action", "/admin/order/showorderlist");
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>订单管理列表</span>
    </legend>
    <div class="layui-field-box">

        <form class="layui-form" action="${ctx}/admin/order/showorderlist" method="post" id="searchForm">
            <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1"/>
            <input placeholder="学员ID" type="hidden" id="userId" name="queryOrder.userId" value="${queryOrder.userId}"/>


            <div class="layui-form-item ">
                <label class="layui-form-label">订单号</label>
                <div class="layui-input-inline">
                    <input class="layui-input" placeholder="订单号" name="queryOrder.keyWord" value="${queryOrder.keyWord}"/>
                </div>
                <label class="layui-form-label">学员昵称</label>
                <div class="layui-input-inline">
                    <input class="layui-input" placeholder="学员昵称" name="queryOrder.showName" value="${queryOrder.showName}"/>
                </div>
                <label class="layui-form-label">类型</label>
                <div class="layui-input-inline">
                    <select name="queryOrder.orderType" id="orderType">
                        <option value="">全部</option>
                        <option value="">全部</option>
                        <option value="COURSE"  ${queryOrder.orderType=='COURSE'?'selected':''}>课程</option>
                        <c:if test="${serviceSwitch.member=='ON'}">
                            <option value="MEMBER" ${queryOrder.orderType=='MEMBER'?'selected':''}>会员</option>
                        </c:if>
                        <option value="ACCOUNT" ${queryOrder.orderType=='ACCOUNT'?'selected':''}>账户</option>
                    </select>
                </div>
                <label class="layui-form-label">状态</label>
                <div class="layui-input-inline">
                    <select name="queryOrder.states" id="states">
                        <option value="">全部</option>
                        <option value="">全部</option>
                        <option value="SUCCESS"  ${queryOrder.states=='SUCCESS'?'selected':''}>已支付</option>
                        <option value="INIT" ${queryOrder.states=='INIT'?'selected':''}>未支付</option>
                        <option value="CANCEL" ${queryOrder.states=='CANCEL'?'selected':''}>已取消</option>
                        <option value="REFUND" ${queryOrder.states=='REFUND'?'selected':''}>退款</option>
                    </select>
                </div>
                <a title="查询订单" onclick="$('#searchForm').submit();" class="layui-btn layui-btn-small layui-btn-danger"
                   href="javascript:void(0)">
                    查询
                </a>
                <a title="返回" onclick="history.go(-1)" class="layui-btn layui-btn-small layui-btn-danger" href="javascript:void(0)">
                    返回
                </a>
            </div>


        </form>
        <table class="layui-table">
            <thead>
            <tr>
                <td align="center">订单号</td>
                <td align="center">创建时间</td>
                <td align="center">学员昵称</td>
                <%--<td align="center">学员邮箱</td>
                <td align="center">学员手机</td>--%>
                <td align="center">金额</td>
                <td align="center">状态</td>
                <td align="center">类型</td>
                <%--<td align="center">支付时间</td>--%>
                <td align="center">开通人</td>
                <td align="center">操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orderList}" var="order" varStatus="index">
                <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                    <td align="center">${order.orderNo}</td>
                    <td align="center">
                        <fmt:formatDate value="${order.createTime}" pattern="yyyy/MM/dd"/>
                    </td>
                    <td align="center">${order.userName}</td>
                        <%--<td align="center">${order.email}</td>
                        <td align="center">${order.mobile}</td>--%>
                    <td align="center">${order.sumMoney}</td>

                    <td align="center">
                        <c:if test="${order.states=='INIT'}">
                            <font color="#e33b00">未支付</font>
                        </c:if>
                        <c:if test="${order.states=='SUCCESS'}">
                            <font color="#00e33b">已支付</font>
                        </c:if>
                        <c:if test="${order.states=='REFUND'}">
                            退款
                        </c:if>
                        <c:if test="${order.states=='CANCEL'}">已取消</c:if>
                        <c:if test="${order.states=='CLOSED'}">
                            <font color="orange">已关闭</font>
                        </c:if>
                    </td>
                    <td align="center">
                        <c:if test="${order.orderType=='COURSE'}">课程</c:if>
                        <c:if test="${order.orderType=='MEMBER'}">会员</c:if>
                        <c:if test="${order.orderType=='ACCOUNT'}">账户</c:if>
                    </td>
                        <%--<td align="center">
                            <c:choose>
                                <c:when test="${order.payTime!=null}">
                                    <fmt:formatDate value="${order.payTime}" pattern="yyyy/MM/dd" />
                                </c:when>
                                <c:otherwise>--</c:otherwise>
                            </c:choose>
                        </td>--%>
                    <td align="center">
                        <c:choose>
                            <c:when test="${order.loginName!=null}">${order.loginName}</c:when>
                            <c:otherwise>--</c:otherwise>
                        </c:choose>
                    </td>
                    <td align="center">
                        <button onclick="window.location.href='${ctx}/admin/order/orderinfo/${order.orderId}'"
                                class="layui-btn layui-btn-small" type="button">详情
                        </button>
                        <samp id="initcancel${order.orderId}">
                            <c:if test="${order.states=='INIT'}">
                                <button onclick="cancelOrRegain('CANCEL',${order.orderId},'${order.orderNo}',this)"
                                        class="layui-btn layui-btn-small" type="button">取消
                                </button>
                            </c:if>
                            <c:if test="${order.states=='CANCEL'}">
                                <button onclick="cancelOrRegain('INIT',${order.orderId},'${order.orderNo}',this)"
                                        class="layui-btn layui-btn-small" type="button">恢复
                                </button>
                            </c:if>
                        </samp>
                        <samp id="auditing${order.orderId}">
                            <c:if test="${order.states=='INIT'}">
                                <button onclick="auditing('${order.orderNo}',this)" class="layui-btn layui-btn-small"
                                        type="button">开通
                                </button>
                            </c:if>
                        </samp>
                        <c:if test="${order.states=='SUCCESS'}">
                            <button onclick="refund('${order.orderId}',this)" class="layui-btn layui-btn-small"
                                    type="button">退款
                            </button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
    </div>
</fieldset>
</body>
</html>