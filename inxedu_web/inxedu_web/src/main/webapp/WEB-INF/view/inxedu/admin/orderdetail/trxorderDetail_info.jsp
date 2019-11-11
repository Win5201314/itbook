<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>订单课程详情</title>
    <script type="text/javascript">
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
                alert("延期时间不能为空！");
                return false;
            }
            var daoqishijian = $(obj).parent().prev().html();
            if (stopTime <= daoqishijian) {
                alert('延期时间必须大于到期时间！');
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
                            alert("已延期");
                            $(obj).parent().prev().show();
                            $(obj).parent().prev().html(stopTime);
                            $(obj).parent().hide();
                        }else{
                            alert(result.message);
                        }
                    },
                    error : function(error) {
                        alert("error");
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
            if(confirm("确定关闭此课程订单吗?")){
                $.ajax({
                    url : baselocation+"/admin/order/closeCourse/"+trxOrderId,
                    data : {
                    },
                    dataType : "json",
                    type : "post",
                    async : false,
                    success : function(result) {
                        if (result.success == true) {
                            alert("关闭订单成功");
                            window.location.reload();
                        }else{
                            alert(result.message);
                        }
                    },
                    error : function(error) {
                        alert("error");
                    }
                });
            }
        }
    </script>

</head>
<body>
<fieldset class="layui-elem-field">
    <legend>订单课程详情</legend>
    <div class="layui-field-box">
        <form action="${ctx }/admin/websiteProfile/update" method="post" id="addprofileForm"  class="layui-form">
            <input type="hidden" name="type" value="${type}" />
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">流水ID</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input layui-input-6" value="${queryTrxorderDetail.id}" disabled="disabled"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">订单编号</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input layui-input-6" value="${queryTrxorderDetail.trxorderId}" disabled="disabled"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">用户昵称</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input layui-input-6" value="${queryTrxorderDetail.userName}" disabled="disabled"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">用户邮箱</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input layui-input-6" value="${queryTrxorderDetail.email}" disabled="disabled"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">用户手机</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input layui-input-6" value="${queryTrxorderDetail.mobile}" disabled="disabled"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">课程名</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input layui-input-6" value="${queryTrxorderDetail.courseName}" disabled="disabled"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">下单时间</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input layui-input-6" value="<fmt:formatDate value="${queryTrxorderDetail.createTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>" disabled="disabled"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">支付时间</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input layui-input-6" value="<fmt:formatDate value="${queryTrxorderDetail.payTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>" disabled="disabled"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">原始价</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input layui-input-6" value="${queryTrxorderDetail.sourcePrice}" disabled="disabled"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">订单状态</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input layui-input-6" value='<c:if test="${queryTrxorderDetail.authStatus=='INIT'}">未支付</c:if>
                    <c:if test="${queryTrxorderDetail.authStatus=='SUCCESS'}">
                       已支付
                    </c:if>
                    <c:if test="${queryTrxorderDetail.authStatus=='REFUND'}">
                        退款
                    </c:if>
                    <c:if test="${queryTrxorderDetail.authStatus=='CANCEL'}">已取消</c:if>
                    <c:if test="${queryTrxorderDetail.authStatus=='CLOSED'}">
                        已关闭
                    </c:if>' disabled="disabled"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">实际支付</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input layui-input-6" value="${queryTrxorderDetail.currentPrice}" disabled="disabled"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">订单备注</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input layui-input-6" value="${queryTrxorderDetail.description}" disabled="disabled"/>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input class="layui-btn" type="button" value="返回" onclick="history.go(-1);"/>
                </div>
            </div>

            <%--<div class="numb-box">  	<fieldset>
                <legend>
                    <span>订单管理</span>
                    &gt;
                    <span>订单详情</span>
                </legend>
                <div class="mt20">
                        <p>
                            <label for="lf"><font color="red">*</font>&nbsp;流水ID</label>
                            ${queryTrxorderDetail.id}
                            <span class="field_desc"></span>
                        </p>
                        <p>
                            <label for="lf"><font color="red">*</font>&nbsp;订单编号</label>
                            ${queryTrxorderDetail.trxorderId}
                            <span class="field_desc"></span>
                        </p>
                        <p>
                            <label for="lf"><font color="red">*</font>&nbsp;用户昵称</label>
                            ${queryTrxorderDetail.userName}
                            <span class="field_desc"></span>
                        </p>
                        <p>
                            <label for="lf"><font color="red">*</font>&nbsp;用户邮箱</label>
                            ${queryTrxorderDetail.email}
                            <span class="field_desc"></span>
                        </p>
                        <p>
                            <label for="lf"><font color="red">*</font>&nbsp;用户手机</label>
                            ${queryTrxorderDetail.mobile}
                            <span class="field_desc"></span>
                        </p>
                    <p>
                        <label for="lf"><font color="red">*</font>&nbsp;课程名</label>
                        <a href="${ctx}/admin/cou/initUpdate/${queryTrxorderDetail.courseId}"> ${queryTrxorderDetail.courseName}</a>
                        <span class="field_desc"></span>
                    </p>
                        <p>
                            <label for="lf"><font color="red">*</font>&nbsp;下单时间</label>
                            <fmt:formatDate value="${queryTrxorderDetail.createTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
                            <span class="field_desc"></span>
                        </p>
                        <p>
                            <label for="lf"><font color="red">*</font>&nbsp;支付时间</label>
                            <fmt:formatDate value="${queryTrxorderDetail.payTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
                            <span class="field_desc"></span>
                        </p>
                        <p>
                            <label for="lf"><font color="red">*</font>&nbsp;原始价</label>
                            ￥${queryTrxorderDetail.sourcePrice}
                            <span class="field_desc"></span>
                        </p>
                        <p>
                            <label for="lf"><font color="red">*</font>&nbsp;订单状态</label>
                            <c:if test="${queryTrxorderDetail.trxStatus=='INIT'}">
                                <font color="#e33b00">未支付</font>
                            </c:if>
                            <c:if test="${queryTrxorderDetail.trxStatus=='SUCCESS'}">
                                <font color="#00e33b">已支付</font>
                            </c:if>
                            <c:if test="${queryTrxorderDetail.trxStatus=='REFUND'}">
                                退款
                            </c:if>
                            <c:if test="${queryTrxorderDetail.trxStatus=='CANCEL'}">已取消</c:if>
                            <c:if test="${queryTrxorderDetail.trxStatus=='CLOSED'}">
                                <font color="orange">已关闭</font>
                            </c:if>
                            <span class="field_desc"></span>
                        </p>
                        <p>
                            <label for="lf"><font color="red">*</font>&nbsp;实际支付</label>
                            ￥ ${queryTrxorderDetail.currentPrice}
                            <span class="field_desc"></span>
                        </p>
                        <p>
                            <label for="lf"><font color="red">*</font>&nbsp;订单备注</label>
                            ${queryTrxorderDetail.description}
                            <span class="field_desc"></span>
                        </p>
                        </tbody>
                </div>
                <input onclick="history.go(-1);" class="button" type="button" value="返回" />
                <!-- /tab4 end -->
            </fieldset></div>--%>
        </form>
    </div>
</fieldset>
</body>
</html>
