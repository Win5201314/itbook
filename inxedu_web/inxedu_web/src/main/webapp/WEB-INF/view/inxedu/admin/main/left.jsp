<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:forEach items="${userFunctionList}" var="uf" varStatus="index">
		<li class="layui-nav-item layui-nav-itemed lefta">
			<c:choose>
				<c:when test="${uf.functionUrl==null || uf.functionUrl==''}">
					<a class="m-l-f-a" href="javascript:">${uf.functionName}</a>
				</c:when>
				<c:otherwise>
					<a href="<c:if test="${uf.urlType==2}">${groupPath}</c:if>${uf.functionUrl}" data-href="<c:if test="${uf.urlType==2}">${groupPath}</c:if>${uf.functionUrl}"  title="${uf.functionName}" class="tooltip">${uf.functionName}</a>
				</c:otherwise>
			</c:choose>
			<c:if test="${uf.childList!=null && uf.childList.size()>0}">
				<c:forEach items="${uf.childList}" var="cuf">
					<dl class="layui-nav-child">
						<c:choose>
							<c:when test="${cuf.functionUrl==null || cuf.functionUrl==''}">
								<dd>
									<a class="m-l-f-a-er" href="javascript:void(0)" title="${cuf.functionName}">${cuf.functionName}</a>
								</dd>
							</c:when>
							<c:otherwise>
								<dd>
									<a  href="<c:if test="${cuf.urlType==2}">${groupPath}</c:if>${cuf.functionUrl}" title="${cuf.functionName}" data-href="<c:if test="${cuf.urlType==2}">${groupPath}</c:if>${cuf.functionUrl}" title="${cuf.functionName}">${cuf.functionName}</a>
								</dd>
							</c:otherwise>
						</c:choose>
					</dl>
				</c:forEach>
			</c:if>
		</li>
</c:forEach>
<script>
	//左侧导航点击事件
	layui.use('element', function(){
		var element = layui.element();
		element.init();
		//监听导航点击
		$(".lefta a").click(function(){
			var href=$(this).attr("data-href");
			var title=$(this).attr("title");
			//如果没有链接则不能点击
			if(href==undefined){
				return;
			}
			delhavetab(title,element);
			element.tabAdd('admintab', {
				title: ''+title
				,content: '<iframe src="'+href+'" width="100%" height="100%" scrolling="yes" frameborder="no" border="0" onLoad="reinitIframeEND(this);"></iframe>' //支持传入html
			});
			//选中新添加的标签
			element.tabChange('admintab', $(".headertab ul li").length-1);

			//左侧菜单 点击 选中样式
			$(".lefta a").removeClass("current");
			$(this).addClass("current");
			return false;
		});

	});
	//删除已存在标签
	function delhavetab(title,element){
		$(".headertab ul li").each(function(i){
			var html = $(this).html();
			html = html.stripHTML().replace("ဆ","");
			if(title==html){
				element.tabDelete('admintab', i);
			}
		});
	}
	//去除字符串html
	String.prototype.stripHTML = function() {
		var reTag = /<(?:.|\s)*?>/g;
		return this.replace(reTag,"");
	}

</script>