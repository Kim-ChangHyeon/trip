package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.TripDAO;
import com.poseidon.dto.TripDTO;


@WebServlet("/tripmodify")
public class TripModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public TripModify() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TripDAO dao = new TripDAO();
		TripDTO dto = new TripDTO();
		TripDTO dtto = new TripDTO();
		int member_no = 0;
		int bno = 0;
		int mno = 0;
		int grade = 0;
		if(session.getAttribute("member_no") != null) member_no = (int)session.getAttribute("member_no");
		if(session.getAttribute("member_grade") != null) grade = (int)session.getAttribute("member_grade");
		if(request.getParameter("bno") != null) {
			bno = Integer.parseInt(request.getParameter("bno"));
			dtto = dao.tripMno(bno);
			mno = dtto.getMember_no();
		}
		if ((session.getAttribute("member_no") != null &&session.getAttribute("member_name") != null && request.getParameter("bno") != null
				&& member_no == mno) || (member_no==10 && grade ==1)) {
			dto = dao.detail(bno,mno);
			if (dto.getMember_name() != null) {
				RequestDispatcher rd = request.getRequestDispatcher("./tripmodify.jsp");
				request.setAttribute("modify", dto);
				request.setAttribute("bno", bno);
				request.setAttribute("grade", grade);
				rd.forward(request, response);
			} else {
				response.sendRedirect("./tripdetail?bno="+bno+"&disagree");
			} 
		} else {
			response.sendRedirect("./tripdetail?bno="+bno+"&disagree");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
