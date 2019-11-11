<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>会员商品列表</title>
    <link rel="stylesheet" type="text/css"
          href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
    <script type="text/javascript"
            src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
    <script type="text/javascript"
            src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>

    <script type="text/javascript">
        layui.use(['form'], function () {
            var form = layui.form();
            form.on('checkbox(selectAll)', function(data){
                $("input[name='memberSaleid']").attr('checked',data.elem.checked);//是否被选中，true或者false
                form.render('checkbox'); //刷新选择框渲染
            });
        });
        function del(id) {
            if (confirm("确定删除吗？")) {
                $.ajax({
                    url: "${cxt}/admin/membersale/del.json",
                    type: "post",
                    data: {"ids": id},
                    dataType: "json",
                    success: function (result) {
                        if (result.success) {
                            layer.msg(result.message, {icon: 1, shift: 6});
                            window.location.reload();
                        } else {
                            layer.msg(result.message, {icon: 5, shift: 6});
                        }
                    }
                })
            }
        }
        function cancel() {
            $("#type").val(0);
        }
        /**
         * 全选
         * @param cb
         */
        function allselect(cb) {
            $("input[class='checkbox']").prop('checked', cb.checked);
        }
        /**
         * 批量删除
         */
        function deleteBatch() {
            var checked = false;
            var str = "";
            $(".checkbox").each(function () {
                if ($(this).prop("checked")) {
                    str += this.value + ",";
                    checked = true;
                }
            });
            if (!checked) {
                layer.msg("请至少选择一条信息", {icon: 5, shift: 6});
                return;
            }
            str = str.substring(0, str.length - 1);
            if (confirm("确定删除所选信息吗？")) {
                $.ajax({
                    url: "${ctx}/admin/membersale/del.json",
                    data: {"ids": str},
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        if (result.success) {
                            layer.msg(result.message, {icon: 1, shift: 6});
                            window.location.href = "/admin/membersale/list.json";
                        } else {
                            layer.msg(result.message, {icon: 5, shift: 6});
                        }
                    }
                });
            }
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>会员价格管理</span>
        &gt;
        <span>会员商品列表</span>
    </legend>
    <div class="layui-field-box">
        <form class="layui-form" action="${ctx}/admin/membersale/list.json" name="searchForm" id="searchForm" method="post">
            <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="1"/>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">类型</label>
                    <div class="layui-input-inline">
                        <select name="queryMemberSale.type" id="type">
                            <option value="0">--请选择--</option>
                            <c:forEach items="${memberTypes }" var="memberType">
                                <option value="${memberType.id }"
                                        <c:if test="${memberType.id==queryMemberSale.type }">selected="selected"</c:if>>${memberType.title }</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <input type="button" class="layui-btn layui-btn-small layui-btn-danger" value="查询" name=""
                           onclick="goPage(1)"/>
                    <input type="button" class="layui-btn layui-btn-small layui-btn-danger" value="清空" name=""
                           onclick="cancel()"/>
                    <button onclick="window.location.href=${cxt}'/admin/membersale/doadd'"
                            class="layui-btn layui-btn-small layui-btn-danger" type="button">新建会员价格
                    </button>
                    <button onclick="deleteBatch()" class="layui-btn layui-btn-small layui-btn-danger"
                            type="button">批量删除
                    </button>

                </div>
            </div>
            <table class="layui-table">
                <thead>
                <tr>
                    <th><span onclick="allCheck(this)">&nbsp;<input type="checkbox" id="chos" lay-filter="selectAll" title="全选"/><a
                            href="javascript:void(0);"></a></span><span>ID</span></th>
                    <th><span>商品名</span></th>
                    <th><span>类型</span></th>
                    <th><span>价格</span></th>
                    <th><span>时长（月）</span></th>
                    <th><span>排序</span></th>
                    <th><span>操作</span></th>
                </tr>
                </thead>
                <tbody id="tabS_02" align="center">
                <c:if test="${memberSaleList.size()>0}">
                    <c:forEach items="${memberSaleList}" var="memberSale">
                        <tr>
                            <td><input type="checkbox" class="checkbox" name="memberSaleid" value="${memberSale.id}"/>&nbsp;${memberSale.id }
                            </td>
                            <td>${memberSale.name }</td>
                            <td>${memberSale.title }</td>
                            <td>${memberSale.price }</td>
                            <td>${memberSale.days }</td>
                            <td>${memberSale.sort}</td>
                            <td align="center">
                                <button onclick="window.location.href=${cxt}'/admin/membersale/doupdate/${memberSale.id}'"
                                        class="layui-btn layui-btn-small" type="button">修改
                                </button>
                                <button onclick="del(${memberSale.id}+'')" class="layui-btn layui-btn-small" type="button">
                                    删除
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${memberSaleList.size()==0||memberSaleList==null}">
                    <tr>
                        <td align="center" colspan="16">
                            <div class="tips">
                                <span>还没有会员商品！</span>
                            </div>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
            <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
        </form>
    </div>
</fieldset>
</body>
</html>
