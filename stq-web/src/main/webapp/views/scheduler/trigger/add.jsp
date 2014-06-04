<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<div>
	<div class="toolbar">
		<a href="javascript:void(0)" id="scheduling-trigger-add-btn"><b class="baocun">保存</b></a>
	</div>
	<form id="scheduling-trigger-form">
		<div class="postcontent">
			<div class="postdiv">
				<fieldset>
					<legend>执行计划</legend>
					<input type="hidden" name="taskName" value="${taskName}" />
					<table class="formtable">
						<colgroup>
							<col style="width: 30%">
							<col style="width: 70%">
						</colgroup>
						<tr>
							<th>开始时间</th>
							<td><tag:dateInput reg='{"compare":["scheduling-trigger-stopTime","le","开始时间不能大于结束时间"]}' id="scheduling-trigger-startTime" name="startTime" onlypast="false" pattern="yyyy/MM/dd HH:mm:ss"></tag:dateInput></td>
						</tr>
						<tr>
							<th>结束时间</th>
							<td><tag:dateInput reg='{"compare":["scheduling-trigger-startTime","ge","开始时间不能大于结束时间"]}' id="scheduling-trigger-stopTime" name="stopTime" onlypast="false" pattern="yyyy/MM/dd HH:mm:ss"></tag:dateInput></td>
						</tr>
						<tr>
							<th><label class="required">cronExpression</label></th>
							<td><input type="text" reg='{"required":"true","maxlength":"30","remote":"${pageContext.request.contextPath}/task/isValidCronExpression"}' name="cronExpression" value="${cronExpression}" /></td>
						</tr>
					</table>
						<jsp:include page="../task/cronExpression.jsp"></jsp:include>
				</fieldset>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
	!(function() {
		var validate;
		$(function() {
			validate = $("#scheduling-trigger-form").easyValidate();
			$("#scheduling-trigger-add-btn").click(function() {
				var result = validate.validateForm();
				if (!result)
				{
					return;
				}

				$("#scheduling-trigger-form").submitFormGetJson({
					url : "/task/saveTrigger",
					callback : submitCallback,
				});
			});

		});

		function submitCallback(data) {
			if (true == data)
			{
				msgUtil.alert("添加成功", function() {
					$.removeDialog("scheduling-trigger-dia");
					viewTask.refreshTriggers();
				});
			} else
			{
				msgUtil.alert("添加失败");
			}

		}

	})();
</script>