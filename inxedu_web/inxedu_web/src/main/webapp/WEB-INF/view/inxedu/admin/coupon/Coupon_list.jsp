<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>优惠券列表</title>
    <script type="text/javascript">
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });
            form.on('radio(selectradio)', function(data){
                if(data.value==1){
                    $('#singleTr').show().next().hide();
                }else if(data.value==2){
                    $('#singleTr').hide().next().show();
                }
            });
        });
        $(function () {
            $('#startCreateTime').click(function () {
                layui.laydate({elem: this, festival: true})
            });
            $('#endCreateTime').click(function () {
                layui.laydate({elem: this, festival: true})
            });
        });

        function cancel() {
            $("#type").val(-1);
            $("#keyWord").val("");
            $("#keyWordType").val(-1);
            $("#useType").val(-1);
            $("#startCreateTime").val("");
            $("#endCreateTime").val("");
            $("#isCouponCode").val(-1);
            $("#giveType").val(0);
        }

        function delCoupon(id, notUserNum) {
            var notUserNumInfo = "";
            if (notUserNum > 0) {
                notUserNumInfo = "还有部分优惠券尚未使用,";
            }
            if (confirm(notUserNumInfo + "是否确认删除？")) {
                $.ajax({
                    url: "${cxt}/admin/ajax/deleteCouponById/" + id,
                    type: "post",
                    data: {},
                    dataType: "json",
                    success: function (result) {
                        if (result.success == true) {
                            msgshow("删除成功");
                            window.location.reload();
                        } else {
                            layer.msg(result.message, {icon: 5, shift: 6});
                        }
                    }
                });
            }
        }
        var totalNum = 0;
        var couponId = 0;
        /**
         * 弹出 赠送优惠券
         */
        function giveCoupon(totalNumtemp, couponIdtemp,useType) {
            totalNum = totalNumtemp;
            couponId = couponIdtemp;
            if(useType==1){
                if(!confirm("此优惠券在有效期内可以被任意多人使用，赠送给学员绑定后，就不能无限使用，确定吗？")){
                    return
                }
            }
            //通过这种方式弹出的层，每当它被选择，就会置顶。
            layer.open({
                title:'赠送优惠券',
                type: 1,
                shade: false,
                area: '500px',
                maxmin: false,
                content: $("#giveCouponWin")
            });
        }
        /**
         * 赠送优惠券
         */
        function togiveCoupon() {
            var type = $("input[name='type']:checked").val();
            var username = $("#username").val();
            if (type == 1) {
                if (isEmpty(username)) {
                    layer.msg("请输入账号", {icon: 5, shift: 6});
                    return;
                }
            }
            if (type == 2) {
                if (parseInt("${userCount}") > totalNum) {
                    layer.msg("优惠券数量不足", {icon: 5, shift: 6});
                    return;
                }
            }
            $.ajax({
                url: "/admin/coupon/ajax/giveCouponCode",
                data: {
                    "username": username,
                    "type": type,
                    "couponId": couponId
                },
                type: "post",
                dataType: "json",
                cache: false,
                async: false,
                success: function (result) {
                    if (result.success) {
                        layer.msg(result.message, {icon: 1, shift: 6});
                        window.location.href = "";
                    } else {
                        layer.msg(result.message, {icon: 5, shift: 6});
                    }
                }
            });
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        优惠券列表
    </legend>
    <div class="layui-field-box">
        <form class="layui-form" action="${ctx}/admin/coupon/page" name="searchForm" id="searchForm" method="post">
            <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">名称</label>
                    <div class="layui-input-inline">
                        <input id="keyWord" type="txt" name="queryCoupon.keyWord" class="layui-input" value="${queryCoupon.keyWord}">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">创建时间</label>

                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" readonly="readonly" class="layui-input" name="queryCoupon.startCreateTime" placeholder="开始时间" id="startCreateTime" value="${queryCoupon.startCreateTime}">
                    </div>
                    <div class="layui-form-mid">-</div>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" readonly="readonly" class="layui-input" name="queryCoupon.endCreateTime" placeholder="结束时间" id="endCreateTime" value="${queryCoupon.endCreateTime}">
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="goPage(1)">查询</button>
                    <input class="layui-btn layui-btn-small layui-btn-danger" type="button" onclick="window.location.href='/admin/coupon/doadd'" value="新建优惠券">
                </div>
                <div class="layui-input-inline" style="display: none;">
                    <select name="queryCoupon.keyWordType" id="keyWordType" style="display: none;">
                        <option value="1" <c:if test="${queryCoupon.keyWordType==1 }">selected="selected"</c:if>>优惠券名称</option>
                    </select>
                </div>
            </div>

            <table cellspacing="0" cellpadding="0" border="0" width="100%" class="layui-table">
                <thead>
                <tr>
                    <%--<td align="center" width="2%"><span>ID</span></td>--%>
                    <td align="center" width="5%"><span>优惠券名称</span></td>
                    <td align="center" width="5%"><span>抵扣金额</span></td>
                    <td align="center" width="6%"><span>有效期</span></td>
                    <td align="center" width="4%"><span>数量</span></td>
                    <td align="center" width="4%"><span>已使用数量</span></td>
                    <%--<td><span>生成编码</span></td>--%>
                    <td align="center" width="6%"><span>创建时间</span></td>
                    <td align="center" width="6%"><span>创建人</span></td>
                    <td align="center" width="8%"><span>操作</span></td>
                </tr>
                </thead>
                <tbody id="tabS_02" align="center">
                <c:if test="${couponDTOList.size()>0}">
                    <c:forEach items="${couponDTOList}" var="coupon" varStatus="index">
                        <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                                <%--<td>${coupon.id }</td>--%>
                            <td>${coupon.title }</td>
                            <td>${coupon.amount }</td>
                            <td>
                                <fmt:formatDate value="${coupon.startTime}" type="both" pattern="yyyy-MM-dd"/>~<fmt:formatDate value="${coupon.endTime}" type="both" pattern="yyyy-MM-dd"/>
                            </td>
                            <td>
                                    ${coupon.totalNum}
                            </td>
                            <td>
                                    ${coupon.userNum}
                            </td>
                            <td>
                                <fmt:formatDate value="${coupon.createTime}" type="both" pattern="yyyy-MM-dd"/>
                            </td>
                            <td>
                                    ${coupon.optuserName }
                            </td>
                            <td class="c_666 czBtn" align="center">
                                    <%--<button onclick="window.location.href='${cxt}/admin/coupon/giveCouponBatch/${coupon.id}'" class="layui-btn layui-btn-small" type="button">赠送</button>--%>
                                <button onclick="giveCoupon(${coupon.totalNum-coupon.userNum},${coupon.id},${coupon.useType})" class="layui-btn layui-btn-small" type="button">赠送</button>
                                    <%--<button onclick="window.location.href='${cxt}/admin/coupon/createcode/${coupon.id}'" class="layui-btn layui-btn-small" type="button">生成编码</button>--%>
                                    <%--<button onclick="window.location.href='${cxt}/admin/coupon/detail/${coupon.id}'" class="layui-btn layui-btn-small" type="button">查看</button>--%>
                                <button onclick="window.location.href='${cxt}/admin/couponcode/page?queryCouponCode.couponId=${coupon.id}'" class="layui-btn layui-btn-small" type="button">详情</button>
                                <button onclick="delCoupon(${coupon.id},${coupon.totalNum-coupon.userNum})" class="layui-btn layui-btn-small" type="button">删除</button>

                            </td>

                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${couponDTOList.size()==0||couponDTOList==null}">
                    <tr>
                        <td align="center" colspan="16">
                            <div class="tips">
                                <span>还没有优惠券！</span>
                            </div>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
            <!-- /pageBar begin -->
            <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
            <!-- /pageBar end -->
            <!-- 内容 结束 -->
        </form>

        <!-- 赠送优惠劵窗口 ,开始-->
        <div id="giveCouponWin" style="display: none">
            <div class="numb-box">
                <form id="sysUserForm" class="layui-form" action="">
                    <div class="layui-form-item">
                        <label class="layui-form-label layui-form-label-w">选择用户</label>
                        <div class="layui-input-block">
                            <input type="radio" name="type" value="1" id="single" title="个人用户" lay-filter="selectradio" checked>
                            <input type="radio" value="2" name="type" id="all" lay-filter="selectradio" title="全部用户">
                        </div>
                    </div>
                    <div  id="singleTr" class="layui-form-item">
                        <label class="layui-form-label layui-form-label-w">赠送账户</label>
                        <div class="layui-input-inline">
                            <input class="layui-input" name="" id="username" type="text">
                        </div>
                    </div>
                    <div class="layui-form-item" style="display: none">
                        <label class="layui-form-label layui-form-label-w">赠送人数</label>
                        <div class="layui-input-inline">
                            <input class="layui-input layui-radio-disbaled layui-disabled" name="" type="text" value="${userCount}" >
                        </div>
                    </div>
                </form>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-danger" onclick="togiveCoupon()">确定</button>
                        <button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll();">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</fieldset>
</body>
</html>
