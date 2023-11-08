package user;
    import java.util.regex.*;
    import java.util.*;
import java.text.*;
    public class date2 {
        String vat_rc_no="";
        String date1 = "";
        String date2 = "";
        public String getVat_rc_no() {
            return vat_rc_no;
        }
        public void setVat_rc_no(String vat_rc_no) {
            encoder c = new encoder();
          this.vat_rc_no = c.encode(vat_rc_no.trim());
           // this.vat_rc_no = vat_rc_no.trim();
        }
        public String getDate1() {
            return date1;
        }
        public void setDate1(String date1) {
          //  this.date1 = date1.trim();
        dateenc c = new dateenc();
          this.date1 = c.encode(date1.trim());
        }
        public String getDate2() {
            return date2;
        }
        public void setDate2(String date2) {
           dateenc c = new dateenc();
          this.date2 = c.encode(date2.trim());
           // this.date2 = date2.trim();
        }
        public static final Integer ERR_vat_rc_no_ENTER = new Integer(1);
        public static final Integer ERR_vat_rc_no_INVALID = new Integer(2);
        public static final Integer ERR_date1_ENTER = new Integer(3);
        public static final Integer ERR_date2_ENTER = new Integer(4);
        public static final Integer ERR_date1_small_INVALID = new Integer(5);
        public static final Integer ERR_date2_small_INVALID = new Integer(6);
        public static final Integer ERR_date1_small_ONLY = new Integer(7);
        public static final Integer ERR_date2_small_ONLY = new Integer(8);

        // Holds error messages for the properties
        Map errorCodes = new HashMap();

        // Maps error codes to textual messages.
        // This map must be supplied by the object that instantiated this bean.
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
            int dat=0,dat1=0,dat2=0;
try{
SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
String sys = df1.format(new java.util.Date());//System date
DateFormat df8 = new SimpleDateFormat("dd-MMM-yyyy");
Date dt8 = df8.parse(date1);
DateFormat dfs = new SimpleDateFormat("dd-MMM-yyyy");
Date dts = dfs.parse(date2);//Issue date converted into date type
DateFormat df6 = new SimpleDateFormat("dd-MMM-yyyy");
Date dt5 = df6.parse(sys);//system date converted into date type
//This function calculate the total number of days between two dates.
dat = (int) ((dt5.getTime() - dt8.getTime())/(1000*60*60*24));//Diff between system date and start date
dat1 = (int) ((dt5.getTime() - dts.getTime())/(1000*60*60*24));//Diff between system date and end date
dat2 = (int) ((dt8.getTime() - dts.getTime())/(1000*60*60*24));//Diff between start date and end date
    }
    catch(Exception e){System.out.println(e);}
             if (vat_rc_no.length() == 0) {
                errorCodes.put("vat_rc_no", ERR_vat_rc_no_ENTER);
            }
            if (vat_rc_no.length() >11 || vat_rc_no.length() <11) {
                errorCodes.put("vat_rc_no", ERR_vat_rc_no_INVALID);
            }
            if (date1.length() == 0) {
                errorCodes.put("date1", ERR_date1_ENTER);
            }
            if (date1.length() >11 || date1.length() <11) {
                errorCodes.put("date1", ERR_date1_ENTER);
            }
             if (date2.length() == 0) {
                errorCodes.put("date2", ERR_date2_ENTER);
            }
            if (date2.length() >11 || date2.length() <11) {
                errorCodes.put("date2", ERR_date2_ENTER);
            }
            else if(!vat_rc_no.matches("[\\d]+"))
            {errorCodes.put("vat_rc_no",ERR_vat_rc_no_INVALID);}

            else if(date1.length() != 0 && !date1.matches("[-\\w]+"))
            {errorCodes.put("date1",ERR_date1_ENTER);}

            else if(date2.length() != 0 && !date2.matches("[-\\w]+"))
            {errorCodes.put("date2",ERR_date2_ENTER);}
            if(dat<0){ errorCodes.put("date1", ERR_date1_small_INVALID);}
            if(dat1<0){ errorCodes.put("date2",ERR_date2_small_INVALID);}
            if(dat2>0){ errorCodes.put("date1",ERR_date1_small_ONLY);
            errorCodes.put("date2",ERR_date2_small_ONLY);}
       // If no errors, form is valid
            return errorCodes.size() == 0;
        }

        public boolean process() {
            if (!isValid()) {
                return false;
            }

            // Process form...

            // Clear the form
            vat_rc_no = "";
            date1="";
            date2="";
            errorCodes.clear();
            return true;
        }


    }
