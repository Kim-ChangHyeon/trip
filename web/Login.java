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


@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LoginDAO dao = new LoginDAO();
    
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("./login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println(id + ":" + pw);
		if(id.length()>4||pw.length()>2) {
			LoginDTO dto = new LoginDTO();
			dto.setMember_id(id);
			dto.setMember_pw(pw);	
			dto = dao.login(dto);
			
			if(dto.getMember_name() != null && dto.getMember_no() > 0 && dto.getMember_id() != null) {
				HttpSession session = request.getSession();
				session.setAttribute("member_name", dto.getMember_name());
				session.setAttribute("member_no", dto.getMember_no());
				session.setAttribute("member_id", dto.getMember_id());
				session.setAttribute("loginCheck", dto.getLoginCheck()); 
				session.setAttribute("member_grade", dto.getMember_grade()); 
				response.sendRedirect("./main");
			}else {
				response.sendRedirect("./login?loginerror");
			}
		}else {
			response.sendRedirect("./login?errorlength");
		}
	}

}
