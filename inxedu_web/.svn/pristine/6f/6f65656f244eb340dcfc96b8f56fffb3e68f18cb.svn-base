//全局变量default布局中重新赋值
var baselocation = "";
var keuploadSimpleUrl="";//kindeditor中使用的路径需要2个参数来区分项目和模块
var uploadSimpleUrl="";//单独的上传按钮使用的路径
var uploadServerUrl="";//上传服务用服务器地址
var staticServer="";//静态服务器地址
var mydomain =$("#mydomain").val();//主站域
/**
 * 验证前台学员是否已经登录 
 * @returns true登录 false未登录
 */
function isLogin(){
	var is = false;
	var user = getLoginUser();
	if(user!=null && user.userId>0){
		is=true;
	}
	return is;
}

/**
 * 获取登录学员
 * @returns User
 */
function getLoginUser(){
	var user = null;
	$.ajax({
		url:baselocation+'/user/ajax/getloginUser',
		type:'post',
		async:false,
		dataType:'json',
		success:function(result){
			user = result.entity;
		}
	});
	return user;
}
/*获取vip开通信息*/
function getVipInfo() {
	var vipInfo = null;
	$.ajax({
		url:baselocation+'/user/ajax/getVipInfo',
		type:'post',
		async:false,
		dataType:'json',
		success:function(result){
			vipInfo = result.entity;
		}
	});
	return vipInfo;
}
/**
 * 学员退出登录
 */
function exit(){
	$.ajax({
		url:baselocation+'/uc/exit',
		type:'post',
		dataType:'json',
		async:true,
		success:function(result){
            reload();
		}
	});
}
function reload(){
    window.location.href=window.location.href+"?solveWx="+10000*Math.random();
}
/**
 * 内容编辑器
 * @param id 文本域ID
 * @param width 编辑器的宽
 * @param height 编辑器的高
 * @param param文件夹名(百度编译器暂不支持,已取消)
 * @param pressText 是否添加水印(暂未配置)
 */
function initKindEditor_addblog(id, width, height,param,pressText) {
	var ue;
	if(baselocation!=uploadServerUrl){
		ue=UE.getEditor(id,{
			initialFrameHeight: height,
			initialFrameWidth:width,
			toolbars: [
				[
					'undo', //撤销
					'redo', //重做
					'bold', //加粗
					'italic', //斜体
					'underline', //下划线
					'strikethrough', //删除线
					'source', //源代码
					'horizontal', //分隔线
					'fontfamily', //字体
					'fontsize', //字号
					'simpleupload', //单图上传
					'insertimage', //多图上传
					'link', //超链接
					'emotion', //表情
					'map', //Baidu地图
					'justifyleft', //居左对齐
					'justifyright', //居右对齐
					'justifycenter', //居中对齐
					'justifyjustify', //两端对齐
					'forecolor', //字体颜色
					'backcolor', //背景色
					'fullscreen', //全屏
					'pagebreak', //分页
					'imagenone', //默认
					'imageleft', //左浮动
					'imageright', //右浮动
					'imagecenter', //居中
					'wordimage', //图片转存
					'lineheight', //行间距
					'edittip ', //编辑提示
					'customstyle', //自定义标题
					'background', //背景
					'template', //模板
					'scrawl', //涂鸦
					'music', //音乐
				]
			]
			,zIndex:200//编辑器在页面上的z-index层级的基数，默认是900
			,autoFloatEnabled: false//是否保持toolbar的位置不动，默认true
			,serverUrl: uploadServerUrl+"/controller.jsp"
		});
	}
	else if(baselocation==uploadServerUrl) {
		ue=UE.getEditor(id,{
			initialFrameHeight: height,
			initialFrameWidth:width,
			toolbars: [
				[
					'undo', //撤销
					'redo', //重做
					'bold', //加粗
					'italic', //斜体
					'underline', //下划线
					'strikethrough', //删除线
					'source', //源代码
					'horizontal', //分隔线
					'fontfamily', //字体
					'fontsize', //字号
					'simpleupload', //单图上传
					'insertimage', //多图上传
					'link', //超链接
					'emotion', //表情
					'map', //Baidu地图
					'justifyleft', //居左对齐
					'justifyright', //居右对齐
					'justifycenter', //居中对齐
					'justifyjustify', //两端对齐
					'forecolor', //字体颜色
					'backcolor', //背景色
					'fullscreen', //全屏
					'pagebreak', //分页
					'imagenone', //默认
					'imageleft', //左浮动
					'imageright', //右浮动
					'imagecenter', //居中
					'wordimage', //图片转存
					'lineheight', //行间距
					'edittip ', //编辑提示
					'customstyle', //自定义标题
					'background', //背景
					'template', //模板
					'scrawl', //涂鸦
					'music', //音乐
				]
			]
			,zIndex:200//编辑器在页面上的z-index层级的基数，默认是900
			,autoFloatEnabled: false//是否保持toolbar的位置不动，默认true
		});
		//复写UEDITOR的getActionUrl 方法,定义自己的Action
		UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
		UE.Editor.prototype.getActionUrl = function (action) {
			if (action == 'uploadimage' || action == 'uploadfile') {
				var id = $('#carInfoId').val();
				return uploadServerUrl + '/image/ueditorupload';
			} else {
				return this._bkGetActionUrl.call(this, action);
			}
		};
	}
	return ue;
}

