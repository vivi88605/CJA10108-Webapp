package com.news.model;

import java.util.List;

public interface NewsDAO_interface {
	public void insert(NewsVO newsVO);

	public void update(NewsVO newsVO);

	public void delete(Integer newsId);

	public NewsVO findByPrimaryKey(Integer newsId);

	public List<NewsVO> getAll();
}
