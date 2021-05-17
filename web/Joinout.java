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


@WebServlet("/joinout")
public class Joinout extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Joinout() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("member_id")!=null && session.getAttribute("member_no")!=null) {
			LoginDAO dao = new LoginDAO();
			dao.memberDel((int)session.getAttribute("member_no"));
			session.invalidate();
			RequestDispatcher rd = request.getRequestDispatcher("./main?delsuccess");
			rd.forward(request, response);
		}else {
			response.sendRedirect("./main?delunsuccess");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
