/**
 * mainFrame 加载完成后 设置mainFrame 高度
 */
function setIframeHeight(iframe) {
	if (iframe) {
		var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
		if (iframeWin.document.body) {
			var resizeHeight = iframeWin.document.documentElement.scrollHeight+10 || iframeWin.document.body.scrollHeight+10;
			var uiSMenuHeight=$("#ui-sMenu").height();
			if(uiSMenuHeight<resizeHeight){
				iframe.height = resizeHeight;
			}else{
				iframe.height=uiSMenuHeight;
			}
			$("#mainFrame").css("visibility","visible");
		}
	}
}
function slideScroll() {
	var prev = $(".prev"),
		next = $(".next"),
		oUl = $(".navList>ul"),
		w = oUl.find("li").outerWidth(true),
		l = oUl.find("li").length;
	oUl.css("width" , w * l + "px");

	//click left
	prev.click(function() {
		if(!oUl.is(":animated")) {
			oUl.animate({"margin-left" : -w}, function() {
				oUl.find("li").eq(0).appendTo(oUl);
				oUl.css("margin-left" , 0);
			});
		}
	});
	//click right
	next.click(function() {
		if(!oUl.is(":animated")) {
			oUl.find("li:last").prependTo(oUl);
			oUl.css("margin-left" , -w);
			oUl.animate({"margin-left" : 0});
		}
	});
}
function navFun() {
	var winW = parseInt(document.documentElement.clientWidth, 10) + parseInt(document.documentElement.scrollLeft || document.body.scrollLeft, 10),
		nlW = winW - 540,
		ulW = $(".navList>ul").width(),
		oPN = $('<a href="javascript: void(0)" title="左" class="prev">&nbsp;</a><a href="javascript: void(0)" title="右" class="next">&nbsp;</a>');
	$(".navList").css("width" , nlW);
	if (nlW > ulW) {
		$(".navList").css("width" , nlW);
		$(".prev,.next").remove();
	} else {
		$(".navList").css("width" , nlW);
		oPN.appendTo(".nav-wrap");
		slideScroll();
	}
}

//查询头部
function queryheader(){
	$.ajax({
		url:baselocation+'/admin/main/header',
		type:'post',
		async:false,
		dataType:'text',
		success:function(result){
			$(".headerhtml").html(result);
		}
	});
}
//查询头部
function queryleft(parentId){
	$.ajax({
		url:baselocation+'/admin/main/left?parentId='+parentId,
		type:'post',
		async:false,
		dataType:'text',
		success:function(result){
			$("#ui-sMenu").html(result);
		}
	});
}

function addTab($this, refresh){
	//如果是跳转到因酷云则直接弹出窗口不放到ifream中
	var datahref = $($this).attr('data-href');
	if(datahref=='/admin/yun/inxedu?url=/video/upload'||
		datahref=='/admin/yun/inxedu?url=/video/list'){
		window.open(baselocation+datahref);
		return false;
	}
	//二级菜单选中
	$($this).addClass("current").parent().parent().siblings().find("a").removeClass("current");
	$(".jericho_tab").show();
	$("#mainFrame").hide();
	$.fn.jerichoTab.addTab({
		tabFirer: $($this),
		title: $($this).text().trim(),
		closeable: true,
		data: {
			dataType: 'iframe',
			dataLink: $($this).attr('data-href')
		}
	}).loadData(refresh);
	//重新算宽度
	$.fn.jerichoTab.resize();
	return false;
}
function adminChangePwd() {
	$("#mainFrame").attr("src",baselocation+"/admin/sysuser/user/toupdatepwd");
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