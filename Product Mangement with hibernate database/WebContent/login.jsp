<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=<device-width>, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="login.css" type="text/css"> 
    
</head>
<body>
    <div class="form"  style="background-color: lavender" >
        <div id="header" style="background-color:lightblue;">
            <h3 id="heading">Login Here </h3>
        </div>
        <br>
        <form class="text-center" action="LoginHandler" method="post">
            <label id="form1">UserName</label>
            <input type="text"  name="username" placeholder="EnterUserName" required>
            <br><br>
            <label id="form2">Password</label>
            <input type="password"name="password" placeholder="EnterPassword" required>
            <br><br>
            <a id="forget-pass" href="forgotPassword.jsp">Forgot Password?</a>
            <br>
            <br>
               <div class="loginbutton" style="background-color: lightblue;">
            <input id="centrebuttoon" type="submit"  value=" Login>>">
        </div>
        </form>
        <div id="signup-f" style="background-color: white;">
            <br>
            <a id="signup" href="signup.jsp">Create a New Account?</a>
        </div>
    </div>
    <br>
    <div class="form"  style="background-color: lavender" >
     <div class="loginbutton" style="background-color: lightblue;">
     <%@ include file="passwordUpdaterMessage.jsp" %>
     </div>
     </div>
</body>
</html>