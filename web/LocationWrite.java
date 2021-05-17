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
import com.poseidon.dao.LocationDAO;
import com.poseidon.dto.LocationDTO;
import com.poseidon.util.ImgReSize;

@WebServlet("/locationwrite")
public class LocationWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LocationWrite() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int grade = 0;
		if(session.getAttribute("member_grade") != null) grade = (int)session.getAttribute("member_grade");	
		RequestDispatcher rd = request.getRequestDispatcher("./locationwrite.jsp");
		request.setAttribute("grade", grade);
		request.setAttribute("location", request.getParameter("location"));
		rd.forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		HttpSession session = request.getSession();
		String location = "";
		if((int)session.getAttribute("member_grade") == 1 && (int)session.getAttribute("member_no") == 10) {
		//파일경로                                            요기변경
		//String url = request.getSession().getServletContext().getRealPath("/");
		String url = "C:/workspace/Webpage/WebContent/";
		System.out.println("실제경로 : " + url);
		int maxSize = 1024 * 1024 * 10;//10mb?
		LocationDTO dto = new LocationDTO();
		String savePath = url + "upload/";
		
		String uploadFile1 = "";
		String uploadFile2 = "";
		String uploadFile3 = "";
		String uploadFile4 = "";
		String uploadFile5 = "";
		String uploadFile6 = "";
		String uploadFile7 = "";
		String uploadFile8 = "";
		String uploadFile9 = "";
		String uploadFile10 = "";
		String newFileName1 = "";
		String newFileName2 = "";
		String newFileName3 = "";
		String newFileName4 = "";
		String newFileName5 = "";
		String newFileName6 = "";
		String newFileName7 = "";
		String newFileName8 = "";
		String newFileName9 = "";
		String newFileName10 = "";
		MultipartRequest multi = 
				new MultipartRequest(request, savePath,
						maxSize, new DefaultFileRenamePolicy());
		String title = new String(multi.getParameter("title").getBytes("8859_1"),"UTF-8");
		String content = new String(multi.getParameter("content").getBytes("8859_1"),"UTF-8");
		String locationName = new String(multi.getParameter("locationName").getBytes("8859_1"),"UTF-8");
		location = new String(multi.getParameter("location").getBytes("8859_1"),"UTF-8");
		System.out.println("multi : " + title);
		System.out.println("multi : " + content);

		long currentTime = System.currentTimeMillis();
		System.out.println(currentTime);//1611715303328
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		if(multi.getFilesystemName("file1") != null){

			uploadFile1 = multi.getFilesystemName("file1");

			File oldFile1 = new File(savePath + uploadFile1);

			newFileName1 = sdf.format(new Date(currentTime)) + "1."
						+ uploadFile1.substring(uploadFile1.lastIndexOf(".") + 1);
				
			File newFile = new File(savePath + newFileName1);
							
			if(!oldFile1.renameTo(newFile)) {
				byte[] buf = new byte[1024];
				FileInputStream fis = new FileInputStream(oldFile1);
				FileOutputStream fos = new FileOutputStream(newFile);
				int read = 0;
				while ((read = fis.read(buf, 0, buf.length)) != -1) {
						fos.write(buf, 0, read);
				}	
			}
			oldFile1.delete();
			System.out.println("서버에 저장할 파일이름 : "+newFileName1);
							
			ImgReSize.reSize(savePath, newFileName1);
			dto.setLocation_file1(newFileName1);
		}	
		
		if(multi.getFilesystemName("file2") != null){

			uploadFile2 = multi.getFilesystemName("file2");

			File oldFile2 = new File(savePath + uploadFile2);

			newFileName2 = sdf.format(new Date(currentTime)) + "2."
						+ uploadFile2.substring(uploadFile2.lastIndexOf(".") + 1);
				
			File newFile2 = new File(savePath + newFileName2);
							
			if(!oldFile2.renameTo(newFile2)) {
				byte[] buf = new byte[1024];
				FileInputStream fis = new FileInputStream(oldFile2);
				FileOutputStream fos = new FileOutputStream(newFile2);
				int read = 0;
				while ((read = fis.read(buf, 0, buf.length)) != -1) {
						fos.write(buf, 0, read);
				}	
			}
			oldFile2.delete();
			System.out.println("서버에 저장할 파일이름 : "+newFileName2);
							
			dto.setLocation_file2(newFileName2);
		}	
		
		if(multi.getFilesystemName("file3") != null){

			uploadFile3 = multi.getFilesystemName("file3");

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
							
			dto.setLocation_file3(newFileName3);
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
			
			dto.setLocation_file4(newFileName4);
		}
		
		if(multi.getFilesystemName("file5") != null){

			uploadFile5 = multi.getFilesystemName("file5");

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
									
			dto.setLocation_file5(newFileName5);
		}
		
		if(multi.getFilesystemName("file6") != null){

			uploadFile6= multi.getFilesystemName("file6");

			File oldFile6 = new File(savePath + uploadFile6);

			newFileName6 = sdf.format(new Date(currentTime)) + "6."
						+ uploadFile6.substring(uploadFile6.lastIndexOf(".") + 1);
				
			File newFile6 = new File(savePath + newFileName6);
							
			if(!oldFile6.renameTo(newFile6)) {
				byte[] buf = new byte[1024];
				FileInputStream fis = new FileInputStream(oldFile6);
				FileOutputStream fos = new FileOutputStream(newFile6);
				int read = 0;
				while ((read = fis.read(buf, 0, buf.length)) != -1) {
						fos.write(buf, 0, read);
				}	
			}
			oldFile6.delete();
			System.out.println("서버에 저장할 파일이름 : "+newFileName6);
							
			dto.setLocation_file6(newFileName6);
		}
		
		if(multi.getFilesystemName("file7") != null){

			uploadFile7 = multi.getFilesystemName("file7");

			File oldFile7 = new File(savePath + uploadFile7);

			newFileName7 = sdf.format(new Date(currentTime)) + "7."
						+ uploadFile7.substring(uploadFile7.lastIndexOf(".") + 1);
				
			File newFile7 = new File(savePath + newFileName7);
							
			if(!oldFile7.renameTo(newFile7)) {
				byte[] buf = new byte[1024];
				FileInputStream fis = new FileInputStream(oldFile7);
				FileOutputStream fos = new FileOutputStream(newFile7);
				int read = 0;
				while ((read = fis.read(buf, 0, buf.length)) != -1) {
						fos.write(buf, 0, read);
				}	
			}
			oldFile7.delete();
			System.out.println("서버에 저장할 파일이름 : "+newFileName7);
							
			dto.setLocation_file7(newFileName7);
		}
		
		if(multi.getFilesystemName("file8") != null){

			uploadFile8 = multi.getFilesystemName("file8");

			File oldFile8 = new File(savePath + uploadFile8);

			newFileName8 = sdf.format(new Date(currentTime)) + "8."
						+ uploadFile8.substring(uploadFile8.lastIndexOf(".") + 1);
				
			File newFile8 = new File(savePath + newFileName8);
							
			if(!oldFile8.renameTo(newFile8)) {
				byte[] buf = new byte[1024];
				FileInputStream fis = new FileInputStream(oldFile8);
				FileOutputStream fos = new FileOutputStream(newFile8);
				int read = 0;
				while ((read = fis.read(buf, 0, buf.length)) != -1) {
						fos.write(buf, 0, read);
				}	
			}
			oldFile8.delete();
			System.out.println("서버에 저장할 파일이름 : "+newFileName8);
								
			dto.setLocation_file8(newFileName8);
		}
		
		if(multi.getFilesystemName("file9") != null){

			uploadFile9 = multi.getFilesystemName("file9");

			File oldFile9 = new File(savePath + uploadFile9);

			newFileName9 = sdf.format(new Date(currentTime)) + "9."
						+ uploadFile9.substring(uploadFile9.lastIndexOf(".") + 1);
				
			File newFile9 = new File(savePath + newFileName9);
							
			if(!oldFile9.renameTo(newFile9)) {
				byte[] buf = new byte[1024];
				FileInputStream fis = new FileInputStream(oldFile9);
				FileOutputStream fos = new FileOutputStream(newFile9);
				int read = 0;
				while ((read = fis.read(buf, 0, buf.length)) != -1) {
						fos.write(buf, 0, read);
				}	
			}
			oldFile9.delete();
			System.out.println("서버에 저장할 파일이름 : "+newFileName9);
							
			dto.setLocation_file9(newFileName9);
		}
		
		if(multi.getFilesystemName("file10") != null){

			uploadFile10 = multi.getFilesystemName("file10");

			File oldFile10 = new File(savePath + uploadFile10);

			newFileName10 = sdf.format(new Date(currentTime)) + "0."
						+ uploadFile10.substring(uploadFile10.lastIndexOf(".") + 1);
				
			File newFile10 = new File(savePath + newFileName10);
							
			if(!oldFile10.renameTo(newFile10)) {
				byte[] buf = new byte[1024];
				FileInputStream fis = new FileInputStream(oldFile10);
				FileOutputStream fos = new FileOutputStream(newFile10);
				int read = 0;
				while ((read = fis.read(buf, 0, buf.length)) != -1) {
						fos.write(buf, 0, read);
				}	
			}
			oldFile10.delete();
			System.out.println("서버에 저장할 파일이름 : "+newFileName10);
								
			dto.setLocation_file10(newFileName10);
		}
		
		System.out.println(location);
		String gtitle = request.getParameter("title");
		
		gtitle = title.replaceAll("<", "&lt;");
		gtitle = title.replaceAll(">", "&gt;");
		
		
		dto.setLocation_title(gtitle);
		dto.setLocation_content(content);
		dto.setLocation_location(locationName);
		
		LocationDAO dao = new LocationDAO();
		dao.write(dto,location);
		
		//RequestDispatcher rd = request.getRequestDispatcher("./location");
		request.setAttribute("location", location);
		response.sendRedirect("./location?location="+location);
		//rd.forward(request, response);
		} else {
			response.sendRedirect("./location?location="+location+"&noadmin");
		}
	}

}
