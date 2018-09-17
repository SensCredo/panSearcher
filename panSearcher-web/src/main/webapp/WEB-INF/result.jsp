<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/result.css" rel="stylesheet" type="text/css" />
<title>${searchItem }_网盘搜索</title>
</head>
<body>
<div id="head">搜索结果 </div> 
<p id="statement">本搜索通过百度贴吧获取网盘链接，暂只支持百度云网盘及微云网盘的链接获取，
对于加密网盘，若出现密码未读取或无效等情况，请前往原帖地址进行查看</p><br/>
<c:forEach items="${partResults }" var="panResult">
<c:if test="${empty panResult.password }">
	<table width="718" border="0">
	  <tr>
	    <td width="109">来源网址</td>
	    <td width="599"><a href="${panResult.sourceUrl }" target="_blank">${panResult.sourceUrl }</a></td>
	  </tr>
	  <tr>
	    <td>网盘地址</td>
	    <td><a href="https://${panResult.panUrl }" target="_blank">${panResult.panUrl }</a></td>
	  </tr>
	</table><br/>
</c:if>
<c:if test="${not empty panResult.password }">
	<table width="718" border="0">
	  <tr>
	    <td width="109">来源网址</td>
	    <td width="599"><a href="${panResult.sourceUrl }" target="_blank">${panResult.sourceUrl }</a></td>
	  </tr>
	  <tr>
	    <td>网盘地址</td>
	    <td><a href="https://${panResult.panUrl }" target="_blank">${panResult.panUrl }</a></td>
	  </tr>
	  <tr>
	    <td>密码</td>
	    <td>${panResult.password }</td>
  	  </tr>
	</table><br/>
	</c:if>	
	</c:forEach>
	<div id="pageChoose">
	&nbsp;&nbsp;&nbsp;
	<c:if test="${currentPage!=1 }">
		<a href="${pageContext.request.contextPath }/handler/show.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;&nbsp;
	</c:if>
	<!-- 通过varStatus可获取当前循环的次数信息 -->
	<c:forEach begin="1" end="${totalPage }" varStatus="loop">
		<a href="${pageContext.request.contextPath }/handler/show.do?currentPage=${loop.index }">${loop.index }</a>&nbsp;&nbsp;&nbsp;
	</c:forEach>
	<c:if test="${currentPage!=totalPage }">
		<a href="${pageContext.request.contextPath }/handler/show.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;&nbsp;
	</c:if>
	<a href="${pageContext.request.contextPath }/handler/exit.do">返回搜索</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	当前第<span id="count">${currentPage }</span>页，共${totalPage }页
	</div>
</body>
</html>