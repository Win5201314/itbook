<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>操作日志详情</title>
    <script type="text/javascript">
    </script>

</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <legend>
            <span>操作日志详情</span>
        </legend>
    </legend>
   <div class="numb-box">
       <div class="layui-field-box">
           <input type="hidden" name="type" value="${type}" />
           <div class="layui-form-item">
               <label class="layui-form-label layui-form-label-w">id</label>
               <div class="layui-input-block">
                   <input class="layui-input layui-disabled layui-input-6" value="${sysLog.id}" type="text">
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label layui-form-label-w">操作人账号</label>
               <div class="layui-input-block">
                   <input class="layui-input layui-disabled layui-input-6" value="${sysLog.loginName}" type="text">
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label layui-form-label-w">操作人姓名</label>
               <div class="layui-input-block">
                   <input class="layui-input layui-disabled layui-input-6" value="${sysLog.userName}" type="text">
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label layui-form-label-w">类型</label>
               <div class="layui-input-block">
                   <input class="layui-input layui-disabled layui-input-6" value=" <c:if test="${sysLog.type=='add'}">添加</c:if>
                <c:if test="${sysLog.type=='update'}">更新</c:if>
                <c:if test="${sysLog.type=='del'}">删除</c:if>" type="text">
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label layui-form-label-w">添加时间</label>
               <div class="layui-input-block">
                   <input class="layui-input layui-disabled layui-input-6" value="<fmt:formatDate value="${sysLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />" type="text">
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label layui-form-label-w">操作描述</label>
               <div class="layui-input-block">
                   <input class="layui-input layui-disabled layui-input-6" value="${sysLog.operation}" type="text">
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label layui-form-label-w">相关信息</label>
               <div class="layui-input-block">
                   <input class="layui-input layui-disabled layui-input-6" type="text" value='${sysLog.content}'>
               </div>
           </div>
           <div class="layui-form-item">
               <div class="layui-input-block mt20">
                   <button type="reset" class="layui-btn layui-btn-danger" onclick="history.go(-1);">返回</button>
               </div>
           </div>
       </div>
   </div>
</fieldset>


</body>
</html>
