package com.news.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsJDBCDAO implements NewsDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/islevilla?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO news (news_title, news_content, news_image, news_time) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
	private static final String GET_ALL_STMT = "SELECT * FROM news";
	private static final String GET_ONE_STMT = "SELECT * FROM news WHERE news_id=?";
	private static final String DELETE = "DELETE FROM news WHERE news_id=?";
	private static final String UPDATE = "UPDATE news SET news_title=?, news_content=?, news_image=?, news_time=? WHERE news_id=?";

	/** 關閉資源的方法 **/
	private void closeResources(ResultSet rs, PreparedStatement pstmt, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		closeResources(pstmt, con);
	}

	private void closeResources(PreparedStatement pstmt, Connection con) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}

	@Override
	public void insert(NewsVO newsVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, newsVO.getNewsTitle());
			pstmt.setString(2, newsVO.getNewsContent());
			pstmt.setBytes(3, newsVO.getNewsImage());
//			pstmt.setTimestamp(4, newsVO.getNewsTime());

			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			closeResources(pstmt, con);
		}
	}

	@Override
	public void update(NewsVO newsVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, newsVO.getNewsTitle());
			pstmt.setString(2, newsVO.getNewsContent());
			pstmt.setBytes(3, newsVO.getNewsImage());
			pstmt.setTimestamp(4, newsVO.getNewsTime());
			pstmt.setInt(5, newsVO.getNewsId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			closeResources(pstmt, con);
		}
	}

	@Override
	public void delete(Integer newsId) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, newsId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			closeResources(pstmt, con);
		}

	}

	@Override
	public NewsVO findByPrimaryKey(Integer newsId) {
		// TODO Auto-generated method stub
		NewsVO newsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, newsId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				newsVO = new NewsVO();
				newsVO.setNewsId(rs.getInt("news_id"));
				newsVO.setNewsTitle(rs.getString("news_title"));
				newsVO.setNewsContent(rs.getString("news_content"));
				newsVO.setNewsImage(rs.getBytes("news_image"));
				newsVO.setNewsTime(rs.getTimestamp("news_time"));
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			closeResources(rs, pstmt, con);
		}
		return newsVO;
	}

	@Override
	public List<NewsVO> getAll() {
		// TODO Auto-generated method stub
		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO newsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				newsVO = new NewsVO();
				newsVO.setNewsId(rs.getInt("news_id"));
				newsVO.setNewsTitle(rs.getString("news_title"));
				newsVO.setNewsContent(rs.getString("news_content"));
				newsVO.setNewsImage(rs.getBytes("news_image"));
				newsVO.setNewsTime(rs.getTimestamp("news_time"));

				list.add(newsVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			closeResources(rs, pstmt, con);
		}
		return list;
	}
}
