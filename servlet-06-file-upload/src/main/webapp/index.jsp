<%@page import="com.jdc.fileupload.Sale"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>File Upload</title>

<style>
	
	body{
		text-align: center;
		min-height: 100 vh;
	}
	
	table{
		margin-top: 2rem;
		width: 80%;
	}
	
	tr,td{
		border: 1px solid gray;
	}
	
	.photos{
		margin-right: 2rem;
		margin-top: 2rem;
		display: flex;
	}
</style>

</head>
<body>

	<h1>Servlet File Upload Test</h1>

	<form action="upload" enctype="multipart/form-data" method="post">
		<label>Select File</label><br />
		<input type="file" name="uploadFile">
		<button type="submit">Upload</button>
	</form>
	
	<% 
		List<Sale> saleList = (List<Sale>)request.getAttribute("list");
		if(null != saleList && !saleList.isEmpty()){
			
	%>

			<table>
				<theader>
					<td>Category</td>
					<td>Product</td>
					<td>Price</td>
					<td>Count</td>
					<td>Total</td>
				</theader>
			
	
			<% for(Sale s : saleList){%>
				
				<tbody>
					<tr>
						<td><%=s.getCategory() %></td>
						<td><%=s.getProduct() %></td>
						<td><%=s.getPrice() %></td>
						<td><%=s.getCount() %></td>
						<td><%=s.getTotal() %></td>
					</tr>
				</tbody>
			
			<%
				}
			%>
			</table>

	<%
		}
	%>
	
	<h3>Image Upload</h3>
	
	<form action="imageUpload" enctype="multipart/form-data" method="post">
		<label>Select Image</label>
		<input type="file" name="imageFile" accept="image/*">
		<button type="sumbmit">Upload Image</button>
	</form>
	
	<% if( null != request.getAttribute("photo")){
		String filePath = getServletContext().getContextPath().concat("/images/").concat(request.getAttribute("photo").toString());
		String realPath = getServletContext().getRealPath("\\images").concat("\\").concat(request.getAttribute("photo").toString());
	%>
		<div>
			<img src=<%=filePath %> width=200px/>
			<p><%=realPath %></p>
			<%-- <p><%=realPath %></p> --%>
		</div>
		<% 
	}
		%>
		
	<h3>Multiple Images Upload</h3>
	
	<form action="multipleImage" enctype="multipart/form-data" method="post">
		<label>Select Multiple Images</label>
		<input type="file" name="multipleFiles" multiple="multiple" accept="image/*">
		<button type="submit">Upload Images</button>
	</form>
	
	<%
		if(null != request.getAttribute("photos")){
			List<String> fileNameList = (List<String>) request.getAttribute("photos");
			
			for(String fileName : fileNameList){
				var imageName = request.getServletContext().getContextPath().concat("/images/").concat(fileName).toString();
	%>					
				<div class="photos">
					<img src="<%=imageName %>" alt="" width="100px"/>
				</div>

	<%      
			}
		}
	%>
	
	<a href="downloadFile">Download File</a>
	
</body>
</html>










