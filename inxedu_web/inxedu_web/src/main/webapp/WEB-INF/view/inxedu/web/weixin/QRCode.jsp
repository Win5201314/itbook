<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>微信支付</title>

    <script type="text/javascript">
        var link = '${wxPayUrl}';
        $(document).ready(function () {
            try {
                //默认字符串转二维码的方法，仅支持html5
                document.createElement('canvas').getContext('2d');
                $('#sourceqrcode').qrcode({render: "canvas", height: 360, width: 360, correctLevel: 0, text: link});
            } catch (e) {
                //如果不支持html5报错后通过table生成二维码
                $('#sourceqrcode').qrcode({render: "table", height: 360, width: 360, correctLevel: 0, text: link});
            }
            setInterval("reviewTrxOrder()", 5000);
            canvasToImage();
        });
        //检查订单是否支付成功
        function reviewTrxOrder() {
            var requestId = '${requestId}';
            var type = '${type}';
            $.ajax({
                url: '/order/review',
                data: {"requestId": requestId, "type": type},
                type: 'post',
                dataType: 'json',
                success: function (result) {
                    if (result.message == "true") {
                        window.location.href = '/front/success?msg=订单支付成功&type=' + type + "&orderNo=" + requestId;
                    }
                }
            });
        }
        //canvas转图片
        function canvasToImage() {
            var $imgs = document.getElementById('qrcode');
            var w = $("#sourceqrcode").width();
            var h = $("#sourceqrcode").height();
            $imgs.appendChild(Canvas2Image.convertToImage($("canvas")[0], w, h, "png"));
        }


        /**
         * 微信内置 WeixinJSBridge 支付
         */
       function onBridgeReady(){
            WeixinJSBridge.invoke(
                    'getBrandWCPayRequest', {
                        "appId":"${appId}",     //公众号名称，由商户传入
                    "timeStamp":"${timeStamp}",         //时间戳，自1970年以来的秒数
                    "nonceStr":"${nonceStr}", //随机串
                    "package":"${info_package}",
                    "signType":"MD5",         //微信签名方式：
                    "paySign":"${paySign}" //微信签名
                },
                function(res){
                    if(res.err_msg == "get_brand_wcpay_request：ok" ) {

                    }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                }
            );
        }

        if (typeof WeixinJSBridge == "undefined") {
            if (document.addEventListener) {
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
            } else if (document.attachEvent) {
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
            }
        } else {
           if(!isEmpty("${appId}")){
               onBridgeReady();
           }
        }
    </script>
    <style>
        .QRcode-lt-ie9 {
            width: 160px;
            height: 160px;
            margin: 0 auto 10px;
        }

        .QRcode-lt-ie9 table {
            border-collapse: collapse;
            border-spacing: 0;
            empty-cells: show;
            width: 100%;
            border: 0;
        }

        .QRcode-lt-ie9 table td {
            overflow: hidden;
            line-height: 2px;
            font-size: 0;
        }
    </style>
</head>
<body class="scrol">
<div class="mt30" style="margin-top:90px;margin-bottom:90px">
    <div class="pb20">
        <section class="mt20 mr20 mb20 ml20">
            <div class=" pr">
                <ol>
                    <li class="tac">
                        <div id="sourceqrcode" class="QRcode-lt-ie9" style="display: none;"></div>
                        <div id="qrcode" class="QRcode-lt-ie9"></div>

                    </li>

                    <li class="mt30" style="text-align: center">
                         <span class="c-333 fsize14">
                             <c:if test="${weixinbrowser=='true'}"><span class="f-fM fsize14 ">↑↑↑请长按识别二维码完成支付</span></c:if>
                              <c:if test="${weixinbrowser!='true'}"><span class="f-fM fsize14 ">请使用微信扫描二维码以完成支付</span></c:if>

                         </span>
                    </li>
                    <li class="mt30" style="text-align: center">
                        <span class="c-333 fsize14">您现在可以：<a class="c-333 mr10" title="返回修改支付方式" href="/front/repay/${orderId }"><u>返回修改支付方式</u></a> |
                            <a class="c-4e ml10 mr10" title="进入学习中心" href="${ctx }/uc/index"><u>进入学习中心</u></a>
                            | <a class="c-4e ml10" title="返回首页" href="${ctx }/"><u>返回首页</u></a></span>
                    </li>
                </ol>
            </div>

        </section>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/common/qrcode/jquery.qrcode.min.js"></script>
<script type="text/javascript" src="${ctx}/static/common/canvas2image.js"></script>
</body>
</html>
