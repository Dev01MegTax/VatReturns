package user;
    import java.util.*;
    import java.util.regex.*;
    import java.sql.*;
    import java.text.*;
    public class Myform5 {
        String frm="";
        String series = "";
        String from_no = "";
        String circle="";
        public String getFrm() {
            return frm;
        }
        public void setFrm(String status) {
            this.frm = status.trim();
        }
        public String getSeries() {
            return series;
        }
        public void setSeries(String charge_cd) {
            this.series = charge_cd.trim();
        }
       public String getFrom_no() {
            return from_no;
        }
        public void setFrom_no(String day) {
            this.from_no = day.trim();
        }
       public String getCircle() {
            return circle;
        }
        public void setCircle(String circle) {
            this.circle = circle.trim();
        }
        public static final Integer ERR_frm_ENTER = new Integer(1);
        public static final Integer ERR_frm_INVALID = new Integer(2);
        public static final Integer ERR_series_ENTER = new Integer(3);
        public static final Integer ERR_series_INVALID = new Integer(4);
        public static final Integer ERR_from_no_ENTER = new Integer(5);
        public static final Integer ERR_from_no_NUM_ONLY = new Integer(6);
        public static final Integer ERR_wb_INVALID = new Integer(7);
        public static final Integer ERR_wb1_ENTER = new Integer(8);
        public static final Integer ERR_wb2_NUM_ONLY = new Integer(9);
        public static final Integer ERR_wb3_NUM_ONLY = new Integer(10);
        public static final Integer ERR_dt_ENTER = new Integer(11);
        
        // Holds error messages for the properties
        Map errorCodes = new HashMap();

        // Maps error codes to textual messages.
        // This map must be supplied by the obfron_noect that instantiated this bean.
        Map msgMap;
        public void setErrorMessages(Map msgMap) {
            this.msgMap = msgMap;
        }

        public String getErrorMessage(String propName) {
            Integer code = (Integer)(errorCodes.get(propName));
            if (code == null) {
                return "";
            } else if (msgMap != null) {
                String msg = (String)msgMap.get(code);
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

             if (circle.length() == 0) {
                errorCodes.put("frm", ERR_frm_ENTER);
            }
            if (frm.length() == 0) {
                errorCodes.put("frm", ERR_frm_ENTER);
            } else if (!frm.matches("I")) {
                errorCodes.put("frm", ERR_frm_INVALID);
            }
             if (series.length() == 0) {
                errorCodes.put("series", ERR_series_ENTER);
            }
            else if (!series.matches("I")) {
                errorCodes.put("series", ERR_series_INVALID);
            }
          if (from_no.length() == 0) {
                errorCodes.put("from_no", ERR_from_no_ENTER);
            }

else if (!from_no.matches("[\\d]+")) {
       errorCodes.put("from_no", ERR_from_no_NUM_ONLY);
            }
         else {
                try {
                    int i = Integer.parseInt(from_no);
                } catch (NumberFormatException e) {
                    errorCodes.put("from_no", ERR_from_no_NUM_ONLY);
                }
            }
    Dbbean ep=new Dbbean();
    String issue_dt="";
    int dat=0;
    try
    {
     ep.connect();
     ResultSet rsb=ep.execSQL("select vat.wbill_sno from vat.m_issue_reg_v where wbill_sno='"+from_no+"'");
     ResultSet rsc=ep.execSQL("select * from vat.wbill_cancel_lost where '"+from_no+"' between from_no and to_no");
     ResultSet rs=ep.execSQL("select issue_dt from vat.m_issue_reg_online where erdc_rc_no='I' and charge_cd='"+circle+"' and ('"+from_no+"' between from_no and to_no)");

        if(rsb.next())
        {
           errorCodes.put("from_no", ERR_wb2_NUM_ONLY);
        }
        else  if(rsc.next())
        {
            errorCodes.put("from_no", ERR_wb3_NUM_ONLY);
        }
        else    if(!rs.next())
       {
           if(from_no.length()>0){errorCodes.put("from_no", ERR_wb_INVALID);}
       }
    ResultSet rs1=ep.execSQL("select to_char(vat.m_issue_reg_online.issue_dt,'dd/mm/yyyy') from vat.m_issue_reg_online where erdc_rc_no='I' and charge_cd='"+circle+"' and '"+from_no+"' between from_no and to_no");
   
if(rs1.next()){
issue_dt=rs1.getString(1);
try
{
SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
String sys = df1.format(new java.util.Date());//System date
DateFormat df8 = new SimpleDateFormat("dd/MM/yyyy");
java.util.Date dt8 = df8.parse(issue_dt);
DateFormat df6 = new SimpleDateFormat("dd-MMM-yyyy");
java.util.Date dt5 = df6.parse(sys);
dat = (int) ((dt5.getTime() - dt8.getTime())/(1000*60*60*24));
if(dat<90)
{
errorCodes.put("from_no", ERR_wb1_ENTER);
}
}
catch(Exception e)
{
}
}
else if(rs1.next()) {}
   ep.close();
    }
    catch(Exception e)
    {
    }
    return errorCodes.size() == 0;
        }

        public boolean process() {
            if (!isValid()) {
                return false;
            }

            // Process form...

            // Clear the form
            from_no = "";
            errorCodes.clear();
            return true;
        }
    }
