package com.jdc.fileupload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/imageUpload")
@MultipartConfig
public class ImageUploadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		var part = req.getPart("imageFile");
		
		if(null != part) {
			var path = Path.of(getServletContext().getRealPath("images"),part.getSubmittedFileName());
			Files.copy(part.getInputStream(), path);
			System.out.println("RealPath : %s".formatted(path));
			System.out.println("ContextPath : %s".formatted(getServletContext().getContextPath().concat("\\images\\").concat(part.getSubmittedFileName().toString())));
			req.setAttribute("photo", part.getSubmittedFileName());
		}
		
		getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}

}









