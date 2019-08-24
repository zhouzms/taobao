<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	<a href="<%=request.getContextPath()%>/home.jsp">
		<img id="logo" src="<%=request.getContextPath()%>/img/site/logo.gif" class="logo">
	</a>
	<form action="<%=request.getContextPath()%>/web/searchThing" method="post" >
		<div class="searchDiv">
			<input name="kw" type="text"  placeholder="时尚男鞋  眼镜 " id="kw">
			<button  type="submit" class="searchButton">搜索</button>
			<div class="searchBelow">
				<c:forEach items="${category}" var="c" varStatus="st">
					<c:if test="${st.count>=4 and st.count<=7}">
						<span>
							<a href="forecategory?cid=${c.id}">
								${c.name}
							</a>
							<c:if test="${st.count!=7}">
								<span>|</span>				
							</c:if>
						</span>			
					</c:if>
				</c:forEach>		
			</div>
		</div>
	</form>	
