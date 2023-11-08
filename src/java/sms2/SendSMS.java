
package sms2;


public class SendSMS 
{
    public static int send(String number, String message) {
        String mobile = (number.length() == 10) ? "91" + number : number;
        System.out.println("MN: "+mobile+" MSG: "+message);
        int response = 0;
        try {
            Sms ob = new Sms();
            int status = ob.sendSMS(number, message, "1");
            response = 1;
            System.out.println("Mobile Status: " + status);
        } catch (Exception e) {
            System.out.println("Error Sending SMS to " + number + " for Exception: " + e);
        }
        return response;
    }
}