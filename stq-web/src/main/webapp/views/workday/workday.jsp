<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<title>工作日设定</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<link href="${ctx}/resources/css/activityHelper.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/resources/js/activityHelper.js" type="text/javascript"></script>
<script type="text/javascript">
var bRequireSubmit = false;	
var ctx = "${ctx}";
var changeMonth = ctx + "/workday/changeMonth";
var save = ctx + "/workday/saveMonth";
var workDaysDelArray = '';
$(document).ready(function (date) {
    var dateTemp = new Date();
    var currentDate = dateTemp.getFullYear() + "-" + (dateTemp.getMonth() + 1) + "-" + dateTemp.getDate();
    var activityHelper = new ActivityHelper({
        renderTo:'activity',
        loadDate:currentDate,
        yearRange:'2012-2030'

    });
    $("#yearDropDown").val(dateTemp.getFullYear());
    $("#monthDropDown").val(dateTemp.getMonth() + 1);
    var isSetted = $("#isSetted").val();
    var workDaysJson = $("#workDaysJson").val();
    if (workDaysJson != '') {
        colorful(eval(workDaysJson));
    }
});

function toWorkDay(date) {
    var year = $("#yearDropDown").val();
    var month = $("#monthDropDown").val();
    if (confirm("将" + year + "年" + month + "月" + date + "日改成工作日?")) {
        $("#" + date).attr("isworkday", "1");
//                $("#" + date).attr("ondblclick", "toHoliday(" + date + ")");
        $("#" + date).bind("dblclick", function toHoliday(date) {
            var year = $("#yearDropDown").val();
            var month = $("#monthDropDown").val();
            if (confirm("将" + year + "年" + month + "月" + date + "日改成非工作日?")) {
                $("#" + date).attr("isworkday", "0");
//                        $("#" + date).attr("ondblclick", "toWorkDay(" + date + ")");
                $("#" + date).removeClass("workDayTd");
                $("#" + date).addClass("holidayTd");
            }
        }, false);
        $("#" + date).removeClass("holidayTd");
        $("#" + date).addClass("workDayTd");
    }
}

function toHoliday(date) {
    var year = $("#yearDropDown").val();
    var month = $("#monthDropDown").val();
    if (confirm("将" + year + "年" + month + "月" + date + "日改成非工作日?")) {
        $("#" + date).attr("isworkday", "0");
//                $("#" + date).attr("ondblclick", "toWorkDay(" + date + ")");
        $("#" + date).bind("dblclick", function toWorkDay(date) {
            var year = $("#yearDropDown").val();
            var month = $("#monthDropDown").val();
            if (confirm("将" + year + "年" + month + "月" + date + "日改成工作日?")) {
                $("#" + date).attr("isworkday", "1");
//                        $("#" + date).attr("ondblclick", "toHoliday(" + date + ")");
                $("#" + date).removeClass("holidayTd");
                $("#" + date).addClass("workDayTd");
            }
        }, false);
        $("#" + date).removeClass("workDayTd");
        $("#" + date).addClass("holidayTd");
    }
}

function gotoMonth() {
    workDaysDelArray = '';
    var year = $("#yearDropDown").val();
    var month = $("#monthDropDown").val();
    if (month.length == "1") {
        month = "0" + month;
    }
    var loadDate = year + "-" + month + "-" + '01'
    $.post(changeMonth, {"dateStr":loadDate}, activeMonth, "json");
}
function preMonth() {
    workDaysDelArray = '';
    var year = $("#yearDropDown").val();
    var month = $("#monthDropDown").val();
    var preMonth = Number(month) - 1;
    if (preMonth == 0) {
        year = (Number(year) - 1).toString();
        month = "12";
    } else {
        if (preMonth < 10) {
            month = "0" + preMonth.toString();
        } else {
            month = preMonth.toString();
        }
    }
    var loadDate = year + "-" + month + "-" + '01';
    $.post(changeMonth, {"dateStr":loadDate}, activeMonth, "json");
}

function nextMonth() {
    workDaysDelArray = '';
    var year = $("#yearDropDown").val();
    var month = $("#monthDropDown").val();
    var nextMonth = Number(month) + 1;
    if (nextMonth == 13) {
        year = (Number(year) + 1).toString();
        month = "01";
    } else {
        if (nextMonth < 10) {
            month = "0" + nextMonth.toString();
        } else {
            month = nextMonth.toString();
        }
    }
    var loadDate = year + "-" + month + "-" + '01';

    $.post(changeMonth, {"dateStr":loadDate}, activeMonth, "json");
}
function activeMonth(result) {
    var isSetted = result.isSetted;
    var workDaysJson = result.workDaysJson;
    $("#isSetted").val("");
    $("#workDaysJson").val("");
    $("#isSetted").val(isSetted);
    $("#workDaysJson").val(workDaysJson);
    $("#activity").empty();
    var loadDate = result.loadDate;
    var activityHelper = new ActivityHelper({
        renderTo:'activity',
        loadDate:loadDate,
        yearRange:'2012-2030'
    });
    $("#yearDropDown").val(loadDate.substring(0, 4));
    var month = loadDate.substring(5, 7);

    if (month.indexOf("0") == 0) {
        month = month.substring(1, 2)
    }
    $("#monthDropDown").val(month);
    $("#month").val(loadDate.substring(0, 4) + "-" + loadDate.substring(5, 7));
//            editCtl();
    if (workDaysJson != '') {
        colorful(eval(workDaysJson));
    }
}

