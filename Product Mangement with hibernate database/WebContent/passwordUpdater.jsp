<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Change Password</title>
    <link rel="stylesheet" href="passwordUpdater.css" />
    <!-- <link
      href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap"
      rel="stylesheet"
    /> -->
  </head>
  <body>
    <div class="signup-box">
      <h1>Change Password</h1>
      <form action="PasswordChanger" method="post">
        <label>Enter New Password</label><br>
        <input type="password" name="newpassword" placeholder="" required />
        <label>Confirm Password</label><br>
        <input type="password" name="confirmnewpassword" placeholder="" required />
        <input type="Submit" value="Save Password" />
      </form>
      <br>
      <%@ include file="passwordUpdaterMessage.jsp" %>
    </div>
  </body>
</html>

