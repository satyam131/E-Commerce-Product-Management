<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Forget</title>
    <link rel="stylesheet" href="otpVerification.css" />
    <!-- <link
      href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap"
      rel="stylesheet"
    /> -->
  </head>
  <body>
    <div class="signup-box">
      <h1>OTP Verification</h1>
      <form action="OtpVerification" method="post">
        <label>Enter OTP</label><br>
        <input type="text"  name="otp" placeholder="Enter OTP" required />
        <input type="Submit" value="Verify" />
      </form>
       <br>
      <%@include file="OtpVerificationMessage.jsp" %>
    </div>
  </body>
</html>

    