/**
 * 后台专用图片上传
 * @param btnId 上传组件的ID
 * @param param 图片上传目录名
 * @param callback 上传成功后的回调函数，函数接收一个参数（上传图片的URL）
 * @param pressText 是否上水印 false或空 否 true是
 *@param cutImg 是拉伸图片 false或空 否 true是
 *@param width height 图片拉伸后宽高
 */
function initSimpleImageUpload(btnId,param,callback,pressText,cutImg,width,height){
		KindEditor.create();
		var uploadbutton = KindEditor.uploadbutton({
			button : KindEditor('#'+btnId+'')[0],
			fieldName : "uploadfile",
			url : uploadSimpleUrl+'&param='+param+'&pressText='+pressText+'&cutImg='+cutImg+
			'&width='+width+'&height='+height,
			afterUpload : function(data) {
				if (data.error == 0) {
					var url = KindEditor.formatUrl(data.url, 'absolute');//absolute,domain
					callback(url);
				} else {
					alert(data.message);
				}
			},
			afterError : function(str) {
				alert('自定义错误信息: ' + str);
			}
		});
		uploadbutton.fileBox.change(function(e) {
			uploadbutton.submit();
		});
}
/**
 * 前台专用图片上传
 * @param btnId 上传组件的ID
 * @param param 图片上传目录名
 * @param callback 上传成功后的回调函数，函数接收一个参数（上传图片的URL）
 */
function webImageUpload(btnId,param,callback){
		var uploadbutton = KindEditor.uploadbutton({
			button : KindEditor('#'+btnId+'')[0],
			fieldName : "uploadfile",
			url : uploadSimpleUrl+'&param='+param+'&fileType=jpg,gif,png,jpeg',
			afterUpload : function(data) {
				if (data.error ==0) {
					var url = KindEditor.formatUrl(data.url, 'absolute');//absolute,domain
					callback(url);
				} else {
					alert(data.message);
				}
			},
			afterError : function(str) {
				alert('自定义错误信息: ' + str);
			}
		});
		uploadbutton.fileBox.change(function(e) {
			uploadbutton.submit();
		});
}

/**
 * 删除文件
 * @param filePath
 */
function deleteFile(filePath){
	$.ajax({
		url:baselocation+'/image/deletefile',
		type:'post',
		data:{'filePath':filePath},
		dataType:'json',
		success:function(){}
	});
}

/**
 * 获取Cookie值
 * @param cookieName cookie名
 * @returns 返回Cookie值
 */
