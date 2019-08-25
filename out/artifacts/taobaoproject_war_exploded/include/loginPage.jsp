<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<script>
$(function(){
	<!--返回来的错误信息-->
	<c:if test="${msg!=null}">
	$("span.errorMessage").html("${msg.msg}");
	$("div.loginErrorMessageDiv").show();
	</c:if>
	
	$("form .loginForm").submit(function(){
		if(0==$("#name").val().length||0==$("#password").val().length){
			$("span.errorMessage").html("请输入账号密码");
			$("div.loginErrorMessageDiv").show();			
			return false;
		}
		return true;
	});
	<!--输入框集中时错误隐藏-->
	$("form.loginForm input").focus(function(){
		$("div.loginErrorMessageDiv").hide();	
	});
	
	
	<!--白色边框的位置为图片位置大小所算出来的-->
	var left = window.innerWidth/2+162;
	$("div.loginSmallDiv").css("left",left);
	<!--遍历cookie-->
	var strCookie = document.cookie;
	var arrCookie = strCookie.split("; ");
	var va1=null,va2=null;
	for(var i = 0; i < arrCookie.length; i++)
	{
		var arr = arrCookie[i].split("=");
		if("username" == arr[0])
		{
			va1=arr[1];
		}
		if("password"==arr[0]){
			va2=arr[1];
		}
	}
	$("#name").val(va1);
	$("#password").val(va2);
})
</script>


<div id="loginDiv" style="position: relative">

	<div class="simpleLogo">
		<a href="<%=request%>/home.jsp"><img src="<%=request.getContextPath()%>/img/site/simpleLogo.png"></a>
	</div>

	<!--图片-->
	<img id="loginBackgroundImg" class="loginBackgroundImg" src="<%=request.getContextPath()%>/img/site/loginBackground.png">
	<!--登陆框-->
	<form class="loginForm" action="<%=request.getContextPath()%>/web/WebUserServlet" method="post">
		<div id="loginSmallDiv" class="loginSmallDiv"><!--白色边框-->
			<div class="loginErrorMessageDiv">
				<div class="alert alert-danger" >
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
				  	<span class="errorMessage"></span>
				</div>
			</div>
				
			<div class="login_acount_text">密码登录</div>
			<div class="loginInput" >
				<span class="loginInputIcon ">
					<span class=" glyphicon glyphicon-user"></span>
				</span>
				<input id="name" name="name" placeholder="手机号/会员名/邮箱号" type="text">
			</div>
			
			<div class="loginInput " >
				<span class="loginInputIcon ">
					<span class=" glyphicon glyphicon-lock"></span>
				</span>
				<input id="password" name="password" type="password" placeholder="密码" type="text">
			</div>
			<input type="checkbox" name="checkflag" checked>&nbsp;&nbsp;&nbsp;
			<b style="color: black;font-size: 14px;display: inline-block;position: relative;top: -3.4px;">记住帐号 (公共电脑不建议使用)</b><br><br>
			<div>
				<!--暂时不做-->
				<a class="notImplementLink" href="#nowhere">忘记登录密码</a>
				<a href="<%=request.getContextPath()%>/register.jsp" class="pull-right">免费注册</a>
			</div>
			<div style="margin-top:20px">
				<button class="btn btn-block redButton" type="submit">登录</button>
			</div>
		</div>	
	</form>


</div>	