let nginx_url = "http://127.0.0.1:8080";

layui.use('form', function(){
	let form = layui.form;
});

$(function() {
	$("#loginButton").click(function() {
		let ident = $("#ident").val();
		alert(ident);
		let username = $("#username").val();
		alert(username);
		if (username === "") {
			alert("请输入用户名");
			return;
		} else if ($("#password").val() === "") {
			alert("请输入密码");
			return;
		} else if (ident === "") {
			alert("请选择你的身份");
			return;
		}
        $.ajax({
                    type: "post",
                    url: '/Login/checkLoginTest',
                    data: $('#loginForm').serialize(),
                    dataType: "json",
                    success: function (result) {
                    alert(result);
                    if(result==="-1")
                        alert("账号或密码错误")
                    else if(result=="0")
                        window.location.href = "/Admin/index";
                    else if(result=="1")
                        window.location.href = "/Teacher/index";
                    else if(result=="2")
                        window.location.href = "/Student/index";
                    else
                        window.location.href = "/Login/index";
                    },
                    error: function() {
                    console.log("error");
                    alert("登录失败，请稍后再试")
                    }
        });
	});
});



