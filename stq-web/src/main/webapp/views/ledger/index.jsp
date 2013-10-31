<!DOCTYPE html>
<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>STQ</title>
   <%--<script type="text/javascript" src="${ctx}/resources/js/jquery.uploadify-3.1.min.js"></script>--%>
    <script>
        var ADD_URL = '${ctx}/ledger/index';
        var SEARCH_URL = '${ctx}/ledger/index';
        var EDIT_URL = '${ctx}/ledger/addApp';
        var DELETE_URL = '${ctx}/ledger/delApp';
        var AD_SEARCH_URL = '${ctx}/ledger/initAdSearch';

        $(function () {
            $('#ledger').datagrid({
//                title:'台账',
                nowrap:true,
                autoRowHeight:false,
                striped:true,
                singleSelect:true,
                url:SEARCH_URL,
                columns:[
                    [
                        {field:'applyId', title:'申请单号', align:'center', width:100},
                        {field:'applyCompanyName', title:'申请公司', align:'center', width:180},
                        {field:'reportCompanyName', title:'报告公司', align:'center', width:180},
                        {field:'sampleName', title:'样品名称', align:'center', width:180},
                        {field:'linkman', title:'联系人', align:'center', width:120},
                        {field:'createDtStr', title:'录入时间', align:'center', width:125},
                        {field:'status', title:'状态', align:'center', width:80}
                    ]
                ],
                pagination:true,
                rownumbers:true
            });
        });

        /**
         * 提交单据表单
         * @param submitType 保存类型：0新建，1更新
         * @return
         */
        function saveApp(type) {
            if(type == 0){
                $('#fm-bill').form('submit', {
                    onSubmit:function () {
                    },
                    success:function (result) {
                        $('#popupApp').dialog('close');
                        $('#ledger').datagrid('reload');
                    }
                });
            }
            if(type == 1){
                $('#fm-bill').form('submit', {
                    onSubmit:function () {
                    },
                    success:function (result) {
                        $('#popupApp').dialog('close');
                        $('#ledger').datagrid('reload');
                    }
                });
            }

        }

        function adSearch(){
            $('#ad-search').form('submit', {
                onSubmit:function () {
                },
                success:function (result) {
//                            $('#popupApp').dialog('close');
//                            $('#ledger').datagrid('reload');
                }
            });
        }

        function closePop() {
            $('#popupApp').dialog('close');
        }

        function closePopEdit() {
            $('#popupApp').dialog('close');
        }

        function initCreate() {
            $('#popupApp').dialog('open').dialog('refresh', '${ctx}/ledger/initCreateApp');
        }

        function initSearch() {
            $('#popupSearchApp').dialog('open').dialog('refresh', '${ctx}/ledger/initAdSearch');
        }

        function initEdit() {
            var row = $('#ledger').datagrid('getSelected');
            if (!row) {
                $.messager.show({
                    title:'警告',
                    msg:'请先选择要修改的申请表。'
                });
            } else {
                $('#popupApp').dialog('open').dialog('refresh', '${ctx}/ledger/initEditApp?applyId=' + row.applyId);
            }
        }

        function delApp() {
            var row = $('#ledger').datagrid('getSelected');
            if (row) {
                $.messager.confirm('提示', '确定要取消单号为 '+row.applyId+' 的申请表？', function (r) {
                    if (r) {
                        $.post(DELETE_URL, {applyId:row.applyId}, function (result) {
//                            if (result.success) {
//                                $('#ledger').datagrid('reload');
//                            } else {
//                                $.messager.show({
//                                    title:'警告',
//                                    msg:"in else"
//                                });
//                            }
                            $('#ledger').datagrid('reload');

                        });
                    }
                });
            } else {
                $.messager.show({
                    title:'警告',
                    msg:'请先选择要取消的申请表。'
                });
            }
        }

        function normalQuery(value, name) {
            $('#ledger').datagrid('load', {"applyId":value});
        }

        function pdf() {
            $.post('${ctx}/access/generateReport');
            $('#ledger').datagrid.reload();
        }

        function formatStatus(value,row){
            if (value == 0){
                return '取消';
            } else if (value == 1){
                return '录入';
            } else {
                return value;
            }
        }



    </script>
</head>
<body class="easyui-layout">

<div id="ledger" data-options="toolbar:'#dlg-toolbar',region:'center'" style="overflow:hidden;"></div>

<div id="dlg-toolbar" style="padding:2px 0">
    <table cellpadding="0" cellspacing="0" style="width:100%">
        <tr>
            <td style="padding-left:2px">
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"
                   onclick="initCreate()">录入</a>
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"
                   onclick="initEdit()">修改</a>
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查看</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-print" plain="true" onclick="delApp()" >导出</a>
            </td>
            <td style="text-align:right;padding-right:14px">
                <input id="normalQuery" class="easyui-searchbox" prompt="按申请单号查询" searcher="normalQuery" style="width: 180px;"/>
                <a href="#" class="easyui-linkbutton" plain="true" onclick="initSearch()">高级查询</a>
            </td>
        </tr>
    </table>
</div>

<div id="popupApp" class="easyui-dialog" title="申请表录入" style="width:800px;height:500px;position:relative"
     closed="true">
</div>

<%--<div id="popupEditApp" class="easyui-dialog" title="申请表修改" style="width:800px;height:500px;position:relative"--%>
<%--closed="true">--%>
<%--</div>--%>

<div id="popupSearchApp" class="easyui-dialog" title="高级查询" style="width:500px;height:200px;"
     closed="true">
</div>

</body>
</html>
