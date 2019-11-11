<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<%--百度编译器--%>
	<script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript">
        $(function(){
            /*$("#startTime").datetimepicker({
                regional:"zh-CN",
                changeMonth: true,
                dateFormat:"yy-mm-dd",
                timeFormat : 'HH:mm:ss',
                timeFormat : 'HH:mm:ss'
            });*/
            initKindEditor_addblog('content', '100%', 200,'email','true');
          //  initKindEditor_addblog('textareaemail', '80%', 300,'email','true');
        });

        function update(){
            var content = UE.getEditor('content').getContent();
            var title = $("#title").val();

            if(title.trim()==''){
                layer.msg("邮件标题不能为空！", {icon: 5, shift: 6});
                return false;
            }
            if(content.trim()==''){
                layer.msg("邮件内容不能为空！", {icon: 5, shift: 6});
                return false;
            }

            if(confirm('确定修改?')==false){
                return false;
            }
            var startTime = $("#startTime").val();
            $.ajax({
                url : "${ctx}/admin/email/sendEmailMsg/update",
                data : {
                    "id" : '${userEmailMsg.id}',
                    "content" : content,
                    "title" : title,
                    "sendTime" : startTime,
                    "email" : $("#textareaemail").val()
                },  // 参数
                type : "post",
                async : false,
                dataType : "json",  //返回json数据
                success:function (result){
                    layer.msg(result.message, {icon: 5, shift: 6});
                    if(result.message=='成功'){
                        window.location.href="/admin/email/sendEmaillist";
                    }
                }
            });
        }
    </script>
</head>
<body >
<fieldset class="layui-elem-field">
	<legend>
		<span>邮件管理</span>
		&gt;
		<span>详情</span>
	</legend>
	<div class="layui-field-box">
		<form action="" method="post" id="addPaperForm" class="layui-form">
			<input name="user.id" id="id" type="hidden" value="${user.id}"/>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>邮件标题：</label>
				<div class="layui-input-block">
					<input name="" type="text" id="title" value="${userEmailMsg.title }" class="layui-input layui-input-6"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>邮件类型：</label>
				<div class="layui-input-block">
					<input name="" type="text" id="" value="${userEmailMsg.type==1?'普通':'定时' }" class="layui-input layui-input-6 layui-radio-disbaled layui-disabled"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>是否发送：</label>
				<div class="layui-input-block">
					<input name="" type="text" value="${userEmailMsg.status==1?'已发送':'未发送' }" class="layui-input layui-input-6 layui-radio-disbaled layui-disabled"/>
				</div>
			</div>
			<c:if test="${userEmailMsg.type==2}">
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w"><font color="red">*</font>定时发送时间：</label>
					<div class="layui-input-block">
						<input name="" type="text" value="<fmt:formatDate value="${userEmailMsg.sendTime}" type="both"/>" id="startTime" onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})" class="layui-input layui-input-6 <c:if test="${userEmailMsg.status!=2&&userEmailMsg.type!=2 }">layui-radio-disbaled layui-disabled</c:if>"/>
					</div>
				</div>
			</c:if>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;邮件内容</label>
				<div class="layui-input-block layui-textarea-block">
					<textarea name="" id="content" <c:if test="${userEmailMsg.status!=2||userEmailMsg.type!=2 }"> readonly disabled</c:if> class=" ">${userEmailMsg.content }</textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;创建时间</label>
				<div class="layui-input-block">
					<input type="text" id="sendDate" value="<fmt:formatDate value="${userEmailMsg.createTime }" type="both"/>" class="layui-input layui-input-6 layui-radio-disbaled layui-disabled"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>发送人：</label>
				<div class="layui-input-block">
					<input name="" type="text" value="${userEmailMsg.loginName }" class="layui-input-6 layui-input layui-radio-disbaled layui-disabled"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label layui-form-label-w"><font color="red">*</font>接收人：</label>
				<div class="layui-input-block layui-textarea-block">
					<textarea name="" id="textareaemail" <c:if test="${userEmailMsg.status!=2||userEmailMsg.type!=2 }"> readonly disabled</c:if> class="layui-textarea">${userEmailMsg.email}</textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<c:if test="${userEmailMsg.type==2&&userEmailMsg.status==2}">
						<button onclick="update()" class="layui-btn layui-btn-danger" type="button" value="修改">修改</button>
					</c:if>
					<button onclick="history.go(-1);" class="layui-btn layui-btn-primary" type="button" value="返回">返回</button>
				</div>
			</div>



			<%--<table class="ad-tea-tab" width="100%">
				<tr>
					<td width="10%"><font color="red">*</font>邮件标题：</td>
					<td style="text-align: left">
						<input  type="text" id="title" value="${userEmailMsg.title }"/>
					</td>
				</tr>
				<tr>
					<td><font color="red">*</font>邮件类型：</td>
					<td style="text-align: left;">
						${userEmailMsg.type==1?'普通':'定时' }
					</td>
				</tr>
				<tr>
					<td><font color="red">*</font>是否发送：</td>
					<td style="text-align: left;">
						${userEmailMsg.status==1?'已发送':'未发送' }
					</td>
				</tr>
				<c:if test="${userEmailMsg.type==2}">
				<tr>
					<td><font color="red">*</font>定时发送时间：</td>
					<td style="text-align: left">
						<input type="text" class="" readonly="readonly" <c:if test="${userEmailMsg.status!=2&&userEmailMsg.type!=2 }">disabled="disabled"</c:if> value="<fmt:formatDate value="${userEmailMsg.sendTime}" type="both"/>" id="startTime" name=""/>

					</td>
				</tr>
				</c:if>
				<tr>
					<td><font color="red">*</font>邮件内容：</td>
					<td style="text-align: left">
						<textarea id="content"> ${userEmailMsg.content }</textarea>
					</td>
				</tr>
				<tr>
					<td><font color="red">*</font>创建时间：</td>
					<td style="text-align: left">
						<input type="text" disabled="disabled" value="<fmt:formatDate value="${userEmailMsg.createTime }" type="both"/>"/>
					</td>
				</tr>
				<tr>
					<td><font color="red">*</font>发送人：</td>
					<td style="text-align: left">
						<input type="text" value="${userEmailMsg.loginName }" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td><font color="red">*</font>接收人：</td>
					<td style="text-align: left">
						<textarea id="textareaemail" rows="6" cols="80" >${userEmailMsg.email}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: left">
						<p class="new-last-btn">
							 <c:if test="${userEmailMsg.type==2&&userEmailMsg.status==2}">
								 <input onclick="update()" class="layui-btn layui-btn-small" type="button" value="修 改" />
							 </c:if>
							 <input onclick="history.go(-1);" class="layui-btn layui-btn-small" type="button" value="返回" />
						 </p>
					</td>
				</tr>
			</table>--%>
		</form>
	</div>
</fieldset>
</body>
</html>
