<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>

	<form id="scheduling-task-view-form">
		<div class="postcontent">
			<c:choose>
		<c:when test="${not empty task}">
			<div class="postdiv">
				<fieldset>
					<legend>任务信息</legend>
					<table class="formtable">
						<tr>
							<th><label>任务Id</label></th>
							<td><input value="${task.name }" readonly="readonly" type="text" name="taskName" /></td>
							<th><label>任务描述</label></th>
							<td><input value="${task.description}" readonly="readonly" type="text" name="taskDescription" /></td>
						</tr>
						<tr>
							<th><label>任务实例</label></th>
							<td colspan="1" ><input value="${task.beanName }" readonly="readonly" type="text"  />
							
							</td>
							<th><label>是否有效</label></th>
							<td colspan="1">
								<c:choose>
									<c:when test="${isRunable==true}">实例有效</c:when>
									<c:otherwise><span style="color:#ff0000">实例无效,请检查是否存在</span></c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th><label>任务参数</label></th>
							<td colspan="3"><textarea readonly="readonly">${task.param}</textarea> </td>
						</tr>
					</table>
				</fieldset>
			</div>
			<div class="postdiv">
				<fieldset>
					<legend>执行计划</legend>
					<div class="toolbar" style="text-align: left;">
						<a href="javascript:void(0)" onclick="viewTask.runTask('${task.name}')"><b class="">立即执行</b></a> <a href="javascript:void(0)"
							onclick="viewTask.resumeTask('${task.name}')"
						><b class="">恢复任务</b></a> <a href="javascript:void(0)" onclick="viewTask.pauseTask('${task.name}')"><b class="">暂停任务</b></a> <a
							style="float: right;" href="javascript:void(0)" onclick="viewTask.addTrigger('${task.name}')"
						><b class="xinz">增加执行计划</b></a>
						
						<a
							style="float: right;" href="javascript:void(0)" onclick="viewTask.refreshTriggers()"
						><b class="">刷新</b></a>
						
					</div>
					<div id="scheduling-task-view-trigger-con" class="repeattable">
						<jsp:include page="taskTriggersResult.jsp"></jsp:include>
					</div>
				</fieldset>
			</div>
			</c:when>
			<c:otherwise>
			<div style="color:red">
				该任务已经被删除!
			</div>
				
			</c:otherwise>
	</c:choose>
		</div>
	</form>
</div>
<script type="text/javascript">
	var viewTask = (function() {

		function refreshTriggers() {
			var searchObj = {
				url : "/task/getTaskTriggers",
				insertDiv : "scheduling-task-view-trigger-con"
			};
			$("#scheduling-task-view-form").submitFormLoadHtml(searchObj);
		}

		function sendCommond(url, param, message, callback) {
			var p = param || {};
			var option = {
				url : url,
				param : p,
				callback : function(data) {
					if (true == data)
					{
						msgUtil.alert(message+"成功", function() {
							if (callback)
							{
								callback();
							}
						});
					} else
					{
						msgUtil.alert(message+"失败");
					}
				}
			};
			$.getJsonByUrl(option);
		}

		function pauseTask(taskName) {
			sendCommond( "/task/pause",{
				taskName : taskName
			},"暂停",refreshTriggers);
			
		}

		function resumeTask(taskName) {
			sendCommond( "/task/resume",{
				taskName : taskName
			},"恢复",refreshTriggers);
		}

		function runTask(taskName) {
			sendCommond( "/task/run",{
				taskName : taskName
			},"执行",refreshTriggers);
		
		}
		
		function pauseTrigger(triggerName) {
			sendCommond( "/task/pauseTrigger",{
				triggerName : triggerName
			},"暂停",refreshTriggers);
			
		}

		function resumeTrigger(triggerName) {
			sendCommond( "/task/resumeTrigger",{
				triggerName : triggerName
			},"恢复",refreshTriggers);
		}
		
		function removeTrigger(triggerName) {
			msgUtil.confirm("确定删除吗?",function(){
				sendCommond( "/task/removeTrigger",{
					triggerName : triggerName
				},"删除",refreshTriggers);
			});
		
		}

		function addTrigger(taskName) {
			var dialogParams = {
				id : "scheduling-trigger-dia",
				url : "/task/addTrigger",
				height : 300,
				width : 550,
				param : {
					taskName : taskName
				},
				title : "添加执行计划",
			};
			$.dialog(dialogParams);
		}

		return {
			pauseTask : pauseTask,
			resumeTask : resumeTask,
			runTask : runTask,
			addTrigger : addTrigger,
			refreshTriggers : refreshTriggers,
			pauseTrigger:pauseTrigger,
			resumeTrigger:resumeTrigger,
			removeTrigger:removeTrigger
		};

	})();
</script>