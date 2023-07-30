package com.nagarro.model;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMail
{ 
	private static String code=null;
	private static String userEmail=null;
    public static void send(String to, String sub, 
                         String msg, final String user,final String pass)
    { 
    	code=msg;
    	userEmail=to;
        //create an instance of Properties Class   
        Properties props = new Properties();
        System.out.println("Password"+pass);
        System.out.println("Username"+user);
        /*  Specifies the IP address of your default mail server
     	      for e.g if you are using gmail server as an email sever
            you will pass smtp.gmail.com as value of mail.smtp host. 
            As shown here in the code. 
            Change accordingly, if your email id is not a gmail id
        */
        props.put("mail.smtp.host", "smtp.gmail.com");  //simple mail transfer protocol
        // below mentioned mail.smtp.port is optional 
        props.put("mail.smtp.port", "587");		
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
     
        /* Pass Properties object(props) and Authenticator object   
           for authentication to Session instance 
        */

        Session session = Session.getInstance(props,new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
  	 	         return new PasswordAuthentication(user,pass); 
            }
        });
        session.setDebug(true);

        try {
 
     	      /*  Create an instance of MimeMessage, 
     	          it accept MIME types and headers 
     	      */
     
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);

            /* Transport class is used to deliver the message to the recipients */
           
            Transport.send(message);
        }
        catch(Exception e) {
    	     e.printStackTrace();
        }
    } 
    public static String getOtp()
    {
    	return code;
    }
    public static String getEmail()
    {
    	return userEmail;
    }
}
