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
	$(function(){
		display();
		
		// 全选和取消全选
		$("#selectAll").click(function(){
			// $(":checkbox") // 获取当前页面中所有的checkbox复选框对象
			// $(":checkbox[name='id']") // 获取当前页面中所有name="code"的checkbox复选框对象
			// jquery支持批量操作（支持多个元素一块操作）
			$(":checkbox[name='code']").prop("checked" , this.checked);
			/*
			if(this.checked){
				$(":checkbox[name='id']").prop("checked" , true);
			} else {
				$(":checkbox[name='id']").prop("checked" , false);
			}
			*/
		});
		
		$("#deleteBtn").click(function(){
			if($(":checkbox[name='items']:checked").size()==0){
				alert("请选择要删除的数据!");
			}else{
				if(window.confirm("您确定要删除吗?")){
					var dataSent = "";
					$.each( $(":checkbox[name='items']:checked"), function(i, n){
						//n是其中一个复选框checkbox对象,并且是dom对象
						dataSent += "&code=" + n.value;
					});
					dataSent = dataSent.substr(1);
					$.post(
						"settings/dictionary/type/delete.do",
						dataSent,
						function(json){
							if(json.success){
								$("#selectAll").prop("checked", false);
								display();
							}else{
								alert("删除失败!");
								
							}
						}
					);
				}
				
			}
		});
		
		
	});
	function display(){
		$.ajax({
			type : "post",
			url : "settings/dictionary/type/list.do",
			cache : false,
			success : function(json){
				//alert(json.dataList);
				//返回一个json格式的Map
				var html = "";
				// {"dataList" : [{"id":"","name":"","owner":"","startDate":"","endDate":""},{}]}
				$.each(json.dataList, function(i,n){
					//alert(n.code);
					html += '<tr class="active">';
					html += '<td><input type="checkbox" name="items" value="'+n.code+'"/></td>';
                    html += '<td>'+(++i)+'</td>';
                    html += '<td>'+n.code+'</td>';
					html += '<td>'+n.name+'</td>';
					html += '<td>'+n.description+'</td>';
					html += '</tr>';
				});
				$("#dicTypeTbody").html(html);
				
			},
			
		});
	}
	
</script>
</head>
<body>

	<div>
		<div style="position: relative; left: 30px; top: -10px;">
			<div class="page-header">
				<h3>字典类型列表</h3>
			</div>
		</div>
	</div>
	<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;left: 30px;">
		<div class="btn-group" style="position: relative; top: 18%;">
		  <button type="button" class="btn btn-primary" onclick="window.location.href='settings/dictionary/type/save.jsp'"><span class="glyphicon glyphicon-plus"></span> 创建</button>
		  <button type="button" class="btn btn-default" onclick="window.location.href='settings/dictionary/type/edit.jsp'"><span class="glyphicon glyphicon-edit"></span> 编辑</button>
		  <button id="deleteBtn" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
		</div>
	</div>
	<div style="position: relative; left: 30px; top: 20px;">
		<table class="table table-hover">
			<thead>
				<tr style="color: #B3B3B3;">
					<td><input type="checkbox" id="selectAll" /></td>
					<td>序号</td>
					<td>编码</td>
					<td>名称</td>
					<td>描述</td>
				</tr>
			</thead>
			<tbody id="dicTypeTbody">
				<!--<tr class="active">
					<td><input type="checkbox" /></td>
					<td>1</td>
					<td>sex</td>
					<td>性别</td>
					<td>性别包括男和女</td> 
				</tr>-->
			</tbody>
		</table>
	</div>
	
</body>
</html>