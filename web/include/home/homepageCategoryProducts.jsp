<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<c:if test="${empty categorycount}">
	<c:set var="categorycount" scope="page" value="100"/>
</c:if>

<c:if test="${!empty param.categorycount}">
	<c:set var="categorycount" scope="page" value="${param.categorycount}"/>
</c:if>
<!--这个是产品列表-->
<div class="homepageCategoryProducts">
	<c:forEach items="${categoriesArrayList}" var="c" varStatus="stc">
		<c:if test="${stc.count<=categorycout}">
			<div class="eachHomepageCategoryProducts">
				<div class="left-mark"></div>
				<span class="categoryTitle">${c.name}</span>
				<br>
				<c:forEach items="${c.arrproduct}" var="p" varStatus="st">
					<c:if test="${st.count<=5}">
						<div class="productItem" >
							<a href="<%=request.getContextPath()%>/web/productdetil?pid=${p.id}">
									<img width="100px" src="<%=request.getContextPath()%>/img/productSingle_middle/
									<c:forEach items="${p.images}" var="c" varStatus="i">
										<c:if test="${i.index==0}">
										${c.id}
										</c:if>
									</c:forEach>
									.jpg">
							</a>
							<a class="productItemDescLink" href="<%=request.getContextPath()%>/web/productdetil?pid=${p.id}">
								<span class="productItemDesc">[热销]
								${fn:substring(p.name, 0, 20)}
								</span>
						    </a>
							<span class="productPrice">
								<fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/>
							</span>
						</div>
					</c:if>				
				</c:forEach>
				<div style="clear:both"></div>
			</div>
		</c:if>
	</c:forEach>
	
	
	<img id="endpng" class="endpng" src="<%=request.getContextPath()%>/img/site/end.png">

</div>