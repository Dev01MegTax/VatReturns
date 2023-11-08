package stlogvalidation;

import java.util.*;
import java.text.*;
import java.sql.*;
import java.util.regex.*;
import java.net.*;
import java.net.URLEncoder;
import filter.*;
import bean.*;

public class periodVal {

    String quarter1 = "";
    String year1 = "";
    String quarter2 = "";
    String year2 = "";
    String s = "";
    int p = 0;
    String q = "";
     String h = "";
        String k = "";
            


    public String getQuarter1() {
        return quarter1;
    }

    public void setQuarter1(String username) {
        // this.username = username.trim();
        encoder c = new encoder();
        this.quarter1 = c.encode(username.trim());
    }

    public String getYear1() {
        return year1;
    }

    public void setYear1(String tin) {
        encoder c = new encoder();
        this.year1 = c.encode(tin.trim());// this.tin = tin.trim();
    }

    public String getQuarter2() {
        return quarter2;
    }

    public void setQuarter2(String username) {
        // this.username = username.trim();
        encoder c = new encoder();
        this.quarter2 = c.encode(username.trim());
    }

    public String getYear2() {
        return year2;
    }

    public void setYear2(String tin) {
        encoder c = new encoder();
        this.year2 = c.encode(tin.trim());// this.tin = tin.trim();
    }

    public String getS() {
        return s;
    }

    public void setS(String tin) {
        encoder c = new encoder();
        this.s = c.encode(tin.trim());// this.tin = tin.trim();
    }

    public static final Integer ERR_from_dt_ENTER = new Integer(1);
    public static final Integer ERR_from_dt_INVALID = new Integer(2);
    public static final Integer ERR_to_dt_ENTER = new Integer(3);
    public static final Integer ERR_to_dt_INVALID = new Integer(4);
    public static final Integer ERR_from_yr_INVALID = new Integer(5);
    public static final Integer ERR_from_yr_ENTER = new Integer(6);
    public static final Integer ERR_to_yr_ENTER = new Integer(7);
    public static final Integer ERR_to_yr_INVALID = new Integer(8);
    public static final Integer ERR1 = new Integer(9);
    public static final Integer ERR2 = new Integer(10);
    public static final Integer ERR3 = new Integer(11);
    public static final Integer ERR4 = new Integer(12);
    public static final Integer ERR5 = new Integer(13);
    public static final Integer ERR6 = new Integer(14);
    public static final Integer ERR7 = new Integer(15);
    public static final Integer ERR8 = new Integer(16);
    public static final Integer ERR9 = new Integer(17);
    public static final Integer ERR10 = new Integer(18);
    public static final Integer ERR11 = new Integer(19);
    public static final Integer ERR12 = new Integer(20);
    public static final Integer ERR13 = new Integer(21);
    public static final Integer ERR14 = new Integer(22);
    public static final Integer ERR15 = new Integer(23);
    public static final Integer ERR16 = new Integer(24);
    public static final Integer ERR17 = new Integer(25);
    public static final Integer ERR18 = new Integer(26);
    public static final Integer ERR19 = new Integer(27);
    public static final Integer ERR20 = new Integer(28);
    public static final Integer ERR21 = new Integer(29);
    public static final Integer ERR22 = new Integer(30);
    public static final Integer ERR23 = new Integer(31);
    public static final Integer ERR24 = new Integer(32);
    public static final Integer ERR25 = new Integer(33);
    public static final Integer ERR26 = new Integer(34);
    public static final Integer ERR27 = new Integer(35);
    public static final Integer ERR28 = new Integer(36);
    public static final Integer ERR29 = new Integer(37);
    public static final Integer ERR30 = new Integer(38);
    public static final Integer ERR31 = new Integer(39);
    public static final Integer ERR32 = new Integer(40);
    public static final Integer ERR33 = new Integer(41);
    public static final Integer ERR34 = new Integer(42);
    public static final Integer ERR35 = new Integer(43);
    public static final Integer ERR36 = new Integer(44);
    public static final Integer ERR37 = new Integer(45);
    public static final Integer ERR38 = new Integer(46);
    public static final Integer ERR39 = new Integer(47);
    public static final Integer ERR40 = new Integer(48);
    public static final Integer ERR41 = new Integer(49);
    public static final Integer ERR42 = new Integer(50);
    public static final Integer ERR43 = new Integer(51);
    public static final Integer ERR44 = new Integer(52);
    public static final Integer ERR45 = new Integer(53);
    public static final Integer ERR46 = new Integer(54);
    public static final Integer ERR47 = new Integer(55);
    public static final Integer ERR48 = new Integer(56);
    public static final Integer ERR49 = new Integer(57);
    Map errorCodes = new HashMap();
    Map msgMap;

