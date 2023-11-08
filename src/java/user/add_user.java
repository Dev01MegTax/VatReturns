package user;
    import java.util.*;
    import java.sql.*;
    import java.util.regex.*;
    public class add_user {
        /*  The properties */
        String id = "";
        String desig = "";
        String username ="";
        String pwd= "";
        String pwd2 = "";
        String ch = "";
        String gp ="";
        String cp="",sc="",ul="",answer="";
        int count=0,count1=0,count2=0,count3=0;

        public String getId() {
            return id;
        }
        public void setId(String id)
        {
          encoder c = new encoder();
          this.id = c.encode(id.trim());
        }
        public String getDesig() {
            return desig;
        }
        public void setDesig(String desig) {
        encoder c = new encoder();
       this.desig = c.encode(desig.trim());
 }
       public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
          encoder c = new encoder();
          this.username = c.encode(username);
        }
       public String getpwd() {
            return pwd;
        }
        public void setpwd(String pwd) {
            this.pwd = pwd;
        }
       public String getpwd2() {
            return pwd2;
        }
        public void setpwd2(String pwd2) {
          
          this.pwd2 = pwd2;
        }
       public String getCh() {
            return ch;
        }
        public void setCh(String ch) {
         encoder c = new encoder();
          this.ch = c.encode(ch.trim());
        }
       public String getGp() {
            return gp;
        }
        public void setGp(String gp) {
            encoder c = new encoder();
            this.gp= c.encode(gp.trim());
        }
        public String getCp() {
            return cp;
        }
        public void setCp(String cp) {
            encoder c = new encoder();
            this.cp= c.encode(cp.trim());
        }
        public String getSc() {
            return sc;
        }
        public void setSc(String sc) {
            encoder c = new encoder();
            this.sc= c.encode(sc.trim());
        }
        public String getAnswer() {
            return answer;
        }
        public void setAnswer(String answer) {
            this.answer= answer.trim();
        }
      public static final Integer ERR_id_ENTER = new Integer(1);
      public static final Integer ERR_id_NUM_ONLY = new Integer(2);
      public static final Integer ERR_desig_ENTER = new Integer(3);
      public static final Integer ERR_username_ENTER = new Integer(4);
      public static final Integer ERR_pwd_ENTER = new Integer(5);
      public static final Integer ERR_pwd_INVALID = new Integer(6);
      public static final Integer ERR_answer_ENTER = new Integer(7);
      public static final Integer ERR_passlen_ENTER = new Integer(8);
      public static final Integer ERR_passmat_ENTER = new Integer(9);
      public static final Integer ERR_id_db1_ONLY = new Integer(10);
      public static final Integer ERR_week_FORMAT = new Integer(11);
      public static final Integer ERR_username_LENGTH = new Integer(12);
      public static final Integer ERR_cp_ENTER = new Integer(13);
      public static final Integer ERR_gp_ENTER = new Integer(14);
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

               if (desig.length() == 0 || desig.length()>20) {
                errorCodes.put("desig", ERR_desig_ENTER);
            }
            Matcher m = patternu.matcher(username);
            boolean result2 = m.find();
            if (username.length() == 0) {
                errorCodes.put("username", ERR_username_ENTER);
            }
            else if (username.length() >30) {
                errorCodes.put("username", ERR_username_LENGTH);
            }
            Matcher m1 = patternu.matcher(pwd);
           boolean result1 = m1.find();
              if (pwd.length() == 0) {
                errorCodes.put("pwd", ERR_pwd_ENTER);
            } else if (pwd.length() <8) {
                errorCodes.put("pwd", ERR_passlen_ENTER);
            }
                 int len = pwd.length();
    for (int i = 0; i < len; i++) {
     int ch = pwd.charAt(i);
    if ('a' <= ch && ch <= 'z') {		// 'A'..'Z'
           count++;
      }
     if ('a' <= ch && ch <= 'z') {	// 'a'..'z'
count1++;
      }
     if ('0' <= ch && ch <= '9') {	// '0'..'9'
count2++;
      }
      if (ch == '#' || ch == '-' || ch == '_' || ch == '.' || ch == '!'  || ch == '~' || ch == '*' || ch == '\'' || ch == '(' || ch == ')' || ch == '$' || ch == '&' || ch == '^' || ch == '%' || ch == '@' || ch == '?' || ch == '[' || ch == ']' || ch == '}' || ch == '{' || ch == '|' || ch == ':' || ch == ';' ) {
      {
          count3++;
      }
    }}

    Matcher m2 = patternu.matcher(pwd2);
    boolean result = m2.find();
             if (pwd2.length() <8) {
                errorCodes.put("pwd2", ERR_passlen_ENTER);
            }
            else if(!pwd.equals(pwd2))
            {errorCodes.put("pwd2", ERR_passmat_ENTER);
            errorCodes.put("pwd1", ERR_passmat_ENTER);
            }
            int len1 = pwd2.length();
    for (int j = 0; j < len1; j++) {
     int chj = pwd2.charAt(j);
    if ('a' <= chj && chj <= 'z') {		// 'A'..'Z'
           count++;
      }
     if ('a' <= chj && chj <= 'z') {	// 'a'..'z'
count1++;
      }
     if ('0' <= chj && chj <= '9') {	// '0'..'9'
count2++;
      }
      if (chj == '$' || chj == '@' || chj == '%' || chj == '^' || chj == '&' || chj == '[' || chj == ']' || chj == '{' || chj == '}' || chj == '#' || chj == ':' ||  chj == '?' ||  chj == '-' || chj == '_' || chj == '.' || chj == '!' || chj == '~' || chj == '*' || chj == '\'' || chj == '(' || chj == ')')
      {
          count3++;
      }
    }
         if(pwd.equals("") || pwd2.equals(""))
       {
        errorCodes.put("pwd2", ERR_week_FORMAT);
        }
         if (answer.length() == 0)
         {
            errorCodes.put("answer", ERR_answer_ENTER);
         }
       if (ch.length() == 0 || ch.length() > 2) {
                errorCodes.put("ch", ERR_cp_ENTER);
            } else if (!ch.matches("[\\d]+")) {
                errorCodes.put("ch", ERR_cp_ENTER);
            }
       if (gp.length() == 0 || gp.length() > 2) {
                errorCodes.put("gp", ERR_gp_ENTER);
            } else if (!gp.matches("[\\d]+")) {
                errorCodes.put("gp", ERR_gp_ENTER);
            }

try{
           ep.connect();
ResultSet rs5=ep.execSQL("select usr_cd from vat.usr_cd where usr_cd='"+id+"'");
if(rs5.next())
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
                pwd= "";
          pwd2 = "";
                return false;
            }
          pwd= "";
          pwd2 = "";
          errorCodes.clear();
            return true;
        }
    }