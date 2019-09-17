<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

<script type="text/javascript">
	
	
	//如果登录页不是顶层窗口，设置为顶层窗口
	if(window.top!=window){
		window.top.location=window.location;
	}

	
	$(function(){
		
		// 用户名文本框获得焦点
		$("#loginAct").focus();
		
		// 给登录按钮绑定click
		$("#loginBtn").click(function(){
			// 登录
			login();
		});
		
		// 回车键也登录
		// window是dom对象
		// $(window)是jquery对象
		$(window).keydown(function(e){ // e是一个变量，e代表当前发生的事件对象。
			// 任何一个键盘事件都有keyCode属性用来获取键值
			if(e.keyCode == 13){ // 键值必须记住两个：13(回车键)和27（ESC：退出）
				// 登录
				login();
			}
		});
	});
	
	// 登录
	function login(){
		// 发送ajax post请求登录验证。
		var loginAct = $.trim($("#loginAct").val());
		var loginPwd = $("#loginPwd").val();
		var flag = "0";
		// $("#flag").checked 这是错误的，为什么？$("#flag")是jquery对象，jquery对象没有checked属性。
		// 你可以这样：$("#flag")[0].checked  这是访问dom对象的checked属性。
		// $("#flag").prop("checked") 获取复选框的选中状态
		// $("#flag").prop("checked" , true) 设置复选框被选中
		// $("#flag").prop("checked" , false) 设置复选框取消选中
		if($("#flag").prop("checked")){ // prop()和attr()一样，都是用来设置或获取元素的属性值。
			flag = "1";
		}
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/login.do",
			data: {
				"loginAct" : loginAct,
				"loginPwd" : loginPwd,
				"flag" : flag
			},
			beforeSend : function(){
				if(loginPwd == "" || loginAct == ""){
					$("#tipMsg").text("用户名和密码不能为空！");
					return false;
				}
				return true;
			},
			success : function(json){
				// 返回什么json？
				// {"success" : true}
				// {"success" : false , "errorMsg" : "错误提示信息"}
				if(json.success){
					// 跳转到工作台
					window.location.href = "${pageContext.request.contextPath}/workbench/index.jsp";
				} else {
					// 失败之后显示失败的原因
					$("#tipMsg").text(json.errorMsg);
				}
				
			}
		});
	}
</script>

</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2017&nbsp;动力节点</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form action="workbench/index.html" class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input id="loginAct" class="form-control" type="text" placeholder="用户名">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input id="loginPwd" class="form-control" type="password" placeholder="密码">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						<label>
							<input type="checkbox" id="flag"> 十天内免登录
						</label>
						<span id="tipMsg" style="color: red;font-size: 12px;"></span>
					</div>
					<button type="button" id="loginBtn" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>