function getCookie(cookieName) {
	var cookieString = document.cookie;
	var start = cookieString.indexOf(cookieName+'=');
	if(start!=-1){
		var cookieValue='';
		var cookieArr = cookieString.split(";");
		for(var i=0;i<cookieArr.length;i++){
			var arr = cookieArr[i].split("=");
			if($.trim(cookieName)==$.trim(arr[0])){
				cookieValue=arr[1];
			}
		}
		return cookieValue;
	}
	return null;
}

/**
 * 设置Cookie值 
 * @param name 
 * @param value
 */
function setCookie(name,value){
	var Days = 2;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires="+ exp.toGMTString() + ";path=/";
}

//自定义方法-数组去重复
Array.prototype.unique = function(){
	var newArr = []; //一个新的临时数组
	for(var i = 0; i < this.length; i++){ //遍历当前数组
		if(this[i]==""){
			continue;
		}
		//如果当前数组的第i已经保存进了临时数组，那么跳过，否则把当前项push到临时数组里面
		if (newArr.indexOf(this[i]) == -1){
			newArr.push(this[i]);
		}
	}
	return newArr;

};

function isEmpty(str){
	if(str==null || str=="" || str.trim()==''){
		return true;
	}
	return false;
}

function isNotEmpty(str){
	return !isEmpty(str);
}

function isNotNull(object){
	return !isNull(object);
}

function isNull(object){
	if(typeof(object)=="undefined" || object==null ||  object==''){
		return true;
	}
	return false;
}


/**
 * 删除Cookies
 * @param name
 */
function DeleteCookie(name) {
	DeleteCookieDomain(name,mydomain);
}

/**
 * 删除指定域名下的共享cookie.二级域名可用
 * @param name
 * @param domain
 */
function DeleteCookieDomain(name,domain) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if(isNotEmpty(domain)){
		document.cookie = name + "=" + escape(cval) + ";expires="
			+ exp.toGMTString() + ";path=/"+";domain="+domain;
	}else{
		document.cookie = name + "=" + escape(cval) + ";expires="
			+ exp.toGMTString() + ";path=/";
	}
}
/**
 * 获取URL中的参数
 * @param val
 * @returns
 */
function getParameter(val) {
	var uri = window.location.search;
	var re = new RegExp("" + val + "=([^&?]*)", "ig");
	return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1)) : null);
}

/**
 * 检查是否手机
 */
function checkIsMobile(){
	var sUserAgent = navigator.userAgent.toLowerCase();
	var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
	var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
	var bIsMidp = sUserAgent.match(/midp/i) == "midp";
	var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
	var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
	var bIsAndroid = sUserAgent.match(/android/i) == "android";
	var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
	var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
	if (bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) { // 移动端环境下效果
		return true;
	}
	return false;
}
/**
 * 减法
 */
function subtraction(arg1, arg2) {
	var r1, r2, m, n;
	try {
		r1 = arg1.toString().split(".")[1].length;
	}
	catch (e) {
		r1 = 0;
	}
	try {
		r2 = arg2.toString().split(".")[1].length;
	}
	catch (e) {
		r2 = 0;
	}
	m = Math.pow(10, Math.max(r1, r2));
	//last modify by deeka
	//动态控制精度长度
	n = (r1 >= r2) ? r1 : r2;
	return ((arg1 * m - arg2 * m) / m).toFixed(n);
}

/**
 * 兼容ie7/8 trim方法报错
 */
Function.prototype.method = function(name, func) {
	this.prototype[name] = func;
	return this;
};
String.method('trim', function () {
	return this.replace(/^\s+|\s+$/g, '');
});
String.method('ltrim', function () {
	return this.replace(/^\s+/g, '');
});
String.method('rtrim', function () {
	return this.replace(/\s+$/g, '');
});

function isNumber(str) {//正整数验证
	var number = /^[0-9]+$/;
	if (number.test(str)) {
		return true;
	}
	return false;
}