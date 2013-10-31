<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tld/ehr-tag.tld" prefix="stq" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Form - jQuery EasyUI Demo</title>
    <style type="text/css">
    </style>
    <script type="text/javascript">
        var GET_CUSTOMERS_URL = '${ctx}/customer/search';
        $(function () {
            toggleOther('sampleReceived', 'sampleReceivedDtPart', 1);
            toggleOther('sample.isLiquid', 'liquidMethod', 1);
            toggleOtherCK('sample.dangerousCode', 'dangerousRemarks', 99);
        });
    </script>

</head>
<body>

<form id="fm-bill" method="post" action="">
<div class="titlebar">
<div class="formBar" style="margin-left: 25px;">
    <div class="formTitle">申请信息</div>
</div>

<table class="stqtable" style="margin-left: 25px;margin-right:25px;padding:10px;background-color: #F5F5F5;line-height:28px;">
    <colgroup style="width:55%;">
        <col style="min-width:60px;width: 8%" />
        <col style="min-width:250px;width: 47%"/>
    </colgroup>
    <colgroup style="width:45%;">
        <col style="min-width:60px;width: 6%" />
        <col style="min-width:250px;width: 39%"/>
    </colgroup>
    <tr>
        <td>申请号</td>
        <td><input name="applyId" value="${application.applyId}" readonly="true"
                  placeholder="审批后系统自动生成" style="background-color: #dcdcdc;"/>

        </td>
        <input type="hidden" name="id" value="${application.id}">
        <td>服务类型</td>
        <td>
            <stq:dic-radio name="serviceType" dicmeta="P0002" value="${application.serviceType}"/>
        </td>
    </tr>
    <tr>
        <td>申请公司</td>
        <td colspan="3" style="line-height: 20px;">
            <input id="applyCompanyName" class="easyui-combobox" name="applyCompanyName" value="${application.applyCompanyName}"
                   data-options="
                        mode:'remote',
                        url:GET_CUSTOMERS_URL,
                        valueField:'companyName',
                        textField:'companyName',
                        panelHeight:'auto',
                        width : 300,
                        delay:600,
                        required:true,
                        missingMessage:'请填写申请公司',
                        onSelect:function(record){
                            $('#applyCompanyAddress').val(record.companyAddress);
                             if(record.linkmen.length >= 1){
                                $('#linkmanName').val(record.linkmen[0].name);
                                $('#tel').val(record.linkmen[0].telephone);
                                $('#mobile').val(record.linkmen[0].mobile);
                                $('#fax').val(record.linkmen[0].fax);
                                $('#email').val(record.linkmen[0].email);
                                $('#applyCompanyId').val(record.companyId);
                                $('#linkmanId').val(record.linkmen[0].linkmanId);
                                    $('#linkmanName').combobox({
                                                url:GET_LINKMAN_URL+'?linkmanIds='+record.linkmanIds,
                                                valueField:'name',
                                                textField:'name',
                                                value: record.linkmen[0].name,
                                                width: 140,
                                                panelHeight: 60,
                                                onSelect:function(record){
                                                    $('#linkmanId').val(record.linkmanId);
                                                    $('#tel').val(record.telephone);
                                                    $('#mobile').val(record.mobile);
                                                    $('#fax').val(record.fax);
                                                    $('#email').val(record.email);
                                                }
                                            });
                             }
                        }
                    ">
            <input type="hidden" id="applyCompanyId" name="applyCompanyId" value="${application.applyCompanyId}">
        </td>
    </tr>
    <tr>
        <td>公司地址</td>
        <td colspan="3"><input class="easyui-validatebox" id="applyCompanyAddress"
                               value="${application.customer.companyAddress}" style="width: 300px;"
                               data-options="required:true,missingMessage:'请填写申请公司地址'"/>
        </td>
    </tr>
    <tr>
        <td>联系人</td>
        <td style="line-height: 20px;">
            <input class="easyui-validatebox" id="linkmanName" name="linkmanName" data-options="required:true,missingMessage:'请填写联系人'"
                   value="${application.linkmanName}" readonly="true"/>
            <input type="hidden" id="linkmanId" name="linkmanId" value="${application.linkmanId}">
            <input type="hidden" name="sellerId" value="${application.sellerId}">
        </td>
        <td>电话</td>
        <td>
            <input class="easyui-validatebox" id="tel" name="tel" value="${application.linkman.telephone}"
                   data-options="required:true,missingMessage:'请填写电话'" readonly="true"/>
        </td>
    </tr>
    <tr>
        <td>手机</td>
        <td>
            <input class="easyui-validatebox" id="mobile" name="mobile" value="${application.linkman.mobile}" readonly="true"/>
        </td>
        <td style="width: 80px">传真</td>
        <td>
            <input class="easyui-validatebox" id="fax" name="fax" value="${application.linkman.fax}" readonly="true"/>
        </td>
    </tr>
    <tr>
        <td>邮箱</td>
        <td colspan="3"><input class="easyui-validatebox" id="email" data-options="validType:'email',required:true,missingMessage:'请填写邮箱'"
                               name="email" style="width: 300px;" value="${application.linkman.email}" readonly="true"/>
        </td>
    </tr>
