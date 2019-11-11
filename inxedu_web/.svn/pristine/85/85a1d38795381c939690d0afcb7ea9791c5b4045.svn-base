<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>Ehcache修改</title>
<script type="text/javascript">
function addEhcache(){
	var ehcacheKey = $("#ehcacheKey").val();
	var ehcacheDesc=$("#ehcacheDesc").val();
	var id='${websiteEhcache.id }';
	if(ehcacheKey.trim()==""){
        layer.msg("Ehcache-key不能为空", {icon: 5, shift: 6});
		return;
	}
	if(ehcacheDesc.trim()==""){
        layer.msg("请输入Ehcache-key描述", {icon: 5, shift: 6});
		return;
	}
	 var judge=confirm("确定修改？");
	 if(judge==true){
		 $.ajax({
				url:"${ctx}/admin/ehcache/updateWebsiteEhcache",
				data:{"websiteEhcache.ehcacheKey":ehcacheKey,"websiteEhcache.ehcacheDesc":ehcacheDesc,"websiteEhcache.id":id},
				dataType:"json",
				type:"post",
				cache:true,
				async:false,
				success:function(result){
					if(!result.success){
                        layer.msg(result.message, {icon: 5, shift: 6});
					}else{
						window.location.href="${ctx}/admin/ehcache/queryWebsiteEhcacheList"
					}
				}
			});
	 }else{
		 return false;
	 }
}
</script>
</head>
<body >
<div class="numb-box"><fieldset class="layui-elem-field">
	<legend>
		<span>缓存管理</span>
		&gt;
		<span>修改</span>
	</legend>
	<div class="">
		<form action="${ctx}/admin/cou/addCourse" method="post" id="saveCourseForm" data-validator-option="{stopOnError:false, timely:false}">
			<p>
				<label for="sf"><font color="red">*</font>&nbsp;Key:</label>
				<input type="text" id="ehcacheKey" class="{required:true} lf" data-rule="required;" value="${websiteEhcache.ehcacheKey }"/>
				<span class="field_desc"></span>
			</p>
			<p>
				<label for="sf"><font color="red">*</font>&nbsp;描述:</label>
				<input type="text" name="" id="ehcacheDesc" class="{required:true} lf" data-rule="required;" value="${websiteEhcache.ehcacheDesc }"/>
				<span class="field_desc"></span>
			</p>
			<p class="new-last-btn">
				<input type="button" value="提 交" class="layui-btn layui-btn-small" onclick="addEhcache()" />
				<input type="button" value="返 回" class="layui-btn layui-btn-small" onclick="history.go(-1);" />
			</p>
		</form>
	</div>
</fieldset></div>
</body>
</html>
