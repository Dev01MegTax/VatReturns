/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stlogvalidation;

import java.util.HashMap;
import java.util.Map;
import bean.*;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class returnno {

    String txtRetNo="";

    public String getTxtRetNo() {
        return txtRetNo;
    }

    public void setTxtRetNo(String salt) {
        //encoder c = new encoder();
        this.txtRetNo = salt.trim();// this.tin = tin.trim();
    }


    public static final Integer ERR_tin_ENTER = new Integer(1);
    public static final Integer ERR_username_db_INVALID = new Integer(2);
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
        try{
        ep.connect();
        ResultSet rs = ep.execSQL("select count(*) from vat.master_return_online where return_no='"+txtRetNo+"'");
        while(rs.next())
        {
        int count = rs.getInt(1);
        System.out.println("count "+count);
        if(count<=0)
        {
          errorCodes.put("fail", ERR_username_db_INVALID);
        }
        }

           }
        catch(Exception e)
        {
        
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
