package com.poseidon.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.TripDAO;
import com.poseidon.dto.TripDTO;

@WebServlet("/tripdelete")
public class TripDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TripDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int member_no = 0;
		int bno = 0;
		String member_name = "";
		int grade = 0;
		if(session.getAttribute("member_no") != null) member_no = (int)session.getAttribute("member_no");
		if(session.getAttribute("member_grade") != null) grade = (int)session.getAttribute("member_grade");
		if(session.getAttribute("member_name") != null) member_name = (String)session.getAttribute("member_name");
		if(request.getParameter("bno") != null) bno =Integer.parseInt(request.getParameter("bno"));
		TripDAO dao = new TripDAO();
		TripDTO dto = dao.tripMno(bno);
		if((request.getParameter("bno") != null && session.getAttribute("member_no") != null && session.getAttribute("member_name") != null
				&& dto.getMember_name().equals(member_name) && dto.getMember_no() == member_no) || (grade == 1 && member_no == 10)) {
			dao.delete(bno);
			response.sendRedirect("./tripreview");
		} else {
			response.sendRedirect("./tripdetail?bno="+bno+"&disagree");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
