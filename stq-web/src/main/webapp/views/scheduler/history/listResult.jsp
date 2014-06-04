<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/ehr-tag.tld" prefix="ehr"%>
<div class="repeattable">
	<table id="studentExamList">
		<colgroup>
			
		</colgroup>
		<thead>
			<tr>
				<th>任务描述</th>
				<th>任务实例</th>
				<th>任务参数</th>
				<th>开始时间</th>
				<th>执行耗时(ms)</th>
				<th>异常消息</th>
				<th>异常详细</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="history" items="${histories}">
				<tr>
					<td><a href="#" onclick="taskHistorySearch.viewTask('${history.jobName}')"><ehr:tip>${history.jobDescription}</ehr:tip></a></td>
					<td><ehr:tip>${history.beanName}</ehr:tip></td>
					<td><ehr:tip>${history.param}</ehr:tip></td>
					<td><ehr:tip><fmt:formatDate value="${history.fireTime}" pattern="yyyy/MM/dd HH:mm:ss" /></ehr:tip></td>
					<td><ehr:tip>${history.jobRunTime}</ehr:tip></td>
					<td style="color:red"><ehr:tip>${history.exceptionMessage}</ehr:tip></td>
					<td ><a title="点击查看详细" href="javascript:void(0)" onclick="taskHistorySearch.showDetailException(this)">${history.exceptionDetail}</a>  </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ehr:paging action="taskHistorySearch.search"></ehr:paging>
</div>