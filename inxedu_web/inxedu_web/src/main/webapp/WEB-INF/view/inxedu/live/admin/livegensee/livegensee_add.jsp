<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>添加展视互动直播</title>
<script type="text/javascript">
	layui.use(['form', 'laydate'], function () {
		var form = layui.form();
		var laydate = layui.laydate;
		form.on('select(uiMode)', function (data) {
			uiModeChange(data.value)
		});
		form.on('submit(*)', function(data){
			//执行提交方法
			formSubmit();
			return false;
		});
		//各种基于事件的操作，下面会有进一步介绍
	});

function formSubmit(){
			var teacherToken =$("#teacherToken").val();
			var studentToken =$("#studentToken").val();
			var studentClientToken =$("#studentClientToken").val();
			var assistantToken =$("#assistantToken").val();

			//验证重复
			var tempDatas = [];
			//不是空的长度
			var tempDatasLength=0;
			if(isNotEmpty(teacherToken)){
				if(teacherToken.length<6||teacherToken.length>15){
                    layer.msg("老师加入口令（长度：6-15）", {icon: 5, shift: 6});
					return false;
				}
				tempDatas.push(teacherToken);
				tempDatasLength++;
			}
			if(isNotEmpty(studentToken)){
				if(studentToken.length<6||studentToken.length>15){
                    layer.msg("Web端学生加入口令（长度：6-15）", {icon: 5, shift: 6});
					return false;
				}
				tempDatas.push(studentToken);
				tempDatasLength++;
			}
			if(isNotEmpty(studentClientToken)){
				if(studentClientToken.length<6||studentClientToken.length>15){
                    layer.msg("客户端学生加入口令 （长度：6-15）", {icon: 5, shift: 6});
					return false;
				}
				tempDatas.push(studentClientToken);
				tempDatasLength++;
			}
			if(isNotEmpty(assistantToken)){
				if(assistantToken.length<6||assistantToken.length>15){
                    layer.msg("助教加入口令（长度：6-15）", {icon: 5, shift: 6});
					return false;
				}
				tempDatas.push(assistantToken);
				tempDatasLength++;
			}
			tempDatas=uniqueArray(tempDatas);
			if(tempDatas.length!=tempDatasLength){
                layer.msg("口令可以为空，但是不能相同", {icon: 5, shift: 6});
				return false;
			}

			var startDate =$("#startDate").val();
			if(isEmpty(startDate)){
                layer.msg("开始日期不能为空", {icon: 5, shift: 6});
				return false;
			}
			var params=$("#addForm").serialize();
			$.ajax({
				url:baselocation+'/admin/liveGensee/add',
				type:'post',
				dataType:'json',
				data:params,
				success:function(result){
					if(result.success){
                        layer.msg("创建成功", {icon: 1, shift: 6});
						var liveGensee=result.entity;
						confirmSelect(liveGensee.teacherJoinUrl,liveGensee.studentJoinUrl,liveGensee.id);
					}else {
                        layer.msg(result.message, {icon: 5, shift: 6});
					}
				}
			});
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

	function uiModeChange(uiMode) {
		if(uiMode==2){//文档/视频为主
			$("#uiWindowTag").show();
			$("#uiVideoTag").show();
		}else {
			$("#uiWindowTag").hide();
			$("#uiVideoTag").hide();
		}
	}

/**
 * 确认选择
 */
function confirmSelect(teacherJoinUrl,studentJoinUrl,genseeId){
		var data = {'teacherJoinUrl':teacherJoinUrl,'studentJoinUrl':studentJoinUrl,"genseeId":genseeId};
	window.opener.addGenseeData(data);
	closeWin();
}

/**
 * 关闭窗口
 */
function closeWin(){
	window.close();
}
</script>
</head>
<body>
<fieldset class="layui-elem-field">
	<legend>
		<span>展视互动直播管理</span>
		&gt;
		<span>添加展视互动直播</span>
	</legend>
	<div class="layui-field-box">
		<form action="${ctx}/admin/liveGensee/add" method="post" id="addForm" class="layui-form">
			<div class="layui-form-item" style="display: none;">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;对应的直播id</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="required" name="liveGensee.liveId" id="liveId" value="${liveId}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;课堂主题</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="required" name="liveGensee.subject" id="subject" value="${kpointName}">
					<p class="fsize12 c-red f-fM hLh20">（不能重复）</p>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">老师加入口令</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="" name="liveGensee.teacherToken" id="teacherToken">
					<p class="fsize12 c-red f-fM hLh20">（长度：6-15）</p>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">Web端学生加入口令</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="" name="liveGensee.studentToken"  id="studentToken" value="">
					<p class="fsize12 c-red f-fM hLh20">（长度：6-15）</p>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">客户端学生加入口令</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="" name="liveGensee.studentClientToken"  id="studentClientToken" value="">
					<p class="fsize12 c-red f-fM hLh20">（长度：6-15）</p>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">助教加入口令</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="" name="liveGensee.assistantToken"  id="assistantToken" value="">
					<p class="fsize12 c-red f-fM hLh20">（长度：6-15）</p>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;开始日期</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="required"  name="liveGensee.startDate" id="startDate"  onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">失效时间</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify=""  name="liveGensee.invalidDate" id="invalidDate"  onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})">
				</div>
			</div>
			<div class="layui-form-item" style="display: none;">
				<label class="layui-form-label layui-form-label-w">老师介绍</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="" name="liveGensee.speakerInfo" id="speakerInfo">
				</div>
			</div>
			<div class="layui-form-item" style="display: none;">
				<label class="layui-form-label layui-form-label-w">课程介绍</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="" name="liveGensee.scheduleInfo" id="scheduleInfo">
				</div>
			</div>
			<div class="layui-form-item" style="display: none;">
				<label class="layui-form-label layui-form-label-w">是否支持Web端学生加入<%--,值为true或者 false。默认值为true--%></label>
				<div class="layui-input-block layui-select-inline">
					<select lay-filter="*" id="webJoin" name="liveGensee.webJoin">
						<option value="true">是</option>
						<option value="false">否</option>
					</select>
					<p class="fsize12 c-red f-fM hLh20">(Web端和客户端不能同时为false,如果设置都为false则都取默认值)</p>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">是否支持客户端端学生加入<%--,值为 true或 者 false。默认值为true--%></label>
				<div class="layui-input-block layui-select-inline">
					<select lay-filter="*"  id="clientJoin" name="liveGensee.clientJoin">
						<option value="true">是</option>
						<option value="false">否</option>
					</select>
					<%--<p class="fsize12 c-red f-fM hLh20">(Web端和客户端不能同时为false,如果设置都为false则都取默认值)</p>--%>
				</div>
			</div>
			<div class="layui-form-item" style="display: none;">
				<label class="layui-form-label layui-form-label-w">课堂介绍</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="" name="liveGensee.description" id="description">
				</div>
			</div>
			<div class="layui-form-item" style="display: none;">
				<label class="layui-form-label layui-form-label-w">课堂持续时长</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="" name="liveGensee.duration" id="duration">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">Web端学生界面设置<%--(1 是三分屏，2是 文档/视频为主，3是两分屏，4互动增 加)--%></label>
				<div class="layui-input-block layui-select-inline">
					<select lay-filter="uiMode" id="uiMode" name="liveGensee.uiMode">
						<option value="1">三分屏</option>
						<option value="2">文档/视频为主</option>
						<option value="3">两分屏</option>
						<option value="4">互动增加</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item"  style="display:none;" id="uiWindowTag">
				<label class="layui-form-label layui-form-label-w">web学生端文档/视频界面显示小窗口<%--uiMode等于 2时候，设置是否显示小窗 口。 默认为false--%></label>
				<div class="layui-input-block layui-select-inline">
					<select lay-filter="*"  id="uiWindow" name="liveGensee.uiWindow">
						<option value="false">否</option>
						<option value="true">是</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item" style="display:none;" id="uiVideoTag">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>是否视频为主<%--uiMode等于 2时候，设置是否视频为主。 默认为false--%></label>
				<div class="layui-input-block layui-select-inline">
					<select lay-filter="*" id="uiVideo" name="liveGensee.uiVideo">
						<option value="false">否</option>
						<option value="true">是</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">三分屏颜色选择<%--（blue, default, green）， 默认是default--%></label>
				<div class="layui-input-block layui-select-inline">
					<select lay-filter="*"  id="uiColor" name="liveGensee.uiColor">
						<option value="default">灰色</option>
						<option value="blue">蓝色</option>
						<option value="green">绿色</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">课堂类型<%--0:大讲堂，1：小班课，默认值：0--%></label>
				<div class="layui-input-block layui-select-inline">
					<select lay-filter="*"  id="scene" name="liveGensee.scene">
						<option value="0">大讲堂</option>
						<option value="1">小班课</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item" style="display: none;">
				<label class="layui-form-label layui-form-label-w">是否允许web升级到客户端</label>
				<div class="layui-input-block layui-select-inline">
					<select lay-filter="*" id="upgrade" name="liveGensee.upgrade">
						<option value="false">否</option>
						<option value="true">是</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item" style="display: none;">
				<label class="layui-form-label layui-form-label-w">密码加密<%--true:表示密码是经过加密的。--%></label>
				<div class="layui-input-block layui-select-inline">
					<select lay-filter="*" id="sec" name="liveGensee.sec">
						<option value="false">否</option>
						<option value="true">是</option>
					</select>
					<p class="fsize12 c-red f-fM hLh20">(后台配置的展视互动密码是否经过加密)</p>
				</div>
			</div>
			<div class="layui-form-item" style="display: none;">
				<label class="layui-form-label layui-form-label-w">无延迟模式<%--true:表示实时的，false：表示非实时的， 默认是false--%></label>
				<div class="layui-input-block layui-select-inline">
					<select lay-filter="*" id="realtime" name="liveGensee.realtime">
						<option value="false">否</option>
						<option value="true">是</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item" style="display: none;">
				<label class="layui-form-label layui-form-label-w">课堂最大并发数<%--。 只有站点开启指定并发数功能，才能够设 置。否则即使传入数据也无效。--%></label>
				<div class="layui-input-block layui-select-inline">
					<input class="layui-input layui-input-6" type="text" lay-verify="" name="liveGensee.maxAttendees" id="maxAttendees">
					<p class="fsize12 c-red f-fM hLh20">（只有站点开启指定并发数功能，才能够设 置。否则即使传入数据也无效）</p>
				</div>
			</div>
			<%--<p>
							<label><font color="red">*</font>登录名</label>
							<input type="text" name="liveGensee.loginName" class="lf" data-rule="required;" id="loginName" value="" />
							<span class="field_desc"></span>
						</p>
						<p>
							<label><font color="red">*</font>密码</label>
							<input type="text" name="liveGensee.password" class="lf" data-rule="required;" id="password" value="" />
							<span class="field_desc"></span>
						</p>--%>
			<%--<p>
				<label><font color="red">*</font>返回实时课堂ID</label>
				<input type="text" name="liveGensee.genseeId" class="lf" data-rule="required;" id="genseeId" value="" />
				<span class="field_desc"></span>
			</p>
			<p>
				<label><font color="red">*</font>返回课堂编号</label>
				<input type="text" name="liveGensee.number" class="lf" data-rule="required;" id="number" value="" />
				<span class="field_desc"></span>
			</p>
			<p>
				<label><font color="red">*</font>返回 老师和助教加入URL</label>
				<input type="text" name="liveGensee.teacherJoinUrl" class="lf" data-rule="required;" id="teacherJoinUrl" value="" />
				<span class="field_desc"></span>
			</p>
			<p>
				<label><font color="red">*</font>学员加入URL</label>
				<input type="text" name="liveGensee.studentJoinUrl" class="lf" data-rule="required;" id="studentJoinUrl" value="" />
				<span class="field_desc"></span>
			</p>--%>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="*">保存</button>
					<button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1);">返回</button>
				</div>
			</div>
		</form>
	</div>
