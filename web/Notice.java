package com.poseidon.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.NoticeDAO;
import com.poseidon.dto.NoticeDTO;
import com.poseidon.util.NumberCheck;

@WebServlet("/notice")
public class Notice extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Notice() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<NoticeDTO> list = new ArrayList<NoticeDTO>();
		NoticeDAO dao = new NoticeDAO();
		
		int page = 1;
		int limit = 10;
		int on =1;
		int grade = 0;
		if(request.getParameter("page") != null ) {
			page = NumberCheck.number((request.getParameter("page")));
		}
		if(request.getParameter("limit") != null ) {
			limit = NumberCheck.number((request.getParameter("limit")));
		}
		if(request.getParameter("on") != null && NumberCheck.number(request.getParameter("on")) < 4) {
			on = NumberCheck.number((request.getParameter("on")));
		}
		if(session.getAttribute("member_grade") != null) {
			grade = (int)session.getAttribute("member_grade");
		}
		if(limit == 10) {
			list = dao.list(page*limit-limit,limit);	
		} else if(limit == 20) {
			list = dao.list(page*limit-limit,limit);	
		} else if(limit == 1000) {
			list = dao.list(page*limit-limit,limit);	
		} else {
			list = dao.list(page*10-10,10);
		}
		
		int total = dao.page();
		request.setAttribute("total", total);
		request.setAttribute("page", page);
		request.setAttribute("limit", limit);
		request.setAttribute("on", on);
		request.setAttribute("grade", grade);
		RequestDispatcher rd = request.getRequestDispatcher("./notice.jsp");
		request.setAttribute("list", list);
		rd.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ArrayList<NoticeDTO> list = new ArrayList<NoticeDTO>();
		NoticeDAO dao = new NoticeDAO();
		
		int page = 1;
		int limit = 10;
		int on =1;
		String opt = "title";
		String condition = "";
		if(request.getParameter("page") != null ) {
			page = NumberCheck.number((request.getParameter("page")));
		}
		if(request.getParameter("limit") != null ) {
			limit = NumberCheck.number((request.getParameter("limit")));
		}
		if(request.getParameter("on") != null && NumberCheck.number(request.getParameter("on")) < 4) {
			on = NumberCheck.number((request.getParameter("on")));
		}
		if(request.getParameter("opt") != null) {
			opt = request.getParameter("opt");
		}
		if(request.getParameter("condition") != null) {
			condition = "%"+request.getParameter("condition")+"%";
		}
		
		if(limit == 10) {
			list = dao.search(page*limit-limit,condition,opt);	
		} else if(limit == 20) {
			list = dao.search(page*limit-limit,condition,opt);	
		} else if(limit == 1000) {
			list = dao.search(page*limit-limit,condition,opt);	
		} else {
			list = dao.search(page*10-10,condition,opt);
		}
		
		int total = dao.page(condition,opt);
		request.setAttribute("total", total);
		request.setAttribute("page", page);
		request.setAttribute("limit", limit);
		request.setAttribute("on", on);
		request.setAttribute("opt", opt);
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("./notice.jsp");
		rd.forward(request, response);
	}

}
