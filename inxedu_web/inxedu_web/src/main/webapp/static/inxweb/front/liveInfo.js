/**
 * 收藏课程
 * @param courseId 课程ID
 */
function favorites(courseId,obj){
	if(isLogin()){
		$.ajax({
			url:baselocation+'/front/createfavorites/'+courseId,
			type:'post',
			dataType:'json',
			success:function(result){
				if(result.success==false){
					msgshow(result.message,"false","3000");
				}else{
					$(obj).attr("onclick","deleteFaveorite("+courseId+",this)").attr("title","取消收藏").addClass("sc-end").find("tt").html("取消收藏");
					msgshow(result.message,"success","3000");
				}
			}
		});
	}else{
		lrFun();
	}
}

/**
 * 取消收藏
 * @param favoriteId
 */
function deleteFaveorite(courseId,obj) {
	$.ajax({
		url:'/webapp/deleteFaveorite',
		type:'post',
		data:{"courseId":courseId},
		dataType: 'json',
		success: function (result) {
			if(result.success==false){
				msgshow(result.message,"false","3000");
			}else{
				$(obj).attr("onclick","favorites("+courseId+",this)").attr("title","收藏").removeClass("sc-end").find("tt").html("收藏");
				msgshow(result.message,"success","3000");
			}
		}
	})
}
/**
 * 立即购买
 * @param courseId 课程ID
 */
function buyNow(courseId){
	var loseTime = new Date(loseTimeTime.replace(/\-/g, "\/"));
	//到期类型
	if(loseType=='0'){
		var nowDAte = new Date();
		if(loseTime>nowDAte){
			//添加到购物车 并跳转
			window.location.href="/shopcart?goodsid="+courseId+"&type=1&command=addShopitem";
		}else{
			dialog('提示',"对不起，直播已过期!",1);
		}
	}else{

		//添加到购物车 并跳转
		window.location.href="/shopcart?goodsid="+courseId+"&type=1&command=addShopitem";
	}
}

/**
 * 加入购物车
 * @param courseId
 */
function addShoppingCart(courseId){
	var loseTime = new Date(loseTimeTime.replace(/\-/g, "\/"));
	//到期类型
	if(loseType=='0'){
		var nowDAte = new Date();
		if(loseTime>nowDAte){
			toShoppingCart(courseId);
		}else{
			dialog('提示',"对不起，课程已过期!",1);
		}
	}else{
		toShoppingCart(courseId);
	}
}

/*
 * 到加入购物车
 */
function toShoppingCart(courseId){

	//添加到购物车
	$.ajax({//验证课程金额
		url:baselocation+"/course/check/"+courseId,
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.message!='true'){
				dialog('提示信息',result.message,1);
			}else{
				//添加到购物车
				$.ajax({//验证课程金额
					url:baselocation+"/shopcart/ajax/add",
					data:{
						"goodsid":courseId,
						"type":"1"
					},
					type:"post",
					dataType:"json",
					success:function(result){
						if(result.success!=true){
							dialog('提示信息',result.message,1);
						}else{
							cShopcar();//购物车飞入效果
							//window.location.href="/shopcart?goodsid="+courseId+"&type=1&command=addShopitem";
						}
					}
				})
			}
		}
	})

}
//购物车飞入效果
var cShopcar=function(){
	// 元素以及其他一些变量
	var eleFlyElement = document.querySelector("#flyItem"), eleShopCart = document.querySelector("#shopCart");

	// 抛物线运动
	var myParabola = funParabola(eleFlyElement, eleShopCart, {
		speed: 400, //抛物线速度
		curvature: 0.0008, //控制抛物线弧度
		complete: function() {
			eleFlyElement.style.visibility = "hidden";
			eleShopCart.querySelector("tt").innerHTML = ++numberItem;
		}
	});
	// 绑定点击事件
	if (eleFlyElement && eleShopCart) {

		[].slice.call(document.getElementsByClassName("btnCart")).forEach(function(event) {
			//button.addEventListener("click", function(event) {
			//button.click(function(event) {
			//var src = $(this).parent().parent().parent().parent().siblings(".c-v-pic-wrap").find('.p-h-video-box').find("img").attr("src");
			var src = $("#aCoursesList").find("article.l-info-pic-wrap").find("img").attr("src");
			$("#flyItem").find("img").attr("src", src);
			// 滚动大小
			var scrollLeft = document.documentElement.scrollLeft || document.body.scrollLeft || 0,
				scrollTop = document.documentElement.scrollTop || document.body.scrollTop || 0;
			eleFlyElement.style.left = event.clientX + scrollLeft + "px";
			eleFlyElement.style.top = event.clientY + scrollTop + "px";
			eleFlyElement.style.visibility = "visible";

			// 需要重定位
			myParabola.position().move();
			//});
		});
	}
};
/*课程简介、列表、评论点击切换显示对应内容*/
function selectCourseInfo(infoId) {
	$("#liveContext,#liveList,#liveComment").hide();
	$("#"+infoId).show();
	if (checkIsMobile()==false){
		var height = $(".cou-in-boc").height();
		/*如果小于要切换出的模块高度 将他的高度设置为auto */
		if (height<$("#"+infoId).height()){
			$(".cou-in-boc").height("auto");
			/*如果不小于要切换出的模块高度 则将高度设置为原高度 */
		}else if ($("#"+infoId).height()<height){
			$(".cou-in-boc").height(height)
		}
	}
}
$(function () {
	treeMenu();
	countHeight();
});
function countHeight() {
	if (checkIsMobile()==false){
		/*计算课程详情部分高度 赋值给简介部分高度*/
		var height = $(".liveInfoRight").height()-20 ;
		if ($(".cou-in-boc").height()<height){
			$(".cou-in-boc").height(height);
		}
	}
}