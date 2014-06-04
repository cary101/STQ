<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="toolbar">
		<a href="javascript:void(0)" id="scheduling-task-update-btn"><b class="baocun">保存</b></a>
	</div>
	<form id="scheduling-task-edit-form">
		<input type="hidden" value="${task.name}" name="taskName">
		<div class="postcontent">
			<div class="postdiv">
				<fieldset>
					<legend>任务信息</legend>
					<table class="formtable">
						<tr>
							<th><label class="required">任务实例</label></th>
							<td><select style="min-width: 180px" reg='{"required":"true","maxlength":"60"}' name="taskBeanName">
									<option value="">请选择</option>
									<c:forEach items="${taskNames}" var="name">
										<option value="${name}" ${task.beanName eq name ?'selected':'' } data-cron="${taskDeatils[name].cron}" data-description="${taskDeatils[name].description}">${name}${taskDeatils[name].description}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<th><label class="required">任务描述</label></th>
							<td><input reg='{"required":"true","maxlength":"80"}' value="${task.description }" type="text" name="taskDescription" /></td>
						</tr>
						<tr>
							<th><label>参数</label></th>
							<td><textarea rows="5" name="param" >${task.param}</textarea></td>
						</tr>
					</table>
				</fieldset>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
	!(function() {
		var validate;
		$(function() {
			validate = $("#scheduling-task-edit-form").easyValidate();
			$("#scheduling-task-update-btn").click(function() {
				var result = validate.validateForm();
				if (!result)
				{
					return;
				}

				$("#scheduling-task-edit-form").submitFormGetJson({
					url : "/task/updateTask",
					callback : submitCallback,
				});
			});

		});

		function submitCallback(data) {
			if(true==data){
				msgUtil.alert("保存成功", function() {
					$.removeDialog("d1");
					taskSearch.refresh();
				});
			}else{
				msgUtil.alert("保存失败");
			}
			
		}
	})();
</script>