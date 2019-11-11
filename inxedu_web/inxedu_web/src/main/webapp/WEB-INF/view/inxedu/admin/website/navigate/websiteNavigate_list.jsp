<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>导航管理</title>
	<style type="text/css">
		.acd-ico-btn{width: 18px;height: 18px;vertical-align: middle;margin: 0 0 0 5px;display: inline-block;}
		.a-i-b-up{background: url("/static/admin/assets/icons/up.png") no-repeat center center;}
		.a-i-b-up:hover{background: url("/static/admin/assets/icons/up-xz.png") no-repeat center center;}
		.a-i-b-dwon{background: url("/static/admin/assets/icons/down.png") no-repeat center center;}
		.a-i-b-dwon:hover{background: url("/static/admin/assets/icons/down-xz.png") no-repeat center center;}
	</style>
<script type="text/javascript">
	var form;
	layui.use(['form', 'laydate'], function () {
		form = layui.form();
		var laydate = layui.laydate;
		form.on('select(filter)', function (data) {
		});
		//各种基于事件的操作，下面会有进一步介绍
	});
	function delNavigate(id){
		if(confirm("真的要删除吗？")==true){
			$.ajax({
				url:"${cxt}/admin/website/delNavigate/"+id,
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.message=="true"){
						msgshow("删除成功");
						window.location.reload();
					}
				}
			});
		}
	} 
	//冻结或解冻导航
	function freezeNavigate(id,status){
		$.ajax({
			url:"${cxt}/admin/website/freezeNavigate",
			type:"post",
			data:{"websiteNavigate.id":id,"websiteNavigate.status":status},
			dataType:"json",
			success:function(result){
				if(result.message=="true"){
					if(status==0){
						msgshow("解冻成功");
					}else{
						msgshow("冻结成功");
					}
					window.location.reload();
				}
			}
		});
	}
	/*新建导航的冻结解冻*/
	function lockNav(obj) {
		var lock = $(obj).html();
		var navigateStatus = $(obj).parent().parent().find(".navigateStatus input").val();
		if (lock=="冻结"&&navigateStatus=="0"){
			$(obj).html("解冻");
			$(obj).parent().parent().find(".navigateStatus").html("<input type='hidden' value='1'>冻结")
		}
		if (lock=="解冻" &&navigateStatus=="1"){
			$(obj).html("冻结");
			$(obj).parent().parent().find(".navigateStatus input").val(0);
			$(obj).parent().parent().find(".navigateStatus").html("<input type='hidden' value='0'>正常")

		}
	}
	/*保存导航*/
	function saveNav(obj,navId) {
		var name = $(obj).parent().parent().find(".navigateName input").val();
		var url = $(obj).parent().parent().find(".navigateUrl input").val();
		var newPage = $(obj).parent().parent().find(".navigateNewPage select").val();
		var orderNum = $(obj).parent().parent().find(".navigateOrderNum input").val();
		var status = $(obj).parent().parent().find(".navigateStatus input").val();
		if(name==null||name==""){
			msgshow("名称不能为空!","false");
			return false;
		}
		if(url==null||url==""){
			msgshow("跳转地址不能为空!","false");
			return false;
		}
		/*if(url.charAt(0)!="/"){
			msgshow("请按正确格式填写链接!",'false');
			return false;
		}*/

		if (navId==null||navId==""){
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"/admin/website/addNavigate",
				data:{"websiteNavigate.name":name,"websiteNavigate.type":"${type}","websiteNavigate.url":url,"websiteNavigate.newPage":newPage,"websiteNavigate.orderNum":orderNum,"websiteNavigate.status":status},
				success:function(result){
					if (result.success){
						$(obj).parent().parent().find(".navigateName").replaceWith("<td class='navigateName'>"+name+"</td>");
						$(obj).parent().parent().find(".navigateUrl").replaceWith("<td class='navigateUrl'>"+url+"</td>");
						if (newPage=="1"){
							$(obj).parent().parent().find(".navigateNewPage").replaceWith("<td class='navigateNewPage'>否</td>");
						}else {
							$(obj).parent().parent().find(".navigateNewPage").replaceWith("<td class='navigateNewPage'>是</td>");
						}
						$(obj).parent().parent().find(".navigateOrderNum").replaceWith("<td class='navigateOrderNum'><input type='hidden' value='"+result.entity.orderNum+"'>" +
								"<a href='javascript:void(0)' onclick='moveNavUp(this,"+result.entity.id+")' title='向上一级' class='acd-ico-btn a-i-b-up'></a>"+
						"<a href='javascript:void(0)'onclick='moveNavDown(this)' title='向下一级' class='acd-ico-btn a-i-b-dwon'></a></td>");
						$(obj).parent().parent().find(".save").attr("onclick","update("+result.entity.id+",this)");
						$(obj).parent().parent().find(".save").html("修改");
						$(obj).parent().parent().find(".delete").attr("onclick","delNavigate("+result.entity.id+")");
						if (result.entity.status=="0"){
							$(obj).parent().parent().find(".lock").attr("onclick","freezeNavigate("+result.entity.id+",1)");
						}else {
							$(obj).parent().parent().find(".lock").attr("onclick","freezeNavigate("+result.entity.id+",0)");
						}

					}else {
						msgshow(result.message)
					}
				}
			});
		}else {
			var name = $(obj).parent().parent().find(".navigateName input").val();
			var url = $(obj).parent().parent().find(".navigateUrl input").val();
			var newPage = $(obj).parent().parent().find(".navigateNewPage select").val();
			var orderNum = $(obj).parent().parent().find(".navigateOrderNum input").val();
			var status = $(obj).parent().parent().find(".navigateStatus input").val();
			var type = "${type}";
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"/admin/website/updateNavigate",
				data:{"websiteNavigate.id":navId,"websiteNavigate.name":name,"websiteNavigate.url":url,"websiteNavigate.newPage":newPage,"websiteNavigate.type":type,"websiteNavigate.orderNum":orderNum,"websiteNavigate.status":status},
				success:function(result){
					if (result.success){
						$(obj).parent().parent().find(".navigateName").replaceWith("<td class='navigateName'>"+name+"</td>");
						$(obj).parent().parent().find(".navigateUrl").replaceWith("<td class='navigateUrl'>"+url+"</td>");
						if (newPage=="1"){
							$(obj).parent().parent().find(".navigateNewPage").replaceWith("<td class='navigateNewPage'>否</td>");
						}else {
							$(obj).parent().parent().find(".navigateNewPage").replaceWith("<td class='navigateNewPage'>是</td>");
						}
						$(obj).parent().parent().find(".navigateOrderNum").replaceWith("<td class='navigateOrderNum'><input type='hidden' value='"+orderNum+"'><a href='javascript:void(0)' onclick='moveNavUp(this,"+result.entity.id+")' title='向上一级' class='acd-ico-btn a-i-b-up'></a>"+
						"<a href='javascript:void(0)'onclick='moveNavDown(this)' title='向下一级' class='acd-ico-btn a-i-b-dwon'></a></td>");
						$(obj).parent().parent().find(".save").attr("onclick","update("+result.entity.id+",this)");
						$(obj).parent().parent().find(".save").html("修改");
						$(obj).parent().parent().find(".delete").attr("onclick","delNavigate("+result.entity.id+")");
						if (result.entity.status=="0"){
							$(obj).parent().parent().find(".lock").attr("onclick","freezeNavigate("+result.entity.id+",1)");
						}else {
							$(obj).parent().parent().find(".lock").attr("onclick","freezeNavigate("+result.entity.id+",0)");
						}
					}else {
						msgshow(result.message)
					}
				}
			});
		}

	}
	/*点击修改后将标签换成input修改内容*/
	function update(navId,obj) {
		$("input[name='websiteNavigate.name']").val($(obj).parent().parent().find(".navigateName").text());
		$("input[name='websiteNavigate.id']").val(navId);
		$("input[name='websiteNavigate.url']").val($(obj).parent().parent().find(".navigateUrl").text());
		$("input[name='websiteNavigate.orderNum']").val($(obj).parent().parent().find(".navigateOrderNum input").val());
		$("#newPage").val($(obj).parent().parent().find(".navigateNewPage").find("input").val());
		layer.closeAll(); //疯狂模式，关闭所有层
		form.render('select'); //刷新select选择框渲染
		layer.open({
			title:'修改配置',
			type: 1,
			shade: false,
			area: '550px',
			maxmin: false,
			content: $("#updateNaveInfo")
		});
	}
	/*上移操作*/
	function moveNavUp(obj,navId) {
		var orderNum = $(obj).parent().find("input").val();
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"/admin/website/moveNavUp",
			data:{"navId":navId,"orderNum":orderNum},
			success:function(result){
				$(obj).parent().find("input").val(result.message);
				$(obj).parent().parent().prev().find(".navigateOrderNum input").val(orderNum);
				$(obj).parent().parent().insertBefore($(obj).parent().parent().prev());
			}
		});
	}
	/*下移操作*/
	function moveNavDown(obj,navId) {
		var orderNum = $(obj).parent().find("input").val();
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"/admin/website/moveNavDown",
			data:{"navId":navId,"orderNum":orderNum},
			success:function(result){
				$(obj).parent().find("input").val(result.message);
				$(obj).parent().parent().next().find(".navigateOrderNum input").val(orderNum);
				$(obj).parent().parent().next().insertBefore($(obj).parent().parent());
			}
		});
	}
    /*新建导航*/
    function addNavigates() {
		$("input[name='websiteNavigate.name']").val("");
		$("input[name='websiteNavigate.id']").val(0);
		$("input[name='websiteNavigate.url']").val("");
		$("input[name='websiteNavigate.newPage']").val("");
		$("input[name='websiteNavigate.orderNum']").val(0);

		layer.open({
			title:'新建导航',
			type: 1,
			shade: false,
			area: '550px',
			maxmin: false,
			content: $("#updateNaveInfo")
		});
    }
