<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>服务器列表</title>
    <script type="text/javascript">
        /**
         * 显示添加窗口
         */
        function showWin(){
            //通过这种方式弹出的层，每当它被选择，就会置顶。
            layer.open({
                title:'添加服务器',
                type: 1,
                shade: false,
                area: '500px',
                maxmin: false,
                content: $('#createWin')
            });
        }

        /**
         * 添加服务器
         */
        function createServer(){
            var params = '';
            $("#serverForm input,#serverForm select").each(function(){
                params+=$(this).serialize()+"&";
            });
            $.ajax({
                url:baselocation+'/admin/video/createserver',
                type:'post',
                dataType:'json',
                data:params,
                success:function(result){
                    if(result.success==true){
                        layer.closeAll(); //疯狂模式，关闭所有层
                        layer.msg(result.message);
                        window.location.reload();
                    }else{
                        layer.msg(result.message, {icon: 5, shift: 6});
                    }
                },
                error:function(error){
                    layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
                }
            });
        }

        /**
         * 删除频道
         */
        function deleteserver(serverId) {
            if (!confirm('确实要删除吗?')) {
                return;
            }
            $.ajax({
                url: baselocation + '/admin/video/deleteserver/' + serverId,
                type: 'post',
                dataType: 'json',
                success: function (result) {
                    if (result.success == false) {
                        layer.msg(result.message, {icon: 5, shift: 6});
                    } else {
                        location.reload();
                    }
                },
                error: function (error) {
                    layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
                }
            });
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        服务器列表
    </legend>
    <div class="layui-field-box">
                <div class="layui-inline">
                    <button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="showWin()">添加服务器</button>
                </div>
        <table class="layui-table" lay-even="" lay-skin="row">
            <thead>
            <tr>
                <th align="center" style="display: none">ID</th>
                <th align="center">服务器名称</th>
                <th align="center">ip地址</th>
                <th align="center">端口号</th>
                <th align="center">频道数</th>
                <th align="center">实时带宽</th>
                <th align="center">总带宽</th>
                <th align="center">运营商</th>
                <th align="center">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${serverList}" var="server" varStatus="index">
                <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                    <td align="center" style="display: none">${server.id}</td>
                    <td align="center">${server.name}</td>
                    <td align="center">${server.ip}</td>
                    <td align="center">${server.port}</td>
                    <td align="center">${server.channum}</td>
                    <td align="center">${server.bandwidth}</td>
                    <td align="center">${server.tatalrtmpbandwidth}</td>
                    <td align="center">${server.operator}</td>
                    <td align="center">
                        <button onclick="deleteserver('${server.id}')" class="layui-btn layui-btn-small" type="button">删除</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
    </div>
    <!-- 添加服务器窗口 ,开始-->
    <div id="createWin" style="display: none">
        <div class="numb-box">
            <form id="serverForm" class="layui-form" action="">
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w">服务器名称</label>
                    <div class="layui-input-inline">
                        <input  class="layui-input" name="server.name" id="serverName" type="text">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w">ip地址</label>
                    <div class="layui-input-inline">
                        <input  class="layui-input" name="server.ip" id="serverIp" type="text">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w">端口号</label>
                    <div class="layui-input-inline">
                        <input  class="layui-input" name="server.port" id="serverPort" type="text">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w">频道数</label>
                    <div class="layui-input-inline">
                        <input  class="layui-input" name="server.channum" id="serverChanNum" type="text">
                    </div>
                </div> <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w">实时带宽</label>
                    <div class="layui-input-inline">
                        <input  class="layui-input" name="server.bandwidth" id="serverBandWidth1" type="text">
                    </div>
                </div> <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w">总带宽</label>
                    <div class="layui-input-inline">
                        <input  class="layui-input" name="server.tatalrtmpbandwidth" id="serverBandWidth2" type="text">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-label-w">运营商</label>
                    <div class="layui-input-inline">
                        <select name="server.operator" lay-filter="select">
                            <option value="0">请选择</option>
                            <option value="电信">电信</option>
                            <option value="移动">移动</option>
                            <option value="联通">联通</option>
                        </select>
                    </div>
                </div>

            </form>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-danger" onclick="createServer()">确定</button>
                    <button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll();">取消</button>
                </div>
            </div>
        </div>
        <!-- 添加服务器窗口 ,结束-->
    </div>
</fieldset>
</body>
</html>