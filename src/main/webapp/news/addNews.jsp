<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>

<%
//見com.emp.controller.EmpServlet.java第238行存入req的empVO物件 (此為輸入格式有錯誤時的empVO物件)
NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
%>
<%-- --<%=newsVO == null%>--${newsVO.newsId}-- --%>
<!-- for line 100 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>新增最新消息 - addNews.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>新增最新消息 - addNews.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>新增最新消息:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<%-- 內容 --%>
	<FORM METHOD="post" ACTION="news.do" name="form1"
		enctype="multipart/form-data">
		<table>

			<tr>
				<td>最新消息標題:</td>
				<td><input type="TEXT" name="newsTitle"
					value="<%=(newsVO == null) ? "請輸入標題" : newsVO.getNewsTitle()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>最新消息內容:</td>
				<td><input type="TEXT" name="newsContent"
					value="<%=(newsVO == null) ? "請輸入內容" : newsVO.getNewsContent()%>"
					size="45" /></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>上傳新圖片:<font color=red><b>*</b></font></td> -->
<!-- 				<td><input type="file" name="newsImage" /></td> -->
<!-- 			</tr> -->

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>
</html>