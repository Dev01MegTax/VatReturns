package user;
    import java.util.*;
    import java.sql.*;
    import java.text.*;
    import java.util.regex.*;
    public class MyForm1 {
        /*  The properties */
        String username = "";
        String password = "";
        int i=0;
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
         encoder c = new encoder();
this.username=c.encode(username);

        }
        public String getPassword() {
        return password;

        }
        public void setPassword(String password) {
            encoder c = new encoder();
            this.password=c.encode(password);
        }
        public static final Integer ERR_username_ENTER = new Integer(1);
        public static final Integer ERR_username_INVALID = new Integer(2);
        public static final Integer ERR_username_db_INVALID = new Integer(3);
        public static final Integer ERR_lock_VALID = new Integer(4);

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
String sys = df1.format(new java.util.Date());
          SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy;hh:mm:ss");
String log_dt = df2.format(new java.util.Date());
        public boolean isValid() {
            errorCodes.clear();
            Pattern pattern1 = Pattern.compile("[\\'\\+\\=-><]+");
 Pattern pattern = Pattern.compile("^\\'><script>|^\\s|\\'><|'|'><img");
           Matcher m = pattern.matcher(username);
           Matcher m1 = pattern.matcher(password);
            boolean res = m.find();boolean res1 = m1.find();
            //if(result2){
            if (username.length() == 0) {
                errorCodes.put("fail", ERR_username_ENTER);
            } else if (username.length()>15) {
               errorCodes.put("fail", ERR_username_ENTER);
            } else if(res){errorCodes.put("fail", ERR_username_ENTER);}
            if (password.length() == 0) {
                errorCodes.put("fail", ERR_username_ENTER);
            } else if (password.length() < 3) {
               errorCodes.put("fail", ERR_username_ENTER);
            }
            else if(res1){errorCodes.put("fail", ERR_username_ENTER);}
 try{
     Dbbean ep=new Dbbean();
     ep.connect();
     int x=ep.updateSQL("UPDATE vat.usr_cd  SET usr_cd.ACC_LOG ='YES' WHERE usr_cd.ACC_LOG  ='NO' and usr_cd.log_date<'"+sys+"'");
     ResultSet rs3=ep.execSQL("select * from vat.usr_cd where usr_cd='"+username+"' and acc_log='NO'");
      if(rs3.next())
     {
         errorCodes.put("fail",ERR_lock_VALID);
     }
      else if(!rs3.next())
      {
          ResultSet rs2=ep.execSQL("select passwd from vat.usr_cd where usr_cd='"+username+"' and acc_log='YES'");

     if(rs2.next()){

String hashed="";int flag=0;
String hash=rs2.getString(1);
ResultSet hs=ep.execSQL("select salt from vat.salt_adm where id=(select max(id) from salt_adm)");
while(hs.next())
{
SimpleSHA1 sha=new SimpleSHA1();
hashed=sha.SHA1(hs.getString(1)+hash);
if(password.matches(hashed))
{
flag=0;
break;
}
else if(!password.matches(hashed) || !password.equals(hashed))
{
    errorCodes.put("fail",ERR_username_db_INVALID);//flag=1;
}
}
    }
     else if(!rs2.next()){   errorCodes.put("fail",ERR_username_INVALID);  }
   
      }   ep.close();
 }catch(Exception e)
 {
   errorCodes.put("fail",ERR_username_INVALID);    }
            return errorCodes.size() == 0;
        }

        public boolean process() {
            if (!isValid()) {

         try{

             Dbbean ep=new Dbbean();
             ep.connect();
//
 ResultSet rs=ep.execSQL("select count from vat.usr_cd where usr_cd='"+username+"'");
 if(rs.next()){
     i=rs.getInt(1);
if(i<3)
{
    int k=ep.updateSQL("update vat.usr_cd set count='"+(1+i)+"',log_date ='"+sys+"' where usr_cd='"+username+"'");
 }
else if(i>=15)
{
 int km=ep.updateSQL("update vat.usr_cd set acc_log='NO',count='0' where usr_cd='"+username+"'"); //and log_date ='"+sys+"'");
 }
 }
ep.close();
         }catch(Exception e){}
 password = "";
                return false;

            }
          try{
  Dbbean ep=new Dbbean();
             ep.connect();
 int km=ep.updateSQL("update vat.usr_cd set count='0',acc_log='YES' where usr_cd='"+username+"' and log_date ='"+sys+"'");
//int del=ep.updateSQL("delete from salt_adm where id<(select max(id) from salt_adm)");
int del=ep.updateSQL("delete from vat.salt_adm");
ep.close();
}catch(Exception e){}
            username = "";
            password = "";
            errorCodes.clear();
            return true;
        }


    }
