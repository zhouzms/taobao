
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	

<script>
var deleteOrderItem = false;
var deleteOrderItemid = 0;
$(function(){

	$("a.deleteOrderItem").click(function(){
		deleteOrderItem = false;
		var oiid = $(this).attr("oiid")
		deleteOrderItemid = oiid;
		$("#deleteConfirmModal").modal('show');	   
	});
	$("button.deleteConfirmButton").click(function(){
		deleteOrderItem = true;
		$("#deleteConfirmModal").modal('hide');
	});
	
	$('#deleteConfirmModal').on('hidden.bs.modal', function (e) {
		if(deleteOrderItem){
			var page="foredeleteOrderItem";
			$.post(
				    page,
				    {"oiid":deleteOrderItemid},
				    function(result){
						if("success"==result){
							$("tr.cartProductItemTR[oiid="+deleteOrderItemid+"]").hide();
						}
						else{
							location.href="login.jsp";
						}
				    }
				);
			
		}
	})	
	
	$("img.cartProductItemIfSelected").click(function(){
		var selectit = $(this).attr("selectit")
		if("selectit"==selectit){
			$(this).attr("src","<%=request.getContextPath()%>/img/site/cartNotSelected.png");
			$(this).attr("selectit","false")
			$(this).parents("tr.cartProductItemTR").css("background-color","#fff");
		}
		else{
			$(this).attr("src","<%=request.getContextPath()%>/img/site/cartSelected.png");
			$(this).attr("selectit","selectit")
			$(this).parents("tr.cartProductItemTR").css("background-color","#FFF8E1");
		}
		syncSelect();
		syncCreateOrderButton();
		calcCartSumPriceAndNumber();
	});
	$("img.selectAllItem").click(function(){
		var selectit = $(this).attr("selectit")
		if("selectit"==selectit){
			$("img.selectAllItem").attr("src","<%=request.getContextPath()%>/img/site/cartNotSelected.png");
			$("img.selectAllItem").attr("selectit","false")
			$(".cartProductItemIfSelected").each(function(){
				$(this).attr("src","<%=request.getContextPath()%>/img/site/cartNotSelected.png");
				$(this).attr("selectit","false");
				$(this).parents("tr.cartProductItemTR").css("background-color","#fff");
			});			
		}
		else{
			$("img.selectAllItem").attr("src","<%=request.getContextPath()%>/img/site/cartSelected.png");
			$("img.selectAllItem").attr("selectit","selectit")
			$(".cartProductItemIfSelected").each(function(){
				$(this).attr("src","<%=request.getContextPath()%>/img/site/cartSelected.png");
				$(this).attr("selectit","selectit");
				$(this).parents("tr.cartProductItemTR").css("background-color","#FFF8E1");
			});				
		}
		syncCreateOrderButton();
		calcCartSumPriceAndNumber();
		

	});
	
	$(".orderItemNumberSetting").keyup(function(){
		var pid=$(this).attr("pid");
		var stock= $("span.orderItemStock[pid="+pid+"]").text();
		var price= $("span.orderItemPromotePrice[pid="+pid+"]").text();
		
		var num= $(".orderItemNumberSetting[pid="+pid+"]").val();
		num = parseInt(num);
		if(isNaN(num))
			num= 1;
		if(num<=0)
			num = 1;
		if(num>stock)
			num = stock;
		
		syncPrice(pid,num,price);
	});

	$(".numberPlus").click(function(){
		
		var pid=$(this).attr("pid");
		var stock= $("span.orderItemStock[pid="+pid+"]").text();
		var price= $("span.orderItemPromotePrice[pid="+pid+"]").text();
		var num= $(".orderItemNumberSetting[pid="+pid+"]").val();

		num++;
		if(num>stock)
			num = stock;
		syncPrice(pid,num,price);
	});
	$(".numberMinus").click(function(){
		var pid=$(this).attr("pid");
		var stock= $("span.orderItemStock[pid="+pid+"]").text();
		var price= $("span.orderItemPromotePrice[pid="+pid+"]").text();
		
		var num= $(".orderItemNumberSetting[pid="+pid+"]").val();
		--num;
		if(num<=0)
			num=1;
		syncPrice(pid,num,price);
	});	
	
	$("button.createOrderButton").click(function(){
		var params = "";
		$(".cartProductItemIfSelected").each(function(){
			if("selectit"==$(this).attr("selectit")){
				var oiid = $(this).attr("oiid");
				params += "&oiid="+oiid;
			}
		});
		params = params.substring(1);
		location.href="forebuy?"+params;
	});
	
	
	
})

