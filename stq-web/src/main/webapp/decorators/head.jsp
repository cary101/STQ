<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
    var ctx = "${ctx}";
</script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>STQ工作平台</title>
        <link rel="stylesheet" type="text/css" href="${ctx}/resources/easyui/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="${ctx}/resources/easyui/themes/icon.css">
        <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/main.css">

        <script type="text/javascript" src="${ctx}/resources/easyui/jquery-1.8.0.min.js"></script>
        <script type="text/javascript" src="${ctx}/resources/easyui/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="${ctx}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="${ctx}/resources/easyui/syUtil.js"></script>

        <script type="text/javascript">
        	$.parser.onComplete = function(){
            	$('body').css('visibility','visible');
            	setTimeout(function(){
	            	$('#loading-mask').remove();
            	},50);
        	};
        	$(function(){
            	$(window).resize(function(){
                	$('#mainlayout').layout('resize');
            	});
        	});
        </script>
