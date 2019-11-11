<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>

<c:if test="${index==0}">
    <div class='d-tips-1'>
        <em class='pa d-t-icon-3'></em>
        <p class='fsize14 c-666'>${msg}</p>
        <div class='tac mt30'>
            <a id="qujiao" href='javascript:void(0)' title=''class='order-submit'>确定</a>
        </div>
    </div>
</c:if>
<c:if test="${index==1}">
    <div class='d-tips-2'>
        <em class='pa d-t-icon-2'></em>
        <p class='fsize14 c-666'>${msg}</p>
        <div class='tac mt30'>
            <a id="qujiao" href='javascript:void(0)'  title='' class='order-submit'>确定</a>
        </div>
    </div>
</c:if>
<c:if test="${index==2}">
    <div class='d-tips-3 lrWrap'>
        <em class='pa d-t-icon-3'></em>
        <p class='fsize14 c-666'>${msg}</p>
        <div class='tac mt30'>
            <a href='${url}' title='' class='sure-submit mr10'>确定</a>
            <a id="qujiao" href='javascript:void(0)' title='' class='cancel-btn ml10'>取消</a>
        </div>
    </div>
</c:if>
<c:if test="${index==3}">
    <div class='d-tips-4'>
    <div class='c-payerror-desc'>
        <p>你选择使用<span>${msg}</span>进行在线支付，<br>请在新打开的支付页面完成支付。<br>祝你学习愉快！</p>
    </div>
    <div class='tac pay-error-btn'>
        <a href='javascript:disOrderSuccess()' title='' class='mr40'>
            <em class='c-pay-btn1'>&nbsp;</em><tt>重新支付</tt>
        </a>
        <a href='javascript:void(0)' onclick='chcckCoursePay("${url}")' title='' class='ml40'>
            <em class='c-pay-btn2'>&nbsp;</em><tt>已完成支付</tt>
        </a>
    </div>
        <%--<p class='tar mt20 c-666'>如有疑问请询问客服：400-6587-777</p>--%>
    </div>
</c:if>
<c:if test="${index==4}">
    <div class='d-tips-5'>
        <div class='c-payerror-desc'>
            <p class="tac c-master">恭喜，你已成功激活。现在你可以：</p>
        </div>
        <div class='tac pay-error-btn pay-cgcx-btn'>
            <a href='' title='登录网页' class='mr20'>
                <tt>登录网页</tt>
            </a>
            <a href='' title='查看课程' class='ml20'>
                <tt>查看课程</tt>
            </a>
        </div>
        <p class='tar mt20 c-666'>如有疑问请询问客服：400-6587-777</p>
    </div>
</c:if>
<c:if test="${index==5}">
    <div class='d-tips-6'>${msg}</div>
</c:if>
<%--激活课程卡--%>
<c:if test="${index==6}">
    <div class='d-tips-7'>
        <ul class='l-r-w-Inpt'>
            <li>
                <label class='vam'>卡号：</label>
                <input type='text' id='cardCourseCode' name='' value='' class='lTxt'>
            </li>
            <li class='mt20'>
                <label class='vam'>密码：</label>
                <input type='password' id='cardCoursePassword' name='' value='' class='lTxt'>
            </li>
            <li class='mt20 btn-list tac'>
                <label class='vam'>&nbsp;</label>
                <span class='login-btn'>
                    <input type='button' value='激 活' onclick='courseCardActive()'>
                </span>
            </li>
        </ul>
    </div>
