<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>

<%
//見com.emp.controller.NewsServlet.java存入req的newsVO物件 (此為從資料庫取出的newsVO, 也可以是輸入格式有錯誤時的newsVO物件)
NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
%>
<%-- --<%=newsVO == null%>--${newsVO.newsId}-- --%>
<!-- for line 100 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料修改 - update_news_input.jsp</title>

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
				<h3>最新消息修改 - update_news_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>最新消息修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="news.do" name="form1"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>最新消息編號:<font color=red><b>*</b></font></td>
				<td><%=newsVO.getNewsId()%></td>
			</tr>
			<tr>
				<td>最新消息標題:</td>
				<td><input type="TEXT" name="newsTitle"
					value="<%=newsVO.getNewsTitle()%>" size="45" /></td>
			</tr>
			<tr>
				<td>最新消息內容:</td>
				<td><textarea name="newsContent" cols="45" rows="5"><%=newsVO.getNewsContent()%></textarea>
				</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>目前圖片:</td> -->
<!-- 				<td><img -->
<%-- 					src="<%= request.getContextPath() %>/news/ShowImage?newsId=<%= newsVO.getNewsId() %>" --%>
<!-- 					width="200" height="150" /></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>上傳新圖片:<font color=red><b>*</b></font></td> -->
<!-- 				<td><input type="file" name="newsImage" /></td> -->
<!-- 			</tr> -->
			<tr>
				<td>新增時間:<font color=red><b>*</b></font></td>
				<td><%=newsVO.getNewsTime()%></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="newsId" value="<%=newsVO.getNewsId()%>">
		<input type="hidden" name="newsTime" value="<%=newsVO.getNewsTime()%>">
		<input type="submit" value="送出修改">
	</FORM>
</body>
</html>