<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="repeattable">
	<table id="studentExamList">
		<colgroup>
			
		</colgroup>
		<thead>
			<tr>
				<th>任务描述</th>
				<th>任务实例</th>
				<th>执行状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="task" items="${tasks}">
				<tr>
					<td><a href="#" onclick="taskSearch.viewTask('${task.name}')">${task.description}</a></td>
					<td>${task.beanName}</td>
					<td>
						<c:forEach items="${task.triggers}" var="trigger">
							<c:choose>
								<c:when test="${trigger.status eq 0}"> <span style="color:#009100">正常</span></c:when>
								<c:when test="${trigger.status eq 1}"> <span style="color:#C4C400">暂停</span> </c:when>
								<c:when test="${trigger.status eq 2}"> <span style="color:#009100">完成</span></c:when>
								<c:when test="${trigger.status eq 3}"> <span style="color:red">错误</span> </c:when>
								<c:when test="${trigger.status eq 4}"> <span style="color:red">阻塞</span> </c:when>
								<c:when test="${trigger.status eq -1}">  </c:when>
								<c:otherwise>${trigger.status}</c:otherwise>
							</c:choose>
						</c:forEach>
					</td>
					<td style="text-align: center">
						<a href="javascript:void(0)" onclick="taskSearch.runTask('${task.name}')">立即执行</a>
						<a href="javascript:void(0)" onclick="taskSearch.pauseTask('${task.name}')">暂停 </a>
					 	<a href="javascript:void(0)" onclick="taskSearch.resumeTask('${task.name}')" >恢复</a>
						<a href="javascript:void(0)" onclick="taskSearch.editTask('${task.name}')">修改</a>
						<a href="javascript:void(0)" onclick="taskSearch.removeTask('${task.name}')">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>