</c:if>
<c:if test="${index==7}">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/common/jcrop/jquery.Jcrop.min.css" />
    <script type="text/javascript" src="${ctx}/static/common/jcrop/jquery.Jcrop.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/inxweb/user/user.js"></script>
    <div class="u-account-box undis" style="display: block;padding: 10px 0;">
        <div id="tabCont">
            <section>
                    <section class="ukindeditor of">
                        <section class="clearfix">
                            <!--个人头像上传控件-->
                            <section class="sc-u-title sc-u-title-uc">
                                <input id="fileupload" type="button" width="133" value="选择头像" height="45" class="pa" />
                            </section>
                            <!--个人头像上传控件-->
                           <div class="clearfix mt20">
                               <!--个人头像裁切控件-->
                               <div class="fl jc-demo-box pr">
                                   <c:choose>
                                       <c:when test="${user.picImg!=null && user.picImg!=''}">
                                           <img src="${staticServer}${user.picImg}" width="100%" height="100%" alt="头像加载中..." class="dis pictureWrap" id="picture" />
                                       </c:when>
                                       <c:otherwise>
                                           <img src="${ctx}/static/inxweb/img/avatar-boy.gif" width="100%" height="100%" alt="头像加载中..." class="dis pictureWrap"
                                                id="picture" />
                                       </c:otherwise>
                                   </c:choose>
                                   <div id="preview-pane" class="preview-pane1">
                                       <div class="preview-container">
                                           <c:choose>
                                               <c:when test="${user.picImg!=null && user.picImg!=''}">
                                                   <img src="${staticServer}${user.picImg}" class="jcrop-preview" alt="头像加载中..." width="100%" />
                                               </c:when>
                                               <c:otherwise>
                                                   <img src="${ctx}/static/inxweb/img/avatar-boy.gif" class="jcrop-preview" alt="头像加载中..." width="100%" />
                                               </c:otherwise>
                                           </c:choose>
                                       </div>
                                       <p class="c-999">180x180像素</p>
                                   </div>
                                   <div id="preview-pane" class="preview-pane2">
                                       <div class="preview-container" style="width: 80px; height: 80px; margin: 0 auto;">
                                           <c:choose>
                                               <c:when test="${user.picImg!=null && user.picImg!=''}">
                                                   <img src="${staticServer}${user.picImg}" class="jcrop-preview" alt="头像加载中..." width="100%" />
                                               </c:when>
                                               <c:otherwise>
                                                   <img src="${ctx}/static/inxweb/img/avatar-boy.gif" class="jcrop-preview" alt="头像加载中..." width="100%" />
                                               </c:otherwise>
                                           </c:choose>
                                       </div>
                                       <p class="c-999">80x80像素</p>
                                   </div>
                                   <div id="preview-pane" class="preview-pane3">
                                       <div class="preview-container" style="width: 50px; height: 50px;">
                                           <c:choose>
                                               <c:when test="${user.picImg!=null && user.picImg!=''}">
                                                   <img src="${staticServer}${user.picImg}" class="jcrop-preview" alt="头像加载中..." width="100%" />
                                               </c:when>
                                               <c:otherwise>
                                                   <img src="${ctx}/static/inxweb/img/avatar-boy.gif" class="jcrop-preview" alt="头像加载中..." width="100%" />
                                               </c:otherwise>
                                           </c:choose>
                                       </div>
                                       <p class="c-999">50x50像素</p>
                                   </div>
                               </div>
                               <!--个人头像裁切控件-->
                               <div class="fl ml30 uploadWrap">
                                   <p class="c-green">您上传的图片将会自动生成三种尺寸的清晰头像，请注意小尺寸的头像是否清晰。</p>
                               </div>
                               <section class="clear"></section>
                           </div>
                            <div class="uploadBc of">
                              <div class="tac">
                                    <a href="javascript:void(0)" title="" class="comm-btn order-submit" onclick="updateImg(${user.userId})">保 存</a>
                                </div>
                            </div>
                        </section>
                    </section>
            </section>
            <!-- /修改个人头像 -->
        </div>
        <input class="commBtn bgGreen w80 ml50" id="deleImage" style="display: none" type="button">
        <!--修改头像，结束-->
        <form method="post" name="photoForm">
            <input id="photoPath" value="${user.picImg}" type="hidden">
            <input id="oldPhotoPath" type="hidden" value="${user.picImg}" />
            <input value="1" id="picture_width" type="hidden">
            <input value="1" id="picture_height" type="hidden">
            <input value="82" id="txt_top" type="hidden">
            <input value="73" id="txt_left" type="hidden">
            <input value="120" id="txt_DropWidth" type="hidden">
            <input value="120" id="txt_DropHeight" type="hidden">
            <input id="txt_Zoom" type="hidden">
        </form>
    </div>