function syncCreateOrderButton(){
	var selectAny = false;
	$(".cartProductItemIfSelected").each(function(){
		if("selectit"==$(this).attr("selectit")){
			selectAny = true;
		}
	});
	
	if(selectAny){
		$("button.createOrderButton").css("background-color","#C40000");
		$("button.createOrderButton").removeAttr("disabled");
	}
	else{
		$("button.createOrderButton").css("background-color","#AAAAAA");
		$("button.createOrderButton").attr("disabled","disabled");		
	}
		
}
function syncSelect(){
	var selectAll = true;
	$(".cartProductItemIfSelected").each(function(){
		if("false"==$(this).attr("selectit")){
			selectAll = false;
		}
	});
	
	if(selectAll)
		$("img.selectAllItem").attr("src","<%=request.getContextPath()%>/img/site/cartSelected.png");
	else
		$("img.selectAllItem").attr("src","<%=request.getContextPath()%>/img/site/cartNotSelected.png");
	
	
	
}
function calcCartSumPriceAndNumber(){
	var sum = 0;
	var totalNumber = 0;
	$("img.cartProductItemIfSelected[selectit='selectit']").each(function(){
		var oiid = $(this).attr("oiid");
		var price =$(".cartProductItemSmallSumPrice[oiid="+oiid+"]").text();
		price = price.replace(/,/g, "");
		price = price.replace(/￥/g, "");
		sum += new Number(price);	
		
		var num =$(".orderItemNumberSetting[oiid="+oiid+"]").val();
		totalNumber += new Number(num);	
		
	});
	
	$("span.cartSumPrice").html("￥"+formatMoney(sum));
	$("span.cartTitlePrice").html("￥"+formatMoney(sum));
	$("span.cartSumNumber").html(totalNumber);
}
function syncPrice(pid,num,price){
	$(".orderItemNumberSetting[pid="+pid+"]").val(num);
	var cartProductItemSmallSumPrice = formatMoney(num*price); 
	$(".cartProductItemSmallSumPrice[pid="+pid+"]").html("￥"+cartProductItemSmallSumPrice);
	calcCartSumPriceAndNumber();
	
	var page = "forechangeOrderItem";
	$.post(
		    page,
		    {"pid":pid,"number":num},
		    function(result){
				if("success"!=result){
					location.href="login.jsp";
				}
		    }
		);

}
</script>	

