<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">

<link rel="stylesheet" href="../../css/mescroll.min.css">
<%
	String totlesize = String.valueOf(request.getAttribute("totlesize"));
%>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	-webkit-touch-callout: none;
	-webkit-user-select: none;
	-webkit-tap-highlight-color: transparent;
}

body {
	background-color: white
}

ul {
	list-style-type: none
}

a {
	text-decoration: none;
	color: #18B4FE;
}

/*模拟的标题*/
.header {
	z-index: 9990;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 44px;
	line-height: 44px;
	text-align: center;
	border-bottom: 1px solid #eee;
	background-color: white;
}

.header .btn-left {
	position: absolute;
	top: 0;
	left: 0;
	padding: 12px;
	line-height: 22px;
}

.header .btn-right {
	position: absolute;
	top: 0;
	right: 0;
	padding: 0 12px;
}
/*说明*/
.mescroll .notice {
	font-size: 14px;
	padding: 20px 0;
	border-bottom: 1px solid #eee;
	text-align: center;
	color: #555;
}
/*列表*/
.mescroll {
	position: fixed;
	top: 44px;
	bottom: 0;
	height: auto;
}
/*展示上拉加载的数据列表*/
.news-list li {
	padding: 16px;
	border-bottom: 1px solid #eee;
}

.news-list .new-content {
	font-size: 14px;
	margin-top: 6px;
	margin-left: 10px;
	color: #666;
}
</style>
</head>

<body>
	<!--标题-->
	<p class="header">
		<a class="btn-left" href="../index.html">main</a> 新闻列表 <a
			class="btn-right" href="list-products.html">list-products</a>
	</p>
	<!--滑动区域-->
	<div id="mescroll" class="mescroll">
		<p class="notice">本Demo的下拉刷新: 添加新数据到列表顶部</p>
		<ul id="newsList" class="news-list">
		</ul>
	</div>
</body>

<script src="../../js/mescroll.min.js" type="text/javascript"
	charset="utf-8"></script>
