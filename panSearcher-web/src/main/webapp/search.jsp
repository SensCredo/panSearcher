<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>网盘搜索</title>
<link href="${pageContext.request.contextPath }/search.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${pageContext.request.contextPath }/check.js" ></script>
</head>
<body>
<br/><br/><br/><br/><br/><br/>
		<center>
		<span id="ss">网盘搜索</span><span id="be">beta</span><br/><br/>
			<form action="${pageContext.request.contextPath }/handler/search.do" method="post" onsubmit="return check()">
				<input type="text" name="searchItem" size="30" style="font-size:30px">				
				<input type="image" src="${pageContext.request.contextPath }/imgs/botton_search.png" align="top" alt="Submit"><br>
				搜索级别:   级别1<input type="radio"" name="searchDeepth" value="3" checked="checked">
				级别2<input type="radio"" name="searchDeepth" value="5">
				级别3<input type="radio"" name="searchDeepth" value="10">
				级别4<input type="radio"" name="searchDeepth" value="15">
				级别5<input type="radio"" name="searchDeepth" value="25">
				<br>
				tips:级别越高，搜索速度越慢，可能结果越多
			</form>

			<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
			<p style="font-size:15px;color:#505050">
			&copy2018  All Right Reserved
			</p>
	</center>
</body>
</html>