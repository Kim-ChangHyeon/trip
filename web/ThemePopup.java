package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.ThemeDAO;
import com.poseidon.dto.ThemeDTO;

@WebServlet("/themepopup")
public class ThemePopup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ThemePopup() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ThemeDAO dao = new ThemeDAO();
		ThemeDTO dto = new ThemeDTO();
		int limit = 10;
		int on = 1;
		int page = 1;
		int pop = 0;
		String view="list";
		String theme = "food";
		String imgtotal = "01";
		HttpSession session = request.getSession();
		int grade = 0;
		if(session.getAttribute("member_grade") != null) grade = (int)session.getAttribute("member_grade");	
		if( request.getParameter("theme") != null && (request.getParameter("theme").equals("food")  || request.getParameter("theme").equals("tour")  ||
				request.getParameter("theme").equals("play") )) {
			theme = request.getParameter("theme");
		}
		if( request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		int total = dao.total(theme);
		if( request.getParameter("page") != null) {
			if(Integer.parseInt(request.getParameter("page")) < (total/limit)) {
				page = Integer.parseInt(request.getParameter("page"));	
			}
		}
		if( request.getParameter("on") != null && Integer.parseInt(request.getParameter("on")) < 4) {
			on = Integer.parseInt(request.getParameter("on"));
		}
		if( request.getParameter("view") != null &&(request.getParameter("view").equals("list") || request.getParameter("view").equals("board"))) {
			view = request.getParameter("view");
		}
		if( request.getParameter("pop") != null) {
			pop = Integer.parseInt(request.getParameter("pop"));
		}
		dto = dao.detail(pop, theme);
		if(dto.getTheme_file2() != null) imgtotal = "02";
		if(dto.getTheme_file3() != null) imgtotal = "03";
		if(dto.getTheme_file4() != null) imgtotal = "04";
		if(dto.getTheme_file5() != null) imgtotal = "05";
		if(dto.getTheme_file6() != null) imgtotal = "06";
		if(dto.getTheme_file7() != null) imgtotal = "07";
		if(dto.getTheme_file8() != null) imgtotal = "08";
		if(dto.getTheme_file9() != null) imgtotal = "09";
		if(dto.getTheme_file10() != null) imgtotal = "10";
		System.out.println("imgtotal : "+imgtotal);
		RequestDispatcher rd = request.getRequestDispatcher("./themepopup.jsp");
		request.setAttribute("detail", dto);
		request.setAttribute("limit", limit);
		request.setAttribute("page", page);
		request.setAttribute("total", total);
		request.setAttribute("on", on);		
		request.setAttribute("view", view);		
		request.setAttribute("pop", pop);		
		request.setAttribute("theme", theme);		
		request.setAttribute("imgtotal", imgtotal);		
		request.setAttribute("grade", grade);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
