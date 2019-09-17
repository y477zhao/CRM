<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 	request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="echarts/echarts.min.js"></script>
<script type="text/javascript">

	$(function(){
		
		
		
		
		
		
		$.ajax({
			
			url : "workbench/transaction/getChart.do",
			type : "get",
			dataType : "json",
			success : function(data){
				
				
				// 基于准备好的dom，初始化echarts实例
		        var myChart = echarts.init(document.getElementById('main'));

		        // 指定图表的配置项和数据
		        option = {
				    title: {
				        text: '交易漏斗图',
				        subtext: ''
				    },
				    tooltip: {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c}"
				    },
				    calculable: true,
				    series: [
				        {
				            name:'漏斗图',
				            type:'funnel',
				            left: '10%',
				            top: 60,
				            //x2: 80,
				            bottom: 60,
				            width: '80%',
				            // height: {totalHeight} - y - y2,
				            min: 0,
				            max: data.total,
				            minSize: '0%',
				            maxSize: '100%',
				            sort: 'descending',
				            gap: 2,
				            label: {
				                normal: {
				                    show: true,
				                    position: 'inside'
				                },
				                emphasis: {
				                    textStyle: {
				                        fontSize: 20
				                    }
				                }
				            },
				            labelLine: {
				                normal: {
				                    length: 10,
				                    lineStyle: {
				                        width: 1,
				                        type: 'solid'
				                    }
				                }
				            },
				            itemStyle: {
				                normal: {
				                    borderColor: '#fff',
				                    borderWidth: 1
				                }
				            },
				            data:data.dataList
				        }
				    ]
				};

		        // 使用刚指定的配置项和数据显示图表。
		        myChart.setOption(option);
				
				
				
			}
			
		})
		
	})
	
</script>

</head>
<body>
	
	<div id="main" style="width: 600px;height:400px;"></div>
	
</body>
</html>