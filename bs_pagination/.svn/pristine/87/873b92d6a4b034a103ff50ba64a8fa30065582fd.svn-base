<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<meta charset="UTF-8">
<%-- jquery --%>
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<%-- bootstrap --%>
<link rel="stylesheet" type="text/css" href="jquery/bootstrap_3.3.0/css/bootstrap.min.css">
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<%-- pagination --%>
<link rel="stylesheet" type="text/css" href="jquery/bs_pagination/css/jquery.bs_pagination.min.css">
<script type="text/javascript" src="jquery/bs_pagination/js/jquery.bs_pagination.min.js"></script>
<script type="text/javascript" src="jquery/bs_pagination/js/en.min.js"></script>

<script type="text/javascript">
	$(function(){
		
		var pageNo = 1;
		var pageSize = 10;
		var total = 53;
		var totalPages = total % pageSize == 0 ? total / pageSize : parseInt(total / pageSize) + 1;
		
		$("#demo_pag1").bs_pagination({
			currentPage: pageNo,  // 页码
			rowsPerPage: pageSize, // 每页多少条记录
			maxRowsPerPage: 20, // 每页最多显示多少条
			totalPages: totalPages, // 总页数
			totalRows: total, // 总记录条数

			visiblePageLinks: 3, // 显示的卡片个数

			showGoToPage: true,
			showRowsPerPage: true,
			showRowsInfo: true,
			
			// 当翻页的时候，每一次翻页这个回调函数都会执行。
			onChangePage : function(event, data){
				// data is an object with properties: currentPage and rowsPerPage.
				// currentPage 就是pageNo
				// rowsPerPage 就是pageSize
				alert(data.currentPage + "," + data.rowsPerPage);
		
				// display(data.currentPage , data.rowsPerPage);
			}
		});
		
	});
</script>
</head>
<body>
	<div id="demo_pag1"></div>
</body>
</html>