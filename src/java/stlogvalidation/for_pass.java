package stlogvalidation;

import java.util.*;
import java.text.*;
import java.util.regex.*;
import java.sql.*;
import bean.*;

public class for_pass {
    /*  The properties */

    String vat_rc_no = "";

     String pan_no="";
    String username = "";
    String password = "";
    String password2 = "";
    String dob = "";
    String answer = "";
    int count = 0, count1 = 0, count2 = 0, count3 = 0;

    public String getVat_rc_no() {
        return vat_rc_no;
    }

    public void setVat_rc_no(String vat_rc_no) {
        this.vat_rc_no = vat_rc_no.trim();
        encoder c = new encoder();
        this.vat_rc_no = c.encode(vat_rc_no.trim());//this.vat_rc_no= vat_rc_no.trim();
    }

     public String getPan_no() {
            return pan_no;
        }
        public void setPan_no(String pan_no) {
          this.pan_no = pan_no.trim();
          encoder c = new encoder();
          this.pan_no = c.encode(pan_no.trim());
        }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        encoder c = new encoder();
        this.username = c.encode(username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        encoder c = new encoder();
        this.password = c.encode(password);
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        encoder c = new encoder();
        this.password2 = c.encode(password2);
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob.trim();
        dateenc c = new dateenc();
        this.dob = c.encode(dob.trim());
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer.trim();
    }
    /* Errors */
    public static final Integer ERR_vat_rc_no_ENTER = new Integer(1);
    public static final Integer ERR_vat_rc_no_INVALID = new Integer(2);
    public static final Integer ERR_vat_rc_no_NUM_ONLY = new Integer(3);
    
