<%@ page import="java.io.PrintWriter"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>系统管理</title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
    <!--
            Visual Admin Template
            http://www.templatemo.com/preview/templatemo_455_visual_admin
            -->

    <link href="../../css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/templatemo-style.css" rel="stylesheet">
    <link rel="stylesheet"
          href="C:\Users\rwh\Desktop\layui-v2.5.4\layui\css\layui.css"
          media="all">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- Left column -->
<div class="templatemo-flex-row">
    <div class="templatemo-sidebar">
        <header class="templatemo-site-header">
            <div class="square"></div>
            <h1>系统管理</h1>
        </header>

        <!-- Search box -->

        <div class="templatemo-search-form">
            <div class="input-group">
                <button type="submit" class="fa fa-search">
                    &nbsp;<i class="i-font-normal"></i>
                </button>
                <label type="text" class="form-control" placeholder="管理员ID"
                       name="srch-term" id="srch-term">管理员：${session}</label>
            </div>
        </div>




        <div class="mobile-menu-icon">
            <i class="i-font-big"></i>
        </div>
        <nav class="templatemo-left-nav">
            <ul>
                <li><a href="/sysAdmi"><i class="i-font-normal"></i>数据导入</a></li>
                <li><a href="/findAllUser.do"  ><i class="i-font-normal"></i>用户管理</a></li>
                <li><a href="findBookById.do"><i class="i-font-normal"></i>图书管理</a></li>
                <li><a href="findArticleById.do" class="active"><i class="i-font-normal"></i>文献管理</a></li>
                <li><a href="/sys-lendinquiry"><i class="i-font-normal"></i>借阅数据</a></li>
                <li><a href="/user-exit"><i class="i-font-normal"></i>账号退出</a></li>
            </ul>
        </nav>
    </div>
    <!-- Main content -->
    <div class="templatemo-content col-1 light-gray-bg">

        <div class="templatemo-content-container">
            <div class="templatemo-flex-row flex-content-row">
                <div class="templatemo-content-widget white-bg col-2">
                    <i class="fa fa-times"></i>
                    <h2 class="templatemo-inline-block">文献管理：</h2>
                    <hr>
                    <form method="post" action="findArticleById.do">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="i-font-normal"></i>文献查询
                            </div>
                            <input type="text" name="usr_id" class="form-control"
                                   placeholder="在这里输入你要查询的文献名称或者作者">

                        </div>
                        <div class="form-group text-center" align="center">
                            <hr>
                            <button type="submit" class="templatemo-blue-button">查询文献</button>
                            <a href="findAllArticle.do">
                                <button type="submit" class="templatemo-blue-button">查询所有文献</button></a>
                        </div>
                    </form>


                    <hr>
                    <table
                            class="table table-striped table-bordered templatemo-user-table">
                        <thead>
                        <tr>
                            <td><a href="" class="white-text templatemo-sort-by">文献标题
                                <span class="caret"></span>
                            </a></td>
                            <td><a href="" class="white-text templatemo-sort-by">作者
                                <span class="caret"></span>
                            </a></td>
                            <td><a href="" class="white-text templatemo-sort-by">期刊
                                <span class="caret"></span>
                            </a></td>
                            <td><a href="" class="white-text templatemo-sort-by">摘要
                                <span class="caret"></span>
                            </a></td>
                            <td><a href="" class="white-text templatemo-sort-by">下载
                                <span class="caret"></span>
                            </a></td>
                            <td><a href="" class="white-text templatemo-sort-by">被引
                                <span class="caret"></span>
                            </a></td>
                            <td><a href="" class="white-text templatemo-sort-by">来源
                                <span class="caret"></span>
                            </a></td>
                            <td><a href="" class="white-text templatemo-sort-by">发表时间
                                <span class="caret"></span>
                            </a></td>
                            <td><a href="" class="white-text templatemo-sort-by">关键字
                                <span class="caret"></span>
                            </a></td>
                            <td width="8%"><a href="" class="white-text templatemo-sort-by">操作
                                <span class="caret"></span>
                            </a></td>
                        </tr>
                        </thead>
                        <tbody>
                        <form
                                action="findArticleByIdnum"
                                class="templatemo-login-form" method="post"
                                enctype="multipart/form-data">
                            <c:forEach items="${articleList}" var="article">
                                <tr>
                                    <td>${article.subject}</td>
                                    <td>${article.author }</td>
                                    <td>${article.type}</td>
                                    <td>${article.abstract1}</td>
                                    <td>${article.download_num}</td>
                                    <td>${article.quote_num}</td>
                                    <td>${article.periodical}</td>
                                    <td>${article.publish_time}</td>
                                    <td>${article.keyword}</td>
                                    <td><div><a
                                            href="updateArticle?article_id=${article.id}"
                                            class="templatemo-edit-btn">编辑</a></div><br>
                                        <div><a href="deleteArticle?article_id=${article.id}"
                                                class="templatemo-edit-btn">删除</a>
                                        </div><br>
                                    </td>

                                </tr>
                            </c:forEach>
                            <c:if test="${jian!=0 }">
                                <tr>

                                    <td colspan="10" align="center">
                                        共${pagetotal+1}页,当前第${pagenum}页 <c:if test="${pagenum-3>0}">
                                        <a
                                                href="findArticleByIdnum?pagenum=${pagenum-3}">${pagenum-3}</a>
                                        </c:if> <c:if test="${pagenum-2>0}">
                                        <a
                                                href="findArticleByIdnum?pagenum=${pagenum-2}">${pagenum-2}</a>
                                        </c:if> <c:if test="${pagenum-1>0}">
                                        <a
                                                href="findArticleByIdnum?pagenum=${pagenum-1}">${pagenum-1}</a>
                                        </c:if>
                                        <c:if test="${pagenum-1>0}">
                                        <a
                                                href="findArticleByIdup">上一页</a>&nbsp;
                                        </c:if>
                                        <c:if test="${pagenum<pagetotal+1}">
                                        <a
                                                href="findArticleByIddn">下一页</a>&nbsp;
                                        </c:if>
                                        <c:if test="${pagenum+1<=pagetotal+1}">
                                        <a
                                                href="findArticleByIdnum?pagenum=${pagenum+1}">${pagenum+1}</a>
                                        </c:if> <c:if test="${pagenum+2<=pagetotal+1}">
                                        <a
                                                href="findArticleByIdnum?pagenum=${pagenum+2}">${pagenum+2}</a>
                                        </c:if> <c:if test="${pagenum+3<=pagetotal+1}">
                                        <a
                                                href="findArticleByIdnum?pagenum=${pagenum+3}">${pagenum+3}</a>
                                        </c:if> 去第<input type="text" name="pn" size=3 />页 <input
                                            type="submit" value="确定" id="btn_id" />
                                </tr>
                            </c:if>
                        </form>
                        </tbody>
                    </table>

                </div>
                <script src="/js/jquery-1.11.2.min.js"></script>
                <!-- jQuery -->
                <script src="/js/jquery-migrate-1.2.1.min.js"></script>
                <!--  jQuery Migrate Plugin -->

                <script type="text/javascript" src="/js/templatemo-script.js"></script>
            </div>
        </div>
    </div>
</div>
</body>
</html>
