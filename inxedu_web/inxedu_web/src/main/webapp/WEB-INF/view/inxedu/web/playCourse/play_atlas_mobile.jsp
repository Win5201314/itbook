<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> 
	<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no,minimal-ui">
	<meta charset="UTF-8">
	<title>图片集</title>
	<style type="text/css">
		blockquote, body, button, dd, dl, dt, fieldset, form, h1, h2, h3, h4, h5, h6, hr, input, legend, li, ol, p, pre, td, textarea, th, ul,img{
			margin: 0;
			padding: 0;
		}
		a {text-decoration: none;}
		body,.m-ptb54 {background-color: #000;}
		ul,li {list-style:none;}
		img {border: none;}
		ul {overflow: hidden;clear: both;}
		.img-container li {float: left;height: 78px;width: 128px;overflow: hidden;}
		.lagre {background-color: #000;position: fixed;height: 100%;width: 100%;top: 0;left: 0;right: 0;bottom: 0;}
		.fadeIn {-webkit-animation-name: fadeIn;animation-name: fadeIn;}
		.p-h-header{background:#444;width:100%}
		.p-h-head{padding:0 80px;position:relative}
		.p-h-title{font-size:20px;line-height:52px;text-align:center;height:52px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap}
		.p-h-goback{position:absolute;left:15px;top:15px}
		.p-h-goback a:hover {text-decoration: none;}
		.p-h-rSc{position:absolute;left:95px;top:22px}
		.p-h-goback em {background: rgba(0, 0, 0, 0) url("/static/inxweb/img/icon.png") no-repeat scroll -113px -213px;}
		.icon24 { display: inline-block; height: 24px; vertical-align: middle;width: 24px;}
		.c-fff {color: #aaa;}
		.fsize14 {font-size: 14px;}
		@-webkit-keyframes fadeInDown {
			0% {
				opacity: 0;
				-webkit-transform: translateY(-20px);
				transform: translateY(-20px);
			}

			100% {
				opacity: 1;
				-webkit-transform: translateY(0);
				transform: translateY(0);
			}
		}

		@keyframes fadeInDown {
			0% {
				opacity: 0;
				-webkit-transform: translateY(-20px);
				-ms-transform: translateY(-20px);
				transform: translateY(-20px);
			}

			100% {
				opacity: 1;
				-webkit-transform: translateY(0);
				-ms-transform: translateY(0);
				transform: translateY(0);
			}
		}
		.bounceIn {
			-webkit-animation-name: bounceIn;
			animation-name: bounceIn;
		}

		@-webkit-keyframes bounceInDown {
			0% {
				opacity: 0;
				-webkit-transform: translateY(-2000px);
				transform: translateY(-2000px);
			}

			60% {
				opacity: 1;
				-webkit-transform: translateY(30px);
				transform: translateY(30px);
			}

			80% {
				-webkit-transform: translateY(-10px);
				transform: translateY(-10px);
			}

			100% {
				-webkit-transform: translateY(0);
				transform: translateY(0);
			}
		}

		@keyframes bounceInDown {
			0% {
				opacity: 0;
				-webkit-transform: translateY(-2000px);
				-ms-transform: translateY(-2000px);
				transform: translateY(-2000px);
			}

			60% {
				opacity: 1;
				-webkit-transform: translateY(30px);
				-ms-transform: translateY(30px);
				transform: translateY(30px);
			}

			80% {
				-webkit-transform: translateY(-10px);
				-ms-transform: translateY(-10px);
				transform: translateY(-10px);
			}

			100% {
				-webkit-transform: translateY(0);
				-ms-transform: translateY(0);
				transform: translateY(0);
			}
		}
		.bounceInRight {
			-webkit-animation-name: bounceInRight;
			animation-name: bounceInRight;
		}

		@-webkit-keyframes bounceInUp {
			0% {
				opacity: 0;
				-webkit-transform: translateY(2000px);
				transform: translateY(2000px);
			}

			60% {
				opacity: 1;
				-webkit-transform: translateY(-30px);
				transform: translateY(-30px);
			}

			80% {
				-webkit-transform: translateY(10px);
				transform: translateY(10px);
			}

			100% {
				-webkit-transform: translateY(0);
				transform: translateY(0);
			}
		}

		@keyframes bounceInUp {
			0% {
				opacity: 0;
				-webkit-transform: translateY(2000px);
				-ms-transform: translateY(2000px);
				transform: translateY(2000px);
			}

			60% {
				opacity: 1;
				-webkit-transform: translateY(-30px);
				-ms-transform: translateY(-30px);
				transform: translateY(-30px);
			}

			80% {
				-webkit-transform: translateY(10px);
				-ms-transform: translateY(10px);
				transform: translateY(10px);
			}

			100% {
				-webkit-transform: translateY(0);
				-ms-transform: translateY(0);
				transform: translateY(0);
			}
		}
		.bounceInLeft {
			-webkit-animation-name: bounceInLeft;
			animation-name: bounceInLeft;
		}

		@-webkit-keyframes bounceInRight {
			0% {
				opacity: 0;
				-webkit-transform: translateX(2000px);
				transform: translateX(2000px);
			}

			60% {
				opacity: 1;
				-webkit-transform: translateX(-30px);
				transform: translateX(-30px);
			}

			80% {
				-webkit-transform: translateX(10px);
				transform: translateX(10px);
			}

			100% {
				-webkit-transform: translateX(0);
				transform: translateX(0);
			}
		}

		@keyframes bounceInRight {
			0% {
				opacity: 0;
				-webkit-transform: translateX(2000px);
				-ms-transform: translateX(2000px);
				transform: translateX(2000px);
			}

			60% {
				opacity: 1;
				-webkit-transform: translateX(-30px);
				-ms-transform: translateX(-30px);
				transform: translateX(-30px);
			}

			80% {
				-webkit-transform: translateX(10px);
				-ms-transform: translateX(10px);
				transform: translateX(10px);
			}

			100% {
				-webkit-transform: translateX(0);
				-ms-transform: translateX(0);
				transform: translateX(0);
			}
		}
	</style>
</head>
<body>
<div class="m-ptb54">
	<header class="p-h-header">
		<section class="p-h-head">
			<aside class="p-h-goback">
				<a title="" href="javascript:window.location.href='/uc/play/${courseKpoint.courseId}?goBack=true';"><em class="icon24">&nbsp;</em><tt class="c-fff fsize14 f-fM vam">返回</tt></a>
			</aside>
			<h2 class="p-h-title"><span id="contentTitle" class="c-fff">${courseKpoint.name}</span></h2>
		</section>
	</header>
	<ul id="container" class="img-container"></ul>
	<div id="lagre_containter" class="lagre animated fadeIn" style="display: none;">
		<img id="lagre_img">
	</div><!-- /大图展示 -->
</div>
<script src="${ctx}/static/inxweb/play/zepto.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx }/static/inxweb/js/common.js"></script>
<script>
	function imgbig(){
		var target = document.getElementById("lagre_img");
		target.style.webkitTransform ="scale(2)";

		touch.on('#lagre_img', 'touchstart', function(ev){
			ev.preventDefault();
		});

		var dx=0, dy= 0,offx= 0,offy=0;

		touch.on('#lagre_img', 'drag', function(ev){
			dx = dx || 0;
			dy = dy || 0;
			offx = dx + ev.x + "px";
			offy = dy + ev.y + "px";

			this.style.webkitTransform = "translate3d(" + offx + "," + offy + ",0)"+' scale(' + currentScale + ')';

		});
		touch.on('#lagre_img', 'dragend', function(ev){
			dx += ev.x;
			dy += ev.y;
		});


		var initialScale = 1;
		var currentScale=2;

		touch.on('#lagre_img', 'pinchend', function(ev){
			currentScale = ev.scale - 1;
			currentScale = initialScale + currentScale;
			currentScale = currentScale > 2 ? 2 : currentScale;
			currentScale = currentScale < 1 ? 1 : currentScale;
			this.style.webkitTransform = "translate3d(" + offx + "," + offy + ",0)"+' scale(' + currentScale + ')';
		});

		touch.on('#lagre_img', 'pinchend', function(ev){
			initialScale = currentScale;
		});

	}
	$(function() {
		picArrFun(17,'${ctx}/static/mobile/img/img-pic','${ctx}/static/mobile/img/img-pic');
	})
	function picArrFun(allPic,urlSmallPic,urlBigPic) {
		var total = 0;//图片总数
		var zWin = $(window);
		//渲染缩略图
		var render = function() {
			var wWidth = zWin.width();
			var padding = 2;
			var picWidth = Math.floor((wWidth-(padding*3))/3);  //一排展示4个图片单张图片的宽度
			if("${message}"!=""){
				dialog('提示','${message}',1);
				return;
			}else{
				var resultList=eval('('+'${kpointAtlasesList}'+')');
				total=resultList.length;//图片总数
				if(total<=0){
					dialog('提示','该章节暂未添加任何图片集',1);
					return;
				}
				var p = padding;
				var tmpl = '';

				for (var i=0;i<resultList.length;i++){
					var imgSrc ='${ctx}'+resultList[i].urlThumbnail;
					var bigImgSrc='${ctx}'+resultList[i].url;
					if ((i+1)%3==1) {   //第一张无padding-left;
					 p = 0;
					 }
					tmpl += '<li atlas-img-url="'+bigImgSrc+'" data-id="'+(i+1)+'" class="animated bounceIn" style=width:'+picWidth+'px;padding-top:'+padding+'px;padding-left:'+p+'px;"><canvas id="cvs_'+(i+1)+'"></canvas></li>';
					var imageObj = new Image();
					imageObj.index = (i+1);
					imageObj.onload = function() {
						var cvs = $("#cvs_"+this.index)[0].getContext("2d");
						cvs.width = this.width;
						cvs.height = this.height;
						cvs.drawImage(this,0,0);
					}
					imageObj.src = imgSrc;
				}
				$("#container").html(tmpl);
			}

			/*var tmpl = '';
			 for (var i = 1;i <= total; i++) {
			 var p = padding;
			 var imgSrc = urlSmallPic+'/'+i+'.jpg';
			 if (i%4==1) {   //第一张无padding-left;
			 p = 0;
			 }
			 tmpl += '<li data-id="'+i+'" class="animated bounceIn" style="width:'+picWidth+'px;height:'+picWidth+'px;padding-top:'+padding+'px;padding-left:'+p+'px;"><canvas id="cvs_'+i+'"></canvas></li>';
			 var imageObj = new Image();
			 imageObj.index = i;
			 imageObj.onload = function() {
			 var cvs = $("#cvs_"+this.index)[0].getContext("2d");
			 cvs.width = this.width;
			 cvs.height = this.height;
			 cvs.drawImage(this,0,0);
			 }
			 imageObj.src = imgSrc;
			 };
			 $("#container").html(tmpl);*/
		}
		render();
		//触摸加载大图
		var bigImg = $("#lagre_img");
		var domeImg = bigImg[0];
		var loadImg = function(id,bigImgUrl,callBack) {
			$("#lagre_containter").css({
				"width":zWin.width(),
				"height":zWin.height(),
				"overflow":"hidden"
			}).show();
			var imgSrc = bigImgUrl;
			var imageObj = new Image();
			imageObj.onload = function() {
				var iW = this.width;
				var iH = this.height;
				var wW = zWin.width();
				var wH = zWin.height();
				var realW = wH*iW/iH;
				var realH = wW*iH/iW;
				var paddingLeft = parseInt((wW-realW)/2);
				var paddingTop = parseInt((wH-realH)/2);
				//初始化大图样式
				bigImg.css({
					"width" : "auto",
					"height" : "auto",
					"padding-top" : "0",
					"padding-left" : "0"
				});
				//判断大图横竖比(当高度大于宽度的20%时为竖图，反之为横图)
				if (iH/iW>1.2) {
					if(realW > wW) {
						bigImg.attr("src" , imgSrc).css({
							"width" : wW + "px",
							"padding-top" : paddingTop
						});
					} else {
						bigImg.attr("src" , imgSrc).css({
							"height" : wH + "px",
							"padding-left" : paddingLeft
						});
					}
				} else {
					bigImg.attr("src" , imgSrc).css({
						"width" : wW + "px",
						"padding-top" : paddingTop
					});
				};
				callBack&&callBack();
			}
			imageObj.src = imgSrc;
		}
		var pId;
		$("#container").delegate("li","tap",function() {
			var _id = pId = $(this).attr("data-id");
			//触摸后加载大图片
			//loadImg(_id);

			var _imgUrl =$(this).attr("atlas-img-url");
			//触摸后加载大图片
			loadImg(_id,_imgUrl);
			$(document).bind("touchmove", function(event) {
				event.stopPropagation();
				event.preventDefault();
			});
			$("html,body").css("overflow","hidden");
			imgbig();
		});
		//触摸左右切换上下张图片且关闭大图预览
		$("#lagre_containter").tap(function() {
			$(this).hide();
			$("html,body").css("overflow","auto");
		})
	}

</script>

</body>

