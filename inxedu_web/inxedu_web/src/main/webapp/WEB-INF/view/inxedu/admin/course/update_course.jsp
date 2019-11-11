<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><c:set var="courseSellType" value="课程"></c:set>
	<c:if test="${course.sellType=='LIVE'}">
		<c:set var="courseSellType" value="直播"></c:set>
	</c:if>
	<c:if test="${course.sellType=='PACKAGE'}">
		<c:set var="courseSellType" value="套餐"></c:set>
	</c:if>${courseSellType}修改</title>


<script type="text/javascript" src="${ctx}/static/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/common/multilevel.js"></script>
<script type="text/javascript" src="${ctx}/static/common/uploadify/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
<script type="text/javascript" src="${ctx}/kindeditor/kindeditor-all.js"></script>
<script src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js"></script>
<script src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/course/course.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/teacher/select_teacher_list.js"></script>
<link rel="stylesheet" href="${ctx}/static/common/nice-validator/jquery.validator.css"/>
<script type="text/javascript" src="${ctx}/static/common/nice-validator/jquery.validator.js"></script>
<script type="text/javascript" src="${ctx}/static/common/nice-validator/local/zh-CN.js"></script>
<%--百度编译器--%>
<script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.all.min.js"> </script>

<link rel="stylesheet" href="/static/admin/css/skin_clean.css"  media="all"><%--日历css--%>

