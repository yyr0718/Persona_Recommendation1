<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>西农图书馆</title>
    <base href="../" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <meta name="referrer" content="no-referrer">
    <script type="application/x-javascript">


        addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
        function hideURLbar(){ window.scrollTo(0,1); }


    </script>
    <!-- Custom Theme files -->
    <link href="css2/bootstrap.css" rel="stylesheet" type="text/css"
          media="all" />
    <link href="css2/style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css2/menu.css" rel="stylesheet" type="text/css" media="all" />
    <!-- menu style -->
    <link href="css2/ken-burns.css" rel="stylesheet" type="text/css"
          media="all" />
    <!-- banner slider -->
    <link href="css2/animate.min.css" rel="stylesheet" type="text/css"
          media="all" />
    <link href="css2/owl.carousel.css" rel="stylesheet" type="text/css"
          media="all">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
          rel="stylesheet" type="text/css" media="all" />
    <!-- carousel slider -->
    <!-- //Custom Theme files -->
    <!-- font-awesome icons -->
    <link href="css2/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- js -->
    <script src="js2/jquery-2.2.3.min.js"></script>
    <!-- //js -->
    <!-- web-fonts -->
    <link
            href='https://fonts.googleapis.com/css?family=Roboto+Condensed:400,300,300italic,400italic,700,700italic'
            rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Lovers+Quarrel'
          rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Offside'
          rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Tangerine:400,700'
          rel='stylesheet' type='text/css'>
    <!-- web-fonts -->
    <script src="js2/owl.carousel.js"></script>
    <script>
        $(document).ready(function() {
            $("#owl-demo").owlCarousel({
                autoPlay : 3000, //Set AutoPlay to 3 seconds
                items : 4,
                itemsDesktop : [ 640, 5 ],
                itemsDesktopSmall : [ 480, 2 ],
                navigation : true

            });
        });
    </script>
    <script src="js2/jquery-scrolltofixed-min.js" type="text/javascript"></script>
    <script>
        $(document).ready(function() {

            // Dock the header to the top of the window when scrolled past the banner. This is the default behaviour.

            $('.header-two').scrollToFixed();
            // previous summary up the page.

            var summaries = $('.summary');
            summaries.each(function(i) {
                var summary = $(summaries[i]);
                var next = summaries[i + 1];

                summary.scrollToFixed({
                    marginTop : $('.header-two').outerHeight(true) + 10,
                    zIndex : 999
                });
            });
        });
    </script>
    <!-- start-smooth-scrolling -->
    <script type="text/javascript" src="js2/move-top.js"></script>
    <script type="text/javascript" src="js2/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function($) {
            $(".scroll").click(function(event) {
                event.preventDefault();
                $('html,body').animate({
                    scrollTop : $(this.hash).offset().top
                }, 1000);
            });
        });
    </script>
    <!-- //end-smooth-scrolling -->
    <!-- smooth-scrolling-of-move-up -->
    <script type="text/javascript">
        $(document).ready(function() {

            var defaults = {
                containerID : 'toTop', // fading element id
                containerHoverID : 'toTopHover', // fading element hover id
                scrollSpeed : 1200,
                easingType : 'linear'
            };

            $().UItoTop({
                easingType : 'easeOutQuart'
            });

        });
    </script>
    <!-- //smooth-scrolling-of-move-up -->
    <script src="js2/bootstrap.js"></script>
    <style>
        .white-text{
            color: #00CC99;
        }
    </style>
