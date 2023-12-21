package com.jdc.fileupload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/upload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var part = req.getPart("uploadFile");
		List<String> lineList = new ArrayList<String>();
		
		try(var reader = new BufferedReader(new InputStreamReader(part.getInputStream()))){

			String line = null;
			while(null != (line = reader.readLine())) {
				lineList.add(line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		req.setAttribute("list", lineList.stream().skip(1).map(Sale::new).toList());
		getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}




}