</table>

<div class="formBar" style="margin-left: 25px;">
    <div class="formTitle">样品信息</div>
</div>
<table class="stqtable" style="margin-left: 25px;margin-right:25px;padding:10px;background-color: #F5F5F5;line-height:28px;">
    <colgroup style="width:50%;">
        <col style="min-width:80px;width: 10%" />
        <col style="min-width:250px;width: 40"/>
    </colgroup>
    <colgroup style="width:50%;">
        <col style="min-width:80px;width: 10%" />
        <col style="min-width:250px;width: 40"/>
    </colgroup>
    <tr>
        <td>样品名称</td>
        <td><input class="easyui-validatebox" name="sample.name" value="${application.sample.name}"
                   data-options="required:true,missingMessage:'请填写样品名称'"/>
            <%--<input type="hidden" name="sampleId" value="${application.sampleId}" >--%>
            <input type="hidden" name="sample.sampleId" value="${application.sampleId}">
        </td>
        <td>接受分包</td>
        <td>
            <stq:dic-radio name="canSubpackage" dicmeta="P0001" value="${application.canSubpackage}"/>
        </td>
    </tr>
    <tr >
        <td>已接样</td>
        <td>
            <stq:dic-radio name="sampleReceived" dicmeta="P0001" value="${application.sampleReceived}"
                    onchange="toggleOther('sampleReceived', 'sampleReceivedDtPart', 1)"/>
        </td>
        <%--<tbody id="sampleReceivedDtPart" style="display: none;">--%>
            <td>接样日期</td>
            <td>
                <input id="sampleReceivedDtInput" class="easyui-datebox" name="sampleReceivedDt" style="width:145px;"
                       value="${application.sampleReceivedDtStr}">
            </td>
        <%--</tbody>--%>
    </tr>
    <tr>
        <td>型号</td>
        <td><input class="easyui-validatebox" name="sample.modelNo" value="${application.sample.modelNo}"
                   data-options="required:true,missingMessage:'请填写型号'"/></td>
        <td>可覆盖型号</td>
        <td>
            <input class="easyui-validatebox" name="sample.mayCoverModel" value="${application.sample.mayCoverModel}"/>
        </td>
    </tr>
    <tr>
        <td>主要材料</td>
        <td>
            <input class="easyui-validatebox" name="sample.mainMeterial" value="${application.sample.mainMeterial}"/>
        </td>
        <td>批号</td>
        <td>
            <input class="easyui-validatebox" name="sample.lotNo" value="${application.sample.lotNo}"/>
        </td>
    </tr>
    <tr>
        <td>供应商</td>
        <td>
            <input class="easyui-validatebox" name="sample.supplier" value="${application.sample.supplier}"/>
        </td>
        <td>买家</td>
        <td>
            <input class="easyui-validatebox" name="sample.buyer" value="${application.sample.buyer}"/>
        </td>
    </tr>
    <tr>
        <td colspan="3" style="">样品为液体/油墨类&nbsp;&nbsp;
            <stq:dic-radio name="sample.isLiquid" dicmeta="P0001" value="${application.sample.liquid}"
                    onchange="toggleOther('sample.isLiquid', 'liquidMethod', 1)"/>
            <span id="liquidMethod" style="display: none;">
                测试方式：
                <stq:dic-radio name="sample.liquidMethod" dicmeta="P0004" value="${application.sample.liquidMethod}"/>
            </span>
        </td>
    </tr>
    <tr>
        <td colspan="2">待测物质浓度>=样品总量的1% &nbsp;&nbsp;
            <stq:dic-radio name="sample.concentrationGt1" dicmeta="P0001" value="${application.sample.concentrationGt1}"/>
        </td>
    </tr>
    <tr>
        <td>样品危险性</td>
        <td colspan="3">
            <stq:dic-checkbox name="sample.dangerousCode" dicmeta="P0003" value="${application.sample.dangerousCode}"
                    onchange="toggleOtherCK('sample.dangerousCode', 'dangerousRemarks', 99)"/>
            <span id="dangerousRemarks" style="display: none">
                <br><input type="text" id="sampleDangerousRemarks" name="sample.dangerousRemarks" value="${application.sample.dangerousRemarks}"
                          placeholder="若选择其他，请请描叙" style="width: 450px;">
            </span>
        </td>
    </tr>
