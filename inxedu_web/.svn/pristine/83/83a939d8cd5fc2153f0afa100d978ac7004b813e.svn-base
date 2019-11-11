<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <%--百度编译器--%>
    <script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript">
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });
        });
        $(function () {
            initKindEditor_addblog('textareamobile', '100%', 200, 'mobile', 'true');
            if (${userMobileMsg.status!=2}||${userMobileMsg.type!=2}) {
                EditorObject.readonly(true);
            }

        });
        function updateSubmit() {
            var sendTime = $("#sendDate").val();
            var nowTime = new Date().getTime();
            var sendDate = Date.parse(sendTime.replace(/-/gi, "/"));

            if ($("#mobileContent").val() == "") {
                layer.msg("短信内容不能为空！", {icon: 5, shift: 6});
                return;
            } else if (sendTime == "" || sendTime == null) {
                layer.msg("发送时间不能为空", {icon: 5, shift: 6});
                return;
            } else if (sendDate < nowTime) {
                layer.msg("定时发送时间必须大于当前系统时间", {icon: 5, shift: 6});
                return;
            }
            if (!UE.getEditor('textareamobile').hasContents()) {// hasContents()  检查有内容返回true，否则返回false
                layer.msg("短信接收人不能为空", {icon: 5, shift: 6});
                return false;
            }

            var id = $("#id").val();
            var content = $("#mobileContent").val();
            $.ajax({
                url: "${ctx}/admin/mobile/updateUserMsg",
                data: {
                    "msgId": id,
                    "content": content,
                    "sendTime": $("#sendDate").val(),
                    "mobile": UE.getEditor('textareamobile').getPlainTxt()
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    layer.msg(result.message, {icon: 1, shift: 6});
                }
            })
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <a href="${ctx}/admin/mobile/sendMsglist">
            <span>短信管理</span>
        </a>
        &gt;
        <span>详情</span>
    </legend>
    <div class="layui-field-box">
        <form action="${ctx}/admin/user/updatePwd" method="post" id="addPaperForm" class="layui-form">
            <input name="userMobileMsg.id" id="id" type="hidden" value="${userMobileMsg.id}"/>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;短信内容</label>
                <div class="layui-input-block layui-textarea-block">
                    <textarea name="userMobileMsg.content" id="mobileContent" data-rule="required;" class="layui-textarea <c:if test="${userMobileMsg.status!=2||userMobileMsg.type!=2 }"> layui-radio-disbaled layui-disabled</c:if> ">${userMobileMsg.content }</textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;创建时间</label>
                <div class="layui-input-block">
                    <input type="text"  value="<fmt:formatDate value="${userMobileMsg.createTime }" type="both" />" class="layui-input layui-input-6 layui-radio-disbaled layui-disabled"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;发送时间</label>
                <div class="layui-input-block">
                    <input type="text" id="sendDate" value="<fmt:formatDate value="${userMobileMsg.sendTime }" type="both"/>" onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})" class="layui-input layui-input-6 <c:if test="${userMobileMsg.status!=2||userMobileMsg.type!=2 }"> layui-radio-disbaled layui-disabled</c:if> "/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;发送状态</label>
                <div class="layui-input-block">
                    <input type="text" value="<c:if test="${userMobileMsg.status==1 }">已发送</c:if><c:if test="${userMobileMsg.status==2 }">未发送</c:if>" class="layui-input layui-input-6  layui-radio-disbaled layui-disabled"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;发送人</label>
                <div class="layui-input-block">
                    <input type="text" value="${userMobileMsg.loginName }" class="layui-input layui-input-6  layui-radio-disbaled layui-disabled"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;接收人</label>
                <div class="layui-input-block layui-textarea-block">
                    <textarea name="userMobileMsg.content" id="textareamobile" data-rule="required;" <c:if test="${userMobileMsg.status!=2||userMobileMsg.type!=2 }"> readonly disabled</c:if> class="">${userMobileMsg.mobile}</textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;发送方式</label>
                <div class="layui-input-block">
                    <input type="text" value="<c:if test="${userMobileMsg.status==1 }">正常发送</c:if><c:if test="${userMobileMsg.status==2 }">定时发送</c:if>" class="layui-input layui-input-6  layui-radio-disbaled layui-disabled"/>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <c:if test="${userMobileMsg.status==2&&userMobileMsg.type==2 }">
                        <button onclick="updateSubmit()" class="layui-btn layui-btn-danger" type="button" value="修改">修改</button>
                        <button onclick="history.go(-1);" class="layui-btn layui-btn-primary" type="button" value="返回">返回</button>
                    </c:if>
                    <c:if test="${userMobileMsg.status!=2||userMobileMsg.type!=2 }">
                        <button onclick="history.go(-1);" class="layui-btn layui-btn-danger" type="button" value="返回">返回</button>
                    </c:if>
                </div>
            </div>
        </form>
    </div>
</fieldset>
</body>
</html>
