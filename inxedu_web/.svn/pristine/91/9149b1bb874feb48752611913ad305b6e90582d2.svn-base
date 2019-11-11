<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html> <!--<![endif]-->
<head>
    <title>邀请好友</title>
</head>
<body>
<article class="col-80 fl">
    <div class="u-r-cont">
        <section>
            <div class="">
                <section class="u-r-tit-all">
                    <h2 class="unFw">
                        <span class="f-fH current">
                            邀请好友
                        </span>
                    </h2>
                </section>
                <div class="u-r-all-box" id="p_tCont">
                    <section class="member-all-box">
                        <div class="member-yt">
                            <div class="mem-title pr">
                                <span class="f-fM pt-pic">
                                   邀请方法一
                                </span>
                                <div>
                                    <p class="fsize14 c-999 f-fM lain">复制您的专属链接：</p>
                                    <div class="u-o-right fr">
                                        <a href="javascript:void(0)" title="复制链接" class="cou-kscp mt5" onclick="copyContent()">复制链接</a>
                                    </div>
                                    <div class="clear"></div>
                                </div>
                            </div>
                            <div class="mem-body">
                               <article class="yqlj-box">
                                    <input class="fsize16 c-999 f-fM" id="copyContent" x-webkit-speech=""  readonly="" type="text" value="${ctx}?msg=ExternalLogin&invitationCode=${user.invitationCode}&time=<%=System.currentTimeMillis()%> 立即注册${websitemap.web.company}，分享邀请好友注册即可免费获得${invite.cashback}元红包。快来参与吧！">
                               </article>
                            </div>
                        </div>
                        <div class="member-jl mt50">
                            <div class="mem-title pr">
                                <span class="f-fM pt-pic">
                                   邀请方法二
                                </span>
                                <p class="fsize14 c-999 f-fM lain">分享到社区平台:</p>
                            </div>
                            <div class="mem-body">
                                <ul class="u-yqpt-list clearfix">
                                    <li>
                                        <a target="_blank" href="http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=${ctx}?msg=ExternalLogin&invitationCode=${user.invitationCode}&showcount=0&summary=${websitemap.web.company}&desc=${websitemap.web.description}&title=${websitemap.web.company}&pics=<%=contextPath%>${websitemap.web.logo}" title="QQ分享">
                                            <img src="${ctx}/static/inxweb/img/QQ.png">
                                        </a>
                                        <p class="tac hLh30 fsize14 c-666 f-fM mt10">QQ分享</p>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0)" onclick="wxShare(event,'${ctx}${shareUrl}')" title="微信分享">
                                            <img src="${ctx}/static/inxweb/img/weixin.png">
                                        </a>
                                        <p class="tac hLh30 fsize14 c-666 f-fM mt10">微信分享</p>
                                    </li>
                                    <li>
                                        <a target="_blank" href="http://service.weibo.com/share/share.php?url=${ctx}?msg=ExternalLogin&invitationCode=${user.invitationCode}&searchPic=true&pic=<%=contextPath%>${websitemap.web.logo}&type=icon&appkey=3687700982&language=zh_cn&title=%23${websitemap.web.company}%23，${websitemap.web.description}" title="新浪微博">
                                            <img src="${ctx}/static/inxweb/img/weibo.png">
                                        </a>
                                        <p class="tac hLh30 fsize14 c-666 f-fM mt10">新浪微博</p>
                                    </li>
                                </ul>

                        </div>
                        <div class="member-jl mt50">
                            <div class="mem-title pr">
                                <span class="f-fM pt-pic">
                                   邀请记录
                                </span>
                            </div>
                            <div class="mem-body">
                                <c:if test="${empty invitationRecordList}">
                                    <!-- /无数据提示 开始-->
                                    <section class="no-data-wrap">
                                        <em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">暂无邀请记录...</span>
                                    </section>
                                    <!-- /无数据提示 结束-->
                                </c:if>
                                <c:if test="${not empty invitationRecordList}">
                                    <table class="member-jl-tab" width="100%" cellspacing="0" cellpadding="0" border="0">
                                        <thead>
                                            <tr>
                                                <th align="center" class="mo-name">用户名</th>
                                                <th align="center">日期</th>
                                                <th align="center">奖励</th>
                                                <th align="center">来源</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${invitationRecordList}" var="invitationRecord">
                                            <tr>
                                                <td align="center" class="mo-name">${invitationRecord.displayName}</td>
                                                <td align="center"><fmt:formatDate value="${invitationRecord.addTime}" pattern="yyyy-MM-dd hh:mm" type="date"/></td>
                                                <td align="center">获得${invitationRecord.cashback}元</td>
                                                <td align="center"><c:if test="${invitationRecord.registerFrom=='register'}">网站</c:if>
                                                    <c:if test="${invitationRecord.registerFrom=='admin'}">后台批量开通</c:if>
                                                    <c:if test="${invitationRecord.registerFrom=='userCard'}">学员卡</c:if>
                                                    <c:if test="${invitationRecord.registerFrom=='OpenAppRegisterFrom'}">第三方登录</c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>

                                        </tbody>
                                    </table>
                                    <form action="${ctx}/uc/myInvite"name="searchForm" id="searchForm" method="post">
                                        <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
                                    </form>
                                    <!-- 公共分页 开始 -->
                                    <jsp:include page="/WEB-INF/view/common/front_page.jsp" />
                                    <!-- 公共分页 结束 -->
                                </c:if>

                                <div class="tab-ts">
                                    <span class="tab-ts-z fsize14 f-fM c-master">注：</span>
                                    <p class="hLh30 f-fM c-666 fsize14">1.奖励规则</p>
                                    <p class="hLh20 f-fM c-999 fsize12">每邀请一个好友成功注册奖励${invite.cashback}学币。</p>
                                    <p class="hLh30 f-fM c-666 fsize14">2.学币有什么用途</p>
                                    <p class="hLh20 f-fM c-999 fsize12">所获得的学币将直接发放到您的账户，方便站内购买商品。</p>
                                    <c:if test="${invite.withdrawCash=='ON'}">
                                        <p class="hLh30 f-fM c-666 fsize14">3.学币提现</p>
                                        <p class="hLh20 f-fM c-999 fsize12">所获得的学币可以提现到您的银行卡。</p>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </section>
    </div>
