//注册文本框焦点
$(document).ready(function() {
	if ($("span.error").is(":visible")) {
		$("span.error").siblings("span.txt_prompt").hide();
	} else {
		$("span.error").siblings("span.txt_prompt").show();
	}
	$(".txt_input").bind("focus",function(){
		$(this).addClass("txt_active");
		$(this).siblings("span.txt_prompt").addClass("txt_prompt_fouce").siblings("span.error").hide();
		if ($(this).siblings("span.error").is(":visible")) {
			$(this).siblings("span.txt_prompt").hide();
		} else {
			$(this).siblings("span.txt_prompt").show();
		}
	});
	$(".txt_input").bind("blur",function(){
		$(this).removeClass("txt_active");
		$(this).siblings("span.txt_prompt").removeClass("txt_prompt_fouce").siblings("span.error").show();
		if ($(this).siblings("span.error").is(":visible")) {
			$(this).siblings("span.txt_prompt").hide();
		} else {
			$(this).siblings("span.txt_prompt").show();
		}
	});
	$(".txt_input").live("keydown",function(){
		if ($(this).siblings("span.error").is(":visible")) {
			$(this).siblings("span.txt_prompt").hide();
		} else {
			$(this).siblings("span.txt_prompt").show();
		}
	});
	$(".txt_input").live("keyup",function(){
		if ($(this).siblings("span.error").is(":visible")) {
			$(this).siblings("span.txt_prompt").hide();
		} else {
			$(this).siblings("span.txt_prompt").show();
		}
	});
});