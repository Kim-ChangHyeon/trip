package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.poseidon.dto.CommentsDTO;

public class CommentDAO {
	private static CommentDAO dao = new CommentDAO();
	public CommentDAO() {}
	
	public static CommentDAO getInstace() {
		return dao;
	}
	
	private Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	// comment 테이블의 cno 가져옴 
	public CommentsDTO getOne(int cno) throws SQLException{
		CommentsDTO dto = new CommentsDTO();
		String sql = "SELECT * FROM comments where cno=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, cno);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		
		dto.setCno(rs.getInt("cno"));
		dto.setMember_name(rs.getString("member_name"));
		dto.setRccontent(rs.getString("rccontent"));
		dto.setRcdate(rs.getString("rcdate"));
		pstmt.close();
		
		return dto;
	}
	
	public void update(CommentsDTO dto) throws SQLException{
		String sql = "UPDATE comments set rccontent=? WHERE rno=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, dto.getRccontent());
		pstmt.setInt(3, dto.getCno());
		pstmt.execute();
		pstmt.close();
	}
	public void delete(int cno) throws SQLException{
		String sql = "DELETE FROM comments WHERE cno=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, cno);
		pstmt.execute();
		pstmt.close();
	}
	public void insert(CommentsDTO dto) throws SQLException{
		String sql = "INSERT INTO comments (member_no, rno, rccontent,member_name) VALUE(?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, dto.getMember_no());
		pstmt.setInt(2, dto.getRno());
		pstmt.setString(3, dto.getRccontent());
		pstmt.setString(4, dto.getMember_name());
		pstmt.execute();
		pstmt.close();
	}
	// ref 값을 조건으로함 -> 댓글의 갯수 리스트 구하기
	public int getCount(int rno) throws SQLException{
		String sql = "SELECT count(*) as count FROM comments WHERE rno=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, rno);
		ResultSet rs = pstmt.executeQuery();
		int cnt = 0;
		if (rs.next()) {
			cnt = rs.getInt("count");
		}
		pstmt.close();
		return cnt;
	}
			
	public int commentcount(int rno) {
		int commentcount = 0;
		String sql = "SELECT COUNT(*) AS commentcount FROM comments WHERE rno=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,rno);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
					commentcount = rs.getInt("commentcount");
			}
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		return commentcount;
	}		
	
	public ArrayList<CommentsDTO> getList(int rno) {
		ArrayList<CommentsDTO> list = new ArrayList<CommentsDTO>();
		String sql = "SELECT * FROM comments WHERE rno=? ORDER BY cno DESC";
		PreparedStatement pstmt = null;
				
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,rno);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
					CommentsDTO dto = new CommentsDTO();
					dto.setCno(rs.getInt("cno"));
					dto.setMember_name(rs.getString("member_name"));
					dto.setRccontent(rs.getString("rccontent"));
					dto.setRcdate(rs.getString("rcdate"));
					list.add(dto);
			}

		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
