package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.poseidon.dto.LoginDTO;
import com.poseidon.dto.CommentsDTO;
import com.poseidon.db.DBConn;

public class LoginDAO {

	public void join(LoginDTO dto) {
		Connection conn = DBConn.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into member"
				+ "(member_id, member_name, member_email, member_pw, member_addr, member_birth, "
				+ "member_tel, member_gender) values(?, ?, ?, ?, ?, ?, ?, ?) ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMember_id());
			pstmt.setString(2, dto.getMember_name());
			pstmt.setString(3, dto.getMember_email1() + "@" + dto.getMember_email2());
			pstmt.setString(4, dto.getMember_pw());
			pstmt.setString(5, dto.getMember_addr1() + " " + dto.getMember_addr2() + " " + dto.getMember_addr3() + " "
					+ dto.getMember_addr4());
			pstmt.setString(6, dto.getMember_birth());
			pstmt.setString(7, dto.getMember_tel1() + "-" + dto.getMember_tel2() + "-" + dto.getMember_tel3());
			pstmt.setString(8, dto.getMember_gender());
			
			pstmt.execute();
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

	}

	public LoginDTO login(LoginDTO dto) {//�씠寃� 濡쒓렇�씤 �븯�뒗 紐낅졊�씠怨좎슂.
		Connection con = DBConn.getInstance().getConnection();

		String sql = "select count(*) as count, member_name, member_no,member_grade from member where member_id=? and member_pw=?";

		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMember_id());
			pstmt.setString(2, dto.getMember_pw());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) != 0) {
					dto.setMember_name(rs.getString("member_name"));
					dto.setMember_no(rs.getInt("member_no"));
					dto.setLoginCheck(rs.getInt("count"));
					dto.setMember_grade(rs.getInt("member_grade"));
				}
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
		return dto;
	}

	public int idCheck(String parameter) {
		Connection con = DBConn.getInstance().getConnection();
		String sql = "SELECT count(*) FROM member WHERE member_id=?";
		PreparedStatement pstmt = null;
		int result = 1;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, parameter);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
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
		return result;
	}
	public LoginDTO myinfo(LoginDTO dto){
		
		
		Connection conn = DBConn.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql="select * from member where member_no=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getMember_no());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				
				dto.setMember_id(rs.getString("member_id"));
				dto.setMember_pw(rs.getString("member_pw"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setMember_birth(rs.getString(("member_birth")));
				dto.setMember_email(rs.getString("member_email"));
				//dto.setMember_email2(rs.getString("member_email2"));
				dto.setMember_tel(rs.getString(("member_tel")));
				//dto.setMember_tel2(rs.getString(("member_tel2")));
				//dto.setMember_tel3(rs.getString(("member_tel3")));
				dto.setMember_addr(rs.getString("member_addr"));
				//dto.setMember_addr2(rs.getString("member_addr2"));
				//dto.setMember_addr3(rs.getString("member_addr3"));
				//dto.setMember_addr4(rs.getString("member_addr4"));
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		return dto;
	}
	public void pwmd(String member_pw, int member_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		conn=DBConn.getInstance().getConnection();
		String sql = "update member set member_pw=? where member_no=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,member_pw);
			pstmt.setInt(2, member_no);
			pstmt.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	public int pwc(int member_no, String member_pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		conn=DBConn.getInstance().getConnection();
		int count = 0;
		
		String sql = "select count(*) as count from member where member_pw=? AND member_no=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, member_pw);
			pstmt.setInt(2, member_no);
			
			
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
	public String pwch(int member_no, String member_pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		conn=DBConn.getInstance().getConnection();
		String pw = "";
		
		String sql = "select member_pw from member where member_pw=? AND member_no=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, member_pw);
			pstmt.setInt(2, member_no);
			
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				pw = rs.getString("member_pw");
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
		return pw;
	}
	public void memberDel(int member_no) {
		Connection conn=DBConn.getInstance().getConnection();
		PreparedStatement pstmt=null;
		
		String sql = "delete from member where member_no=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			pstmt.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void gradeChange(int member_no,int member_grade) {
		Connection conn=DBConn.getInstance().getConnection();
		PreparedStatement pstmt=null;
		
		String sql = "UPDATE member SET member_grade=? where member_no=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, member_grade);
			pstmt.setInt(2, member_no);
			pstmt.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public ArrayList<LoginDTO> adminMember() {
		Connection conn=DBConn.getInstance().getConnection();
		PreparedStatement pstmt=null;
		ArrayList<LoginDTO> list = new ArrayList<LoginDTO>();
		String sql = "SELECT * FROM member";
		
		try {
			pstmt=conn.prepareStatement(sql);
			ResultSet rs =pstmt.executeQuery();
			
			while(rs.next()) {
				LoginDTO dto = new LoginDTO();
				dto.setMember_no(rs.getInt("member_no"));
				dto.setMember_id(rs.getString("member_id"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setMember_pw(rs.getString("member_pw"));
				dto.setMember_email(rs.getString("member_email"));
				dto.setMember_gender(rs.getString("member_gender"));
				dto.setMember_addr(rs.getString("member_addr"));
				dto.setMember_tel(rs.getString("member_tel"));
				dto.setMember_birth(rs.getString("member_birth"));
				dto.setMember_grade(rs.getInt("member_grade"));
				list.add(dto);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			if(pstmt!=null) {
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
	
	public String adminMemberCheck(int member_no, String member_pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		conn=DBConn.getInstance().getConnection();
		String pw = "";
		
		String sql = "select member_pw from member where member_pw=? AND member_no=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, member_pw);
			pstmt.setInt(2, member_no);
			
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				pw = rs.getString("member_pw");
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
		return pw;
	}
	
	public String adminMemberNo(int member_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		conn=DBConn.getInstance().getConnection();
		String name ="";
		String sql = "select member_name from member where member_no=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				name = rs.getString("member_name");
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
		return name;
	}
}
