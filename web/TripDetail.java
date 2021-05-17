package com.poseidon.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.CommentDAO;
import com.poseidon.dao.TripDAO;
import com.poseidon.dto.CommentsDTO;
import com.poseidon.dto.TripDTO;
import com.poseidon.util.NumberCheck;

@WebServlet("/tripdetail")
public class TripDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TripDetail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int grade = 0;
		HttpSession session = request.getSession();
		int bno=1;
		int member_no = 0;
		String member_name = "";
		int commentcount = 0;
		if(request.getParameter("bno") != null) {
			bno = NumberCheck.number(request.getParameter("bno"));
		}
		if(session.getAttribute("member_grade") != null) {
			grade = (int)session.getAttribute("member_grade");
		}
		if(session.getAttribute("member_no") != null) {
			member_no = (int)session.getAttribute("member_no");
		}
		if(session.getAttribute("member_name") != null) {
			member_name = (String)session.getAttribute("member_name");
		}
		TripDAO dao = new TripDAO();
		TripDTO dto = new TripDTO();
		CommentsDTO cdto = new CommentsDTO();
		CommentDAO cdao = new CommentDAO();
		commentcount = dao.commentcount(bno);
		ArrayList<CommentsDTO> clist = null;
		clist = dao.getList(bno);
		dto = dao.detail(bno);
		int checkuser = dao.userfind(bno, member_no);
		request.setAttribute("bno", bno);
		request.setAttribute("detail", dto);
		request.setAttribute("grade", grade);
		request.setAttribute("checkuser", checkuser);
		request.setAttribute("member_name", member_name);
		request.setAttribute("member_no", member_no);
		request.setAttribute("clist", clist);
		request.setAttribute("commentcount", commentcount);
		RequestDispatcher rd = request.getRequestDispatcher("./tripdetail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
