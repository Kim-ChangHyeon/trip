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

import com.poseidon.dto.LocationDTO;
import com.poseidon.util.NumberCheck;
import com.poseidon.dao.LocationDAO;

@WebServlet("/location")
public class Location extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Location() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		ArrayList<LocationDTO> list = new ArrayList<LocationDTO>();
		LocationDAO dao = new LocationDAO();
		int limit = 10;
		int on = 1;
		int page = 1;
		String view="list";
		String location = "gyeonggi";
		int grade = 0;
		if( request.getParameter("location") != null && (request.getParameter("location").equals("seoul") || request.getParameter("location").equals("gyeonggi")  || 
				request.getParameter("location").equals("gangwon") || request.getParameter("location").equals("chungcheong") || 
				request.getParameter("location").equals("busan") || request.getParameter("location").equals("daejeon") || 
				request.getParameter("location").equals("daegu") || request.getParameter("location").equals("ulsan") ||
				request.getParameter("location").equals("jeolla") || request.getParameter("location").equals("gyeongsang") || 
				request.getParameter("location").equals("jeju") || request.getParameter("location").equals("gwangju"))) {
			location = request.getParameter("location");
		}
		int total = dao.total(location);
		if( request.getParameter("limit") != null) {
			limit = NumberCheck.number(request.getParameter("limit"));
		}
		if( request.getParameter("page") != null) {
			if(NumberCheck.number(request.getParameter("page")) <= ((total/limit)+1)) {
				page = NumberCheck.number(request.getParameter("page"));	
			}
		}
		if( request.getParameter("on") != null && NumberCheck.number(request.getParameter("on")) < 4) {
			on = NumberCheck.number(request.getParameter("on"));
		}
		if( request.getParameter("view") != null &&(request.getParameter("view").equals("list") || request.getParameter("view").equals("board"))) {
			view = request.getParameter("view");
		}
		if(session.getAttribute("member_grade") != null) {
			grade = (int)session.getAttribute("member_grade");
		}
		if(limit == 10) {
			list = dao.list(page*limit-limit,limit,location);
			
		} else if(limit == 20) {
			list = dao.list(page*limit-limit,limit,location);
			
		} else if(limit == 1000) {
			list = dao.list(page*limit-limit,limit,location);
		} else {
			list = dao.list(page*10-10,10,location);
		}
		request.setAttribute("list", list);
		request.setAttribute("limit", limit);
		request.setAttribute("page", page);
		request.setAttribute("total", total);
		request.setAttribute("on", on);		
		request.setAttribute("view", view);			
		request.setAttribute("location", location);		
		request.setAttribute("grade", grade);		
		RequestDispatcher rd = request.getRequestDispatcher("./location.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
