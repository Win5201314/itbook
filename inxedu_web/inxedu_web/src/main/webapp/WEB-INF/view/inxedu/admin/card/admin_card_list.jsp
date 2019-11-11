<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>详情</title>
    <script type="text/javascript">
        layui.use(['form', 'laydate'], function () {
            var form = layui.form();
            var laydate = layui.laydate;
        });
        function cancel() {
            $("#createUser").val('');
            $("#cardName").val('');
            $("#startCreateTime").val('');
            $("#endCreateTime").val('');
            $("#trxStatus").val("");
            $("#payType").val(0);
            $("#cardNode").val('');
            $("#cardId").val("");
            $("#email").val('');
        }
        //关闭课程卡
        function cLoseCard(cardId, index, status) {
            if (confirm('是否改变该课程卡状态')) {
                $.ajax({
                    url: "${ctx}/admin/card/closecard?id=" + cardId + "&&status=" + status,
                    post: "post",
                    success: function (result) {
                        window.location.reload();
                    },
                    error: function (error) {
                        layer.msg(error.responseText, {icon: 5, shift: 6});
                    }
                })
            }
        }

        //excel导出
        function exportExcel() {
            document.searchForm.action = "/admin/card/exportCard";
            $("#searchForm").submit();
            document.searchForm.action = "/admin/card/cardlist";
        }
        function searchCard() {
            $("#searchForm").submit();
        }

    </script>
</head>
<body>

<c:set var="cartType" value="课程卡"></c:set>
<c:if test="${card.type==2}">
    <c:set var="cartType" value="充值卡"></c:set>
</c:if>
<c:if test="${card.type==3}">
    <c:set var="cartType" value="学员卡"></c:set>
