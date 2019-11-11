<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>用户账户信息</title>
    <script type="text/javascript">
        var isTrue = false;
        var reAmount = 0;
        function validAmount(amount) {//验证
            var reg = /^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
            if (!reg.test(amount)) {
                isTrue = false;
                layer.msg("请输入正确的金额", {icon: 5, shift: 6});
                return;
            }
            isTrue = true;
            reAmount = amount;
        }
        function rechargeForm(userId, optFlag) {
            if (isTrue) {
                var optInfo = "";
                var optSure = "";
                if (optFlag == "credit") {//充值 信息赋值
                    optInfo = "充值金额必须大于0";
                    optSure = "确定充值吗,充值后不得修改?";
                } else {
                    optInfo = "扣款金额必须大于0";
                    optSure = "确认扣款吗,操作后不得修改";
                }
                if (reAmount <= 0) {
                    layer.msg(optInfo, {icon: 5, shift: 6});
                    return;
                }
                if (confirm(optSure)) {
                    $.ajax({
                        url: "${ctx}/admin/account/recharge",
                        data: {"userId": userId, "flag": optFlag, "balance": reAmount},
                        dataType: "json",
                        type: "post",
                        success: function (result) {
                            if (result.success) {
                                layer.msg(result.message, {icon: 1, shift: 6});
                                window.location.href = "${ctx}/admin/account/list";
                            } else {
                                layer.msg(result.message, {icon: 5, shift: 6});
                            }
                        }
                    });
                }
            } else {
                layer.msg("请输入正确的金额", {icon: 5, shift: 6});
            }
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>用户账户管理</span> &gt; <span>账户充值</span>
    </legend>
   <form action="" class="layui-form">
       <div class="layui-field-box">
           <div class="layui-form-item">
               <label class="layui-form-label layui-form-label-w">学员ID</label>
               <div class="layui-input-block">
                   <div class="xy-warp fsize14 c-666 f-fM">
                       ${userAccountDTO.userId }
                   </div>
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label layui-form-label-w">账号</label>
               <div class="layui-input-block">
                   <div class="xy-warp fsize14 c-666 f-fM">
                       ${userAccountDTO.userName}
                   </div>
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label layui-form-label-w">邮箱</label>
               <div class="layui-input-block">
                   <div class="xy-warp fsize14 c-666 f-fM">
                       ${userAccountDTO.email}
                   </div>
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label layui-form-label-w">创建时间</label>
               <div class="layui-input-block">
                   <div class="xy-warp fsize14 c-666 f-fM">
                       <fmt:formatDate value="${userAccountDTO.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                   </div>
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label layui-form-label-w">更新时间</label>
               <div class="layui-input-block">
                   <div class="xy-warp fsize14 c-666 f-fM">
                       <fmt:formatDate value="${userAccountDTO.lastUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                   </div>
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label layui-form-label-w">账户余额</label>
               <div class="layui-input-block">
                   <div class="xy-warp fsize14 c-666 f-fM">
                       ${userAccountDTO.balance}
                   </div>
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label layui-form-label-w">冻结金额</label>
               <div class="layui-input-block">
                   <div class="xy-warp fsize14 c-666 f-fM">
                       ${userAccountDTO.forzenAmount}
                   </div>
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label layui-form-label-w">银行入账</label>
               <div class="layui-input-block">
                   <div class="xy-warp fsize14 c-666 f-fM">
                       ${userAccountDTO.cashAmount}
                   </div>
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label layui-form-label-w">返现金额</label>
               <div class="layui-input-block">
                   <div class="xy-warp fsize14 c-666 f-fM">
                       ${userAccountDTO.backAmount}
                   </div>
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label layui-form-label-w">充值卡入账</label>
               <div class="layui-input-block">
                   <div class="xy-warp fsize14 c-666 f-fM">
                       ${userAccountDTO.vmAmount}
                   </div>
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label layui-form-label-w">账户状态</label>
               <div class="layui-input-block">
                   <div class="xy-warp fsize14 c-666 f-fM">
                       <c:if test="${userAccountDTO.accountStatus=='FROZEN'}">
                           冻结
                       </c:if>
                       <c:if test="${userAccountDTO.accountStatus=='ACTIVE'}">
                           正常
                       </c:if>
                   </div>
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label layui-form-label-w">${flag=='credit'?'充值金额':'扣款金额'}</label>
               <div class="layui-input-block">
                   <input type="text" value="0" name="rechargeAmount" onblur="validAmount(this.value)" class="layui-input layui-input-6"/>
               </div>
           </div>
           <div class="layui-form-item">
               <div class="layui-input-block mt20">
                   <c:if test="${flag=='credit'}">
                       <a class="layui-btn layui-btn-danger" title="充值" href="javascript:rechargeForm(${userAccountDTO.userId },'credit')">充值</a>
                   </c:if>
                   <c:if test="${flag=='debit'}">
                       <a class="layui-btn layui-btn-danger" title="扣款" href="javascript:rechargeForm(${userAccountDTO.userId },'debit')">扣款</a>
                   </c:if>
                   <a class="layui-btn layui-btn-primary" title="返 回" href="javascript:history.go(-1);">返 回</a>
               </div>
           </div>
       </div>
   </form>
</fieldset>
</body>
</html>
