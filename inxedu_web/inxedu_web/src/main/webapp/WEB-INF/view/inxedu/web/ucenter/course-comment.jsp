<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
<title>课程评论</title>
<script>
	otherId = '${otherId}';
	var type = 2;
	$(function(){
		//左侧订单中心选中
		$("a[href$='/uc/order/myOrderList/ALL']").parent().addClass("current");
	});
	// 添加评论
	function addComment() {
		// 点击时清空错误提示
		$(".commentContentmeg").val("").hide();

		// 评论必须先登陆
		if (!isLogin()) {
			lrFun();
			return;
		}
		// 评论
		var content = $("#commentContent").val().trim();
		// 评论内容不能为空
		if (content == "") {
			$(".commentContentmeg").html("内容不能为空！").show();
			return;
		}
		$.ajax({
			url : baselocation + '/web/comment/ajax/addcomment',
			type : 'post',
			data : {
				"pCommentId" : "0",
				"content" : content,
				"type" : type,
				"otherId" : otherId
			},
			async : true,
			dataType : 'text',
			success : function(result) {
				dialog("提示信息", "发布成功,点击确定返回，取消继续发表！", 2, baselocation+"/uc/order/myOrderList/ALL");
			}
		});
	}
</script>
</head>
<body>
	<article class="col-80 fl">
		<div class="u-r-cont">
			<section>
				<div class="u-r-all-box">
					<section class="u-r-tit-all">
						<h2 class="clearfix">
							<span class="fsize16 c-333 f-fM unFw name">课程评价</span>
						</h2>
					</section>
					<section>
						<div class="clearfix cou-r-pl-all">
							<article class="col-3 fl">
								<div class="cc-l-wrap mr20 ml20">
									<section class="course-img">
										<img class="img-responsive" xsrc="${ctx}${course.logo}" src="${ctx}${course.logo}" alt="初中教师资格证—教学知识与能力">
										<div class="cc-mask">
											<a class="comm-btn c-btn-1" href="${ctx}/front/couinfo/${course.courseId}" title="开始学习">开始学习</a>
										</div>
									</section>
									<div class="bg-f8 coure-bg-g">
										<h3 class="hLh30 txtOf">
											<a class="course-title fsize16 c-333" href="${ctx}/front/couinfo/${course.courseId}" title="${course.courseName}">${course.courseName}</a>
										</h3>
										<section class="mt10 hLh20 of">
											<span class="fl">
											<tt class="c-master fsize14 f-fM">￥${course.currentPrice}</tt>
											</span>
										</section>
									</div>
								</div>
							</article>
							<article class="col-7 fl">

								<div class="cou-r-pl-r-box">
									<p class="hLh30 fsize16 c-666 f-fM">学员感受：</p>
									<section class="u-r-t-big">
										<textarea class="u-r-textarea" id="commentContent" maxlength="500" placeholder="请输入您对本课程的意见，以便于我们更好的为您提供服务！"></textarea>
									</section>
									<span class="fl "><tt class="c-red commentContentmeg" style="display: none;"></tt></span>

									<section class="tac mt20 u-o-right">
										<a class="cou-kscp gmkc" href="javascript:addComment();">发表评论</a>
									</section>
								</div>
							</article>
						</div>
					</section>
				</div>
			</section>
		</div>
	</article>
</body>
</html>