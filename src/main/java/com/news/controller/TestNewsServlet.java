package com.news.controller;

import java.io.IOException;
import java.util.List;

import com.news.model.NewsDAO;
import com.news.model.NewsVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TestNewsServlet")
public class TestNewsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsDAO dao = new NewsDAO();
        List<NewsVO> list = dao.getAll();
        response.setContentType("text/plain; charset=UTF-8");
        for (NewsVO vo : list) {
            response.getWriter().println("【"+vo.getNewsTitle()+"】");
            response.getWriter().println(vo.getNewsContent());
            response.getWriter().println(vo.getNewsTime());
            response.getWriter().println("--------------------");
        }
    }
}