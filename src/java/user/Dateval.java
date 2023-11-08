package user;
    import java.util.regex.*;
    import java.util.*;
    import java.text.*;
    import java.sql.*;
    public class Dateval {
        String num="";
        String reval_dt = "";
        String circle = "";
        public String getNum() {
            return num;
        }
        public void setNum(String status) {
            this.num = status.trim();
        }
        public String getReval_dt() {
            return reval_dt;
        }
        public void setReval_dt(String charge_cd) {
            this.reval_dt = charge_cd.trim();
        }
        public String getCircle() {
            return circle;
        }
        public void setCircle(String circle) {
            this.circle = circle.trim();
        }
       
        public static final Integer ERR_num_ENTER = new Integer(1);
        public static final Integer ERR_num_INVALID = new Integer(2);
        public static final Integer ERR_reval_dt_ENTER = new Integer(3);
        public static final Integer ERR_reval_dt_INVALID = new Integer(4);
        public static final Integer ERR_reval_INVALID = new Integer(5);
        public static final Integer ERR_num_NUM_ONLY = new Integer(6);
        Map errorCodes = new HashMap();
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
            int dat=0;
try{
        SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
String sys = df1.format(new java.util.Date());//System date
DateFormat df8 = new SimpleDateFormat("dd-MMM-yyyy");
java.util.Date dt8 = df8.parse(reval_dt);//Issue date converted into date type
DateFormat df6 = new SimpleDateFormat("dd-MMM-yyyy");
java.util.Date dt5 = df6.parse(sys);//system date converted into date type
//This function calculate the total number of days between two dates.
dat = (int) ((dt5.getTime() - dt8.getTime())/(1000*60*60*24));
//System.out.println(di);
    }
    catch(Exception e){}

             if (reval_dt.length() == 0) {
                errorCodes.put("reval_dt", ERR_reval_dt_ENTER);
            }

            if(dat<0){ errorCodes.put("reval_dt", ERR_reval_INVALID);}

            return errorCodes.size() == 0;
        }

        public boolean process() {
            if (!isValid()) {
                return false;
            }

            // Process form...

            // Clear the form
            reval_dt = "";
            errorCodes.clear();
            return true;
        }


    }
