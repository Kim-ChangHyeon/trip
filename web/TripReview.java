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

import com.poseidon.dao.TripDAO;
import com.poseidon.dto.CommentsDTO;
import com.poseidon.dto.TripDTO;
import com.poseidon.util.NumberCheck;

@WebServlet("/tripreview")
public class TripReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TripReview() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		TripDAO dao = new TripDAO();
		ArrayList<CommentsDTO> dto = new ArrayList<CommentsDTO>();
		int page = 1;
		int grade = 0;
		int count = 0;
		if (request.getParameter("page") != null) {
			page = NumberCheck.number(request.getParameter("page"));
		}
		int limit = 10;
		if (request.getParameter("limit") != null) {
			limit = NumberCheck.number(request.getParameter("limit"));
		}
		if(session.getAttribute("member_grade") != null) {
			grade = (int)session.getAttribute("member_grade");
		}
		if(dao.tripCount() != 0) count = dao.tripCount();
		ArrayList<TripDTO> list = dao.list(page * limit - limit ,limit );
		ArrayList<TripDTO> bestList = dao.bestList();
		dto = dao.commentcount();
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.setAttribute("page", page);
		request.setAttribute("limit", limit);
		request.setAttribute("bestList", bestList);
		request.setAttribute("grade", grade);
		request.setAttribute("commentcount", dto);
		RequestDispatcher rd = request.getRequestDispatcher("./tripreview.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
