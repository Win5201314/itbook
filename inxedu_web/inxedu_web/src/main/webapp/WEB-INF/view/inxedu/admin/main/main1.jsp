<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>网校后台-${websitemap.web.company}-${websitemap.web.title}</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">

	<script>
		layui.use('element', function(){
			var element = layui.element();

		});
		$(function(){
			layui.use('element', function(){
				var element = layui.element();
				element.init();
				var href="/admin/main/index";
				var title="首页";
				element.tabAdd('admintab', {
					title: ''+title
					,content: '<iframe src="'+href+'" width="100%" height="100%" scrolling="yes" frameborder="no" border="0" onLoad="reinitIframeEND(this);"></iframe>' //支持传入html
				});
				//选中新添加的标签
				element.tabChange('admintab', $(".headertab ul li").length-1);

				//头部导航点击事件
				element.on('nav(headernav)', function(elem){
//					console.log(elem); //得到当前点击的DOM对象
					$.get($($(elem).find("a")).attr("data-href"), function(data){
						$("#ui-sMenu").html(data);
					});
				});
				//默认触发第一个头部导航
				$("#menuHeader a")[0].click();
			});
		});

		//首页的定时判断iframe高度结束定时
		function reinitIframeEND(obj){
			var iframe = obj;
			try{
				/*var contentHeight = $(".layui-tab-content").height();
				var height = $(obj).contents().find("body").height();
				if(height<contentHeight){
					height = contentHeight;
				}*/
				//可见区域高度
				var viewHeight=document.documentElement.clientHeight-$("div.layui-header.header.header-demo").height()-$("ul.layui-tab-title").height()-$("div.layui-footer.footer.footer-demo").height();
				//if(height<viewHeight){
					height = viewHeight-68;
				//}
				$(".layui-tab-content").parent().css("min-height",viewHeight);

				$(iframe).css({ height: height+10 });
			}catch (ex){}
		}
		/*快捷操作切换对应的导航栏*/
		function quicklyToPage(tapId,tapUrl) {
			if (tapId!=null){
				$("a[data-id='"+tapId+"']").click();

			}
			var time = setInterval(function () {
				clearTimeout(time);
				$("a[data-href='"+tapUrl+"']").addClass("current");
			},100)
		}
	</script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
	<div class="layui-header header header-demo">
		<div class="layui-main">
			<a class="logo" href="${ctx}">
				<img src="${websitemap.web.logo}" alt="logo">
			</a>
			<div class="layui-larry-menu">
				<ul class="layui-nav" id="menuHeader" lay-filter="headernav" >
					<c:forEach items="${userFunctionList}" var="fun" varStatus="index">
						<c:if test="${fun.parentId==0}">
							<li class="layui-nav-item ${index.count==1?'layui-this':''}" >
								<a class="headermenu" href="javascript:void(0)" data-id="${fun.functionId}" data-href="${ctx}/admin/main/left?parentId=${fun.functionId}">${fun.functionName}</a>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
			<ul class="layui-nav layui-right-header-item larry-header-item" lay-filter="" >
				<li class="layui-nav-item first">
					<a href="javascript:void(0)">
						<i class="iconfont icon-jiaoseguanli2"></i>
						<cite>${sysuser.loginName}</cite>
					</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:void(0)" onclick="updatePwd(${sysuser.userId})">修改密码</a>
						</dd>
					</dl>
				</li>
				<li class="layui-nav-item">
					<a href="${ctx}/admin/outlogin">
						<i class="iconfont icon-exit"></i>
						退出
					</a>
				</li>
			</ul>
		</div>
	</div>
	<!-- 修改密码窗口 ,开始-->
	<div id="updatePwdWin" style="display: none;">
		<div class="numb-box">
			<form class="layui-form" action="">
				<input type="hidden" id="sysUserId" value="0" />
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">密码</label>
					<div class="layui-input-inline">
						<input  class="layui-input" type="password">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">确认密码</label>
					<div class="layui-input-inline">
						<input class="layui-input" type="password">
					</div>
				</div>
			</form>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn layui-btn-danger" onclick="submitUpdatePwd()">确定</button>
					<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll();">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 修改密码窗口 ,结束-->
	<div class="layui-side layui-bg-black" >
		<div class="layui-side-scroll" >
			<ul class="layui-nav layui-nav-tree site-demo-nav" lay-filter="leftnav" id="ui-sMenu">
			</ul>
		</div>
	</div>
	<div class="layui-body site-demo">
		<div class="layui-tab layui-tab-card headertab" lay-allowClose="true" lay-filter="admintab" >
			<ul class="layui-tab-title">

			</ul>
			<div class="layui-tab-content" >
			</div>
		</div>
	</div>
	<div class="layui-footer footer footer-demo">
		<div class="layui-main">
			<p>
				Powered by <a href="${ctx}" target="_blank">${websitemap.web.company}</a>
			</p>
		</div>
	</div>
	<div class="site-tree-mobile layui-hide">
		<i class="layui-icon">&#xe602;</i>
	</div>
	<div class="site-mobile-shade"></div>
	<script>
		layui.config({
			base: '/static/common/layui/modules/layui/'
			,version: '1481073704865'
		}).use('global');

		window.global = {
			preview: function(){
				var preview = document.getElementById('LAY_preview');
				return preview ? preview.innerHTML : '';
			}()
		};
		/**
		 * 显示修改密码窗口
		 */
		function updatePwd(userId){
			//layer.closeAll(); //疯狂模式，关闭所有层
			$("#updatePwdWin #sysUserId").val(userId);
			//通过这种方式弹出的层，每当它被选择，就会置顶。
			layer.open({
				title:'修改密码',
				type: 1,
				shade: false,
				area: '500px',
				maxmin: false,
				content: $('#updatePwdWin')
			});
		}
		/**
		 * 提交修改密码
		 */
		function submitUpdatePwd(){
			var passArr = $("#updatePwdWin input:password");
			if(passArr[0].value==null ||$.trim(passArr[0].value)==''){
                layer.msg("请输入密码！", {icon: 5, shift: 6});
				return false;
			}else if (passArr[1].value==null || $.trim(passArr[1].value)==''){
                layer.msg("请输入确认密码！", {icon: 5, shift: 6});
				return false;
			}
			if(passArr[0].value!=passArr[1].value){
                layer.msg("两次密码不一致！", {icon: 5, shift: 6});
				return false;
			}
			var userId = $("#updatePwdWin #sysUserId").val();
			$.ajax({
				url:baselocation+'/admin/sysuser/updatepwd/'+userId,
				type:'post',
				dataType:'json',
				data:{'newPwd':passArr[0].value},
				success:function(result){
					if(result.success==true){
						layer.closeAll(); //疯狂模式，关闭所有层
					}
					layer.msg(result.message);
				},
				error:function(error){
                    layer.msg("系统繁忙，请稍后再操作！", {icon: 5, shift: 6});
				}
			});
		}
	</script>
</div>
</body>
</html>

