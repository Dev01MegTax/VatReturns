package stlogvalidation;

import java.util.*;
import java.text.*;
import java.sql.*;
import java.util.regex.*;
import java.net.*;
import java.net.URLEncoder;
import filter.*;
import bean.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

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
    String firstOnlineReturn = "";

    public String getFirstOnlineReturn() {
	return firstOnlineReturn;
    }

    public void setFirstOnlineReturn(String firstOnlineReturn) {
	this.firstOnlineReturn = firstOnlineReturn;
    }

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
    public static final Integer ERR50 = new Integer(58);
    public static final Integer ERRA = new Integer(59);
    public static final Integer ERRB = new Integer(60);
    public static final Integer ERRC = new Integer(61);
    public static final Integer ERRD = new Integer(62);
    public static final Integer ERRE = new Integer(63);
    public static final Integer ERRF = new Integer(64);
    public static final Integer ERRG = new Integer(65);
    public static final Integer ERRH = new Integer(66);
    public static final Integer ERRFR = new Integer(67);
    public static final Integer ERRFM = new Integer(68);
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

	// Clear all errors
	errorCodes.clear();
	//  errorCodes.put("fail", ERR_tin_ENTER);
	Pattern p1 = Pattern.compile("^MAR|^JUN|^SEP|^DEC");
	Matcher m = p1.matcher(quarter1);
	if (!quarter1.matches("01|04|07|10")) {
	    //     System.out.println("1 " + quarter1);
	    errorCodes.put("quarter1", ERR_from_dt_INVALID);
	} else if (quarter1.length() == 0) {
	    errorCodes.put("quarter1", ERR_from_dt_ENTER);
	} else if (!quarter1.matches("[\\d]+")) {
	    //     System.out.println("2 " + quarter1);
	    errorCodes.put("quarter1", ERR_from_dt_INVALID);
	}
	if (!quarter2.matches("03|06|09|12")) {
	    //   System.out.println("3 " + quarter2);
	    errorCodes.put("quarter2", ERR_to_dt_INVALID);
	} else if (!quarter2.matches("[\\d]+")) {
	    //    System.out.println("4 " + quarter2);
	    errorCodes.put("quarter2", ERR_from_dt_INVALID);
	}

	if (quarter1.matches("01")) {
	    if (!quarter2.matches("03")) {
		System.out.println("Period   " + quarter1 + "   " + quarter2);
		errorCodes.put("quarter2", ERR49);
	    }
	}
	if (quarter1.matches("04")) {
	    if (!quarter2.matches("06")) {
		System.out.println("Period   " + quarter1 + "   " + quarter2);
		errorCodes.put("quarter2", ERR49);
	    }
	}
	if (quarter1.matches("07")) {
	    if (!quarter2.matches("09")) {
		System.out.println("Period   " + quarter1 + "   " + quarter2);
		errorCodes.put("quarter2", ERR49);
	    }
	}
	if (quarter1.matches("10")) {
	    if (!quarter2.matches("12")) {
		System.out.println("Period   " + quarter1 + "   " + quarter2);
		errorCodes.put("quarter2", ERR49);
	    }
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
	if (quarter1 != "") {
	    p = Integer.parseInt(quarter1);
	}

	int u = 0;

	if (year1 != "") {
	    u = Integer.parseInt(year1);
	}
	dbBean db = new dbBean();
	ResultSet rs = null;

	try {
	    db.connect();
//	    int year11 = Integer.parseInt(year1);
//	    int year22 = Integer.parseInt(year2);
//	    if (year11 != year22) {
//		System.out.println("years   " + year11 + "   " + year22);
//		errorCodes.put("fail", ERR50);
//	    }
//	    String sql1 = "select count(*) from master_return_online where vat_rc_no='" + s + "'";
//
//	    ResultSet rs1 = db.execSQL(sql1);
//	    int first = 0;
//	    String month = "";
//	    String year = "";
//	    int pmonth = 0;
//	    int pyear = 0;

//            rs = db.execSQL("select max(prd_from_mon),max(prd_from_yr),vat_rc_no from master_return_online where vat_rc_no='" + s + "'  and prd_from_yr=(select max(prd_from_yr) from master_return_online where vat_rc_no='" + s + "') group by vat_rc_no");
//            while (rs.next()) {
//                int yr_db = Integer.parseInt(rs.getString(2));
//                int mon_db = Integer.parseInt(rs.getString(1));
//
//                System.out.println(yr_db + "  " + mon_db + "  " + u + "  " + p);
//                if (u < yr_db) {
//                    errorCodes.put("fail", ERRA);
//                }
//                if (u == yr_db) {
//                    if (p < mon_db)//Condition 1
//                    {
//                        errorCodes.put("fail", ERRB);
//                    }
//                    if (p == mon_db)//Condition 2
//                    {
//                        errorCodes.put("fail", ERRC);
//                    }
//                    if (p > mon_db)//Condition 3
//                    {
//                        if (mon_db != 12) {
//                            if (p != mon_db + 3) {
//                                errorCodes.put("fail", ERRF);
//                            } else if (mon_db == 12) {
//                                if (p != 01 || p != 1) {
//                                    errorCodes.put("fail", ERRG);
//                                }
//                            }
//                        }
//                    }
//                }
//
//                if (u > yr_db) {
//                    if (u == (yr_db + 1)) {
//                        if (mon_db == 10) {
//                            if (p != 01) {
//                                errorCodes.put("fail", ERRD);
//                            }
//                        } else if (mon_db != 10) {
//                            errorCodes.put("fail", ERRE);
//                        }
//                    } else if (u != (yr_db + 1)) {
//                        errorCodes.put("fail", ERRH);
//                    }
//                }//
//
//            }
	    rs = db.execSQL("select count(*) count from master_return_online where vat_rc_no='" + s + "'  and prd_from_yr='" + year1 + "' and prd_to_yr='" + year2 + "' and prd_from_mon='" + quarter1 + "' and prd_to_mon='" + quarter2 + "'");
	    while (rs.next()) {
		int count = Integer.parseInt(rs.getString("count"));
		if (count > 0) {
		    errorCodes.put("fail", ERR1);
		}
	    }
	} catch (Exception e) {
	    System.out.println("Error C " + e);
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