    public static final Integer ERR_username_ENTER = new Integer(7);
    public static final Integer ERR_username_INVALID = new Integer(8);
    public static final Integer ERR_password_ENTER = new Integer(9);
    public static final Integer ERR_password_INVALID = new Integer(10);
    public static final Integer ERR_password2_ENTER = new Integer(11);
    public static final Integer ERR_password2_INVALID = new Integer(12);
    public static final Integer ERR_dob_ENTER = new Integer(13);
    public static final Integer ERR_dob_INVALID = new Integer(14);
    public static final Integer ERR_pass_INVALID = new Integer(15);
    public static final Integer ERR_password_length_ENTER = new Integer(16);
    public static final Integer ERR_vat_rc_no_db_ONLY = new Integer(17);
    public static final Integer ERR_vat_rc_no_db1_ONLY = new Integer(18);
    public static final Integer ERR_username_db_ONLY = new Integer(19);
    public static final Integer ERR_dob_db_ONLY = new Integer(20);
    public static final Integer ERR_dob_FORMAT = new Integer(21);
    public static final Integer ERR_dob1_FORMAT = new Integer(22);
    public static final Integer ERR_pan_no_db_ONLY = new Integer(23);
    public static final Integer ERR_week_FORMAT = new Integer(24);
    public static final Integer ERR_answer_ENTER = new Integer(25);
     public static final Integer ERR_pan_no_ENTER = new Integer(31);
    // Holds error messages for the properties
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
    /* Form validation and processing */
    public boolean isValid() {
        // Clear all errors
        errorCodes.clear();
        dbBean ep = new dbBean();
        Pattern pattern = Pattern.compile("[\\'\\+\\=-><]+");
        // Validate email
        if (vat_rc_no.length() == 0) {
            errorCodes.put("vat_rc_no", ERR_vat_rc_no_ENTER);
        } else if (!vat_rc_no.matches("[\\d]+")) {
            errorCodes.put("vat_rc_no", ERR_vat_rc_no_INVALID);
        } else if (vat_rc_no.length() < 11 || vat_rc_no.length() > 11) {
            errorCodes.put("vat_rc_no", ERR_vat_rc_no_NUM_ONLY);
        }

        if (pan_no.length() == 0) {
                errorCodes.put("pan_no", ERR_pan_no_ENTER);
            }
        Matcher m = pattern.matcher(username);
        boolean result2 = m.find();
        if (username.length() == 0) {
            errorCodes.put("username", ERR_username_ENTER);
        } else if (username.length() > 15) {
            errorCodes.put("username", ERR_username_ENTER);
        }

        int len = password.length();
        for (int i = 0; i < len; i++) {
            int ch = password.charAt(i);
            if ('a' <= ch && ch <= 'z') {		// 'A'..'Z'
                count++;
            }
            if ('a' <= ch && ch <= 'z') {	// 'a'..'z'
                count1++;
            }
            if ('0' <= ch && ch <= '9') {	// '0'..'9'
                count2++;
            }

        }
        if (password.equals("0")) //    if(count<=0 || count1<=0 || count2<=0 || count3<=0)
        {
            errorCodes.put("password", ERR_week_FORMAT);
        }
        Matcher m1 = pattern.matcher(password);
        boolean result1 = m1.find();
        if (password.length() == 0) {
            errorCodes.put("password", ERR_password_ENTER);
        } else if (password.length() < 8) {
            errorCodes.put("password", ERR_password_length_ENTER);
        }
        Matcher m2 = pattern.matcher(password2);
        boolean result = m2.find();
        if (password2.length() == 0) {
            errorCodes.put("password2", ERR_password2_ENTER);
        } else if (password2.length() < 8) {
            errorCodes.put("password2", ERR_password_length_ENTER);
        } else if (password2.matches("[><+=']+")) {
            errorCodes.put("password2", ERR_password2_INVALID);
        }
        if (dob.length() == 0) {
            errorCodes.put("dob", ERR_dob_ENTER);
        } else if (dob.length() < 11 || dob.length() < 11) {
            errorCodes.put("dob", ERR_dob_INVALID);
        } else if (!dob.matches("[-\\w]+")) {
            errorCodes.put("dob", ERR_dob_INVALID);
        }
        if (!password.equals(password2)) {
            errorCodes.put("password", ERR_pass_INVALID);
            errorCodes.put("password2", ERR_pass_INVALID);
        }
        if (answer.length() == 0) {
            errorCodes.put("answer", ERR_answer_ENTER);
        }
        try {
            ep.connect();
            ResultSet rs = ep.execSQL("select vat_rc_no from vat.login_dealer where vat_rc_no='" + vat_rc_no + "' and usr_cd='" + username + "' and dob='" + dob + "'");
            ResultSet rs4 = ep.execSQL("select vat_rc_no from vat.regd_dealer where vat_rc_no='" + vat_rc_no + "' and pan_no='" + pan_no + "' and appl_for='VC'");


            if (!rs.next()) {
                errorCodes.put("vat_rc_no", ERR_vat_rc_no_db_ONLY);
                errorCodes.put("username", ERR_username_db_ONLY);
                errorCodes.put("dob", ERR_dob_db_ONLY);

            } else if (!rs4.next()) {
                errorCodes.put("vat_rc_no", ERR_vat_rc_no_db_ONLY);
                errorCodes.put("cst_rc_no", ERR_pan_no_db_ONLY);
            }
            ep.close();
        } catch (Exception e) {
        }

        // validating format of the date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        java.util.Date testDate = null;
        // we will now try to parse the string into date form
        if (dob.length() != 0) {
            try {
                testDate = sdf.parse(dob);
                String sys = sdf.format(new java.util.Date());//System date
                DateFormat df8 = new SimpleDateFormat("dd-MMM-yyyy");
                java.util.Date dt8 = df8.parse(dob);//Issue date converted into date type
                DateFormat df6 = new SimpleDateFormat("dd-MMM-yyyy");
                java.util.Date dt5 = df6.parse(sys);//system date converted into date type
//This function calculate the total number of days between two dates.
                int dat = (int) ((dt5.getTime() - dt8.getTime()) / (1000 * 60 * 60 * 24));
                if (dat < 0) {
                    errorCodes.put("dob", ERR_dob1_FORMAT);
                }
            } catch (ParseException e) {
                errorCodes.put("dob", ERR_dob_FORMAT);
            }
        }
        // If no errors, form is valid
        return errorCodes.size() == 0;
    }

    public boolean process() {
        if (!isValid()) {
            password = "";
            password2 = "";
            return false;
        }
        password = "";
        password2 = "";
        answer = "";
        errorCodes.clear();
        return true;
    }
}
