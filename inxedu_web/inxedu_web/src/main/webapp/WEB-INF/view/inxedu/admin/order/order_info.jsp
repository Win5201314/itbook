<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>订单详情</title>
    <script type="text/javascript">
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;

            form.on('select(filter)', function (data) {
            });
        });
        /**
         * 课程延期 订单状态成功  流水状态过期  才可以进行延期
         * @param orderState detailState
         */
        function delayCourse(em) {
            $(em).parent().prev().prev().find("span").eq(0).hide();
            $(em).parent().prev().prev().find("span").eq(1).show();

        }

        function clickStopTIme(obj,detailId) {
            var stopTime = $(obj).prev().val();
            if (stopTime == "") {
                layer.msg("延期时间不能为空！", {icon: 5, shift: 6});
                return false;
            }
            var daoqishijian = $(obj).parent().prev().html();
            if (stopTime <= daoqishijian) {
                layer.msg("延期时间必须大于到期时间！", {icon: 5, shift: 6});
                return false;
            }
            if(confirm("确定延期吗?")){
                $.ajax({
                    url : baselocation+"/admin/order/delayorder",
                    data : {
                        "trxorderDetail.id" : detailId,
                        "trxorderDetail.authTime" : stopTime
                    },
                    dataType : "json",
                    type : "post",
                    async : false,
                    success : function(result) {
                        if (result.success == true) {
                            layer.msg("已延期", {icon: 1, shift: 6});
                            $(obj).parent().prev().show();
                            $(obj).parent().prev().html(stopTime);
                            $(obj).parent().hide();
                        }else{
                            layer.msg(result.message, {icon: 5, shift: 6});
                        }
                    },
                    error : function(error) {
                        layer.msg("error", {icon: 5, shift: 6});
                    }

                });
            }
        }

        /*
         * 取消延期
         */
        function clickStopTImequxiao(obj){
            $(obj).parent().hide();
            $(obj).parent().prev().show();
        }


        /*
         *关闭订单
         */
        function closeOrderDetail(trxOrderId){
            if(confirm("确定关闭此订单吗?")){
                $.ajax({
                    url : baselocation+"/admin/order/closeCourse/"+trxOrderId,
                    data : {
                    },
                    dataType : "json",
                    type : "post",
                    async : false,
                    success : function(result) {
                        if (result.success == true) {
                            layer.msg("关闭订单成功", {icon: 1, shift: 6});
                            window.location.reload();
                        }else{
                            layer.msg(result.message, {icon: 5, shift: 6});
                        }
                    },
                    error : function(error) {
                        layer.msg("error", {icon: 5, shift: 6});
                    }
                });
            }
        }
    </script>

