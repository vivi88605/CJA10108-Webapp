package com.news.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import com.news.model.*;

@MultipartConfig
public class NewsServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doGet(req, res);
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(req, res);
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		/******************** 【一、顯示單筆消息】 ********************/
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage
			// view.
			req.setAttribute("errorMsgs", errorMsgs);

			/** 1.接收請求參數: 輸入格式的錯誤處理 **/
			// 沒有編號
			String str = req.getParameter("newsId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入最新消息編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/news/select_page.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			// 編號格式錯誤
			Integer newsId = null;
			try {
				newsId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("最新消息編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/news/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/** 2.開始查詢資料 **/
			NewsService newsSvc = new NewsService();
			NewsVO newsVO = newsSvc.getOneNews(newsId);
			if (newsVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/news/select_page.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/** 3.查詢完成，準備轉交(Send the Success view) **/
			req.setAttribute("newsVO", newsVO); // 資料庫取出的newsVO物件，存入req
			String url = "/news/listOneNews.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_news_jsp
			successView.forward(req, res);
		}

		/******************** 【二、進入更新消息頁面】 ********************/
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/** 1.接收請求參數 **/
			Integer newsId = Integer.valueOf(req.getParameter("newsId"));

			/** 2.開始查詢資料 **/
			NewsService newsSvc = new NewsService();
			NewsVO newsVO = newsSvc.getOneNews(newsId);

			/** 3.查詢完成，準備轉交(Send the Success view) **/
			req.setAttribute("newsVO", newsVO); // 資料庫取出的newsVO物件，存入req
			String url = "/news/update_news_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 update_news_input.jsp
			successView.forward(req, res);
		}

		/******************** 【三、更新消息】 ********************/
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/** 1.接收請求參數: 輸入格式的錯誤處理 **/
			// 抓數值
			Integer newsId = Integer.valueOf(req.getParameter("newsId").trim());
			String newsTitle = req.getParameter("newsTitle");
			String newsContent = req.getParameter("newsContent");
			/** ！圖片！ **/
			byte[] newsImage = null;
//			Part part = req.getPart("newsImage");
//
//			if (part != null && part.getSize() > 0) {
//				InputStream in = part.getInputStream();
//				newsImage = in.readAllBytes();
//				in.close();
//			} else {
//				// 若使用者未上傳新圖片，就取原圖片
//				NewsService newsSvc = new NewsService();
//				NewsVO originalVO = newsSvc.getOneNews(newsId);
//				newsImage = originalVO.getNewsImage();
//			}
			// 時間
			Timestamp newsTime = Timestamp.valueOf(req.getParameter("newsTime"));

			// 檢查標題
			if (newsTitle == null || newsTitle.trim().length() == 0) {
				errorMsgs.add("請輸入標題");
			}
			// 檢查內文
			if (newsContent == null || newsContent.trim().length() == 0) {
				errorMsgs.add("請輸入內文");
			}

			NewsVO newsVO = new NewsVO();
			newsVO.setNewsTitle(newsTitle);
			newsVO.setNewsContent(newsContent);
			newsVO.setNewsImage(newsImage);
			newsVO.setNewsTime(newsTime);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("newsVO", newsVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/news/update_news_input.jsp");
				failureView.forward(req, res);
				return;
			}
			/** 2.開始新增資料 **/
			NewsService newsSvc = new NewsService();
			newsVO = newsSvc.updateNews(newsId, newsTitle, newsContent, newsImage, newsTime);

			/** 3.新增完成，準備轉交(Send the Success view) **/
			String url = "/news/listAllNews.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllNews.jsp
			successView.forward(req, res);
		}

		/******************** 【四、新增消息】 ********************/
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/** 1.接收請求參數: 輸入格式的錯誤處理 **/
			// 標題
			String newsTitle = req.getParameter("newsTitle");
			if (newsTitle == null || newsTitle.trim().length() == 0) {
				errorMsgs.add("請輸入標題");
			}
			// 內文
			String newsContent = req.getParameter("newsContent");
			if (newsContent == null || newsContent.trim().length() == 0) {
				errorMsgs.add("請輸入內文");
			}
			/** ！圖片！ **/
			byte[] newsImage = null;
//			Part part = req.getPart("newsImage");
//			InputStream in = part.getInputStream();
//			newsImage = in.readAllBytes();
//			in.close();

			NewsVO newsVO = new NewsVO();
			newsVO.setNewsTitle(newsTitle);
			newsVO.setNewsContent(newsContent);
			newsVO.setNewsImage(newsImage);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("newsVO", newsVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/news/addNews.jsp");
				failureView.forward(req, res);
				return;
			}
			/** 2.開始新增資料 **/
			NewsService newsSvc = new NewsService();
			newsVO = newsSvc.addNews(newsTitle, newsContent, newsImage);
			/** 3.新增完成，準備轉交(Send the Success view) **/
			String url = "/news/listAllNews.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		/******************** 五、刪除資料 ********************/
		if ("delete".equals(action)) { // 來自listAllNews.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/** 1.接收請求參數 **/
			Integer newsId = Integer.valueOf(req.getParameter("newsId"));

			/** 2.開始刪除資料 **/
			NewsService newsSvc = new NewsService();
			newsSvc.deleteNews(newsId);

			/** 3.刪除完成，準備轉交(Send the Success view) **/
			String url = "/news/listAllNews.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後，轉交回送出刪除來源的網頁
			successView.forward(req, res);
		}

//		/******************** ！圖片！ ********************/
//
//		if ("showImage".equals(action)) {
//			Integer newsId = Integer.valueOf(req.getParameter("newsId"));
//			NewsService newsSvc = new NewsService();
//			NewsVO newsVO = newsSvc.getOneNews(newsId);
//
//			byte[] imageBytes = newsVO.getNewsImage();
//
//			if (imageBytes != null) {
//				res.setContentType("image/jpeg"); // 或根據實際圖片格式設置 MIME 類型
//				ServletOutputStream out = res.getOutputStream();
//				out.write(imageBytes);
//				out.flush();
//				out.close();
//			}
//			return; // 記得 return 避免繼續執行其他 action 分支
//		}
	}
}