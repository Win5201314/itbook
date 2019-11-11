<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>资讯列表</title>
    <script type="text/javascript" src="${ctx}/static/admin/article/article.js"></script>
    <script>
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });
        });
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>资讯列表</span>
    </legend>
    <div class="layui-field-box">
        <div class="numb-box-t">
            <div class="layui-field-box">
                <form action="${ctx}/admin/article/showlist" class="layui-form" method="post" id="searchForm">
                    <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1"/>
                    <input type="hidden" placeholder="ID" type="text" name="queryArticle.articleId" value="${queryArticle.articleId}"/>

                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">标题</label>
                            <div class="layui-input-inline">
                                <input placeholder="标题" class="layui-input" type="text" name="queryArticle.queryKey" value="${queryArticle.queryKey}"/>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">创建时间</label>
                            <div class="layui-input-inline" style="width: 100px;">
                                <input type="text" name="queryArticle.beginCreateTime" placeholder="开始时间" value="<fmt:formatDate value="${queryArticle.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" id="beginCreateTime" class="layui-input" onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})">
                            </div>
                            <div class="layui-form-mid" style="display: none">-</div>
                            <div class="layui-input-inline">
                                <input type="text" name="queryArticle.endCreateTime" placeholder="结束时间" value="<fmt:formatDate value="${queryArticle.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" id="endCreateTime" class="layui-input" onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <a title="查找用户" onclick="$('#searchForm').submit();" class="layui-btn layui-btn-small layui-btn-danger" href="javascript:void(0)">查找资讯</a>
                            <a title="新增资讯" class="layui-btn layui-btn-small layui-btn-danger" href="${ctx}/admin/article/initcreate">新增资讯</a>
                            <%--<a style="display: none" title="清空" onclick="$('#searchForm input:text').val('');$('#searchForm select').val(0);" class="layui-btn layui-btn-small" href="javascript:void(0)">清空
                            </a>--%>
                            <a style="display: none" title="批量删除" onclick="deleteArticle()" class="layui-btn layui-btn-small" href="javascript:void(0)">批量删除</a>
                        </div>
                    </div>


                </form>
                <form action="${ctx}/admin/article/delete" id="deleteForm" method="post">
                    <table class="layui-table">
                        <thead>
                        <tr>
                            <td align="center" style="display: none">
                                <input name="allck" type="checkbox" onclick="selectAll(this)"/>
                            </td>
                            <td align="center">标题</td>

                            <td align="center">创建时间</td>
                            <%--<td align="center">发布时间</td>--%>
                            <td align="center">点击量</td>
                            <td align="center" width="400">操作</td>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${articleList}" var="article" varStatus="index">
                            <tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
                                <td align="center" style="display: none">
                                    <input type="checkbox" name="articelId" value="${article.articleId}"/>
                                </td>
                                <td align="center">${article.title}</td>
                                <td align="center">
                                    <fmt:formatDate value="${article.createTime}" pattern="yyyy/MM/dd HH:mm"/>
                                </td>
                                    <%-- <td align="center">
                                         <fmt:formatDate value="${article.publishTime}" pattern="yyyy/MM/dd HH:mm"/>
                                     </td>--%>
                                <td align="center">${article.clickNum}</td>
                                <td align="center">
                                    <button onclick="window.location.href='${ctx}/admin/comment/query/${article.articleId}/1'"
                                            class="layui-btn layui-btn-small" type="button">查看评论
                                    </button>

                                    <button onclick="window.location.href='${ctx}/admin/article/initupdate/${article.articleId}'"
                                            class="layui-btn layui-btn-small" type="button">修改
                                    </button>
                                    <button onclick="thisDelete(this)" class="layui-btn layui-btn-small" type="button">删除</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>
                <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
            </div>
        </div>
    </div>
</fieldset>
</body>
</html>