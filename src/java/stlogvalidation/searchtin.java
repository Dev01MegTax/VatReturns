package stlogvalidation;
import java.util.HashMap;
import java.util.Map;
import bean.*;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class searchtin {
  
    String vatrc = "";

    public String getVatrc() {
        return vatrc;
    }

    public void setVatrc(String vatrc) {
        this.vatrc = vatrc;
    }

    public Map getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(Map errorCodes) {
        this.errorCodes = errorCodes;
    }

    public Map getMsgMap() {
        return msgMap;
    }

    public void setMsgMap(Map msgMap) {
        this.msgMap = msgMap;
    }
  
   
    public static final Integer ERR_tin_ENTER = new Integer(1);
    
    public static final Integer ERR_INVALID = new Integer(2);
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
        System.out.println("length "+vatrc.length());
        if (vatrc.length() != 10) {
            errorCodes.put("fail", ERR_tin_ENTER);
        }
        dbBean ep = new dbBean();
        try {
            ep.connect();
            String sql="select count(*) from vat.login_dealer where vat_rc_no='" + vatrc +"'";
            System.out.println("sql "+ sql);
            ResultSet rs = ep.execSQL(sql);
            if(rs.next())
            {
                 int count = rs.getInt(1);
                System.out.println("count " + count);
                if (count <= 0) {
                    errorCodes.put("fail", ERR_tin_ENTER);
                }
            }
            
          
         
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return errorCodes.size() == 0;
    }
    public boolean process() {
        if (!isValid()) {
            vatrc= "";
            return false;
        }
        vatrc = "";
        errorCodes.clear();
        return true;
    }
}