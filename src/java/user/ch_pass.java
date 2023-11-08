package user;
    import java.util.*;
    import java.sql.*;
    import java.util.regex.*;
    public class ch_pass {
        /*  The properties */
        String id = "";
        String op ="";
        String np= "";
        String cp = "";
        String answer="";
        int count=0,count1=0,count2=0,count3=0;

        public String getId() {
            return id;
        }
        public void setId(String id)
        {
          encoder c = new encoder();
          this.id = c.encode(id.trim());
        }
        public String getOp() {
            return op;
        }
        public void setOp(String op) {
        encoder c = new encoder();
       this.op = c.encode(op.trim());
 }
      
       public String getNp() {
            return np;
        }
        public void setNp(String np) {
            this.np = np;
        }
        public String getCp() {
            return cp;
        }
        public void setCp(String cp) {
           // encoder c = new encoder();
            this.cp= cp.trim();
        }
        public String getAnswer() {
            return answer;
        }
        public void setAnswer(String answer) {
            encoder c = new encoder();
          this.answer = c.encode(answer.trim());
        }
      public static final Integer ERR_id_ENTER = new Integer(1);
      public static final Integer ERR_id_NUM_ONLY = new Integer(2);
      public static final Integer ERR_op_ENTER = new Integer(3);
      public static final Integer ERR_np_ENTER = new Integer(4);
      public static final Integer ERR_answer_ENTER = new Integer(5);
      public static final Integer ERR_passlen_ENTER = new Integer(6);
      public static final Integer ERR_passmat_ENTER = new Integer(7);
      public static final Integer ERR_id_db1_ONLY = new Integer(8);
      public static final Integer ERR_week_FORMAT = new Integer(9);
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
            Dbbean ep=new Dbbean();
            Pattern pattern = Pattern.compile("\\.|\\@|\\!|\\>|\\<|\\*|\\&|\\+|\\_|\\-|\\=|\\{|[a-zA-Z]|<script>");
            Pattern patternu = Pattern.compile("\\>|\\<|\\-|\\=|<script>|alert");
            Pattern pattern1 = Pattern.compile("[\\-\\w]+");
            Matcher mv = pattern.matcher(id);
            boolean result2v = mv.find();
            if (id.length() == 0) {
                errorCodes.put("id", ERR_id_ENTER);
            } else if(id.length()>8)
                { errorCodes.put("id",ERR_id_NUM_ONLY);}

            if (op.length() == 0) {
                errorCodes.put("op", ERR_op_ENTER);
            }
              if (np.length() == 0) {
                errorCodes.put("np", ERR_np_ENTER);
            } else if (np.length() <8) {
                errorCodes.put("np", ERR_passlen_ENTER);
            }
           if(!np.equals(cp))
            {
            errorCodes.put("np", ERR_passmat_ENTER);
            }
         if(np.equals("") || cp.equals(""))
       {
        errorCodes.put("np", ERR_week_FORMAT);
        }
         if (answer.length() == 0)
         {
            errorCodes.put("answer", ERR_answer_ENTER);
         }

try{
           ep.connect();
ResultSet rs5=ep.execSQL("select usr_cd from vat.usr_cd where usr_cd='"+id+"' and passwd='"+op+"'");
if(rs5.next())
          {
//int k=ep.updateSQL("update usr_cd set passwd='"+np+"' where  usr_cd='"+id+"' and passwd='"+op+"'");
          }
else if(!rs5.next())
{
 errorCodes.put("id",ERR_id_db1_ONLY);
}
          ep.close();
           }
           catch(Exception e){
           }
  // If no errors, form is valid
            return errorCodes.size() == 0;
        }

        public boolean process() {
            if (!isValid()) {
          op="";
          np= "";
          cp = "";
                return false;
            }
          op="";
          np= "";
          cp = "";
          errorCodes.clear();
            return true;
        }
    }