</fieldset>
	<%--<fieldset class="layui-elem-field">
		<legend>
			<span>展视互动直播管理</span>
			&gt;
			<span>添加展视互动直播</span>
		</legend>
		<!-- /tab4 begin -->
		<div class="mt20">
			<div class="commonWrap">
				<form action="${ctx}/admin/liveGensee/add" method="post" id="addForm">
					<p>
						<span>
								展视互动直播基本属性
								<tt class="c_666 ml20 fsize12">
									（<font color="red">*</font>&nbsp;为必填项）
								</tt>
							</span>
						<span class="field_desc"></span>
					</p>
					<p style="display: none">
						<label><font color="red">*</font>对应的直播id</label>
						<input type="text" name="liveGensee.liveId" class="lf" data-rule="required;" id="liveId" value="${liveId}" />
						<span class="field_desc"></span>
					</p>
					<p>
						<label><font color="red">*</font>课堂主题</label>
						<input type="text" name="liveGensee.subject" class="lf" data-rule="required;" id="subject" value="${kpointName}" />
						<span class="field_desc">（不能重复）</span>
					</p>
					<p>
						<label>老师加入口令</label>
						<input type="text" name="liveGensee.teacherToken" class="lf" id="teacherToken" value="" />
						<span class="field_desc">（长度：6-15）</span>
					</p>
					<p>
						<label>Web端学生加入口令&lt;%&ndash;（长度：最大 15）&ndash;%&gt;</label>
						<input type="text" name="liveGensee.studentToken" class="lf" id="studentToken" value="" />
						<span class="field_desc">（长度：6-15）</span>
					</p>
					<p>
						<label>客户端学生加入口令</label>
						<input type="text" name="liveGensee.studentClientToken" class="lf" id="studentClientToken" value="" />
						<span class="field_desc">（长度：6-15）</span>
					</p>
					<p>
						<label>助教加入口令&lt;%&ndash;（长度：6-15）（会自动生 成随机数）&ndash;%&gt;</label>
						<input type="text" name="liveGensee.assistantToken" class="lf" id="assistantToken" value="" />
						<span class="field_desc">（长度：6-15）</span>
					</p>
					<p>
						<label><font color="red">*</font>开始日期</label>
						<input type="text" name="liveGensee.startDate" class="lf" id="startDate" value="<fmt:formatDate value="${not empty beginTime?beginTime:now}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
						<span class="field_desc"></span>
					</p>
					<p>
						<label>失效时间</label>
						<input type="text" name="liveGensee.invalidDate" class="lf" id="invalidDate" value="<fmt:formatDate value="${liveEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
						<span class="field_desc"></span>
					</p>
					<p style="display: none">
						<label>老师介绍</label>
						<input type="text" name="liveGensee.speakerInfo" class="lf" id="speakerInfo" value="" />
						<span class="field_desc"></span>
					</p>
					<p style="display: none">
						<label>课程介绍</label>
						<input type="text" name="liveGensee.scheduleInfo" class="lf"  id="scheduleInfo" value="" />
						<span class="field_desc"></span>
					</p>
					<p style="display: none">
						<label>是否支持Web端学生加入&lt;%&ndash;,值为true或者 false。默认值为true&ndash;%&gt;</label>
						<select id="webJoin" name="liveGensee.webJoin">
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
						<span class="field_desc">(Web端和客户端不能同时为false,如果设置都为false则都取默认值)</span>
					</p>
					<p>
						<label>是否支持客户端端学生加入&lt;%&ndash;,值为 true或 者 false。默认值为true&ndash;%&gt;</label>
						<select id="clientJoin" name="liveGensee.clientJoin">
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
						<span class="field_desc">(Web端和客户端不能同时为false,如果设置都为false则都取默认值)</span>
					</p>
					<p style="display: none">
						<label>课堂介绍</label>
						<input type="text" name="liveGensee.description" class="lf" id="description" value="" />
						<span class="field_desc"></span>
					</p>
					<p style="display: none">
						<label>课堂持续时长</label>
						<input type="text" name="liveGensee.duration" class="lf" id="duration" value="" />
						<span class="field_desc">（单位为分钟）</span>
					</p>
					<p>
						<label>Web端学生界面设置&lt;%&ndash;(1 是三分屏，2是 文档/视频为主，3是两分屏，4互动增 加)&ndash;%&gt;</label>
						<select id="uiMode" name="liveGensee.uiMode" onchange="uiModeChange()">
							<option value="1">三分屏</option>
							<option value="2">文档/视频为主</option>
							<option value="3">两分屏</option>
							<option value="4">互动增加</option>
						</select>
						<span class="field_desc"></span>
					</p>
					<p style="display:none;" id="uiWindowTag">
						<label>web学生端文档/视频界面显示小窗口&lt;%&ndash;uiMode等于 2时候，设置是否显示小窗 口。 默认为false&ndash;%&gt;</label>
						<select id="uiWindow" name="liveGensee.uiWindow">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span class="field_desc"></span>
					</p>
					<p  style="display:none;" id="uiVideoTag">
						<label><font color="red">*</font>是否视频为主&lt;%&ndash;uiMode等于 2时候，设置是否视频为主。 默认为false&ndash;%&gt;</label>
						<select id="uiVideo" name="liveGensee.uiVideo">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span class="field_desc"></span>
					</p>
					<p>
						<label>三分屏颜色选择&lt;%&ndash;（blue, default, green）， 默认是default&ndash;%&gt;</label>
						<select id="uiColor" name="liveGensee.uiColor">
							<option value="default">灰色</option>
							<option value="blue">蓝色</option>
							<option value="green">绿色</option>
						</select>
						<span class="field_desc"></span>
					</p>
					<p>
						<label>课堂类型&lt;%&ndash;0:大讲堂，1：小班课，默认值：0&ndash;%&gt;</label>
						<select id="scene" name="liveGensee.scene">
							<option value="0">大讲堂</option>
							<option value="1">小班课</option>
						</select>
						<span class="field_desc"></span>
					</p>

					<p style="display: none">
						<label>是否允许web升级到客户端</label>
						<select id="upgrade" name="liveGensee.upgrade">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span class="field_desc"></span>
					</p>
					<p style="display: none">
						<label>密码加密&lt;%&ndash;true:表示密码是经过加密的。&ndash;%&gt;</label>
						<select id="sec" name="liveGensee.sec">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span class="field_desc">(后台配置的展视互动密码是否经过加密)</span>
					</p>
					<p style="display: none">
						<label>无延迟模式&lt;%&ndash;true:表示实时的，false：表示非实时的， 默认是false&ndash;%&gt;</label>
						<select id="realtime" name="liveGensee.realtime">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span class="field_desc"></span>
					</p>
					<p style="display: none">
						<label>课堂最大并发数&lt;%&ndash;。 只有站点开启指定并发数功能，才能够设 置。否则即使传入数据也无效。&ndash;%&gt;</label>
						<input type="text" name="liveGensee.maxAttendees" class="lf" id="maxAttendees" value="" />
						<span class="field_desc">（只有站点开启指定并发数功能，才能够设 置。否则即使传入数据也无效）</span>
					</p>
					&lt;%&ndash;<p>
						<label><font color="red">*</font>登录名</label>
						<input type="text" name="liveGensee.loginName" class="lf" data-rule="required;" id="loginName" value="" />
						<span class="field_desc"></span>
					</p>
					<p>
						<label><font color="red">*</font>密码</label>
						<input type="text" name="liveGensee.password" class="lf" data-rule="required;" id="password" value="" />
						<span class="field_desc"></span>
					</p>&ndash;%&gt;
					&lt;%&ndash;<p>
						<label><font color="red">*</font>返回实时课堂ID</label>
						<input type="text" name="liveGensee.genseeId" class="lf" data-rule="required;" id="genseeId" value="" />
						<span class="field_desc"></span>
					</p>
					<p>
						<label><font color="red">*</font>返回课堂编号</label>
						<input type="text" name="liveGensee.number" class="lf" data-rule="required;" id="number" value="" />
						<span class="field_desc"></span>
					</p>
					<p>
						<label><font color="red">*</font>返回 老师和助教加入URL</label>
						<input type="text" name="liveGensee.teacherJoinUrl" class="lf" data-rule="required;" id="teacherJoinUrl" value="" />
						<span class="field_desc"></span>
					</p>
					<p>
						<label><font color="red">*</font>学员加入URL</label>
						<input type="text" name="liveGensee.studentJoinUrl" class="lf" data-rule="required;" id="studentJoinUrl" value="" />
						<span class="field_desc"></span>
					</p>&ndash;%&gt;
					<p>
						<input type="button" value="保 存" class="layui-btn layui-btn-small" onclick="formSubmit()" />
					</p>
				</form>
			</div>
		</div>
	</fieldset>--%>
</body>
</html>
