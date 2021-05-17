package com.poseidon.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.poseidon.dao.TripDAO;
import com.poseidon.dto.TripDTO;
import com.poseidon.util.ReviewImgReSize;


@WebServlet("/tripwrite")
public class TripWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TripWrite() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int grade = 0;
		if(session.getAttribute("member_grade") != null) grade = (int)session.getAttribute("member_grade");	
		request.setAttribute("grade", grade);	
		RequestDispatcher rd = request.getRequestDispatcher("./tripwrite.jsp");
		rd.forward(request, response);		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		HttpSession session = request.getSession();
		String location = "";
		if((int)session.getAttribute("member_no") > 0) {                                  
		//String url = request.getSession().getServletContext().getRealPath("/");
		String url = "C:/workspace/Webpage/WebContent/";
		System.out.println("실제경로 : " + url);
		int maxSize = 1024 * 1024 * 10;//10mb?
		TripDTO dto = new TripDTO();
		String savePath = url + "upload/";
		
		String uploadrfile1 = "";
		String uploadrfile2 = "";
		String uploadFile3 = "";
		String uploadFile4 = "";
		String uploadFile5 = "";
		String newFileName1 = "";
		String newFileName2 = "";
		String newFileName3 = "";
		String newFileName4 = "";
		String newFileName5 = "";
		MultipartRequest multi = 
				new MultipartRequest(request, savePath,
						maxSize, new DefaultFileRenamePolicy());
		String title = new String(multi.getParameter("rtitle").getBytes("8859_1"),"UTF-8");
		String content = new String(multi.getParameter("rcontent").getBytes("8859_1"),"UTF-8");
		String rlocation = new String(multi.getParameter("rlocation").getBytes("8859_1"),"UTF-8");
		System.out.println("multi : " + title);
		System.out.println("multi : " + content);

		long currentTime = System.currentTimeMillis();
		System.out.println(currentTime);//1611715303328
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		if(multi.getFilesystemName("rfile1") != null){

			uploadrfile1 = multi.getFilesystemName("rfile1");

			File oldrfile1 = new File(savePath + uploadrfile1);

			newFileName1 = sdf.format(new Date(currentTime)) + "11."
						+ uploadrfile1.substring(uploadrfile1.lastIndexOf(".") + 1);
				
			File newFile = new File(savePath + newFileName1);
							
			if(!oldrfile1.renameTo(newFile)) {
				byte[] buf = new byte[1024];
				FileInputStream fis = new FileInputStream(oldrfile1);
				FileOutputStream fos = new FileOutputStream(newFile);
				int read = 0;
				while ((read = fis.read(buf, 0, buf.length)) != -1) {
						fos.write(buf, 0, read);
				}	
			}
			oldrfile1.delete();
			System.out.println("서버에 저장할 파일이름 : "+newFileName1);
							
			ReviewImgReSize.reSize(savePath, newFileName1);
			dto.setRfile1(newFileName1);
		}	
		
		if(multi.getFilesystemName("rfile2") != null){

			uploadrfile2 = multi.getFilesystemName("rfile2");

			File oldrfile2 = new File(savePath + uploadrfile2);

			newFileName2 = sdf.format(new Date(currentTime)) + "2."
						+ uploadrfile2.substring(uploadrfile2.lastIndexOf(".") + 1);
				
			File newrfile2 = new File(savePath + newFileName2);
							
			if(!oldrfile2.renameTo(newrfile2)) {
				byte[] buf = new byte[1024];
				FileInputStream fis = new FileInputStream(oldrfile2);
				FileOutputStream fos = new FileOutputStream(newrfile2);
				int read = 0;
				while ((read = fis.read(buf, 0, buf.length)) != -1) {
						fos.write(buf, 0, read);
				}	
			}
			oldrfile2.delete();
			System.out.println("서버에 저장할 파일이름 : "+newFileName2);
			ReviewImgReSize.reSize(savePath, newFileName2);		
			dto.setRfile2(newFileName2);
		}	
		
		if(multi.getFilesystemName("rfile3") != null){

			uploadFile3 = multi.getFilesystemName("rfile3");

			File oldFile3 = new File(savePath + uploadFile3);

			newFileName3 = sdf.format(new Date(currentTime)) + "3."
						+ uploadFile3.substring(uploadFile3.lastIndexOf(".") + 1);
				
			File newFile3 = new File(savePath + newFileName3);
							
			if(!oldFile3.renameTo(newFile3)) {
				byte[] buf = new byte[1024];
				FileInputStream fis = new FileInputStream(oldFile3);
				FileOutputStream fos = new FileOutputStream(newFile3);
				int read = 0;
				while ((read = fis.read(buf, 0, buf.length)) != -1) {
						fos.write(buf, 0, read);
				}	
			}
			oldFile3.delete();
			System.out.println("서버에 저장할 파일이름 : "+newFileName3);
			ReviewImgReSize.reSize(savePath, newFileName3);		
			dto.setRfile3(newFileName3);
		}
		
		if(multi.getFilesystemName("file4") != null){

			uploadFile4 = multi.getFilesystemName("file4");

			File oldFile4 = new File(savePath + uploadFile4);

			newFileName4 = sdf.format(new Date(currentTime)) + "4."
						+ uploadFile4.substring(uploadFile4.lastIndexOf(".") + 1);
				
			File newFile4 = new File(savePath + newFileName4);
							
			if(!oldFile4.renameTo(newFile4)) {
				byte[] buf = new byte[1024];
				FileInputStream fis = new FileInputStream(oldFile4);
				FileOutputStream fos = new FileOutputStream(newFile4);
				int read = 0;
				while ((read = fis.read(buf, 0, buf.length)) != -1) {
						fos.write(buf, 0, read);
				}	
			}
			oldFile4.delete();
			System.out.println("서버에 저장할 파일이름 : "+newFileName4);				
			ReviewImgReSize.reSize(savePath, newFileName4);
			dto.setRfile4(newFileName4);
		}
		
		if(multi.getFilesystemName("rfile5") != null){

			uploadFile5 = multi.getFilesystemName("rfile5");

			File oldFile5 = new File(savePath + uploadFile5);

			newFileName5 = sdf.format(new Date(currentTime)) + "5."
						+ uploadFile5.substring(uploadFile5.lastIndexOf(".") + 1);
				
			File newFile5 = new File(savePath + newFileName5);
							
			if(!oldFile5.renameTo(newFile5)) {
				byte[] buf = new byte[1024];
				FileInputStream fis = new FileInputStream(oldFile5);
				FileOutputStream fos = new FileOutputStream(newFile5);
				int read = 0;
				while ((read = fis.read(buf, 0, buf.length)) != -1) {
						fos.write(buf, 0, read);
				}	
			}
			oldFile5.delete();
			System.out.println("서버에 저장할 파일이름 : "+newFileName5);
			ReviewImgReSize.reSize(savePath, newFileName5);
			dto.setRfile5(newFileName5);
		}
		
		title = title.replaceAll("<", "&lt;");
		title = title.replaceAll(">", "&gt;");

		dto.setRtitle(title);
		dto.setRcontent(content);
		dto.setRlocation(Integer.parseInt(rlocation));
		dto.setMember_no((int)session.getAttribute("member_no"));
		
		//dto.setMember_name(name);
		TripDAO dao = new TripDAO();
		dao.write(dto);
		System.out.println(savePath);
		response.sendRedirect("./tripreview");	
		
		}
		
		
				
		}
		
	}
