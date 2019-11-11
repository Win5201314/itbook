<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" http-equiv="Content-Type" />
<title>后台管理系统-${websitemap.web.company}-${websitemap.web.title}</title>
<script type="text/javascript" src="${ctx}/static/admin/js/highChart/highcharts.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/js/highChart/highcharts-3d.js"></script>
<script type="text/javascript" language="javascript">
$(function() {
	//活跃度 15天
	drawCartogramLogin(15);
});

//活跃度按天数
function drawCartogramLogin(days) {
    var dateTime = '';
    var studentNum =  "" ;
    $.ajax({
        url:baselocation +"/admin/statistics/web/detailajax",
        type:"post",
        data:{"days":days,"type":"LOGIN_NUM"},
        dataType:"json",
        async:false,
        success:function(result){
            if(result.success){
                dateTime=result.message;
                studentNum= "["+result.entity+"]" ;
            }else{
                layer.msg("请求错误!", {icon: 5, shift: 6});
            }
        }
    });

    var myWidth = $('.commonTab01').css('width').slice(0,-2);//获取chartContainer容器的宽度，创建时自带宽度。会带有px，highchart的width只认数字。

    $('#chartContainer').highcharts({
        chart: {
            type: 'column',
            backgroundColor: 'rgba(0,0,0,0)',
            width:myWidth*0.8
        },
	        title: {
	            text: '最近'+days+'天活跃学员',
	            x: -20 //center
	        },
	        subtitle: {
	            text: '',
	            x: -20
	        },
	        xAxis: {
	        	categories : eval("(" + dateTime + ")")
	        },

        yAxis: [{ // left y axis
            title: {
                text: null
            },
            labels: {
                align: 'left',
                x: 3,
                y: 16,
                format: '{value:.,0f}'
            },
            showFirstLabel: false,
            min:0
        }, { // right y axis
            linkedTo: 0,
            gridLineWidth: 0,
            opposite: true,
            title: {
                text: null
            },
            labels: {
                align: 'right',
                x: -3,
                y: 16,
                format: '{value:.,0f}'
            },
            showFirstLabel: false  ,
            min:0
        }],

	        tooltip: {
	            valueSuffix: '人'
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series: [{
	        	name : '活跃人数',
				data : eval("(" + studentNum + ")")
	        }]
	    });
}

//注册人数活跃度按天数
function drawCartogramRegistered(days) {
    var dateTime = '';
    var studentNum =  "" ;
    $.ajax({
        url:baselocation +"/admin/statistics/web/detailajax",
        type:"post",
        data:{"days":days,"type":"REGISTERED_NUM"},
        dataType:"json",
        async:false,
        success:function(result){
            if(result.success){
                dateTime=result.message;
                studentNum= "["+result.entity+"]" ;
            }else{
                layer.msg("请求错误!", {icon: 5, shift: 6});
            }
        }
    });
    var myWidth = $('.commonTab01').css('width').slice(0,-2);//获取chartContainer容器的宽度，创建时自带宽度。会带有px，highchart的width只认数字。

    $('#chartContainer').highcharts({
            chart: {
                type: 'column',
                backgroundColor: 'rgba(0,0,0,0)',
                width:myWidth*0.8
            },
	        title: {
	            text: '最近'+days+'天注册学员',
	            x: -20 //center
	        },
	        subtitle: {
	            text: '',
	            x: -20
	        },
	        xAxis: {
	        	categories : eval("(" + dateTime + ")")
	        },

        yAxis: [{ // left y axis
            title: {
                text: null
            },
            labels: {
                align: 'left',
                x: 3,
                y: 16,
                format: '{value:.,0f}'
            },
            showFirstLabel: false,
            min:0
        }, { // right y axis
            linkedTo: 0,
            gridLineWidth: 0,
            opposite: true,
            title: {
                text: null
            },
            labels: {
                align: 'right',
                x: -3,
                y: 16,
                format: '{value:.,0f}'
            },
            showFirstLabel: false  ,
            min:0
        }],

	        tooltip: {
	            valueSuffix: '人'
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series: [{
	        	name : '注册人数',
				data : eval("(" + studentNum + ")")
	        }]
	    });
}

//订单数
function drawCartogramOrder(days) {
    var dateTime = '';
    var orderNum =  "" ;//订单数量
    var orderSuccessNum = "";//支付成功的订单数量
    var orderInitNum = "";//未支付成功的订单数量
    var orderClosedNum = "";//已取消支付的订单数量
    $.ajax({
        url:baselocation +"/admin/statistics/web/detailajax",
        type:"post",
        data:{"days":days,"type":"ORDER_NUM"},
        dataType:"json",
        async:false,
        success:function(result){
            if(result.success){
                dateTime=result.message;
                orderNum= "["+result.entity.orderNum+"]" ;
                orderSuccessNum= "["+result.entity.orderSuccessNum+"]" ;
                orderInitNum= "["+result.entity.orderInitNum+"]" ;
                orderClosedNum= "["+result.entity.orderClosedNum+"]" ;
            }else{
                layer.msg("请求错误!", {icon: 5, shift: 6});
            }
        }
    });
    var myWidth = $('.commonTab01').css('width').slice(0,-2);//获取chartContainer容器的宽度，创建时自带宽度。会带有px，highchart的width只认数字。

    $('#chartContainer').highcharts({
        chart: {
            type: 'column',
            backgroundColor: 'rgba(0,0,0,0)',
            width:myWidth*0.8
        },
        title: {
            text: '最近'+days+'天订单数量',
            x: -20 //center
        },
        subtitle: {
            text: '',
            x: -20
        },
        xAxis: {
        	categories : eval("(" + dateTime + ")")
        },
        yAxis: [{ // left y axis
            title: {
                text: null
            },
            labels: {
                align: 'left',
                x: 3,
                y: 16,
                format: '{value:.,0f}'
            },
            showFirstLabel: false,
            min:0
        }, { // right y axis
            linkedTo: 0,
            gridLineWidth: 0,
            opposite: true,
            title: {
                text: null
            },
            labels: {
                align: 'right',
                x: -3,
                y: 16,
                format: '{value:.,0f}'
            },
            showFirstLabel: false  ,
            min:0
        }],

	        tooltip: {
	            valueSuffix: '条'
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series: [{
	        	name : '订单数量',
				data : eval("(" + orderNum + ")")
	        },{
	        	name : '已支付订单数量',
	        	data : eval("("+orderSuccessNum+")")
	        },{
	        	name : '未支付订单数量',
	        	data : eval("("+orderInitNum+")")
	        },{
	        	name : '已取消订单数量',
	        	data : eval("("+orderClosedNum+")")
	        }]
	    });
}

//今天登录人数
function logintoday(){
    var myWidth = $('.commonTab01').css('width').slice(0,-2);//获取chartContainer容器的宽度，创建时自带宽度。会带有px，highchart的width只认数字。

    $('#chartContainer').highcharts({
        chart: {
            type: 'column',
            backgroundColor: 'rgba(0,0,0,0)',
            width:myWidth*0.8
        },
        title: {
            text: '今天活跃人数'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            type: 'category',
            labels: {
                rotation: 0,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: ''
            }
        },
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: '今天活跃人数: <b>{point.y} 人</b>'
        },
        series: [{
            name: 'Population',
            data: [
                ['活跃人数', ${todayloginnum}]
            ],
            dataLabels: {
                enabled: true,
                rotation: -360,
                color: '#FFFFFF',
                align: 'right',
                x: 4,
                y: 10,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif',
                    textShadow: '0 0 3px black'
                }
            }
        }]
    });
}
//今天登录人数 ==/

//今天注册人数
function registeredday(){
	var registeredNum=0;
	$.ajax({
		url:baselocation +"/admin/todayRegisteredNum",
        type:"post",
        dataType:"json",
        async:false,
        success:function(result){
            if(result.success){
                registeredNum=result.entity;
            }else{
                layer.msg("请求错误!", {icon: 5, shift: 6});
            }
        }
	});
    var myWidth = $('.commonTab01').css('width').slice(0,-2);//获取chartContainer容器的宽度，创建时自带宽度。会带有px，highchart的width只认数字。

    $('#chartContainer').highcharts({
        chart: {
            type: 'column',
            backgroundColor: 'rgba(0,0,0,0)',
            width:myWidth*0.8
        },
        title: {
            text: '今天注册人数'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            type: 'category',
            labels: {
                rotation: 0,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: ''
            }
        },
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: '今天注册人数: <b>{point.y} 人</b>'
        },
        series: [{
            name: 'Population',
            data: [
                ['注册人数', registeredNum]
            ],
            dataLabels: {
                enabled: true,
                rotation: -360,
                color: '#FFFFFF',
                align: 'right',
                x: 4,
                y: 10,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif',
                    textShadow: '0 0 3px black'
                }
            }
        }]
    });
}
//今天订单数
function orderday() {
    var orderNum = "";//订单数量
    var orderSuccessNum = "";//支付成功的订单数量
    var orderInitNum = "";//未支付成功的订单数量
    var orderClosedNum = "";//已取消支付的订单数量
    $.ajax({
        url: baselocation + "/admin/todayOrderNum",
        type: "post",
        dataType: "json",
        async: false,
        success: function (result) {
            if (result.success) {
                orderNum = result.entity.orderNum;
                orderSuccessNum = result.entity.orderSuccessNum;
                orderInitNum = result.entity.orderInitNum;
                orderClosedNum = result.entity.orderClosedNum;
            } else {
                layer.msg("请求错误!", {icon: 5, shift: 6});
            }
        }
    });
    var myWidth = $('.commonTab01').css('width').slice(0,-2);//获取chartContainer容器的宽度，创建时自带宽度。会带有px，highchart的width只认数字。

    $('#chartContainer').highcharts({
        chart: {
            type: 'column',
            backgroundColor: 'rgba(0,0,0,0)',
            width:myWidth*0.8
        },
        title: {
            text: '今天订单数'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: ['订单数', '已支付订单数', '未支付订单数', '已取消订单数']

        },
        yAxis: {
            min: 0,
            title: {
                text: ''
            }
        },
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: '今天订单数: <b>{point.y} 人</b>'
        },
        series: [{data: [orderNum, orderSuccessNum, orderInitNum, orderClosedNum]}]
    });
}
function xuanze(str) {
    if (str == 'LOGIN_NUM') {
        $("#countName").html("学员活跃度走势");
        $("#day").attr("onclick", "logintoday()");
        $("#day7").attr("onclick", "drawCartogramLogin(7)");
        $("#day30").attr("onclick", "drawCartogramLogin(30)");
        drawCartogramLogin(15);
    } else if (str == 'REGISTERED_NUM') {
        $("#countName").html("学员注册数走势");
        $("#day").attr("onclick", "registeredday()");
        $("#day7").attr("onclick", "drawCartogramRegistered(7)");
        $("#day30").attr("onclick", "drawCartogramRegistered(30)");
        drawCartogramRegistered(15);
    } else if (str == 'ORDER_NUM') {
        $("#countName").html("学院订单数走势");
        $("#day").attr("onclick", "orderday()");
        $("#day7").attr("onclick", "drawCartogramOrder(7)");
        $("#day30").attr("onclick", "drawCartogramOrder(30)");
        drawCartogramOrder(15);
    } else {
        $("#countName").html("学员活跃度走势");
        $("#day").attr("onclick", "logintoday()");
        $("#day7").attr("onclick", "drawCartogramLogin(7)");
        $("#day30").attr("onclick", "drawCartogramLogin(30)");
        drawCartogramLogin(15);
    }
}
function quicklyToPage(tapId,tapUrl,windowUrl) {
    window.parent.quicklyToPage(tapId,tapUrl);

    window.location.href= baselocation+windowUrl

}

