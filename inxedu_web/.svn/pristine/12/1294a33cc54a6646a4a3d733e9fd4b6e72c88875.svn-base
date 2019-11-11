<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>图片列表</title>
    <script type="text/javascript">
        var form;
        layui.use(['form', 'laydate'], function () {
            form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });

            form.on('checkbox(selectAll)', function (data) {
                $("input[name='imageId']").attr('checked', data.elem.checked);//是否被选中，true或者false
                form.render('checkbox'); //刷新选择框渲染
            });
            //各种基于事件的操作，下面会有进一步介绍
        });
        $(function () {
            $("input[name='imageId']").click(function () {
                var allarr = $("input[name='imageId']");
                var selectarr = $("input[name='imageId']:checked");
                if (selectarr != null && allarr != null && allarr.length == selectarr.length) {
                    $("#selectAll").attr('checked', true);
                } else {
                    $("#selectAll").attr('checked', false);
                }
            });
        });

        //批量删除删除图片
        function deleteImage() {
            var selectarr = $("input[name='imageId']:checked");
            if (selectarr == null || selectarr.length == 0) {
                layer.msg("选择要删除的图片", {icon: 5, shift: 6});
                return false;
            }
            if (confirm('确认要删除选择中的图片？')) {
                $("#deleteForm").submit();
            }
        }

        //单条记录删除
        function deleteImgThis(em) {
            $("input[name='imageId']").attr('checked', false);
            var input = $($(em).parent("td").parent("tr").children('td')[0]).children('input')[0];
            $(input).attr('checked', true);
            if (confirm('确认要删除该图片！')) {
                $("#deleteForm").submit();
            }
        }
    </script>
</head>
<body>
	<fieldset class="layui-elem-field">
		<legend>
			广告图
		</legend>
		<div class="layui-field-box">
			<form action="${ctx}/admin/website/imagesPage" method="post" id="searchForm" class="layui-form">
				<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">图片标题</label>
						<div class="layui-input-inline">
							<input class="layui-input" placeholder="图片标题" type="text" name="websiteImages.title" value="${websiteImages.title}" />
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">请选类型</label>
						<div class="layui-input-inline">
							<select name="websiteImages.typeId" lay-filter="select">
								<option value="0">请选类型</option>
								<c:forEach items="${typeList}" var="type">
									<option <c:if test="${websiteImages.typeId==type.typeId}">selected</c:if> value="${type.typeId}">${type.typeName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="layui-inline">
						<button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="$('#searchForm').submit();">查找</button>
						<button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="deleteImage()" type="button">批量删除</button>
						<button class="layui-btn layui-btn-small layui-btn-danger" lay-submit="" onclick="window.location.href='${ctx}/admin/website/doAddImages'" type="button">新建图片</button>
						<a title="图片类型列表" class="layui-btn layui-btn-small" href="${ctx}/admin/imagetype/getlist" style="display: none">
							图片类型列表
						</a>
					</div>
				</div>
			</form>
			<form action="${ctx}/admin/website/delImages" id="deleteForm" method="post" class="layui-form">
				<table class="layui-table" lay-even="" lay-skin="row">
					<thead>
						<tr>
							<td align="center">
								<input name="allck" type="checkbox" id="selectAll" lay-filter="selectAll" title="全选" />
							</td>
							<td align="center">标题</td>
							<td align="center">图片URL</td>
							<td align="center">链接URL</td>
							<td align="center">类型</td>
							<%--<td align="center">序列号</td>--%>
							<td align="center" class="">操作</td>
						</tr>
					</thead>
                <tbody>
                <c:forEach items="${websiteImagesList}" var="image" varStatus="index">
                    <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                        <td align="center">
                            <input type="checkbox" name="imageId" value="${image.imageId}"/>
                        </td>
                        <td align="center">${image.title}</td>
                        <td align="center">${image.imagesUrl}</td>
                        <td align="center">${image.linkAddress}</td>
                        <td align="center">${image.typeName}</td>
                            <%--<td align="center">${image.seriesNumber}</td>--%>
                        <td align="center">
                            <button onclick="window.open('${staticServer}
                                ${image.imagesUrl}')" target="_blank" class="layui-btn layui-btn-small" type="button">预览
                            </button>
                            <button onclick="deleteImgThis(this)" class="layui-btn layui-btn-small" type="button">删除
                            </button>
                            <button onclick="window.location.href='${ctx}/admin/website/doUpdateImages/${image.imageId}'"
                                    class="layui-btn layui-btn-small" type="button">修改
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
        <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
    </div>
</fieldset>
</body>
</html>