</c:if>
<%--激活优惠券--%>
<c:if test="${index==8}">
    <div class='d-tips-7'>
        <ul class='l-r-w-Inpt'>
            <li>
                <label class='vam'>优惠券：</label>
                <input type='text' id='couponCode' name='' value='' class='lTxt'>
            </li>
            <li class='mt20 btn-list tac'>
                <label class='vam'>&nbsp;</label>
                <span class='login-btn'>
                    <input type='button'  value='兑 换' onclick='couponActive()'>
                </span>
            </li>
        </ul>
    </div>
</c:if>
<%--激活课程卡--%>
<c:if test="${index==9}">
    <div class='d-tips-8'>
        <ul class='l-r-w-Inpt'>
            <li>
                <label class='vam'>分类：</label>
                <select class='lTxt'>
                    <option onclick="memberType(0)">请选择</option>
                    <c:forEach items="${memberTypes}" var="memberType">
                        <option onclick="memberType(${memberType.id})">${memberType.title}</option>
                    </c:forEach>
                </select>
            </li>
            <li class='mt20'>
                <label class='vam'>时间：</label>
                <select id="memberSale" class='lTxt'>
                    <option onclick="showPrice(0,0)">请选择</option>
                </select>
            </li>
            <li class='mt20'>
                <label class='vam'>金额：</label>
                <span class="vam f-fM"><tt class="fsize14 c-999">共计</tt><tt class="fsize18 c-master f-fM ">&nbsp;<span class="price">0</span>&nbsp;</tt><tt class="fsize14 c-999 f-fM ">元</tt></span>
            </li>
            <li class='mt20 btn-list tac'>
                <label class='vam'>&nbsp;</label>
                <span class='login-btn l-b-small mr20'>
                    <input type='button' id="payMember" value='去支付'>
                </span>
                <span class='login-btn l-b-small l-b-small-close'>
                    <input type='button' value='取 消'id="qujiao">
                </span>
            </li>
        </ul>
    </div>
</c:if>
<%--余额提现--%>
<c:if test="${index==10}">
    <div class='d-tips-9'>
        <ul class='l-r-w-Inpt l-r-w-Inpt-tx'>
            <li class="li-one hLh30">
                <span class="">
                    <tt class="vam fsize12 c-999 f-fM">可提现金额：</tt>
                    <tt class="vam fsize12 c-master f-fM">￥
                        <c:if test="${withdrawCash=='OFF'}">${userAccount.cashAmount}</c:if>
                        <c:if test="${withdrawCash=='ON'}">${userAccount.cashAmount + userAccount.backAmount}</c:if>
                    </tt>
                </span>
                <span class="ml50">
                    <a href="javascript:void (0)" onclick="getAllMoney(<c:if test="${withdrawCash=='OFF'}">${userAccount.cashAmount}</c:if><c:if test="${withdrawCash=='ON'}">${userAccount.cashAmount + userAccount.backAmount}</c:if>)" title="全部提现" class="f-fM c-green fsize14 fr mr20">
                        全部提现
                    </a>
                </span>
                <div class="clear"></div>
            </li>
            <li class="mt20">
                <label class='vam'>金额：</label>
                <input type='text' id='money' name='' value='' class='lTxt'>
            </li>
            <li class='mt20'>
                <label class='vam'>卡号：</label>
                <input type='text' id='card' name='' value='' class='lTxt'>
            </li>
            <li class='mt20'>
                <label class='vam'>银行：</label>
                <select id="bank" class='lTxt'>
                    <option value="">请选择</option>
                    <option value="广大银行">广大银行</option>
                    <option value="中国工商银行">中国工商银行</option>
                    <option value="中国建设银行">中国建设银行</option>
                    <option value="中国农业银行">中国农业银行</option>
                    <option value="招商银行">招商银行</option>
                    <option value="中国银行">中国银行</option>
                    <option value="中国交通银行">中国交通银行</option>
                    <option value="中国邮政储蓄银行">中国邮政储蓄银行</option>
                    <option value="兴业银行">兴业银行</option>
                    <option value="中国民生银行">中国民生银行</option>
                    <option value="中兴银行">中兴银行</option>
                    <option value="平安银行">平安银行</option>
                    <option value="深圳发展银行">深圳发展银行</option>
                    <option value="上海银行">上海银行</option>
                    <option value="北京农商银行">北京农商银行</option>
                </select>
            </li>
            <li class='mt30 btn-list tac'>
                <label class='vam'>&nbsp;</label>
                <span class='login-btn l-b-small mr20'>
                    <input type='button' onclick="withdrawMoney()" value='确 定'>
                </span>
                <span class='login-btn l-b-small l-b-small-close'>
                    <input type='button' value='取 消'id="qujiao">
                </span>
            </li>
        </ul>
    </div>
