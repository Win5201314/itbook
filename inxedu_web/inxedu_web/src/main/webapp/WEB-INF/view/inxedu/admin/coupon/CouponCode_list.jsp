<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>优惠券编码列表</title>
    <link rel="stylesheet" href="${ctx}/static/common/ztree/css/demo.css?v=${v}" type="text/css"/>
    <script type="text/javascript">
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });
        });
        function cancel() {
            $("#type").val(-1);
            $("#useType").val(-1);
            $("#status").val(-1);
            $("#couponId").val("");
            $("#requestId").val("");
            $("#couponCode").val("");
            $("#title").val("");
        }
        function allCheck(cb) {
            $("input[name=ids]:checkbox").attr('checked', cb.checked);
        }
        function waste(id, status) {
            $.ajax({//   =_=!
                url: "${cxt}/admin/couponcode/waste",
                type: "post",
                data: {"ids": id, "status": status},
                dataType: "json",
                success: function (result) {
                    if (result.message == "true") {
                        if (status == "4") {
                            msgshow("已作废")
                        } else if (status == "1") {
                            msgshow("已恢复")
                        }
                        window.location.reload();
                    }
                }
            });
        }
        function allCheck(cb) {
            $("input[name=ids]").attr('checked', cb.checked);
        }
        function wasteBatch() {
            var codeIds = document.getElementsByName("ids");
            //var num=0;
            var ids = '';
            for (var i = 0; i < codeIds.length; i++) {
                if (codeIds[i].checked == true) {
                    //num++;
                    ids += codeIds[i].value;
                    //if(i!=codeIds.length-1){
                    ids += ",";
                    //}
                }
            }
            ids = ids.substr(0, ids.length - 1);
            if (ids.length == 0) {
                layer.msg("请选择要作废的优惠码", {icon: 5, shift: 6});
                return;
            }
            $.ajax({
                url: "${cxt}/admin/couponcode/waste",
                type: "post",
                data: {"ids": ids},
                dataType: "json",
                success: function (result) {
                    if (result.message == "true") {
                        layer.msg("已作废", {icon: 1, shift: 6});
                        window.location.reload();
                    }
                }
            });
        }
        //导出
        function couponExcel() {
            $("#searchForm").prop("action", "${ctx}/admin/coupon/exportcode");
            $("#searchForm").submit();
            $("#searchForm").prop("action", "${ctx}/admin/couponcode/page");
        }
    </script>
