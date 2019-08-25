<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<script>
$(function(){
	$("div.productsAsideCategorys div.row a").each(function(){
		var v = Math.round(Math.random() *10);
		if(v == 3){$(this).css("color","red");}

	});
});
</script>
<c:forEach items="${categoriesArrayList}" var="c">
	<div cid="${c.id}" class="productsAsideCategorys">
		<c:forEach items="${c.arrproduct}" var="p" >
			<div class="row">
					<c:if test="${!empty p.subTitle}">
						<a href="<%=request.getContextPath()%>/web/productdetil?pid=${p.id}">
							<c:forEach items="${fn:split(p.subTitle, ' ')}" var="title" varStatus="st">
									${title}
							</c:forEach>
						</a>
					</c:if>
			</div>
		</c:forEach>
	</div>
</c:forEach>

