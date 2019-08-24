<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<nav class="top">
		<a href="<%=request.getContextPath()%>/home.jsp">
			<span  style="color: red;" class=" glyphicon glyphicon-home "></span>
			天猫首页
		</a>	
		
		<span>喵，欢迎来天猫</span>
		<c:if test="${ sessionScope.user!=null}">
			<a href="<%=request.getContextPath()%>/login.jsp" style="color:blue;"><b style="color:#999;">昵称:&nbsp;</b>${sessionScope.user.username_l}</a>
			<a href="<%=request.getContextPath()%>/userexit.jsp">退出</a>
		</c:if>
		
		<c:if test="${sessionScope.user==null}">
			<a href="<%=request.getContextPath()%>/login.jsp">请登录</a>
			<a href="<%=request.getContextPath()%>/register.jsp">免费注册</a>
		</c:if>


		<span class="pull-right">
			<c:if test="${ sessionScope.user!=null}">
				<a href="<%=request.getContextPath()%>/forebought">我的订单</a>
				<a href="<%=request.getContextPath()%>/forecart">
			<span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-shopping-cart redColor"></span>
			购物车  <strong style="color: blue">10</strong>件</a>
			</c:if>
	<c:if test="${ sessionScope.user==null}">
			<a href="<%=request.getContextPath()%>/login.jsp">我的订单</a>
			<a href="<%=request.getContextPath()%>/login.jsp">
				<i class="glyphicon glyphicon-shopping-cart" style="color: red;margin-left: 10px;"></i>&nbsp;&nbsp;我的购物车
			</a>
	</c:if>
		</span>
</nav>



