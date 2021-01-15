<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图书详情</title>
<base href="../" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Custom Theme files -->
<link href="css2/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css2/style.css" rel="stylesheet" type="text/css" media="all" /> 
<link href="css2/animate.min.css" rel="stylesheet" type="text/css" media="all" /><!-- animation -->
<link href="css2/menu.css" rel="stylesheet" type="text/css" media="all" /> <!-- menu style -->   
<link href="css2/owl.carousel.css" rel="stylesheet" type="text/css" media="all"> <!-- carousel slider -->  
<!-- //Custom Theme files -->
<!-- font-awesome icons -->
<link href="css2/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
<!-- js -->
<script src="js2/jquery-2.2.3.min.js"></script> 
<script src="js2/owl.carousel.js"></script>
<script src="js2/bootstrap.js"></script>
<!--flex slider-->
<script defer src="js2/jquery.flexslider.js"></script>
<link rel="stylesheet" href="css2/flexslider.css" type="text/css" media="screen" />
<script>
	// Can also be used with $(document).ready()
	$(window).load(function() {
	  $('.flexslider').flexslider({
		animation: "slide",
		controlNav: "thumbnails"
	  });
	});
</script>
<!--flex slider-->
<script src="js2/imagezoom.js"></script>
<!-- //js --> 
<!-- web-fonts -->
<link href='https://fonts.googleapis.com/css?family=Roboto+Condensed:400,300,300italic,400italic,700,700italic' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Lovers+Quarrel' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Offside' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Tangerine:400,700' rel='stylesheet' type='text/css'>
<!-- web-fonts --> 
<!-- scroll to fixed--> 
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
                marginTop: $('.header-two').outerHeight(true) + 10, 
                zIndex: 999
            });
        });
    });
</script>
<!-- //scroll to fixed--> 
<!-- start-smooth-scrolling -->
<script type="text/javascript" src="js2/move-top.js"></script>
<script type="text/javascript" src="js2/easing.js"></script>	
<script type="text/javascript">
		jQuery(document).ready(function($) {
			$(".scroll").click(function(event){		
				event.preventDefault();
				$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
			});
		});
</script>
<!-- //end-smooth-scrolling -->
<!-- smooth-scrolling-of-move-up -->
<script type="text/javascript">
	$(document).ready(function() {
	
		var defaults = {
			containerID: 'toTop', // fading element id
			containerHoverID: 'toTopHover', // fading element hover id
			scrollSpeed: 1200,
			easingType: 'linear' 
		};
		
		$().UItoTop({ easingType: 'easeOutQuart' });
		
	});
