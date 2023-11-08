/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stlogvalidation;

import java.util.HashMap;
import java.util.Map;
import bean.*;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Admin
 */
public class returnno1 {
    String txtRetNo = "";
    String tin = "";
    public String getTxtRetNo() {
        return txtRetNo;
    }
    public void setTxtRetNo(String salt) {
        //encoder c = new encoder();
        this.txtRetNo = salt.trim();// this.tin = tin.trim();
    }
    public String getTin() {
        return tin;
    }
    public void setTin(String tin) {
        //encoder c = new encoder();
        this.tin = tin.trim();// this.tin = tin.trim();
    }
    public static final Integer ERR_tin_ENTER = new Integer(1);
    public static final Integer ERR_username_db_INVALID = new Integer(2);
    public static final Integer ERR_INVALID = new Integer(3);
     public static final Integer ERR_APPROVED = new Integer(4);
     public static final Integer ERR_REJECTED =new Integer(5);
     
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
    public boolean isValid() {
        errorCodes.clear();
        if (txtRetNo.length() == 0) {
            errorCodes.put("fail", ERR_tin_ENTER);
        }
        dbBean ep = new dbBean();
        try {
            ep.connect();
            ResultSet rs = ep.execSQL("select count(*) from vat.master_return_online where return_no='" + txtRetNo + "' and vat_rc_no='" + tin + "' and flag='N'");
            while (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("count " + count);
                System.out.println("return_no :"+txtRetNo);
                if (count <= 0) {
                    errorCodes.put("fail", ERR_username_db_INVALID);
                }
            }
            
            
            //------NEW CODE-----
            ResultSet ra = ep.execSQL("select return_no from vat.master_return_online where return_no='" + txtRetNo + "' and vat_rc_no='" + tin + "' and flag='Y'");
            if (ra.next()) {
                System.out.println("Filed return has been accepted");
                errorCodes.put("fail", ERR_APPROVED);
            }
            ResultSet rr = ep.execSQL("select return_no from vat.master_return_online where return_no='" + txtRetNo + "' and vat_rc_no='" + tin + "' and flag='R'");
            if (rr.next()) {
                System.out.println("Filed return has been Rejected");
                errorCodes.put("fail", ERR_REJECTED);
            }
            //------END NEW CODE------
            
            String return_dt = "";
            
            ResultSet rs1 = ep.execSQL("select to_char(log_dt,'dd-MM-yyyy') from vat.master_return_online where return_no='" + txtRetNo + "' and vat_rc_no='" + tin + "' and flag='N'");
            while (rs1.next()) {
                System.out.println("inside rs");
                return_dt = rs1.getString(1);
  
                //  }
            }
            SimpleDateFormat df1 = new SimpleDateFormat("dd/MMM/yyyy");
            String sys = df1.format(new java.util.Date());//System date
            DateFormat df8 = new SimpleDateFormat("dd-MMM-yyyy");
            java.util.Date dt8 = df8.parse(return_dt);//Issue date converted into date type
            DateFormat df6 = new SimpleDateFormat("dd-MMM-yyyy");
            java.util.Date dt5 = df6.parse(sys);//system date converted into date type
            System.out.println("return date----"+dt5);
//This function calculate the total number of days between two dates.
            int dat = (int) ((dt5.getTime() - dt8.getTime()) / (1000 * 60 * 60 * 24));
            System.out.println("Dates   " + dt5 + "  " + dt8 + "  " + dat + "   " + tin);
            if (dat > 60) {
                errorCodes.put("fail", ERR_INVALID);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return errorCodes.size() == 0;
    }
    public boolean process() {
        if (!isValid()) {
            txtRetNo = "";
            return false;
        }
        txtRetNo = "";
        errorCodes.clear();
        return true;
    }
}