package stlogvalidation;

import java.util.*;
import java.sql.*;
import java.text.*;
import java.util.regex.*;
import bean.*;
import java.text.ParseException;

public class online {
    /*  The properties */

    String vat_rc_no = "";
    String cst_rc_no = "";
    String pan_no = "";
    String username = "";
    String password = "";
    String password2 = "";
    String dob = "";
    String answer = "";
    String email = "";
    String mobile = "";
    int count = 0, count1 = 0, count2 = 0, count3 = 0, count4 = 0;

    public String getVat_rc_no() {
        return vat_rc_no;
    }

    public void setVat_rc_no(String vat_rc_no) {
        //encoder c = new encoder();
        //this.vat_rc_no = c.encode(vat_rc_no);//
        this.vat_rc_no = vat_rc_no.trim();
        encoder c = new encoder();
        this.vat_rc_no = c.encode(vat_rc_no.trim());
    }

    public String getCst_rc_no() {
        return cst_rc_no;
    }

    public void setCst_rc_no(String cst_rc_no) {
        //encoder c = new encoder();
        //this.cst_rc_no = c.encode(cst_rc_no);//
        this.cst_rc_no = cst_rc_no.trim();
        encoder c = new encoder();
        this.cst_rc_no = c.encode(cst_rc_no.trim());
    }

    public String getPan_no() {
        return pan_no;
    }

    public void setPan_no(String pan_no) {
        //encoder c = new encoder();
        //this.cst_rc_no = c.encode(cst_rc_no);//
        this.pan_no = pan_no.trim();
        encoder c = new encoder();
        this.pan_no = c.encode(pan_no.trim());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        encoder c = new encoder();
        this.username = c.encode(username);//this.username = username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        encoder c = new encoder();
        this.password = c.encode(password);//this.password = password.trim();
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        encoder c = new encoder();
        this.password2 = c.encode(password2);//this.password2 = password2.trim();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile.trim();
    }
    /* Errors */
    public static final Integer ERR_vat_rc_no_ENTER = new Integer(1);
    public static final Integer ERR_vat_rc_no_INVALID = new Integer(2);
    public static final Integer ERR_vat_rc_no_NUM_ONLY = new Integer(3);
    //public static final Integer ERR_cst_rc_no_ENTER = new Integer(4);
    //public static final Integer ERR_cst_rc_no_INVALID = new Integer(5);
    //public static final Integer ERR_cst_rc_no_NUM_ONLY = new Integer(6);
    public static final Integer ERR_username_ENTER = new Integer(7);
    public static final Integer ERR_username_INVALID = new Integer(8);
    public static final Integer ERR_password_ENTER = new Integer(9);
    public static final Integer ERR_password_INVALID = new Integer(10);
    public static final Integer ERR_password2_ENTER = new Integer(11);
    public static final Integer ERR_password2_INVALID = new Integer(12);
    public static final Integer ERR_dob_ENTER = new Integer(13);
    public static final Integer ERR_dob_INVALID = new Integer(14);
    public static final Integer ERR_answer_ENTER = new Integer(15);
    public static final Integer ERR_passlen_ENTER = new Integer(16);
    public static final Integer ERR_pass2len_INVALID = new Integer(17);
    public static final Integer ERR_passmat_ENTER = new Integer(18);
    public static final Integer ERR_vat_rc_no_db_ONLY = new Integer(19);
    public static final Integer ERR_vat_rc_no_db1_ONLY = new Integer(20);
    public static final Integer ERR_username_db_ONLY = new Integer(21);
    public static final Integer ERR_pan_no_db_ONLY = new Integer(22);
    public static final Integer ERR_dob_FORMAT = new Integer(23);
    public static final Integer ERR_dob1_FORMAT = new Integer(24);
    public static final Integer ERR_week_FORMAT = new Integer(25);
    public static final Integer ERR_email_ENTER = new Integer(26);
    public static final Integer ERR_email_FORMAT = new Integer(27);
    public static final Integer ERR_mobile_ENTER = new Integer(28);
    public static final Integer ERR_mobile_INVALID = new Integer(29);
    public static final Integer ERR_mobile_NUM_ONLY = new Integer(30);
    public static final Integer ERR_pan_no_ENTER = new Integer(31);
    //public static final Integer ERR_pan_no_INVALID = new Integer(32);
    //public static final Integer ERR_pan_no_NUM_ONLY = new Integer(33);
    // Holds error messages for the properties
    Map errorCodes = new HashMap();
    // Maps error codes to textual messages.
    // This map must be supplied by the object that instantiated this bean.
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
        Pattern pattern = Pattern.compile("[\\'\\+\\=-><]+_ ");
        if (vat_rc_no.length() == 0) {
            errorCodes.put("vat_rc_no", ERR_vat_rc_no_ENTER);
        } else if (!vat_rc_no.matches("[\\d]+")) {
            errorCodes.put("vat_rc_no", ERR_vat_rc_no_INVALID);
        } else if (vat_rc_no.length() < 11 || vat_rc_no.length() > 11) {
            errorCodes.put("vat_rc_no", ERR_vat_rc_no_NUM_ONLY);
        }

