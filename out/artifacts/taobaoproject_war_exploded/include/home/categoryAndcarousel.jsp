
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>


<script>
function showProductsAsideCategorys(cid){
	$("div.eachCategory[cid="+cid+"]").css("background-color","red");
	$("div.eachCategory[cid="+cid+"] a").css("color","white");
	$("div.productsAsideCategorys[cid="+cid+"]").show();
}


function hideProductsAsideCategorys(cid){
	$("div.eachCategory[cid="+cid+"]").css("background-color","#e2e2e3");
	$("div.eachCategory[cid="+cid+"] a").css("color","#000");
	$("div.productsAsideCategorys[cid="+cid+"]").hide();
}
$(function(){
	<!--左右链接的变色-->
    $("div.eachCategory").mouseenter(function(){
        var cid = $(this).attr("cid");
        showProductsAsideCategorys(cid);
    });
    $("div.eachCategory").mouseleave(function(){
        var cid = $(this).attr("cid");
        hideProductsAsideCategorys(cid);
    });
    $("div.productsAsideCategorys").mouseenter(function(){
    	var cid = $(this).attr("cid");
    	showProductsAsideCategorys(cid);
    });
    $("div.productsAsideCategorys").mouseleave(function(){
    	var cid = $(this).attr("cid");
    	hideProductsAsideCategorys(cid);
    });
	<!--导航栏上的猫-->
	$("div.rightMenu span").mouseenter(function(){
		var left = $(this).position().left;
		var top = $(this).position().top;
		var width = $(this).css("width");
		var destLeft = parseInt(left) + parseInt(width)/2;
		$("img#catear").css("left",destLeft);
		$("img#catear").css("top",top-20);
		$("img#catear").fadeIn(200);
				
	});
	$("div.rightMenu span").mouseleave(function(){
		$("img#catear").hide();
	});
	
	var left = $("div#carousel-of-product").offset().left;
	$("div.categoryMenu").css("left",left-20);
	$("div.categoryWithCarousel div.head").css("margin-left",left);
	$("div.productsAsideCategorys").css("left",left-20);
	
	
});
</script>

<img src="<%=request.getContextPath()%>/img/site/catear.png" id="catear" class="catear"/>
	
<div class="categoryWithCarousel">


<div class="headbar show1">
	<div class="head ">
	
		<span style="margin-left:10px" class="glyphicon glyphicon-th-list"></span>
		<span style="margin-left:10px" >商品分类</span>
		
	</div>
	<!--导航栏上面的文字-->
	<div class="rightMenu">
		<span><a href=""><img src="<%=request.getContextPath()%>/img/site/chaoshi.png"/></a></span>
		<span><a href=""><img src="<%=request.getContextPath()%>/img/site/guoji.png"/></a></span>
		<c:forEach items="${category}" var="c" begin="1" end="4">
				<span>
				<a href="<%=request.getContextPath()%>/web/productdetil?cid=${c.id}">
					${c.name}
				</a></span>
		</c:forEach>
	</div>
	
</div>

	<!--链接栏 （平板电脑）-->
<div style="position: relative">
	<%@include file="categoryMenu.jsp" %>
</div>
<!--旁边属性-->
<div style="position: relative;left: 0;top: 0;">
	<%@include file="productsAsideCategorys.jsp" %>
</div>


<!--图片轮播-->
<%@include file="carousel.jsp" %>

<div class="carouselBackgroundDiv">
</div>

</div>





