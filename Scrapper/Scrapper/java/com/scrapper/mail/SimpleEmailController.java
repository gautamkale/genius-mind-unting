package com.scrapper.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class SimpleEmailController {

  

 @Autowired

 private JavaMailSender sender;



 @RequestMapping("/simpleemail")

 @ResponseBody

 public String home() {

     try {

         sendEmail();

         return "Email Sent!";

     }catch(Exception ex) { 

         System.out.println("Error in sending email: "+ex);

     }
	return "";

 }



 private void sendEmail() throws Exception{

     MimeMessage message = sender.createMimeMessage();

     MimeMessageHelper helper = new MimeMessageHelper(message);

      

     helper.setTo("gautam16kale@gmail.com");

     helper.setText("How are you?");

     helper.setSubject("Hi");

      

     sender.send(message);

 }

}

