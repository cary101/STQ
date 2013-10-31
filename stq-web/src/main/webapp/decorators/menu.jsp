<%-- 
    Document   : main
    Created on : 2008-3-21, 19:26:18
    Author     : Administrator
--%>
<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<html style="height:100%">
    <head>
    	<jsp:include page="head.jsp"></jsp:include>
        <decorator:head />
    </head>
    <body style="margin:0;padding:0;height:100%;background:#F2FBFF">
    	<div id="loading-mask"><div id="loading-msg">正在加载...</div></div>
    	<div class="mainwrap">
    		<div id="mainlayout" class="easyui-layout" fit="true">
    			<div region="north" border="false" style="height:60px;background:#A1C4ff">
					<jsp:include page="north.jsp"></jsp:include>
    			</div>
                <div region="east" border="false" style="padding:5px;width:170px;border-left:1px solid #92B7D0">
                    <div class="subtitle">相关单据</div>
                    <div class="submenu" style="padding:0 20px;">
                        <a href="${pageContext.request.contextPath}/application/index">申请表</a>
                        <a href="${pageContext.request.contextPath}/ledger/index">台账</a>
                        <a href="${pageContext.request.contextPath}/workday/index">工作日设定</a>
                    </div>
                    <div class="subtitle">系统维护</div>
                    <div class="submenu" style="padding:0 20px;">
                        <a href="${pageContext.request.contextPath}/customer/index">客户信息维护</a>
                        <a href="">测试项目维护</a>
                    </div>
                </div>
    			<div region="center" border="false" style="overflow:hidden">
    				<decorator:body />
    			</div>
    		</div>
    	</div>
    </body>
</html>
