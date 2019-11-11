<%@page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>在线演示 - layui</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="/static/common/layui/layui/css/layui.css"  media="all">
	<link rel="stylesheet" href="/static/common/layui/layui/css/global.css" media="all">
	<script src="/static/common/layui/layui/layui.js" charset="utf-8"></script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
	<div class="layui-header header header-demo">
		<div class="layui-main">
			<a class="logo" href="/">
				<img src="/static/common/layui/img/logo-1.png" alt="layui">
			</a>

			<ul class="layui-nav">
				<li class="layui-nav-item ">
					<a href="/doc/">文档</a>
				</li>
				<li class="layui-nav-item layui-this">
					<a href="/demo/">演示</a>
				</li>
				<li class="layui-nav-item ">
					<a href="/demo/">演示</a>
				</li>
				<li class="layui-nav-item ">
					<a href="/demo/">演示</a>
				</li>

				<li class="layui-nav-item">
					<a href="http://fly.layui.com/" target="_blank">社区</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">

			<ul class="layui-nav layui-nav-tree site-demo-nav">

				<li class="layui-nav-item layui-nav-itemed">
					<a class="javascript:;" href="javascript:;">开发工具</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="/demo/">调试预览</a>
						</dd>
					</dl>
				</li>
			</ul>
		</div>
	</div>
	<div class="layui-body site-demo">

		<div class="layui-tab layui-tab-card" lay-allowClose="true" style="height: 100%;">
			<ul class="layui-tab-title">
				<li class="layui-this">网站设置</li>
				<li>用户管理</li>
				<li>权限分配</li>
				<li>商品管理</li>
				<li>订单管理</li>
			</ul>
			<div class="layui-tab-content" style="height: 100%;">
				<div class="layui-tab-item layui-show">
					<form class="layui-form" action="">
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w">输入框</label>
							<div class="layui-input-block">
								<input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w">密码框</label>
							<div class="layui-input-inline">
								<input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">辅助文字</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w">选择框</label>
							<div class="layui-input-block">
								<select name="city" lay-verify="required">
									<option value=""></option>
									<option value="0">北京</option>
									<option value="1">上海</option>
									<option value="2">广州</option>
									<option value="3">深圳</option>
									<option value="4">杭州</option>
								</select>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w">复选框</label>
							<div class="layui-input-block">
								<input type="checkbox" name="like[write]" title="写作">
								<input type="checkbox" name="like[read]" title="阅读" checked>
								<input type="checkbox" name="like[dai]" title="发呆">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w">开关</label>
							<div class="layui-input-block">
								<input type="checkbox" name="switch" lay-skin="switch">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w">单选框</label>
							<div class="layui-input-block">
								<input type="radio" name="sex" value="男" title="男">
								<input type="radio" name="sex" value="女" title="女" checked>
							</div>
						</div>
						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label layui-form-label-w">文本域</label>
							<div class="layui-input-block">
								<textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
							</div>
						</div>
					</form>

					<script>
						//Demo
						layui.use('form', function(){
							var form = layui.form();

							//监听提交
							form.on('submit(formDemo)', function(data){
								alert(111111111111111111);
								return false;
							});
						});
					</script>
				</div>
				<div class="layui-tab-item">
					<form class="layui-form" action="">
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w">输入框</label>
							<div class="layui-input-block">
								<input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w">密码框</label>
							<div class="layui-input-inline">
								<input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">辅助文字</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w">选择框</label>
							<div class="layui-input-block">
								<select name="city" lay-verify="required">
									<option value=""></option>
									<option value="0">北京</option>
									<option value="1">上海</option>
									<option value="2">广州</option>
									<option value="3">深圳</option>
									<option value="4">杭州</option>
								</select>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w">复选框</label>
							<div class="layui-input-block">
								<input type="checkbox" name="like[write]" title="写作">
								<input type="checkbox" name="like[read]" title="阅读" checked>
								<input type="checkbox" name="like[dai]" title="发呆">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w">开关</label>
							<div class="layui-input-block">
								<input type="checkbox" name="switch" lay-skin="switch">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label layui-form-label-w">单选框</label>
							<div class="layui-input-block">
								<input type="radio" name="sex" value="男" title="男">
								<input type="radio" name="sex" value="女" title="女" checked>
							</div>
						</div>
						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label layui-form-label-w">文本域</label>
							<div class="layui-input-block">
								<textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
							</div>
						</div>
					</form>

					<script>
						//Demo
						layui.use('form', function(){
							var form = layui.form();

							//监听提交
							form.on('submit(formDemo)', function(data){
								alert(2222222222222222222222);
								return false;
							});
						});
					</script>

				</div>
				<div class="layui-tab-item">3</div>
				<div class="layui-tab-item">4</div>
				<div class="layui-tab-item">5</div>
				<div class="layui-tab-item">6</div>
			</div>
		</div>

		<%--<div class="" style="height: 100%;width: 100%">
			<iframe frameborder="0" src="http://www.baidu.com" id="LAY_demo" style="height: 100%;width: 100%"></iframe>
		</div>--%>
	</div>
	<div class="layui-footer footer footer-demo">
		<div class="layui-main">
			<p>
				<a href="http://www.inxedu.com" target="_blank">因酷网校</a>
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
	</script>
</div>
</body>
</html>