</script>
<!-- //smooth-scrolling-of-move-up -->  
</head>
<body>
<div id="collectionAlert" class="alert alert-success" style="position:fixed;top:10px;width:100%" >
						    <a href="#" class="close" data-dismiss="alert">
						        &times;
						    </a>
						    <strong>成功！</strong>收藏添加成功！。
						</div>
						<script >
						$("#collectionAlert").hide();
						</script>
	<!-- header -->
	<div class="header">
		<div class="w3ls-header"><!--header-one--> 
			<div class="w3ls-header-left">
				<p><a href="index.jsp"><i class="fa fa-gift" aria-hidden="true"></i>  云图书</a></p>
			</div>
			<div class="w3ls-header-right">
				<ul>
					<li class="dropdown head-dpdn"><a
						href="goProfile" class="dropdown-toggle"><i
							class="fa fa-user" aria-hidden="true"></i> 个人中心</a></li>
					<li class="dropdown head-dpdn"><a href="user-exit"
						class="dropdown-toggle"><i class="fa fa-map-marker"
							aria-hidden="true"></i> 退出</a></li>
					
				</ul>
			</div>
			<div class="clearfix"> </div> 
		</div>
		<div class="header-two"><!-- header-two -->
			<div class="container">
				<div class="header-logo">
					<div class="logo-img"><h1><a href="index.jsp"><img src="./images/xiaohui.jpg"></a></h1></div>
				</div>	
				<div class="header-search">
					<form action="search" method="post">
						<input type="search" name="search" required="">
						<button type="submit" class="btn btn-default" aria-label="Left Align">
							<i class="fa fa-search" aria-hidden="true"> </i>
						</button>
					</form>
				</div>
				<div class="header-cart"> 
					<div class="my-account">
						<a href="contact.html"><i class="fa fa-map-marker" aria-hidden="true"></i> 联系管理员</a>						
					</div>
					<div class="cart"> 
							<a href="user-center/collection.html"><button class="w3view-cart" type="button" name="submit" value=""><i class="fa fa-heart" aria-hidden="true"></i></button></a>
						</form>  
					</div>
					<div class="clearfix"> </div> 
				</div> 
				<div class="clearfix"> </div>
			</div>		
		</div><!-- //header-two -->
		<div class="header-three">
			<!-- header-three -->
			<div class="container">
				<div class="menu">
					<div class="cd-dropdown-wrapper">
						<a class="cd-dropdown-trigger" href="#0"
							style="font-family: SimHei;">所有图书分类</a>
						<nav class="cd-dropdown">
							<a href="#0" class="cd-close">Close</a>
							<ul class="cd-dropdown-content">
								<li class="has-children"><a href="#"><i
										class="fa fa-tasks" aria-hidden="true"></i> &nbsp;索书号ABCDE</a>
									<ul class="cd-secondary-dropdown is-hidden">
										<li class="go-back"><a href="#">Menu</a></li>
										<li class="see-all"><a href="../product/all.do">查看所有图书</a></li>
										<li><a href="../product/all.do">索书号A</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#">All Electronics</a></li>
												<li>马克思主义</li>
												<li>列宁主义</li>
												<li>毛泽东思想</li>
												<li>邓小平理论</li>
												<li><a href="../product/all.do">more</a></li>
											</ul></li>
										<li><a href="../product/all.do">索书号B</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#">All Electronics </a></li>
												<li>哲学</li>
												<li>宗教</li>
												<li><a href="products.html">more</a></li>
											</ul></li>
										<li><a href="../product/all.do">索书号C</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#">All Electronics</a></li>
												<li>社会科学总论</li>
												<li><a href="products.html">more</a></li>
											</ul></li>
										<li><a href="../product/all.do">索书号D</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#0">All Electronics </a></li>
												<li>政治</li>
												<li>法律</li>
												<li><a href="products.html">more</a></li>
											</ul></li>
										<li><a href="../product/all.do">索书号E</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#0">All Electronics </a></li>
												<li>军事理论</li>
												<li>世界军事</li>
												<li>战略学</li>
												<li><a href="products.html">more</a></li>
											</ul></li>
										
									</ul> <!-- .cd-secondary-dropdown --></li>
								<!-- .has-children -->
								<li class="has-children"><a href="#"><i
										class="fa fa-tasks" aria-hidden="true"></i> &nbsp;索书号FGHIJ</a>
									<ul class="cd-secondary-dropdown is-hidden">
										<li class="go-back"><a href="#">Menu</a></li>
										<li class="see-all"><a href="../product/all.do">查看所有图书</a></li>
										<li><a href="../product/all.do">索书号F</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#">All Fashion Stores</a></li>
												<li>经济学</li>
												<li>农业、工业经济</li>
												<li>各国经济</li>
												<li><a href="products1.html">more</a></li>
											</ul></li>
										<li><a href="../product/all.do">索书号G</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#">All Fashion Stores</a></li>
												<li>文化</li>
												<li>科学</li>
												<li>教育</li>
												<li>体育</li>
												<li><a href="products1.html">more</a></li>
											</ul></li>
										<li><a href="../product/all.do">索书号H</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#">All Fashion Stores</a></li>
												<li>语言</li>
												<li>文字</li>
												<li><a href="../product/all.do">more</a></li>
											</ul></li>
											<li><a href="../product/all.do">索书号I</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#">All Fashion Stores</a></li>
												<li>世界文学</li>
												<li>中国文学</li>
												<li><a href="../product/all.do">more</a></li>
											</ul></li>
											<li><a href="../product/all.do">索书号J</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#">All Fashion Stores</a></li>
												<li>艺术</li>
												<li><a href="../product/all.do">more</a></li>
											</ul></li>
											
									</ul> <!-- .cd-secondary-dropdown --></li>
								<!-- .has-children -->
								<li class="has-children"><a href="products2.html"><i
										class="fa fa-tasks" aria-hidden="true"></i>&nbsp; 索书号KNOPQ</a>
									<ul class="cd-secondary-dropdown is-hidden">
										<li class="go-back"><a href="#">Menu</a></li>
										<li class="see-all"><a href="../product/all.do">查看所有图书</a></li>
										<li><a href="../product/all.do">索书号K</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#">All Kids Fashions</a></li>
												<li>历史</li>
												<li>地理</li>
												
												<li><a href="../product/all.do">more</a></li>
											</ul></li>
										<li><a href="../product/all.do">索书号N</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#">All Kids Fashions</a></li>
												<li>自然科学总论</li>
												<li><a href="../product/all.do">more</a></li>
											</ul></li>
										<li><a href="../product/all.do">索书号O</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#">All Kids Fashions</a></li>
												<li>数理科学</li>
												<li>化学</li>
												<li><a href="#">more</a></li>
											</ul></li>
									
									<li><a href="../product/all.do">索书号P</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#">All Kids Fashions</a></li>
												<li>天文学</li>
												<li>地球科学</li>
												<li><a href="#">more</a></li>
											</ul></li>
									
									<li><a href="../product/all.do">索书号Q</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#">All Kids Fashions</a></li>
												<li>生物科学</li>
												
												<li><a href="#">more</a></li>
											</ul></li>
									</ul></li>
								<li class="has-children"><a href="#"><i
										class="fa fa-tasks" aria-hidden="true"></i>&nbsp;索书号RSTUV</a>
									<ul class="cd-secondary-dropdown is-hidden">
										<li class="go-back"><a href="#">Menu</a></li>
										<li class="see-all"><a href="products3.html">查看所有图书</a></li>
										<li><a href="../product/all.do">索书号R</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#"></a></li>
												<li>医药</li>
												<li>卫生</li>
												
												<li><a href="../product/all.do">more</a></li>
											</ul></li>
										<li><a href="../product/all.do">索书号S </a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#"></a></li>
												<li>农业科学</li>
												<li><a href="products3.html">more</a></li>
											</ul></li>
										<li><a href="../product/all.do">索书号T</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#"></a></li>
												<li>工业技术</li>
												<li><a href="products3.html">more</a></li>
											</ul></li>
											<li><a href="../product/all.do">索书号U</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#"></a></li>
												<li>交通运输</li>
												<li><a href="products3.html">more</a></li>
											</ul></li>
											<li><a href="../product/all.do">索书号V</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#"></a></li>
												<li>航空</li>
												<li>航天</li>
												<li><a href="products3.html">more</a></li>
											</ul></li>
									</ul> <!-- .cd-secondary-dropdown --></li>
								<!-- .has-children -->
								<li class="has-children"><a href="#"><i
										class="fa fa-tasks" aria-hidden="true"></i>&nbsp;索书号XZ</a>
									<ul class="cd-secondary-dropdown is-hidden"
										style="height: 450px;">
										<li class="go-back"><a href="#">Menu</a></li>
										<li class="see-all"><a
											style="position: relative; bottom: 5px;"
											href="../product/all.do">查看所有图书</a></li>
										<li><a href="../product/all.do">索书号X</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#"></a></li>
												<li>环境科学</li>
												<li>安全科学</li>
												
												<li><a href="../product/all.do">more</a></li>
											</ul></li>
										<li><a href="../product/all.do">索书号Z</a>
											<ul class="is-hidden">
												<li class="go-back"><a href="#"></a></li>
												<li>综合图书</li>
												<li>辞典</li>
												<li>百科全书</li>
												<li>期刊</li>
												
												<li><a href="../product/all.do">more</a></li>
											</ul></li>
										
									</ul> <!-- .cd-secondary-dropdown --></li>
								<!-- .has-children -->

							</ul>
							<!-- .cd-dropdown-content -->
						</nav>
						<!-- .cd-dropdown -->
					</div>
					<!-- .cd-dropdown-wrapper -->
				</div>
				<div class="move-text">
					<div class="marquee"><a href="offers.html"> 好的书籍是最贵重的珍宝。 <span>书籍就像一盏神灯，它照亮人们最遥远最黯淡的生活道路。
						</span> <span> 带一卷书，走十里路，选一个清净地，看天，听鸟，倦了时，和身在草绵绵处寻梦去。
