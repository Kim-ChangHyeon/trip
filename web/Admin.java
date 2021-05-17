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

import com.poseidon.dao.LoginDAO;
import com.poseidon.dao.ThemeDAO;
import com.poseidon.dao.TripDAO;
import com.poseidon.dto.LoginDTO;
import com.poseidon.dto.ThemeDTO;
import com.poseidon.dto.TripDTO;

@WebServlet("/admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		LoginDAO dao = new LoginDAO();
		int grade = 0;
		if (session.getAttribute("member_grade") != null) grade = (int)session.getAttribute("member_grade");
		if((int)session.getAttribute("member_no") == 10 && (int)session.getAttribute("member_grade") == 1) {
			ArrayList<LoginDTO> list = new ArrayList<LoginDTO>();
			list = dao.adminMember();
			request.setAttribute("member", list);		
			request.setAttribute("grade", grade);		
			RequestDispatcher rd = request.getRequestDispatcher("./admin.jsp");
			rd.forward(request, response);			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
