<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<div class="mbqh-qt">
	<ul>
		
		<%--<li id="page49" class="li-4">
			<a href="javascript:void (0)"  onclick="setHelloPage(49)" class="f-fM " title="创意秀">时尚感</a>
		</li>
		<li id="page1" class="li-5">
			<a href="javascript:void (0)"  onclick="setHelloPage(1)" class="f-fM " title="科技风">科技风</a>
		</li>
		<li id="page2" class="li-6">
			<a href="javascript:void (0)"   onclick="setHelloPage(2)" class="f-fM " title="时尚感">致青春</a>
		</li>
		<li id="page3" class="li-7">
			<a href="javascript:void (0)" onclick="setHelloPage(3)" class="f-fM " title="致青春">创意秀</a>
		</li>--%>
	</ul>
</div>
<style>
	.mbqh-qt{width:32px;position: fixed;top:70px;left: 0;z-index: 9999999;}
	.mbqh-qt ul li{display: block;width: 32px;height: 80px;position: absolute;}
	.mbqh-qt ul li a {color: #fff;display: block;font-size: 14px;line-height: 18px;margin-top: 12px;padding: 0 0 0 6px;width: 20px;}
	.mbqh-qt ul li.li-1{background: url("/static/inxweb/img/indexchange/li-1.png") no-repeat top left;z-index: 99;top:0px;right: 0;}
	.mbqh-qt ul li.li-2{background: url("/static/inxweb/img/indexchange/li-2.png") no-repeat top left;z-index: 100;top:75px;right: 0;}
	.mbqh-qt ul li.li-3{background: url("/static/inxweb/img/indexchange/li-3.png") no-repeat top left;z-index: 101;top:150px;right: 0;}

	.mbqh-qt ul li.li-1:hover,.mbqh-qt ul li.li-1.current{background: url("/static/inxweb/img/indexchange/li-1-xz.png") no-repeat top left;}
	.mbqh-qt ul li.li-2:hover,.mbqh-qt ul li.li-2.current{background: url("/static/inxweb/img/indexchange/li-2-xz.png") no-repeat top left;}
	.mbqh-qt ul li.li-3:hover,.mbqh-qt ul li.li-3.current{background: url("/static/inxweb/img/indexchange/li-3-xz.png") no-repeat top left;}
	.mbqh-qt ul li:hover a{text-decoration: none;}
</style>