<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="section">
	<div class="repeattable">
		<c:choose>
			<c:when test="${not empty scheduler }">
				<div class="toolbar">
					<a onclick="schedulerIndex.start();" style="float: left" href="javascript:void(0)" id="startAdd"><b class="xinz">启动</b></a>
					 <a  onclick="schedulerIndex.stop()" style="float: left" href="javascript:void(0)" id="stopAdd"
					><b class="xinz">停止</b></a>
				</div>
				<table>
					<tr>
						<td>任务管理id</td>
						<td>${scheduler.schedulerName}</td>
					</tr>
					<tr>
						<td>启动时间</td>
						<td><fmt:formatDate value="${scheduler.runningSince}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
					</tr>
					<tr>
						<td>任务执行数量</td>
						<td>${scheduler.numJobsExecuted}</td>
					</tr>
					<tr>
						<td>状态</td>
						<td><c:choose>
								<c:when test="${scheduler.state eq 1 }">
									<span style="color:#009100">运行中</span>
								</c:when>
								<c:otherwise>
									<span style="color:red">停止中</span>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<span style="color: red">任务管理未配置,请配置后重启再试</span>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<script type="text/javascript">
	var schedulerIndex = (function() {
		function sendCommond(url, param, message, callback) {
			var p = param || {};
			var option = {
				url : url,
				param : p,
				callback : function(data) {
					if (true == data)
					{
						msgUtil.alert(message + "成功", function() {
							if (callback)
							{
								callback();
							}
						});
					} else
					{
						msgUtil.alert(message + "失败");
					}
				}
			};
			$.getJsonByUrl(option);
		}

		function start(taskName) {
				sendCommond("/task/scheduler/start", {}, "启动", refresh);
			
		}

		function stop(taskName) {
			msgUtil.confirm("停止后,所有任务将被暂停,确定停止?",function(){
				sendCommond("/task/scheduler/stop", {}, "停止", refresh);
			});
		}

		function refresh() {
			baseLayoutLoad.loadMenuContent(contextPath + '/task/scheduler/index');
		}

		return {
			start : start,
			stop : stop
		}
	})();
</script>