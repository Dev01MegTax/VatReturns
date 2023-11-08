/*    */ package sms;
/*    */
/*    */ import java.io.PrintStream;
/*    */ import java.net.URL;
/*    */ import java.net.URLEncoder;
/*    */ import javax.net.ssl.HttpsURLConnection;
/*    */
/*    */ public class SmsCon
/*    */ { 
/*    */   public String sendSMS(String name, String pass, String senderid, String no, String msg, String concat)
/*    */   {
/* 14 */     String res = "";
/* 15 */     String localHost = "https://smsgw.nic.in/sendsms_nic/sendmsg.php";//"https://smsgw.nic.in/sendsms_nic/sendmsg.php";
                 //https://smsgw.nic.in/sendsms_nic/sendmsg.php  //https://164.100.14.9/sendsms_nic/sendmsg.php
/* 16 */     String username = name;
/* 17 */     String password = pass;
/* 18 */     String send = senderid;
/* 19 */     String phoneno = no;
/* 20 */     String message = msg;
	     String concatenation = concat;
/*    */     try
/*    */     {
/* 23 */       message = URLEncoder.encode(message, "UTF-8");
/* 24 */       //System.out.println("message:---" + message);
/* 25 */       String localuri = localHost + "?uname=" + username + "&pass=" + password + "&send=" + send + "&dest=" + phoneno + "&msg=" + message + "&concat=" + concatenation;
/* 26 */       //System.out.println("URI:--" + localuri);
/* 27 */       HttpsURLConnection connection = (HttpsURLConnection)new URL(localuri).openConnection();
/* 28 */       connection.setHostnameVerifier(new CustomizedHostnameVerifier());
/* 29 */       res = connection.getResponseMessage();
/* 30 */       
/* 31 */       int code = connection.getResponseCode();
               System.out.println("Message send:--" + res+"   "+code);
/* 32 */       if (code == 200)
/*    */       {
/* 34 */         connection.disconnect();
/*    */       }
/*    */     }
/*    */     catch (Exception e) {
/* 38 */       System.out.println("Error:--" + e);
/*    */     }
/*    */
/* 41 */     return res;
/*    */   }
/*    */ }