
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

	
<script type="text/javascript">
$(function(){
	<!--返回来的错误信息 msg.msg为空-->
	<c:if test="${msg!=null}">
	$("span.errorMessage").html("${msg.msg}");
	$("div.registerErrorMessageDiv").css("visibility","visible");
	</c:if>
	<!--检查验证码-->
	/*倒计时*/
	function time(){
		var num =30;
		var interval=setInterval(chock,1000);
		function chock(){
			if(num>0){
				$("#checkcodesend").text(num+" "+"s");
				$("#checkcodesend").attr('disabled',true);
			}else {
				clearInterval(interval);
				$("#checkcodesend").attr('disabled',false);
				$("#checkcodesend").text("发送验证码");
			}
			num--;
		}
	}
	$("#checkcodesend").click(function () {
		$("#checkcode").focus();
		//发送验证码
		$.ajax( {
			url:"web/getcode",
			type:"post",
			dataType:"json",
			data:{
				"phonenum":$("#regphone").val()
			},
			beforeSend:function(data){
				/*将其中的发送变为数字*/
				//检查手机号码格式
				var reg = /(1[3-9]\d{9}$)/;
				if ($("#regphone").val().length<=0){
					$("span.errorMessage").html("手机号不为空");
					$("div.registerErrorMessageDiv").css("visibility","visible");
					$("#regphone").focus();
					return false;
				}else if(!reg.test($("#regphone").val())){
					$("span.errorMessage").html("手机格式不正确");
					$("div.registerErrorMessageDiv").css("visibility","visible");
					$("#regphone").focus();
					return false;
				}else {
					time();
					$("span.errorMessage").html("短信发送成功，请注意查收");
					$("div.registerErrorMessageDiv").css("visibility","visible");
					return true;
				}
			},
			success: function (data) {
				if(data.flag){
					//发送成功
					$("span.errorMessage").html(data.msg);
					$("div.registerErrorMessageDiv").css("visibility","visible");
				}else {
					$("span.errorMessage").html(data.msg);
					$("div.registerErrorMessageDiv").css("visibility","visible");
				}
			}
		});
	});
	<!--表单提交验证-->
	$("#regsubmit").submit(function(){
		if(0==$("#regphone").val().length){
			$("span.errorMessage").html("请输入手机号");
			$("div.registerErrorMessageDiv").css("visibility","visible");
			$("#regphone").focus();
			return false;
		}		
		if(0==$("#checkcode").val().length){
			$("span.errorMessage").html("验证码不为空");
			$("div.registerErrorMessageDiv").css("visibility","visible");
			$("#checkcode").focus();
			return false;
		}		
		if(0==$("#regpassword").val().length){
			$("span.errorMessage").html("请输入注册密码");
			$("div.registerErrorMessageDiv").css("visibility","visible");
			$("#regpassword").focus();
			return false;
		}
		var reg = /(1[3-9]\d{9}$)/;
		if (!reg.test($("#regphone").val())){
			$("span.errorMessage").html("手机格式不正确");
			$("div.registerErrorMessageDiv").css("visibility","visible");
			$("#regphone").focus();
			return false;
		}
		$("#code").val($("#checkcode").val());
		return true;
	});
	//切换下来框
	$("#div1-li").click(function () {
		//获取属性值
		var div1=$("#div1").css("display");//block
		var div2=$("#div2").css("display");//none
		$("#div1").css("display","block");
		$("#div2").css("display","none");
	});
	$("#div2-li").click(function () {
		//获取属性值
		var div1=$("#div1").css("display");
		var div2=$("#div2").css("display");
		$("#div1").css("display","none");
		$("#div2").css("display","block");

	});
	//邮箱注册js
	//正则验证
	$("#emailform").submit(function () {
		if(0==$("#emailnum").val().length){
			$("span.errorMessage").html("请输入邮箱号");
			$("div.registerErrorMessageDiv").css("visibility","visible");
			$("#emailnum").focus();
			return false;
		}
		var regex = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
		if (!regex.test($("#emailnum").val())){
			$("span.errorMessage").html("邮箱格式不正确");
			$("div.registerErrorMessageDiv").css("visibility","visible");
			$("#emailnum").focus();
			return false;
		}
		if(0==$("#emailcode").val().length){
			$("span.errorMessage").html("验证码不为空");
			$("div.registerErrorMessageDiv").css("visibility","visible");
			$("#emailcode").focus();
			return false;
		}
		if(0==$("#emailpassword").val().length){
			$("span.errorMessage").html("请输入邮箱注册密码");
			$("div.registerErrorMessageDiv").css("visibility","visible");
			$("#emailpassword").focus();
			return false;
		}
		return true;
	});
	//获取邮箱验证码
	$("#emailsend").click(function () {
		$("#emailcode").focus();
		//发送验证码
		$.ajax({
			url:"web/getemailcode",
			type:"post",
			dataType:"json",
			data:{
				"emailenum":$("#emailnum").val()
			},
			beforeSend:function(data){
				/*将其中的发送变为数字*/
				//检查邮箱格式
				var regex = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;;
				if ($("#emailnum").val().length<=0){
					$("span.errorMessage").html("邮箱号不为空");
					$("div.registerErrorMessageDiv").css("visibility","visible");
					$("#emailnum").focus();
					return false;
				}else if (!regex.test($("#emailnum").val())){
					$("span.errorMessage").html("邮箱格式不正确");
					$("div.registerErrorMessageDiv").css("visibility","visible");
					$("#emailnum").focus();
					return false;
				}else {
					//定时器
					var num =30;
					var interval=setInterval(chock,1000);
					function chock(){
						if(num>0){
							$("#emailsend").text(num+" "+"s");
							$("#emailsend").attr('disabled',true);
						}else {
							clearInterval(interval);
							$("#emailsend").attr('disabled',false);
							$("#emailsend").text("发送验证码");
						}
						num--;
					}
					//错误检测
					$("span.errorMessage").html("邮箱验证码发送成功，请注意查收");
					$("div.registerErrorMessageDiv").css("visibility","visible");
					return true;
				}
				},
			success:function (data) {
				if (data.flag) {
					//发送成功
					$("span.errorMessage").html(data.msg);
					$("div.registerErrorMessageDiv").css("visibility", "visible");
				} else {
					$("span.errorMessage").html(data.msg);
					$("div.registerErrorMessageDiv").css("visibility", "visible");
				}
			}
			});
		});
});
</script>




