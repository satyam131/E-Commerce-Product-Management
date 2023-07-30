<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Sign Up Page</title>
    <link rel="stylesheet" href="signup.css" />
  </head>
  <body>
    <div class="signup-box">
      <h1>Sign Up</h1>
      <form action="SignupHandler" method="post">
        <label>Full Name</label>
        <input type="text" name="username" placeholder=""  required />
        <label>Email</label>
        <input type="email" name="useremail" placeholder="" required/>
        <label>Password</label>
        <input type="password" name="userpassword" placeholder="" required/>
        <label>Confirm Password</label>
        <input type="password" name="confirmpassword" placeholder="" required/>
        <input type="submit" value="Register" />
      </form>
      <br>
      <%@include file="signupMessage.jsp" %>
    </div>
    <p class="para-2">
      Already have an account? <a href="login.jsp">Login here</a>
    </p>
  </body>
</html>

