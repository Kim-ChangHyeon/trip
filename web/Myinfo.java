package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.LoginDAO;
import com.poseidon.dto.LoginDTO;


@WebServlet("/myinfo")
public class Myinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Myinfo() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		LoginDTO dto = new LoginDTO();
		if(session.getAttribute("member_id")!=null && session.getAttribute("member_no")!=null && session.getAttribute("member_name")!=null) {
			dto.setMember_no((int)session.getAttribute("member_no"));
			LoginDAO dao = new LoginDAO();
			dto = dao.myinfo(dto);
			request.setAttribute("dto", dto);
			RequestDispatcher rd = request.getRequestDispatcher("/myinfo.jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
