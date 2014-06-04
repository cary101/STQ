<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4>
	<a href="javascript:void(0)" onclick="$('#cronExpressionDesc').toggle()">查看cronExpression说明</a>
</h4>
<div class="hide" id="cronExpressionDesc">
	<p>
		<strong>0 0 12 * * ? * 每一位的含义为 秒 分 时 天 月 周 [年],使用空格分割。详细如下表</strong>
	</p>
	<br />
	<table >
		<tbody>
			<tr>
				<td><strong>字段名</strong></td>
				<td><strong>必须有</strong><strong>?</strong></td>
				<td><strong>值范围</strong></td>
				<td><strong>允许的特殊字符</strong></td>
			</tr>
			<tr>
				<td>秒</td>
				<td>YES</td>
				<td>0-59</td>
				<td>, – * /</td>
			</tr>
			<tr>
				<td>分</td>
				<td>YES</td>
				<td>0-59</td>
				<td>, – * /</td>
			</tr>
			<tr>
				<td>时</td>
				<td>YES</td>
				<td>0-23</td>
				<td>, – * /</td>
			</tr>
			<tr>
				<td>日</td>
				<td>YES</td>
				<td>1-31</td>
				<td>, – * ? / L W C</td>
			</tr>
			<tr>
				<td>月</td>
				<td>YES</td>
				<td>1-12 or JAN-DEC</td>
				<td>, – * /</td>
			</tr>
			<tr>
				<td>星期</td>
				<td>YES</td>
				<td>1-7 or SUN-SAT</td>
				<td>, – * ? / L C #</td>
			</tr>
			<tr>
				<td>年</td>
				<td>NO</td>
				<td>empty, 1970-2099</td>
				<td>, – * /<br />
				<br />
				</td>
			</tr>
		</tbody>
	</table>
	<ul>
		<li>* ：匹配该域的任意值；如*用在分所在的域，表示每分钟都会触发事件</li>
		<li>? ：匹配该域的任意值</li>
		<li>- ：匹配一个特定的范围值</li>
		<li>, ：匹配多个指定的值</li>
		<li>/ ：左边是开始触发时间，右边是每隔固定时间触发一次事件</li>
		<li>L ：last，最后的</li>
		<li>W ：weekday，工作日的意思</li>
		<li># ：用来指定每个月的第几个星期几</li>
	</ul>
	<strong>示例</strong>
	<ul>
		<li>"0 0 12 * * ?" 每天12:00触发事件</li>
		<li>"0 0/5 14 * * ?" 每天14点开始触发到14:59分结束的每5分钟触发一次事件</li>
		<li>"0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五10:15触发一次事件</li>
		<li>"0 15 10 ? * 6#3" 每月的第三个星期五10:15触发一次事件</li>
	</ul>
	<p></p>
</div>
