package sms2;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
public class Sms {
    //The following parameters can be kept in an external properties file
//    String localHost = "https://164.100.14.9/sendsms_nic/sendmsg.php";
//    String username = "meghcaste.auth";
//    String password = "Auth.meghcaste@123";
//    String sender = "MEGHOL";
    
    
    //String localHost = "https://smsgw71.nic.in/sendsms_nic/sendmsg.php";
    //String localHost = "http://164.100.14.211/failsafe/HttpLink";
    String localHost = "https://164.100.14.211/failsafe/HttpLink";
    String username = "meghalayavat.auth";
    String password = "Auth.meghalayavat@123";
    String sender = "MEGVAT";
   

    public int sendSMS(String mobileno, String message, String concat) {
        String res = "";
        int code = 0;
       
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
            };
            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            System.out.println("Exception thrown by " + this.getClass() + " Error Message is " + e.getMessage());
        }
         
        try {
            message = URLEncoder.encode(message, "UTF-8");
             String localuri =localHost+ "?username="+ username + "&pin=" + password + "&message=" + message + "&mnumber=" + mobileno + "&signature=" + sender;
             System.out.println(localuri);
            //String localuri = localHost + "?uname=" + username + "&pass=" + password + "&send=" + sender + "&dest=" + mobileno + "&msg=" + message + "&concat=" + concat;
//            HttpsURLConnection connection = (HttpsURLConnection) new URL(localuri).openConnection();
//            
//           
//            connection.setHostnameVerifier(new CustomizedHostnameVerifier());
//            res = connection.getResponseMessage();
             
    // HttpURLConnection connection = (HttpURLConnection)new URL(localuri).openConnection();        
             
            HttpsURLConnection connection = (HttpsURLConnection)new URL(localuri).openConnection();
           
            connection.setHostnameVerifier(new CustomizedHostnameVerifier());
            
             
             
            code = connection.getResponseCode();
            System.out.println(new java.util.Date() + " " + this.getClass() + " Response Code " + code);
            if (code == 200) {
                connection.disconnect();
            }
        } catch (Exception e) {
            System.out.println(new java.util.Date() + " Exception thrown by " + this.getClass() + " Error Message is " + e.getMessage());
        }
        return code;
    }
}