</head>
<body>
<!-- header -->
<div class="header">
    <div class="w3ls-header">
        <!--header-one-->
        <div class="w3ls-header-left">
            <p>
                <a href="/"><i class="fa fa-gift" aria-hidden="true"></i>
                    西农图书馆</a>
            </p>
        </div>
        <div class="w3ls-header-right">
            <ul>
                <li class="dropdown head-dpdn"><a
                        href="reader-zx?cert_id=${session}" class="dropdown-toggle"><i
                        class="fa fa-user" aria-hidden="true"></i> 个人中心</a></li>

                <li class="dropdown head-dpdn"><a href="user-exit"
                                                  class="dropdown-toggle"><i class="fa fa-map-marker"
                                                                             aria-hidden="true"></i> 退出</a></li>

            </ul>
        </div>
        <div class="clearfix"></div>
    </div>

    <div class="header-two">
        <!-- header-two -->
        <div class="container">
            <div class="header-logo">
                <div class="logo-img">
                    <h1>
                        <a href="/"><img src="./images/xiaohui.jpg"></a>
                    </h1>
                </div>

            </div>
            <div class="header-search">
                <form action="lwinquiry" method="post">
                    <input type="search" name="search">
                    <button type="submit" class="btn btn-default"
                            aria-label="Left Align" id='sbumi8tm'>
                        <i class="fa fa-search" aria-hidden="true"> </i>
                    </button>
                </form>
            </div>
            <div class="header-cart">
                <div class="my-account">
                    <a href="index"><i class="fa fa-map-marker"
                                       aria-hidden="true"></i> 图书推荐</a>
                </div>
                <div class="cart">
                    <a href="user-center/collection.html"><button
                            class="w3view-cart" type="button" name="submit" value="">
                        <i class="fa fa-heart" aria-hidden="true"></i>
                    </button></a>

                </div>
                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <!-- //header-two -->
</div>
<!-- //header -->
<!-- banner -->


