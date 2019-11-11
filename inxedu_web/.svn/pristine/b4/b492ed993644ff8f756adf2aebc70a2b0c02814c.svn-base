var subjectList;
var setting = {
	view : {
		showLine : true,
		showIcon : true,
		selectedMulti : false
	},
	data : {
		simpleData : {
			enable : true,
			idKey : 'subjectId',
			pIdKey : 'parentId',
			rootPid : ''
		},
		key : {
			name : 'subjectName',
			title : 'subjectName'
		}
	},
	callback : {
		onClick:seleSubject
	}
};
$(function() {
	initSimpleImageUpload('fileuploadButton', 'teacher', callback,'',true,'288','288');
	subjectList = eval('('+subjectList+')');
	var treeObj = $.fn.zTree.init($("#ztreedemo"), setting, subjectList);
});


function callback(imgUrl) {
	$("#imagesUrl").val(imgUrl);
	$("#subjcetpic").attr("src", staticServer + imgUrl);
}

/**
 * 选择专业
 */
function seleSubject(event, treeId, treeNode){
	$("#subjectId").val(treeNode.subjectName);
	$("input[name='teacher.subjectId']").val(treeNode.subjectId);
	$("#ztreedemo").hide();
}

/**
 * 显示专业组件
 */
function showSubjectList(){
	$("#ztreedemo").show();
	$("body").bind("mousedown",function (event) {
		if (!$(event.target).parents('#ztreedemo').length>0){
			$("#ztreedemo").hide();
		}
	})
}
/**
 * 保存讲师信息
 */
function teacherFormSubmit() {

	var subjectId = $("input[name='teacher.subjectId']").val();
	if(subjectId<=0){
        layer.msg("请选择讲师专业！", {icon: 5, shift: 6});
		return false;
	}
	
	$("#saveTeacherForm").submit();
}