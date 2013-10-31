<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="header">
	<div style="padding:10px;text-align:right;">
		<span style="color:#ddd">欢迎你,${currentUser.userName }</span>&nbsp;&nbsp;&nbsp;
		<a href="${pageContext.request.contextPath}/access/initPage" style="color:#fff">退出</a>
	</div>
	<%--<div class="toptitle">STQ</div>--%>
</div>

					