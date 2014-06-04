<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<div class="section">
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
						<td style="text-align: left;"><input class="hide"  /><input type="button" id="btnSearch" value="查询" class="search_btn" /></td>
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
</div>
<script type="text/javascript">
	var taskHistorySearch = (function() {
		$(function() {
			$("#searchForm").onEnter(search, 1);
			$("#btnSearch").click(function() {
				search(1);
			});
			search(1);
		});

		var currentIndexPage = 1;

		function search(indexPage) {
			currentIndexPage = indexPage;
			var searchObj = {
				url : "/task/history/listResult",
				insertDiv : "listDiv",
				param : {
					indexPage : indexPage
				}
			};
			$("#searchForm").submitFormLoadHtml(searchObj);
		}

		function refresh() {
			search(currentIndexPage);
		}
		
		function showDetailException(target){
			var $a=$(target);
			var message=$a.text();
			msgUtil.alert(message);
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
			$.dialog(dialogParams);
		}

		return {
			search : search,
			refresh : refresh,
			showDetailException:showDetailException,
			viewTask:viewTask
		};
	})();
</script>