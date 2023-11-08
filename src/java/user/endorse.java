package user;
    import java.util.*;
    import java.util.regex.*;
    public class endorse {

        /*  The properties */
        String from_no = "";
        String series = "";
        public String getSeries() {
            return from_no;
        }
        public void setSeries(String series) {
            encoder c = new encoder();
          this.series = c.encode(series);
        }
        public String getFrom_no() {
            return from_no;
        }
        public void setFrom_no(String from_no) {
             encoder c = new encoder();
          this.from_no = c.encode(from_no);
        }

/* Errors */
        public static final Integer ERR_from_no_ENTER = new Integer(1);
        public static final Integer ERR_from_no_INVALID = new Integer(2);
        public static final Integer ERR_series_ENTER = new Integer(3);

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
            if (series.length() == 0) {
                errorCodes.put("series", ERR_series_ENTER);
            } else
           if (!series.matches("I")) {
                errorCodes.put("series", ERR_series_ENTER);
            }
            // Validate username
            if (from_no.length() == 0) {
                errorCodes.put("from_no", ERR_from_no_ENTER);
            }
            else if (!from_no.matches("[\\d]+")) {
 errorCodes.put("from_no", ERR_from_no_INVALID);
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
            series="";
            from_no="";
            errorCodes.clear();
            return true;
        }
    }
