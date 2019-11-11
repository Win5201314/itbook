<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>流水详情</title>

    <script type="text/javascript">
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });
        });
        /**
         * 会员进行延期
         * @param orderState detailState
         */
        function delayCourse(em) {
            $("#hideSpan").css("display", "block");
            $(em).hide();
        }

        function clickStopTIme() {
            var stopTime = document.getElementById('loseTime').value;
            if (stopTime == "") {
                layer.msg("延期时间不能为空！", {icon: 5, shift: 6});
                return false;
            }
            var daoqishijian = document.getElementById('daoqishijian').value;
            if (stopTime < daoqishijian) {
                layer.msg("延期时间必须大于到期时间！", {icon: 5, shift: 6});
                return false;
            }
            var detailId = '${memberRecordDTO.id}';
            if (detailId == null || detailId == '') {
                layer.msg("会员信息错误", {icon: 5, shift: 6});
                return false;
            }
            $.ajax({
                url: "${ctx}/admin/memberrecord/updatemrecord",
                data: {
                    "trxorderId": detailId,
                    "authTime": stopTime
                },
                dataType: "json",
                type: "post",
                async: false,
                success: function (result) {
                    if (result.success == true) {
                        layer.msg("已延期", {icon: 1, shift: 6});
                        $("#hideSpan").hide();
                        $(".delaybtn").show();
                        window.location.reload();
                    }
                },
                error: function (error) {
                    layer.msg("error", {icon: 5, shift: 6});
                }

            });

        }
        function clickStopTImequxiao(obj) {
            $("#hideSpan").hide();
            $(".delaybtn").show();
            $("#loseTime").val("");
        }
    </script>

</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>会员开通记录管理</span>
        &gt;
        <span>会员开通记录详情</span>
    </legend>
    <div class="layui-field-box">
        <form class="layui-form" action="">
            <input type="hidden" id="daoqishijian"
                   value="<fmt:formatDate value="${memberRecordDTO.endDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>"/>

            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">用户邮箱</label>
                <div class="layui-input-block">
                    <input type="text" name="username" lay-verify="required" value="${memberRecordDTO.email}" readonly autocomplete="off" class="layui-input layui-input-6">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">类型</label>
                <div class="layui-input-block">
                   <div class="xy-warp fsize14 c-999 f-fM">
                       ${memberRecordDTO.memberTitle}
                   </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">开通时间</label>
                <div class="layui-input-block">
                    <div class="xy-warp fsize14 c-999 f-fM">
                        <fmt:formatDate value="${memberRecordDTO.beginDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">过期时间</label>
                <div class="layui-input-block">
                    <div class="xy-warp fsize14 c-999 f-fM">
                        <fmt:formatDate value="${memberRecordDTO.endDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">修改时间</label>
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-small delaybtn" lay-submit="" onclick="delayCourse(this);" type="button">延期</button>
                    <div id="hideSpan" style="display: none">
                        <input readonly="readonly" id="loseTime" type="text" name="queryTrxorderDetail.authTime" class="layui-input layui-input-6" onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})"/>
                        <div class="mt10">
                            <button class="layui-btn layui-btn-small delaybtn" lay-submit="" onclick="clickStopTIme();" type="button">提交</button>
                            <button class="layui-btn layui-btn-small layui-btn-primary" type="button"  onclick="clickStopTImequxiao(this)">取消</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block mt20">
                    <a class="layui-btn layui-btn-danger" title="返 回" href="javascript:history.go(-1);">返回</a>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
