<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<style type="text/css">
	.error {
		color:red;
		font-size:12px;
	}
</style>
<script type="text/javascript">
	$(function(){
		//页面加载完毕后给id="code"的文本框绑定失去焦点事件
		$("#code").blur(function(){
			
			var code = $.trim(this.value);//取得编码位置输入的值
			//alert(code);
			if(code==""){
				//提示错误信息
				$("#codeErrorMsg").text("字型编码不能为空");
			}else {
				//不为空,继续判断是否含有特殊符号
				/*
					JS的两种创建正则表达式对象的方式:
						var regExp = new RegExp("正则表达式") //JS内置对象RegExp
						var regExp = /正则表达式/  //常用
					
					常见政策表达式符号:
						\s 表示空白
						[0-9]表示0-9任意一个数字
						[0-9]{2} 表示0-9任意两个数字
						[0-9]{2,} 表示0-9至少两个数字
						[0-9]{2,4} 表示0-9数字出现次数为：[2到4次]
						[0-9]* 表示0-9数字出现次数为：0--N次
						[0-9]+ 表示0-9数字出现次数为：1--N次
						[0-9]? 表示0-9数字出现次数为：0或1次
						[a-zA-Z0-9]{3} 
						
						^ 表示字符串开始
						$ 表示字符串结束
				*/
				var regExp = /^[a-zA-Z0-9]+$/
				// 正则表达式对象有test()方法，专门用来测试字符串和正则表达式是否可以正常匹配
				var ok = regExp.test(code);
				if(ok){
					// 不为空，并且不含有特殊符号，继续判断它的唯一性：这个需要发送ajax请求
					$.ajax({
						type : "get",
						url : "settings/dictionary/type/checkCodeUnique.do",
						cache : false, //自动添加时间戳,解决浏览器对于get方法的缓存问题
						data : {
							"code" : code
						},
						success: function(json){
							//{"success" : true} 表示可以使用
							//{"success" : false} 表示不能使用(已存在)
							if(json.success){
								$("#codeErrorMsg").text("");
							}else{
								$("#codeErrorMsg").text("字典类型编码已存在!");
							}
						}
					});
				}else {
					$("#codeErrorMsg").text("字典类型编码只能由数字和字母组成!");
				}
			}
				
		});
		
		//绑定获取焦点事件
		$("#code").focus(function(){
			if($("#codeErrorMsg").text() != ""){
				$("#code").val("");
			}
			$("#codeErrorMsg").text("");
		});
		
		$("#saveBtn").click(function(){
			// 手动触发blur事件
			$("#code").blur(); //blur事件一旦发生之后，就会发送ajax，这个ajax设置为同步之后，浏览器被锁住。直到该ajax请求结束，这里代码才能继续往下执行。
			if($("#codeErrorMsg").text() == ""){
				$("#dicTypeForm").submit();
			}
		});
		
	})

</script>
</head>
<body>

	<div style="position:  relative; left: 30px;">
		<h3>新增字典类型</h3>
	  	<div style="position: relative; top: -40px; left: 70%;">
			<button type="button" class="btn btn-primary" id="saveBtn">保存</button>
			<button type="button" class="btn btn-default" onclick="window.history.back();">取消</button>
		</div>
		<hr style="position: relative; top: -40px;">
	</div>
	<form id="dicTypeForm" action="settings/dictionary/type/save.do" method=post class="form-horizontal" role="form">
					
		<div class="form-group">
			<label for="create-code" class="col-sm-2 control-label">编码<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="code" name="code" style="width: 200%;" placeholder="编码作为主键，不能是中文">
				<span id="codeErrorMsg" class="error"></span>
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-name" class="col-sm-2 control-label">名称</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="name" name="name" style="width: 200%;">
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-describe" class="col-sm-2 control-label">描述</label>
			<div class="col-sm-10" style="width: 300px;">
				<textarea class="form-control" rows="3" id="description" name="description" style="width: 200%;"></textarea>
			</div>
		</div>
	</form>
	
	<div style="height: 200px;"></div>
</body>
</html>