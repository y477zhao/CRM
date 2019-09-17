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
		color: red;
		font-size: 12px;
	}
</style>

<script type="text/javascript">
	
	// $(function(){});
	
	// $(document).ready(function(){});
	
	/*
	function callback(){
	}
	
	$(callback);
	*/
	
	$(function(){
		
		// $("#code").blur(); // 触发失去焦点事件（失去焦点事件发生了）
		
		// 页面加载完毕之后给id="code"的文本框绑定失去焦点事件
		$("#code").blur(function(){
			// 失去焦点之后执行回调函数。
			// this 是dom对象，不是jquery对象。
			// $(this) 是jquery对象（jquery对象和dom对象的方法不能交叉使用，各自使用各自的。）
			// jquery对象转换成dom对象：$(this)[0] 或者 $(this).get(0)
			// var code = this.value;
			// var code = $(this).val();
			var code = $.trim(this.value);
			// if(code.length == 0){} // 说明字符串是空字符串，不是null，是""
			// 一般情况下，js中判断字符串不会调用length方法。
			if(code == ""){
				// 提示错误信息
				$("#codeErrorMsg").text("字典类型编码不能为空！");
			} else {
				// 不为空，继续判断是否含有特殊符号
				/*
					正则表达式：
						1、在JS中怎么创建正则表达式对象？
							第一种方式：var regExp = new RegExp("正则表达式");  在JS中有一个内置的对象，叫做:RegExp
							第二种方式：var regExp = /正则表达式/; 这种方式比较常用，因为代码少。
						2、不要求会复杂的正则表达式，简单的还是要会的。常见的正则表达式符号有哪些？
							\s	表示空白
							
							[0-9] 表示0-9任意一个数字
							[0-9]{2} 表示0-9任意两个数字
							[0-9]{2,} 表示0-9至少两个数字
							[0-9]{2,4} 表示0-9数字出现次数为：[2到4次]
							[0-9]* 表示0-9数字出现次数为：0-N次
							[0-9]+ 表示0-9数字出现次数为：1-N次
							[0-9]? 表示0-9数字出现次数为：0或1次
							[a-zA-Z0-9]{3} 
							
							^ 表示字符串开始
							$ 表示字符串结束
				*/
				var regExp = /^[a-zA-Z0-9]+$/;
				// 正则表达式对象有test()方法，专门用来测试字符串和正则表达式是否可以正常匹配。
				var ok = regExp.test(code);
				if(ok){
					// 不为空，并且不含有特殊符号，继续判断它的唯一性：这个需要发送ajax请求。
					/*
						$.ajax({
							type : "get",
							url : "url",
							data : {
								"name" : value,
								"name1" : value1
							},
							success : function(json){
								// 响应成功之后的回调函数。
							},
							dataType : "json"
						});
					
						$.get(url,data,callback,dataType);	基于$.ajax实现的 
						$.post(url,data,callback,dataType); 基于$.ajax实现的
						
						$.getJSON(url,data,callback); 基于$.get实现的。
					*/
					/*
					$.ajax({
						type : "get",
						url : "settings/dictionary/type/checkCodeUnique.do",
						data : {
							"code" : code,
							"t" : new Date().getTime()  // 时间戳不是所有的get请求都需要添加的，但这里由于每一次都需要连接数据库，所以必须添加时间戳。
						}, 
						success : function(json){
							// {"success" : true}  表示可以使用
							// {"success" : false} 表示不能使用(已存在)
							if(json.success){
								$("#codeErrorMsg").text("");
							}else{
								$("#codeErrorMsg").text("字典类型编码已存在！");
							}
						},
						dataType : "json"
					});
					*/
					
					$.ajax({
						type : "get",
						url : "settings/dictionary/type/checkCodeUnique.do",
						cache : false,  // 自动添加时间戳。
						async : false, // 同步ajax。
						data : {
							"code" : code
						}, 
						success : function(json){
							// {"success" : true}  表示可以使用
							// {"success" : false} 表示不能使用(已存在)
							if(json.success){
								$("#codeErrorMsg").text("");
							}else{
								$("#codeErrorMsg").text("字典类型编码已存在！");
							}
						}
					});
				} else {
					$("#codeErrorMsg").text("字典类型编码只能由数字和字母组成！");
				}
			}
		});
		
		// 绑定获取焦点事件
		$("#code").focus(function(){
			if($("#codeErrorMsg").text() != ""){
				$(this).val("");	
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
		
	});
	
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
	
	<form id="dicTypeForm" action="settings/dictionary/type/save.do" method="post" class="form-horizontal" role="form">
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