<!-- welcome -->
<div class="welcome">
    <div class="container">


        <div class="welcome-info">
            <div class="bs-example bs-example-tabs" role="tabpanel"
                 data-example-id="togglable-tabs">
                <ul id="myTab" class=" nav-tabs" role="tablist">

                    <li role="presentation"  class="active">
                        <a href="#home" id="home-tab" role="tab" data-toggle="tab">
                            <i class="fa fa-laptop" aria-hidden="true"></i>
                            <h5>院系推荐</h5>
                        </a></li>
                    <li role="presentation"><a href="#love" role="tab"
                                               id="love-tab" data-toggle="tab">
                        <i class="fa fa-heart" aria-hidden="true" ></i>
                        <h5>兴趣推荐</h5>
                    </a></li>

                    <li role="presentation"><a href="#carl" role="tab"
                                               id="carl-tab" data-toggle="tab"> <i class="fa fa-comments"
                                                                                    aria-hidden="true"></i>
                        <h5>群体推荐</h5>
                    </a></li>

                    <li role="presentation"><a href="#james" role="tab" id="james-tab" data-toggle="tab">
                        <i class="fa fa-book" aria-hidden="true" style="color: #FF8C00;"></i>
                        <h5>Top推荐</h5>
                    </a></li>
                    <li role="presentation"><a href="#decor" role="tab"
                                               id="decor-tab" data-toggle="tab">
                        <i class="fa fa-heartbeat" aria-hidden="true" style="color: #6495ED;"></i>
                        <h5>最新推荐</h5>
                    </a></li>
                </ul>
                <div class="clearfix"></div>
                <h3 class="w3ls-title" style="font-family: SimHei;">为您推荐论文</h3>
                <div id="myTabContent" class="tab-content">
                    <div role="tabpanel" class="tab-pane fade in active" id="home" aria-labelledby="home-tab">
                        <table class="table table-striped table-bordered templatemo-user-table">
                            <thead>
                            <tr>
                                <td align="center" style="background-color: #94c7aa">
                                    <a  class="white-text templatemo-sort-by" style="color: black;font-size: 18px">题名
                                    <span class="caret"></span>
                                </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a  class="white-text templatemo-sort-by" style="color: black;font-size: 18px">作者
                                    <span class="caret"></span>
                                </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a  class="white-text templatemo-sort-by" style="color: black;font-size: 18px">来源
                                    <span class="caret"></span>
                                </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">发表时间
                                    <span class="caret"></span>
                                </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">下载次数
                                    <span class="caret"></span>
                                </a></td>
                                <td align="center" style="background-color: #94c7aa" >
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">被引次数
                                    <span class="caret"></span>
                                </a></td>
                            </tr>
                            </thead>
                            <tbody>
                            <form
                                    action="${pageContext.request.contextPath}/center-lwinquirynum"
                                    class="templatemo-login-form" method="post"
                                    enctype="multipart/form-data">
                                <c:forEach items="${resultlwByInfor}" var="resultlwByInfor">
                                    <tr>
                                        <td>${resultlwByInfor.subject}</td>
                                        <td>${resultlwByInfor.author}</td>
                                        <td>${resultlwByInfor.periodical}</td>
                                        <td>${resultlwByInfor.publish_time}</td>
                                        <td>${resultlwByInfor.download_num}</td>
                                        <td>${resultlwByInfor.quote_num}</td>
                                    </tr>
                                </c:forEach>
                            </form>
                            </tbody>
                        </table>
                    </div>
                    <div role="tabpanel" class="tab-pane fade" id="love"
                         aria-labelledby="love-tab">
                        <table class="table table-striped table-bordered templatemo-user-table">
                            <thead>
                            <tr>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">题名
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">作者
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">来源
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">发表时间
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">下载次数
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">被引次数
                                        <span class="caret"></span>
                                    </a></td>
                            </tr>
                            </thead>
                            <tbody>
                            <form
                                    action="${pageContext.request.contextPath}/center-lwinquirynum"
                                    class="templatemo-login-form" method="post"
                                    enctype="multipart/form-data">
                                <c:forEach items="${resultlwByBQ}" var="resultlwByBQ">
                                    <tr>
                                        <td>${resultlwByBQ.subject}</td>
                                        <td>${resultlwByBQ.author}</td>
                                        <td>${resultlwByBQ.periodical}</td>
                                        <td>${resultlwByBQ.publish_time}</td>
                                        <td>${resultlwByBQ.download_num}</td>
                                        <td>${resultlwByBQ.quote_num}</td>
                                    </tr>
                                </c:forEach>
                            </form>
                            </tbody>
                        </table>
                    </div>
                    <div role="tabpanel" class="tab-pane fade" id="carl"
                         aria-labelledby="carl-tab">
                        <table class="table table-striped table-bordered templatemo-user-table">
                            <thead>
                            <tr>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">题名
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">作者
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">来源
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">发表时间
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">下载次数
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa" >
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">被引次数
                                        <span class="caret"></span>
                                    </a></td>
                            </tr>
                            </thead>
                            <tbody>
                            <form
                                    action="${pageContext.request.contextPath}/center-lwinquirynum"
                                    class="templatemo-login-form" method="post"
                                    enctype="multipart/form-data">
                                <c:forEach items="${resultlwBykind}" var="resultlwBykind">
                                    <tr>
                                        <td>${resultlwBykind.subject}</td>
                                        <td>${resultlwBykind.author}</td>
                                        <td>${resultlwBykind.periodical}</td>
                                        <td>${resultlwBykind.publish_time}</td>
                                        <td>${resultlwBykind.download_num}</td>
                                        <td>${resultlwBykind.quote_num}</td>
                                    </tr>
                                </c:forEach>
                            </form>
                            </tbody>
                        </table>
                    </div>
                    <div role="tabpanel" class="tab-pane fade" id="james"
                         aria-labelledby="james-tab">
                        <table class="table table-striped table-bordered templatemo-user-table">
                            <thead>
                            <tr>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">题名
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">作者
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">来源
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">发表时间
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">下载次数
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa" >
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">被引次数
                                        <span class="caret"></span>
                                    </a></td>
                            </tr>
                            </thead>
                            <tbody>
                            <form
                                    action="${pageContext.request.contextPath}/center-lwinquirynum"
                                    class="templatemo-login-form" method="post"
                                    enctype="multipart/form-data">
                                <c:forEach items="${resultlwTop}" var="resultlwTop">
                                    <tr>
                                        <td>${resultlwTop.subject}</td>
                                        <td>${resultlwTop.author}</td>
                                        <td>${resultlwTop.periodical}</td>
                                        <td>${resultlwTop.publish_time}</td>
                                        <td>${resultlwTop.download_num}</td>
                                        <td>${resultlwTop.quote_num}</td>
                                    </tr>
                                </c:forEach>
                            </form>
                            </tbody>
                        </table>
                    </div>
                    <div role="tabpanel" class="tab-pane fade" id="decor"
                         aria-labelledby="decor-tab">
                        <table class="table table-striped table-bordered templatemo-user-table">
                            <thead>
                            <tr>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">题名
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">作者
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">来源
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">发表时间
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa">
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">下载次数
                                        <span class="caret"></span>
                                    </a></td>
                                <td align="center" style="background-color: #94c7aa" >
                                    <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">被引次数
                                        <span class="caret"></span>
                                    </a></td>
                            </tr>
                            </thead>
                            <tbody>
                            <form
                                    action="${pageContext.request.contextPath}/center-lwinquirynum"
                                    class="templatemo-login-form" method="post"
                                    enctype="multipart/form-data">
                                <c:forEach items="${resultlwNew}" var="resultlwNew">
                                    <tr>
                                        <td>${resultlwNew.subject}</td>
                                        <td>${resultlwNew.author}</td>
                                        <td>${resultlwNew.periodical}</td>
                                        <td>${resultlwNew.publish_time}</td>
                                        <td>${resultlwNew.download_num}</td>
                                        <td>${resultlwNew.quote_num}</td>
                                    </tr>
                                </c:forEach>
                            </form>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- //welcome -->

