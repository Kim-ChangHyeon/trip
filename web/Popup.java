package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.LocationDAO;
import com.poseidon.dto.LocationDTO;

@WebServlet("/popup")
public class Popup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Popup() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocationDAO dao = new LocationDAO();
		LocationDTO dto = new LocationDTO();
		int limit = 10;
		int on = 1;
		int page = 1;
		int pop = 0;
		String view="list";
		String location = "gyeonggi";
		String imgtotal = "01";
		HttpSession session = request.getSession();
		int grade = 0;
		if(session.getAttribute("member_grade") != null) grade = (int)session.getAttribute("member_grade");	
		if( request.getParameter("location") != null && (request.getParameter("location").equals("seoul") || request.getParameter("location").equals("gyeonggi")  || 
				request.getParameter("location").equals("gangwon") || request.getParameter("location").equals("chungcheng") || 
				request.getParameter("location").equals("busan") || request.getParameter("location").equals("daejeon") || 
				request.getParameter("location").equals("daegu") || request.getParameter("location").equals("ulsan") ||
				request.getParameter("location").equals("jeolla") || request.getParameter("location").equals("gyeongsang") || 
				request.getParameter("location").equals("jeju") || request.getParameter("location").equals("gwangju"))) {
			location = request.getParameter("location");
		}
		if( request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		int total = dao.total(location);
		if( request.getParameter("page") != null) {
			if(Integer.parseInt(request.getParameter("page")) <= ((total/limit)+1)) {
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
		
		dto = dao.detail(pop, location);
		if(dto.getLocation_file2() != null) imgtotal = "02";
		if(dto.getLocation_file3() != null) imgtotal = "03";
		if(dto.getLocation_file4() != null) imgtotal = "04";
		if(dto.getLocation_file5() != null) imgtotal = "05";
		if(dto.getLocation_file6() != null) imgtotal = "06";
		if(dto.getLocation_file7() != null) imgtotal = "07";
		if(dto.getLocation_file8() != null) imgtotal = "08";
		if(dto.getLocation_file9() != null) imgtotal = "09";
		if(dto.getLocation_file10() != null) imgtotal = "10";
		RequestDispatcher rd = request.getRequestDispatcher("./popup.jsp");
		request.setAttribute("detail", dto);
		request.setAttribute("limit", limit);
		request.setAttribute("page", page);
		request.setAttribute("total", total);
		request.setAttribute("on", on);		
		request.setAttribute("view", view);		
		request.setAttribute("pop", pop);		
		request.setAttribute("location", location);		
		request.setAttribute("imgtotal", imgtotal);		
		request.setAttribute("grade", grade);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