</c:if>
<%--充值卡激活--%>
<c:if test="${index==11}">
    <div class='d-tips-10'>
        <ul class='l-r-w-Inpt'>
            <li>
                <label class='vam'>卡号：</label>
                <input type='text' id='couponCode' name='' value='' class='lTxt'>
            </li>
            <li class='mt20'>
                <label class='vam'>密码：</label>
                <input type='text' id='couponPwd' name='' value='' class='lTxt'>
            </li>
            <li class='mt30 btn-list tac'>
                <label class='vam'>&nbsp;</label>
                <span class='login-btn l-b-small mr20'>
                    <input type='button' onclick="recharge()" value='确 定'>
                </span>
                <span class='login-btn l-b-small l-b-small-close'>
                    <input type='button' value='取 消'id="qujiao">
                </span>
            </li>
        </ul>
    </div>
</c:if>
<%--余额提现成功提示--%>
<c:if test="${index==12}">
    <div class='d-tips-11'>
        <ul class='l-r-w-Inpt l-r-w-Inpt-nom'>
            <li class="li-one tac">
                <p class='f-fM fsize16 c-green tac hLh30'><em class='icon20 sqcg-ico'></em>申请提现成功</p>
                <p class='f-fM fsize16 c-master tac hLh30'><em class='icon20 sqsb-ico'></em>申请提现失败</p>
                <p class='f-fM fsize12 c-999 tac hLh30'>正在处理中，成功后将发送信息提醒</p>
            </li>
            <li class='mt10 hLh30'>
                <label class='vam'>提现金额：</label>
                <span class="fsize16 c-333 f-fM vam">50.00元</span>
            </li>
            <li class='mt10 hLh30'>
                <label class='vam'>银行卡：</label>
                <span class="fsize14 c-999 f-fM vam">工商银行-储蓄卡(0123)</span>
            </li>
            <li class='mt10 hLh30'>
                <label class='vam'>交易订单：</label>
                <span class="fsize14 c-999 f-fM vam">11111111111111111111</span>
            </li>
            <li class='mt30 btn-list tac'>
                <label class='vam'>&nbsp;</label>
                <span class='login-btn l-b-small mr20'>
                    <input type='button' id="payMember" value='确 定'>
                </span>
            </li>
        </ul>
    </div>
</c:if>
<c:if test="${index==13}">
    <div class='d-tips-3 lrWrap'>
        <p class='fsize14 c-666 ' style="padding-left: 0">${msg}</p>
        <div class='tac mt30'>
            <a id="qujiao" href='javascript:void (0)' onclick="openBaijiayun()" title='' class='sure-submit mr10'>打开客户端</a>
            <a  id="qujiao" href='${fn:split(url,"|" )[1]}' title='' class='cancel-btn ml10'>下载客户端</a>
        </div>
    </div>
    <script>
        function openBaijiayun() {
            window.location.href='${fn:split(url,"|" )[0]}'
        }
    </script>
</c:if>
<script type="text/javascript" src="${ctx}/static/inxweb/front/dialog.js"></script>