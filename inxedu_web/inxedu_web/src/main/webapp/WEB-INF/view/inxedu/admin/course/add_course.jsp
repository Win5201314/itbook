<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><c:set var="courseSellType" value="课程"></c:set>
	<c:if test="${sellType=='LIVE'}">
		<c:set var="courseSellType" value="直播"></c:set>
	</c:if>
	<c:if test="${sellType=='PACKAGE'}">
		<c:set var="courseSellType" value="套餐"></c:set>
	</c:if>${courseSellType}添加</title>
<script type="text/javascript" src="${ctx}/static/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/common/multilevel.js"></script>
<script type="text/javascript" src="${ctx}/static/common/uploadify/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
<script type="text/javascript" src="${ctx}/kindeditor/kindeditor-all.js"></script>
<script src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js"></script>

<script type="text/javascript" src="${ctx}/static/admin/course/course.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/teacher/select_teacher_list.js"></script>
<%--验证框架--%>
<link rel="stylesheet" href="${ctx}/static/common/nice-validator/jquery.validator.css"/>
<script type="text/javascript" src="${ctx}/static/common/nice-validator/jquery.validator.js"></script>
<script type="text/javascript" src="${ctx}/static/common/nice-validator/local/zh-CN.js"></script>
<%--百度编译器--%>
<script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.all.min.js"> </script>

<script type="text/javascript">
	layui.use(['form', 'laydate'], function () {
		var form = layui.form();
		var laydate = layui.laydate;
		form.on('select(sellType)', function (data) {
			ifLive($("#sellType"));
		});
		//各种基于事件的操作，下面会有进一步介绍
	});
    subjectList='${subjectList}';
    $(function(){
		ifLive($("#sellType"));
    });
    function ifLive(obj) {
        if ($(obj).val()=="LIVE"){
            $(".showLive").show();
		}else {
            $(".showLive").hide();
		}
    }
    </script>
</head>
<body>
<c:set var="courseSellType" value="课程"></c:set>
<c:if test="${sellType=='LIVE'}">
	<c:set var="courseSellType" value="直播"></c:set>
</c:if>
<c:if test="${sellType=='PACKAGE'}">
	<c:set var="courseSellType" value="套餐"></c:set>
</c:if>
<fieldset class="layui-elem-field">
	<legend>
		<span>${courseSellType}管理</span>
		&gt;
		<span>添加${courseSellType}</span>
	</legend>
	<div class="layui-field-box">
		<form action="${ctx}/admin/cou/addCourse" method="post" id="saveCourseForm" data-validator-option="{stopOnError:false, timely:false}" class="layui-form">
			<input type="hidden" name="course.logo" />
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">名称</label>
				<div class="layui-input-block">
					<input name="course.courseName" type="text"  class="layui-input layui-input-6"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">专业分类</label>
				<div class="layui-input-block">
					<input type="hidden" id="subjectId" name="course.subjectId" value="${course.subjectId}" />
					<input id="subjectNameBtn" type="text" class="layui-input layui-input-6" value=""  onclick="showSubjectMenu()"/>
					<div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;z-index:100;width: 59%">
						<ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:100%;"></ul>
					</div>
				</div>
				<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
				<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
				<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
				<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.exedit-3.5.js?v=${v}"></script>
				<script type="text/javascript" src="/static/admin/subject/subject_util.js"></script>
				<script>
					//全部专业的json数据
					var subject_treedata=${subjectList};
				</script>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">类型</label>
				<div class="layui-input-block layui-select-inline">
					<select id="sellType" lay-filter="sellType" onchange="ifLive(this)" class="valid" name="course.sellType">
						<option value="COURSE">课程</option>
						<c:if test="${serviceSwitch.live=='ON'}">
							<option value="LIVE">直播</option>
						</c:if>
						<c:if test="${serviceSwitch.PackageSwitch=='ON'}">
							<option value="PACKAGE">套餐</option>
						</c:if>
					</select>
				</div>
			</div>
			<div class="layui-form-item showLive" style="display: none;">
				<label class="layui-form-label layui-form-label-w">直播开始时间</label>
				<div class="layui-input-block">
					<input name="course.liveBeginTime" readonly="readonly" id="liveBeginTime" onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})" type="text" class="layui-input layui-input-6"/>
				</div>
			</div>
			<div class="layui-form-item showLive" style="display: none;">
				<label class="layui-form-label layui-form-label-w">直播结束时间</label>
				<div class="layui-input-block">
					<input name="course.liveEndTime" readonly="readonly" id="liveEndTime" type="text"onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})" class="layui-input layui-input-6" onchange="changeEndTime(this)"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">状态</label>
				<div class="layui-input-block layui-select-inline">
					<select id="isavaliable" class="valid" name="course.isavaliable">
						<option value="1">上架</option>
						<option value="2">下架</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">总课时</label>
				<div class="layui-input-block">
					<input name="course.lessionNum" value="0" type="text" class="layui-input layui-input-6" data-rule="required;integer[+0]"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">原价格</label>
				<div class="layui-input-block">
					<input name="course.sourcePrice" value="0" type="text" class="layui-input layui-input-6" data-rule="required;integer[+0]"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">售价</label>
				<div class="layui-input-block">
					<input name="course.currentPrice" type="text" value="0" class="layui-input layui-input-6" data-rule="required;"/>
				</div>
			</div>
			<div class="layui-form-item" style="display: none">
				<label class="layui-form-label layui-form-label-w">有效期类型</label>
				<div class="layui-input-block layui-select-inline">
					<select id="losetype" class="valid select" name="course.loseType">
						<%--<option value="0">到期时间</option>--%>
						<option value="1">按天数</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item endTimeShow" >
				<label class="layui-form-label layui-form-label-w">有效期结束时间</label>
				<div class="layui-input-block">
					<input name="course.endTime" readonly="readonly" id="endTime" type="text" class="layui-input layui-input-6"/>
				</div>
			</div>
			<div class="layui-form-item loseTimeShow" style="display: none;">
				<label class="layui-form-label layui-form-label-w">按天数</label>
				<div class="layui-input-block">
					<input style="display: inline-block" id="loseTime"  class="required number layui-input layui-input-6" type="text" value="365" name="course.loseTime" onkeyup="this.value=this.value.replace(/\D/g,'')" />
					<span class="fsize14 c-999 f-fM vam">天</span>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">添加教师</label>
				<div class="layui-input-block">
					<input type="hidden" name="teacherIdArr" value="" />
					<div id="teacherList" class="teacherList" style="display: none"></div>
					<a href="javascript:void(0)" onclick="selectTeacher()" class="layui-btn layui-btn-small">选择老师</a>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">简介</label>
				<div class="layui-input-block layui-textarea-block">
					<textarea class="layui-textarea" placeholder="请输入内容" name="course.title"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">图片</label>
				<div class="layui-input-block">
					<img id="showImage" width="278px" height="155" src="${ctx }/static/admin/assets/logo.png" />
					<input type="button" value="上传" id="imageFile" />
					<font color="red">(请上传 640*357(长X宽)像素 的图片)</font>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">详情</label>
				<div class="layui-input-block" style="width: 55%;">
					<textarea name="course.context" id="content" data-rule="required;" class=""></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<input onclick="saveCourse()" class="layui-btn" type="button" value="下一步" />
					<input onclick="history.go(-1);" class="layui-btn" type="button" value="返回" />
				</div>
			</div>
		</form>
	</div>
</fieldset>
</body>
</html>