<!--mescroll本身不依赖jq,这里为了模拟发送ajax请求-->
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
		$(function(){
			//创建MeScroll对象
			var s=new Array();
			$.ajax({
		data :"申明:本书由奇书网(Www.Qisuu.Com)自网络收集整理制作,仅供预览交流学习使用,版权归原作者和出版社所有,如果喜欢,请支持订阅购买正版.阿达发发",
		url : "https://nlp-ext.cn-north-4.myhuaweicloud.com/v1/nlp-ext.cn-north-4.myhuaweicloud.com/nlp-fundamental/segment",
		type : "POST",
		dataType : "JSON",
		success : function(httpdata) {
			s=httpdata
		}
				});
			var mescroll = new MeScroll("mescroll", {
				down: {
					auto: false, //是否在初始化完毕之后自动执行下拉回调callback; 默认true
					callback: downCallback //下拉刷新的回调
				},
				up: {
					auto: true, //是否在初始化时以上拉加载的方式自动加载第一页数据; 默认false
					isBounce: false, //此处禁止ios回弹,解析(务必认真阅读,特别是最后一点): http://www.mescroll.com/qa.html#q10
					callback: upCallback, //上拉回调,此处可简写; 相当于 callback: function (page) { upCallback(page); }
					toTop:{ //配置回到顶部按钮
						src : "../res/img/mescroll-totop.png", //默认滚动到1000px显示,可配置offset修改
						//offset : 1000
					}
				}
			});
			
			/*下拉刷新的回调 */
			function downCallback(){
				//联网加载数据
				getListDataFromNet(0, 1, function(data){
					//联网成功的回调,隐藏下拉刷新的状态
					mescroll.endSuccess();
					//设置列表数据
					setListData(data, false);
				}, function(){
					//联网失败的回调,隐藏下拉刷新的状态
	                mescroll.endErr();
				});
			}
			
			/*上拉加载的回调 page = {num:1, size:10}; num:当前页 从1开始, size:每页数据条数 */
			function upCallback(page){
				console.log("1");
				//联网加载数据
				getListDataFromNet(page.num, page.size, function(curPageData){
					console.log("2");
					//联网成功的回调,隐藏下拉刷新和上拉加载的状态;
					//mescroll会根据传的参数,自动判断列表如果无任何数据,则提示空;列表无下一页数据,则提示无更多数据;
					var tt='<%=totlesize%>';
					console.log("page.num="+page.num+", page.size="+page.size+", curPageData.length="+curPageData.length);
					
					//方法一(推荐): 后台接口有返回列表的总页数 totalPage
					//mescroll.endByPage(curPageData.length, totalPage); //必传参数(当前页的数据个数, 总页数)
					
					//方法二(推荐): 后台接口有返回列表的总数据量 totalSize
					mescroll.endBySize(curPageData.length,tt); //必传参数(当前页的数据个数, 总数据量)
					
					//方法三(推荐): 您有其他方式知道是否有下一页 hasNext
					//mescroll.endSuccess(curPageData.length, hasNext); //必传参数(当前页的数据个数, 是否有下一页true/false)
					
					//方法四 (不推荐),会存在一个小问题:比如列表共有20条数据,每页加载10条,共2页.如果只根据当前页的数据个数判断,则需翻到第三页才会知道无更多数据,如果传了hasNext,则翻到第二页即可显示无更多数据.
					//mescroll.endSuccess(curPageData.length,tt);
					
					//提示:curPageData.length必传的原因:
					// 1.判断是否有下一页的首要依据: 当传的值小于page.size时,则一定会认为无更多数据.
					// 2.比传入的totalPage, totalSize, hasNext具有更高的判断优先级
					// 3.使配置的noMoreSize生效
					
					//设置列表数据
					setListData(curPageData, true);
				}, function(){
					//联网失败的回调,隐藏下拉刷新和上拉加载的状态;
	                mescroll.endErr();
				});
			}
			
			/*设置列表数据*/
			function setListData(curPageData, isAppend) {
				console.log("3");
				var listDom=document.getElementById("newsList");
				for (var i = 0; i < curPageData.length; i++) {
					var newObj=curPageData[i];
					
					var str='<p>'+newObj.title+'</p>';
					str+='<p class="new-content">'+newObj.content+'</p>';
					
					var liDom=document.createElement("li");
					liDom.innerHTML=str;
					
					if (isAppend) {
						listDom.appendChild(liDom);//加在列表的后面,上拉加载
					} else{
						listDom.insertBefore(liDom, listDom.firstChild);//加在列表的前面,下拉刷新
					}
				}
			}
			
			/*联网加载列表数据
			 在您的实际项目中,请参考官方写法: http://www.mescroll.com/api.html#tagUpCallback
			 请忽略getListDataFromNet的逻辑,这里仅仅是在本地模拟分页数据,本地演示用
			 实际项目以您服务器接口返回的数据为准,无需本地处理分页.
			 * */
			var downIndex=0;
			function getListDataFromNet(pageNum,pageSize,successCallback,errorCallback) {
				//延时一秒,模拟联网
				console.log("4");
                setTimeout(function () {
                	try{
                		var newArr=[];
	                	if(pageNum==0){
	                		//此处模拟下拉刷新返回的数据
	                		downIndex++;
	                		var newObj={title:"hello", content:"hello"};
	                		newArr.push(newObj);
	                	}else{
	                		//此处模拟上拉加载返回的数据
	                		for (var i = 0; i < pageSize; i++) {
	                			var upIndex=(pageNum-1)*pageSize+i;
	                			var newObj={title:s[upIndex].text, content:s[upIndex].time};
	                			console.log(newObj);
	                			newArr.push(newObj);
	                		}
	                	}
	                	//联网成功的回调
	                	successCallback&&successCallback(newArr);
                	}catch(e){
                		//联网失败的回调
                		successCallback&&successCallback(newArr);
                	}
                },1000)
		
			}
				
		});
	</script>
	
</html>