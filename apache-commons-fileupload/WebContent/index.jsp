<%@page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
	<head>
		<title>fileup</title>
	</head>
	<body>
		
		<%--
			1、文件上传的控件是：type="file"
			2、文件上传必须是post请求，get请求只能提交字符串。
			3、文件上传的时候，form表单的enctype属性必须手动设置为：enctype="multipart/form-data"
			注意：
				enctype默认值是：application/x-www-form-urlencoded
				enctype还可以设置为：multipart/form-data
				以上为HTTP协议规定的，以普通表单元素的方式提交数据，请使用：application/x-www-form-urlencoded
				提交流媒体等数据的时候，必须使用：multipart/form-data
				我们平时所描述的 ： url?name=value&name=value&name=value，实际上是依赖enctype="application/x-www-form-urlencoded"
		--%>
		<form action="${pageContext.request.contextPath }/fileup.do" method="post" enctype="multipart/form-data">
			用户名<input type="text" name="username"><br> <%-- FileItem --%>
			文件1<input type="file" name="file1"><br><%-- FileItem --%>
			文件2<input type="file" name="file2"><br><%-- FileItem --%>
			<input type="submit" value="提交">
		</form>
		
	</body>
</html>





















