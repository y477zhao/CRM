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
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

<%-- pagination --%>
<link rel="stylesheet" type="text/css" href="jquery/bs_pagination/css/jquery.bs_pagination.min.css">
<script type="text/javascript" src="jquery/bs_pagination/js/jquery.bs_pagination.min.js"></script>
<script type="text/javascript" src="jquery/bs_pagination/js/en.min.js"></script>

<script type="text/javascript">

	$(function(){
		
		// 开始日期和结束日期
		$(".time").datetimepicker({
			language : "zh-CN",
			format : "yyyy-mm-dd",  // 设置显示的日期格式
			autoclose : true, // 设置日历插件选择日期之后自动关闭
			minView : "month", // 显示的最小视图
			todayBtn : true,  // 设置是否显示“今天”按钮
			clearBtn : true
		});
		
		//定制字段
		$("#definedColumns > li").click(function(e) {
			//防止下拉菜单消失
	        e.stopPropagation();
	    });
		// 给id="createBtn"的创建按钮绑定click
		$("#createBtn").click(function(){
			// 重置form
			$("#activityForm")[0].reset();
			// 发送ajax get请求，查询所有的所有者
			$.get(
				"${pageContext.request.contextPath}/workbench/activity/create.do",
				{"_" : new Date().getTime()}, // 这里不能使用cache : false
				function(array){
					// [{"id":"5c85d86d239f4912a01527d8fa6e4770","name":"李四"},{"id":"a8d29c7423854eebb0f65b26ae17c7be","name":"张三"}]
					var html = "";
					$.each(array , function(i , n){
						// i是下标，n是数组中的每一个元素
						html += "<option value='"+n.id+"'>"+n.name+"</option>";
					});
					$("#owner").html(html);
					// 下拉列表定位(客户需求：当前登录的用户被默认选中。)
					// 下拉列表的选项怎么选中？设置下拉列表的value之后，该value被自动选中。
					$("#owner").val("${user.id}");
					// 显示modal窗口
					$("#createActivityModal").modal("show");
				}
			);
		});
		
		$("#saveBtn").click(function(){
			// 发送ajax post请求保存市场活动
			$.post(
				"${pageContext.request.contextPath}/workbench/activity/save.do",
				{
					"owner" : $.trim($("#owner").val()),
					"name" : $.trim($("#name").val()),
					"startDate" : $.trim($("#startDate").val()),
					"endDate" : $.trim($("#endDate").val()),
					"cost" : $.trim($("#cost").val()),
					"description" : $.trim($("#description").val())
				},
				function(json){
					// {"success" : true}
					// {"success" : false}
					if(json.success){
						$("#createActivityModal").modal("hide");
						// 分页查询第一页数据
						display(1 , $("#activityPagination").bs_pagination('getOption', 'rowsPerPage'));
					}else{
						alert("保存失败！");
					}
				}
			);
		});
		
		// 分页查询第一页数据
		// 实际开发中，每页显示的记录条数20条比较合适。
		display(1 , 2); // 显示第一页数据，每页显示2条。
		
		$("#queryBtn").click(function(){
			
			// 将4个条件放到隐藏域当中
			$("#h-name").val($.trim($("#p-name").val()));
			$("#h-owner").val($.trim($("#p-owner").val()));
			$("#h-startDate").val($.trim($("#p-startDate").val()));
			$("#h-endDate").val($.trim($("#p-endDate").val()));
			
			display(1 , $("#activityPagination").bs_pagination('getOption', 'rowsPerPage'));
		});
		
		// 全选和取消全选
		$("#firstChk").click(function(){
			// $(":checkbox") // 获取当前页面中所有的checkbox复选框对象
			// $(":checkbox[name='id']") // 获取当前页面中所有name="id"的checkbox复选框对象
			// jquery支持批量操作（支持多个元素一块操作）
			$(":checkbox[name='id']").prop("checked" , this.checked);
			/*
			if(this.checked){
				$(":checkbox[name='id']").prop("checked" , true);
			} else {
				$(":checkbox[name='id']").prop("checked" , false);
			}
			*/
		});
		
		// 这里无法绑定click，原因是：这里的代码在执行的时候，name='id'的checkbox在浏览器页面上还不存在呢。
		/*
		$(":checkbox[name='id']").click(function(){
			alert(111);
		});
		*/
		
		/*
			jquery中有一种非常重要的机制：给后期ajax动态生成的元素绑定事件
			语法格式：
				$(父元素选择器).on("事件" , "被绑定事件的元素的选择器" , 回调函数);
		
			on的实现原理是什么？
				on在运行的时候实际上相当于在浏览器内存中启动一个“看管”线程。
				一直处于运行状态，看管着"父元素"所属区域。
				直到有一个元素动态的添加到“父元素”区域当中，马上动态的给该元素绑定事件。
				父元素选择器范围越大，扫描越大，效率越低。
		*/
		/*
		$("body").on("click" , ":checkbox[name='id']" , function(){
			alert(333);
		});
		*/
		$("#activityTbody").on("click" , ":checkbox[name='id']" , function(){
			// 判断复选框的总数量和选中的总数量是否相等。
			/*
			var count = $(":checkbox[name='id']").size();
			var checkedCount = $(":checkbox[name='id']:checked").size();
			if(count == checkedCount){
				$("#firstChk").prop("checked" , true);
			} else {
				$("#firstChk").prop("checked" , false);
			}
			*/
			
			$("#firstChk").prop("checked" , $(":checkbox[name='id']").size() == $(":checkbox[name='id']:checked").size());
		});
		
		
		$("#delBtn").click(function(){
			if($(":checkbox[name='id']:checked").size() == 0){
				alert("选择要删除的数据！");
			}else{
				if(window.confirm("真的删除行么？")){
					var sendData = "";
					$.each($(":checkbox[name='id']:checked") , function(i , n){
						// n 是其中一个复选框checkbox对象，并且是dom对象
						// n.value
						sendData += "&id=" + n.value;
					});
					sendData = sendData.substr(1);
					// 发送ajax post请求删除
					$.post(
						"${pageContext.request.contextPath}/workbench/activity/delete.do",
						sendData,
						function(json){
							if(json.success){
								// 第一个复选框取消选中
								$("#firstChk").prop("checked" , false);
								// 分页查询第一页数据
								display(1 , $("#activityPagination").bs_pagination('getOption', 'rowsPerPage'));
							}else{
								alert("删除失败！");
							}
						}
					);
				}
			}
		});
		
		$("#editBtn").click(function(){
			if($(":checkbox[name='id']:checked").size() == 0){
				alert("请选择一条数据！");
			} else if ($(":checkbox[name='id']:checked").size() > 1){
				alert("一次只能修改一条数据！");
			} else {
				// 发送ajax get请求，提交市场活动的id
				$.get(
					"${pageContext.request.contextPath}/workbench/activity/edit.do",
					{
						"id" : $(":checkbox[name='id']:checked").val(),
						"_" : new Date().getTime()
					},
					function(json){
						/*
							{
								"userList" : [{"id":"","name":""},{"id":"","name":""}] , 
								"activity" : {"id":"","name":"","owner":"id形式","startDate":"","endDate":"","description":"","cost":""}
							}
						*/
						// 动态生成下拉列表
						var html = "";
						$.each(json.userList , function(i , n){
							html += "<option value='"+n.id+"'>"+n.name+"</option>";
						});
						$("#e-owner").html(html);
						
						// 设置文本框的value
						$("#e-id").val(json.activity.id);
						$("#e-name").val(json.activity.name);
						// 定位下拉列表
						$("#e-owner").val(json.activity.owner);
						$("#e-startDate").val(json.activity.startDate);
						$("#e-endDate").val(json.activity.endDate);
						$("#e-description").val(json.activity.description);
						$("#e-cost").val(json.activity.cost);
						
						// 展示modal窗口
						$("#editActivityModal").modal("show");
					}
				);
			}
		});
		
		$("#updateBtn").click(function(){
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/workbench/activity/update.do",
				data : {
					"id" : $.trim($("#e-id").val()),
					"owner" : $.trim($("#e-owner").val()),
					"name" : $.trim($("#e-name").val()),
					"startDate" : $.trim($("#e-startDate").val()),
					"endDate" : $.trim($("#e-endDate").val()),
					"cost" : $.trim($("#e-cost").val()),
					"description" : $.trim($("#e-description").val())
				},
				beforeSend : function(){
					// 发送请求之前保证所有表单项都是合法的。
					return true;
				},
				success : function(json){
					if(json.success){
						$("#editActivityModal").modal("hide");
						// 分页查询，在第几页则还回到这一页。
						display($("#activityPagination").bs_pagination('getOption', 'currentPage') , $("#activityPagination").bs_pagination('getOption', 'rowsPerPage'));
					}else{
						alert("更新失败！");
					}
				}
			});
		});
		
		// 导出所有
		$("#exportAllBtn").click(function(){
			if(window.confirm("全部导出吗？")){
				// 发送传统请求。
				window.location.href = "${pageContext.request.contextPath}/workbench/activity/exportAll.do";
			}
		});
		
		// 导出选中
		$("#exportChkedBtn").click(function(){
			if($(":checkbox[name='id']:checked").size() == 0){
				alert("请选择要导出的数据！");
			}else{
				if(window.confirm("导出选中的数据吗？")){
					var sendData = "";
					$.each($(":checkbox[name='id']:checked") , function(i , n){
						sendData += "&id=" + n.value;
					});
					sendData = sendData.substr(1);
					// 发送传统请求。
					window.location.href = "${pageContext.request.contextPath}/workbench/activity/exportChked.do?" + sendData;
				}
			}
		});
		
		$("#importBtn").click(function(){
			// FormData是JS内置的对象，可以直接使用，但是浏览器要支持H5，因为这是HTML5中引入的对象FormData
			var formData = new FormData();
			formData.append("activityFile" , $("#myFile")[0].files[0]);
			// 发送ajax post请求，提交excel文件
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/workbench/activity/import.do",
				data : formData,
				contentType : false, // 默认值是true，表示enctype="application/x-www-form-urlencoded", false表示enctype="multipart/form-data"
				processData : false, // 避免将对象转换成字符串（是对象就提交对象，设置为false表示不进行字符串的转换。）
				success : function(json){
					if(json.success){
						// 隐藏modal窗口
						$("#importActivityModal").modal("hide");
						// 分页查询显示第一页数据
						display(1 , $("#activityPagination").bs_pagination('getOption', 'rowsPerPage'));
					}else{
						alert("导入失败！");
					}
				}
			});
		});
		
	});
	
	// pageNo页码
	// pageSize每页显示的记录条数
	function display(pageNo , pageSize){
		// 发送ajax get请求，查询显示第一页数据.
		$.ajax({
			type : "get",
			cache : false,
			url : "${pageContext.request.contextPath}/workbench/activity/page.do",
			data : {
				"pageNo" : pageNo , 
				"pageSize" : pageSize,
				"name" : $.trim($("#h-name").val()),
				"owner" : $.trim($("#h-owner").val()),
				"startDate" : $.trim($("#h-startDate").val()),
				"endDate" : $.trim($("#h-endDate").val())
			},
			success : function(json){
				// {"total" : 50 , "dataList" : [{"id":"","name":"","owner":"","startDate":"","endDate":""},{}]}
				var html = "";
				$.each(json.dataList , function(i , n){
					html += '<tr>';
					html += '<td><input type="checkbox" name="id" value="'+n.id+'"/></td>';
					html += '<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href=\'${pageContext.request.contextPath}/workbench/activity/detail.do?id='+n.id+'\';">'+n.name+'</a></td>';
                    html += '<td>'+n.owner+'</td>';
					html += '<td>'+n.startDate+'</td>';
					html += '<td>'+n.endDate+'</td>';
					html += '</tr>';
				});
				$("#activityTbody").html(html);
				
				// 在这里确实代码依赖顺序，这里可以绑定click。
				/*
				$(":checkbox[name='id']").click(function(){
					alert(222);
				});
				*/
				
				// 显示翻页的组件
				var total = json.total;
				var totalPages = total % pageSize == 0 ? total / pageSize : parseInt(total / pageSize) + 1;
				$("#activityPagination").bs_pagination({
					currentPage: pageNo,  // 页码
					rowsPerPage: pageSize, // 每页多少条记录
					maxRowsPerPage: 20, // 每页最多显示多少条
					totalPages: totalPages, // 总页数
					totalRows: total, // 总记录条数
					visiblePageLinks: 3, // 显示的卡片个数
					showGoToPage: true,
					showRowsPerPage: true,
					showRowsInfo: true,
					onChangePage : function(event, data){
						// 将4个隐藏域当中的条件恢复到页面上
						$("#p-name").val($("#h-name").val());
						$("#p-owner").val($("#h-owner").val());
						$("#p-startDate").val($("#h-startDate").val());
						$("#p-endDate").val($("#h-endDate").val());
						display(data.currentPage , data.rowsPerPage);
					}
				});
			}
		});
	}
	
