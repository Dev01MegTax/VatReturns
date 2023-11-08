package stlogvalidation;

import java.util.*;
import java.text.*;
import java.sql.*;
import java.util.regex.*;
import java.net.*;
import java.net.URLEncoder;
import filter.*;
import bean.*;

public class period {

    String quarter1 = "";
    String year1 = "";
    String quarter2 = "";
    String year2 = "";
    String s = "";
    int p = 0;

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


        //Start


        //  String s=request.getParameter("ltp");
        // String month1="";
        //  month1=request.getParameter("lmm");
        //   if(month1!="")
        //  {
        //    p = Integer.parseInt(quarter1);
        //  }
        p = Integer.parseInt(quarter1);

        int u = 0;
        String q = "";
        // q=request.getParameter("lpp");
        //// if(q!="")
        //  {
        u = Integer.parseInt(year1);
        //  }

        ResultSet rs = null;
        String h = null;
        String k = null;
        try {
            // db.connect();
            dbBean db = new dbBean();
            rs = db.execSQL("select max(prd_from_mon),max(prd_from_yr) from master_return where vat_rc_no='" + s + "' and prd_from_yr=(select max(prd_from_yr) from master_return where vat_rc_no='" + s + "') ");


            while (rs.next()) {
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
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == w - 6 && u == y) {
                    // out.println("You have already submitted the return for this period");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == w - 9 && u == y) {
                    // out.println("You have already submitted the return for this period");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == w + 3 && u == y) {
                    //out.println(1);
                    errorCodes.put("fail", ERR1);
                }

                if (h != null && k != null && p == w + 6 && u == y) {
                    //  out.println("You have to return for"+(w+3)+"to"+(w+5));
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == w + 9 && u == y) {
                    // out.println("You have to return for"+(w+3)+"to"+(w+5));
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == w && u == y + 1) {
                    // out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+y+"year");
                    errorCodes.put("fail", ERR1);

                }
                if (h != null && k != null && p == (w + 3) && u == y + 1) {
                    //out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+y+"year");
                    errorCodes.put("fail", ERR1);

                }
                if (h != null && k != null && p == (w + 6) && u == y + 1) {
                    //   out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+y+"year");
                    errorCodes.put("fail", ERR1);

                }
                if (h != null && k != null && p == (w + 9) && u == y + 1) {
                    //   out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+y+"year");
                    errorCodes.put("fail", ERR1);

                }

                if (h != null && k != null && p == ((w + 3) - 12) && u == y + 1) {
                    //    out.println(1);
                    errorCodes.put("fail", ERR1);
                }

                if (h != null && k != null && p == ((w + 6) - 12) && u == y + 1) {
                    //  out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12));
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == ((w + 9) - 12) && u == y + 1) {
                    // out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12));
                    errorCodes.put("fail", ERR1);
                }
                /* if(h!=null&&k!=null&&p==((w+12)-12)&&u==y+1)
                {
                out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12));

                }*/

                if (h != null && k != null && p == (w + 3) - 12 && u == y + 2) {
                    //  out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+1)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == ((w + 6) - 12) && u == y + 2) {
                    //  out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+1)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == ((w + 9) - 12) && u == y + 2) {
                    //  out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+1)+"year");
                    errorCodes.put("fail", ERR1);
                }
                /* if(h!=null&&k!=null&&p==(w+12)-12&&u==y+2)
                {
                out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+1)+"year");

                }*/
                if (h != null && k != null && p == (w + 3) && u == y + 2) {
                    //     out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+(y+1)+"year");
                    errorCodes.put("fail", ERR1);
                }

                if (h != null && k != null && p == (w + 6) && u == y + 2) {
                    //  out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+(y+1)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == (w + 9) && u == y + 2) {
                    //    out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+(y+1)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == w && u == y + 2) {
                    //   out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+1)+"year");
                    errorCodes.put("fail", ERR1);
                }



                if (h != null && k != null && p == (w + 3) - 12 && u == y + 3) {
                    //   out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == ((w + 6) - 12) && u == y + 3) {
                    //  out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == ((w + 9) - 12) && u == y + 3) {
                    //  out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == (w + 12) - 12 && u == y + 3) {
                    //  out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+(y)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == (w + 3) - 12 && u == y + 4) {
                    // out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == ((w + 6) - 12) && u == y + 4) {
                    //   out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y)+"year");
                }
                if (h != null && k != null && p == ((w + 9) - 12) && u == y + 4) {
                //    out.println("You have to return for" + ((w + 3) - 12) + "to" + ((w + 5) - 12) + "in the" + (y + 3) + "year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == (w + 12) - 12 && u == y + 4) {
                   // out.println("You have to return for" + (w + 3) + "to" + (w + 5) + "in the" + (y + 3) + "year");
                     errorCodes.put("fail", ERR1);

                }
                if (h != null && k != null && p == (w + 3) - 12 && u == y + 5) {
                    //   out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+4)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == ((w + 6) - 12) && u == y + 5) {
                    // out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+4)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == ((w + 9) - 12) && u == y + 5) {
                    //  out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+4)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == (w + 12) - 12 && u == y + 5) {
                    //   out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+(y+4)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == (w + 3) - 12 && u == y + 6) {
                    //   out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+5)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == ((w + 6) - 12) && u == y + 6) {
                    //   out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+5)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == ((w + 9) - 12) && u == y + 6) {
                    //  out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+5)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == (w + 12) - 12 && u == y + 6) {
                    //   out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+(y+5)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == (w + 3) - 12 && u == y + 7) {
                    //out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+6)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == ((w + 6) - 12) && u == y + 7) {
                    // out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+6)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == ((w + 9) - 12) && u == y + 7) {
                    // out.println("You have to return for"+((w+3)-12)+"to"+((w+5)-12)+"in the"+(y+6)+"year");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == (w + 12) - 12 && u == y + 7) {
                    // out.println("You have to return for"+(w+3)+"to"+(w+5)+"in the"+(y+6)+"year");
                    errorCodes.put("fail", ERR1);
                }
                //reverse
                if (h != null && k != null && (u == y - 2 || u == y - 3 || u == y - 4 || u == y - 5 || u == y - 6)) {
                    //  out.println("You have already submitted the return for this period");
                    errorCodes.put("fail", ERR1);
                }


                if (h != null && k != null && p == (w + 6) - 12 && u == y - 1) {
                    //  out.println("You have already submitted the return for this period");

                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == (w + 9) - 12 && u == y - 1) {
                    //  out.println("You have already submitted the return for this period");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == (w + 12) - 12 && u == y - 1) {
                    // out.println("You have already submitted the return for this period");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == (w + 3) - 12 && u == y - 1) {
                    //  out.println("You have already submitted the return for this period");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == (w + 18) - 12 && u == y - 1) {
                    //  out.println("You have already submitted the return for this period");
                    errorCodes.put("fail", ERR1);
                }
                if (h != null && k != null && p == (w + 21) - 12 && u == y - 1) {
                    //  out.println("You have already submitted the return for this period");
                    errorCodes.put("fail", ERR1);
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
