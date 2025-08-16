<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.news.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
NewsVO newsVO = (NewsVO) request.getAttribute("newsVO"); //NewsServlet.java(Concroller), 存入req的newsVO物件
%>

<html>
<head>
<title>員工資料 - listOneNews.jsp</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>最新消息 - listOneNews.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>編號</th>
			<th>標題</th>
			<th>內容</th>
			<!-- 			<th>最新消息圖片</th> -->
			<th>上傳時間</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<tr>
			<td><%=newsVO.getNewsId()%></td>
			<td><%=newsVO.getNewsTitle()%></td>
			<td><%=newsVO.getNewsContent()%></td>
			<%-- 			<td><%=newsVO.getNewsImage()%></td> --%>
			<td><%=newsVO.getNewsTime()%></td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/news/news.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改"> <input type="hidden"
						name="newsId" value="${newsVO.newsId}"> <input
						type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/news/news.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="刪除"> <input type="hidden"
						name="newsId" value="${newsVO.newsId}"> <input
						type="hidden" name="action" value="delete">
				</FORM>
			</td>
		</tr>
	</table>

</body>
</html>