<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<link href="${pageContext.request.contextPath}/js/fineuploader/fineuploader-3.4.1.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fineuploader/jquery.fineuploader-3.4.1.js"></script>
<div class="section">
	<div class="toolbar">
		<a href="javascript:void(0)" id="btnAdd"><b class="xinz">新增</b></a> <a href="javascript:void(0)" id="btnExport"><b class="export">导出</b></a> <a
			href="javascript:void(0)" style="position: relative; width: 50px;" id="btnImport"
		><b class="import">导入</b></a>
	</div>
	<div class="searchbox">
		<form id="searchForm">
			<table id="searchTable">
				<colgroup>
					<col style="width: 10%">
					<col style="width: 25%">
					<col style="width: 8%">
					<col style="width: 23%">
					<col style="width: 10%">
					<col style="width: 23%">
				</colgroup>
				<tbody>
					<tr>
						<td class="coltext">任务描述</td>
						<td class="colinput"><input type="text" name="taskDescription" style="width: 150px;" /></td>
						<td></td>
						<td></td>
						<td style="text-align: left;"><input class="hide" /><input type="button" id="btnSearch" value="查询" class="search_btn" /></td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<table>
				<tr>
					<td class="colbottom"><span class="ico-bottom" onclick="util.toggle(this,'searchTable')">&nbsp;</span></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="listDiv"></div>
    <div id="popupApp"></div>
</div>
<script type="text/javascript">
	var taskSearch = (function() {
		$(function() {
//			$("#searchForm").onEnter(search, 1);
			$("#btnSearch").click(function() {
				search(1);
			});
			$("#btnAdd").click(function() {
				addTask();
			});
			$("#btnExport").click(exportList);
			$("#btnImport").click(importList);
//			search(1);
		});

		var currentIndexPage = 1;

		function search(indexPage) {
			currentIndexPage = indexPage;
			var searchObj = {
				url : "/task/listResult",
				insertDiv : "listDiv",
				param : {
					indexPage : indexPage
				}
			};
//			$("#searchForm").submitFormLoadHtml(searchObj);
		}

		function refresh() {
			search(currentIndexPage);
		}

		function addTask() {
			var dialogParams = {
				id : "d1",
				url : "/task/add",
				height : 380,
				width : 550,
				title : "增加任务"
			};
//			$.dialog(dialogParams);
            $('#popupApp').dialog('open').dialog('refresh', '${pageContext.request.contextPath}'+'/task/add?id=d1' );
		}

		function viewTask(taskName) {
			var dialogParams = {
				id : "d1",
				url : "/task/getTask",
				height : 500,
				width : 850,
				title : "查看任务",
				param : {
					taskName : taskName
				}
			};
//			$.dialog(dialogParams);
		}

		function runTask(taskName) {
			var option = {
				url : "/task/run",
				param : {
					taskName : taskName
				},
				callback : function(data) {
					if (true == data)
					{
						msgUtil.alert("执行成功", function() {
							refresh();
						});
					} else
					{
						msgUtil.alert("执行失败");
					}
				}
			};
			$.getJsonByUrl(option);
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
			},"暂停",refresh);
			
		}

		function resumeTask(taskName) {
			sendCommond( "/task/resume",{
				taskName : taskName
			},"恢复",refresh);
		}

		function editTask(taskName) {
			var dialogParams = {
				id : "d1",
				url : "/task/editTask",
				height : 340,
				width : 550,
				param : {
					taskName : taskName
				},
				title : "修改任务"
			};
//			$.dialog(dialogParams);
		}

		function removeTask(jobName) {
			msgUtil.confirm("确定删除吗？", function() {
				var option = {
					url : "/task/remove",
					param : {
						jobName : jobName
					},
					callback : function(data) {
						if (true == data)
						{
							msgUtil.alert("删除成功", function() {
								refresh();
							});
						} else
						{
							msgUtil.alert("删除失败");
						}
					}
				};
				$.getJsonByUrl(option);
			});
		}
		
		function exportList(){
			window.open(contextPath+"/task/export");
		}
		
		function importList(){
            var dialogParams = {
                id : "scheduling-task-import-dia",
                url : "/task/importPage",
                height : 200,
                width : 400,
                title : "导入任务"
            };
//            $.dialog(dialogParams);
		}

		return {
			search : search,
			runTask : runTask,
			removeTask : removeTask,
			viewTask : viewTask,
			editTask : editTask,
			refresh : refresh,
			pauseTask : pauseTask,
			resumeTask : resumeTask

		};
	})();
</script>