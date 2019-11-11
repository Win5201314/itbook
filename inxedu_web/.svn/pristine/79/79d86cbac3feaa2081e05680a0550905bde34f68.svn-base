$(function(){
	$("input[name='articelId']:checkbox").click(function(){
		var selectlen = $("input[name='articelId']:checked").length;
		var alllen = $("input[name='articelId']").length;
		if(alllen>0 && alllen==selectlen){
			$('input[name="allck"]:checkbox').attr('checked',true);
		}else{
			$('input[name="allck"]:checkbox').attr('checked',false);
		}
	});
	
});

/**
 * 单条记录的删除
 */
function thisDelete(em){
	$("input:checkbox").attr('checked',false);
	var checkbox= $($(em).parent("td").parent('tr').children('td')[0]).children('input:checkbox');
	$(checkbox).attr('checked',true);
	deleteArticle();
}

/**
 * 删除
 */
function deleteArticle(){
	var arr = $("input[name='articelId']:checked");
	if(arr==null || arr.length==0){
        layer.msg("请选择要删除文章！", {icon: 5, shift: 6});
		return false;
	}
	if( !confirm('确认要删除选择文章？')){
		return false;
	}
	$("#deleteForm").submit();
}
/**
 * 全选或反选 
 */
function selectAll(em){
	$("input[name='articelId']").prop('checked',$(em).prop('checked'));
}

Date.prototype.Format = function (fmt) { //author: meizz
	var o = {
		"M+": this.getMonth() + 1, //月份
		"d+": this.getDate(), //日
		"h+": this.getHours(), //小时
		"m+": this.getMinutes(), //分
		"s+": this.getSeconds(), //秒
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度
		"S": this.getMilliseconds() //毫秒
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};
/**
 * 保存文章
 */
function saveArticle(){
	var time = new Date().Format("yyyy-MM-dd hh:mm:ss");
	$("#publishTime").val(time);
	$("#articleForm").submit();
}

/**
 * 图片上传回调函数
 * @param imgUrl 上传成功的图片路径
 */
function callback(imgUrl){
	$("input[name='article.imageUrl']").val(imgUrl);
	$("#showImage").attr("src",staticServer+imgUrl)
}