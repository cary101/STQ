<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<body>

<c:if test="${model == 'create'}">
<form id="fm-customer" method="post" action= "${ctx}/customer/create">
    </c:if>
    <c:if test="${model == 'update'}">
    <form id="fm-customer" method="post" action= "${ctx}/customer/update">
        </c:if>
        <div style="overflow: hidden;padding: 10px">
            <table class="stqtable" style="width: 100%">
                <tr>
                    <th>公司名称</th>
                    <td><input type="text" class="easyui-validatebox" name="companyName"
                               value="${customer.companyName}" style="width: 450px;"/>
                        <c:if test="${model == 'update'}"><input type="hidden" name="companyId" value="${customer.companyId}"></c:if></td>
                </tr>
                <tr><th>英文名称</th>
                    <td><input type="text" class="easyui-validatebox" name="companyNameE"
                               value="${customer.companyNameE}" style="width: 450px;"/></td></tr>
                <tr>
                    <th>地址</th>
                    <td style="width: 455px;"><input class="easyui-validatebox" type="text" name="companyAddress"
                               value="${customer.companyAddress}" style="width: 450px;"/></td>

                </tr>
                <tr>
                    <th>英文地址</th>
                    <td><input class="easyui-validatebox" type="text" name="companyAddressE"
                               value="${customer.companyAddressE}" style="width: 450px;"/></td>
                </tr>
                <tr>
                    <th style="width:30px">邮编</th>
                    <td><input class="easyui-validatebox" type="text" name="postCode" value="${customer.postCode}" style="width: 50px;"/></td>
                </tr>
                <tr>
                    <th>公司网址</th>
                    <td style="width: 455px;"><input class="easyui-validatebox" type="text" name="webAddress"
                                           value="${customer.webAddress}" style="width: 470px;"/></td>
                </tr>
            </table>
            <table class="stqtable" style="width: 100%">
                <%--<tr>--%>
                    <%--<th>联系人</th>--%>
                    <%--<td><input class="easyui-validatebox" type="text" name="linkman"--%>
                               <%--value="${customer.linkman}"/>--%>
                    <%--</td>--%>
                    <%--<th>英文名</th>--%>
                    <%--<td><input class="easyui-validatebox" type="text" name="linkmanE"--%>
                               <%--value="${customer.linkmanE}"/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th>职位</th>--%>
                    <%--<td><input class="easyui-validatebox" type="text" name="position"--%>
                               <%--value="${customer.position}"/>--%>
                    <%--</td>--%>
                    <%--<th>职位英文</th>--%>
                    <%--<td><input class="easyui-validatebox" type="text" name="positionE"--%>
                               <%--value="${customer.positionE}"/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th>联系电话</th>--%>
                    <%--<td style="width:280px"><input class="easyui-validatebox" type="text" name="telephone"--%>
                               <%--value="${customer.telephone}"/>--%>
                    <%--</td>--%>
                    <%--<th>手机</th>--%>
                    <%--<td style="width:280px"><input class="easyui-validatebox" type="text" name="mobile"--%>
                               <%--value="${customer.mobile}"/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th>传真</th>--%>
                    <%--<td><input class="easyui-validatebox" type="text" name="fax" value="${customer.fax}"/>--%>
                    <%--</td>--%>
                    <%--<th>邮箱</th>--%>
                    <%--<td><input class="easyui-validatebox" type="text" name="email" value="${customer.email}"/></td>--%>
                <%--</tr>--%>

            </table>

            <table id="dg" class="easyui-datagrid" style="width:auto;height:auto"
                   data-options="
                        iconCls: 'icon-edit',
                        singleSelect: true,
                        title: '联系人',
                        toolbar: '#tb',
                        <%--url: GET_LINKMEN+'?refComId='+${customer.companyId},--%>
                        onClickRow: onClickRow
                    ">
                <thead>
                <tr>
                    <th data-options="field:'name',width:90,align:'center',editor:'text'">姓名</th>
                    <th data-options="field:'nameE',width:100,align:'center',editor:'text'">英文名</th>
                    <th data-options="field:'position',width:110,align:'center',editor:'text'">职位</th>
                    <th data-options="field:'telephone',width:135,align:'center',editor:'text'">联系电话</th>
                    <th data-options="field:'mobile',width:100,align:'center',editor:{type:'numberbox'}">手机</th>
                    <th data-options="field:'fax',width:120,align:'center',editor:'text'">传真</th>
                    <th data-options="field:'email',width:180,align:'center',editor:'text'">邮箱</th>
                    <%--<th data-options="field:'status',width:120,align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}">测试项目4</th>--%>
                </tr>
                </thead>
            </table>
            <div id="tb" style="height:auto">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="remove()">移除</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">确定</a>
            </div>



        </div>
    </form>
</body>
</html>