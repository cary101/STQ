<!DOCTYPE html>
<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>STQ</title>

<script>
var INIT_SEARCH_URL = '${ctx}/application/initAdSearch'
var SEARCH_URL = '${ctx}/application/searchApp';
var INTI_ADD_URL = '${ctx}/application/initCreateApp';
var ADD_URL = '${ctx}/application/createApp';
var INIT_EDIT_URL = '${ctx}/application/initEditApp';
var EDIT_URL = '${ctx}/application/updateApp';
var DELETE_URL = '${ctx}/application/delApp';
var AD_SEARCH_URL = '${ctx}/application/initAdSearch';
var GET_CUSTOMERS_URL = '${ctx}/customer/search';
var GET_LINKMAN_URL = '${ctx}/linkman/search';
var GET_TESTITEMS_URL = '${ctx}/testitems/getItems';
</script>
<script type="text/javascript" src="${ctx}/resources/js/stq/application.js"></script>
</head>
<body class="easyui-layout">
    <div id="dg-toolbar" style="padding:2px 0">
        <table cellpadding="0" cellspacing="0" style="width:100%">
            <tr>
                <td style="padding-left:2px">
                    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"
                       onclick="initCreate()">录入</a>
                    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"
                       onclick="initEdit()">修改</a>
                    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查看</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="delApp()">取消</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-print" plain="true"
                       onclick="export2File()">导出</a>
                </td>
                <td style="text-align:right;padding-right:14px">
                    <input id="normalQuery" class="easyui-searchbox" prompt="按申请单号查询" searcher="normalQuery"/>
                </td>
            </tr>
        </table>
    </div>
<%--</div>--%>

<div id="dg-application" data-options="toolbar:'#dg-toolbar',region:'center'" style="overflow:hidden;"></div>

<div id="popupApp" class="easyui-dialog" title="申请表录入" style="width:900px;height:650px;position:relative;"
     closed="true" data-options="
				iconCls: 'icon-save',
				buttons: '#dlg-buttons'
			">
</div>
<div id="dlg-buttons">
    <%--<ul id="tt" class="easyui-tree" data-options="url:GET_TESTITEMS_URL,animate:true,checkbox:true"></ul>--%>
    <table style="width: 100%;background-color: #e8e8e8;">
        <tr style="text-align: center">
            <td style="padding: 10px;">
                <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closePop()">取消</a>
                <a href="#" id="createPop" class="easyui-linkbutton" iconCls="icon-save" onclick="saveApp(0)">保存</a>
                <a href="#" id="editPop" class="easyui-linkbutton" iconCls="icon-edit" onclick="saveApp(1)">修改</a>
                <a href="#" id="submitApp" class="easyui-linkbutton" iconCls="icon-edit" onclick="saveApp(9)">提交</a>
                <%--<a href="#" id="authApp" class="easyui-linkbutton" iconCls="icon-edit" onclick="saveApp(9)">审批</a>--%>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