<!-- coming soon -->
<div class="wthree-offers">
    <div class="container">
        <h3 class="w3ls-title" style="font-family: SimHei;">搜索结果</h3>
        <div id="mySearch" class="tab-content">
            <table
                    class="table table-striped table-bordered templatemo-user-table">
                <thead>
                <tr>
                    <td align="center" style="background-color: #94c7aa">
                        <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">题名
                            <span class="caret"></span>
                        </a></td>
                    <td align="center" style="background-color: #94c7aa">
                        <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">作者
                            <span class="caret"></span>
                        </a></td>
                    <td align="center" style="background-color: #94c7aa">
                        <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">来源
                            <span class="caret"></span>
                        </a></td>
                    <td align="center" style="background-color: #94c7aa">
                        <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">发表时间
                            <span class="caret"></span>
                        </a></td>
                    <td align="center" style="background-color: #94c7aa">
                        <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">下载次数
                            <span class="caret"></span>
                        </a></td>
                    <td align="center" style="background-color: #94c7aa">
                        <a   class="white-text templatemo-sort-by" style="color: black;font-size: 18px">被引次数
                            <span class="caret"></span>
                        </a></td>
                </tr>
                </thead>
                <tbody>
                <form
                        class="templatemo-login-form" method="post"
                        enctype="multipart/form-data">
                    <c:forEach items="${lwinquiry}" var="lwinquiry">
                        <tr>
                            <td>${lwinquiry.subject}</td>
                            <td>${lwinquiry.author }</td>
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
                                        href="${pageContext.request.contextPath}/lwinquirynum?pagenum=${pagenum-3}">${pagenum-3}</a>
                                </c:if> <c:if test="${pagenum-2>0}">
                                <a
                                        href="${pageContext.request.contextPath}/lwinquirynum?pagenum=${pagenum-2}">${pagenum-2}</a>
                                </c:if> <c:if test="${pagenum-1>0}">
                                <a
                                        href="${pageContext.request.contextPath}/lwinquirynum?pagenum=${pagenum-1}">${pagenum-1}</a>
                                </c:if> <c:if test="${pagenum-1>0}">
                                <a
                                        href="${pageContext.request.contextPath}/lwinquiryup">上一页</a>&nbsp;
                                </c:if>
                                <c:if test="${pagenum<pagetotal+1}">
                                <a
                                        href="${pageContext.request.contextPath}/lwinquirydn">下一页</a>&nbsp;
                                </c:if>
                                <c:if test="${pagenum+1<=pagetotal}">
                                <a
                                        href="${pageContext.request.contextPath}/lwinquirynum?pagenum=${pagenum+1}">${pagenum+1}</a>
                                </c:if> <c:if test="${pagenum+2<=pagetotal}">
                                <a
                                        href="${pageContext.request.contextPath}/lwinquirynum?pagenum=${pagenum+2}">${pagenum+2}</a>
                                </c:if> <c:if test="${pagenum+3<=pagetotal}">
                                <a
                                        href="${pageContext.request.contextPath}/lwinquirynum?pagenum=${pagenum+3}">${pagenum+3}</a>
                                </c:if> 去第<input type="text" name="pn" size=3 />页 <input
                                    type="submit" value="确定" id="btn_id" />
                        </tr>
                    </c:if>
                </form>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- //coming soon -->