function colorful(workDaysObj) {
    for (var i = 0; i < workDaysObj.length; i++) {
        var isworkday = workDaysObj[i].isworkday;
        var id = workDaysObj[i].date;
        if (isworkday == "1") {
            $("#" + id).attr("isworkday", "1");
//                    $("#" + id).attr("ondblclick", "toHoliday(" + id + ")");
//                    $("#" + id).bind("dblclick", toHoliday(id), false);
            $("#" + id).addClass("workDayTd");

        } else {
            $("#" + id).attr("isworkday", "0");
//                    $("#" + id).attr("ondblclick", "toWorkDay(" + id + ")");
//                    $("#" + id).bind("dblclick", toWorkDay(id), false);
            $("#" + id).addClass("holidayTd");
        }
    }
}

function saveMonth() {
    var yearAndMonth = $("#month").val();
    var workDays = "[";
    $("[naturalday = 'Y']").each(function (index) {
        var workDay = "{";
        var date = $(this).attr("id");
        if (Number(date) < 10) {
            date = "0" + date;
        }
        workDay = workDay + "date:\'" + date + "\'," + "isworkday:\'" + $(this).attr("isworkday") + "\'},"
        workDays = workDays + workDay;
    });
    workDays = workDays.substring(0, workDays.length - 1) + "]";
    var isSetted = $("#isSetted").val();
    $.post(save, {"month":yearAndMonth, "isSetted":isSetted, "workDaysJson":workDays}, saveResult, "json");
}

function saveResult(result) {
    if (result.code == 0) {
        alert("设定失败");
    } else {
        var isSetted = $("#isSetted").val("1");
        bRequireSubmit = false;
        alert("设定成功");
    }
}

//        function editCtl(){
//            var btn = $("#saveBtn");
//            var currentDate = new Date();
//            var currentMonth = currentDate.getFullYear().toString() + formatTo2((currentDate.getMonth() + 1).toString());
//            var pageMonth = getPageMonth();
//            if(Number(currentMonth) > Number(pageMonth)){
//                btn.remove();
//                $("[naturalday = 'Y']").each(function(index){
//                    $(this).attr("ondblclick", "");
//                });
//            }
//        }

function getPageMonth() {
    var year = $("#yearDropDown").val();
    var month = $("#monthDropDown").val();
    return year + formatTo2(month);
}

function formatTo2(data) {
    if (data.length == 1) {
        data = "0" + data;
    }
    return data.toString();
}

function formatTo1(data) {
    if (Number(data) < 10) {
        data = data.remove("0");
    }
    return data.toString();
}

function changeWorkDay(td) {
    var year = $("#yearDropDown").val();
    var month = $("#monthDropDown").val();
    var date = td.id;
    var workDaysDel = $("#workDaysJson").val();
    if (workDaysDel != '' && workDaysDelArray == '') {
        workDaysDelArray = eval(workDaysDel);
    }
    if (workDaysDelArray != '') {
        if ("1" == workDaysDelArray[date - 1].isworkday) {
            if (confirm("将" + year + "年" + month + "月" + date + "日改成非工作日?")) {
            	bRequireSubmit = true;
                $("#" + date).attr("isworkday", "0");
                workDaysDelArray[date - 1].isworkday = "0";
                $("#" + date).removeClass("workDayTd");
                $("#" + date).addClass("holidayTd");
            }
        } else {
            if (confirm("将" + year + "年" + month + "月" + date + "日改成工作日?")) {
            	bRequireSubmit = true;
                $("#" + date).attr("isworkday", "1");
                workDaysDelArray[date - 1].isworkday = "1";
                $("#" + date).removeClass("holidayTd");
                $("#" + date).addClass("workDayTd");
            }
        }
    }
}

function changeWorkDayD(td){
    var year = $("#yearDropDown").val();
    var month = $("#monthDropDown").val();
    var date = td.id;
    var isworkday = $(td).attr("isworkday");
    if ("1" == isworkday) {
        if (confirm("将" + year + "年" + month + "月" + date + "日改成非工作日?")) {
        	bRequireSubmit = true;
            $("#" + date).attr("isworkday", "0");
            $(td).attr("isworkday", "0");
            $("#" + date).removeClass("workDayTd");
            $("#" + date).addClass("holidayTd");
        }
    } else {
        if (confirm("将" + year + "年" + month + "月" + date + "日改成工作日?")) {
        	bRequireSubmit = true;
            $("#" + date).attr("isworkday", "1");
            $(td).attr("isworkday", "1");
            $("#" + date).removeClass("holidayTd");
            $("#" + date).addClass("workDayTd");
        }
    }
}
	window.onbeforeunload = function (oEvent) {
	
		if (bRequireSubmit){
			bRequireSubmit = false;
			if(!oEvent) oEvent = window.event;
		    oEvent.returnValue = "所有未提交的数据将丢失。";
		}
	};
	document.onkeydown = function (e) { 
		var theEvent = window.event || e; 
		var code = theEvent.keyCode || theEvent.which; 
		if (code == 13) { 
        	$('#saveBtn').focus();			
			$('#saveBtn').click();
			theEvent.keyCode = 0;
			return false;
		}
	}; 	
</script>
</head>
<body onselectstart="return false" style="-moz-user-select: none;padding: 30px;">
<input id="month" type="hidden" value="${month}">
<input id="isSetted" type="hidden" value="${isSetted}">
<input id="workDaysJson" type="hidden" value='${workDaysJson}'>

<%--<div id="activity" style="margin-top: 30px;margin-left: 40px;;margin-right: 40px;">--%>
<div id="warp"  style="width:700px;padding: 30px;padding-top: 0">
<div id="activity"></div>
</div>

</body>
</html>