<script type="text/javascript">
	layui.use(['form', 'laydate'], function () {
		var form = layui.form();
		var laydate = layui.laydate;
		form.on('select(filter)', function (data) {
		});
		//各种基于事件的操作，下面会有进一步介绍
	});

    subjectList='${subjectList}';
    $(function(){

    	addTeahcerList(${teacherList});
		if("${course.sellType}"=="LIVE"){
			$(".showLive").show();
		}
    });
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
	<legend>
		<span>${courseSellType}管理</span>
		&gt;
		<span>修改${courseSellType}</span>
	</legend>
	<div class="layui-field-box">
		<form action="${ctx}/admin/cou/updateCourse" class="layui-form" method="post" id="saveCourseForm" data-validator-option="{stopOnError:false, timely:false}">
			<input type="hidden" name="course.courseId" value="${course.courseId}" />
			<input type="hidden" name="course.logo" value="${course.logo}" />
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">名称</label>
				<div class="layui-input-block">
					<input name="course.courseName" value="${course.courseName}" type="text" class="layui-input layui-input-6"/>
				</div>
			</div>
			<div class="layui-form-item" style="display: none;">
				<label class="layui-form-label layui-form-label-w">类型</label>
				<div class="layui-select-title">
					<select id="sellType" class="valid" lay-filter="select" name="course.sellType" onchange="sellTypeChange(this)" disabled="disabled">
						<option value="COURSE">课程</option>
						<option value="LIVE">直播</option>
					</select>
				</div>
				<script>
					$("#sellType").val('${course.sellType}');
				</script>
			</div>
			<div class="layui-form-item showLive" style="display: none;">
				<label class="layui-form-label layui-form-label-w">直播开始时间</label>
				<div class="layui-input-block">
					<input name="course.liveBeginTime" value="<fmt:formatDate value="${course.liveBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})" readonly="readonly" id="liveBeginTime" type="text" class="layui-input layui-input-6"/>
				</div>
			</div>
			<div class="layui-form-item showLive" style="display: none;">
				<label class="layui-form-label layui-form-label-w">直播结束时间</label>
				<div class="layui-input-block">
					<input name="course.liveEndTime" value="<fmt:formatDate value="${course.liveEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})" readonly="readonly" id="liveEndTime" type="text" class="layui-input layui-input-6" onchange="changeEndTime(this)"/>
				</div>
			</div>
			<div class="layui-form-item " >
				<label class="layui-form-label layui-form-label-w">专业分类</label>
				<div class="layui-input-block">
					<input type="hidden" id="subjectId" name="course.subjectId" value="${course.subjectId}" />
					<input id="subjectNameBtn" type="text" class="layui-input layui-input-6" value=""  onclick="showSubjectMenu()"/>
					<div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;z-index:100;">
						<ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:160px;"></ul>
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
				<label class="layui-form-label layui-form-label-w">状态</label>
				<div class="layui-input-inline layui-select-inline">
					<div class="layui-unselect layui-form-select">
						<select id="isavaliable" class="xz-new-sele" lay-filter="select" name="course.isavaliable">
							<option value="1">上架</option>
							<option value="2">下架</option>
						</select>
						</div>
				</div>
				<script>
					$("#isavaliable").val('${course.isavaliable}');
				</script>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">总课时</label>
				<div class="layui-input-block">
					<input name="course.lessionNum" value="${course.lessionNum}" type="text"  data-rule="required;integer[+0]" class="layui-input layui-input-6"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">原价格</label>
				<div class="layui-input-block">
					<input name="course.sourcePrice" type="text" value="${course.sourcePrice}" data-rule="required;" class="layui-input layui-input-6"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">销售价格</label>
				<div class="layui-input-block">
					<input name="course.currentPrice" type="text" value="${course.currentPrice}" data-rule="required;" class="layui-input layui-input-6"/>
				</div>
			</div>
			<div class="layui-form-item" style="display: none;">
				<label class="layui-form-label layui-form-label-w">有效期类型</label>
				<div class="layui-input-block">
					<select id="losetype" name="course.loseType" lay-filter="select" class="xz-new-sele layui-input" >
						<option value="0">到期时间</option>
						<option value="1">按天数</option>
					</select>
				</div>
				<script>
					$("#losetype").val('${course.loseType}');
				</script>
			</div>
			<div class="layui-form-item endTimeShow">
				<label class="layui-form-label layui-form-label-w">有效期结束时间</label>
				<div class="layui-input-block">
					<input name="course.endTime" value="<fmt:formatDate value="${course.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly"
						   id="endTime" type="text" class="layui-input layui-input-6"/>
				</div>
			</div>
			<div class="layui-form-item loseTimeShow">
				<label class="layui-form-label layui-form-label-w">按天数</label>
				<div class="layui-input-block">
					<input id="loseTime" class="required number layui-input layui-input-6" type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" value="${course.loseTime }" name="course.loseTime" />
				</div>
				<div class="layui-form-mid layui-word-aux">天</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">添加教师</label>
				<div class="layui-input-block">
					<input type="hidden" name="teacherIdArr" value="" />
					<div id="teacherList" class="teacherList"></div>
					<a href="javascript:void(0)" onclick="selectTeacher()" class="layui-btn layui-btn-small layui-btn-danger">选择老师</a>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">销售数量</label>
				<div class="layui-input-block">
					<input name="course.pageBuycount" data-rule="required;integer[+0]" value="${course.pageBuycount}" type="text" class="layui-input layui-input-6" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">浏览量</label>
				<div class="layui-input-block">
					<input name="course.pageViewcount" data-rule="required;integer[+0]" value="${course.pageViewcount}" type="text" class="layui-input layui-input-6" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">简介</label>
				<div class="layui-input-block layui-textarea-block">
					<textarea name="course.title" data-rule="required;" class="layui-textarea">${course.title}</textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">图片</label>
				<div class="layui-input-block">
					<c:choose>
						<c:when test="${course.logo!=null && course.logo!=''}">
							<img id="showImage" width="200px" height="117" src="${staticServer}${course.logo}" />
						</c:when>
						<c:otherwise>
							<img id="showImage" width="200px" height="117" src="${ctx }/static/admin/assets/logo.png" />
						</c:otherwise>
					</c:choose>
					<input type="button" value="上传" id="imageFile" />
					<font color="red">(请上传 640*357(长X宽)像素 的图片)</font>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">详情</label>
				<div class="layui-input-block"  style="width: 55%;">
					<textarea name="course.context" id="content" data-rule="required;" >${course.context}</textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<input onclick="saveCourse()" class="layui-btn layui-btn-small layui-btn-danger" type="button" value="保存" />
					<input onclick="history.go(-1);" class="layui-btn layui-btn-small layui-btn-danger" type="button" value="返回" />
				</div>
			</div>
		</form>
	</div>
</fieldset>
</body>
</html>
