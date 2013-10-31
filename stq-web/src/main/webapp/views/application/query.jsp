<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>--%>
<%@ include file="../layouts/base.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div style="padding:10px">
    <div class="subtitle" style="border-bottom:1px solid #ccc">请输入查询参数：</div>
    <form id="ad-search" method="post" action="${ctx}/application/searchApp">
        <div style="padding:10px 0 10px 30px">
            <table cellpadding="0" cellspacing="2">
                <%--<tr>--%>
                    <%--<td>录入时间从：</td>--%>
                    <%--<td><input name="createDtFrom" class="query easyui-datebox" style="width:106px"/></td>--%>
                    <%--<td>到：</td>--%>
                    <%--<td><input name="createDtTo" class="query easyui-datebox" style="width:106px"/></td>--%>
                <%--</tr>--%>
                <tr>
                    <td>报告时间从：</td>
                    <td><input name="reportDtFrom" class="query easyui-datebox" style="width:106px"/></td>
                    <td>到：</td>
                    <td><input name="reportDtTo" class="query easyui-datebox" style="width:106px"/></td>
                </tr>
                <tr>
                    <td>申请公司：</td>
                    <td><input name="applyCompanyName" class="query" style="width:106px"/></td>
                    <td>状态：</td>
                    <td>
                        <input name="status" class="query easyui-combobox" style="width:106px"
                        <%--url="<c:url value='/data/depot/getItems'/>"--%>
                               valueField="id" textField="name"/>
                    </td>
                </tr>
                <tr style="text-align: center">
                    <td colspan="6" style="padding: 15px;">
                        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="adSearch()">查询</a>
                        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="saveApp(1)">修改</a>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
</body>
</html>
<script>
    //	$('#dlg-query').find('input[name=intercourseId]').combointercourse({
    <%--url:'<c:url value="/data/intercourse/getItems"/>'--%>
    //	});
</script>