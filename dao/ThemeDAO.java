package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.poseidon.db.DBConn;
import com.poseidon.dto.ThemeDTO;

public class ThemeDAO {
	public ArrayList<ThemeDTO> list(int page,int limit,String theme) {
		ArrayList<ThemeDTO> list = new ArrayList<ThemeDTO>();
		DBConn dbConn = DBConn.getInstance();
		Connection con = dbConn.getConnection();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM food ORDER BY theme_no DESC limit ?,?";
		if(theme != null) {
			sql ="SELECT * FROM "+theme+" ORDER BY theme_no DESC limit ?,?";			
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, page);
			pstmt.setInt(2, limit);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ThemeDTO dto = new ThemeDTO();
				dto.setTheme_no(rs.getInt("theme_no"));
				dto.setTheme_title(rs.getString("theme_title"));
				dto.setTheme_content(rs.getString("theme_content"));
				dto.setTheme_file1(rs.getString("theme_file1")); 
				dto.setTheme_theme(rs.getString("theme_theme")); 
				list.add(dto);
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
		return list;
	}
	
	public void write(ThemeDTO dto,String theme) {
		DBConn dbConn = DBConn.getInstance();
		Connection con = dbConn.getConnection();
		PreparedStatement pstmt = null;
		String sql ="";	
		
		if(theme.equals("food") || theme.equals("tour") || theme.equals("play")) {
			sql ="INSERT INTO "+theme+"(theme_title,theme_content,theme_file1,theme_theme) VALUES(?,?,?,?)";
			if(dto.getTheme_file2() != null)	sql ="INSERT INTO "+theme+"(theme_title,theme_content,theme_file1,theme_theme,theme_file2) VALUES(?,?,?,?,?)";
			if(dto.getTheme_file3() != null)	sql ="INSERT INTO "+theme+"(theme_title,theme_content,theme_file1,theme_theme,theme_file2,theme_file3) VALUES(?,?,?,?,?,?)";
			if(dto.getTheme_file4() != null)	sql ="INSERT INTO "+theme+"(theme_title,theme_content,theme_file1,theme_theme,theme_file2,theme_file3,theme_file4) VALUES(?,?,?,?,?,?,?)";
			if(dto.getTheme_file5() != null)	sql ="INSERT INTO "+theme+"(theme_title,theme_content,theme_file1,theme_theme,theme_file2,theme_file3,theme_file4,theme_file5) VALUES(?,?,?,?,?,?,?,?)";
			if(dto.getTheme_file6() != null)	sql ="INSERT INTO "+theme+"(theme_title,theme_content,theme_file1,theme_theme,theme_file2,theme_file3,theme_file4,theme_file5,theme_file6) VALUES(?,?,?,?,?,?,?,?,?)";
			if(dto.getTheme_file7() != null)	sql ="INSERT INTO "+theme+"(theme_title,theme_content,theme_file1,theme_theme,theme_file2,theme_file3,theme_file4,theme_file5,theme_file6,theme_file7) VALUES(?,?,?,?,?,?,?,?,?,?)";
			if(dto.getTheme_file8() != null)	sql ="INSERT INTO "+theme+"(theme_title,theme_content,theme_file1,theme_theme,theme_file2,theme_file3,theme_file4,theme_file5,theme_file6,theme_file7,theme_file8) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			if(dto.getTheme_file9() != null)	sql ="INSERT INTO "+theme+"(theme_title,theme_content,theme_file1,theme_theme,theme_file2,theme_file3,theme_file4,theme_file5,theme_file6,theme_file7,theme_file8,theme_file9) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			if(dto.getTheme_file10() != null)   sql ="INSERT INTO "+theme+"(theme_title,theme_content,theme_file1,theme_theme,theme_file2,theme_file3,theme_file4,theme_file5,theme_file6,theme_file7,theme_file8,theme_file9,theme_file10) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";		
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTheme_title());
			pstmt.setString(2, dto.getTheme_content());
			pstmt.setString(3, dto.getTheme_file1());
			pstmt.setString(4, dto.getTheme_theme());
			if(dto.getTheme_file2() != null) pstmt.setString(5, dto.getTheme_file2());
			if(dto.getTheme_file3() != null) pstmt.setString(6, dto.getTheme_file3());
			if(dto.getTheme_file4() != null) pstmt.setString(7, dto.getTheme_file4());
			if(dto.getTheme_file5() != null) pstmt.setString(8, dto.getTheme_file5());
			if(dto.getTheme_file6() != null) pstmt.setString(9, dto.getTheme_file6());
			if(dto.getTheme_file7() != null) pstmt.setString(10, dto.getTheme_file7());
			if(dto.getTheme_file8() != null) pstmt.setString(11, dto.getTheme_file8());
			if(dto.getTheme_file9() != null) pstmt.setString(12, dto.getTheme_file9());
			if(dto.getTheme_file10() != null) pstmt.setString(13, dto.getTheme_file10());
			
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
	
	public void delete(int num,String theme) {
		DBConn dbConn = DBConn.getInstance();
		Connection con = dbConn.getConnection();
		PreparedStatement pstmt = null;
		String sql ="";		
		if(theme != null) {
			sql = "DELETE FROM "+theme+" WHERE theme_no=?";
		}
		
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
	
	public int total(String theme) {
		int total=0;
		Connection con = DBConn.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql = "SELECT COUNT(*) AS total FROM food";
		
		if(theme != null) {
			sql = "SELECT COUNT(*) AS total FROM "+theme;			
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt("total");
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
		
		return total;
	}
	
	public ThemeDTO detail(int num,String theme) {
		ThemeDTO dto = new ThemeDTO();
		DBConn dbConn = DBConn.getInstance();
		Connection con = dbConn.getConnection();
		PreparedStatement pstmt = null;
		String sql ="SELECT * FROM food WHERE theme_no=?";
		
		if(theme != null) {
			sql ="SELECT * FROM "+theme+" WHERE theme_no=?";		
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setTheme_no(rs.getInt("theme_no"));
				dto.setTheme_title(rs.getString("theme_title"));
				dto.setTheme_content(rs.getString("theme_content"));
				dto.setTheme_file1(rs.getString("theme_file1")); 
				dto.setTheme_theme(rs.getString("theme_theme"));
				if(rs.getString("theme_file2") != null) dto.setTheme_file2(rs.getString("theme_file2")); 
				if(rs.getString("theme_file3") != null) dto.setTheme_file3(rs.getString("theme_file3"));
				if(rs.getString("theme_file4") != null) dto.setTheme_file4(rs.getString("theme_file4"));
				if(rs.getString("theme_file5") != null) dto.setTheme_file5(rs.getString("theme_file5"));
				if(rs.getString("theme_file6") != null) dto.setTheme_file6(rs.getString("theme_file6"));
				if(rs.getString("theme_file7") != null) dto.setTheme_file7(rs.getString("theme_file7"));
				if(rs.getString("theme_file8") != null) dto.setTheme_file8(rs.getString("theme_file8"));
				if(rs.getString("theme_file9") != null) dto.setTheme_file9(rs.getString("theme_file9"));
				if(rs.getString("theme_file10") != null) dto.setTheme_file10(rs.getString("theme_file10"));
			
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
	
	public ArrayList<ThemeDTO> tBestList(String theme) {
		ArrayList<ThemeDTO> lBestList = new ArrayList<ThemeDTO>();
		DBConn dbConn = DBConn.getInstance();
		Connection con = dbConn.getConnection();
		PreparedStatement pstmt = null;
		String sql = "SELECT theme_no, theme_title, theme_file1 FROM food ORDER BY theme_no DESC limit 4";
		
		if(theme != null) {
			sql ="SELECT theme_no, theme_title, theme_file1 FROM "+theme+" ORDER BY theme_no DESC limit 4";		
		}
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ThemeDTO dto = new ThemeDTO();
				dto.setTheme_no(rs.getInt("theme_no"));
				dto.setTheme_title(rs.getString("theme_title"));
				dto.setTheme_file1(rs.getString("theme_file1"));  
				lBestList.add(dto);
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
		return lBestList;
	}
}