        /* if (cst_rc_no.length() == 0) {
         errorCodes.put("cst_rc_no", ERR_cst_rc_no_ENTER);
         } else if (!cst_rc_no.matches("[\\d]+")) {
         errorCodes.put("cst_rc_no", ERR_cst_rc_no_INVALID);
         } else if(cst_rc_no.length() <11 || cst_rc_no.length()>11)
         { errorCodes.put("cst_rc_no",ERR_cst_rc_no_NUM_ONLY);}*/

        if (pan_no.length() == 0) {
            errorCodes.put("pan_no", ERR_pan_no_ENTER);
        }


        // Validate zipcode
        Matcher m = pattern.matcher(username);
        boolean result2 = m.find();
        if (username.length() == 0) {
            errorCodes.put("username", ERR_username_ENTER);
        } //else

        //  if(result2){  errorCodes.put("username", ERR_username_INVALID);}
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
            if (ch == '#' || ch == '-' || ch == '_' || ch == '.' || ch == '!' || ch == '~' || ch == '*' || ch == '\'' || ch == '(' || ch == ')' || ch == '$' || ch == '&' || ch == '^' || ch == '%' || ch == '@' || ch == '?' || ch == '[' || ch == ']' || ch == '}' || ch == '{' || ch == '|' || ch == ':' || ch == ';') {
                {
                    count3++;
                }
            }
        }
        if (username.length() > 0) {
            int ch = 0;
            System.out.println("length " + username.length());
            len = username.length();
            for (int j = 0; j < len - 1; j++) {
                ch = username.charAt(j);

                if (ch == ' ' || ch == '#' || ch == '-' || ch == '_' || ch == '.' || ch == '!' || ch == '~' || ch == '*' || ch == '\'' || ch == '(' || ch == ')' || ch == '$' || ch == '&' || ch == '^' || ch == '%' || ch == '@' || ch == '?' || ch == '[' || ch == ']' || ch == '}' || ch == '{' || ch == '|' || ch == ':' || ch == ';') {

                    count4++;

                }
            }
        }
        if (count4 > 0) {
            errorCodes.put("username", ERR_username_INVALID);
        }



        Matcher m1 = pattern.matcher(password);
        boolean result1 = m1.find();
        if (password.length() == 0) {
            errorCodes.put("password", ERR_password_ENTER);
        } else if (password.length() < 8) {
            errorCodes.put("password", ERR_passlen_ENTER);
        } else if (password.matches("itsfalse") || password2.matches("itsfalse")) {
            errorCodes.put("password", ERR_week_FORMAT);
            errorCodes.put("password2", ERR_week_FORMAT);
        }
