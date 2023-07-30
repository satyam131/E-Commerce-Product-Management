<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Forgot Password</title>
    <link rel="stylesheet" href="forgotPassword.css" />
    <!-- <link
      href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap"
      rel="stylesheet"
    /> -->
  </head>
  <body>
    <div class="signup-box">
      <h1>Forgot Password?</h1>
      <form action="PasswordHandler" method="post">
        <label>Enter Register Email</label><br>
        <input type="email" name="userNameOrEmail" placeholder="Enter username or email" required />
        <input type="Submit" value="SendOTP" />
      </form>
      <br>
      <%@include file="forgotPasswordMessage.jsp" %>
    </div>
  </body>
</html>