</head>
<body>
<form action="${ctx }/admin/websiteProfile/update" method="post" id="addprofileForm" class="layui-form">
    <input type="hidden" name="type" value="${type}" />
    <div class="numb-box">
        <fieldset class="layui-elem-field">
            <legend>
                <a href="${ctx}/admin/order/showorderlist">
                <span>订单管理</span>
                </a>
                &gt;
                <span>订单详情</span>
                &gt;
                <a href="javascript:void(0)" class="fsize12 c-master f-fM" onclick="history.go(-1)" title="返回">
                    返 回
                </a>
            </legend>
            <div class="numb-box">
                <div class="commonWrap">
                    <div class="clearfix">
                        <div class="wrp50 fl">
                            <div class="mr50">
                                <div class="">
                                    <blockquote class="layui-elem-quote " style="margin-bottom: 0">
                                    <span class="fsize14 c-333 f-fM mr20">
                                        详细数据信息
                                    </span>
                                    </blockquote>
                                </div>
                                <div class="">
                                    <div class="divpaperMiddleid br-d" papermiddleid="333" sort="1">
                                        <div class="numb-box">
                                            <div class="layui-form-item">
                                                <div class="layui-form-item">
                                                    <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;订单ID</label>
                                                    <div class="layui-input-block">
                                                        <input class="layui-input layui-input-6 layui-radio-disbaled layui-disabled" value="${order.orderId}" disabled type="text">
                                                    </div>
                                                </div>
                                                <div class="layui-form-item">
                                                    <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;订单编号</label>
                                                    <div class="layui-input-block">
                                                        <input class="layui-input layui-input-6 layui-radio-disbaled layui-disabled" value="${order.orderNo}" disabled type="text">
                                                    </div>
                                                </div>
                                                <div class="layui-form-item">
                                                    <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;支付商户订单号</label>
                                                    <div class="layui-input-block">
                                                        <input class="layui-input layui-input-6 layui-radio-disbaled layui-disabled" value="${order.outTradeNo}" disabled type="text">
                                                    </div>
                                                </div>
                                                <div class="layui-form-item">
                                                    <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;用户昵称</label>
                                                    <div class="layui-input-block">
                                                        <input class="layui-input layui-input-6 layui-radio-disbaled layui-disabled" value="${user.showName}" disabled type="text">
                                                    </div>
                                                </div>
                                                <div class="layui-form-item">
                                                    <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;用户邮箱</label>
                                                    <div class="layui-input-block">
                                                        <input class="layui-input layui-input-6 layui-radio-disbaled layui-disabled" value="${user.email}" disabled type="text">
                                                    </div>
                                                </div>
                                                <div class="layui-form-item">
                                                    <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;用户手机</label>
                                                    <div class="layui-input-block">
                                                        <input class="layui-input layui-input-6 layui-radio-disbaled layui-disabled" value="${user.mobile}" disabled type="text">
                                                    </div>
                                                </div>
                                                <div class="layui-form-item">
                                                    <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;下单时间</label>
                                                    <div class="layui-input-block">
                                                        <input class="layui-input layui-input-6 layui-radio-disbaled layui-disabled" value="<fmt:formatDate value="${order.createTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>" disabled type="text">
                                                    </div>
                                                </div>
                                                <div class="layui-form-item">
                                                    <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;支付时间</label>
                                                    <div class="layui-input-block">
                                                        <input class="layui-input layui-input-6 layui-radio-disbaled layui-disabled" value="<fmt:formatDate value="${order.payTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>" disabled type="text">
                                                    </div>
                                                </div>
                                                <div class="layui-form-item">
                                                    <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;原始价</label>
                                                    <div class="layui-input-block">
                                                        <input class="layui-input layui-input-6 layui-radio-disbaled layui-disabled" value="${order.sumMoney}" disabled type="text">
                                                    </div>
                                                </div>
                                                <div class="layui-form-item">
                                                    <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;优惠金额</label>
                                                    <div class="layui-input-block">
                                                        <input class="layui-input layui-input-6 layui-radio-disbaled layui-disabled" value="${order.couponAmount}" disabled type="text">
                                                    </div>
                                                </div>
                                                <c:if test="${couponCodeDTO.couponCode!=null}">
                                                    <div class="layui-form-item">
                                                        <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;优惠券编码</label>
                                                        <div class="layui-input-block">
                                                            <input class="layui-input layui-input-6 layui-radio-disbaled layui-disabled" value="${couponCodeDTO.couponCode}" disabled type="text">
                                                        </div>
                                                    </div>
                                                </c:if>

                                                <div class="layui-form-item">
                                                    <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;支付类型</label>
                                                    <div class="layui-input-block">
                                                        <input class="layui-input layui-input-6 layui-radio-disbaled layui-disabled" value="<c:if test="${order.payType=='ALIPAY'}">支付宝</c:if>
                                                    <c:if test="${order.payType=='WEIXIN'}">微信</c:if>
                                                    <c:if test="${order.payType=='KUAIQIAN'}">块钱</c:if>
                                                    <c:if test="${order.payType=='FREE'}">赠送</c:if>
                                                    <c:if test="${order.payType=='INTEGRAL'}">积分</c:if>
                                                    <c:if test="${order.payType=='ACCOUNT'}">账户</c:if>
                                                    <c:if test="${order.payType=='YEEPAY'}">易宝</c:if>
                                                    <c:if test="${order.payType=='USERCARD'}">学员卡</c:if>
                                                    <c:if test="${order.payType=='CARD'}">课程卡</c:if>" disabled type="text">
                                                    </div>
                                                </div>
                                                <div class="layui-form-item">
                                                    <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;订单状态</label>
                                                    <div class="layui-input-block">
                                                        <input class="layui-input layui-input-6 layui-radio-disbaled layui-disabled" value="<c:if test="${order.states=='INIT'}">未支付</c:if>
                                                    <c:if test="${order.states=='SUCCESS'}">已支付</c:if>
                                                    <c:if test="${order.states=='REFUND'}">退款</c:if>
                                                    <c:if test="${order.states=='CANCEL'}">已取消</c:if>
                                                    <c:if test="${order.states=='CLOSED'}">已关闭</c:if>" disabled type="text">
                                                    </div>
                                                </div>
                                                <div class="layui-form-item">
                                                    <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;实际支付</label>
                                                    <div class="layui-input-block">
                                                        <input class="layui-input layui-input-6 layui-radio-disbaled layui-disabled" value="${order.orderAmount}" disabled type="text">
                                                    </div>
                                                </div>
                                                <c:if test="${order.states=='REFUND'}">
                                                    <div class="layui-form-item">
                                                        <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;退款金额</label>
                                                        <div class="layui-input-block">
                                                            <input class="layui-input layui-input-6 layui-radio-disbaled layui-disabled" value="${order.refundAmount}" disabled type="text">
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <div class="layui-form-item">
                                                    <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;订单备注</label>
                                                    <div class="layui-input-block layui-textarea-block">
                                                        <textarea class="layui-textarea layui-radio-disbaled layui-disabled" disabled>${order.description}</textarea>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="wrp50 fl">
                            <div class="">
                                <blockquote class="layui-elem-quote " style="margin-bottom: 0">
                                    <span class="fsize14 c-333 f-fM mr20">
                                        详细内容信息
                                    </span>
                                </blockquote>
                            </div>
                            <div class="">
                                <div >
                                    <div class="">
                                        <table class="layui-table" lay-even="" lay-skin="row">
                                            <thead>
                                            <tr>
                                                <%--<th width="10%"><span>ID</span></th>--%>
                                                <%--<th><span>下单时间</span></th>--%>

                                                <%--<th><span>支付时间</span></th>--%>
                                                <td align="center" width="13%"><span>课程名</span></td>
                                                <td align="center" width="7%"><span>原始价</span></td>
                                                <td align="center" width="7%"><span>销售价</span></td>
                                                <td align="center" width="7%"><span>订单状态</span></td>
                                                <td align="center" width="10%"><span>过期时间</span></td>
                                                <td align="center" width="14%"><span>描述</span></td>
                                                <td align="center" width="14%"><span>操作</span></td>
                                            </tr>
                                            </thead>
                                            <tbody id="tabS_02" align="center">
                                            <c:if test="${trxDetailList.size()>0}">
                                                <c:forEach items="${trxDetailList}" var="orderDetail">
                                                    <tr id="rem${orderDetail.id }">
                                                            <%--<td>${orderDetail.id}</td>--%>
                                                            <%--<td><fmt:formatDate value="${orderDetail.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>--%>

                                                            <%--<td><fmt:formatDate value="${orderDetail.payTime}" type="date"  pattern="yyyy-MM-dd HH:mm:ss"/></td>--%>
                                                        <td>${orderDetail.courseName}</td>
                                                        <td>${orderDetail.sourcePrice}</td>
                                                        <td>${orderDetail.currentPrice}</td>
                                                        <td>
                                                            <c:if test="${orderDetail.authStatus=='INIT'}">
                                                                未付款
                                                            </c:if> <c:if test="${orderDetail.authStatus=='SUCCESS'}">
                                                            支付成功
                                                        </c:if> <c:if test="${orderDetail.authStatus=='REFUND'}">
                                                            已退款
                                                        </c:if> <c:if test="${orderDetail.authStatus=='CLOSED'}">
                                                            已关闭
                                                        </c:if> <c:if test="${orderDetail.authStatus=='LOSED'}">
                                                            已过期
                                                        </c:if>
                                                        </td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${orderDetail.authTime!=null}">
                                                                    <span><fmt:formatDate value="${orderDetail.authTime}" type="date"  pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                    <span style="display:none;">
                                        <input type="text" value='<fmt:formatDate value="${orderDetail.authTime}" type="date"  pattern="yyyy-MM-dd HH:mm:ss"/>'  onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})" class="layui-input" style="width: 130px;"/>
                                        <input type="button" value="提交" onclick="clickStopTIme(this,${orderDetail.id})" class="layui-btn layui-btn-small layui-btn-danger">
                                        <input type="button" value="取消" onclick="clickStopTImequxiao(this)" class="layui-btn layui-btn-small layui-btn-danger">
                                    </span>
                                                                </c:when>
                                                                <c:otherwise>--</c:otherwise>
                                                            </c:choose>

                                                        </td>
                                                        <td>${orderDetail.description}</td>
                                                        <td>
                                                            <c:if test="${orderDetail.authStatus=='SUCCESS'}">
                                                                <button onclick="closeOrderDetail('${orderDetail.id}')" class="layui-btn layui-btn-small" type="button">关闭</button>
                                                            </c:if>
                                                            <c:if test="${orderDetail.authStatus=='SUCCESS'}">
                                                                <button onclick="delayCourse(this)" class="layui-btn layui-btn-small" type="button">延期</button>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${trxDetailList.size()==0||trxDetailList==null}">
                                                <tr>
                                                    <td align="center" colspan="16">
                                                        <div class="tips">
                                                            <span>还没有订单信息！</span>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:if>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                    </div>
                </div>
                <!-- /tab4 end -->
            </div>
        </fieldset>
    </div>
</form>
</body>
</html>