</script>
</head>
<body>
<fieldset class="layui-elem-field">
	<legend> 导航管理 </legend>
	<!-- /tab1 begin-->
	<div class="numb-box">
		<div class="commonWrap">
			<form class="layui-form"  action="${ctx}/admin/website/navigates" name="searchForm" id="searchForm" method="post">
				<table class="layui-table">
					<%--<caption>
						<div class="mt10 clearfix">
							<p class="fl c_666">
								导航类型：
								<select name="websiteNavigate.type" id="type" class="sele-box">
									<option value="">请选择</option>
									<option value="INDEX" <c:if test="${websiteNavigate.type=='INDEX'}">selected="selected"</c:if>>首页</option>
									<option value="FRIENDLINK" <c:if test="${websiteNavigate.type=='FRINEDLINK'}">selected="selected"</c:if>>尾部友链</option>
									<option value="TAB" <c:if test="${websiteNavigate.type=='TAB'}">selected="selected"</c:if>>尾部标签</option>
								</select>
								<input type="submit" class="layui-btn layui-btn-small" value="查询" />
								<input type="button" class="layui-btn layui-btn-small" value="清空" onclick="$('#type').val('');" />
								<input type="button" class="layui-btn layui-btn-small" value="添加" onclick="window.location.href='${cxt}/admin/website/doAddNavigates'" />
							</p>
						</div>
					</caption>--%>
					<thead>
						<tr>
							<td  align="center">
								<span>名称</span>
							</td>
							<td  align="center">
								<span >跳转地址</span>
							</td>
							<td  align="center">
								<span>在新页面打开</span>
							</td>
							<td  align="center">
								<span>排序</span>
							</td>
							<td  align="center">
								<span>状态</span>
							</td>
							<td align="center">
								<span>操作</span>
							</td>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
						<c:if test="${websiteNavigates.size()>0}">
							<c:forEach items="${websiteNavigates}" var="navigate" varStatus="index">
								<tr <c:if test="${index.count%2==1 }">class="odd"</c:if>>
									<td class="navigateName">${navigate.name }</td>
									<td class="navigateUrl">${navigate.url }</td>
									<td class="navigateNewPage">
										<c:if test="${navigate.newPage==0 }"><input type="hidden" value="0">是</c:if>
										<c:if test="${navigate.newPage==1 }"><input type="hidden" value="1">否</c:if>
									</td>
									<td class="navigateOrderNum">
										<input type="hidden" value="${navigate.orderNum}"/>
										<a href="javascript:void(0)" onclick="moveNavUp(this,${navigate.id})" title="向上一级" class="acd-ico-btn a-i-b-up"></a>
										<a href="javascript:void(0)"onclick="moveNavDown(this,${navigate.id})" title="向下一级" class="acd-ico-btn a-i-b-dwon"></a>
									</td>
									<td class="navigateStatus">
										<c:if test="${navigate.status==0 }"><input type="hidden"value="0">正常</c:if>
										<c:if test="${navigate.status==1 }"><input type="hidden"value="1">冻结</c:if>
									</td>
									<td class="c_666 czBtn" align="center">
										<button type="button" class="layui-btn layui-btn-small   save"
											onclick="update(${navigate.id},this)">修改</button>

										<button type="button" class="layui-btn layui-btn-small   delete" onclick="delNavigate(${navigate.id})">删除</button>

										<c:if test="${navigate.status==0}">
											<button type="button" class="layui-btn layui-btn-small   lock" onclick="freezeNavigate(${navigate.id},1)">冻结</button>
										</c:if>
										<c:if test="${navigate.status==1}">
											<button type="button" class="layui-btn layui-btn-small   lock" onclick="freezeNavigate(${navigate.id},0)">解冻</button>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${websiteNavigates.size()==0||websiteNavigates==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
										<span>还没有导航！</span>
									</div>
								</td>
							</tr>
						</c:if>
                    </tbody>
				</table>
				<div class="layui-form-item">
					<div class="tac mt20">
						<button onclick="addNavigates()" class="layui-btn layui-btn-small layui-btn-danger" type="button">
							新建导航
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div id="updateNaveInfo" style="display: none">
		<div class="numb-box">
			<form class="layui-form" action="${ctx}/admin/website/updateNav">
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">名称</label>
					<div class="layui-input-block">
						<input type="text" required="required"  name="websiteNavigate.name"  value="" class="layui-input layui-input-6"  />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">跳转地址</label>
					<div class="layui-input-block">
						<input type="text" required="required"  name="websiteNavigate.url" value=""  class="layui-input layui-input-6" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label layui-form-label-w">在新页面打开</label>
					<div  class="layui-input-block layui-select-inline" style="width: 48.5%">
						<select id="newPage" class="layui-input" name="websiteNavigate.newPage" >
							<option value="1">否</option>
							<option value="0">是</option>
						</select>
					</div>
				</div>
				<input type="hidden" name="websiteNavigate.orderNum" value=""/>
				<input type="hidden" name="websiteNavigate.type" value="INDEX"/>
				<input type="hidden" name="websiteNavigate.id" value=""/>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button type="submit" class="layui-btn layui-btn-small layui-btn-danger" >保 存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</fieldset>
</body>
</html>