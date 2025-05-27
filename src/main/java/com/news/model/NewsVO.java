package com.news.model;

import java.sql.Timestamp;

public class NewsVO implements java.io.Serializable {
	private Integer newsId;
	private String newsTitle;
	private String newsContent;
	private byte[] newsImage;
	private Timestamp newsTime;

	public NewsVO() {
		// TODO Auto-generated constructor stub
		super();
	}

	public NewsVO(Integer newsId, String newsTitle, String newsContent, byte[] newsImage, Timestamp newsTime) {
		super();
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.newsImage = newsImage;
		this.newsTime = newsTime;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public byte[] getNewsImage() {
		return newsImage;
	}

	public void setNewsImage(byte[] newsImage) {
		this.newsImage = newsImage;
	}

	public Timestamp getNewsTime() {
		return newsTime;
	}

	public void setNewsTime(Timestamp newsTime) {
		this.newsTime = newsTime;
	}
}
