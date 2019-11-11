<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>修改展视互动直播</title>
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
					var params=$("#updForm").serialize();
					$.ajax({
						url:baselocation+'/admin/liveGensee/update',
						type:'post',
						dataType:'json',
						data:params,
						success:function(result){
							if(result.success){
                                layer.msg("修改成功", {icon: 1, shift: 6});
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
		<span>修改展视互动直播</span>
	</legend>
	<div class="layui-field-box">
		<form action="${ctx}/admin/liveGensee/update" method="post" id="updForm" class="layui-form">
			<input type="hidden" name="liveGensee.id" value="${liveGensee.id }" />
			<%--<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;对应的直播id</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="required" name="liveGensee.liveId" id="liveId" value="${liveId}">
				</div>
			</div>--%>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;课堂主题</label>
				<div class="layui-input-block">
					<input type="hidden" name="liveGenseeOldSubject" class="lf" value="${liveGensee.subject }" />
					<input class="layui-input layui-input-6" type="text" lay-verify="required" name="liveGensee.subject" id="subject" value="${liveGensee.subject }">
					<p class="fsize12 c-red f-fM hLh20">（不能重复）</p>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">老师加入口令</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" name="liveGensee.teacherToken" id="teacherToken" value="${liveGensee.teacherToken }" lay-verify="">
					<p class="fsize12 c-red f-fM hLh20">（长度：6-15）</p>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">Web端学生加入口令</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="" name="liveGensee.studentToken"  id="studentToken" value="${liveGensee.studentToken }">
					<p class="fsize12 c-red f-fM hLh20">（长度：6-15）</p>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">客户端学生加入口令</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="" name="liveGensee.studentClientToken"  id="studentClientToken" value="${liveGensee.studentClientToken }">
					<p class="fsize12 c-red f-fM hLh20">（长度：6-15）</p>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">助教加入口令</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="" name="liveGensee.assistantToken"  id="assistantToken" value="${liveGensee.assistantToken }">
					<p class="fsize12 c-red f-fM hLh20">（长度：6-15）</p>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;开始日期</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="required"  name="liveGensee.startDate" id="startDate" value="<fmt:formatDate value="${liveGensee.startDate }" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">失效时间</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify=""  name="liveGensee.invalidDate" id="invalidDate" value="<fmt:formatDate value="${liveGensee.invalidDate }" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})">
				</div>
			</div>
			<div class="layui-form-item" style="display: none">
				<label class="layui-form-label layui-form-label-w">老师介绍</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="" name="liveGensee.speakerInfo" id="speakerInfo" value="${liveGensee.speakerInfo }">
				</div>
			</div>
			<div class="layui-form-item" style="display: none">
				<label class="layui-form-label layui-form-label-w">课程介绍</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="" name="liveGensee.scheduleInfo" id="scheduleInfo" value="${liveGensee.scheduleInfo }">
				</div>
			</div>
			<div class="layui-form-item" style="display: none">
				<label class="layui-form-label layui-form-label-w">是否支持Web端学生加入<%--,值为true或者 false。默认值为true--%></label>
				<div class="layui-input-block layui-select-inline">
					<select lay-filter="*" id="webJoin" name="liveGensee.webJoin">
						<option value="true">是</option>
						<option value="false">否</option>
					</select>
					<p class="fsize12 c-red f-fM hLh20">(Web端和客户端不能同时为false,如果设置都为false则都取默认值)</p>
					<script type="text/javascript">
						$("#webJoin").val("${liveGensee.webJoin }");
					</script>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">是否支持客户端端学生加入<%--,值为 true或 者 false。默认值为true--%></label>
				<div class="layui-input-block layui-select-inline">
					<select lay-filter="*"  id="clientJoin" name="liveGensee.clientJoin">
						<option value="true">是</option>
						<option value="false">否</option>
					</select>
					<p class="fsize12 c-red f-fM hLh20">(Web端和客户端不能同时为false,如果设置都为false则都取默认值)</p>
					<script type="text/javascript">
						$("#clientJoin").val("${liveGensee.clientJoin }");
					</script>
				</div>
			</div>
			<div class="layui-form-item" style="display: none">
				<label class="layui-form-label layui-form-label-w">课堂介绍</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="" name="liveGensee.description" id="description" value="${liveGensee.description }">
				</div>
			</div>
			<div class="layui-form-item" style="display: none">
				<label class="layui-form-label layui-form-label-w">课堂持续时长</label>
				<div class="layui-input-block">
					<input class="layui-input layui-input-6" type="text" lay-verify="" name="liveGensee.duration" id="duration" value="${liveGensee.duration }">
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
				<script type="text/javascript">
					$("#uiMode").val("${liveGensee.uiMode }");
				</script>
			</div>
			<div class="layui-form-item" style="display:none;" id="uiWindowTag">
				<label class="layui-form-label layui-form-label-w">web学生端文档/视频界面显示小窗口<%--uiMode等于 2时候，设置是否显示小窗 口。 默认为false--%></label>
				<div class="layui-input-block layui-select-inline">
					<select lay-filter="*"  id="uiWindow" name="liveGensee.uiWindow">
						<option value="false">否</option>
						<option value="true">是</option>
					</select>
				</div>
				<script type="text/javascript">
					$("#uiMode").val("${liveGensee.uiMode }");
				</script>
			</div>
			<div class="layui-form-item" style="display:none;" id="uiVideoTag">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>是否视频为主<%--uiMode等于 2时候，设置是否视频为主。 默认为false--%></label>
				<div class="layui-input-block layui-select-inline">
					<select lay-filter="*" id="uiVideo" name="liveGensee.uiVideo">
						<option value="false">否</option>
						<option value="true">是</option>
					</select>
				</div>
				<script type="text/javascript">
					$("#uiColor").val("${liveGensee.uiColor }");
					$(function(){
						uiModeChange("${liveGensee.uiMode }");
					})
				</script>
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
				<script type="text/javascript">
					$("#uiColor").val("${liveGensee.uiColor }");
				</script>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w">课堂类型<%--0:大讲堂，1：小班课，默认值：0--%></label>
				<div class="layui-input-block layui-select-inline">
					<select lay-filter="*"  id="scene" name="liveGensee.scene">
						<option value="0">大讲堂</option>
						<option value="1">小班课</option>
					</select>
				</div>
				<script type="text/javascript">
					$("#scene").val("${liveGensee.scene }");
				</script>
			</div>
			<div class="layui-form-item" style="display: none">
				<label class="layui-form-label layui-form-label-w">是否允许web升级到客户端</label>
				<div class="layui-input-block layui-select-inline">
					<select lay-filter="*" id="upgrade" name="liveGensee.upgrade">
						<option value="false">否</option>
						<option value="true">是</option>
					</select>
				</div>
				<script type="text/javascript">
					$("#upgrade").val("${liveGensee.upgrade }");
				</script>
			</div>
			<div class="layui-form-item" style="display: none">
				<label class="layui-form-label layui-form-label-w">密码加密<%--true:表示密码是经过加密的。--%></label>
				<div class="layui-input-block layui-select-inline">
					<select lay-filter="*" id="sec" name="liveGensee.sec">
						<option value="false">否</option>
						<option value="true">是</option>
					</select>
					<p class="fsize12 c-red f-fM hLh20">(后台配置的展视互动密码是否经过加密)</p>
					<script type="text/javascript">
						$("#sec").val("${liveGensee.sec }");
					</script>
				</div>
			</div>
			<div class="layui-form-item" style="display: none">
				<label class="layui-form-label layui-form-label-w">无延迟模式<%--true:表示实时的，false：表示非实时的， 默认是false--%></label>
				<div class="layui-input-block layui-select-inline">
					<select lay-filter="*" id="realtime" name="liveGensee.realtime">
						<option value="false">否</option>
						<option value="true">是</option>
					</select>
				</div>
				<script type="text/javascript">
					$("#realtime").val("${liveGensee.realtime }");
				</script>
			</div>
			<div class="layui-form-item" style="display: none">
				<label class="layui-form-label layui-form-label-w">课堂最大并发数<%--。 只有站点开启指定并发数功能，才能够设 置。否则即使传入数据也无效。--%></label>
				<div class="layui-input-block layui-select-inline">
					<input class="layui-input layui-input-6" type="text" lay-verify="" name="liveGensee.maxAttendees" id="maxAttendees" value="${liveGensee.maxAttendees }" >
					<p class="fsize12 c-red f-fM hLh20">（只有站点开启指定并发数功能，才能够设 置。否则即使传入数据也无效）</p>
				</div>
			</div>
			<div class="layui-form-item" style="display: none">
				<label class="layui-form-label layui-form-label-w">返回实时课堂ID</label>
				<div class="layui-input-block layui-select-inline">
					<input class="layui-input layui-input-6" type="text" lay-verify="" name="liveGensee.genseeId" id="genseeId" value="${liveGensee.genseeId }" >
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
			<p style="display: none">
				<label><font color="red">*</font>返回课堂编号</label>
				<input type="text" name="liveGensee.number" class="lf" data-rule="required;" id="number" value="${liveGensee.number}" />
				<span class="field_desc"></span>
			</p>
			<p style="display: none">
				<label><font color="red">*</font>返回 老师和助教加入URL</label>
				<input type="text" name="liveGensee.teacherJoinUrl" class="lf" data-rule="required;" id="teacherJoinUrl" value="${liveGensee.teacherJoinUrl}" />
				<span class="field_desc"></span>
			</p>
			<p style="display: none">
				<label><font color="red">*</font>学员加入URL</label>
				<input type="text" name="liveGensee.studentJoinUrl" class="lf" data-rule="required;" id="studentJoinUrl" value="${liveGensee.studentJoinUrl}" />
				<span class="field_desc"></span>
			</p>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="*">保存</button>
					<button type="reset" class="layui-btn layui-btn-primary" onclick="closeWin()">关闭</button>
				</div>
			</div>
		</form>
	</div>
</fieldset>
</body>
</html>
