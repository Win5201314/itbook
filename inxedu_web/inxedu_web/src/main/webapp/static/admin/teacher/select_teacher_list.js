$(function(){
	/*$("#selectAll").click(function(){
		$("input[name='teacherId']").each(function(){
			if($(this).attr('checked')=='checked'){
				$(this).attr('checked',false);
			}else{
				$(this).attr('checked',true);
			}
		});
		
	});*/
	$("input[name='teacherId']").click(function(){
		var leng = $("input[name='teacherId']").length;
		var len = $("input[name='teacherId']:checked").length;
		if(leng!=null && leng==len){
			$("#selectAll").attr('checked',true);
		}else{
			$("#selectAll").attr('checked',false);
		}
	});
});

/**
 * 选择老师 
 * @param type 页面显示的单选还是多选 checkbox多选（默认），radio单选
 */
function selectTeacher(type){
	if(type==null || $.trim(type)==''){
		type='checkbox';
	}
	window.open(baselocation+'/admin/teacher/selectlist/'+type ,'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=600');
}

/**
 * 确认选择
 */
function confirmSelect(){
	var teahcer = $("input[name='teacherId']:checked");
	if(teahcer==null || teahcer.length==0){
        layer.msg("请选择讲师", {icon: 5, shift: 6});
		return false;
	}
	var teahcerArr = [];
	for(var i=0;i<teahcer.length;i++){
		var tcId = $(teahcer[i]).val();
		var tcName = $(teahcer[i]).attr('title');
		var tc = {'id':tcId,'name':tcName};
		teahcerArr.push(tc);
	}
	window.opener.addTeahcerList(teahcerArr);
	closeWin();
}

/**
 * 关闭窗口
 */
function closeWin(){
	window.close();
}



var timer;
var winOpen;
var timerQuestion;
var winOpenQuestion;
function IfWindowClosed() {
	if (winOpen.closed == true) {
		window.clearInterval(timer);
		addPaperList(unescape(getCookie("inxeduPaper")).split(",")[0]);
	}
}
/**
 * 选择添加试卷回调
 * @param arr 老师数组
 */
function addPaperList(arr){
	if(!isEmpty(arr)){
		var paper = arr.split("|");
		//课后作业 保存课程id 章节id 试卷id
		$("#examPaperId").val(paper[0]);
		$("#content").val(paper[1]);
		$("#exercises").text(paper[1]);
	}
	setCookie("inxeduPaper","")
}


function IfWindowClosedQuestion() {
	if(isEmpty($("#examQuestionIds").val())){
		//清空
		$("#exercises").html("");
		$("#examPaperId").val("");
	}
	if (winOpenQuestion.closed == true) {
		window.clearInterval(timerQuestion);
		var questions=unescape(getCookie("inxedu_exam_questions"));
		if(!isEmpty(questions)){
			var Ids = [];
			if ($("#examQuestionIds").val().trim() != "") {
				Ids = ( $("#examQuestionIds").val()+questions).split(",");
			}else{
				Ids = unescape(getCookie("inxedu_exam_questions")).split(",");
			}
			//Ids.sort();
			Ids = uniqueArray(Ids);
			$("#examQuestionIds").val(Ids+",");
			//$("#exercises").text(Ids);

			var questionsContent=unescape(getCookie("inxedu_exam_questions_content"));
			var content = [];
			if (questionsContent != "") {
				content = questionsContent.split(",");
				var showQuestionHtml=$("#exercises").html();
				for (var i = 0; i < content.length; i++) {
					//是否选择过
					if (showQuestionHtml.indexOf(content[i])==-1) {
						showQuestionHtml+='<p style="margin: 0 0 0em;" id="questionsPId'+content[i].split("、")[0]+'">'+content[i]+'&nbsp;&nbsp;<a href="javascript:void(0)" onclick="questionsRemove('+content[i].split("、")[0]+')" class="layui-btn layui-btn-small">删除</a></p>'
					}
				}
				$("#exercises").html(showQuestionHtml);
			}
		}
		setCookie("inxedu_exam_questions","")
	}
}
function uniqueArray(a) {
	temp = [];
	for (var i = 0; i < a.length; i++) {
		if (!contains(temp, a[i])) {
			temp.length += 1;
			temp[temp.length - 1] = a[i];
		}
	}
	return temp;
}
function contains(a, e) {
	for (var j = 0; j < a.length; j++)if (a[j] == e)return true;
	return false;
}


/**
 * 选择
 */
function selectExam(){
	if($("#examType").val()==0){
		//选择试卷
		winOpen=window.open('/admin/queryExamPaperList?queryPaper.type=7&selectType=radio','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=600');
		timer=window.setInterval("IfWindowClosed()",500);
	}else{
		//选择试题
		winOpenQuestion=window.open('/admin/quest/webGetQuestionList','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=1300,height=600');
		timerQuestion=window.setInterval("IfWindowClosedQuestion()",500);
	}
}


/**
 * 删除选择的试题
 * @param questinosId
 */
function questionsRemove(questinosId){
	$("#questionsPId"+questinosId).remove();

	var examQuestionIds=$("#examQuestionIds").val();
	examQuestionIds = examQuestionIds.replace(questinosId+",","");
	$("#examQuestionIds").val(examQuestionIds);
}