$(function() {
	cSortFun(); //分类更多按钮交互效果
	scrollLoad(); //响应滚动加载课程图片
	$("#headerSearchName").val($("#hiddenCourseName").val());
	/*给导航加选中*/
    $("a[href$='/front/liveIndex']").parent().addClass("current");
});
//sort suMore
var cSortFun = function() {
	$(".c-s-dl>dl .c-s-more").each(function() {
		var _this = $(this),
			_uList = _this.siblings("ul"),
			_uLw = _uList.height();
		if (_uLw <= "50") {
			_this.hide();
		} else {
			_uList.css("height","40px");
			_this.hover(function() {
				_this.children(".icon-gd").addClass("current");
				_this.children(".m-a-box").show();
			},function(){
				_this.children(".icon-gd").removeClass("current");
				_this.children(".m-a-box").hide();
			});
			//把overflow：hidden 的li追加到 .m-a-b-more
			$(_uList).children("li").each(function(index,tag){
				if($(tag).position().top>0){
					var aHtml=$(tag).html();
					$(".m-a-b-more").append(aHtml);    //追加节点
				}
			})
		}
	});
};
/**
 * 不同条件查询直播
 * @param type 1班类型（专业） 2讲师 3排序条件
 * @param keyWord type==1(专业ID) type==2(老师ID) type=3(排序条件)
 */
function submitForm(type,keyWord){
	if(type==1){
		$("input[name='queryCourse.subjectId']").val(keyWord);
	}else if(type==2){
		$("input[name='queryCourse.teacherId']").val(keyWord);
	}else if(type==3){
		$("input[name='queryCourse.order']").val(keyWord);
	}else if(type==4){
		if(keyWord=='DESCENDING'){
			$("input[name='queryCourse.order']").val('ASCENDING');
		}else if(keyWord=='ASCENDING'){
			$("input[name='queryCourse.order']").val('DESCENDING');
		}else{
			$("input[name='queryCourse.order']").val('ASCENDING');
		}
	}
	$("input[name='queryCourse.courseName']").val($("#headerSearchName").val());
	$("#searchForm").submit();
}
/*手机端选专业*/
function mobileSubmitForm(keyWord) {
	$("input[name='queryCourse.subjectId']").val(keyWord);


	/*根据class获取子集*/
	var subjectClass =".subject"+ keyWord;
	/*如果数量大于0 就显示子集*/
	if ($(subjectClass).size()>0){
		$(".childSubject").hide();
		$(subjectClass).show();
		return;
	}
	$("#searchForm").submit();
}
/*复选框搜索条件*/
function selectByCheckbox(obj,typeId) {
	if ($(obj).hasClass("check")){
		$("#"+typeId).val("false");
		$("#searchForm").submit();

	}else {
		$("#"+typeId).val("true");
		$("#searchForm").submit();

	}
}

/**
 * 复选框点击选中
 * @param obj
 * @param typeId 搜索类型
 * @condition 是否移除的搜索条件
 */
function checkboxChecked(obj,typeId,condition){
	if(isNotEmpty(condition)){
		//移除同级的搜索条件
		$("."+condition).removeClass("current");
	}
	if ($(obj).hasClass("current")){
		$("#"+typeId).val("");
		$(obj).removeClass("current");
	}else {
		//conditionVal隐藏的搜索条件值
		$("#"+typeId).val($(obj).attr("conditionVal"));
		$(obj).addClass("current");
	}
}

/**
 * 搜索条件 重置
 */
function searchReset(){
	$(".check.condition3").eq(0).click();
	$(".check.condition2").each(function(index,tag){
		if($(tag).hasClass("current")){
			$(tag).click();
		}
	});
}
/*会员类型搜索*/
function memberType(memberTypeId,obj) {
	if (checkIsMobile()){
		if (!$(obj).hasClass("current")){
			$("input[name='queryCourse.memberTypeId']").val(memberTypeId);
			$(obj).parent().addClass("current").siblings().removeClass("current");
		}
	}else {
		$("input[name='queryCourse.memberTypeId']").val(memberTypeId);
		$("#searchForm").submit();
	}
}