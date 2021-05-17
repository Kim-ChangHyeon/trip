package com.poseidon.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.poseidon.dao.CommentDAO;
import com.poseidon.db.DBConn;
import com.poseidon.dto.CommentsDTO;

public class CommentService {
	private static CommentService service = new CommentService();
	private CommentService() {}
	public static CommentService getInstance() {
		return service;
	}
	
	public boolean update(CommentsDTO dto) {
		Connection con = DBConn.getInstance().getConnection();
		CommentDAO dao = CommentDAO.getInstace();
		dao.setConnection(con);
		
		boolean flag = false;
		try {
			CommentsDTO res = dao.getOne(dto.getCno());
			if (res.getMember_no() == dto.getMember_no()) {
				dao.update(dto);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return flag;
	}
	
	public boolean delete(int cno, int mno) {
		Connection con = DBConn.getInstance().getConnection();
		CommentDAO dao = CommentDAO.getInstace();
		dao.setConnection(con);
		
		boolean flag = false;
		try {
			CommentsDTO res = dao.getOne(cno);
			if (res.getMember_no() == mno) {
				dao.delete(cno);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public void insert(CommentsDTO dto) {
		Connection con = DBConn.getInstance().getConnection();
		CommentDAO dao = CommentDAO.getInstace();
		dao.setConnection(con);
		
		try {
			dao.insert(dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public int getCount(int cno) {
		Connection con = DBConn.getInstance().getConnection();
		CommentDAO dao = CommentDAO.getInstace();
		dao.setConnection(con);
		
		int cnt = 0;
		try {
			cnt = dao.getCount(cno);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	
	public ArrayList<CommentsDTO> getList(int ref){
		ArrayList<CommentsDTO> list = null;
		Connection con = DBConn.getInstance().getConnection();
		CommentDAO dao = CommentDAO.getInstace();
		dao.setConnection(con);
		
		try {
			list = dao.getList(ref);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
}
