package com.news.model;

import java.util.List;

public class NewsService {
	private NewsDAO_interface dao;

	public NewsService() {
		dao = new NewsDAO();
	}

	public NewsVO addNews(String newsTitle, String newsContent, byte[] newsImage) {
		NewsVO newsVO = new NewsVO();

		newsVO.setNewsTitle(newsTitle);
		newsVO.setNewsContent(newsContent);
		newsVO.setNewsImage(newsImage);
//		newsVO.setNewsTime(newsTime);
		dao.insert(newsVO);

		return newsVO;
	}

	public NewsVO updateNews(Integer newsId, String newsTitle, String newsContent, byte[] newsImage,
			java.sql.Timestamp newsTime) {
		NewsVO newsVO = new NewsVO();

		newsVO.setNewsId(newsId);
		newsVO.setNewsTitle(newsTitle);
		newsVO.setNewsContent(newsContent);
		newsVO.setNewsImage(newsImage);
		newsVO.setNewsTime(newsTime);
		dao.update(newsVO);

		return newsVO;
	}

	public void deleteNews(Integer newsId) {
		dao.delete(newsId);
	}

	public NewsVO getOneNews(Integer newsId) {
		return dao.findByPrimaryKey(newsId);
	}

	public List<NewsVO> getAll() {
		return dao.getAll();
	}

}
