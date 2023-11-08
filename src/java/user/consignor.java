package user;
    import java.util.*;
    import java.text.*;
    public class consignor {

        String cstrcno = "";
        String dealername = "";
        String dealeraddr1 = "";
        String dealeraddr2 = "";
        String dealeraddr3 = "";
        String dealerpin = "";
        String state_nm = "";
        String state_cd = "";
        int i=0;
        public String getCstrcno() {
            return cstrcno;
        }
        public void setCstrcno(String cstrcno) {
         encoder c = new encoder();
this.cstrcno=c.encode(cstrcno);

        }
        public String getDealername() {
        return dealername;

        }
        public void setDealername(String dealername) {
            encoder c = new encoder();
this.dealername=c.encode(dealername);
        }
        public String getDealeraddr1() {
        return dealeraddr1;

        }
        public void setDealeraddr1(String dealeraddr1) {
            encoder c = new encoder();
this.dealeraddr1=c.encode(dealeraddr1);
        }
        public String getDealeraddr2() {
        return dealeraddr2;

        }
        public void setDealeraddr2(String dealeraddr2) {
            encoder c = new encoder();
this.dealeraddr2=c.encode(dealeraddr2);
        }
        public String getDealeraddr3() {
        return dealeraddr3;

        }
        public void setDealeraddr3(String dealeraddr3) {
            encoder c = new encoder();
this.dealeraddr3=c.encode(dealeraddr3);
        }
public String getDealerpin() {
        return dealerpin;

        }
        public void setDealerpin(String dealerpin) {
            encoder c = new encoder();
this.dealerpin=c.encode(dealerpin);
        }
        public String getState_nm() {
        return state_nm;

        }
        public void setState_nm(String state_nm) {
            encoder c = new encoder();
this.state_nm=c.encode(state_nm);
        }
        public String getState_cd() {
        return state_cd;

        }
        public void setState_cd(String state_cd) {
            encoder c = new encoder();
this.state_cd=c.encode(state_cd);
        }
        /* Errors */

        public static final Integer ERR_cstrcno_ENTER = new Integer(1);
        public static final Integer ERR_cstrcno_INVALID = new Integer(2);
        public static final Integer ERR_dealername_ENTER = new Integer(3);
        public static final Integer ERR_dealerpin_INVALID = new Integer(4);
        public static final Integer ERR_dealerpin_ENTER = new Integer(5);


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
          SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
String sys = df1.format(new java.util.Date());//System date
          SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy;hh:mm:ss");
String log_dt = df2.format(new java.util.Date());
       /* Form validation and processing */
        public boolean isValid() {
             if(cstrcno.length()==0 || cstrcno.length()<11 || cstrcno.length()>11)
             {
             errorCodes.put("cstrcno", ERR_cstrcno_ENTER);
             }
             else if(!cstrcno.matches("[\\d]+"))
             {
             errorCodes.put("cstrcno", ERR_cstrcno_INVALID);
             }
             if(dealername.length()==0)
            {
             errorCodes.put("dealername", ERR_dealername_ENTER);
             }
             if(dealerpin.length()==0)
            {
             errorCodes.put("dealerpin", ERR_dealerpin_ENTER);
             }
             else if(!dealerpin.matches("[\\d]+"))
             {
             errorCodes.put("dealerpin", ERR_dealerpin_INVALID);
             }
 return errorCodes.size() == 0;

        }

        public boolean process() {
            if (!isValid()) {
                return false;

            }
            errorCodes.clear();
            return true;
        }


    }
