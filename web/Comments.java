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
import com.poseidon.util.CommentService;



@WebServlet("/comments")
public class Comments extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Comments() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();

		int cno = 0;
		int mno = 0;
		int rno = 0;
		int exe = 0;
		String name = null;
		String rccontentmodify = null;
		TripDAO dao = new TripDAO();
		if(request.getParameter("exe") != null ) {
			exe = Integer.parseInt(request.getParameter("exe"));
		}
		if(request.getParameter("cno") != null ) {
			cno = Integer.parseInt(request.getParameter("cno"));
		}
		if(session.getAttribute("member_no") != null ) {
			mno = (int)session.getAttribute("member_no");
		}
		if(session.getAttribute("member_name") != null ) {
			name = (String)session.getAttribute("member_name");
		}
		if(request.getParameter("bno") != null ) {
			rno =  Integer.parseInt(request.getParameter("bno")); 
		}
		if(request.getParameter("rccontentmodify") != null ) {
			rccontentmodify =  request.getParameter("rccontentmodify");; 
		}
		 
		String rccontent = request.getParameter("rccontent");
		String rcdate = request.getParameter("rcdate");
		if(mno > 0 && rno > 0 && name != null) {
			CommentsDTO dto = new CommentsDTO();
			dto.setCno(cno);
			dto.setMember_name(name);
			dto.setRcdate(rcdate);
			dto.setRno(rno);
			dto.setMember_no(mno);
			CommentService comment = CommentService.getInstance();
			if(exe == 1) {
				dto.setRccontent(rccontent);
				comment.insert(dto);				
			}
			if(exe == 2 ) {
				dto.setCno(cno);
				dto.setRccontent(rccontentmodify);
				System.out.println(cno);
				System.out.println(rccontentmodify);
				dao.commentupdate(dto);
			}
			response.sendRedirect("./tripdetail?bno="+rno);
		} else {
			response.sendRedirect("./error?error=1222");
		}
	}

}
