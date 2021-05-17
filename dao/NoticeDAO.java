package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.poseidon.db.DBConn;
import com.poseidon.dto.NoticeDTO;

public class NoticeDAO {
	public ArrayList<NoticeDTO> list(int page) {
		ArrayList<NoticeDTO> list = new ArrayList<NoticeDTO>();
		Connection conn = DBConn.getInstance().getConnection();
		String sql = "SELECT * FROM noticeview LIMIT ?, 10";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				NoticeDTO dto = new NoticeDTO();
				dto.setMember_grade(rs.getInt("member_grade"));
				dto.setNotice_no(rs.getInt("notice_no"));
				dto.setNotice_title(rs.getString("notice_title"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setNotice_date(rs.getString("notice_date"));
				dto.setNotice_view(rs.getInt("notice_view"));

				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public ArrayList<NoticeDTO> list(int page,int limit) {
		ArrayList<NoticeDTO> list = new ArrayList<NoticeDTO>();
		Connection conn = DBConn.getInstance().getConnection();
		String sql = "SELECT * FROM noticeview LIMIT ?, ?";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page);
			pstmt.setInt(2, limit);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				NoticeDTO dto = new NoticeDTO();
				dto.setMember_grade(rs.getInt("member_grade"));
				dto.setNotice_no(rs.getInt("notice_no"));
				dto.setNotice_title(rs.getString("notice_title"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setNotice_date(rs.getString("notice_date"));
				dto.setNotice_view(rs.getInt("notice_view"));

				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public ArrayList<NoticeDTO> search(int page,String search,String where) {
		ArrayList<NoticeDTO> list = new ArrayList<NoticeDTO>();
		Connection conn = DBConn.getInstance().getConnection();
		String sql = "SELECT * FROM noticeview WHERE notice_titleLIKE ? limit ?,10";
		PreparedStatement pstmt = null;
		
		if(where != null && (where.equals("title") || where.equals("content"))) {
			sql = "SELECT * FROM noticeview WHERE notice_"+where+" LIKE ? limit ?,10";
		}
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setInt(2, page);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				NoticeDTO dto = new NoticeDTO();
				dto.setMember_grade(rs.getInt("member_grade"));
				dto.setNotice_no(rs.getInt("notice_no"));
				dto.setNotice_title(rs.getString("notice_title"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setNotice_date(rs.getString("notice_date"));
				dto.setNotice_view(rs.getInt("notice_view"));

				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	// detail
	public NoticeDTO detail(int bno) {
		NoticeDTO dto = new NoticeDTO();
		Connection con = DBConn.getInstance().getConnection();
		String sql = "SELECT * FROM noticeview WHERE notice_no=?";
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNotice_no(rs.getInt("notice_no"));
				dto.setNotice_title(rs.getString("notice_title"));
				dto.setNotice_date(rs.getString("notice_date"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setMember_no(rs.getInt("member_no"));
				dto.setNotice_view(rs.getInt("notice_view"));
				dto.setNotice_content(rs.getString("notice_content"));
				countUp(bno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	// detail 2
	public NoticeDTO detail(int bno, int mno) {
		NoticeDTO dto = new NoticeDTO();
		Connection con = DBConn.getInstance().getConnection();
		String sql = "SELECT * FROM noticeview WHERE notice_no=?";
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNotice_no(rs.getInt("notice_no"));
				dto.setNotice_title(rs.getString("notice_title"));
				dto.setNotice_date(rs.getString("notice_date"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setMember_no(rs.getInt("member_no"));
				dto.setNotice_view(rs.getInt("notice_view"));
				dto.setNotice_content(rs.getString("notice_content"));
				countUp(bno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	// modify
	public void modify(NoticeDTO dto) {
		Connection con = DBConn.getInstance().getConnection();
		String sql = "UPDATE notice SET notice_title=?, notice_content=? WHERE notice_no=?";
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getNotice_title());
			pstmt.setString(2, dto.getNotice_content());
			pstmt.setInt(3, dto.getNotice_no());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// delete
	public void delete(int num) {
		Connection con = DBConn.getInstance().getConnection();
		String sql = "DELETE FROM notice WHERE notice_no=?";
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// write
	public void write(NoticeDTO dto) {
		//System.out.println(dto.getNotice_title());
		//System.out.println(dto.getNotice_content());
		Connection conn = DBConn.getInstance().getConnection();
		String sql = "INSERT INTO notice (notice_title, notice_content,member_no) VALUES (?, ?,?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getNotice_title());
			pstmt.setString(2, dto.getNotice_content());
			pstmt.setInt(3, dto.getMember_no());
			pstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	// count up
	public void countUp(int bno) {
		Connection con = DBConn.getInstance().getConnection();
		String sql = "UPDATE notice SET notice_view=notice_view+1 WHERE notice_no=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//paging
	public int page(){
		Connection conn = DBConn.getInstance().getConnection();
		String sql = "SELECT count(*) AS count FROM noticeview";   // ? ->1~10  11 ~ 20 21 ~ 30
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;	
	}	
	
	public int page(String search,String where){
		Connection conn = DBConn.getInstance().getConnection();
		String sql = "SELECT count(*) AS count FROM noticeview WHERE notice_title LIKE ?";
		PreparedStatement pstmt = null;
		int count = 0;
		if(where != null && (where.equals("title") || where.equals("content"))) {
			sql = "SELECT count(*) AS count FROM noticeview WHERE notice_"+where+" LIKE ?";
		}
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;	
	}	
}

