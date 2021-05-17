package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.poseidon.db.DBConn;
import com.poseidon.dto.LocationDTO;

public class LocationDAO {
	public ArrayList<LocationDTO> list(int page,int limit,String location) {
		ArrayList<LocationDTO> list = new ArrayList<LocationDTO>();
		DBConn dbConn = DBConn.getInstance();
		Connection con = dbConn.getConnection();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM gyeonggi ORDER BY location_no DESC limit ?,?";
		if(location != null) {
			sql ="SELECT * FROM "+location+" ORDER BY location_no DESC limit ?,?";			
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, page);
			pstmt.setInt(2, limit);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				LocationDTO dto = new LocationDTO();
				dto.setLocation_no(rs.getInt("location_no"));
				dto.setLocation_title(rs.getString("location_title"));
				dto.setLocation_content(rs.getString("location_content"));
				dto.setLocation_file1(rs.getString("location_file1")); 
				dto.setLocation_location(rs.getString("location_location")); 
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
					// TODO 자동 생성된 catch 블록
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public void write(LocationDTO dto,String location) {
		DBConn dbConn = DBConn.getInstance();
		Connection con = dbConn.getConnection();
		PreparedStatement pstmt = null;
		String sql ="INSERT INTO gyeonggi(location_title,location_content,location_file1,location_location) VALUES(?,?,?,?)";	
		
		if(location != null) {
			sql ="INSERT INTO "+location+"(location_title,location_content,location_file1,location_location) VALUES(?,?,?,?)";
			if(dto.getLocation_file2() != null)	sql ="INSERT INTO "+location+"(location_title,location_content,location_file1,location_location,location_file2) VALUES(?,?,?,?,?)";
			if(dto.getLocation_file3() != null)	sql ="INSERT INTO "+location+"(location_title,location_content,location_file1,location_location,location_file2,location_file3) VALUES(?,?,?,?,?,?)";
			if(dto.getLocation_file4() != null)	sql ="INSERT INTO "+location+"(location_title,location_content,location_file1,location_location,location_file2,location_file3,location_file4) VALUES(?,?,?,?,?,?,?)";
			if(dto.getLocation_file5() != null)	sql ="INSERT INTO "+location+"(location_title,location_content,location_file1,location_location,location_file2,location_file3,location_file4,location_file5) VALUES(?,?,?,?,?,?,?,?)";
			if(dto.getLocation_file6() != null)	sql ="INSERT INTO "+location+"(location_title,location_content,location_file1,location_location,location_file2,location_file3,location_file4,location_file5,location_file6) VALUES(?,?,?,?,?,?,?,?,?)";
			if(dto.getLocation_file7() != null)	sql ="INSERT INTO "+location+"(location_title,location_content,location_file1,location_location,location_file2,location_file3,location_file4,location_file5,location_file6,location_file7) VALUES(?,?,?,?,?,?,?,?,?,?)";
			if(dto.getLocation_file8() != null)	sql ="INSERT INTO "+location+"(location_title,location_content,location_file1,location_location,location_file2,location_file3,location_file4,location_file5,location_file6,location_file7,location_file8) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			if(dto.getLocation_file9() != null)	sql ="INSERT INTO "+location+"(location_title,location_content,location_file1,location_location,location_file2,location_file3,location_file4,location_file5,location_file6,location_file7,location_file8,location_file9) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			if(dto.getLocation_file10() != null)sql ="INSERT INTO "+location+"(location_title,location_content,location_file1,location_location,location_file2,location_file3,location_file4,location_file5,location_file6,location_file7,location_file8,location_file9,location_file10) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";		
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getLocation_title());
			pstmt.setString(2, dto.getLocation_content());
			pstmt.setString(3, dto.getLocation_file1());
			pstmt.setString(4, dto.getLocation_location());
			if(dto.getLocation_file2() != null)	pstmt.setString(5, dto.getLocation_file2());
			if(dto.getLocation_file3() != null) pstmt.setString(6, dto.getLocation_file3());
			if(dto.getLocation_file4() != null) pstmt.setString(7, dto.getLocation_file4());
			if(dto.getLocation_file5() != null) pstmt.setString(8, dto.getLocation_file5());
			if(dto.getLocation_file6() != null) pstmt.setString(9, dto.getLocation_file6());
			if(dto.getLocation_file7() != null) pstmt.setString(10, dto.getLocation_file7());
			if(dto.getLocation_file8() != null) pstmt.setString(11, dto.getLocation_file8());
			if(dto.getLocation_file9() != null) pstmt.setString(12, dto.getLocation_file9());
			if(dto.getLocation_file10() != null) pstmt.setString(13, dto.getLocation_file10());
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					con.close();
				} catch (SQLException e) {
					// TODO 자동 생성된 catch 블록
					e.printStackTrace();
				}
			}
		}
	}
	
	public int total(String location) {
		int total=0;
		Connection con = DBConn.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql = "SELECT COUNT(*) AS total FROM gyeonggi";
		
		if(location != null) {
			sql = "SELECT COUNT(*) AS total FROM "+location;			
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt("total");
			}
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					con.close();
				} catch (SQLException e) {
					// TODO 자동 생성된 catch 블록
					e.printStackTrace();
				}
			}
		}
		
		return total;
	}
	
	public LocationDTO detail(int num,String location) {
		LocationDTO dto = new LocationDTO();
		DBConn dbConn = DBConn.getInstance();
		Connection con = dbConn.getConnection();
		PreparedStatement pstmt = null;
		String sql ="SELECT * FROM gyeonggi WHERE location_no=?";
		
		if(location != null) {
			sql ="SELECT * FROM "+location+" WHERE location_no=?";		
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setLocation_no(rs.getInt("location_no"));
				dto.setLocation_title(rs.getString("location_title"));
				dto.setLocation_content(rs.getString("location_content"));
				dto.setLocation_file1(rs.getString("location_file1")); 
				dto.setLocation_location(rs.getString("location_location"));
				if(rs.getString("location_file2") != null) dto.setLocation_file2(rs.getString("location_file2")); 
				if(rs.getString("location_file3") != null) dto.setLocation_file3(rs.getString("location_file3"));
				if(rs.getString("location_file4") != null) dto.setLocation_file4(rs.getString("location_file4"));
				if(rs.getString("location_file5") != null) dto.setLocation_file5(rs.getString("location_file5"));
				if(rs.getString("location_file6") != null) dto.setLocation_file6(rs.getString("location_file6"));
				if(rs.getString("location_file7") != null) dto.setLocation_file7(rs.getString("location_file7"));
				if(rs.getString("location_file8") != null) dto.setLocation_file8(rs.getString("location_file8"));
				if(rs.getString("location_file9") != null) dto.setLocation_file9(rs.getString("location_file9"));
				if(rs.getString("location_file10") != null) dto.setLocation_file10(rs.getString("location_file10"));
			
			}
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					con.close();
				} catch (SQLException e) {
					// TODO 자동 생성된 catch 블록
					e.printStackTrace();
				}
			}
		}
		return dto;
		
	}
	
	public LocationDTO grade(int num,String location) {
		LocationDTO dto = new LocationDTO();
		DBConn dbConn = DBConn.getInstance();
		Connection con = dbConn.getConnection();
		PreparedStatement pstmt = null;
		String sql ="SELECT location_no,member_grade,location_title,location_content "
				+ "FROM member m JOIN gyeonggi g on m.member_no = g.member_no WHERE m.member_no=?";
		
		if(location != null) {
			sql ="SELECT * FROM "+location+" WHERE location_no=?";		
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setLocation_no(rs.getInt("location_no"));
				dto.setLocation_title(rs.getString("location_title"));
				dto.setLocation_content(rs.getString("location_content"));
				dto.setLocation_file1(rs.getString("location_file1")); 
				dto.setLocation_location(rs.getString("location_location"));
				if(rs.getString("location_file2") != null) dto.setLocation_file2(rs.getString("location_file2")); 
				if(rs.getString("location_file3") != null) dto.setLocation_file3(rs.getString("location_file3"));
				if(rs.getString("location_file4") != null) dto.setLocation_file4(rs.getString("location_file4"));
				if(rs.getString("location_file5") != null) dto.setLocation_file5(rs.getString("location_file5"));
				if(rs.getString("location_file6") != null) dto.setLocation_file6(rs.getString("location_file6"));
				if(rs.getString("location_file7") != null) dto.setLocation_file7(rs.getString("location_file7"));
				if(rs.getString("location_file8") != null) dto.setLocation_file8(rs.getString("location_file8"));
				if(rs.getString("location_file9") != null) dto.setLocation_file9(rs.getString("location_file9"));
				if(rs.getString("location_file10") != null) dto.setLocation_file10(rs.getString("location_file10"));
			
			}
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					con.close();
				} catch (SQLException e) {
					// TODO 자동 생성된 catch 블록
					e.printStackTrace();
				}
			}
		}
		return dto;
		
	}
	
	public void delete(int num,String location) {
		DBConn dbConn = DBConn.getInstance();
		Connection con = dbConn.getConnection();
		PreparedStatement pstmt = null;
		String sql ="";		
		if(location != null) {
			sql = "DELETE FROM "+location+" WHERE location_no=?";
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
}