<div class="registerDiv">
	<div class="registerErrorMessageDiv">
		<div class="alert alert-danger" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
		  	<span class="errorMessage"></span>
		</div>		
	</div>

	
	<%--<table class="registerTable" align="center" style="border: 1px solid red;">
		<tr>
			<td  class="registerTip registerTableLeftTD">设置会员名</td>
			<td></td>
		</tr>
		<tr>
			<td class="registerTableLeftTD">登陆名</td>
			<td  class="registerTableRightTD">
					<input id="name" name="name" placeholder="会员名一旦设置成功，无法修改" > </td>
		</tr>
		<tr>
			<td  class="registerTip registerTableLeftTD">设置登陆密码</td>
			<td  class="registerTableRightTD">登陆时验证，保护账号信息</td>
		</tr>		
		<tr>
			<td class="registerTableLeftTD">登陆密码</td>
			<td class="registerTableRightTD"><input id="password" name="password" type="password"  placeholder="设置你的登陆密码" > </td>
		</tr>
		<tr>
			<td class="registerTableLeftTD">密码确认</td>
			<td class="registerTableRightTD"><input id="repeatpassword" type="password"   placeholder="请再次输入你的密码" > </td>
		</tr>
				
		<tr>
			<td colspan="2" class="registerButtonTD">
				<a href="registerSuccess.jsp"><button>提   交</button></a>
			</td>
		</tr>				
	</table>--%>

	<div style="padding: 20px 100px 10px;">
			<div class="row">
				<div class="col-md-4 col-md-offset-4" align="center">
					<p style="color: black;font-size: 20px;">
						&nbsp;&nbsp;欢迎注册
					</p>
					<div class="input-group" style="border: 1px solid red;border-radius: 4px;">
				<input type="text" class="form-control" disabled value="请选择注册方式！">
				<div class="input-group-btn">
					<button type="buietton" class="btn btn-default
                        dropdown-toggle" data-toggle="dropdown">点击
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right" id="swap">
						<li id="div1-li">
							<a href="javascript:void(0);">手机号注册</a>
						</li>
						<li class="divider"></li>
						<li id="div2-li">
							<a href="javascript:void(0);">邮箱注册</a>
						</li>
					</ul>
				</div><!-- /btn-group -->
			</div><!-- /input-group -->
					<!--各种注册方式-->
					<!--手机号注册-->
					<div style="margin-top: 15px;display: block; " id="div1" >
						<form action="<%=request.getContextPath()%>/web/userServletRegister" method="post" id="regsubmit">
							<input type="hidden"  name="code" id="code" /><!--隐藏-->
							<!--手机号-->
							<div class="input-group">
								<span class="input-group-addon">
								<i class="glyphicon glyphicon-phone"></i>
								</span>
								<input type="text" class="form-control" placeholder="请输入手机号" name="regphone" id="regphone">
								<span class="input-group-addon">+86</span>
							</div>
							<!--验证码-->
							<div class="input-group">
								<div class="border-my">
									<!--定义验证码-->
									<div id="border-my-3">
										<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-envelope"></i>
											</span>
											<input type="text" class="form-control" placeholder="请输入验证码" name="checkcode" id="checkcode">
										</div>
									</div>
									<div id="border-my-4">
										<button class=" btn btn-primary" id="checkcodesend" type="button">发送验证码</button>
									</div>
								</div>
							</div>
							<!--密码-->
							<div class="input-group" style="margin-top: 9px;">
								<span class="input-group-addon">
								<i class="glyphicon glyphicon-lock"></i>
								</span>
								<input type="password" class="form-control" placeholder="请输入注册密码" name="regpassword" id="regpassword">
							</div>
							<!--注册按钮-->
							<div class="input-group" style="margin-top: 9px;">
									<input type="submit" value="注册"  class="border-my" id="issubmit">
							</div>
						</form>
					</div>
					<!--邮箱注册-->
					<div style="margin-top: 15px;display: none;" id="div2">
						<form action="<%=request.getContextPath()%>/web/userservletqqregister" method="post" id="emailform">
							<p style="text-align: left;font-size: 14px;display: block;margin-bottom: 3px;margin-top: -5px;"><span style="color: red;">*</span>&nbsp;电子邮箱</p>
					<div class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-user"></i>
						</span>
						<input type="text" class="form-control" placeholder="请输入邮箱帐号" name="emailnum" id="emailnum">
						<span class="input-group-addon">@qq.com</span>
					</div>
							<p style="text-align: left;font-size: 14px;display: block;margin-bottom: -10px;margin-top: 8px;"><span style="color: red;">*</span>&nbsp;邮箱验证码</p>
					<!--邮箱验证码-->
					<div class="input-group">
						<div class="border-my">
							<!--定义验证码-->
							<div id="border-my-1">
								<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-list-alt"></i>
											</span>
									<input type="text" class="form-control" placeholder="请输入验证码" name="emailcode" id="emailcode">
								</div>
							</div>
							<div id="border-my-2">
								<button class=" btn btn-primary"  type="button" id="emailsend">点击发送</button>
							</div>
						</div>
					</div>
							<p style="text-align: left;font-size: 14px;display: block;margin-bottom: 3px;margin-top: 2px;"><span style="color: red;">*</span>&nbsp;密码</p>
							<!--密码-->
					<div class="input-group">
					<span class="input-group-addon">
						<i class="glyphicon glyphicon-lock"></i>
					</span>
					<input type="password" class="form-control" placeholder="请输入密码" name="emailpassword" id="emailpassword">
				</div>
					<div class="input-group" style="margin-top: 5px;">
						<input type="submit" value="注册"  class="border-my" id="emailsubmit">
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>