/**
 * Created by Administrator on 2017/1/5.
 */

/*充值卡激活*/
function recharge() {
    /*充值卡卡号*/
    var couponCode = $("#couponCode").val();
    /*充值卡密码*/
    var couponPwd = $("#couponPwd").val();
    $.ajax({
        url:baselocation+"/uc/ajax/recharge",
        post : "post",
        data:{"couponCode":couponCode,"couponPwd":couponPwd},
        success : function(result) {
            var msg="";
            if(result.entity=='passwordError'){
                msg="卡号或密码错误，请确认，谢谢！";
            }else if(result.entity=='dontActivate'){
                msg="该卡未被激活，请联系客服进行处理！谢谢";
            }else if(result.entity=='alreadyUse'){
                msg="该卡已被使用，不能再进行激活，请确认！谢谢";
            }else if(result.entity=='overDue'){
                msg="该卡已过期，不能进行激活，请确认！谢谢";
            }else if(result.entity=='close'){
                msg="该卡已作废，不能进行激活，请确认！谢谢";
            }else if (result.entity=='typeError'){
                msg="卡号或密码错误，请确认，谢谢！";
            }else if(result.entity=='dateError'){
                msg="该卡不在有效期内，请确认！谢谢";
            }else{
                msg="";
                $('.dialog-shadow').remove();
                dialog('激活提示',"您的充值卡已激活成功!",2,'/uc/myAccount');
            }
            if(msg!=""){
                $('.dialog-shadow').remove();
                dialog('激活提示',msg,1);
            }
        }
    })
}
/*全部提现*/
function getAllMoney(allMoney) {
    $("#money").val(allMoney)
}
/*提现*/
function withdrawMoney() {
    /*提取金额*/
    var money = $("#money").val();
    /*银行卡号*/
    var card = $("#card").val();
    /*转账银行*/
    var bank = $("#bank").val();
    if (money==""||money==null){
        msgshow("请填写提现金额","false","1000");
        return;
    }
    if(isNaN(money)){
        msgshow("金额只能填数字","false","1000");
        return;
    }
    if(money<=0){
        msgshow("提现金额必须大于0","false","1000");
        return;
    }
    if (card==""||card==null){
        msgshow("请填写银行卡号","false","1000");
        return;
    }
    if (bank==""||bank==null){
        msgshow("请选择银行","false","1000");
        return;
    }
    $.ajax({
        url:baselocation+"/uc/ajax/drawMoney",
        post:"post",
        data:{"money":money,"card":card,"bank":bank},
        success : function(result) {
            if (result.success==true){
                dialog('申请提现',"已经申请提现!",2,'/uc/myAccount');

            }else {
                dialog('提现提示',result.message,1);
            }
        }
    })
}