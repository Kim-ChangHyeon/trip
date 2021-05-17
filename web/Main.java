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

import com.poseidon.dao.ThemeDAO;
import com.poseidon.dao.TripDAO;
import com.poseidon.dto.ThemeDTO;
import com.poseidon.dto.TripDTO;

@WebServlet("/main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Main() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String theme = "food";
		TripDAO dao = new TripDAO();
		ThemeDAO tdao = new ThemeDAO();
		int grade = 0;
		int member_no = 0;
		ArrayList<TripDTO> bestList = dao.bestList();
		
		if( request.getParameter("theme") != null && (request.getParameter("theme").equals("food") || request.getParameter("theme").equals("tour") ||
				request.getParameter("theme").equals("play"))) {
			theme = request.getParameter("theme");
		}
		if(session.getAttribute("member_grade") != null ) {
			grade = (int)session.getAttribute("member_grade");
		}
		if(session.getAttribute("member_no") != null ) {
			member_no = (int)session.getAttribute("member_no");
		}
		ArrayList<ThemeDTO> tBestList = tdao.tBestList(theme);

		request.setAttribute("theme", theme);		
		request.setAttribute("bestList", bestList);
		request.setAttribute("tBestList", tBestList);
		request.setAttribute("grade", grade);
		request.setAttribute("member_no", member_no);
		RequestDispatcher rd = request.getRequestDispatcher("./main.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
