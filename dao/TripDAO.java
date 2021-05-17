package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.poseidon.db.DBConn;
import com.poseidon.dto.CommentsDTO;
import com.poseidon.dto.TripDTO;

public class TripDAO {

	public ArrayList<TripDTO> list(int page, int limit) {
		ArrayList<TripDTO> list = new ArrayList<TripDTO>();
		Connection con = DBConn.getInstance().getConnection();
		String sql = "SELECT * FROM reviewview limit ?, ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, page);
			pstmt.setInt(2, limit);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				TripDTO dto = new TripDTO();
				dto.setRno(rs.getInt("rno"));
				dto.setRlocation(rs.getInt("rlocation"));
				dto.setRtitle(rs.getString("rtitle"));
				dto.setRcontent(rs.getString("rcontent"));
				dto.setRdate(rs.getString("rdate"));
				dto.setRviews(rs.getString("rviews"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setMember_no(rs.getInt("member_no"));
				if(rs.getString("rfile1") != null) dto.setRfile1(rs.getString("rfile1"));
				if(rs.getString("rfile2") != null) dto.setRfile1(rs.getString("rfile2"));
				if(rs.getString("rfile3") != null) dto.setRfile1(rs.getString("rfile3"));
				if(rs.getString("rfile4") != null) dto.setRfile1(rs.getString("rfile4"));
				if(rs.getString("rfile5") != null) dto.setRfile1(rs.getString("rfile5"));
				dto.setCount(rs.getInt("count"));
				
				list.add(dto);
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
		return list;
	}
	
	public ArrayList<TripDTO> bestList(){
		ArrayList<TripDTO> bestList = new ArrayList<TripDTO>();
		Connection con = DBConn.getInstance().getConnection();
		String sql = "SELECT rno, rtitle, rfile1 from reviewview WHERE rfile1 LIKE '%.jpg' OR rfile1 LIKE '%.png' ORDER BY rviews DESC LIMIT 4";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				TripDTO dto = new TripDTO();
				dto.setRno(rs.getInt("rno"));
				dto.setRtitle(rs.getString("rtitle"));
				dto.setRfile1(rs.getString("rfile1"));
				bestList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bestList;
	}
	
	public void countUp(int rno) {
		Connection con = DBConn.getInstance().getConnection();
		String sql = "UPDATE review SET rviews = rviews + 1 WHERE rno=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rno);
			pstmt.execute();
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
	}
	
	public CommentsDTO commentMno(int cno) {
		Connection con = DBConn.getInstance().getConnection();
		String sql = "SELECT * from commentsview WHERE cno=?";
		PreparedStatement pstmt = null;
		CommentsDTO dto = new CommentsDTO();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cno);
			ResultSet rs = pstmt.executeQuery();	
			if(rs.next()) {
				dto.setMember_no(rs.getInt("member_no"));
				dto.setMember_name(rs.getString("cmember_name"));
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
	
	public TripDTO tripMno(int rno) {
		Connection con = DBConn.getInstance().getConnection();
		String sql = "SELECT member_no,member_name from reviewview WHERE rno=?";
		PreparedStatement pstmt = null;
		TripDTO dto = new TripDTO();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rno);
			ResultSet rs = pstmt.executeQuery();	
			if(rs.next()) {
				dto.setMember_no(rs.getInt("member_no"));
				dto.setMember_name(rs.getString("member_name"));
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
	
	public int tripCount() {
		Connection con = DBConn.getInstance().getConnection();
		String sql = "SELECT COUNT(*) AS count from reviewview";
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();	
			if(rs.next()) count = rs.getInt("count");
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
		return count;
	}
	
	public TripDTO detail(int rno) {
		TripDTO dto = new TripDTO();
		Connection con = DBConn.getInstance().getConnection();
		String sql = "SELECT * FROM reviewview WHERE rno=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rno);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setRno(rs.getInt("rno"));
				dto.setRlocation(rs.getInt("rlocation"));
				dto.setRtitle(rs.getString("rtitle"));
				dto.setRcontent(rs.getString("rcontent"));
				dto.setRdate(rs.getString("rdate"));
				dto.setRviews(rs.getString("rviews"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setMember_no(rs.getInt("member_no"));
				if(rs.getString("rfile1") != null) dto.setRfile1(rs.getString("rfile1"));
				if(rs.getString("rfile2") != null) dto.setRfile2(rs.getString("rfile2"));
				if(rs.getString("rfile3") != null) dto.setRfile3(rs.getString("rfile3"));
				if(rs.getString("rfile4") != null) dto.setRfile4(rs.getString("rfile4"));
				if(rs.getString("rfile5") != null) dto.setRfile5(rs.getString("rfile5"));
				countUp(rno);
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
	public TripDTO detail(int rno, int mno) {
		TripDTO dto = new TripDTO();
		Connection con = DBConn.getInstance().getConnection();
		String sql = "SELECT * FROM reviewview WHERE rno=? AND member_no=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rno);
			pstmt.setInt(2, mno);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setRno(rs.getInt("rno"));
				dto.setRlocation(rs.getInt("rlocation"));
				dto.setRtitle(rs.getString("rtitle"));
				dto.setRcontent(rs.getString("rcontent"));
				dto.setRdate(rs.getString("rdate"));
				dto.setRviews(rs.getString("rviews"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setMember_no(rs.getInt("member_no"));
				if(rs.getString("rfile1") != null) dto.setRfile1(rs.getString("rfile1"));
				if(rs.getString("rfile2") != null) dto.setRfile1(rs.getString("rfile2"));
				if(rs.getString("rfile3") != null) dto.setRfile1(rs.getString("rfile3"));
				if(rs.getString("rfile4") != null) dto.setRfile1(rs.getString("rfile4"));
				if(rs.getString("rfile5") != null) dto.setRfile1(rs.getString("rfile5"));
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
	
	public void write(TripDTO dto) {
		Connection con = DBConn.getInstance().getConnection();
		String sql = "INSERT INTO review (rtitle, rcontent, rlocation, member_no) VALUES(?,?,?,?)";
		if(dto.getRfile1() != null) sql = "INSERT INTO review (rtitle, rcontent, rlocation, member_no,rfile1) VALUES(?,?,?,?,?)";
		if(dto.getRfile2() != null) sql = "INSERT INTO review (rtitle, rcontent, rlocation, member_no,rfile1,rfile2) VALUES(?,?,?,?,?,?)";
		if(dto.getRfile3() != null) sql = "INSERT INTO review (rtitle, rcontent, rlocation, member_no,rfile1,rfile2,rfile3) VALUES(?,?,?,?,?,?,?)";
		if(dto.getRfile4() != null) sql = "INSERT INTO review (rtitle, rcontent, rlocation, member_no,rfile1,rfile2,rfile3,rfile4) VALUES(?,?,?,?,?,?,?,?)";
		if(dto.getRfile5() != null) sql = "INSERT INTO review (rtitle, rcontent, rlocation, member_no,rfile1,rfile2,rfile3,rfile4,rfile5) VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getRtitle());
			pstmt.setString(2, dto.getRcontent());
			pstmt.setInt(3, dto.getRlocation());
			pstmt.setInt(4, dto.getMember_no());
			if(dto.getRfile1() != null) pstmt.setString(5, dto.getRfile1());
			if(dto.getRfile2() != null) pstmt.setString(6, dto.getRfile2());
			if(dto.getRfile3() != null) pstmt.setString(7, dto.getRfile3());
			if(dto.getRfile4() != null) pstmt.setString(8, dto.getRfile4());
			if(dto.getRfile5() != null) pstmt.setString(9, dto.getRfile5());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
	
	public void delete(int num) {
		Connection con = DBConn.getInstance().getConnection();
		String sql = "DELETE FROM review WHERE rno=?";
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
	
	public void modify(TripDTO dto) {
		Connection con = DBConn.getInstance().getConnection();
		String sql = "UPDATE review SET rtitle=?, rcontent=?, rlocation=? WHERE rno=?";
		if(dto.getRfile1() != null) sql = "UPDATE review SET rtitle=?, rcontent=?, rlocation=?, rfile1=? WHERE rno=?";
		if(dto.getRfile2() != null) sql = "UPDATE review SET rtitle=?, rcontent=?, rlocation=?, rfile1=?, rfile2=? WHERE rno=?";
		if(dto.getRfile3() != null) sql = "UPDATE review SET rtitle=?, rcontent=?, rlocation=?, rfile1=? ,rfile2=? ,rfile3=? WHERE rno=?";
		if(dto.getRfile4() != null) sql = "UPDATE review SET rtitle=?, rcontent=?, rlocation=?, rfile1=?,rfile2=?,rfile3=? ,rfile4=? WHERE rno=?";
		if(dto.getRfile5() != null) sql = "UPDATE review SET rtitle=?, rcontent=?, rlocation=?, rfile1=?,rfile2=?,rfile3=? ,rfile4=?,rfile5=? WHERE rno=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getRtitle());
			pstmt.setString(2, dto.getRcontent());
			pstmt.setInt(3, dto.getRlocation());
			if(dto.getRfile1() != null) {
				pstmt.setString(4, dto.getRfile1());
				pstmt.setInt(5, dto.getRno());
			} else if(dto.getRfile2() != null){
				pstmt.setString(4, dto.getRfile1());
				pstmt.setString(5, dto.getRfile2());
				pstmt.setInt(6, dto.getRno());
			} else if(dto.getRfile2() != null) {
				pstmt.setString(4, dto.getRfile1());
				pstmt.setString(5, dto.getRfile2());
				pstmt.setString(6, dto.getRfile3());
				pstmt.setInt(7, dto.getRno());
			} else if(dto.getRfile2() != null) {
				pstmt.setString(4, dto.getRfile1());
				pstmt.setString(5, dto.getRfile2());
				pstmt.setString(6, dto.getRfile3());
				pstmt.setString(7, dto.getRfile4());
				pstmt.setInt(8, dto.getRno());
			} else if(dto.getRfile2() != null) {
				pstmt.setString(4, dto.getRfile1());
				pstmt.setString(5, dto.getRfile2());
				pstmt.setString(6, dto.getRfile3());
				pstmt.setString(7, dto.getRfile4());
				pstmt.setString(8, dto.getRfile5());
				pstmt.setInt(9, dto.getRno());
			} else {
				pstmt.setInt(4, dto.getRno());	
			}
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
	
	public int userfind(int rno,int member_no) {
		Connection con = DBConn.getInstance().getConnection();
		int checkuser = 0;
		String sql = "SELECT COUNT(*) as checkuser,rno,member_no FROM reviewview WHERE rno=? AND member_no=? ";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rno);
			pstmt.setInt(2, member_no);
			ResultSet rs =  pstmt.executeQuery();
			
			if(rs.next()) {
				checkuser = rs.getInt("checkuser");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return checkuser;
	}
	
	public String findFile(int rno) {
		String fileName = "";
		Connection con = DBConn.getInstance().getConnection();
		String sql = "SELECT rfile1 FROM review WHERE rno=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rno);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				fileName = rs.getString("rfile1");
			}
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
		return fileName;
	}
	public ArrayList<CommentsDTO> comments(int rno){
		ArrayList<CommentsDTO> comments = new ArrayList<CommentsDTO>();
		Connection con = DBConn.getInstance().getConnection();
		String sql = "SELECT * FROM commentsview WHERE rno=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rno);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CommentsDTO dto = new CommentsDTO();
				//dto.setCon(rs.getInt("cno"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setMember_no(rs.getInt("member_no"));
				dto.setRccontent(rs.getString("rccontent"));
				dto.setRcdate(rs.getString("rcdate"));
				comments.add(dto);
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
		return comments;
	}
	public void commentInsert(CommentsDTO dto) {
		Connection con = DBConn.getInstance().getConnection();
		String sql = "INSERT INTO comments (member_no, rno, ccontent) VALUE(?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getMember_no());
			pstmt.setInt(2, dto.getRno());
			pstmt.setString(3, dto.getRccontent());
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
	
	public ArrayList<CommentsDTO> getList(int rno) {
		ArrayList<CommentsDTO> list = new ArrayList<CommentsDTO>();
		Connection con = DBConn.getInstance().getConnection();
		String sql = "SELECT * FROM comments WHERE rno=? ORDER BY cno DESC";
		PreparedStatement pstmt = null;
				
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,rno);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
					CommentsDTO dto = new CommentsDTO();
					dto.setCno(rs.getInt("cno"));
					dto.setRno(rs.getInt("rno"));
					dto.setMember_name(rs.getString("member_name"));
					dto.setMember_no(rs.getInt("member_no"));
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
	
	public void commentupdate(CommentsDTO dto) {
		Connection con = DBConn.getInstance().getConnection();
		String sql = "UPDATE comments set rccontent=? WHERE cno=?";
		PreparedStatement pstmt = null;
		try {
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, dto.getRccontent());
			pstmt.setInt(2, dto.getCno());
			pstmt.execute();
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
	}
	public void commentdelete(int cno) {
		Connection con = DBConn.getInstance().getConnection();
		String sql = "DELETE FROM comments WHERE cno=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cno);
			pstmt.execute();
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
	}
	
	public int commentcount(int rno) {
		Connection con = DBConn.getInstance().getConnection();
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
	
	public ArrayList<CommentsDTO> commentcount() {
		Connection con = DBConn.getInstance().getConnection();
		String sql = "SELECT COUNT(*) AS commentcount, rno from comments GROUP BY rno";
		PreparedStatement pstmt = null;
		ArrayList<CommentsDTO> list = new ArrayList<CommentsDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				CommentsDTO dto = new CommentsDTO();
				dto.setCommentcount(rs.getInt("commentcount"));
				dto.setRno(rs.getInt("rno"));
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
	
	public ArrayList<TripDTO> adminMemberReviewCheck(int member_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		conn=DBConn.getInstance().getConnection();	
		String sql = "SELECT * from reviewview WHERE member_no=?";
		ArrayList<TripDTO> list = new ArrayList<TripDTO>();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				TripDTO dto = new TripDTO();
				dto.setRno(rs.getInt("rno"));
				dto.setRlocation(rs.getInt("rlocation"));
				dto.setRtitle(rs.getString("rtitle"));
				dto.setRcontent(rs.getString("rcontent"));
				dto.setRdate(rs.getString("rdate"));
				dto.setRviews(rs.getString("rviews"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setMember_no(rs.getInt("member_no"));
				list.add(dto);
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
		return list;
	}
	
	public ArrayList<CommentsDTO> adminMemberCommentsCheck(int member_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		conn=DBConn.getInstance().getConnection();	
		String sql = "SELECT * from commentsview WHERE member_no=?";
		ArrayList<CommentsDTO> list = new ArrayList<CommentsDTO>();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				CommentsDTO dto = new CommentsDTO();
				dto.setRno(rs.getInt("rno"));
				dto.setCno(rs.getInt("cno"));
				dto.setRccontent(rs.getString("rccontent"));
				dto.setRcdate(rs.getString("rcdate"));
				dto.setMember_no(rs.getInt("member_no"));
				dto.setMember_name(rs.getString("cmember_name"));
				list.add(dto);
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
		return list;
	}
}
