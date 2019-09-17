<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<style type="text/css">
	.error {
		color: red;
		font-size: 12px;
	}
</style>

<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(function(){
		$("#value").blur(function(){
			// 字典类型编码是否为空
			var typeCode = $("#typeCode").val();
			if(typeCode == ""){
				$("#typeCodeError").text("字典类型编码不能为空！");
			}else{
				// 字典类型编码不为空，继续判断字典值是否为空！
				var value = $.trim(this.value);
				if(value == ""){
					$("#valueError").text("字典值不能为空！");	
				}else{
					// 字典类型编码和字典值都不为空！
					// 发送ajax get请求，验证在该“字典类型”下“此字典值”是否存在！
					$.ajax({
						type : "get",
						url : "${pageContext.request.contextPath}/settings/dictionary/value/checkValueUnique.do",
						cache : false,
						async : false,
						data : {
							"typeCode" : typeCode,
							"value" : value
						},
						success : function(json){
							// {"success" : true}
							// {"success" : false}
							if(json.success){
								$("#valueError").text("");	
							}else{
								$("#valueError").text("该字典值在此字典类型下已存在！");
							}
						}
					});
				}
			}
		});
		
		$("#typeCode").change(function(){
			// 下拉列表的value
			var typeCode = this.value;
			if(typeCode == ""){
				$("#typeCodeError").text("字典类型编码不能为空！");
			}else{
				$("#typeCodeError").text("");
			}
		});
		
		$("#value").focus(function(){
			if($("#valueError").text() != ""){
				$(this).val("");
			}
			$("#valueError").text("");	
		});
		
		$("#orderNo").blur(function(){
			var orderNo = this.value;
			if(orderNo != ""){
				// 必须是正整数
				var regExp = /^[1-9][0-9]*$/;
				var ok = regExp.test(orderNo);
				if(ok){
					$("#orderNoError").text("");	
				}else{
					$("#orderNoError").text("排序号必须是正整数！");
				}
			}
		});
		
		$("#orderNo").focus(function(){
			if($("#orderNoError").text() != ""){
				$(this).val("");
			}
			$("#orderNoError").text("");	
		});
		
		$("#saveBtn").click(function(){
			$("#value").blur();
			$("#orderNo").blur();
			if($("#typeCodeError").text() == "" && $("#valueError").text() == "" && $("#orderNoError").text() == ""){
				$("#dicValueForm").submit();
			}
		});
		
	});
</script>

</head>
<body>

	<div style="position:  relative; left: 30px;">
		<h3>新增字典值</h3>
	  	<div style="position: relative; top: -40px; left: 70%;">
			<button type="button" class="btn btn-primary" id="saveBtn">保存</button>
			<button type="button" class="btn btn-default" onclick="window.history.back();">取消</button>
		</div>
		<hr style="position: relative; top: -40px;">
	</div>
	<form id="dicValueForm" action="settings/dictionary/value/save.do" method="post"  class="form-horizontal" role="form">
					
		<div class="form-group">
			<label for="create-dicTypeCode" class="col-sm-2 control-label">字典类型编码<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<select class="form-control" id="typeCode" name="typeCode" style="width: 200%;">
				<option value=""></option>
				<c:forEach items="${dtList }" var="dt">
				<option value="${dt.code }">${dt.name }</option>
				</c:forEach>
				</select>
				<span id="typeCodeError" class="error"></span>
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-dicValue" class="col-sm-2 control-label">字典值<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="value" name="value" style="width: 200%;">
				<span id="valueError" class="error"></span>
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-text" class="col-sm-2 control-label">文本</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="text" name="text" style="width: 200%;">
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-orderNo" class="col-sm-2 control-label">排序号</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="orderNo" name="orderNo" style="width: 200%;">
				<span id="orderNoError" class="error"></span>
			</div>
		</div>
	</form>
	
	<div style="height: 200px;"></div>
</body>
</html>