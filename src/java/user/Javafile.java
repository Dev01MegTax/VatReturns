package user;
import java.util.*;
import java.security.MessageDigest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
public class Javafile {

   public static String toHex(String message) {
      try {
         MessageDigest md = MessageDigest.getInstance("MD5");
         byte array[] = md.digest(message.getBytes("CP1252"));
         StringBuffer sb = new StringBuffer();
         for (int i = 0; i< array.length; ++i) {
           sb.append(Integer.toHexString((array[i]& 0xFF)|0x100).substring(1, 3));

         }
         return sb.toString();
      } catch (NoSuchAlgorithmException e) {
      } catch (UnsupportedEncodingException e) {
      }
      return null;
   }
  
  public static void main(String[] args){
    Random rand = new Random();
 int num = rand.nextInt(17061040);
      System.out.println("Generated Random Number between 0 to 10 is : " + num);
   int numNoRange = rand.nextInt();
       System.out.println("Generated Random Number without specifying any range is : " + numNoRange);
  } 
   String vat_rc_no;
   String cst_rc_no;
   public void setVat_rc_no( String value )
    {
        vat_rc_no = value;
    }
   public void setCst_rc_no( String value )
    {
        cst_rc_no = value;
    }
    public String getVat_rc_no() { return vat_rc_no; } 
    public String getCst_rc_no() { return cst_rc_no; }
    String username;
    String password;
    String cd;
    String ch;
    String td;
    String date1;
    String date2;
    String nos;  
    String charge_cd;
   
public void setTd( String value )
    {
        td = value;
    }
public void setNos( String value )
    {
        nos = value;
    }
public void setCharge_cd( String value )
    {
        charge_cd = value;
    }
public void setDate1( String value )
    {
        date1 = value;
    }
public void setDate2( String value )
    {
        date2 = value;
    }
    public void setUsername( String value )
    {
        username = value;
    }
 public void setPassword( String value )
    {
        password = value;
    }
   public void setCd( String value )
    {
        cd = value;
    }
public void setCh( String value )
    {
        ch = value;
    }
public String getTd() { return td; }
public String getDate1() { return date1; }
public String getDate2() { return date2; }
    public String getUsername() { return username; }
public String getCd() { return cd; }
public String getCh() { return ch; }
public String getNos() { return nos; }
public String getCharge_cd() { return charge_cd; }
   public String getPassword() { return password; }
    String password2;
    String dob; 
    

  public void setPassword2( String value )
    {
        password2 = value;
    }
  public void setDob( String value )
    {
        dob = value;
    }
   public String getPassword2() { return password2; }
   public String getDob() { return dob; }
   
     
}