<!-- deals -->
<div class="deals">
    <div class="container">
        <h3 class="w3ls-title" style="font-family: SimHei;">所有图书分类</h3>
        <div class="deals-row">
            <div class="col-md-2 focus-grid w3focus-grid-mdl">
                <a href="../product/all.do" class="wthree-btn wthree2">
                    <div class="focus-image">
                        <i class="fa fa-laptop"></i>
                    </div>
                    <h4 class="clrchg">计算机</h4>
                </a>
            </div>
            <div class="col-md-2 focus-grid w3focus-grid-mdl">
                <a href="../product/all.do" class="wthree-btn wthree4">
                    <div class="focus-image">
                        <i class="fa fa-asterisk"></i>
                    </div>
                    <h4 class="clrchg">科学技术</h4>
                </a>
            </div>
            <div class="col-md-2 focus-grid w3focus-grid-mdl">
                <a href="../product/all.do" class="wthree-btn wthree3">
                    <div class="focus-image">
                        <i class="fa fa-book"></i>
                    </div>
                    <h4 class="clrchg">青春文学</h4>
                </a>
            </div>

            <div class="col-md-2 focus-grid w3focus-grid-mdl">
                <a href="../product/all.do" class="wthree-btn wthree">
                    <div class="focus-image">
                        <i class="fa fa-music" aria-hidden="true"></i>
                    </div>
                    <h4 class="clrchg">音乐歌曲</h4>
                </a>
            </div>
            <div class="col-md-2 focus-grid w3focus-grid-mdl">
                <a href="../product/all.do" class="wthree-btn wthree1">
                    <div class="focus-image">
                        <i class="fa fa-car"></i>
                    </div>
                    <h4 class="clrchg">交通运输</h4>
                </a>
            </div>
            <div class="col-md-2 focus-grid w3focus-grid-mdl"
                 style="color: #FFB6C1;">
                <a href="../product/all.do" class="wthree-btn wthree6"
                   style="height: 131.39px;">
                    <div class="focus-image">
                        <br>
                        <p>
                            <b>更多...</b>
                        </p>
                        <br>
                    </div>
                </a>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!-- footer -->
<div class="footer">
    <div class="container">
        <div class="footer-info w3-agileits-info">
            <div class="col-md-4 address-left agileinfo">
                <div class="footer-logo header-logo">
                    <h2>
                        <a href="index"><img src="./images/xiaohui.jpg"></a>
                    </h2>

                </div>
                <ul>
                    <li><i class="fa fa-map-marker"></i>陕西省·咸阳市·杨凌区·西北农林科技大学</li>
                    <li><i class="fa fa-mobile"></i> 333 222 3333</li>
                    <li><i class="fa fa-phone"></i> +222 11 4444</li>
                    <li><i class="fa fa-envelope-o"></i> <a
                            href="mailto:example@mail.com"> mail@example.com</a></li>
                </ul>
            </div>
            <div class="col-md-8 address-right">
                <div class="col-md-4 footer-grids">
                    <h3 style="font-family: SimHei;">西农图书馆</h3>
                    <ul>
                        <li><a href="about.html">关于我们</a></li>
                        <li><a href="marketplace.html">友情链接</a></li>
                        <li><a href="values.html">服务宗旨</a></li>
                        <li><a href="privacy.html">隐私保护</a></li>
                    </ul>
                </div>
                <div class="col-md-4 footer-grids">
                    <h3 style="font-family: SimHei;">为您服务</h3>
                    <ul>
                        <li><a href="contact.html">联系我们</a></li>
                        <li><a href="login.html">意见反馈</a></li>
                        <li><a href="faq.html">联系方式</a></li>
                        <li><a href="sitemap.html">作者中心</a></li>
                        <li><a href="login.html">帮助中心</a></li>
                    </ul>
                </div>

                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!-- //footer -->
<div class="copy-right">
    <div class="container">
        <p>
            Copyright &copy; 2018-2019 All rights reserved. 版权所有：<a
                href="index.html">西农图书馆</a>
        </p>
    </div>
</div>
<!-- menu js aim -->
<script src="js2/jquery.menu-aim.js">

</script>
<script src="js2/main.js"></script>
<!-- Resource jQuery -->
<!-- //menu js aim -->
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>