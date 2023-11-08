package user;
    import java.util.*;
    import java.util.regex.*;
    public class print {

        /*  The properties */
        String wbno = "";
        public String getWbno() {
            return wbno;
        }
        public void setWbno(String wbno) {
             encoder c = new encoder();
          this.wbno = c.encode(wbno);
        }

/* Errors */
        public static final Integer ERR_wbno_ENTER = new Integer(1);
        public static final Integer ERR_wbno_INVALID = new Integer(2);
        public static final Integer ERR_wbno_NUM_ONLY = new Integer(3);

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

            Pattern pattern = Pattern.compile("[\\'\\+\\=-><]+");
            Matcher m = pattern.matcher(wbno);
            if (wbno.length() == 0) {
                errorCodes.put("wbno", ERR_wbno_ENTER);
            }
            else if (!wbno.matches("[\\d]+")) {
    errorCodes.put("wbno", ERR_wbno_INVALID);
            }

            // If no errors, form is valid
            return errorCodes.size() == 0;
        }

        public boolean process() {
            if (!isValid()) {
                return false;
            }

            // Process form...

            // Clear the form
            wbno="";
            errorCodes.clear();
            return true;
        }
    }
