<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/noResult.css" rel="stylesheet" type="text/css" />
<title>${searchItem }_网盘搜索</title>
</head>
<body>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<div id="err_div">
	  <p><img src="${pageContext.request.contextPath }/imgs/err.PNG" width="79" height="92" alt="err" /></p>
	  <p1>哎呀，没有找到任何相关的网盘资源（T﹏T）</p1>
	  <p>您可以尝试以下方式继续查找</p>
	  <ul>
	    <li>在查找的关键词后加上 资源/百度云/网盘 等词汇</li>
	    <li>使用更高的搜索级别进行搜索</li>
	    <li>使用对资源描述更为精准的关键词进行搜索</li>
	  </ul>
 	<p><a href="${pageContext.request.contextPath }/handler/exit.do">返回搜索</a></p>
	</div>
</body>
</html>