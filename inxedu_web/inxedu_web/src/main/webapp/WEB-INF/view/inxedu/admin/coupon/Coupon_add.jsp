<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>优惠券添加</title>
    <script type="text/javascript"
            src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
    <script type="text/javascript"
            src="${ctx}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
    <link rel="stylesheet" href="${ctx}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/static/common/ztree/css/demo.css?v=${v}" type="text/css"/>
    <script type="text/javascript" src="${ctx}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
    <script type="text/javascript" src="${ctx}/static/common/ztree/jquery.ztree.exedit-3.5.js?v=${v}"></script>
    <script type="text/javascript">
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('radio(radio-range)', function (data) {
                console.log(data.value); //被点击的radio的value值
                changeRange(data.value);
            });
            form.on('submit(*)', function(data){
                addSubmit();
                return false;
            });
        });
        //subject ztree start
        var subject_setting = {
            view: {
                showLine: true,
                showIcon: true,
                selectedMulti: false,
                dblClickExpand: false
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: 'subjectId',
                    pIdKey: 'parentId',
                    rootPid: ''
                },
                key: {
                    name: 'subjectName',
                    title: 'subjectName'
                }
            },
            callback: {
                onClick: subject_treeOnclick
            }
        };
        var subject_treedata =${subjectList};
        //切换专业时操作
        function subject_treeOnclick(e, treeId, treeNode) {
            hideSubjectMenu();
            $("#subjectId").val(treeNode.subjectId);
            $("#subjectNameBtn").val(treeNode.subjectName);
        }

        //清空专业的查询条件时同时清除考点
        function subject_cleantreevalue() {
            hideSubjectMenu();
            $("#subjectId").val(0);
            $("#subjectNameBtn").val("");
        }
        function showSubjectMenu() {
            var cityObj = $("#subjectNameBtn");
            var cityOffset = $("#subjectNameBtn").offset();
            $("#subjectmenuContent").css({
                left: cityOffset.left + "px",
                top: cityOffset.top + cityObj.outerHeight() + "px"
            }).slideDown("fast");
            $("body").bind("mousedown", onBodyDown);
        }
        function hideSubjectMenu() {
            $("#subjectmenuContent").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown);
        }
        function onBodyDown(event) {
            if (!(event.target.id == "subjectNameBtn" || event.target.id == "subjectmenuContent" || $(event.target).parents("#subjectmenuContent").length > 0)) {
                hideSubjectMenu();
            }
        }

        $().ready(function () {
            $.fn.zTree.init($("#subject_ztreedemo"), subject_setting, subject_treedata);
            //专业名称显示
            if ($("#subjectId").val() != "" && $("#subjectId").val() != 0) {
                var zTree = $.fn.zTree.getZTreeObj("subject_ztreedemo");
                var treeNode = zTree.getNodeByParam("subjectId", $("#subjectId").val(), null);
                if (treeNode != null) {
                    $("#subjectNameBtn").val(treeNode.subjectName);
                }
            }
        });
        $(function () {
            changeType($('input[name="type"]:checked').val());//初始化优惠券类型
            changeRange($('input[name="range"]:checked').val());//初始化优惠券限制范围
            changeUseType($('input[name="useType"]:checked').val());//初始化次数限制
        });
        /**
         * 选择优惠券类型
         * @param val
         */
        function changeType(val) {
            //显示优惠券发放类型
            //showHideGiveType(0);

            $('input[name="range"]:eq(0)').prop("checked", true);
            changeRange(1);
            if (val == 1) {
                $("#rangeTr").show();
                $("#reduceTr").css("display", "none");
                $("#preferentialTr").css("display", "");
                $("#reduceInput").attr("name", "");
                $("#preferentialSelect").attr("name", "coupon.amount");
            } else if (val == 2) {
                $("#rangeTr").show();
                $("#preferentialTr").css("display", "none");
                $("#reduceTr").css("display", "");
                $("#preferentialSelect").attr("name", "");
                $("#reduceInput").attr("name", "coupon.amount");
            } else if (val == 3) {//会员定额券无范围限制
                $("#rangeTr").hide();
                $("#preferentialTr").css("display", "none");
                $("#reduceTr").css("display", "");
                $("#preferentialSelect").attr("name", "");
                $("#reduceInput").attr("name", "coupon.amount");

                //隐藏优惠券发放类型
                showHideGiveType(1);
            }
        }

        /**
         * 选择优惠券使用次数
         **/
        function changeUseType(val) {
            if (val == 2) {
                $("#totalNumTr").css("display", "");
                $("#totalNum").attr("name", "coupon.totalNum");

                //显示发送优惠券方式
                //showHideGiveType(0);
            } else {
                $("#totalNumTr").css("display", "none");
                $("#totalNum").attr("name", "");

                //隐藏发送优惠券方式
                showHideGiveType(1);
            }
        }
        function changeRange(val) {
            //显示购买课程赠送优惠券选项
            $("#buyCourse").hide();

            if (val == 1) {//所有范围
                subject_cleantreevalue();//清空专业
                $("#courseIdHidden").val("");
                $("#limitSubject").css("display", "none");
                $("#limitCourse").css("display", "none");
                $("#subjectSelect").val(-1);
                //清空选择的课程信息
                $("#coursestr p").remove();
            } else if (val == 2) {//单选专业
                $("#courseIdHidden").val("");
                $("#limitSubject").show();
                $("#limitCourse").css("display", "none");
                //清空选择的课程信息
                $("#coursestr p").remove();
            } else if (val == 3) {//多选课程
                subject_cleantreevalue();//清空专业
                $("#limitSubject").css("display", "none");
                $("#limitCourse").css("display", "");
                $("#subjectSelect").val(-1);
                //隐藏购买课程赠送优惠券选项
                $("#buyCourse").show();
            }
        }

        function addCourse() {
            window.open('${cxt}/admin/cou/couponCourseList?page.currentPage=1',
                    +'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=802'
            );
        }
        function getCourseList(CourseList) {
            //p对象的name获取已存在的课程集合，去除提交过来的重复对象
            $("p[name='courseName']").each(function (i, val) {
                for (var j = 0; j < CourseList.length; j++) {
                    var id = CourseList[j];
                    if (val.id == 'coursespan' + id[0]) {
                        CourseList.splice(j, 1);
                    }
                }
            });
            //插入到表格中
            coursePAdd(CourseList);
        }
	function coursePAdd(myArray){
		var str="";
		var courseIds=$("#courseIdHidden").val();
		for(var i=0;i<myArray.length;i++){
			var arr=myArray[i];
			str+="<p style='width:100%;margin: 0 0 0em' id='coursespan"+arr[0]+"' name='courseName' value='"+arr[0]+"' title='"+arr[1]+"'>"+arr[1]+"&nbsp;&nbsp;<input type='button' value='删除' class='layui-btn layui-btn-small' onclick='delcourse(\""+arr[0]+"\",\""+arr[1]+"\");'></p>";
			courseIds+=arr[0]+",";
		}
		$("#coursestr").prepend(str);
		$("#courseIdHidden").val(courseIds);
	}
	function delcourse(courseId,title){
		if(confirm("确定要删除【"+title+"】")){
			$("#coursespan"+courseId).remove();
			var ids= $("#courseIdHidden").val();
			ids=ids.replace(courseId+",","");
			$("#courseIdHidden").val(ids);
		}
	}
	function clearcourse()
	{
		$("#courseIdHidden").val("");
		$("#coursestr>p").remove();
	}

        function addSubmit() {
            if (parseFloat($("#amount").val()) <= 0) {
                layer.msg("优惠金额必须大于0", {icon: 5, shift: 6});
                return false;
            }
            if (parseInt($("#totalNum").val()) <= 0) {
                layer.msg("生成优惠券编码数量必须大于0", {icon: 5, shift: 6});
                return;
            }

            if (parseInt($("#totalNum").val()) > 5000) {
                layer.msg("生成优惠券编码数量不能大于5000", {icon: 5, shift: 6});
                return;
            }
            if (parseFloat($("#limitAmount").val()) < parseFloat($("#reduceInput").val())) {
                layer.msg("使用限额必须大于优惠金额", {icon: 5, shift: 6});
                return false;
            }
//验证优惠券限制范围
            var range = $('input[name="range"]:checked').val();
            if (range == 2) {//单选专业
                if ($("#subjectId").val() == null || $("#subjectId").val() == 0) {
                    layer.msg("请选择优惠券限制专业", {icon: 5, shift: 6});
                    return;
                }
            } else if (range == 3) {//多选课程
                if ($("#courseIdHidden").val() == null || $("#courseIdHidden").val() == '') {
                    layer.msg("请选择优惠券限制课程", {icon: 5, shift: 6});
                    return;
                }
            }
            //优惠券使用开始至截至日期
            if ($("#startTime").val() == null || $("#startTime").val() == '') {
                layer.msg("请选择使用起始日期", {icon: 5, shift: 6});
                return;
            }

            if ($("#endTime").val() == null || $("#endTime").val() == '') {
                layer.msg("请选择使用截止日期", {icon: 5, shift: 6});
                return;
            }
            var begin = new Date($("#startTime").val().replace(/-/g, "/"));
            var end = new Date($("#endTime").val().replace(/-/g, "/"));

            if (begin > end) {
                layer.msg("截止日期必须大于起始日期", {icon: 5, shift: 6});
                return;
            }
            //验证优惠券生成数量
            var useType = $('input[name="useType"]:checked').val();
            if (useType == 2) {//适用人群为个人时验证生成数量，所有人只生成一个优惠券编码
                var giveType = $("[name='coupon.giveType']:checked").val();
                if (giveType == 0 && ($("#totalNum").val() == null || $("#totalNum").val() == '')) {
                    layer.msg("请输入优惠券编码生成数量", {icon: 5, shift: 6});
                    return;
                }
                if (isNaN($("#totalNum").val())) {
                    layer.msg("生成优惠券编码数量只能为数字", {icon: 5, shift: 6});
                    return;
                }
                if (parseInt($("#totalNum").val()) > 5000) {
                    layer.msg("生成优惠券编码数量不能大于5000", {icon: 5, shift: 6});
                    return;
                }
                if (parseInt($("#totalNum").val()) <= 0 && giveType == 1) {
                    layer.msg("生成优惠券编码数量不能小于0", {icon: 5, shift: 6});
                    return;
                }
            }
            $("#addCouponForm").submit();
        }
        /**
         * 优惠券发放类型显示隐藏  0 显示 1 隐藏
         * @param type
         */
        function showHideGiveType(type) {
            if (type == 0) {
                $("#giveType").show();
            }
            if (type == 1) {
                $("#giveType :radio").first().prop("checked", "checked");
                $("#giveType").hide();
            }
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <a href="${ctx}/admin/coupon/page">
            <span>优惠券管理</span>
        </a>
        &gt;
        <span>优惠券添加</span>
    </legend>
    <!-- /tab4 begin -->
    <div class="numb-box-t">
        <div class="layui-field-box">
            <form class="layui-form" action="${ctx}/admin/coupon/add" method="post" id="addCouponForm">
            <input type="hidden" name="coupon.subjectId" id="subjectId" value="0"/>
            <input type="hidden" name="limitCourseId" id="courseIdHidden"/>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;优惠券名称</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input layui-input-6" name="coupon.title" id="couponTitle" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w">次数限制</label>
                <div class="layui-input-block">
                    <input type="radio" value="2" title="正常" name="useType" checked="checked" onclick="changeUseType(2)"/>
                    <input type="radio" value="1" title="无限" name="useType" onclick="changeUseType(1)"/>
                    <font class="fsize12 c-red f-fM vam">无限'优惠券在有效期内可以被任意多人使用多次，'正常'优惠券使用一次后不能继续使用</font>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;使用限额</label>
                <div class="layui-input-block">
                    <input type="number" class="layui-input layui-input-6" lay-verify="number" id="limitAmount" name="coupon.limitAmount"/>
                    <p class="fsize12 c-red f-fM hLh20">*只有订单总金额达到这个数的订单才能使用此折扣优惠券，如300元，则请输入'300'</p>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;优惠金额</label>
                <div class="layui-input-block">
                    <input type="number" class="layui-input layui-input-6" name="coupon.amount" id="amount" lay-verify="number"/>
                    <p class="fsize12 c-red f-fM hLh20">*该优惠券金额，如5元优惠券，则请输入'5'</p>
                </div>
            </div>
            <div class="layui-form-item" id="rangeTr">
                <label class="layui-form-label layui-form-label-w">适用范围</label>
                <input type="radio" value="1"  lay-filter="radio-range" name="range" title="所有课程" checked="checked"/>
                <input type="radio" value="2"  lay-filter="radio-range" name="range" title="单选专业"/>
                <input type="radio" value="3"  lay-filter="radio-range" name="range" title="多选课程"/>
            </div>
            <div class="layui-form-item" id="limitSubject">
                <div class="layui-input-block">
                    <input id="subjectNameBtn" type="text" class="layui-input" readonly="readonly" style=" width: 160px" onclick="showSubjectMenu()"/>
                    <div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;z-index: 99999;" >
                        <ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:160px;"></ul>
                    </div>
                </div>
            </div>
            <div class="layui-form-item" id="limitCourse">
                <div class="layui-input-block" id="coursestr">
                    <input class="layui-btn layui-btn-small layui-btn-danger" type="button" onclick="addCourse()" value="添加课程">
                    <input class="layui-btn layui-btn-small layui-btn-danger" type="button" onclick="clearcourse()" value="清空">
                </div>
            </div>
            <div class="layui-form-item" id="totalNumTr">
                <div class="">
                    <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;生成优惠编码数量</label>
                    <div class="layui-input-block">
                        <input type="number" lay-verify="number" class="layui-input layui-input-6" name="coupon.totalNum" id="totalNum" value="0"/>
                        <p class="fsize12 c-red f-fM hLh20">*不得大于5000</p>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label layui-form-label-w"><font color="red">*</font>&nbsp;使用日期</label>
                    <div class="layui-input-inline">
                        <input id="startTime" class="layui-input" name="coupon.startTime" placeholder="开始日期" lay-verify="date" value="<fmt:formatDate value="${queryCourse.beginCreateTime}" pattern="yyyy-MM-dd "/>" onclick="layui.laydate({elem: this, festival: true})">
                    </div>
                    <div class="layui-input-inline">
                        <input id="endTime" class="layui-input" name="coupon.endTime" placeholder="截止日期" lay-verify="date" value="<fmt:formatDate value="${queryCourse.endCreateTime}" pattern="yyyy-MM-dd "/>" onclick="layui.laydate({elem: this, festival: true})">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="*">提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1);">返回</button>
                </div>
            </div>

        </form>
        </div>
    </div>
</fieldset>
</body>
</html>
