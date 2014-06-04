<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table id="studentExamList">
	<colgroup>
	</colgroup>
	<thead>
		<tr>
			<th style="text-align: center;">开始时间</th>
			<th style="text-align: center;">上次执行时间</th>
			<th style="text-align: center;">下次执行时间</th>
			<th style="text-align: center;">cronExpression</th>
			<th style="text-align: center;">当前执行状态</th>
			<th style="text-align: center;">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="trigger" items="${taskTriggers}">
			<tr>
				<td><fmt:formatDate value="${trigger.startTime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
				<td><fmt:formatDate value="${trigger.previousFireTime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
				<td><fmt:formatDate value="${trigger.nextFireTime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
				<td>${trigger.cronExpression}</td>
				<td><c:choose>
						<c:when test="${trigger.status eq 0}"> <span style="color:#009100">正常</span></c:when>
								<c:when test="${trigger.status eq 1}"> <span style="color:#C4C400">暂停</span> </c:when>
								<c:when test="${trigger.status eq 2}"> <span style="color:#009100">完成</span></c:when>
								<c:when test="${trigger.status eq 3}"> <span style="color:red">错误</span> </c:when>
								<c:when test="${trigger.status eq 4}"> <span style="color:red">阻塞</span> </c:when>
								<c:when test="${trigger.status eq -1}">  </c:when>
								<c:otherwise>${trigger.status}</c:otherwise>
					</c:choose></td>
				<td style="text-align: center"><c:choose>
						<c:when test="${trigger.type eq 'CronTrigger'}">
							<a href="javascript:void(0)" onclick="viewTask.pauseTrigger('${trigger.name}')">暂停</a>
							<a href="javascript:void(0)" onclick="viewTask.resumeTrigger('${trigger.name}')">恢复</a>
							<a href="javascript:void(0)" onclick="viewTask.removeTrigger('${trigger.name}')">删除</a>
						</c:when>
						<c:otherwise>
							手动执行,等待自动结束
						</c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