</span></a></div>
					<script type="text/javascript" src="js2/jquery.marquee.min.js"></script>
					<script>
					  $('.marquee').marquee({ pauseOnHover: true });
					  //@ sourceURL=pen.js
					</script>
				</div>
			</div>
		</div>
	</div>
	<!-- //header -->	

	<!-- breadcrumbs --> 
	<div class="container"> 
		<ol class="breadcrumb breadcrumb1">
			<li><a href="index">首页</a></li>
			<li class="active">图书详情</li>
		</ol> 
		<div class="clearfix"> </div>
	</div>
	<!-- //breadcrumbs -->
	<!-- products -->
	<div class="products">	 
		<div class="container">  
			<div class="single-page">
				<div class="single-page-row" id="detail-21">
					<div class="col-md-6 single-top-left">	
						<div class="flexslider">
							<ul class="slides">
								<li data-thumb="./images/s1.jpg">
									<div class="thumb-image detail_images"> <img src="${detail.BOOK_IMAGES}" data-imagezoom="true" class="img-responsive" alt="" style="width:300px"> </div>
								</li>
							</ul>
						</div>
					</div>
					<div class="col-md-6 single-top-right">
						<h3 class="item_name" style="color:black">${detail.BOOK_TITLE}</h3>
						<br>
						<div class="single-rating">
							<ul>
								<li><i class="fa fa-star-o" aria-hidden="true"></i></li>
								<li><i class="fa fa-star-o" aria-hidden="true"></i></li>
								<li><i class="fa fa-star-o" aria-hidden="true"></i></li>
								<li><i class="fa fa-star-o" aria-hidden="true"></i></li>
								<li><i class="fa fa-star-o" aria-hidden="true"></i></li>
								<li class="rating">图书评分：5</li>
								
							</ul> 
						</div>
						<div class="single-price">
							<ul style="color:#999">
								作者<li><span class="w3off" style="font-size:0.7em">${detail.BOOK_AUTHOR}</span></li>
								出版社<li><span class="w3off" style="font-size:2em">${detail.BOOK_PUBLISHER}</span></li>   
							</ul>	
						</div> 
						<p class="single-price-text"></p>
						<form action="../order/makeOrder.do" method="post" onSubmit="return isnull()">
							<input type="hidden" name="name" value=""/>
							<input type="hidden" name="cashPledge" value="" /> 
							<input type="hidden" name="rent" value="" />
							<input type="hidden" name="img" value="" />
							<input type="hidden" name="userId" value="" />
							
							<input type="hidden" name="goodsId" value=""/>
							
							
						</form>
						
						<form>
						    <button type="button" class="w3ls-cart" id="addCollection"><i class="fa fa-heart-o" aria-hidden="true"></i> 加入收藏</button>
						</form>
						<script>
						
						var x=${pro.goodsid};
						$("#addCollection").click(function(){
							$.ajax({
				                type: 'POST',
				                url: "../product/detail.do",
				                data:{"collectionGoodsId":x}
				            
				            });
							
							$("#collectionAlert").slideDown(100);
							$('#collectionAlert').delay(1000).hide(100);
							
				});
						</script>
						
						