<title>购物车</title>
<div class="cartDiv">
	<div class="cartTitle pull-right">
		<span>已选商品  (不含运费)</span>
		<span class="cartTitlePrice">￥0.00</span>
		<button class="createOrderButton" disabled="disabled">结 算</button>
	</div>
	
	
	<div class="cartProductList">
		<table class="cartProductTable">
			<thead>
				<tr>
					<th class="selectAndImage">
							<img selectit="false" class="selectAllItem" src="<%=request.getContextPath()%>/img/site/cartNotSelected.png">
					全选
					
					</th>
					<th>商品信息</th>
					<th>单价</th>
					<th>数量</th>
					<th width="120px">金额</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			<!--没有登录使用Cookie中的数据-->
			<c:if test="${sessionScope.user==null}">
				<c:forEach items="${certShop.cert}" var="oi" varStatus="s">
					<tr oiid="${s.index+1}" class="cartProductItemTR">
						<td>
							<img selectit="false" oiid="${s.index+1}" class="cartProductItemIfSelected" src="<%=request.getContextPath()%>/img/site/cartNotSelected.png">
							<a style="display:none" href="#nowhere"><img src="<%=request.getContextPath()%>/img/site/cartSelected.png"></a>
							<img class="cartProductImg"  src="<%=request.getContextPath()%>/img/productSingle_middle/
									<c:forEach items="${oi.key.images}" var="c" varStatus="i">
										<c:if test="${i.index==0}">
										${c.id}
										</c:if>
									</c:forEach>
							.jpg">
						</td>
						<td>
							<div class="cartProductLinkOutDiv">
								<a href="<%=request.getContextPath()%>/web/productdetil?pid=${oi.key.id}" class="cartProductLink">${oi.key.name}</a>
								<div class="cartProductLinkInnerDiv">
									<img src="<%=request.getContextPath()%>/img/site/creditcard.png" title="支持信用卡支付">
									<img src="<%=request.getContextPath()%>/img/site/7day.png" title="消费者保障服务,承诺7天退货">
									<img src="<%=request.getContextPath()%>/img/site/promise.png" title="消费者保障服务,承诺如实描述">
								</div>
							</div>

						</td>
						<td>
							<span class="cartProductItemOringalPrice">￥${oi.key.orignalPrice}</span>
							<span  class="cartProductItemPromotionPrice">￥${oi.key.promotePrice}</span>

						</td>
						<td>

							<div class="cartProductChangeNumberDiv">
								<span class="hidden orderItemStock " pid="${oi.key.id}">${oi.key.stock}</span>
								<span class="hidden orderItemPromotePrice " pid="${oi.key.id}">${oi.key.promotePrice}</span>
								<a  pid="${oi.key.id}" class="numberMinus" href="#nowhere">-</a>
								<input pid="${oi.key.id}" oiid="${s.index+1}" class="orderItemNumberSetting" autocomplete="off" value="${oi.value}">
								<a  stock="${oi.key.stock}" pid="${oi.key.id}" class="numberPlus" href="#nowhere">+</a>
							</div>

						</td>
						<td >
							<span class="cartProductItemSmallSumPrice" oiid="${s.index+1}" pid="${oi.key.id}" >
							￥<fmt:formatNumber type="number" value="${oi.key.promotePrice*oi.value}" minFractionDigits="2"/>
							</span>

						</td>
						<td>
							<a class="deleteOrderItem" oiid="${s.index+1}"  href="#nowhere">删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${sessionScope.user!=null}">
				<c:forEach items="${cert.cert}" var="oi" varStatus="s">
					<tr oiid="${s.index+1}" class="cartProductItemTR">
						<td>
							<img selectit="false" oiid="${s.index+1}" class="cartProductItemIfSelected" src="<%=request.getContextPath()%>/img/site/cartNotSelected.png">
							<a style="display:none" href="#nowhere"><img src="<%=request.getContextPath()%>/img/site/cartSelected.png"></a>
							<img class="cartProductImg"  src="<%=request.getContextPath()%>/img/productSingle_middle/
									<c:forEach items="${oi.key.images}" var="c" varStatus="i">
										<c:if test="${i.index==0}">
										${c.id}
										</c:if>
									</c:forEach>
							.jpg">
						</td>
						<td>
							<div class="cartProductLinkOutDiv">
								<a href="<%=request.getContextPath()%>/web/productdetil?pid=${oi.key.id}" class="cartProductLink">${oi.key.name}</a>
								<div class="cartProductLinkInnerDiv">
									<img src="<%=request.getContextPath()%>/img/site/creditcard.png" title="支持信用卡支付">
									<img src="<%=request.getContextPath()%>/img/site/7day.png" title="消费者保障服务,承诺7天退货">
									<img src="<%=request.getContextPath()%>/img/site/promise.png" title="消费者保障服务,承诺如实描述">
								</div>
							</div>

						</td>
						<td>
							<span class="cartProductItemOringalPrice">￥${oi.key.orignalPrice}</span>
							<span  class="cartProductItemPromotionPrice">￥${oi.key.promotePrice}</span>

						</td>
						<td>

							<div class="cartProductChangeNumberDiv">
								<span class="hidden orderItemStock " pid="${oi.key.id}">${oi.key.stock}</span>
								<span class="hidden orderItemPromotePrice " pid="${oi.key.id}">${oi.key.promotePrice}</span>
								<a  pid="${oi.key.id}" class="numberMinus" href="#nowhere">-</a>
								<input pid="${oi.key.id}" oiid="${s.index+1}" class="orderItemNumberSetting" autocomplete="off" value="${oi.value}">
								<a  stock="${oi.key.stock}" pid="${oi.key.id}" class="numberPlus" href="#nowhere">+</a>
							</div>

						</td>
						<td >
							<span class="cartProductItemSmallSumPrice" oiid="${s.index+1}" pid="${oi.key.id}" >
							￥<fmt:formatNumber type="number" value="${oi.key.promotePrice*oi.value}" minFractionDigits="2"/>
							</span>

						</td>
						<td>
							<a class="deleteOrderItem" oiid="${s.index+1}"  href="#nowhere">删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			</tbody>
		</table>
	</div>
	
	<div class="cartFoot">
		<img selectit="false" class="selectAllItem" src="<%=request.getContextPath()%>/img/site/cartNotSelected.png">
		<span>全选</span>
<!-- 		<a href="#">删除</a> -->
		
		<div class="pull-right">
			<span>已选商品 <span class="cartSumNumber" >0</span> 件</span>
			
			<span>合计 (不含运费): </span> 
			<span class="cartSumPrice" >￥0.00</span>
			<button class="createOrderButton" disabled="disabled" >结  算</button>
		</div>
		
	</div>
	
</div>