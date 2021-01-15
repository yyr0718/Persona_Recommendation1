<%@ page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.net.*"%>
<%-- <%@ page import="com.iamoe.entity.critical_tables" %> --%>
<%@ page import="java.io.PrintWriter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<meta charset="utf8">
<html lang="en">
<head>
<meta charset="gb2312">
<meta http-equiv="X-UA-Compatible" content="IE=edge">


<title>用户画像</title>
<meta name="description" content="">
<meta name="author" content="templatemo">
<script src="../../js/lib/esl.js"></script>
<script src="../../js/lib/config.js"></script>
<script src="../../js/lib/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="../../css/lanrenzhijia.css" />
<script type="text/javascript" src="../../js/lanrenzhijia.js"></script>
<style type="text/css">
#main {
	width: 100%;
	padding: 0;
	margin: 0;
	height: 100%;
}
</style>

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/favicon.ico"
	type="image/x-icon" />

<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/css/templatemo-style.css"
	rel="stylesheet" type="text/css" media="all" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/animate.js">
	
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap-filestyle.min.js">
	
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/common.js">
	
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-migrate-1.2.1.min.js">
	
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/rotarymap.js">
	
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/templatemo-script.js">
	
</script>
<style>
	
html {
    cursor: url('../../images/menghuan.ico'), auto;
}
.draw {
    position: fixed;
    width: 1px;
    line-height: 1px;
    pointer-events: none;
}
@keyframes floatOne {
0% {
opacity:1;
}
50% {
opacity:1;
}
100% {
opacity:0;
transform:translate3D(0, -20px, 0) scale(5) rotate(45deg);
}
}
</style>
</head>
<body>

	<!-- Left column -->
	<div class="templatemo-flex-row">
		<div class="templatemo-sidebar">
			<header class="templatemo-site-header">
				<div class="square"></div>
				<h1>个人中心</h1>
			</header>
			<div class="profile-photo-container">
				<img
					src="${pageContext.request.contextPath}/images/profile-photo.jpg"
					alt="Profile Photo" class="img-responsive">
				<div class="profile-photo-overlay"></div>
			</div>
			<!-- Search box -->
			<%
				//           String session_user = (String)session.getAttribute("session_user");
				//           if(session_user==null){
				//             response.setContentType("text/html;charset=UTF-8");
				//             PrintWriter outPrintWriter = response.getWriter();
				//             PrintWriter writer = response.getWriter();
				//             String msg = null;
				//             msg = "alert( '登录超时，请重新登录' );location.href='/loginPage.do'";
				//             writer.print("<script type='text/javascript'>" + msg + "</script>");
				//             writer.flush();
				//             writer.close();
				//             response.sendRedirect("loginPage.do");
				//             return;}
			%>
			<div class="templatemo-search-form">
				<div class="input-group">
					<button type="submit" class="fa fa-search">
						&nbsp;<i class="i-font-normal"></i>
					</button>
					<label type="text" class="form-control" name="srch-term"
						id="srch-term">用户ID：${session}</label>
				</div>
			</div>
			<div class="mobile-menu-icon">
				<i class="i-font-big"></i>
			</div>
			<nav class="templatemo-left-nav">
				<ul>
					<li><a href="${pageContext.request.contextPath}/reader-zx?cert_id=${session}"><i
							class="i-font-normal"></i>个人信息</a></li>
