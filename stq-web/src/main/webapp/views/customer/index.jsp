<!DOCTYPE html>
<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>STQ</title>
    <%--<script type="text/javascript" src="${ctx}/resources/js/jquery.uploadify-3.1.min.js"></script>--%>
    <script>
        var SEARCH_URL = '${ctx}/customer/search';
        var INIT_ADD_URL = '${ctx}/customer/initCreate';
        var ADD_URL = '${ctx}/customer/create';
        var INIT_EDIT_URL = '${ctx}/customer/initUpdate';
        var EDIT_URL = '${ctx}/customer/update';
        var DELETE_URL = '${ctx}/customer/delete';
        var GET_LINKMEN = '${ctx}/linkman/search';

        $(function () {
            $('#dg-customer').datagrid({
                nowrap:true,
                autoRowHeight:false,
                fitColumns:true,
                striped:true,
                singleSelect:true,
                url:SEARCH_URL,
                scrollbarSize:0,
                fit:true,
                columns:[
                    [
                        {field:'companyName', title:'公司名称', align:'center', width:200},
                        {field:'companyAddress', title:'地址', align:'center', width:320},
                        {field:'postCode', title:'邮编', align:'center', width:65},
                        {field:'webAddress', title:'网址', align:'center', width:150}
                    ]
                ],
                pagination:true,
                rownumbers:true
            });
        });

        function normalQuery(value, name) {
            $('#dg-customer').datagrid('load', {"companyName":value});
        }

        /**
         * 提交单据表单
         * @param submitType 保存类型：0新建，1更新
         * @return
         */
        function saveCusInfo(type) {
            if (type == 0) {
                $('#fm-customer').form('submit', {
                    onSubmit:function () {
                    },
                    success:function (result) {
                        $('#popup').dialog('close');
                        $('#dg-customer').datagrid('reload');
                    }
                });
            }
            if (type == 1) {
                $('#fm-customer').form('submit', {
                    onSubmit:function () {
                    },
                    success:function (result) {
                        $('#popup').dialog('close');
                        $('#dg-customer').datagrid('reload');
                    }
                });
            }

        }

        function del() {
            var row = $('#dg-customer').datagrid('getSelected');
            if (row) {
                $.messager.confirm('提示', '确定要删除 '+row.companyName+' ？', function (r) {
                    if (r) {
                        $.post(DELETE_URL, {companyId:row.companyId}, function () {
//                            if (result.success) {
//                                $('#application').datagrid('reload');
//                            } else {
//                                $.messager.show({
//                                    title:'警告',
//                                    msg:"in else"
//                                });
//                            }
                            $('#dg-customer').datagrid('reload');

                        });
                    }
                });
            } else {
                $.messager.show({
                    title:'警告',
                    msg:'请选择要删除的信息。'
                });
            }
        }

        function closePop() {
            $('#popup').dialog('close');
        }

        function closePopEdit() {
            $('#popup').dialog('close');
        }

        function initCreate() {
            $('#createPop').show();
            $('#editPop').hide();
            $('#popup').dialog('open').dialog('refresh', INIT_ADD_URL);
        }

        function initEdit() {
            $('#createPop').hide();
            $('#editPop').show();
            var row = $('#dg-customer').datagrid('getSelected');
            if (!row) {
                $.messager.show({
                    title:'警告',
                    msg:'请先选择要修改的客户'
                });
            } else {
                $('#popup').dialog('open').dialog('refresh', INIT_EDIT_URL + '?companyId=' + row.companyId);
            }
        }

        function formatStatus(value, row) {
            if (value == 0) {
                return '取消';
            } else if (value == 1) {
                return '录入';
            } else {
                return value;
            }
        }

        var editIndex = undefined;
        function endEditing(){
            if (editIndex == undefined){return true}
            if ($('#dg').datagrid('validateRow', editIndex)){
                var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'name'});
//                var productname = $(ed.target).combobox('getText');
//                $('#dg').datagrid('getRows')[editIndex]['productname'] = productname;
                $('#dg').datagrid('endEdit', editIndex);
                editIndex = undefined;
                return true;
            } else {
                return false;
            }
        }
        function onClickRow(index){
            if (editIndex != index){
                if (endEditing()){
                    $('#dg').datagrid('selectRow', index)
                            .datagrid('beginEdit', index);
                    editIndex = index;
                } else {
                    $('#dg').datagrid('selectRow', editIndex);
                }
            }
        }
        function append(){
            if (endEditing()){
                $('#dg').datagrid('appendRow',{status:'P'});
                editIndex = $('#dg').datagrid('getRows').length-1;
                $('#dg').datagrid('selectRow', editIndex)
                        .datagrid('beginEdit', editIndex);
            }
        }
        function remove(){
            if (editIndex == undefined){return}
            $('#dg').datagrid('cancelEdit', editIndex)
                    .datagrid('deleteRow', editIndex);
            editIndex = undefined;
        }
        function accept(){
            if (endEditing()){
                $('#dg').datagrid('acceptChanges');
            }
        }
        function reject(){
            $('#dg').datagrid('rejectChanges');
            editIndex = undefined;
        }
        function getChanges(){
            var rows = $('#dg').datagrid('getChanges');
            alert(rows.length+' rows are changed!');
        }

    </script>

    <style>
        .stqtable th {text-align: left;background-color: #F6FCFF; font-weight: normal; width: 70px;}
        .stqtable tr {line-height: 30px;}
    </style>
</head>
<body class="easyui-layout">

<div id="dg-customer" data-options="toolbar:'#dg-toolbar',region:'center'" style="overflow:hidden;"></div>

<div id="dg-toolbar" style="padding:2px 0">
    <table cellpadding="0" cellspacing="0" style="width:100%">
        <tr>
            <td style="padding-left:2px">
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"
                   onclick="initCreate()">添加</a>
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"
                   onclick="initEdit()">修改</a>
                <%--<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查看</a>--%>
                <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="del()">删除</a>
            </td>
            <td style="text-align:right;padding-right:14px">
                <input id="normalQuery" class="easyui-searchbox" prompt="按公司名查询" searcher="normalQuery"
                       style="width: 180px;"/>
                <a href="#" class="easyui-linkbutton" plain="true" onclick="initSearch()">高级查询</a>
            </td>
        </tr>
    </table>
</div>

<div id="popup" class="easyui-dialog" title="客户信息表录入" style="width:900px;height:500px;position:relative"
     closed="true" data-options="
				iconCls: 'icon-save',
				buttons: '#dlg-buttons'
			">
</div>
<div id="dlg-buttons">
    <table style="width: 100%">
        <tr style="text-align: center">
            <td style="padding: 5px;">
                <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closePop()">取消</a>
                <a href="#" class="easyui-linkbutton" id="createPop" iconCls="icon-save" onclick="saveCusInfo(0)">保存</a>
                <a href="#" class="easyui-linkbutton" id="editPop" iconCls="icon-edit" onclick="saveCusInfo(1)">修改</a>

            </td>
        </tr>
    </table>
</div>

<div id="popupSearch" class="easyui-dialog" title="高级查询" style="width:500px;height:200px;"
     closed="true">
</div>

</body>
</html>
