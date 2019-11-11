<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<div id="adminCommonPage" style="text-align:center"></div>

<script type="text/javascript">	
	var totalPageSize=${page.totalPageSize};
	var currentPage =${page.currentPage-1}<1?1:${page.currentPage};
	var totalPage = ${page.totalPageSize};
	layui.use(['laypage', 'layer'], function() {
		var laypage = layui.laypage
				, layer = layui.layer;
		laypage({
			cont: 'adminCommonPage'
			,pages: totalPage//分页数。一般通过服务端返回得到
			,skip: true
			,groups: 5//连续分页数
			,curr: currentPage//当前页
			,jump: function(obj, first){
				if(first){
					return;
				}
				//得到了当前页，用于向服务端请求对应数据
				var curr = obj.curr;
				$("#pageCurrentPage").val(curr);
				$("#searchForm").submit();
			}
		});

	});

	function goPage(curr){
		$("#pageCurrentPage").val(curr);
		$("#searchForm").submit();
	}
</script>