    public void setErrorMessages(Map msgMap) {
        this.msgMap = msgMap;
    }

    public String getErrorMessage(String propName) {
        Integer code = (Integer) (errorCodes.get(propName));
        if (code == null) {
            return "";
        } else if (msgMap != null) {
            String msg = (String) msgMap.get(code);
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

        // System.out.println("username "+username);
        // Clear all errors
        errorCodes.clear();
        //  errorCodes.put("fail", ERR_tin_ENTER);
        Pattern p1 = Pattern.compile("^MAR|^JUN|^SEP|^DEC");
        Matcher m = p1.matcher(quarter1);
        if (!quarter1.matches("01|04|07|10")) {
            System.out.println("1 " + quarter1);
            errorCodes.put("quarter1", ERR_from_dt_INVALID);
        } else if (quarter1.length() == 0) {
            errorCodes.put("quarter1", ERR_from_dt_ENTER);
        } else if (!quarter1.matches("[\\d]+")) {
            System.out.println("2 " + quarter1);
            errorCodes.put("quarter1", ERR_from_dt_INVALID);
        }
        if (!quarter2.matches("03|06|09|12")) {
            System.out.println("3 " + quarter2);
            errorCodes.put("quarter2", ERR_to_dt_INVALID);
        } else if (!quarter2.matches("[\\d]+")) {
            System.out.println("4 " + quarter2);
            errorCodes.put("quarter2", ERR_from_dt_INVALID);
        }
        if (year1.length() == 0) {
            errorCodes.put("year1", ERR_from_yr_ENTER);
        } else if (!year1.matches("[\\d]+")) {
            errorCodes.put("year1", ERR_from_yr_INVALID);
        }
        if (year2.length() == 0) {
            errorCodes.put("year2", ERR_to_yr_ENTER);
        } else if (!year2.matches("[\\d]+")) {
            errorCodes.put("year2", ERR_to_yr_INVALID);
        }
 if(quarter1!="")
        {
            p = Integer.parseInt(quarter1);
        }
//p = Integer.parseInt(quarter1);

       int u = 0;

 if(year1!="")
        {
     u = Integer.parseInt(year1);
 }
dbBean db = new dbBean();
        ResultSet rs = null;
        //Start
     try {System.out.println("tin from vat  "+s);
             db.connect();
            
            rs = db.execSQL("select max(prd_from_mon),max(prd_from_yr) from master_return where vat_rc_no='" + s + "' and prd_from_yr=(select max(prd_from_yr) from master_return where vat_rc_no='" + s + "') ");
            while (rs.next()) {
                System.out.println("tin from vat  "+rs.getString(1)+"  "+rs.getString(2));
                h = rs.getString(1);

                k = rs.getString(2);
                int w = Integer.parseInt(h);
                int y = Integer.parseInt(k);
                            if (h == null && k == null) {
                    // out.println("");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == w && u == y) {
                    //   out.println("You have already submitted the return for this period");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == w - 3 && u == y) {
                    // out.println("You have already submitted the return for this period");
                    errorCodes.put("fail", ERR2);
                }
                if (h != null && k != null && p == w - 6 && u == y) {
                    // out.println("You have already submitted the return for this period");
                    errorCodes.put("fail", ERR3);
                }
                if (h != null && k != null && p == w - 9 && u == y) {
                    // out.println("You have already submitted the return for this period");
                    errorCodes.put("fail", ERR4);
                }
                if (h != null && k != null && p == w + 3 && u == y) {
                    //out.println(1);
                    errorCodes.put("fail", ERR5);
                }

                if (h != null && k != null && p == w + 6 && u == y) {
                    //  out.println("You have to return for"+(w+3)+"to"+(w+5));
                    errorCodes.put("fail", ERR6);
                }
                if (h != null && k != null && p == w + 9 && u == y) {
                    // out.println("You have to return for"+(w+3)+"to"+(w+5));
                    errorCodes.put("fail", ERR7);
                }
                if (h != null && k != null && p == w && u == y + 1) {
                    // out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+y+"year");
                    errorCodes.put("fail", ERR8);

                }
                if (h != null && k != null && p == (w + 3) && u == y + 1) {
                    //out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+y+"year");
                    errorCodes.put("fail", ERR9);

                }
                if (h != null && k != null && p == (w + 6) && u == y + 1) {
                    //   out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+y+"year");
                    errorCodes.put("fail", ERR10);

                }
                if (h != null && k != null && p == (w + 9) && u == y + 1) {
                    //   out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+y+"year");
                    errorCodes.put("fail", ERR11);

                }

                if (h != null && k != null && p == ((w + 3) - 12) && u == y + 1) {
                    //    out.println(1);
                    errorCodes.put("fail", ERR12);
                }

                if (h != null && k != null && p == ((w + 6) - 12) && u == y + 1) {
                    //  out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12));
                    errorCodes.put("fail", ERR13);
                }
                if (h != null && k != null && p == ((w + 9) - 12) && u == y + 1) {
                    // out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12));
                    errorCodes.put("fail", ERR14);
                }
                /* if(h!=null&&k!=null&&p==((w+12)-12)&&u==y+1)
                {
                out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12));

                }*/

                if (h != null && k != null && p == (w + 3) - 12 && u == y + 2) {
                    //  out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+1)+"year");
                    errorCodes.put("fail", ERR15);
                }
                if (h != null && k != null && p == ((w + 6) - 12) && u == y + 2) {
                    //  out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+1)+"year");
                    errorCodes.put("fail", ERR16);
                }
                if (h != null && k != null && p == ((w + 9) - 12) && u == y + 2) {
                    //  out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+1)+"year");
                    errorCodes.put("fail", ERR17);
                }
                /* if(h!=null&&k!=null&&p==(w+12)-12&&u==y+2)
                {
                out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+1)+"year");

                }*/
                if (h != null && k != null && p == (w + 3) && u == y + 2) {
                    //     out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+(y+1)+"year");
                    errorCodes.put("fail", ERR18);
                }

                if (h != null && k != null && p == (w + 6) && u == y + 2) {
                    //  out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+(y+1)+"year");
                    errorCodes.put("fail", ERR19);
                }
                if (h != null && k != null && p == (w + 9) && u == y + 2) {
                    //    out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+(y+1)+"year");
                    errorCodes.put("fail", ERR20);
                }
                if (h != null && k != null && p == w && u == y + 2) {
                    //   out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+1)+"year");
                    errorCodes.put("fail", ERR21);
                }



                if (h != null && k != null && p == (w + 3) - 12 && u == y + 3) {
                    //   out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y)+"year");
                    errorCodes.put("fail", ERR22);
                }
                if (h != null && k != null && p == ((w + 6) - 12) && u == y + 3) {
                    //  out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y)+"year");
                    errorCodes.put("fail", ERR23);
                }
                if (h != null && k != null && p == ((w + 9) - 12) && u == y + 3) {
                    //  out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y)+"year");
                    errorCodes.put("fail", ERR24);
                }
                if (h != null && k != null && p == (w + 12) - 12 && u == y + 3) {
                    //  out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+(y)+"year");
                    errorCodes.put("fail", ERR25);
                }
                if (h != null && k != null && p == (w + 3) - 12 && u == y + 4) {
                    // out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y)+"year");
                    errorCodes.put("fail", ERR26);
                }
                if (h != null && k != null && p == ((w + 6) - 12) && u == y + 4) {
                    //   out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y)+"year");
                    errorCodes.put("fail", ERR48);
                }
                if (h != null && k != null && p == ((w + 9) - 12) && u == y + 4) {
                //    out.println("You have to return for" + ((w + 3) - 12) + "to" + ((w + 5) - 12) + "in the" + (y + 3) + "year");
                    errorCodes.put("fail", ERR27);
                }
                if (h != null && k != null && p == (w + 12) - 12 && u == y + 4) {
                   // out.println("You have to return for" + (w + 3) + "to" + (w + 5) + "in the" + (y + 3) + "year");
                     errorCodes.put("fail", ERR28);

                }
                if (h != null && k != null && p == (w + 3) - 12 && u == y + 5) {
                    //   out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+4)+"year");
                    errorCodes.put("fail", ERR29);
                }
                if (h != null && k != null && p == ((w + 6) - 12) && u == y + 5) {
                    // out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+4)+"year");
                    errorCodes.put("fail", ERR30);
                }
                if (h != null && k != null && p == ((w + 9) - 12) && u == y + 5) {
                    //  out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+4)+"year");
                    errorCodes.put("fail", ERR31);
                }
                if (h != null && k != null && p == (w + 12) - 12 && u == y + 5) {
                    //   out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+(y+4)+"year");
                    errorCodes.put("fail", ERR32);
                }
                if (h != null && k != null && p == (w + 3) - 12 && u == y + 6) {
                    //   out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+5)+"year");
                    errorCodes.put("fail", ERR33);
                }
                if (h != null && k != null && p == ((w + 6) - 12) && u == y + 6) {
                    //   out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+5)+"year");
                    errorCodes.put("fail", ERR34);
                }
                if (h != null && k != null && p == ((w + 9) - 12) && u == y + 6) {
                    //  out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+5)+"year");
                    errorCodes.put("fail", ERR35);
                }
                if (h != null && k != null && p == (w + 12) - 12 && u == y + 6) {
                    //   out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+(y+5)+"year");
                    errorCodes.put("fail", ERR36);
                }
                if (h != null && k != null && p == (w + 3) - 12 && u == y + 7) {
                    //out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+6)+"year");
                    errorCodes.put("fail", ERR37);
                }
                if (h != null && k != null && p == ((w + 6) - 12) && u == y + 7) {
                    // out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+6)+"year");
                    errorCodes.put("fail", ERR38);
                }
                if (h != null && k != null && p == ((w + 9) - 12) && u == y + 7) {
                    // out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+6)+"year");
                    errorCodes.put("fail", ERR39);
                }
                if (h != null && k != null && p == (w + 12) - 12 && u == y + 7) {
                    // out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+(y+6)+"year");
                    errorCodes.put("fail", ERR40);
                }
                //reverse
                if (h != null && k != null && (u == y - 2 || u == y - 3 || u == y - 4 || u == y - 5 || u == y - 6)) {
                    //  out.println("You have already submitted the return for this period");
                    errorCodes.put("fail", ERR41);
                }


                if (h != null && k != null && p == (w + 6) - 12 && u == y - 1) {
                    //  out.println("You have already submitted the return for this period");

                    errorCodes.put("fail", ERR42);
                }
                if (h != null && k != null && p == (w + 9) - 12 && u == y - 1) {
                    //  out.println("You have already submitted the return for this period");
                    errorCodes.put("fail", ERR43);
                }
                if (h != null && k != null && p == (w + 12) - 12 && u == y - 1) {
                    // out.println("You have already submitted the return for this period");
                    errorCodes.put("fail", ERR44);
                }
                if (h != null && k != null && p == (w + 3) - 12 && u == y - 1) {
                    //  out.println("You have already submitted the return for this period");
                    errorCodes.put("fail", ERR45);
                }
                if (h != null && k != null && p == (w + 18) - 12 && u == y - 1) {
                    //  out.println("You have already submitted the return for this period");
                    errorCodes.put("fail", ERR46);
                }
                if (h != null && k != null && p == (w + 21) - 12 && u == y - 1) {
                    //  out.println("You have already submitted the return for this period");
                    errorCodes.put("fail", ERR47);
                    
                }
            }
        } catch (Exception e) {
            //  out.println(1);
        }
        //End




        return errorCodes.size() == 0;
    }

    public boolean process() {
        if (!isValid()) {
            //  quarter1 = "";
            //   quarter2 = "";
            //   year1 = "";
            //   year2 = "";
            return false;
        }
        //quarter1 = "";
        // quarter2 = "";
        // year1 = "";
        // year2 = "";
        errorCodes.clear();
        return true;
    }
}
