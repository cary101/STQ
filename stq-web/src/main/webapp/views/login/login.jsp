<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>登录入口_STQ</title>
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/resources/easyui/jquery-1.8.0.min.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/resources/js/login.js"></script>
</head>

<body>
<div class="Area1" style="padding-top:40px; ">
    <!--头部logo开始-->
    <div class="Hr_10"></div>
    <div class="Logo L"><img src="${pageContext.request.contextPath}/resources/images/stq-logo.png" border="0"/></div>
    <div class="F20 Fw G9 L24 L" style="margin-top:20px; padding-left:20px;">专业的检测给您最好的服务</div>
    <div style="height: 10px"></div>
    <!--头部logo结束-->
</div>
<div class=" Cl Hr_10"></div>
<div class="Area1">
    <div class="LeftW L"><img src="${pageContext.request.contextPath}/resources/images/login.jpg" width="470" height="430" border="0"></div>
    <div class="MidLine L"></div>
    <div class="RightW R">
        <div style="height:25px;">&nbsp;</div>
        <div class="Login P10">
            <div class="Hr_10"></div>
            <div class=" F18 Fw G4 L30 Di" style=" padding-left:28px;">欢迎登录</div>
            <div class="Hr_20"></div>

            <form name="login" id="login" action="${pageContext.request.contextPath}/access/login" method="post">
                <div class="F14" style=" border-top:1px solid #d3d3d7; border-bottom:1px solid #d3d3d7">
                    <div class="Hr_20"></div>
                    <div class="L28 Tr L PLR5" style="width:70px; padding-top:5px;">账号</div>
                    <div class="L" style="width:225px;"><input type="text" class="BoxM5 F14 L35 Fa"  value="邮箱/手机/展位号" name="loginId" id="loginId" onblur="usernameChk()"/></div>
                    <div style="padding-left: 80px; display: inline;" id="loginT" class="F12 error"></div>
                    <div class="Hr_10"></div>
                    <div class="L28 Tr L PLR5" style="width:70px; padding-top:5px;">密码</div>
                    <div class="L" style="width:225px;"><input type="password" class="BoxM5 F14 L35"  name="password"  id="password" onblur="passwordChk()"/></div>
                    <div style="padding-left: 80px; display: inline;" id="passwordT" class="F12 error"></div>
                    <div class="Hr_10"></div>
                    <div class="L28 L" style="width:70px;">&nbsp;</div>
                    <div class="L28 L" style="width:225px; color: #ff0000"> ${message}
                        </div>
                    <div class="Hr_10"></div>
                    <div class="L28 L" style="width:70px;">&nbsp;</div>
                    <div class="L28 L" style="width:225px;">
                        <div class="L">
                        <span><input name="ISmemorize" type="checkbox" id="ISmemorize" value="1" checked="checked" />
                        <input name="IsPublicPC" type="hidden" id="IsPublicPC" value="1" /></span>
                            <span>保存密码</span>
                        </div>
                        <div class="L" style="padding-left:30px;"><input type="submit" name="Submit" value="" class="Menu mn1" style="border:none;cursor:pointer;"/><!--div class="Menu mn1 L" style="cursor:pointer;" id="divLogin" title="登录" ></div--></div>

                    </div>
                    <div class="Hr_10"></div>
                    <div class="L35 L Di" style=" padding-left:28px;"></div>
                    <div class="Hr_10"></div>
                </div>
                <input type="hidden" name="strURL" id="strURL" value="http://www.woyaoce.cn/member/T100186/" />
                <input name="LoginSource" type="hidden" id="LoginSource" value="18" />
                <input name="LoginInit" type="hidden" id="LoginInit" value="1" />
            </form>

            <div class="Hr_10"></div>
            <div class=" F14 G4 L28 PL">
                <div class="L Di" style="padding-left:28px;">忘记密码请联系管理员：abc@cary.com</div>
                <%--<div class="Menu mn2 R" style="cursor:pointer;" onclick="window.open('Register01.aspx?registerSource=0')" title="免费快速注册" ></div>--%>
            </div>
            <div class="Hr_10"></div>
            <div class="Cl"></div>
        </div>
    </div>
    <div class="Hr_20"></div>
    <!--全站底部文件调用-->
    <div class="Footer Tc">版权所有，未经书面授权，所有页面内容不得以任何形式进行复制 </div>
</div>
</body>
</html>
