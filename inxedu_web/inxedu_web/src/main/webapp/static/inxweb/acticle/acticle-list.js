$(function() {
	articleRecommend();//好文推荐
	scrollLoad(); //响应滚动加载课程图片
	$("#headerSearchName").val($("#articleQueryKey").val());
});

//好文推荐
function articleRecommend() {
	$.ajax({
		url : baselocation + '/front/ajax/recommend',
		type : 'post',
		async : true,
		dataType : 'text',
		success : function(result) {
			$(".articleRecommend").html(result);
		}
	});
}