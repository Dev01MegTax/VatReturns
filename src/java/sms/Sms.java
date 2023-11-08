package sms;

import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Sms {

    public String sendSMS(String name, String pass, String senderid, String no, String msg, String concat) {
        String res = "";
        //String localHost = "https://164.100.14.9/sendsms_nic/sendmsg.php";
        String localHost = "https://smsgw.nic.in/sendsms_nic/sendmsg.php";
        String username = "meghalayavat.auth";
        String password = "Auth.meghalayavat@123";
        String send = "MEGVAT";
        String phoneno = no;
        String message = msg;
        String concatenation = concat;
        
        try {


        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {

                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };

// Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");            
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            System.out.println("Exception thrown by " + this.getClass() + " Error Message is " + e.getMessage());
        }

        try {
            message = URLEncoder.encode(message, "UTF-8");
            String localuri = localHost + "?uname=" + username + "&pass=" + password + "&send=" + send + "&dest=" + phoneno + "&msg=" + message + "&concat=" + concatenation;
           
            HttpsURLConnection connection = (HttpsURLConnection) new URL(localuri).openConnection();
            connection.setHostnameVerifier(new CustomizedHostnameVerifier());
            res = connection.getResponseMessage();

            int code = connection.getResponseCode();
            if (code == 200) {
                connection.disconnect();
            }
        } catch (Exception e) {
            System.out.println("Exception thrown by " + this.getClass() + " Error Message is " + e.getMessage());
        }

        return res;
    }

    public String sendSMS(String name, String pass, String senderid, String no, String msg, String concat, String dummyParameter) {
        String res = "";
       //String localHost = "https://164.100.14.9/sendsms_nic/sendmsg.php";
        String localHost = "https://smsgw.nic.in/sendsms_nic/sendmsg.php";
        String username = "meghalayavat.auth";
        String password = "Auth.meghalayavat@123";
        String send = "MEGVAT";
        String phoneno = no;
        String message = msg;
        String concatenation = concat;

        try {
            message = URLEncoder.encode(message, "UTF-8");
            String localuri = localHost + "?uname=" + username + "&pass=" + password + "&send=" + send + "&dest=" + phoneno + "&msg=" + message + "&concat=" + concatenation;

            HttpsURLConnection connection = (HttpsURLConnection) new URL(localuri).openConnection();
            connection.setHostnameVerifier(new CustomizedHostnameVerifier());
            res = connection.getResponseMessage();

            int code = connection.getResponseCode();
            if (code == 200) {
                connection.disconnect();
            }
        } catch (Exception e) {
            System.out.println("Exception thrown by " + this.getClass() + " Error Message is " + e.getMessage());
        }

        return res;
    }
}
