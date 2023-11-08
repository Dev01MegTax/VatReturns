package user;
    import java.util.*;
    import java.text.*;
    public class transport {

        /*  The properties */
        String dealername = "";
        String dealeraddr11 = "";
        String dealeraddr12 = "";
        String dealeraddr13 = "";
        String dealerpin1 = "";
        String pin11 = "";
        int i=0;
        public String getDealername() {
        return dealername;

        }
        public void setDealername(String dealername) {
            encoder c = new encoder();
this.dealername=c.encode(dealername);
        }
        public String getDealeraddr11() {
        return dealeraddr11;

        }
        public void setDealeraddr11(String dealeraddr1) {
            encoder c = new encoder();
this.dealeraddr11=c.encode(dealeraddr1);
        }
        public String getDealeraddr12() {
        return dealeraddr12;

        }
        public void setDealeraddr12(String dealeraddr2) {
            encoder c = new encoder();
this.dealeraddr12=c.encode(dealeraddr2);
        }
        public String getDealeraddr13() {
        return dealeraddr13;

        }
        public void setDealeraddr13(String dealeraddr3) {
            encoder c = new encoder();
this.dealeraddr13=c.encode(dealeraddr3);
        }
public String getDealerpin1() {
        return dealerpin1;

        }
        public void setDealerpin1(String dealerpin) {
            encoder c = new encoder();
this.dealerpin1=c.encode(dealerpin);
        }
        public String getPin11() {
        return pin11;

        }
        public void setPin11(String state_nm) {
            encoder c = new encoder();
this.pin11=c.encode(state_nm);
        }
        public static final Integer ERR_dealername_ENTER = new Integer(1);
        public static final Integer ERR_pinlen_ENTER = new Integer(2);
        public static final Integer ERR_pinno_ENTER = new Integer(3);

        // Holds error messages for the properties
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
          SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
String sys = df1.format(new java.util.Date());//System date
          SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy;hh:mm:ss");
String log_dt = df2.format(new java.util.Date());
       /* Form validation and processing */
        public boolean isValid() {
if(dealername.length()==0)
{
errorCodes.put("dealername",ERR_dealername_ENTER);
}
if(!pin11.matches("[\\d]+"))
{
errorCodes.put("pin11",ERR_pinno_ENTER);
}
else if(pin11.length()>6)
{
errorCodes.put("pin11",ERR_pinlen_ENTER);
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

