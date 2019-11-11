<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>系统消息列表</title>

    <script type="text/javascript">
        layui.use(['form', 'laydate'], function(){
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function(data){
            });
            form.on('checkbox(selectAll)', function (data) {
                $("input[class='checkbox']").prop('checked', data.elem.checked);//是否被选中，true或者false
                form.render('checkbox'); //刷新选择框渲染
            });
        });
        $(function () {
            $("#status").val('${msgSystem.status}');
        });
        function submitSearch(){
            $("#searchForm").submit();
        }
        function clean(){
            $("input:text").val('');
            $("select").val('0');
        }
        /**
         * 批量删除
         */
        function deleteBatch(){
            var checked = false;
            var str = "";
            $(".checkbox").each(function(){
                if($(this).prop("checked")){
                    str+=this.value+",";
                    checked=true;
                }
            });
            if (!checked) {
                layer.msg("请至少选择一条信息", {icon: 5, shift: 6});
                return;
            }
            str=str.substring(0,str.length-1);
            if (confirm("确定删除所选消息吗？")) {
                $.ajax({
                    url:"${ctx}/admin/letter/delsystem",
                    data:{"ids":str},
                    type:"post",
                    dataType:"json",
                    success:function(result){
                        if(result.success){
                            layer.msg(result.message, {icon: 1, shift: 6});
                            window.location.href="/admin/letter/systemmsglist";
                        }else{
                            layer.msg(result.message, {icon: 5, shift: 6});
                        }
                    }
                });
            }
        }
        function newMsg() {
            window.location.href = "/admin/user/letter/toSendSystemMessages"
        }
    </script>
</head>
<body>

<fieldset class="layui-elem-field">
    <legend>
        <a href="${ctx}/admin/mobile/sendMsglist"><span>系统管理</span></a>&gt;<span>系统消息列表</span>
    </legend>
    <div class="layui-field-box">
    <form  class="layui-form" action="${ctx}/admin/letter/systemmsglist" name="searchForm" id="searchForm" method="post">

        <input  class="layui-input" id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">内容</label>
                <div class="layui-input-inline" >
                    <input  class="layui-input" type="text" name="msgSystem.content" value="${msgSystem.content}"/>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">状态</label>
                <div class="layui-inline">
                    <select id="status" name="msgSystem.status">
                        <option value="-1">全部</option>
                        <option value="0">正常</option>
                        <option value="1">已删除</option>
                        <option value="2">已过期</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <input type="button" class="layui-btn layui-btn-small layui-btn-danger" value="查询" name="" onclick="submitSearch()"/>
                <input type="button" class="layui-btn layui-btn-small layui-btn-danger" value="新增消息" name="" onclick="newMsg()"/>
                <input type="button" class="layui-btn layui-btn-small layui-btn-danger" onclick="deleteBatch();" value="批量删除">
            </div>
        </div>

        <%--消息添加时间：
        <input id="startDate" type="text" name="msgSystem.addTime" value='<fmt:formatDate value="${msgSystem.addTime}" type="both" pattern="yyyy-MM-dd "/>'/>
        -
        <input id="endDate" type="text" name="msgSystem.endTime" value='<fmt:formatDate value="${msgSystem.endTime}" type="both" pattern="yyyy-MM-dd "/>'/>--%>


        <%--<input type="button" class="layui-btn layui-btn-small " value="清空" name="" onclick="clean()"/>--%>


        <table  class="layui-table" lay-even="" lay-skin="row">
            <thead>
            <tr>
                <td align="center" width="20%"><span><input type="checkbox" title="全选" lay-filter="selectAll"/>&nbsp;id</span></td>
                <td align="center" width="40%"><span>消息内容</span></td>
                <td align="center" width="20%"><span>注册时间</span></td>
                <td align="center" width="20%"><span>状态</span></td>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty nsgSystemList}">
                <c:forEach items="${nsgSystemList}" var="list" varStatus="index">
                    <tr>
                        <td align="center"><input type="checkbox" class="checkbox" value="${list.id}"/>&nbsp;${list.id}
                        </td>
                        <td align="center">
                            <div style="overflow-y: auto;height:auto;">${list.content }</div>
                        </td>
                        <td align="center"><fmt:formatDate value="${list.addTime}" type="both"/></td>
                        <td align="center">
                            <c:choose>
                                <c:when test="${list.status==1}">已删除</c:when>
                                <c:when test="${list.status==2}">已过期</c:when>
                                <c:otherwise>正常</c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty nsgSystemList}">
                <tr>
                    <td align="center" colspan="16">
                        <div class="tips">
                            <span>暂无相关信息！</span>
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
    </div>
</fieldset>
</body>
</html>
