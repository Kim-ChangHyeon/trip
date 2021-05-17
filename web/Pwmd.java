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


@WebServlet("/pwmd")
public class Pwmd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public Pwmd() {
        super();
   
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 ����ڰ� �Է��� ��й�ȣ�ϰ� ������ ����� ��й�ȣ�� ������ �˻�
				System.out.println("�Ѿ��");
				if(request.getParameter("pw")!=null) {
					HttpSession session = request.getSession();
					String pw = request.getParameter("pw");
					int no = (int)session.getAttribute("member_no");
					//String pw1=(String)session.getAttribute("member_pw");
					
					
					LoginDAO dao = new LoginDAO();
					int count = dao.pwc(no, pw);
					String pw1 = dao.pwch(no, pw);
					System.out.println("��µ�"+count+pw1+pw);
					if(pw1.equals(pw)) {
						System.out.println("�� �Ǿ���.");
						dao.pwmd(request.getParameter("npw"), no);
						response.sendRedirect("./login.jsp?loginsuccess=good");
					}else {
						response.sendRedirect("./myinfo?error=111");
					}
				}
	}

}
