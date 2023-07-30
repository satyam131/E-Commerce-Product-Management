<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.nagarro.dao.ProductData"%>
    <%@ page import = "java.util.*"%>
    <%@ page import = "com.nagarro.model.Product"%>
    <%@ page import = "com.nagarro.model.EmailContainer"%>
<html>
<head>
<title>Product Management Tool</title>
<link rel="stylesheet" href="product.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="text-centre" id="header">
		<h1 id="heading">Product Management Tool</h1>
		<form action="ProductOperation" method="post">
        <input type="Submit" id="lbotton" value="Logout>> "/>
        <input type="hidden" name="operation" value="logout">
        </form>
		<p id="username"><%= EmailContainer.getEmail() %></p>
	</div>
	<%List<Product> allProducts=ProductData.getProductList(EmailContainer.getEmail()); %>
<div class="card-body">	
	<form action="ProductOperation" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="operation" value="prodAdder">
		<p id="para">Please Enter Product Details to Add to ths stock</p>
		<label>Title</label>
        <input type="text" name="title" required>
		<br>
		<label>Quantity</label>
        <input type="text" name="quantity" required>
		<br>
		<label>Size</label>
        <input type="text" name="size" required>
		<br>
		<label for="image">Image:</label>
        <input type="text" id="image-name" name="image" readonly>
        <button type="button" onclick="document.getElementById('image').click()">Browse</button>
       <input type="file" id="image" name="uploadedImage" accept="image/*" onchange="updateImageName()" style="display:none" required>
	   <button type="Submit" id="submitbutton">Submit</button>
		<br>
	</form>
</div>
<div id="container" >
		<div id="content" >
	      <table id="table" >
			<div id="row">
				<tr>
					<th id="sno">S No.</th>
					<th id="title">Title</th>
					<th id="Quantity">Quantity</th>
					<th id="size">Size</th>
					<th id="image">Image</th>
					<th id="Action">Action</th>
				</tr>
			</div>
			<%for(int i=0; i<allProducts.size(); i++) {
			 Product data=allProducts.get(i);%>
			<div id="inside">
				<tr style="height: 280px;">
					<td id="sdata"><%=(i+1)%></td>
					<td id="sdata"><%=data.getTitle() %></td>
					<td id="sdata"><%=data.getQuantity() %></td>
					<td id="sdata"><%=data.getSize() %></td>
					<td id="sdata"> <img src="img/<%=data.getImage()%>" id="image" alt="errr"></td>
					<td>
						 <a href="EditIconHandler?id=<%=data.getId()%>" id="edit"><i class="fa fa-pencil"></i></a>
						<a href="DeleteIconHandler?id=<%=data.getId()%>" id="delete"><i class="fa fa-trash"></i></a>
					</td>
				</tr>	
		    </div>
		    <%} %>			
		 </table>
	    </div>
</div>
<script>
    function updateImageName() {
      const input = document.getElementById("image");
      const imageName = document.getElementById("image-name");
      imageName.value = input.files[0].name;
    }
  </script>
 <script type="text/javascript">

        function openPage(pageURL) {
            window.location = pageURL;
        }

    </script>
</body>
</html>