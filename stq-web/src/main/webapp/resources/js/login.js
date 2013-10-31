$(function () {

    $("#loginId").focus(function () {
        if ($("#loginId").val() == "邮箱/手机/展位号") {
            $("#loginId").val("");
        }
    });

    $("#login").submit(function () {
        if ($.trim($("#loginId").val()) == "") {
            $("#loginT").html("账号不能为空！");
            return false;
        }
        if ($.trim($("#loginId").val()) == "邮箱/手机/展位号") {
            $("#loginT").html("账号不能为空！");
            return false;
        }
        if ($.trim($("#password").val()) == "") {
            $("#passwordT").html("密码不能为空！");
            return false;
        }

    });
});
function usernameChk() {
    if ($.trim($("#loginId").val()) == "") {
        $("#loginT").html("账号不能为空！");
        return;
    }
    else if ($.trim($("#loginId").val()) == "邮箱/手机/展位号") {
        $("#loginT").html("账号不能为空！");
        return;
    }
    else {
        $("#loginT").html("");
    }
}
function passwordChk() {
    if ($.trim($("#password").val()) == "") {
        $("#passwordT").html("密码不能为空！");
        return false;
    }
    else {
        $("#passwordT").html("");
    }
}