</script>
</head>
<body>
	
	<%-- 4个条件对应4个隐藏域 --%>
	<input type="hidden" id="h-name">
	<input type="hidden" id="h-owner">
	<input type="hidden" id="h-startDate">
	<input type="hidden" id="h-endDate">

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form id="activityForm" class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="create-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="owner">
								</select>
							</div>
                            <label for="create-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="name">
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="startDate" readonly="readonly">
							</div>
							<label for="create-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="endDate" readonly="readonly">
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="cost">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="description"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="saveBtn">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
	
		<%-- 隐藏域 --%>
		<input type="hidden" id="e-id">
		
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="e-owner">
								</select>
							</div>
                            <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="e-name">
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="e-startDate" readonly="readonly">
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="e-endDate" readonly="readonly">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="e-cost">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="e-description"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="updateBtn">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- 导入市场活动的模态窗口 -->
	<div class="modal fade" id="importActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">导入市场活动</h4>
				</div>
				<div class="modal-body" style="height: 350px;">
					<div style="position: relative;top: 20px; left: 50px;">
						请选择要上传的文件：<small style="color: gray;">[仅支持.xls或.xlsx格式]</small>
					</div>
					<div style="position: relative;top: 40px; left: 50px;">
						<input type="file" id="myFile">
					</div>
					<div style="position: relative; width: 400px; height: 320px; left: 45% ; top: -40px;" >
						<h3>重要提示</h3>
						<ul>
							<li>给定文件的第一行将视为字段名。</li>
							<li>请确认您的文件大小不超过5MB。</li>
							<li>从XLS/XLSX文件中导入全部重复记录之前都会被忽略。</li>
							<li>复选框值应该是1或者0。</li>
							<li>日期值必须为MM/dd/yyyy格式。任何其它格式的日期都将被忽略。</li>
							<li>日期时间必须符合MM/dd/yyyy hh:mm:ss的格式，其它格式的日期时间将被忽略。</li>
							<li>默认情况下，字符编码是UTF-8 (统一码)，请确保您导入的文件使用的是正确的字符编码方式。</li>
							<li>建议您在导入真实数据之前用测试文件测试文件导入功能。</li>
						</ul>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="importBtn">导入</button>
				</div>
			</div>
		</div>
	</div>
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="p-name">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="p-owner">
				    </div>
				  </div>


				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control time" readonly="readonly" type="text" id="p-startDate" />
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control time" readonly="readonly" type="text" id="p-endDate">
				    </div>
				  </div>
				  
				  <%--
				  <button type="submit" class="btn btn-default">查询</button>
				   --%>
				  <button type="button" class="btn btn-default" id="queryBtn">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" id="createBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" id="editBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" id="delBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-default" data-toggle="modal" data-target="#importActivityModal"><span class="glyphicon glyphicon-import"></span> 导入</button>
				  <button type="button" class="btn btn-default" id="exportChkedBtn"><span class="glyphicon glyphicon-export"></span> 导出选中</button>
				  <button type="button" class="btn btn-default" id="exportAllBtn"><span class="glyphicon glyphicon-export"></span> 导出所有</button>
				</div>
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="firstChk"/></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="activityTbody">
						<%--
						<tr class="active">
							<td><input type="checkbox" /></td>
							<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='workbench/activity/detail.jsp';">发传单</a></td>
                            <td>zhangsan</td>
							<td>2020-10-10</td>
							<td>2020-10-20</td>
						</tr>
                        <tr class="active">
                            <td><input type="checkbox" /></td>
                            <td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='workbench/activity/detail.jsp';">发传单</a></td>
                            <td>zhangsan</td>
                            <td>2020-10-10</td>
                            <td>2020-10-20</td>
                        </tr>
                         --%>
					</tbody>
				</table>
			</div>
			
			<div id="activityPagination"></div>
			
		</div>
		
	</div>
</body>
</html>