</table>

<div class="formBar" style="margin-left: 25px;">
    <div class="formTitle">报告信息</div>
</div>
<table class="stqtable" style="margin-left: 25px;margin-right:25px;padding:10px;background-color: #F5F5F5;line-height:28px;">
    <colgroup style="width:50%;">
        <col style="min-width:80px;width: 10%" />
        <col style="min-width:250px;width: 40"/>
    </colgroup>
    <colgroup style="width:50%;">
        <col style="min-width:80px;width: 10%" />
        <col style="min-width:250px;width: 40"/>
    </colgroup>
    <tr>
        <td style="width: 75px;">报告公司抬头</td>
        <td colspan="3">
            <input class="easyui-validatebox" name="reportCompanyName" data-options="required:true,missingMessage:'请填写报告公司抬头'"
                   value="${application.reportCompanyName}" style="width: 400px;"/></td>
    </tr>
    <tr>
        <td style="width: 75px;">报告公司地址</td>
        <td colspan="3">
            <input class="easyui-validatebox" name="reportCompanyAddress" data-options="required:true,missingMessage:'请填写报告公司地址'"
                   value="${application.reportCompanyAddress}" style="width: 400px;"/></td>
    </tr>
    <tr>
        <td style="width: 75px;">报告类型</td>
        <td colspan="2">
            <stq:dic-checkbox name="reportType" dicmeta="P0005" value="${application.reportType}"/>
        </td>
    </tr>
    <tr>
        <td>期望报告时间</td>
        <td style="line-height: 20px;">
            <input id="dd" class="easyui-datebox" name="reportDt" value="${application.reportDtStr}" data-options="required:true,
            missingMessage:'请填写期望报告时间'">
        </td>
    </tr>
    <tr>
        <td style="width: 75px;">发票抬头</td>
        <td colspan="3">
            <input class="easyui-validatebox" name="invoiceCommany" data-options="required:true,missingMessage:'请填写发票抬头'"
                   value="${application.invoiceCommany}" style="width: 400px;"/>
        </td>
    </tr>
    <tr>
        <td style="width: 75px;">寄送地址</td>
        <td colspan="3">
            <input class="easyui-validatebox" name="reportInvoiceTo" data-options="required:true,missingMessage:'请填写寄送地址'"
                   value="${application.reportInvoiceTo}" style="width: 400px;"/>
        </td>
    </tr>
</table>

<div class="formBar" style="margin-left: 25px;">
    <div class="formTitle">检测项目</div>
</div>
<div style="padding-left: 20px;">
    <div id="tt" class="easyui-tree" data-options="url:GET_TESTITEMS_URL,animate:true,checkbox:true"></div>
</div>
</div>
</form>
</body>

</html>