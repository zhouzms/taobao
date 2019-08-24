<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<div >
	<a href="<%=request.getContextPath()%>/home.jsp">
		<img id="simpleLogo" class="simpleLogo" src="<%=request.getContextPath()%>/img/site/simpleLogo.png">
	</a>

	<form action="<%=request.getContextPath()%>/web/searchThing" method="post" >
		<div class="searchDiv">
			<input name="kw" type="text"  placeholder="男表 女表 眼镜" id="kw">
			<button  type="submit" class="searchButton">搜索</button>
			<div class="searchBelow">
				<c:forEach items="${category}" var="c" varStatus="st">
					<c:if test="${st.count>=3 and st.count<=6}">
						<span>
							<a href="forecategory?cid=${c.id}">
									${c.name}
							</a>
							<c:if test="${st.count!=6}">
								<span>|</span>
							</c:if>
						</span>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</form>
	<div style="clear:both"></div>
</div>