<%--					<li><a href="${pageContext.request.contextPath}/booktuijian"><i--%>
<%--							class="i-font-normal"></i>图书推荐</a></li>--%>
<%--					<li><a href="${pageContext.request.contextPath}/lwtuijian">--%>
<%--						<i class="i-font-normal"></i>论文推荐</a></li>--%>
					<li><a href="${pageContext.request.contextPath}/center-ciyun"
						class="active"> <i class="i-font-normal"></i>用户画像
					</a></li>
					<li><a
						href="${pageContext.request.contextPath}/center-lendinquiry"><i
							class="i-font-normal"></i>借阅历史</a></li>
					<li><a
						href="${pageContext.request.contextPath}/center-bookinquiry"><i
							class="i-font-normal"></i>图书查询</a></li>
					<li><a href="${pageContext.request.contextPath}/center-lwinquiry"><i
							class="i-font-normal"></i>论文查询</a></li>
					<!-- 					<li><a -->
					<%-- 						href="${pageContext.request.contextPath}/center-zlinquiry"><i --%>
					<!-- 							class="i-font-normal"></i>专利查询</a></li> -->
					<li><a href="index"><i class="i-font-normal"></i>返回首页</a></li>
				</ul>
			</nav>
		</div>
		<!-- Main content -->

		<div style="position: relative;"
			class="templatemo-content col-1 light-gray-bg">
			<div class="templatemo-top-nav-container">
				<div class="row">
					<nav class="templatemo-top-nav col-lg-12 col-md-12">
						<ul class="text-uppercase">
							<li><a
								href="${pageContext.request.contextPath}/center-ciyun">图书画像</a></li>
							<li><a href="${pageContext.request.contextPath}/qun">群体画像</a></li>
							<li><a href="${pageContext.request.contextPath}/center-plw">论文专利画像</a></li>
							<li><a href="${pageContext.request.contextPath}/center-data">数据中心</a></li>
							
						</ul>
					</nav>
				</div>
			</div>
			<div id="main" style="position: absolute; z-index: 2"></div>
			<div id="div1"
				style="position: absolute; z-index: 3; left: 26%; top: 21%">
				<c:forEach items="${bq}" var="bq" begin="0" varStatus="userStatus">
						<c:choose>
						<c:when test="${userStatus.index<4}">
							<a class="blue3" href="${pageContext.request.contextPath}/booktuijian?guanjianci=${bq.key1}">${bq.key1}</a>

						</c:when>
						<c:when test="${userStatus.index<7}">
							<a class="blue4" href="${pageContext.request.contextPath}/booktuijian?guanjianci=${bq.key1}">${bq.key1}</a>

						</c:when>
						<c:when test="${userStatus.index<10}">
							<a class="blue5" href="${pageContext.request.contextPath}/booktuijian?guanjianci=${bq.key1}">${bq.key1}</a>

						</c:when>
						<c:when test="${userStatus.index<13}">
							<a class="blue6" href="${pageContext.request.contextPath}/booktuijian?guanjianci=${bq.key1}">${bq.key1}</a>

						</c:when>
						<c:when test="${userStatus.index<16}">
							<a class="blue7" href="${pageContext.request.contextPath}/booktuijian?guanjianci=${bq.key1}">${bq.key1}</a>

						</c:when>
						<c:when test="${userStatus.index<41}">
							<a class="blue<%=(int) (Math.floor(Math.random() * 3))%>"
								href="${pageContext.request.contextPath}/booktuijian?guanjianci=${bq.key1}">${bq.key1}</a>

						</c:when>
						</c:choose>
				</c:forEach>

			</div>
			<!-- 			<div style="position: absolute; z-index: 1; left: 31%; top: 37%"> -->
			<%-- 				<img src="/ciyun/${session}.png"> --%>
			<!-- 			</div> -->

		</div>
	</div>
	<!-- style="position: relative; display: block; border-style: solid; white-space: nowrap;  padding: 10px; left: 50%; top: 118px; width: 200px; height: 80px; border-radius: 3px; z-index: 100; " -->

	<!-- JS -->

	<script src="../../js/jquery-1.11.2.min.js"></script>
	<!-- jQuery -->
	<script src="../../js/jquery-migrate-1.2.1.min.js"></script>
	<!--  jQuery Migrate Plugin -->
	
	<script>
		require([ 'echarts'
		// 'echarts/chart/tree',
		// 'echarts/component/tooltip',
		// 'echarts/component/legend'
		], function(echarts) {
			var chart = echarts.init(document.getElementById('main'), null, {

			});

			window.onresize = function() {
				chart.resize();
			};

			var data1 = {
					"name" : "兴趣关键词"+"\r\n"+"(从上到下权重递减)",
				"children" : [ {
					"name" : "${map.key1}",
					"value" : "${map.value1}"
				}, {
					"name" : "${map.key2}",
					"value" : "${map.value2}"
				}, {
					"name" : "${map.key3}",
					"value" : "${map.value3}"
				}, {
					"name" : "${map.key4}",
					"value" : "${map.value4}"
				}, {
					"name" : "${map.key5}",
					"value" : "${map.value5}"
				}, {
					"name" : "${map.key6}",
					"value" : "${map.value6}"
				}, {
					"name" : "${map.key7}",
					"value" : "${map.value7}"
				}, {
					"name" : "${map.key8}",
					"value" : "${map.value8}"
				}, {
					"name" : "${map.key9}",
					"value" : "${map.value9}"
				}, {
					"name" : "${map.key10}",
					"value" : "${map.value10}"
				}

				]
			};

			var data2 = {
				"name" : "固定属性标签",
				"children" : [ {
					"name" : "姓名",
					"children" : [ {
						"name" : "${user.name}"
					} ]
				}, {
					"name" : "性别",
					"children" : [ {
						"name" : "${sex}"
					},

					]
				}, {
					"name" : "年龄",
					"children" : [ {
						"name" : "${age}岁"
					} ]
				}, {
					"name" : "学院",
					"children" : [ {
						"name" : "${user.dept}"
					} ]
				}]
			};
			var data3 = {
				"name" : "访问偏好标签",
				"children" : [ {
					"name" : "访问时间偏好",
					"children" : [ {
						"name" : "${time}",
						"value" : "${time}"
					} ]
				}, {
					"name" : "访问ip偏好",
					"children" : [ {
						"name" : "${ip}",
						"value" : "${ip}"
					},

					]
				}, {
					"name" : "访问终端偏好",
					"children" : [ {
						"name" : "${zd}",
						"value" : "${zd}"
					} ]
				}, {
					"name" : "访问位置偏好",
					"children" : [ {
						"name" : "${weizhi}",
						"value" : "${weizhi}"
					} ]
				} ]
			};

			chart.setOption({

				tooltip : {
					trigger : 'item',
					triggerOn : 'mousemove'
				},

				legend : {
					top : '2%',
					left : '3%',
					orient : 'vertical',
					data : [ {
						name : 'tree1',
						icon : 'rectangle'
					}, {
						name : 'tree2',
						icon : 'rectangle'
					}, {
						name : 'tree3',
						icon : 'rectangle'
					} ],
					borderColor : '#c23531'
				},

				series : [ {
					type : 'tree',

					name : 'tree1',

					data : [ data1 ],

					top : '10%',
					left : '5%',
					bottom : '12%',
					right : '75%',
					//默认向右，RL向左,BT向上，vertical向下
					symbolSize : 14,
					orient : 'RL',
					initialTreeDepth : 0,//默认：2，树图初始展开的层级（深度）。根节点是第 0 层，然后是第 1 层、第 2 层，... ，直到叶子节点
					label : {
						position : 'right',
						verticalAlign : 'bottom',
						align : 'right',
						fontSize : 20
					//文字的字体大小
					},

					leaves : {
						label : {
							position : 'right',
							verticalAlign : 'bottom',
							align : 'right',
							fontSize : 20
						//文字的字体大小
						}
					},

					expandAndCollapse : true,

					animationDuration : 550,
					animationDurationUpdate : 750

				}, {
					type : 'tree',
					name : 'tree2',
					data : [ data2 ],

					top : '20%',
					left : '55%',
					bottom : '22%',
					right : '20%',

					symbolSize : 14,
					initialTreeDepth : 0,//默认：2，树图初始展开的层级（深度）。根节点是第 0 层，然后是第 1 层、第 2 层，... ，直到叶子节点
					label : {
						position : 'top',
						verticalAlign : 'middle',
						align : 'left',
						fontSize : 20
					//文字的字体大小
					},

					leaves : {
						label : {
							position : 'left',
							verticalAlign : 'bottom',
							align : 'bottomt',
							fontSize : 20
						//文字的字体大小
						}
					},

					expandAndCollapse : true,

					animationDuration : 550,
					animationDurationUpdate : 750
				}, {
					type : 'tree',
					name : 'tree3',
					data : [ data3 ],

					top : '70%',
					left : '20%',
					bottom : '3%',
					right : '40%',

					symbolSize : 14,
					//orient : 'RL',默认向右，RL向左,BT向上，vertical向下
					orient : 'vertical',
					expandAndCollapse : true,//默认：true；子树折叠和展开的交互，默认打开 。

					initialTreeDepth : 0,//默认：2，树图初始展开的层级（深度）。根节点是第 0 层，然后是第 1 层、第 2 层，... ，直到叶子节点
					label : {
						position : 'right',
						verticalAlign : 'middle',
						align : 'left',
						fontSize : 20
					//文字的字体大小
					},

					leaves : {
						label : {
							position : 'bottom',
							verticalAlign : 'middle',
							align : 'middle',
							fontSize : 20
						//文字的字体大小
						}
					},
					animationDuration : 550,
					animationDurationUpdate : 750
				} ]
			});
		});
	</script>
	<script>
		/* Google Chart 
		-------------------------------------------------------------------*/
		// Load the Visualization API and the piechart package.
		google.load('visualization', '1.0', {
			'packages' : [ 'corechart' ]
		});

		// Set a callback to run when the Google Visualization API is loaded.
		google.setOnLoadCallback(drawChart);

		// Callback that creates and populates a data table,
		// instantiates the pie chart, passes in the data and
		// draws it.
		function drawChart() {

			// Create the data table.
			var data = new google.visualization.DataTable();
			data.addColumn('string', 'Topping');
			data.addColumn('number', 'Slices');
			data.addRows([ [ 'Mushrooms', 3 ], [ 'Onions', 1 ],
					[ 'Olives', 1 ], [ 'Zucchini', 1 ], [ 'Pepperoni', 2 ] ]);

			// Set chart options
			var options = {
				'title' : 'How Much Pizza I Ate Last Night'
			};

			// Instantiate and draw our chart, passing in some options.
			var pieChart = new google.visualization.PieChart(document
					.getElementById('pie_chart_div'));
			pieChart.draw(data, options);

			var barChart = new google.visualization.BarChart(document
					.getElementById('bar_chart_div'));
			barChart.draw(data, options);
		}

		$(document).ready(function() {
			if ($.browser.mozilla) {
				//refresh page on browser resize
				// http://www.sitepoint.com/jquery-refresh-page-browser-resize/
				$(window).bind('resize', function(e) {
					if (window.RT)
						clearTimeout(window.RT);
					window.RT = setTimeout(function() {
						this.location.reload(false); /* false to get page from cache */
					}, 200);
				});
			} else {
				$(window).resize(function() {
					drawChart();
				});
			}
		});
	</script>
	<script type="text/javascript" src="../../js/templatemo-script.js"></script>
 
	<!-- Templatemo Script -->
</body>
</html>