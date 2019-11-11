<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>促销卡列表</title>
    <script type="text/javascript">
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
            form.on('select(filter)', function (data) {
            });
        });
        $(function () {
        });
        function cancel() {
            $("#createUser").val('');
            $("#cardName").val('');
            $("#startCreateTime").val('');
            $("#endCreateTime").val('');
            $("#trxStatus").val(0);
            $("#payType").val(0);

        }
        //关闭课程卡
        function cLoseCard(cardId) {
            if (confirm('此作废,会将该组下的所有的课程卡作废')) {
                $.ajax({
                    url: "${ctx}/admin/card/closemaincard/" + cardId,
                    post: "post",
                    success: function (result) {

                    },
                    error: function (error) {
                    }
                });
                layer.msg("操作成功！", {icon: 1, shift: 6});
            }
        }

        //课程卡详情页面
        function detailsCard(mianCardId) {
            window.location.href = "/admin/card/cardlist?queryCardCode.cardId=" + mianCardId;
        }
        //新建课程卡
        function newCourseCard() {
            var type = "${queryMainCard.type}";
            if (type == 1) {
                window.location.href = "/admin/card/tocreatecard?addtype=course";
            } else if (type == 2) {
                window.location.href = "/admin/card/toCreateRechargecard";
            } else {
                window.location.href = "/admin/card/tocreatUserecard";
            }
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>
        <span>  <c:if test="${queryMainCard.type==1 }">课程卡</c:if>
               <c:if test="${queryMainCard.type==2 }">充值卡</c:if>
               <c:if test="${queryMainCard.type==3 }">学员卡</c:if>列表</span>
    </legend>
    <div class="layui-field-box">

        <form class="layui-form" action="${ctx}/admin/card/mainlist" name="searchForm" id="searchForm" method="post">
            <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
            <input type="hidden" name="queryMainCard.type" value="${queryMainCard.type}"/>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">创建时间</label>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" name="queryMainCard.beginTime" placeholder="开始时间" id="startCreateTime" class="layui-input"
                               onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})" value="<fmt:formatDate value='${queryMainCard.beginTime}' pattern='yyyy-MM-dd HH:mm:ss' /> ">
                    </div>
                    <div class="layui-form-mid">-</div>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" name="queryMainCard.endTime" placeholder="结束时间" id="endCreateTime" class="layui-input"
                               onclick="layui.laydate({elem: this, istime: true, festival: true,format: 'YYYY-MM-DD hh:mm:ss'})" value="<fmt:formatDate value='${queryMainCard.endTime}' pattern='yyyy-MM-dd HH:mm:ss' />">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">操作人</label>
                    <div class="layui-input-inline">
                        <input id="createUser" type="text" name="queryMainCard.createUser" value="${queryMainCard.createUser}" class="layui-input"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-inline">
                        <select class="layui-input" name="queryMainCard.status">
                            <option value="">全部</option>
                            <option value="">全部</option>
                            <option value="1" <c:if test="${queryMainCard.status==1 }">selected="selected"</c:if>>正常</option>
                            <option value="2" <c:if test="${queryMainCard.status==2 }">selected="selected"</c:if>>已过期</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline" style="display: none">
                    <label class="layui-form-label">课程卡类型</label>
                    <div class="layui-input-inline">
                        <select id="payType" name="queryMainCard.type" class="ml10">
                            <option value="0">--请选择--</option>
                            <option value="1"
                                    <c:if test="${queryMainCard.type==1 }">selected="selected"</c:if>>课程卡
                            </option>
                            <option value="3"
                                    <c:if test="${queryMainCard.type==3 }">selected="selected"</c:if>>学员卡
                            </option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline" style="display: none">
                    <label class="layui-form-label">卡名称</label>
                    <div class="layui-input-inline">
                        <input
                                id="cardName" type="text" name="queryMainCard.cardName"
                                value="${queryMainCard.cardName}" class="layui-input"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <input type="button" onclick="goPage(1)" value="查询" class="layui-btn layui-btn-small layui-btn-danger"/>
                    <input type="button" onclick="newCourseCard()"
                           <c:if test="${queryMainCard.type==1 }">value="新建课程卡"</c:if>
                           <c:if test="${queryMainCard.type==2 }">value="新建充值卡"</c:if>
                           <c:if test="${queryMainCard.type==3 }">value="新建学员卡"</c:if>

                           class="layui-btn layui-btn-small layui-btn-danger"/>
                </div>
            </div>

            <!-- 内容 开始  -->
            <table cellspacing="0" cellpadding="0" border="0" class="layui-table">
                <thead>
                <tr>
                    <td align="center" style="display: none" width="8%"><span>ID</span></td>
                    <td align="center"><span>卡名称</span></td>
                    <td align="center" style="display: none"><span>类型</span></td>
                    <c:if test="${queryMainCard.type==2 }">
                        <td align="center"><span>金额</span></td>
                    </c:if>
                    <c:if test="${queryMainCard.type==1 }">
                        <td align="center"><span>有效期</span></td>
                    </c:if>
                    <td align="center"><span>创建时间</span></td>
                    <c:if test="${queryMainCard.type==1 }">
                        <td align="center"><span>课程名称</span></td>
                    </c:if>
                    <td align="center"><span>操作人</span></td>
                    <td align="center"><span>数量</span></td>
                    <td align="center"><span>已使用数量</span></td>
                    <td align="center"><span>状态</span></td>
                    <td align="center"><span>操作</span></td>
                </tr>
                </thead>
                <tbody id="tabS_02" align="center">
                <c:if test="${!empty mainCardList}">
                    <c:forEach items="${mainCardList}" var="card" varStatus="count">
                        <tr <c:if test="${count.count%2==1 }">class="odd"</c:if>>
                            <td style="display: none">${card.id}</td>
                            <td>${card.name }</td>
                            <td style="display: none"><c:if test="${card.type==1}">课程卡</c:if>
                                <c:if test="${card.type==3 }">学员卡</c:if></td>
                            <c:if test="${queryMainCard.type==2 }">
                                <td>${card.money}</td>
                            </c:if>
                            <c:if test="${queryMainCard.type==1 }">
                                <td><fmt:formatDate value="${card.beginTime}" type="both"
                                                    pattern="yyyy-MM-dd  "/>~<fmt:formatDate value="${card.endTime}"
                                                                                             type="both"
                                                                                             pattern="YYYY-MM-dd "/></td>
                            </c:if>
                            <td><fmt:formatDate value="${card.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <c:if test="${queryMainCard.type==1 }">
                                <td>${fn:substring(card.courseName,0,20)}</td>
                            </c:if>
                            <td>${card.createUser}</td>
                            <td>${card.num}</td>
                            <td>${card.usedCardCode}</td>
                            <td>
                                <c:if test="${card.status==2 }">已过期</c:if>
                                <c:if test="${card.status!=2 }">正常</c:if>
                            </td>
                            <td>
                                <button onclick="detailsCard(${card.id})" class="layui-btn layui-btn-small" type="button">
                                    详情
                                </button>
                                <button onclick="cLoseCard(${card.id})" class="layui-btn layui-btn-small" type="button">作废
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${empty mainCardList}">
                    <tr>
                        <td align="center" colspan="16">
                            <div class="tips">
                                <span>还没有课程卡！！</span>
                            </div>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
            <!-- /pageBar begin -->
            <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
            <!-- /pageBar end -->
            <!-- 内容 结束 -->
        </form>
    </div>
</body>
</html>