</div>
				   <div class="clearfix"> </div>  
				</div>
				<div class="single-page-icons social-icons"> 
					<ul>
						<li><h4>分享</h4></li>
						<li><a href="#" class="fa fa-facebook icon facebook"> </a></li>
						<li><a href="#" class="fa fa-twitter icon twitter"> </a></li>
						<li><a href="#" class="fa fa-google-plus icon googleplus"> </a></li>
						<li><a href="#" class="fa fa-dribbble icon dribbble"> </a></li>
						<li><a href="#" class="fa fa-rss icon rss"> </a></li> 
					</ul>
				</div>
			</div> 
			<!-- collapse-tabs -->
			<div class="collpse tabs">
				<h3 class="w3ls-title">关于图书</h3> 
				<div class="panel-group collpse" id="accordion" role="tablist" aria-multiselectable="true">
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingOne">
							<h4 class="panel-title">
								<a class="pa_italic" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
									<i class="fa fa-file-text-o fa-icon" aria-hidden="true"></i> 图书详情 <span class="fa fa-angle-down fa-arrow" aria-hidden="true"></span> <i class="fa fa-angle-up fa-arrow" aria-hidden="true"></i>
								</a>
							</h4>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">
								${detail.description}
								<br>
								<img src="${detail.BOOK_IMAGES}" data-imagezoom="true" class="img-responsive"  style="width:300px">
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingThree">
							<h4 class="panel-title">
								<a class="collapsed pa_italic" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
									<i class="fa fa-check-square-o fa-icon" aria-hidden="true"></i> 图书评价 <span class="fa fa-angle-down fa-arrow" aria-hidden="true"></span> <i class="fa fa-angle-up fa-arrow" aria-hidden="true"></i>
								</a>
							</h4>
						</div>
						<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree" style="height:auto">
							<div class="panel-body" style="height:280px">
								<ul style="list-style:none;width:100%">
									<li style="position:relative;top:10px">
									 <div style="float:left;width:100%;">
										<div style="float:left;width:100px;text-align:center;line-height:1.5;box-sizing: border-box;">
										<img src="images/head.jpg" style="width:20px;height:20px;">
										<div>324***541</div>
										</div>
										<div style="position:relative;margin-left:0px;zoom:1;width:100%;">${comment[0].description}</div>
									 </div>
									</li>
									<li style="position:relative;top:35px">
									 <div style="float:left;width:100%;">
										<div style="float:left;width:100px;text-align:center;line-height:1.5;box-sizing: border-box;">
										<img src="images/head.jpg" style="width:20px;height:20px;">
										<div>450***478</div>
										</div>
										<div style="position:relative;margin-left:0px;zoom:1;width:100%;">这本书很吸引我，书的内容很充实，是一本值得研读的好书！</div>
									 </div>
									</li>
									<li style="position:relative;top:60px">
									 <div style="float:left;width:100%">
										<div style="float:left;width:100px;text-align:center;line-height:1.5;box-sizing: border-box;">
										<img src="images/head.jpg" style="width:20px;height:20px;">
										<div>780***120</div>
										</div>
										<div style="position:relative;margin-left:0px;zoom:1;width:100%;">书中自有黄金屋，这本书让我学习到很多我需要的知识</div>
									 </div>
									</li>
									
								</ul>
							</div>
						</div>
					</div>
					
				</div>
			</div>
			<!-- //collapse --> 
			
		</div>
	</div>
	<!--//products-->  
	<!-- footer-top -->
	<div class="footer">
		<div class="container">
			<div class="footer-info w3-agileits-info">
				<div class="col-md-4 address-left agileinfo">
					<div class="footer-logo header-logo">
						<h2>
							<a href="index.html"><img src="./images/xiaohui.jpg">
						</h2>

						</a>
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
					href="index.jsp">西农图书馆</a>
			</p>
		</div>
	</div>
	
	<script type="text/javascript">
	 	

	    function isnull() {
	    	var oTxt = document.getElementById('duration');
	    	
	        var str = oTxt.value.replace(/(^\s*)|(\s*$)/g, '');//去除空格;

	        if (str == '' || str == undefined || str == null) {
	            alert('请输入租借时长！');
	            return false;
	        } else {
	            return true;
	        }
	    }

	</script> 
	<!-- menu js aim -->
	<script src="js2/jquery.menu-aim.js"> </script>
	<script src="js2/main.js"></script> <!-- Resource jQuery -->
	<!-- //menu js aim --> 
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster --> 
</body>
</html>