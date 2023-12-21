package com.jdc.fileupload;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/downloadFile")
public class FileDownload extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var product = """
				[
					{"Name":"Coke", "Price":"700"},
					{"Name":"Pepsi", "Price":"800"},
					{"Name":"Orange", "Price":"600"},
					{"Name":"M-150", "Price":"800"}
				]
				""";
		
		resp.setHeader("Content-Type", "application/json");
//		resp.setHeader("Content-Disposition", "attachment; filename=\"product.json\"");
		resp.getWriter().append(product);
	}
}
