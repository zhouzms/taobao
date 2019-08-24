
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	
<div class="searchProducts">
	
	<c:forEach items="${products}" var="p">
		<div class="productUnit" price="${p.promotePrice}">
			<a href="<%=request.getContextPath()%>/web/productdetil?pid=${p.id}">
				<img class="productImage" src="<%=request.getContextPath()%>/img/productSingle/
										<c:forEach items="${p.images}" var="c" varStatus="i">
										<c:if test="${i.index==0}">
										${c.id}
										</c:if>
									</c:forEach>
								.jpg">
			</a>
			<span class="productPrice">¥<fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/></span>
			<a class="productLink" href="<%=request.getContextPath()%>/web/productdetil?pid=${p.id}">
			 ${fn:substring(p.name, 0, 50)}
			</a>
			
			<a class="tmallLink" href="<%=request.getContextPath()%>/web/productdetil?pid=${p.id}">天猫专卖</a>

			<div class="show1 productInfo">
				<%--<span class="monthDeal">月成交 <span class="productDealNumber">${p.saleCount}笔</span></span>--%>
				<c:if test="${p.reviews!=null}"></c:if>
				<span class="productReview">评价<span class="productReviewNumber">
					<!--没办法el表达式没用-->
					<% int i=0;%>
						<c:forEach items="${p.reviews}" varStatus="s">
							<%i++;%>
						</c:forEach>
					<%=i%>
					<%--${fn:length(p.reviews)}--%>
				</span></span>
				<span class="wangwang"><img src="<%=request.getContextPath()%>/img/site/wangwang.png"></span>
			</div>
			
		</div>
	</c:forEach>
	<c:if test="${empty products}">
		<div class="noMatch">没有满足条件的产品<div>
	</c:if>
	
		<div style="clear:both"></div>
		</div></div>