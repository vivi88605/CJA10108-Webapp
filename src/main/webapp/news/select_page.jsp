<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>islevilla News: Home</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>IBM News: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for islevilla News: Home</p>

	<h3>資料查詢:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllNews.jsp'>List</a> all News. <br>
		<br></li>


		<li>
			<FORM METHOD="post" ACTION="news.do">
				<b>輸入最新消息編號 :</b> <input type="text" name="newsId"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="newsSvc" scope="page"
			class="com.news.model.NewsService" />

		<li>
			<FORM METHOD="post" ACTION="news.do">
				<b>選擇最新消息編號:</b> <select size="1" name="newsId">
					<c:forEach var="newsVO" items="${newsSvc.all}">
						<option value="${newsVO.newsId}">${newsVO.newsId}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="news.do">
				<b>選擇最新消息標題:</b> <select size="1" name="newsId">
					<c:forEach var="newsVO" items="${newsSvc.all}">
						<option value="${newsVO.newsId}">${newsVO.newsTitle}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>


	<h3>新增最新消息</h3>

	<ul>
		<li><a href='addNews.jsp'>Add</a> a new News.</li>
	</ul>

</body>
</html>