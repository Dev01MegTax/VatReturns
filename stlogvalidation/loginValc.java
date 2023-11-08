package stlogvalidation;

import java.util.*;
import java.text.*;
import java.sql.*;
import java.util.regex.*;
import java.net.*;
import java.net.URLEncoder;
import filter.*;
import bean.*;

public class loginValc {

    String username = "";
    String password = "";
    String tin = "";
    int i = 0;
    int count = i++;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        // this.username = username.trim();
        encoder c = new encoder();
        this.username = c.encode(username.trim());
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
    public static final Integer ERR_tin_ENTER = new Integer(1);
    public static final Integer ERR_username_db_INVALID = new Integer(2);
    public static final Integer ERR_password_db_INVALID = new Integer(3);
    public static final Integer ERR_count_INVALID = new Integer(4);
    public static final Integer ERR_login_INVALID = new Integer(5);
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
    /* Form validation and processing */

    public boolean isValid() {
        System.out.println("username "+username);
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
       /* else if (username.matches("[>%<+=']+")) {
            errorCodes.put("fail", ERR_tin_ENTER);
        }*/
        // Validate password
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
        /*if (result3) {
        errorCodes.put("fail", ERR_tin_ENTER);
        }*/

        dbBean ep = new dbBean();
        if (tin.matches("[\\d]+") && tin.length() != 0 && username.length() != 0 && password.length() >= 8) {
            /*if (!m22.find() || !m2.find()) {*/
                try {
                    ep.connect();
                    int x = ep.updateSQL("UPDATE LOGIN_DEALER  SET LOGIN_DEALER.ACC_LOG ='YES' WHERE LOGIN_DEALER.ACC_LOG  ='NO' and login_dealer.log_date<'" + sys + "'");
                    ResultSet rs5 = ep.execSQL("select password from reg_online_login where circle_Cd='" + tin + "' and user_cd='" + username + "'");
                    if (rs5.next()) {

                        ResultSet rs4 = ep.execSQL("select password from reg_online_login where circle_Cd='" + tin + "' and user_cd='" + username + "'");// and  acc_log='YES'");
                        if (rs4.next()) {
                            String hashed = "";
                            int flag = 0;
                            String hash = rs4.getString(1);
                            ResultSet hs = ep.execSQL("select salt from salt_return where id=(select max(id) from salt_return)");
                            while (hs.next()) {
                                SimpleSHA1 sha = new SimpleSHA1();
                                hashed = sha.SHA1(hs.getString(1) + hash);
                                System.out.println("Printing...3  "+hash+" salt is "+hs.getString(1));
                                if (password.matches(hashed)) { System.out.println("Printing...4"+hashed);
                                    flag = 0;
                                    break;
                                } else if (!password.matches(hashed) || !password.equals(hashed)) {
                                    errorCodes.put("fail", ERR_username_db_INVALID);//flag=1;
                                }
                            }
                        } else if (!rs4.next()) {
                            errorCodes.put("fail", ERR_count_INVALID);//
                        }
                    } else {
                        errorCodes.put("fail", ERR_password_db_INVALID);
                    }

                }//end try
                catch (Exception e) {
                    System.out.println("Error has comeout "+e);
                    errorCodes.put("fail", ERR_password_db_INVALID);
                }
         //   }
        }
        return errorCodes.size() == 0;
    }

    public boolean process() {
        if (!isValid()) {

            /*try {

            dbBean ep = new dbBean();
            ep.connect();
            InetAddress ownIP = InetAddress.getLocalHost();
            String IP = ownIP.getHostAddress();
            ResultSet rs = ep.execSQL("select count from login_dealer where vat_rc_no='" + tin + "'");
            if (rs.next()) {
            i = rs.getInt(1);
            if (i < 3) {
            int k = ep.updateSQL("update login_dealer set count='" + (1 + i) + "',log_date ='" + sys + "' where vat_rc_no='" + tin + "'");
            } else if (i >= 3) {
            int km = ep.updateSQL("update login_dealer set count='0',acc_log='NO' where vat_rc_no='" + tin + "' and log_date ='" + sys + "'");
            }
            }
            ep.close();
            } catch (Exception e) {
            }*/
            password = "";
            return false;

        }
        /*try {
        dbBean ep = new dbBean();
        ep.connect();
        int km = ep.updateSQL("update login_dealer set count='0',acc_log='YES' where vat_rc_no='" + tin + "' and log_date ='" + sys + "'");
        int del = ep.updateSQL("delete from salt_return where id<(select max(id) from salt_return)");
        ep.close();
        } catch (Exception e) {
        }*/
        username = "";
        password = "";
        tin = "";
        errorCodes.clear();
        return true;
    }
}
