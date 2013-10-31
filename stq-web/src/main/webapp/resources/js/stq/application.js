$(function () {
    toggleOther('sampleReceived', 'sampleReceivedDtPart', 1);
    toggleOther('sample.isLiquid', 'liquidMethod', 1);
    toggleOtherCK('sample.dangerousCode', 'dangerousRemarks', 99);
    $('#dg-application').datagrid({
        nowrap:true,
        autoRowHeight:true,
        striped:true,
        singleSelect:true,
        url:SEARCH_URL,
        fitColumns:true,
        border:false,
        scrollbarSize:0,
        fit:true,
        pagination:true,
        rownumbers:true,
        pageList:[15,30,50],
        columns:[
            [
                {field:'id', hidden:true},
                {field:'applyId', title:'申请单号', align:'center', width:110},
                {field:'applyCompanyName', title:'申请公司', align:'center', width:250, formatter:formatCom},
                {field:'reportCompanyName', title:'报告公司', align:'center', width:250, formatter:formatCom},
                {field:'sampleName', title:'样品名称', align:'center', width:180},
                {field:'linkmanName', title:'联系人', align:'center', width:100},
                {field:'createDtStr', title:'录入时间', align:'center', width:125},
                {field:'statusDesc', title:'状态', align:'center', width:80}
            ]
        ]
    });
});

/**
 * 提交单据表单
 * @param submitType 保存类型：0新建，1更新, 9提交
 * @return
 */
function saveApp(type) {
    var urlStr = '';
    if(type == 0){
        urlStr = ADD_URL;
    }
    if(type == 1){
        urlStr = EDIT_URL;
    }
    if(type == 9){
        urlStr = EDIT_URL + "?saveType=9";
    }

    $('#fm-bill').form('submit', {
        url: urlStr,
        onSubmit:function () {
            var isValid = $(this).form('validate');
            if (isValid) {
                $.messager.progress();
            }
            return isValid;
        },
        success:function (result) {
            commitResult(result, type);
        }
    });
}

function commitResult(result, type){
    $.messager.progress('close');
    $('#popupApp').dialog('close');
    $('#dg-application').datagrid('reload');
    var res = result.substring(1, result.length-1);
    if(result.length > 1){
        $.messager.alert("错误",result);
    }else{
        var message;
        if(type == 0){
            message = "新建";
        }else if(type == 1){
            message = "修改";
        }else if(type == 9){
            message = "提交";
        }
        $.messager.alert("提示", message+"成功！");
    }
}

function formatCom(val,row){
    if (val.length > 12){
//        return '<span title ='+val+'>'+val.substring(0,10)+'...</span>';
        return '<span title ='+val+'>'+val+'</span>';
    } else {
        return val;
    }
}

function closePop() {
    $('#popupApp').dialog('close');
}

function initCreate() {
//    window.location.href = INTI_ADD_URL;
    $('#createPop').show();
    $('#editPop').hide();
    $('#popupApp').dialog('open').dialog('refresh', INTI_ADD_URL);
//    $.post(INTI_ADD_URL
//        ,
////        {"applyId":row.applyId},
//        function (result) {
//            if (result.success == 1) {
//                $.messager.show({
//                    title:'提示',
//                    msg:'导出成功！'
//                });
//            } else {
//                $.messager.show({
//                    title:'提示',
//                    msg:'导出失败！' + result.message
//                });
//            }
//        },
//        'json'
//    );
}

function initSearch() {
    $('#popupSearchApp').dialog('open').dialog('refresh', INIT_SEARCH_URL);
}

//修改
function initEdit() {
    $('#editPop').show();
    $('#createPop').hide();
    var row = $('#dg-application').datagrid('getSelected');
    if (!row) {
        $.messager.show({
            title:'警告',
            msg:'请先选择要修改的申请表。'
        });
    } else {
        $('#popupApp').dialog('open').dialog('refresh', INIT_EDIT_URL+'?applyId=' + row.applyId + '&id=' + row.id);
    }
}

//删除
function delApp() {
    var row = $('#dg-application').datagrid('getSelected');
    if (row) {
        $.messager.confirm('提示', '确定要取消单号为 ' + row.applyId + ' 的申请表？', function (r) {
            if (r) {
                $.post(DELETE_URL, {applyId:row.applyId}, function (result) {
//                            if (result.success) {
//                                $('#application').datagrid('reload');
//                            } else {
//                                $.messager.show({
//                                    title:'警告',
//                                    msg:"in else"
//                                });
//                            }
                    $('#dg-application').datagrid('reload');

                });
            }
        });
    } else {
        $.messager.alert('警告', '请先选择要取消的申请表', 'info');
    }
}

//导出
function export2File() {
    var row = $('#dg-application').datagrid('getSelected');
    if (row) {
        $.post('${ctx}/application/generateReport',
            {"applyId":row.applyId},
            function (result) {
                if (result.success == 1) {
                    $.messager.show({
                        title:'提示',
                        msg:'导出成功！'
                    });
                } else {
                    $.messager.show({
                        title:'提示',
                        msg:'导出失败！' + result.message
                    });
                }
            },
            'json'
        );
    } else {
        $.messager.alert('警告', '请先选择要导出的申请表', 'info');
    }

}

function normalQuery(value, name) {
    $('#dg-application').datagrid('load', {"applyId":value});
}





function otherSelect(radio, id) {
    var value = $(radio).val();
    if (value == 8) {
        $("#" + id).attr("disabled", false);
    } else {
        $("#" + id).attr("value", '');
        $("#" + id).attr("disabled", true);
    }
}

function otherChecked(checkBox, id) {
    var value = $(checkBox).val();
    if ($(checkBox).checked == "checked") {
        $("#" + id).attr("disabled", false);
    } else {
        $("#" + id).attr("value", '');
        $("#" + id).attr("disabled", true);
    }
}
//if($("#checkbox2").attr("checked")==true){

var editIndex = undefined;
function endEditing(){
    if (editIndex == undefined){return true}
    if ($('#dg').datagrid('validateRow', editIndex)){
        var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'productid'});
        var productname = $(ed.target).combobox('getText');
        $('#dg').datagrid('getRows')[editIndex]['productname'] = productname;
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
