<!DOCTYPE html>
<%@page import="java.util.HashMap"%>
<%@page import="java.net.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%-- <%@ page import="com.iamoe.entity.critical_tables" %> --%>
<%@ page import="java.io.PrintWriter"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<meta charset="utf8">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>论文查询</title>
<meta name="description" content="">
<meta name="author" content="templatemo">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/favicon.ico"
	type="image/x-icon" />

<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/css/templatemo-style.css"
	rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js">
	
</script>
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
<style>
.test {
	font-size: 24px;
	color: #FF0000;
	font-style: italic;
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
				//             String session_user = (String)session.getAttribute("session_user");
				//             if(session_user==null){
				//                 response.setContentType("text/html;charset=UTF-8");
				//                 PrintWriter outPrintWriter = response.getWriter();
				//                 PrintWriter writer = response.getWriter();
				//                 String msg = null;
				//                 msg = "alert( '登录超时，请重新登录' );location.href='/loginPage.do'";
				//                 writer.print("<script type='text/javascript'>" + msg + "</script>");
				//                 writer.flush();
				//                 writer.close();
				//                 response.sendRedirect("loginPage.do");
				//                 return;}
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
<%-- 					<li><a href="${pageContext.request.contextPath}/lwtuijian"><i--%>
<%-- 							class="i-font-normal"></i>论文推荐</a></li>--%>
					<li><a href="${pageContext.request.contextPath}/center-ciyun"
						> <i class="i-font-normal"></i>用户画像
					</a></li>
					<li><a
						href="${pageContext.request.contextPath}/center-lendinquiry"><i
							class="i-font-normal"></i>借阅历史</a></li>
					<li><a
						href="${pageContext.request.contextPath}/center-bookinquiry"><i
							class="i-font-normal"></i>图书查询</a></li>
 					<li><a
 						href="${pageContext.request.contextPath}/center-lwinquiry" class="active"><i
 							class="i-font-normal"></i>论文查询</a></li>
<!-- 					<li><a -->
<%-- 						href="${pageContext.request.contextPath}/center-zlinquiry"><i --%>
<!-- 							class="i-font-normal"></i>专利查询</a></li> -->
					<li><a href="index"><i class="i-font-normal"></i>返回首页</a></li>
				</ul>
			</nav>
		</div>
		<!-- Main content -->

		<div class="templatemo-content col-1 light-gray-bg">
			<div class="templatemo-top-nav-container">
				<div class="row">
					<nav class="templatemo-top-nav col-lg-12 col-md-12">
						<ul class="text-uppercase">
							<li><a
								href="${pageContext.request.contextPath}/center-bookinquiry">图书检索</a></li>
								<li><a
								href="${pageContext.request.contextPath}/center-lwinquiry">论文检索</a></li>
								<li><a
								href="${pageContext.request.contextPath}/center-zlinquiry">专利检索</a></li>

						</ul>
					</nav>
				</div>
			</div>
			<div>&nbsp;</div>
			<form action="${pageContext.request.contextPath}/center-lwjiansuo">
				<div class="test" style="float: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;按题名或作者名字检索论文：
				</div>
				<div>
					<input type="text" name="bookname"> <input type="submit"
						value="开始检索" name="jiansuo">
				</div>

			</form>
			<div class="templatemo-content-container">
				<div class="templatemo-flex-row flex-content-row">

					<table
						class="table table-striped table-bordered templatemo-user-table">
						<thead>
							<tr>
								<td align="center"><a href="" class="white-text templatemo-sort-by">题名
										<span class="caret"></span>
								</a></td>
								<td align="center"><a href="" class="white-text templatemo-sort-by">作者
										<span class="caret"></span>
								</a></td>
								<td align="center"><a href="" class="white-text templatemo-sort-by">来源
										<span class="caret"></span>
								</a></td>
								<td align="center"><a href="" class="white-text templatemo-sort-by">发表时间
										<span class="caret"></span>
								</a></td>
								<td align="center"><a href="" class="white-text templatemo-sort-by">下载次数
										<span class="caret"></span>
								</a></td>
								<td align="center"><a href="" class="white-text templatemo-sort-by">被引次数
										<span class="caret"></span>
								</a></td>
							</tr>
						</thead>
						<tbody>
							<form
								action="${pageContext.request.contextPath}/center-lwinquirynum"
								class="templatemo-login-form" method="post"
								enctype="multipart/form-data">
								<c:forEach items="${lwinquiry}" var="lwinquiry">
									<tr>
										<td>${lwinquiry.subject}</td>
										<td>${lwinquiry.author}</td>
										<td>${lwinquiry.periodical}</td>
										<td>${lwinquiry.publish_time}</td>
										<td>${lwinquiry.download_num}</td>
										<td>${lwinquiry.quote_num}</td>
									</tr>
								</c:forEach>
								<c:if test="${jian!=0 }">
									<tr>

										<td colspan="6" align="center">
											共${pagetotal+1}页,当前第${pagenum}页 <c:if test="${pagenum-3>0}">
												<a
													href="${pageContext.request.contextPath}/center-lwinquirynum?pagenum=${pagenum-3}">${pagenum-3}</a>
											</c:if> <c:if test="${pagenum-2>0}">
												<a
													href="${pageContext.request.contextPath}/center-lwinquirynum?pagenum=${pagenum-2}">${pagenum-2}</a>
											</c:if> <c:if test="${pagenum-1>0}">
												<a
													href="${pageContext.request.contextPath}/center-lwinquirynum?pagenum=${pagenum-1}">${pagenum-1}</a>
											</c:if> <c:if test="${pagenum-1>0}">
											<a
											href="${pageContext.request.contextPath}/center-lwinquiryup">上一页</a>&nbsp;
											</c:if>
											<c:if test="${pagenum<pagetotal+1}">
											<a
											href="${pageContext.request.contextPath}/center-lwinquirydn">下一页</a>&nbsp;
											</c:if>
											<c:if test="${pagenum+1<=pagetotal}">
												<a
													href="${pageContext.request.contextPath}/center-lwinquirynum?pagenum=${pagenum+1}">${pagenum+1}</a>
											</c:if> <c:if test="${pagenum+2<=pagetotal}">
												<a
													href="${pageContext.request.contextPath}/center-lwinquirynum?pagenum=${pagenum+2}">${pagenum+2}</a>
											</c:if> <c:if test="${pagenum+3<=pagetotal}">
												<a
													href="${pageContext.request.contextPath}/center-lwinquirynum?pagenum=${pagenum+3}">${pagenum+3}</a>
											</c:if> 去第<input type="text" name="pn" size=3 />页 <input
											type="submit" value="确定" id="btn_id" />
									</tr>
								</c:if>
						</tbody>
						</form>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- JS -->
	<script src="../../js/jquery-1.11.2.min.js"></script>
	<!-- jQuery -->
	<script src="../../js/jquery-migrate-1.2.1.min.js"></script>
	<!--  jQuery Migrate Plugin -->
	
	<script type="text/javascript" src="../../js/templatemo-script.js"></script>
	<!-- Templatemo Script -->
 
</body>
</html>