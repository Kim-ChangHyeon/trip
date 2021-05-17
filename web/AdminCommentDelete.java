package com.poseidon.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.TripDAO;
import com.poseidon.dto.CommentsDTO;

@WebServlet("/admincommentdelete")
public class AdminCommentDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminCommentDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();

		PrintWriter out = response.getWriter();

		
		int member_no = 0;
		int grade = 0;
		int mno = 0;
		String mname = null;
		int bno = 0;
		int cno = 0;
		TripDAO dao = new TripDAO();
		CommentsDTO dto = new CommentsDTO();
		if(request.getParameter("cno") != null) {
			cno =Integer.parseInt(request.getParameter("cno"));
		}
		if(cno != 0) {
			dto = dao.commentMno(cno);			
			mno = dto.getMember_no();
			mname = dto.getMember_name();
		}	
		if(session.getAttribute("member_no") != null) {
			member_no = (int)session.getAttribute("member_no");
		}
		if(session.getAttribute("member_grade") != null) {
			grade = (int)session.getAttribute("member_grade");
		}
		if(request.getParameter("bno") != null) {
			bno =Integer.parseInt(request.getParameter("bno"));
		}
		if((request.getParameter("bno") != null && member_no > 0 && member_no == mno && request.getParameter("cno") != null && mname != null)
				|| (grade==1 && member_no == 10)) {
			dao.commentdelete(cno);
			response.sendRedirect("./admindetail?mno="+mno);
		} else {
			out.println("<script>");
			out.println("alert('본인의 댓글만 삭제 가능합니다!');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
