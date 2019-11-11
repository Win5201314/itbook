$(function() {
	cssNavigation();//设置导航选中样式
	showUserInfo();//头部显示用户信息
	iSortFun(".n-item-menu",".n-i-m-bottom .i-j-item",".n-item-right-op","curr");
	headHight();//获取左侧菜单栏高度与图片相等
	mobileheaderlogo();//移动端底顶部logo切换

});
//如果是首页移动端左上角则显示logo
function mobileheaderlogo(){
	if(checkIsMobile()){
		var url = window.document.location.pathname;
		if (url!="/"&&url!="/index2"&&url!="/index3") {
			$(".headerfanhui").show();
			$(".headerlogo").hide();
		}
	}
}
//获取左侧菜单栏高度与图片相等
function headHight(){
	$(".n-i-m-bottom").css("height",$(".imgload").eq(0).height());
	$(".n-item-right").css("height",$(".imgload").eq(0).height()-2);
}
/**
 * 头部显示用户信息
 */
function showUserInfo() {
	var user = getLoginUser();
	if(user!=null && user.userId>0){
		queryUnReadNum();// 查询未读消息
		var showName = user.displayName;
		if (showName == null || $.trim(showName) == '') {
			showName = user.email;
		}
		// 头像
		var useImg = user.picImg;
		if (useImg == null || $.trim(useImg) == '') {
			useImg = baselocation + '/static/inxweb/img/avatar-boy.gif';
		} else {
			useImg = staticServer + useImg;
		}
		$("#userName").text(showName);
		$(".userImgPhoto").attr("src", useImg);
		$(".userImgPhoto").attr("alt", showName);
		$(".userNameClass").html(showName);
		$("#showName").text(showName);
		$("#showName").attr('title', showName);
		userBannerImage = user.bannerUrl;
		// 头部显示
		$("#userName").text(showName);
		$("#userName").attr('title', showName);
		$("#is-login-one,#is-login-two,#mobileExitDiv,.loginedLiShow").show();
		$(".picImg").attr("src", useImg);
		/*vip开通情况显示*/
		var vip = getVipInfo();
		if ($(vip).size()>0){
			$(".u-tit-vip").show();
		}else {
			$(".u-tit-vip-gq").show();
		}

	} else {
		$("#no-login").show();
		$(".no-login").show();
	}
}
/**
 * 设置导航选中样式
 */
function cssNavigation() {
	var url = window.document.location.pathname;
	$("a[href$='" + url + "']").parent().addClass("current");

	//根据请求地址 ，设置导航头部 搜索 交地址
	if (url.indexOf("/teacherlist") > 0) {
		searchType('TEACHER');
	} else if (url.indexOf("/liveIndex")>0||url.indexOf("/showLivelist")>0){
		searchType('LIVES')
	} else if (url.indexOf("/articlelist") > 0) {
		searchType('ARTICLE');
	} else if (url=="/questions/list") {
		searchType('QUESTIONS');

	} else {
		searchType('COURSE');
	}

	//顶部搜索下拉类型选择
	ssFun();
}

/**
 * 初始化选择搜索类型
 * @param type
 */
function searchType(type) {
	if (type == "TEACHER") {
		$("#headerformSearch").attr("action", "/front/teacherlist");
		$("#headerSearchName").attr("name", "queryTeacher.name");
		$("#headerSearchTitle").html("名&nbsp;师");

		$("#headerH5SearchPage").attr("href","/h5/ajax/h5Search?searchType=/front/teacherlist");
	} else if (type == "ARTICLE") {
		$("#headerformSearch").attr("action", "/front/articlelist");
		$("#headerSearchName").attr("name", "queryArticle.queryKey");
		$("#headerSearchTitle").html("资&nbsp;讯");

		$("#headerH5SearchPage").attr("href","/h5/ajax/h5Search?searchType=/front/articlelist");
	}else if (type=="LIVES"){
		$("#headerformSearch").attr("action","/front/showLivelist");
		$("#headerSearchName").attr("name","queryCourse.courseName");
		$("#headerSearchTitle").html("直&nbsp;播");

		$("#headerH5SearchPage").attr("href","/h5/ajax/h5Search?searchType=/front/showLivelist");
	}else if (type == "QUESTIONS") {
		$("#headerformSearch").attr("action", "/questions/list");
		$("#headerSearchName").attr("name", "questions.title");
		$("#headerSearchTitle").html("问&nbsp;答");

		$("#headerH5SearchPage").attr("href","/h5/ajax/h5Search?searchType=/questions/list");
	} else {
		$("#headerformSearch").attr("action", "/front/showcoulist");
		$("#headerSearchName").attr("name", "queryCourse.courseName");
		$("#headerSearchTitle").html("课&nbsp;程");

		$("#headerH5SearchPage").attr("href","/h5/ajax/h5Search?searchType=/front/showcoulist");
	}
}

	//顶部消息下拉
function ssFun() {
	var _sBox = $(".tit-new-list"),
		/*_sTxt = $(".tit-new-list .news-icon"),*/
		_sOl = _sBox.children(".tit-n-warp");
		/*_sLi = $(".tit-n-warp>li");*/
	_sBox.hover(function () {
		_sOl.stop(true,true).slideDown(50);
	}, function () {
		_sOl.stop(true,true).slideUp(50);
	});
}
//课程分类展开
function iSortFun(obj,iTem,iCont,curr) {
	var _timer = null;
	$(obj).on({
		mouseover: function(e) {
			var _this = $(this),
				_index = _this.index();
			if($(iCont).children().eq(_index).length>0) {
				e.stopPropagation();
				_this.addClass(curr).siblings().removeClass(curr);
				_timer = setTimeout(function() {
					$(iCont).show().children().eq(_index).show().siblings().hide();
				}, 100);
				$(iCont).unbind().bind('mouseover', function(e) {
					e.stopPropagation();
					clearTimeout(_timer);
				});
				$(document).unbind().bind('mouseover', function(e) {
					e.stopPropagation();
					clearTimeout(_timer);
					_this.removeClass(curr);
					$(iCont).hide().children().eq(_index).hide();
				})
			}
		}
	}, iTem);
}
