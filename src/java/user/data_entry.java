package user;
    import java.util.*;
    import java.sql.*;
    public class data_entry {
        String frm="";
        String series = "";
        String from_no = "";
                public String getFrm() {
            return frm;
        }
        public void setFrm(String frm) {
            encoder c = new encoder();
          this.frm = c.encode(frm);
        }
        public String getSeries() {
            return series;
        }
        public void setSeries(String charge_cd) {
           encoder c = new encoder();
          this.series = c.encode(charge_cd);
        }
       public String getFrom_no() {
            return from_no;
        }
        public void setFrom_no(String from_no) {
             encoder c = new encoder();
          this.from_no = c.encode(from_no);
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
    try
    {
     ep.connect();
     ResultSet rs=ep.execSQL("select * from vat.m_issue_reg_online where '"+from_no+"' between from_no and to_no");
   if(rs.next())
   {
     ResultSet rsb=ep.execSQL("select * from vat.endorsement where wbill_sno='"+from_no+"' and CONSIGNOR_TRADENM is null");
if(!rsb.next())
        {
           errorCodes.put("from_no", ERR_wb2_NUM_ONLY);
        }
//else {}
   }
   else
   {
         errorCodes.put("from_no", ERR_wb3_NUM_ONLY);
   }
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
           // from_no = "";
            errorCodes.clear();
            return true;
        }
    }
