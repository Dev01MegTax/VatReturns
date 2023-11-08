package stlogvalidation;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.util.regex.*;
import java.net.*;
import java.net.URLEncoder;
import filter.*;
import java.io.*;
import bean.*;
public class loginValc {


String username = "";
    String password = "";
    String tin = "";
    int i = 0;
    String salt="";
    String tkn="";
    String answer="";
    int count = i++;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        // this.username = username.trim();
        encoder c = new encoder();
        this.username = c.encode(username.trim());
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        //encoder c = new encoder();
        this.salt = salt;// this.tin = tin.trim();
    }
    public String getTkn() {
        return tkn;
    }
    public void setTkn(String tkn) {
        encoder c = new encoder();
        this.tkn = tkn.trim();
    }
    public String getTin() {
        return tin;
    }
    public void setTin(String tin) {
        encoder c = new encoder();
        this.tin = tin.trim();// this.tin = tin.trim();
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        encoder c = new encoder();
        this.password = password.trim();
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        encoder c = new encoder();
        this.answer = c.encode(answer.trim());
    }
    String ipaddr;

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public static final Integer ERR_tin_ENTER = new Integer(1);
    public static final Integer ERR_username_db_INVALID = new Integer(2);
    public static final Integer ERR_password_db_INVALID = new Integer(3);
    public static final Integer ERR_count_INVALID = new Integer(4);
    public static final Integer ERR_login_INVALID = new Integer(5);
    public static final Integer ERR_tkn_INVALID = new Integer(6);
    public static final Integer ERR_answer_ENTER = new Integer(7);
    //public static final Integer ERR_login_INVALID = new Integer(5);
    Map errorCodes = new HashMap();
    Map msgMap;

    public void setErrorMessages(Map msgMap) {
        this.msgMap = msgMap;
    }
    public String getErrorMessage(String propName) {
        Integer code = (Integer) (errorCodes.get(propName));
        if (code == null) {
            return "";
        } else if (msgMap != null) {
            String msg = (String) msgMap.get(code);
            if (msg != null) {
                return msg;
            }
        }
        return "Error";
    }
    SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
    String sys = df1.format(new java.util.Date());//System date
    SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy;hh:mm:ss");
    String log_dt = df2.format(new java.util.Date());
   
  


    public boolean isValid() {
        System.out.println("ip" +ipaddr);
        // Clear all errors
        errorCodes.clear();
        Pattern pattern = Pattern.compile("[\\'\\+\\=\\-\\><|<script>]+");
        Pattern pattern1 = Pattern.compile("^\\'><script>|^\\s|\\'><|'|'><img");
        // Validate username
        Matcher m = pattern.matcher(username);
        Matcher m2 = pattern1.matcher(username);
        boolean result2 = m.find();
        boolean result22 = m2.find();
        if (username.length() == 0) {
            errorCodes.put("fail", ERR_tin_ENTER);
        }
        if(tkn.length()==0)
        {
            System.out.println("token value  " + tkn);
            errorCodes.put("fail", ERR_tkn_INVALID);
        }
           if (answer.length() == 0) {
                errorCodes.put("answer", ERR_answer_ENTER);
            }
        Matcher m1 = pattern.matcher(password);
        Matcher m22 = pattern1.matcher(password);
        boolean result1 = m1.find();
        if (password.length() == 0) {
            errorCodes.put("fail", ERR_tin_ENTER);
        } else if (password.length() < 8) {
            errorCodes.put("fail", ERR_tin_ENTER);
        }
        Matcher m3 = pattern.matcher(tin);
        boolean result3 = m3.find();
        if (tin.length() == 0) {
            errorCodes.put("fail", ERR_tin_ENTER);
        }        
        dbBean ep = new dbBean();
        if (tin.matches("[\\d]+") && tin.length() != 0 && username.length() != 0 && password.length() >= 8) {
            /*if (!m22.find() || !m2.find()) {*/
                try {
                    ep.connect();
                    System.out.println("Data r1  "+tin+"  "+username+"  "+salt+"    " + "token "+tkn);
                    String sql="UPDATE vat.reg_online_login  SET ACC_LOG='YES' WHERE  ACC_LOG='NO' and  log_dt<'" + sys + "'";
                    System.out.println("update "+sql);
                    int x = ep.updateSQL(sql);
                    sql="select password from vat.reg_online_login where circle_cd='" + tin + "' and user_cd='" + username + "'";
                    System.out.println("update "+sql);
                    ResultSet rs5 = ep.execSQL(sql);
                 
                    InetAddress ownIP = InetAddress.getLocalHost();
                String IP = ownIP.getHostAddress();
                    SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy;hh:mm:ss");
                    String sys1 = df1.format(new java.util.Date());
                    if (rs5.next()) {
                        sql="select password from vat.reg_online_login where circle_cd='" + tin + "' and user_cd='" + username + "' and acc_log='YES'";
                        System.out.println("update "+sql);
                    ResultSet rs4 = ep.execSQL(sql);
                    System.out.println("hello 1");
                    if (rs4.next()) {
                        String hashed = "";
                        int flag = 0;
                        String hash = rs4.getString(1);
                        //String a="select salt from salt_return where id='"+tkn+"'";
                        ResultSet hs = ep.execSQL("select salt from vat.salt_return where id='"+tkn+"'");
                        System.out.println("Data r  "+tin+"  "+username+"  "+salt+"  dbpasswd  "+rs4.getString(1) +" passwd  " +  password + "token "+tkn);
                            while (hs.next())
                            {
                                String slt=hs.getString(1);
                                SimpleSHA1 sha = new SimpleSHA1();
                                    //hashed = sha.SHA1(hs.getString(1) + hash);
                                    hashed = sha.SHA1(slt + hash);
                                    System.out.println("Printing...3  "+hashed+" salt is "+hs.getString(1));
                                    if (password.matches(hashed) && password.equals(hashed)) {
                                    System.out.println("Printing...4"+hashed);
                                    flag = 0;
                                    
                                    break;
                                    } else if (!password.matches(hashed) || !password.equals(hashed)) {
                                        ep.updateSQL("insert into vat.login_detail(ID,USERNAME,LOG_DT,IP,LOG_STATUS,LOG_DATE) values(nextval('vat.\"LOGIN_SEQQ\"'),'" + username + "','" + sys1 + "','" + IP + "','Login Failed Passwd','" + sys + "')");
                                    errorCodes.put("fail", ERR_username_db_INVALID);//flag=1;
                                    }
                            }
                        } else if (!rs4.next()) {
                            ep.updateSQL("insert into vat.login_detail(ID,USERNAME,LOG_DT,IP,LOG_STATUS,LOG_DATE) values(nextval('vat.\"LOGIN_SEQQ\"'),'" + username + "','" + sys1 + "','" + IP + "','Login Failed Locked','" + sys + "')");
                            errorCodes.put("fail", ERR_count_INVALID);
                        }
                        } else {
                        ep.updateSQL("insert into vat.login_detail(ID,USERNAME,LOG_DT,IP,LOG_STATUS,LOG_DATE) values(nextval('vat.\"LOGIN_SEQQ\"'),'" + username + "','" + sys1 + "','" + IP + "','Login Failed Passwd','" + sys + "')");
                            errorCodes.put("fail", ERR_password_db_INVALID);
                        }
                    
                    
                }//end try
                catch (Exception e) {
                   System.out.println("Error has comeout "+e);
                    errorCodes.put("fail", ERR_tkn_INVALID);
                }
         //   }
        }
        return errorCodes.size() == 0;
    }

    public boolean process() {
        if (!isValid()) {
            try {               
                dbBean ep = new dbBean();
                ep.connect();
                InetAddress ownIP = InetAddress.getLocalHost();
                String IP = ownIP.getHostAddress();

                System.out.println("username:"+username);

                ResultSet rs = ep.execSQL("select count from vat.reg_online_login where user_cd='" + username + "'");
                if (rs.next()) {
                    i = rs.getInt(1);
                    if (i < 3) {
                        int k = ep.updateSQL("update vat.reg_online_login set count='" + (1 + i) + "',log_dt ='" + sys + "' where user_cd='" + username + "'");
                    } else if (i >= 3) {
                        int km = ep.updateSQL("update vat.reg_online_login set count='0',acc_log='NO' where user_cd='" + username + "' and log_dt ='" + sys + "'");
                    }
                }
                //int del = ep.updateSQL("delete from salt_return");
                ep.close();
            } catch (Exception e) {
            }
            password = "";
            return false;
        }     
        username = "";
        password = "";
        tin = "";
        errorCodes.clear();
        return true;
    }  
}