</article>
<div id="wx-bd-win" style="height: 280px;left: 945.5px;top: 629.5px;width: 240px;border: 1px solid #d8d8d8;font-size: 12px;padding:10px;position:absolute;background:none repeat scroll 0 0 #fff;z-index:100001;display: none;">
    <div class="hLh20">
        <span class="fsize14 c-666 f-fM">分享到微信朋友圈</span>
        <a href="javascript:void(0)" onclick="clesWin()" style="float: right;font-weight:bold;font-size: 14px;">×</a>
    </div>
    <div id="bd_weixin_popup" style="text-align: center;" class="mt10">

    </div>
    <div class="bd_weixin_popup_foot mt10">
        <p class="fsize12 c-666 f-fM">打开微信，点击底部的“发现”，</p>
        <p class="fsize12 c-666 f-fM">使用“扫一扫”即可将网页分享至朋友圈。</p>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/common/qrcode/jquery.qrcode.min.js"></script>
<script>
    function wxShare(ev,urls){
        var oEvent=ev||event;
        var oTop=oEvent.clientY;
        var clientHeight = document.documentElement.scrollTop;
        var _top = (clientHeight+oTop)/4;
        $("#wx-bd-win").css('top',_top);
        var str = toUtf8(urls);
        $("#bd_weixin_popup").html('');
        $("#bd_weixin_popup").qrcode({
            render:'canvas',
            text:str,
            type:'cn',
            width:200,
            height:200
        });
        $("#wx-bd-win").show();
    }


    /**
     * 二维码生成
     */
    function clesWin(){
        $("#wx-bd-win").hide();
    }


    function toUtf8(str) {
        var out, i, len, c;
        out = "";
        len = str.length;
        for(i = 0; i < len; i++) {
            c = str.charCodeAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                out += str.charAt(i);
            } else if (c > 0x07FF) {
                out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));
                out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
            } else {
                out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));
                out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
            }
        }
        return out;
    }
</script>
</body>
</html>