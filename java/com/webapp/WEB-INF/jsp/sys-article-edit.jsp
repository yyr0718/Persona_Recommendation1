<%@ page import="java.io.PrintWriter"%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>用户管理</title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
    <link href="../../css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/templatemo-style.css" rel="stylesheet">

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
                       name="srch-term" id="srch-term">管理员：${reader.name}</label>
            </div>
        </div>
        <div class="mobile-menu-icon">
            <i class="i-font-big"></i>
        </div>
        <nav class="templatemo-left-nav">
            <ul>
                <li><a href="/sysAdmi"><i class="i-font-normal"></i>数据导入</a></li>
                <li><a href="/findAllUser.do" class="active"><i class="i-font-normal"></i>用户管理</a></li>
                <li><a href="findBookById.do"><i class="i-font-normal"></i>图书管理</a></li>
                <li><a href="findArticleById.do"><i class="i-font-normal"></i>文献管理</a></li>
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
                    <i class="i-font-x fa-times"></i>
                    <div class="square"></div>
                    <h2 class="templatemo-inline-block">在此处进行文献信息更改</h2>
                    <hr>



                    <form action="sys-updateArticleInform"
                          class="templatemo-login-form">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="i-font-normal"></i>文献编号
                                </div>
                                <label class="form-control">${article.id}</label>
                                <input type="hidden" name="article_id" value="${article.id}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="i-font-normal"></i>文献标题
                                </div>
                                <input type="text" name="article_name" class="form-control"
                                       value="${article.subject}" placeholder="${user.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="i-font-normal"></i>作者
                                </div>
                                <input type="text" name="article_author" class="form-control"
                                       value="${article.author}">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="i-font-normal"></i>期刊
                                </div>
                                <input type="text" name="article_type" class="form-control"
                                       value="${article.type}">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="i-font-normal"></i>摘要
                                </div>
                                <input type="text" name="article_abstract1" class="form-control"
                                       value="${article.abstract1}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="i-font-normal"></i>下载
                                </div>
                                <input type="text" name="article_download_num" class="form-control"
                                       disabled="disabled" value="${article.download_num}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="i-font-normal"></i>被引
                                </div>
                                <input type="text" name="article_quote_num" class="form-control"
                                       disabled="disabled" value="${article.quote_num}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="i-font-normal"></i>来源
                                </div>
                                <input type="text" name="article_periodical" class="form-control"
                                      value="${article.periodical}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="i-font-normal"></i>发表时间
                                </div>
                                <input type="text" name="article_publishtime" class="form-control"
                                       value="${article.publish_time}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="i-font-normal"></i>关键字
                                </div>
                                <input type="text" name="article_key" class="form-control"
                                       value="${article.keyword}" />
                            </div>
                        </div>
                        <hr />
                        <div class="form-group">
                            <div class="form-group text-center">
                                <button type="submit" class="templatemo-blue-button">提交</button>
                                <button type="reset" class="templatemo-white-button">重置</button>

                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- JS -->
            <script src="/js/jquery-1.11.2.min.js"></script>
            <!-- jQuery -->
            <script src="/js/jquery-migrate-1.2.1.min.js"></script>
            <!--  jQuery Migrate Plugin -->

            <script type="text/javascript" src="/js/templatemo-script.js"></script>
            <!-- Templatemo Script -->
        </div>
    </div>
</div>
</body>
</html>