</c:if>
<fieldset class="layui-elem-field">
    <legend>
        <em class="icon14 i_01"></em>
        &nbsp;
        <span>${cartType}</span>
        &gt;
        <span>${cartType}编码详情</span>
    </legend>
   <div class="layui-field-box">
       <div class="numb-box-t">
           <form action="${ctx}/admin/card/cardlist" class="layui-form" name="searchForm" id="searchForm" method="post">
               <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>

               <div class="layui-form-item">
                   <div class="layui-inline">
                       <label class="layui-form-label">卡编号</label>
                       <div class="layui-input-inline">
                           <input type="text" id="cardNode" name="queryCardCode.cardNode" class="layui-input"
                                  value="${queryCardCode.cardNode}"/>
                       </div>
                   </div>
                   <div class="layui-inline">
                       <label class="layui-form-label">状态</label>
                       <div class="layui-input-inline">
                           <select id="trxStatus" name="queryCardCode.status" >
                               <option value="">-请选择-</option>
                               <option value="">-请选择-</option>
                               <option value="INIT"
                                       <c:if test="${queryCardCode.status=='INIT'}">selected="selected"</c:if>>未使用
                               </option>
                               <option value="USED"
                                       <c:if test="${queryCardCode.status=='USED'}">selected="selected"</c:if>>已使用
                               </option>
                               <option value="OVERDUE"
                                       <c:if test="${queryCardCode.status=='OVERDUE'}">selected="selected"</c:if>>
                                   已过期
                               </option>
                               <option value="CLOSE"
                                       <c:if test="${queryCardCode.status=='CLOSE'}">selected="selected"</c:if>>已作废
                               </option>
                           </select>
                       </div>
                   </div>
                   <input type="button" onclick="searchCard()" value="查询"
                          class="layui-btn layui-btn-small layui-btn-danger"/>
                   <%--<input style="display: none" type="button" value="清空" class="layui-btn layui-btn-small" onclick="cancel()" />--%>
                   <input type="button" onclick="exportExcel()" class="layui-btn layui-btn-small layui-btn-danger"
                          value="导出Excel"/>
                   <input type="button" value="返回" class="layui-btn layui-btn-small layui-btn-danger"
                          onclick="history.go(-1)"/>
               </div>


               <ul>
                   <li style="display: none">
                       <span class="ddTitle"><font>创建人：</font></span>
                       <input id="createUser" type="text" name="queryCardCode.createUser"
                              value="${queryCardCode.createUser}" class="ml10"/>
                   </li>
                   <li style="display: none">
                       <span class="ddTitle"><font>卡名称：</font></span>
                       <input type="text" id="cardName" name="queryCardCode.name" value="${queryCardCode.name}"
                              class="ml10"/>
                   </li>
                   <li style="display: none">
                       <span class="ddTitle"><font>创建开始时间：</font></span>
                       <input type="text" readonly="readonly" id="startCreateTime"
                              name="queryCardCode.beginTime"
                              value="<fmt:formatDate value='${queryCardCode.beginTime}' pattern='yyyy-MM-dd HH:mm:ss' /> "
                              class="ml10"/>
                   </li>
                   <li style="display: none">
                       <span class="ddTitle"><font>主卡id：</font></span>
                       <input type="text" id="cardId" name="queryCardCode.cardId" class="ml10"
                              value="${queryCardCode.cardId}"/>
                   </li>
                   <li style="display: none">
                       <span class="ddTitle"><font>卡类型：</font></span>
                       <select id="payType" name="queryCardCode.type" class="ml10">
                           <option value="0">-请选择-</option>
                           <option value="1" <c:if test="${queryCardCode.type==1 }">selected="selected"</c:if>>
                               课程卡
                           </option>
                           <option value="3" <c:if test="${queryCardCode.type==3 }">selected="selected"</c:if>>
                               学员卡
                           </option>
                       </select>
                   </li>

                   <li style="display: none">
                       <span class="ddTitle"><font>创建结束时间：</font></span>
                       <input type="text" readonly="readonly" name="queryCardCode.endTime" id="endCreateTime"
                              value="<fmt:formatDate value='${queryCardCode.endTime}' pattern='yyyy-MM-dd HH:mm:ss' />"
                              class="ml10"/>
                   </li>
               </ul>
               <table class="layui-table">
                   <thead>
                   <tr>
                       <td align="center" style="display: none" width="8%"><span>ID</span></td>
                       <td align="center" style="display: none"><span>卡名称</span></td>
                       <td align="center" style="display: none"><span>类型</span></td>
                       <td align="center" style="display: none"><span>金额</span></td>
                       <td align="center"><span>卡编号</span></td>
                       <td align="center"><span>密码</span></td>
                       <td align="center" style="display: none"><span>创建时间</span></td>
                       <td align="center" style="display: none"><span>创建人</span></td>
                       <td align="center" style="display: none"><span>订单编号</span></td>
                       <td align="center"><span>有效期</span></td>
                       <td align="center"><span>状态</span></td>
                       <td align="center"><span>操作</span></td>
                   </tr>
                   </thead>
                   <tbody id="tabS_02" align="center">
                   <c:if test="${!empty cardCodeList}">
                       <c:forEach items="${cardCodeList}" var="card" varStatus="count">
                           <tr <c:if test="${count.count%2==1 }">class="odd"</c:if>>
                               <td style="display: none">${card.id}</td>
                               <td style="display: none">${card.name }</td>
                               <td style="display: none"><c:if test="${card.type==1}">课程卡</c:if>
                                   <c:if test="${card.type==3 }">学员卡</c:if></td>
                               <td style="display: none">${card.money}</td>

                               <td>${card.cardCode}</td>
                               <td>${card.cardCodePassword}</td>
                               <td style="display: none"><fmt:formatDate value="${card.createTime}"
                                                                         pattern="yyyy-MM-dd HH:mm:ss"/></td>
                               <td style="display: none">${card.createUser}</td>
                               <td style="display: none">${card.requestId}</td>
                               <td><fmt:formatDate value="${card.beginTime}" pattern="yyyy-MM-dd  "/>
                                   ~<fmt:formatDate value="${card.endTime}" pattern="YYYY-MM-dd "/></td>
                               <td>
										<span id="status${count.count}">
											<c:if test="${card.status=='INIT'}">未使用</c:if>
											<c:if test="${card.status=='USED'}">已使用</c:if>
											<c:if test="${card.status=='OVERDUE'}">已过期</c:if>
											<c:if test="${card.status=='CLOSE'}">已作废</c:if>
										</span>
                               </td>
                               <td>
                                   <c:if test="${card.status=='INIT'}">
                                       <button onclick="cLoseCard(${card.id},${count.count},'CLOSE')"
                                               class="layui-btn layui-btn-small" type="button">作废
                                       </button>
                                   </c:if>
                                   <c:if test="${card.status=='CLOSE'}">
                                       <button onclick="cLoseCard(${card.id},${count.count},'INIT')"
                                               class="layui-btn layui-btn-small" type="button">恢复
                                       </button>
                                   </c:if>
                                       <%--学员卡才会出现--%>
                                   <c:if test="${queryCardCode.type==3}">
                                       <button onclick="window.location.href='/admin/user/getuserList?queryUser.keyWord=${card.cardCode}'"
                                               class="layui-btn layui-btn-small" type="button">查询学员
                                       </button>
                                   </c:if>


                               </td>
                           </tr>
                       </c:forEach>
                   </c:if>
                   <c:if test="${empty cardCodeList}">
                       <tr>
                           <td align="center" colspan="16">
                               <div class="tips">
                                   <span>还没有虚拟卡！！</span>
                               </div>
                           </td>
                       </tr>
                   </c:if>
                   </tbody>
               </table>
               <!-- /pageBar begin -->
               <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
               <!-- /pageBar end -->
           </form>
       </div>
   </div>
</fieldset>
</body>
</html>