</script>
</head>
<body style="background: none;">
	<div class="rMain">
		<%--<h1 class="hLh30 fsize28 c-999">
			欢迎你,
			<span class="c-master">超级管理员!</span>
		</h1>--%>
		<div class="numb-box-t">
            <div class="layui-field-box">
                <p class="hLh30 fsize20 c-333 f-fH">快捷入口</p>
                <a href="/admin/systemstate/CPU" title="" style="display: none;">CPU</a>
                <a href="/admin/systemstate/memory" title="" style="display: none;">内存</a>
                <a href="/admin/systemstate/disk" title="" style="display: none;">硬盘</a>
                <a href="/admin/systemstate/net" title="" style="display: none;">网络</a>
                <div class="mt20">
                    <!-- Big buttons -->
                    <ul class="dash clearfix">
                        <li>
                            <a class="tooltip tool-1" title="新增课程" onclick="quicklyToPage(209,'/admin/cou/list','/admin/cou/toAddCourse')" href="javascript:void(0)">
                                <div class="clearfix all-box">
                                    <div class="pic-box">
                                        <img src="${ctx}/static/admin/assets/tool-1.png">
                                    </div>
                                    <div class="tool-txt">
                                        <span>新增课程</span>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="quicklyToPage(202,'/admin/article/showlist','/admin/article/initcreate')" title="新增资讯" class="tooltip tool-2 clearfix">
                                <div class="clearfix all-box">
                                    <div class="pic-box">
                                        <img src="${ctx}/static/admin/assets/tool-2.png">
                                    </div>
                                    <div class="tool-txt">
                                        <span>新增资讯</span>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="quicklyToPage(209,'/admin/cou/list','/admin/cou/list')" title="资讯列表" class="tooltip tool-3 clearfix">
                                <div class="clearfix all-box">
                                    <div class="pic-box">
                                        <img src="${ctx}/static/admin/assets/tool-3.png">
                                    </div>
                                    <div class="tool-txt">
                                        <span>课程列表</span>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="quicklyToPage(null,'/admin/sysuser/userlist','/admin/sysuser/userlist')"title="管理用户"  class="tooltip tool-4 clearfix">
                                <div class="clearfix all-box">
                                    <div class="pic-box">
                                        <img src="${ctx}/static/admin/assets/tool-4.png">
                                    </div>
                                    <div class="tool-txt">
                                        <span>管理用户</span>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="quicklyToPage(null,'/admin/website/imagesPage','/admin/website/imagesPage')"title="媒体图片管理" class="tooltip tool-5 clearfix">
                                <div class="clearfix all-box">
                                    <div class="pic-box">
                                        <img src="${ctx}/static/admin/assets/tool-5.png">
                                    </div>
                                    <div class="tool-txt">
                                        <span>媒体图片管理</span>
                                    </div>
                                </div>
                            </a>
                        </li>
                    </ul>
                    <!-- End of Big buttons -->
                </div>
            </div>
            <div class="layui-field-box">
                <p class="hLh30 fsize20 c-333 f-fH mt20">数据信息</p>
                <div class="sj-box">
                    <div class="sj-tit">数据信息</div>
                    <div class="stat-desc">
                <span>
                    <img src="${ctx}/static/admin/assets/sj-l-1.png">
                    <p class="sjxx-list-txt">资讯数：${webCountMap.articleCount}</p>
                </span>
                <span>
                    <img src="${ctx}/static/admin/assets/sj-l-2.png">
                    <p class="sjxx-list-txt">课程数：${webCountMap.courseCount}</p>
                </span>
                <span>
                    <img src="${ctx}/static/admin/assets/sj-l-3.png">
                    <p class="sjxx-list-txt">用户数：${webCountMap.userCount}</p>
                </span>
                        <%--<span>
                            <img src="${ctx}/static/admin/assets/sj-l-4.png">
                            <p class="sjxx-list-txt">问答数：${webCountMap.questionsCount}</p>
                        </span>--%>
                <span>
                    <img src="${ctx}/static/admin/assets/sj-l-5.png">
                    <p class="sjxx-list-txt">订单数：${webCountMap.orderCount}</p>
                </span>
                <span>
                    <img src="${ctx}/static/admin/assets/sj-l-6.png">
                    <p class="sjxx-list-txt">已支付订单：${webCountMap.orderSuccessCount}</p>
                </span>
                <span>
                    <img src="${ctx}/static/admin/assets/sj-l-7.png">
                    <p class="sjxx-list-txt">未支付订单：${webCountMap.orderInitCount}</p>
                </span>
                    </div>
                </div>
            </div>
            <div class="layui-field-box">
                <p class="hLh30 mt20 fsize20 c-333 f-fH"><tt class="fsize20 c-333 f-fH">网站统计</tt> &gt; <tt class="fsize20 c-333 f-fH">学员活跃度走势</tt></p>
                <div class="layui-field-box mt20">
                    <div class="layui-form-item">
                        <button onclick="xuanze('LOGIN_NUM')" class="layui-btn layui-btn-small" type="button" >学员活跃度走势</button>
                        <button onclick="xuanze('REGISTERED_NUM')" class="layui-btn layui-btn-small" type="button" >学员注册数走势</button>
                        <button onclick="xuanze('ORDER_NUM')" class="layui-btn layui-btn-small" type="button" >学员订单数走势</button>
                    </div>
                    <div class="layui-form-item">
                        <div class="mt20">
                            <div class="commonWrap commonTab01" >
                                <caption><span><input type="button" onclick="logintoday()" id="day" value="今天" class="layui-btn layui-btn-small layui-btn-danger">
                                <input type="button" onclick="drawCartogramLogin(7)" id="day7" value="最近7天" class="layui-btn layui-btn-small layui-btn-danger">
                                 <input type="button" onclick="drawCartogramLogin(30)" id="day30" value="最近30天" class="layui-btn layui-btn-small layui-btn-danger">
                                </span>
                                </caption>
                                <div id="chartContainer" class="chartContainer"></div>
                            </div><!-- /commonWrap -->
                        </div>
                        <div class="page_head">
                            <h4></h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
	</div>
</body>
</html>