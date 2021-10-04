<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>电脑网站支付return_url</title>
</head>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.hodo.alipay.*"%>
<%@ page import="com.alipay.api.*"%>
<%@ page import="com.alipay.api.internal.util.*"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/main/style/js/jquery.js"></script>

<body>
</body>
<script>
	$.ajax({ 
	       url : "${pageContext.request.contextPath}/car/pay",
	       type : "post", //请求方式
	       async : true,
	       cache : false,
	       dataType : 'json', //预期返回格式
	       success : function(data) {
	      	 	 alert(data.msg);
	             if(data.success){
	          	   setTimeout( function(){
	          		 window.location.href="${pageContext.request.contextPath}/desk/toDeskSel";
		               }, 1 * 1000 );//延迟5000毫米
	             }
	       },
	       error : function(data) {
	         alert("error:" + data.responseText);
	       }
	 });
</script>
</html>