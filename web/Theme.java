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

import com.poseidon.dto.ThemeDTO;
import com.poseidon.dao.ThemeDAO;

@WebServlet("/theme")
public class Theme extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Theme() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		ArrayList<ThemeDTO> list = new ArrayList<ThemeDTO>();
		ThemeDAO dao = new ThemeDAO();
		HttpSession session = request.getSession();
		int limit = 10;
		int on = 1;
		int page = 1;
		int pop = 0;
		String view="list";
		String theme = "food";
		int grade = 0;
		if( request.getParameter("theme") != null && (request.getParameter("theme").equals("food") || request.getParameter("theme").equals("tour") ||
				request.getParameter("theme").equals("play"))) {
			theme = request.getParameter("theme");
		}
		int total = dao.total(theme);
		if( request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		if( request.getParameter("page") != null) {
			if(Integer.parseInt(request.getParameter("page")) <= ((total/limit)+1)) {
				page = Integer.parseInt(request.getParameter("page"));	
			}
		}
		if( request.getParameter("on") != null && Integer.parseInt(request.getParameter("on")) < 4) {
			on = Integer.parseInt(request.getParameter("on"));
		}
		if( request.getParameter("view") != null &&(request.getParameter("view").equals("list") || request.getParameter("view").equals("board"))) {
			view = request.getParameter("view");
		}
		if( request.getParameter("pop") != null) {
			pop = Integer.parseInt(request.getParameter("pop"));
		}
		if(session.getAttribute("member_grade") != null) {
			grade = (int)session.getAttribute("member_grade");
		}
		if(limit == 10) {
			list = dao.list(page*limit-limit,limit,theme);
			
		} else if(limit == 20) {
			list = dao.list(page*limit-limit,limit,theme);
			
		} else if(limit == 1000) {
			list = dao.list(page*limit-limit,limit,theme);
		} else {
			list = dao.list(page*10-10,10,theme);
		}
		request.setAttribute("list", list);
		request.setAttribute("limit", limit);
		request.setAttribute("page", page);
		request.setAttribute("total", total);
		request.setAttribute("on", on);		
		request.setAttribute("view", view);		
		request.setAttribute("pop", pop);		
		request.setAttribute("theme", theme);		
		request.setAttribute("grade", grade);		
		RequestDispatcher rd = request.getRequestDispatcher("./theme.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