</head>
<body>
<div class="">
    <!-- 内容 开始  -->
    <form class="layui-form" action="${ctx}/admin/couponcode/page" name="searchForm" id="searchForm" method="post">
        <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
        <input type="hidden" name="queryCouponCode.couponId" id="couponId" value="${queryCouponCode.couponId}"/>

        <div class="layui-form-item">
            <label class="layui-form-label">持有人昵称：</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" name="queryCouponCode.userName" id="userName"
                       value="${queryCouponCode.userName}"/>
            </div>
            <label class="layui-form-label">优惠券编码：</label>
            <div class="layui-input-inline">
            <input type="text" class="layui-input" name="queryCouponCode.couponCode" id="couponCode"
                   value="${queryCouponCode.couponCode}"/>
            </div>
            <label class="layui-form-label">状态：</label>
            <div class="layui-input-inline">
                <select name="queryCouponCode.status" id="status">
                    <option value="-1">--请选择--</option>
                    <option value="1" <c:if test="${queryCouponCode.status==1 }">selected="selected"</c:if>>未使用</option>
                    <option value="2" <c:if test="${queryCouponCode.status==2 }">selected="selected"</c:if>>已使用</option>
                    <option value="3" <c:if test="${queryCouponCode.status==3 }">selected="selected"</c:if>>过期</option>
                    <option value="4" <c:if test="${queryCouponCode.status==4 }">selected="selected"</c:if>>作废</option>
                    <option value="6" <c:if test="${queryCouponCode.status==6 }">selected="selected"</c:if>>冻结</option>
                </select>
            </div>
            <div class="layui-input-inline">
                <input class="layui-btn layui-btn-small layui-btn-danger" type="button" onclick="goPage(1)" value="查询">
                <input class="layui-btn layui-btn-small layui-btn-danger" type="button" onclick="couponExcel()" value="导出Excel">
            </div>
        </div>

        <table cellspacing="0" cellpadding="0" border="0" width="100%" class="layui-table">
            <thead>
            <tr>
                <td style="display: none" align="center" width=""><span><input type="checkbox"
                                                                               onclick="allCheck(this)"/>全选</span></td>
                <td align="center"><span>优惠券编码</span></td>
                <%--<td><span>限额</span></td>--%>
                <td align="center"><span>持有人</span></td>
                <td align="center"><span>有效期</span></td>
                <td align="center"><span>状态</span></td>
                <%--<td><span>订单编号</span></td>--%>
                <%--<td><span>创建时间</span></td>--%>
                <td align="center"><span>使用时间</span></td>
                <%--<td><span>创建人</span></td>--%>
                <td align="center"><span>操作</span></td>
            </tr>
            </thead>
            <tbody id="tabS_02" align="center">
            <c:if test="${couponCodeDTOList.size()>0}">
                <c:forEach items="${couponCodeDTOList}" var="couponCodeDTO" varStatus="index">
                    <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                        <td style="display: none"><input type="checkbox" name="ids" value="${couponCodeDTO.id}"/></td>
                        <td>${couponCodeDTO.couponCode}</td>
                            <%--<td>${couponCodeDTO.limitAmount }</td>--%>
                        <td>
                            <c:choose>
                                <c:when test="${couponCodeDTO.userId>0}">
                                    ${couponCodeDTO.userId}-${couponCodeDTO.userName}
                                </c:when>
                                <c:otherwise>--</c:otherwise>
                            </c:choose>
                        </td>
                            <%--<td>
                                <fmt:formatDate value="${couponCodeDTO.startTime}" type="both"  pattern="yyyy-MM-dd"/>~<fmt:formatDate value="${couponCodeDTO.endTime}" type="both"  pattern="yyyy-MM-dd"/>
                            </td>--%>
                        <td>
                            <fmt:formatDate value="${couponCodeDTO.startTime}" type="both"
                                            pattern="yyyy-MM-dd"/>~<fmt:formatDate value="${couponCodeDTO.endTime}"
                                                                                   type="both" pattern="yyyy-MM-dd"/>
                        </td>
                        <td>
                            <c:if test="${couponCodeDTO.status==1 }">未使用</c:if>
                            <c:if test="${couponCodeDTO.status==2 }">已使用</c:if>
                            <c:if test="${couponCodeDTO.status==3 }">过期</c:if>
                            <c:if test="${couponCodeDTO.status==4 }">作废</c:if>
                            <c:if test="${couponCodeDTO.status==6 }">冻结</c:if>
                        </td>
                            <%--<td>${couponCodeDTO.requestId}</td>--%>
                            <%--<td>
                                <fmt:formatDate value="${couponCodeDTO.createTime}" type="both"  pattern="yyyy-MM-dd"/>
                            </td>--%>
                        <td>
                            <fmt:formatDate value="${couponCodeDTO.payTime}" type="both" pattern="yyyy-MM-dd"/>
                        </td>
                            <%--<td>
                                ${couponCodeDTO.optuserName }
                            </td>--%>
                        <td class="c_666 czBtn" align="center">
                                <%--<button onclick="window.location.href='${cxt}/admin/couponcode/detail/${couponCodeDTO.id}'" class="layui-btn layui-btn-small" type="button">查看</button>--%>
                            <c:if test="${couponCodeDTO.status==1 }">
                                <button onclick="waste(${couponCodeDTO.id},'4')" class="layui-btn layui-btn-small" type="button">作废
                                </button>
                            </c:if>
                            <c:if test="${couponCodeDTO.status==4 }">
                                <button onclick="waste(${couponCodeDTO.id},'1')" class="layui-btn layui-btn-small" type="button">恢复
                                </button>
                            </c:if>
                        </td>

                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${couponCodeDTOList.size()==0||couponCodeDTOList==null}">
                <tr>
                    <td align="center" colspan="16">
                        <div class="tips">
                            <span>还没有优惠券编码！</span>
                        </div>
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </form>
    <!-- /pageBar begin -->
    <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
    <!-- /pageBar end -->
    <!-- 内容 结束 -->
</div>
</body>
</html>
