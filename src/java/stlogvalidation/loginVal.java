package stlogvalidation;

import java.util.*;
import java.text.*;
import java.sql.*;
import java.util.regex.*;
import java.net.*;
import java.net.URLEncoder;
import filter.*;
import bean.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class loginVal {

    String username = "";
    String password = "";
    String tin = "";
    String salt = "";
    String tkn = "";
    String answer = "";
    int i = 0;
    int count = i++;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {

        encoder c = new encoder();
        this.username = c.encode(username.trim());
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        encoder c = new encoder();
        this.tin = c.encode(tin.trim());// this.tin = tin.trim();
    }

    public String getTkn() {
        return tkn;
    }

    public void setTkn(String tkn) {
        encoder c = new encoder();
        this.tkn = c.encode(tkn.trim());
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        //encoder c = new encoder();
        this.salt = salt;// this.tin = tin.trim();
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
    public static final Integer ERR_tin_ENTER = new Integer(1);
    public static final Integer ERR_username_db_INVALID = new Integer(2);
    public static final Integer ERR_password_db_INVALID = new Integer(3);
    public static final Integer ERR_count_INVALID = new Integer(4);
    public static final Integer ERR_login_INVALID = new Integer(5);
    public static final Integer ERR_tkn_INVALID = new Integer(6);
    public static final Integer ERR_answer_ENTER = new Integer(7);
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
    SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
    String log_dt = df3.format(new java.util.Date());


    /* Form validation and processing */
    public boolean isValid() {
        System.out.println("");
        //System.out.println("username "+username);
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
        } else if (tin.length() < 11 || tin.length() > 11) {
            errorCodes.put("fail", ERR_tin_ENTER);
        } else if (!tin.matches("[\\d]+")) {
            errorCodes.put("fail", ERR_tin_ENTER);
        }
        /*if (result3) {
         errorCodes.put("fail", ERR_tin_ENTER);
         }*/

        dbBean ep = new dbBean();
        if (tin.matches("[\\d]+") && tin.length() != 0 && username.length() != 0 && password.length() >= 8) {
            /*if (!m22.find() || !m2.find()) {*/
            try {
                System.out.println("---------------LOGIN VAL-------------");
                System.out.println("------------Logdate-----" + log_dt);
                ep.connect();
                int x = ep.updateSQL("UPDATE vat.LOGIN_DEALER  SET ACC_LOG ='YES' WHERE ACC_LOG  ='NO' and log_date<'" + log_dt + "'");
                String sql = "select hash from vat.login_dealer where vat_rc_no='" + tin + "' and usr_cd='" + username + "'";
                System.out.println("sql " + sql);
                ResultSet rs5 = ep.execSQL(sql);

                InetAddress ownIP = InetAddress.getLocalHost();
                String IP = ownIP.getHostAddress();
                SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy;hh:mm:ss");
                String sys1 = df1.format(new java.util.Date());
                SimpleDateFormat df2 = new SimpleDateFormat("dd-MMM-yyyy");
                String s = df2.format(new java.util.Date());
                SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
                String logtime = time.format(new java.util.Date());

                String acc_log = "YES";
                if (rs5.next()) {

                    String sql1 = "select hash from vat.login_dealer where vat_rc_no='" + tin + "' and usr_cd='" + username + "' and  acc_log='YES'";
                    //  String sql1 = "select hash from login_dealer where vat_rc_no='" + tin + "' and usr_cd='" + username + "'";
                    ResultSet rs4 = null;
                    try {

                        rs4 = ep.execSQL(sql1);

                        if (!rs4.isBeforeFirst()) {
                            System.out.println("No data");
                        }

                        System.out.println("sql1 " + sql1);

                    } catch (Exception e) {
                        System.out.println("xx err" + e);
                    }

                    if (rs4.next()) {
                        String hashed = "";
                        int flag = 0;
                        String hash = rs4.getString(1);
                        System.out.println("================tkn is:=============" + tkn);
                        ResultSet hs = ep.execSQL("select salt from vat.salt_return where id='" + tkn + "'");
                        System.out.println("Data r  " + tin + "  " + username + "  " + salt + "  dbpasswd  " + rs4.getString(1) + " passwd  " + password);
                        while (hs.next()) {
                            SimpleSHA1 sha = new SimpleSHA1();
                            String slt = hs.getString(1);
                            hashed = sha.SHA1(slt + hash);
                            //  System.out.println("Printing...3  "+hash+" salt is "+hs.getString(1));
                            if (password.matches(hashed)) { //System.out.println("Printing...4"+hashed);
                                flag = 0;
                                break;
                                //nextval('vat.\"login_seqq\"')
                            } else if (!password.matches(hashed) || !password.equals(hashed)) {
                                ep.updateSQL("insert into vat.login_detail(ID,USERNAME,LOG_DT,IP,LOG_STATUS,LOG_DATE) values(nextval('vat.\"LOGIN_SEQQ\"'),'" + username + "','" + sys1 + "','" + IP + "','Login Failed Passwd','" + sys + "')");
                                //ep.updateSQL("insert into OFFICE_AUDIT_TRAIL(AUDIT_TRAIL_ID,USER_NAME,LOG_DATE,LOG_TIME,LOG_IP,LOG_MESSAGE,LOG_MODULE) values(OFFICE_AUDIT_TRAIL_SEQ.nextval,'" + username + "','" + s + "','" + logtime + "','"+IP+"','Login Failed!','6')");
                                errorCodes.put("fail", ERR_username_db_INVALID);//flag=1;
                            }
                        }
                    } else {
                        ep.updateSQL("insert into vat.login_detail(ID,USERNAME,LOG_DT,IP,LOG_STATUS,LOG_DATE) values(nextval('vat.\"LOGIN_SEQQ\"'),'" + username + "','" + sys1 + "','" + IP + "','Login Failed Locked','" + sys + "')");
                        errorCodes.put("fail", ERR_count_INVALID);//
                    }
                } else {
                    ep.updateSQL("insert into vat.login_detail(ID,USERNAME,LOG_DT,IP,LOG_STATUS,LOG_DATE) values(nextval('vat.\"LOGIN_SEQQ\"')'),'" + username + "','" + sys1 + "','" + IP + "','Login Failed Tin','" + sys + "')");
                    errorCodes.put("fail", ERR_password_db_INVALID);
                }

            }//end try
            catch (Exception e) {
                System.out.println("Error has comeout " + e);
                errorCodes.put("fail", ERR_tkn_INVALID);
            } //   }
            finally {
                try {
                    ep.close();
                } catch (SQLException ex) {
                    Logger.getLogger(loginVal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
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
                ResultSet rs = ep.execSQL("select count(*) from vat.login_dealer where vat_rc_no='" + tin + "'");
                if (rs.next()) {
                    i = rs.getInt(1);
                    if (i < 3) {
                        int k = ep.updateSQL("update vat.login_dealer set count='" + (1 + i) + "',log_date ='" + sys + "' where vat_rc_no='" + tin + "'");
                    } else if (i >= 3) {
                        int km = ep.updateSQL("update vat.login_dealer set count='0',acc_log='NO' where vat_rc_no='" + tin + "' and log_date ='" + sys + "'");
                    }
                }
                //int del = ep.updateSQL("delete from salt_return");
                ep.close();
            } catch (Exception e) {
                System.out.println("error update " + e);
            }
            password = "";
            return false;

        }
        try {
            dbBean ep = new dbBean();
            ep.connect();
            int km = ep.updateSQL("update vat.login_dealer set count='0',acc_log='YES' where vat_rc_no='" + tin + "' and log_date ='" + sys + "'");
            //int del = ep.updateSQL("delete from salt_return");// where id<(select max(id) from salt_return)");
            ep.close();
        } catch (Exception e) {
            System.out.println("error 2" + e);
        }
        username = "";
        password = "";
        tin = "";
        errorCodes.clear();
        return true;
    }
}
