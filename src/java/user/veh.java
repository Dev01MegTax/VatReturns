
package user;
    import java.util.*;
    public class veh {
        String vehilce12 = "";
        String vehilce11 = "";
       public String getVehilce11() {
            return vehilce11;
        }
            public void setVehilce11(String charge_cd) {
           encoder c = new encoder();
          this.vehilce11 = c.encode(charge_cd);
        }
       public String getVehilce12() {
            return vehilce12;
        }
           public void setVehilce12(String vehilce12) {
            encoder c = new encoder();
          this.vehilce12 = c.encode(vehilce12.trim());;
        }
        public static final Integer ERR_vehilce12_ENTER = new Integer(1);
        public static final Integer ERR_vehilce12_INVALID = new Integer(2);
        public static final Integer ERR_vehilce11_ENTER = new Integer(3);
        public static final Integer ERR_vehilce11_NUM_ONLY = new Integer(4);
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
        public boolean isValid() {
            // Clear all errors
            errorCodes.clear();
if (vehilce12.length() == 0) {
                errorCodes.put("vehilce12", ERR_vehilce12_ENTER);
            }
else       if (vehilce12.length() >6 || vehilce12.length() <1) {
                errorCodes.put("vehilce12", ERR_vehilce12_INVALID);
            }

            else if(vehilce12.length() != 0 && !vehilce12.matches("[-\\w]+"))
            {errorCodes.put("vehilce12",ERR_vehilce12_INVALID);}
if (vehilce11.length() == 0 || vehilce11.length()>4) {
                errorCodes.put("vehilce11", ERR_vehilce11_ENTER);
            }
else if (!vehilce11.matches("[\\d]+")) {
       errorCodes.put("vehilce11", ERR_vehilce11_NUM_ONLY);
            }
         else {
                try {
                    int i = Integer.parseInt(vehilce11);
                } catch (NumberFormatException e) {
                    errorCodes.put("vehilce11", ERR_vehilce11_NUM_ONLY);
                }
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
