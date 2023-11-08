package user;
    import java.util.*;
    import java.sql.*;
    public class consignment {
        String vehicle2 = "";
        String vehicle1 = "";
       public String getVehicle1() {
            return vehicle1;
        }
        public void setVehicle1(String charge_cd) {
           encoder c = new encoder();
          this.vehicle1 = c.encode(charge_cd);
        }
       public String getVehicle2() {
            return vehicle2;
        }
        public void setVehicle2(String vehicle2) {
             dateenc c = new dateenc();
          this.vehicle2 = c.encode(vehicle2.trim());;
        }
        public static final Integer ERR_vehicle2_ENTER = new Integer(1);
        public static final Integer ERR_vehicle2_INVALID = new Integer(2);
        public static final Integer ERR_vehicle1_ENTER = new Integer(5);
        public static final Integer ERR_vehicle1_NUM_ONLY = new Integer(6);
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
if (vehicle2.length() == 0) {
                errorCodes.put("vehicle2", ERR_vehicle2_ENTER);
            }
else       if (vehicle2.length() >11 || vehicle2.length() <11) {
                errorCodes.put("vehicle2", ERR_vehicle2_INVALID);
            }

            else if(vehicle2.length() != 0 && !vehicle2.matches("[-\\w]+"))
            {errorCodes.put("vehicle2",ERR_vehicle2_INVALID);}
if (vehicle1.length() == 0) {
                errorCodes.put("vehicle1", ERR_vehicle1_ENTER);
            }
else if (vehicle1.length() >25) {
       errorCodes.put("vehicle1", ERR_vehicle1_NUM_ONLY);
            }

    return errorCodes.size() == 0;
        }

        public boolean process() {
            if (!isValid()) {
                return false;
            }

            // Process form...

            // Clear the form
           // vehicle1 = "";
            errorCodes.clear();
            return true;
        }
    }
