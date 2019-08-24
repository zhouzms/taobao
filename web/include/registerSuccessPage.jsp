
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

	<%@include file="header.jsp"%>
<script type="text/javascript">
	var num =3;
	var interval=setInterval(chock,1000);
	function chock(){
		if(num>0){
			$("#time").text(num+" "+"s");
		}else {
			clearInterval(interval);
			window.location.href="<%=request.getContextPath()%>/login.jsp";
		}
		num--;
	}
</script>
<div style="margin-top: 200px;margin-left:400px;width: 550px;height: 200px;text-align: center;">
	<div class="registerSuccessDiv">
		<img src="<%=request.getContextPath()%>/img/site/registerSuccess.png">
			恭喜注册成功
	</div>
	<div id="time" style="width: 70px;height: 70px;border-radius: 50%;background: red;color: white;font-size: 24px;line-height: 70px;margin-left: 235px;margin-top: 20px;"></div>
</div>