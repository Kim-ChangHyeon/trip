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
import com.poseidon.dto.CommentsDTO;
import com.poseidon.dto.LoginDTO;
import com.poseidon.dto.ThemeDTO;
import com.poseidon.dto.TripDTO;

@WebServlet("/admindetail")
public class AdminDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDetail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TripDAO dao = new TripDAO();
		LoginDAO ldao = new LoginDAO();
		String name = null;
		int grade = 0;
		int mno = 0;
		if(request.getParameter("mno") != null) mno = Integer.parseInt(request.getParameter("mno"));
		if (session.getAttribute("member_grade") != null) grade = (int)session.getAttribute("member_grade");
		if((int)session.getAttribute("member_no") == 10 && (int)session.getAttribute("member_grade") == 1) {
			ArrayList<TripDTO>list = new ArrayList<TripDTO>();
			ArrayList<CommentsDTO> clist = new ArrayList<CommentsDTO>();
			list = dao.adminMemberReviewCheck(mno);
			clist = dao.adminMemberCommentsCheck(mno);
			name = ldao.adminMemberNo(mno);
			
			request.setAttribute("list", list);		
			request.setAttribute("clist", clist);		
			request.setAttribute("grade", grade);		
			request.setAttribute("name", name);		
			RequestDispatcher rd = request.getRequestDispatcher("./admindetail.jsp");
			rd.forward(request, response);			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
