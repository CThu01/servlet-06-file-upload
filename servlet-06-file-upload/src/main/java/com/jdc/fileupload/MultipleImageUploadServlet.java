package com.jdc.fileupload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/multipleImage")
@MultipartConfig
public class MultipleImageUploadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		var parts = req.getParts();
		List<String> imageList = new ArrayList<String>();
		
		for(var part : parts) {
			
			if(part.getContentType().startsWith("image")) {
				
				var imageName = part.getSubmittedFileName();
				System.out.println("getContentType : %s".formatted(part.getContentType()));
				
				imageList.add(imageName);
				var filePath = Path.of(getServletContext().getRealPath("images"),imageName );
				Files.copy(part.getInputStream(), filePath,StandardCopyOption.REPLACE_EXISTING);
			}		
		}
		
		req.setAttribute("photos", imageList);
		getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);

	}

}