//Email Validation Starts
        if (email.length() == 0) {
            errorCodes.put("email", ERR_email_ENTER);
        }
        Pattern p = Pattern.compile("^\\.|^\\@");
        Matcher mm = p.matcher(email);
        if (mm.find()) //System.err.println("Email addresses don't start" + " with dots or @ signs.");
        {
            errorCodes.put("email", ERR_email_FORMAT);
        }
        p = Pattern.compile("^www\\.");
        mm = p.matcher(email);
        if (mm.find()) {
            errorCodes.put("email", ERR_email_FORMAT);//System.out.println("Email addresses don't start" + " with \"www.\", only web pages do.");
        }
        p = Pattern.compile("[\\@]+");
        mm = p.matcher(email);
        boolean result12 = mm.find();
        if (!result12) {
            errorCodes.put("email", ERR_email_FORMAT);//System.out.println("Noo"+result1);
        }
        p = Pattern.compile("[^A-Za-z0-9\\.\\@_\\-~#]+");
        mm = p.matcher(email);
        StringBuffer sb = new StringBuffer();
        boolean result22 = m.find();
        boolean deletedIllegalChars = false;

        while (result22) {
            deletedIllegalChars = true;
            mm.appendReplacement(sb, "");
            result22 = mm.find();
        }
        mm.appendTail(sb);
        email = sb.toString();
        if (deletedIllegalChars) {
            errorCodes.put("email", ERR_email_FORMAT);//System.out.println("It contained incorrect characters" +" , such as spaces or commas.");
        }
        //  Email Validation ends
        //Mobile validation
        if (mobile.length() == 0) {
            errorCodes.put("mobile", ERR_mobile_ENTER);
        } else if (!mobile.matches("[\\d]+")) {
            errorCodes.put("mobile", ERR_mobile_INVALID);
        } else if (mobile.length() < 10 || mobile.length() > 10) {
            errorCodes.put("mobile", ERR_mobile_NUM_ONLY);
        }
        //Mobile Validation ends
        Matcher m2 = pattern.matcher(password2);
        boolean result = m2.find();
        if (password2.length() == 0) {
            errorCodes.put("password2", ERR_password2_ENTER);
        } else if (password2.length() < 8) {
            errorCodes.put("password2", ERR_pass2len_INVALID);
        } 
        else if (!password.equals(password2)) {
            errorCodes.put("password2", ERR_passmat_ENTER);
            errorCodes.put("password1", ERR_passmat_ENTER);
        }

        if (dob.length() == 0) {
            errorCodes.put("dob", ERR_dob_ENTER);
        } else if (!dob.matches("[-\\w]+")) {
            errorCodes.put("dob", ERR_dob_INVALID);
        }
        if (answer.length() == 0) {
            errorCodes.put("answer", ERR_answer_ENTER);
        }
        //  if(vat_rc_no.matches("[\\d]+") &&  password.equals(password2) && dob.length() != 0 && answer.length() != 0 && vat_rc_no.length() == 0 && cst_rc_no.length() == 0 && password2.length() >=6)
        // {
        try {
            ep.connect();
            ResultSet rs = ep.execSQL("select vat_rc_no from vat.regd_dealer where vat_rc_no='" + vat_rc_no + "' and pan_no='" + pan_no + "'");
            String sql = "select vat_rc_no from login_dealer where vat_rc_no='" + vat_rc_no + "'";
            System.out.println("rs1 " + sql);
            ResultSet rs1 = ep.execSQL(sql);
            System.out.println();
            ResultSet rs2 = ep.execSQL("select vat_rc_no from vat.login_dealer where usr_cd='" + username + "'");

            if (!rs.next()) {
                errorCodes.put("vat_rc_no", ERR_vat_rc_no_db_ONLY);
                errorCodes.put("pan_no", ERR_pan_no_db_ONLY);
            } else if (rs1.next()) {
                errorCodes.put("vat_rc_no", ERR_vat_rc_no_db1_ONLY);
            } else if (rs2.next()) {
                errorCodes.put("username", ERR_username_db_ONLY);
            }
            ep.close();
        } catch (Exception e) {
            // errorCodes.put("username",e);errorCodes.put("tin",e);errorCodes.put("password",e);
            System.out.println("error "+e);
        }
        // }

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
        }   // If no errors, form is valid
        return errorCodes.size() == 0;
    }

    public boolean process() {
        if (!isValid()) {
            password = "";
            password2 = "";
            return false;
        }

        // Process form...

        // Clear the form
     /*     vat_rc_no = "";
         cst_rc_no = "";
         username = "";
         password = "";
         password2 = "";
         dob = "";
         answer = "";*/
        errorCodes.clear();
        return true;
    }
}