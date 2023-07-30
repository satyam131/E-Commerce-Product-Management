<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "com.nagarro.model.Product"%>
<html>
<head>
<title>Product Management Tool</title>
<link rel="stylesheet" href="editIcon.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="card-body">	
<%Product data; %>
<%   data= (Product) request.getSession().getAttribute("Object"); %>
	<form action="ProductModifier" method="post" enctype="multipart/form-data">
		<p id="para">Please Edit all the Details</p>
		<label>Title</label>
        <input type="text" name="title" value="<%out.println(data.getTitle()); %>" >
		<br>
		<label>Quantity</label>
        <input type="text" name="quantity" value="<%out.println(data.getQuantity()); %>" >
		<br>
		<label>Size</label>
        <input type="text" name="size" value="<%out.println(data.getSize()); %>" >
		<br>
		<label for="image">Image:</label>
        <input type="text" name="imageName" value="<%out.println(data.getImage()); %>"  id="image-name" >
		<button type="button" onclick="document.getElementById('image').click()">Browse</button>
		<input type="file" id="image" name="image" accept="image/*" onchange="updateImageName()" style="display:none">
		<button type="submit" id="submitbutton">Submit</button>
		 <br>  
		 <br>
		 <br>
		 <%@ include file="editIconMessage.jsp" %>
		<br>
	</form>
</div>
<script>
    function updateImageName() {
      const input = document.getElementById("image");
      const imageName = document.getElementById("image-name");
      imageName.value = input.files[0].name;
    }
  </script>

</body>
</html>