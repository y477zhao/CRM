<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	int i = 100;
	String username = "lisi";
%>
<html>
	<head>
		<title>test jsp</title>
		<style type="text/css">
			a {
				font-size: 12px;
				color: red;
			}
		</style>
		<script type="text/javascript">
			function sayHello(){
				var k = <%=i%>;
				<%--
				// alert(k);
				--%>
				alert("<%=username%>");
			}
			
			<%--
			function pageLoad(){
				<%
					int age = Integer.parseInt(request.getParameter("age"));
					if(age > 55){
				%>
					alert("老年人！");
				<%
					}
				%>
			}
			--%>
			
			function pageLoad(){
				<c:if test="${param.age > 55}">
				alert("老年人！!!!!!!!!!!!!!");
				</c:if>
			}
		</script>
	</head>
	<body onload="pageLoad();">
		<a href="javascript:void(0);" onclick="sayHello();">点击我执行一段JS代码，页面不跳转</a>
	</body>
</html>