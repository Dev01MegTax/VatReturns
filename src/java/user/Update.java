/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rajib
 */
public class Update extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        System.out.println("Update.java srvlet--------------------------------------------");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Dbbean db = new Dbbean();
        try {
            HttpSession session = request.getSession();
            String t1 = session.getAttribute("tin1").toString();//request.getParameter("Tinn");
            out.println(t1);
            System.out.println("tin no---" + t1);
            String month1 = request.getParameter("quarter1").toString();
            System.out.println("Month:" + month1);
            String yearF = request.getParameter("year1");
            System.out.println("Month:" + yearF);

            String month2 = request.getParameter("quarter2");
            System.out.println("month2---" + month2);
            String yearT = request.getParameter("year2");
            System.out.println("yearT----" + yearT);
            String combo1 = request.getParameter("combo1");
            System.out.println("combo1-----" + combo1);
            String str_date = request.getParameter("T72");
            System.out.println("str_date----" + str_date);
            String year = request.getParameter("year2");
            System.out.println("year---------" + year);
            String ret_no = request.getParameter("T68");
            System.out.println("ret no-----" + ret_no);

//            /************************************************/
//            int month1 = Integer.parseInt(request.getParameter("quarter1"));
//            int yearF = Integer.parseInt(request.getParameter("year1"));
//            int month2 = Integer.parseInt(request.getParameter("quarter2"));
//            int yearT = Integer.parseInt(request.getParameter("year2"));
//            int combo1 = Integer.parseInt(request.getParameter("combo1"));
//            String str_date = request.getParameter("T72");
//            int year = Integer.parseInt(request.getParameter("year2"));
//            String ret_no = request.getParameter("T68");
            /**
             * *********************************************
             */
            String y = "";

            if (year != null) {
                y = year.substring(2);
            }

            int s1 = 0;

            if (request.getParameter("Namr") != null) {
                //   s1 = Integer.parseInt(request.getParameter("Namr"));
            }

            //4-10
            double a1 = 0;
            double a2 = 0;
            double a3 = 0;
            double a4 = 0;
            double a5 = 0;
            double a6 = 0;
            double a7 = 0;
            double a8 = 0;

            double a9 = 0;
            double rp = 0;
            double rp1 = 0;

            double a10 = 0;
            double a11 = 0;
            double a12 = 0;
            double a13 = 0;
            double a14 = 0;
            double ot15 = 0;
            double ot16 = 0;
            double ot17 = 0;
            double due = 0;
            double a15 = 0;
            double a16 = 0;
            double a17 = 0;
            double a18 = 0;
            double a19 = 0;
            double a20 = 0;
            double a21 = 0;
            double a22 = 0;
            double a23 = 0;
            double a24 = 0;
            double a25 = 0;
            double a26 = 0;
            double a27 = 0;
            double a28 = 0;
            double a29 = 0;
            double a30 = 0;
            double a31 = 0;
            double a32 = 0;
            double a33 = 0;
            double a34 = 0;
            double a35 = 0;
            double a36 = 0;
            double a43 = 0;
            double a44 = 0;
            double a39 = 0;
            double a40 = 0;
            double a41 = 0;
            double a42 = 0;
            double a46 = 0;
            double a47 = 0;
            double a48 = 0;
            double a49 = 0;
            double a50 = 0;
            double a51 = 0;
            double a52 = 0;
            double a53 = 0;
            double a54 = 0;
            double a55 = 0;
            double a56 = 0;
            double a57 = 0;
            double a58 = 0;
            double a60 = 0;
            double a61 = 0;
            double a63 = 0;
            double a64 = 0;
            double a65 = 0;
            double a66 = 0;
            double a67 = 0;

            System.out.println("Update.java");

            String test = request.getParameter("T67");

            if (request.getParameter("T1") != null) {
                a1 = Double.parseDouble(request.getParameter("T1"));
            }
            System.out.println("1");
            System.out.println("a1: ");

            if (request.getParameter("T2") != null) {
                a2 = Double.parseDouble(request.getParameter("T2"));
            }
            System.out.println("2");
            if (request.getParameter("T3") != null) {
                a3 = Double.parseDouble(request.getParameter("T3"));
            }
            System.out.println("3");
            if (request.getParameter("T4") != null) {
                a4 = Double.parseDouble(request.getParameter("T4"));
            }

            System.out.println("4");
            if (request.getParameter("T5") != null) {
                a5 = Double.parseDouble(request.getParameter("T5"));
            }
            System.out.println("5");
            if (request.getParameter("T6") != null) {
                a6 = Double.parseDouble(request.getParameter("T6"));
            }
            System.out.println("6");
            if (request.getParameter("T7") != null) {
                a7 = Double.parseDouble(request.getParameter("T7"));
            }
            System.out.println("7");
            if (request.getParameter("T8") != null) {
                a8 = Double.parseDouble(request.getParameter("T8"));
            }
            System.out.println("8");

            if (request.getParameter("T9") != null) {
                a9 = Double.parseDouble(request.getParameter("T9"));
            }
            System.out.println("9");
            if (request.getParameter("T10") != null) {
                a10 = Double.parseDouble(request.getParameter("T10"));
            }
            System.out.println("10");
            if (request.getParameter("T11") != null) {
                a11 = Double.parseDouble(request.getParameter("T11"));
            }
            if (request.getParameter("T12") != null) {
                a12 = Double.parseDouble(request.getParameter("T12"));
            }
            if (request.getParameter("T13") != null) {
                a13 = Double.parseDouble(request.getParameter("T13"));
            }
            if (request.getParameter("T14") != null) {
                a14 = Double.parseDouble(request.getParameter("T14"));
            }
            if (request.getParameter("T15") != null) {
                a15 = Double.parseDouble(request.getParameter("T15"));
            }

            //11-19
            if (request.getParameter("T16") != null) {
                a16 = Double.parseDouble(request.getParameter("T16"));
            }
            if (request.getParameter("T17") != null) {
                a17 = Double.parseDouble(request.getParameter("T17"));
            }
            if (request.getParameter("T18") != null) {
                a18 = Double.parseDouble(request.getParameter("T18"));
            }
            if (request.getParameter("T19") != null) {
                a19 = Double.parseDouble(request.getParameter("T19"));
            }
            if (request.getParameter("T20") != null) {
                a20 = Double.parseDouble(request.getParameter("T20"));
            }
            if (request.getParameter("T21") != null) {
                a21 = Double.parseDouble(request.getParameter("T21"));
            }
            if (request.getParameter("T22") != null) {
                a22 = Double.parseDouble(request.getParameter("T22"));
            }
            if (request.getParameter("T23") != null) {
                a23 = Double.parseDouble(request.getParameter("T23"));
            }
            if (request.getParameter("T24") != null) {
                a24 = Double.parseDouble(request.getParameter("T24"));
            }
            if (request.getParameter("T25") != null) {
                a25 = Double.parseDouble(request.getParameter("T25"));
            }
            if (request.getParameter("T26") != null) {
                a26 = Double.parseDouble(request.getParameter("T26"));
            }
            if (request.getParameter("T27") != null) {
                a27 = Double.parseDouble(request.getParameter("T27"));
            }
            if (request.getParameter("T28") != null) {
                a28 = Double.parseDouble(request.getParameter("T28"));
            }
            if (request.getParameter("T29") != null) {
                a29 = Double.parseDouble(request.getParameter("T29"));
            }
            if (request.getParameter("T30") != null) {
                a30 = Double.parseDouble(request.getParameter("T30"));
            }

            if (request.getParameter("T31") != null) {
                a31 = Double.parseDouble(request.getParameter("T31"));
            }
            if (request.getParameter("T32") != null) {
                a32 = Double.parseDouble(request.getParameter("T32"));
            }
            if (request.getParameter("T33") != null) {
                a33 = Double.parseDouble(request.getParameter("T33"));
            }
            if (request.getParameter("T34") != null) {
                a34 = Double.parseDouble(request.getParameter("T34"));
            }
            if (request.getParameter("T35") != null) {
                a35 = Double.parseDouble(request.getParameter("T35"));
            }
            if (request.getParameter("T36") != null) {
                a36 = Double.parseDouble(request.getParameter("T36"));
            }
            //20-22
            // int a38=Integer.parseInt(request.getParameter("T38"));
            if (request.getParameter("T39") != null) {
                a39 = Double.parseDouble(request.getParameter("T39"));
            }
            if (request.getParameter("T40") != null) {
                a40 = Double.parseDouble(request.getParameter("T40"));
            }
            if (request.getParameter("T41") != null) {
                a41 = Double.parseDouble(request.getParameter("T41"));
            }
            if (request.getParameter("T42") != null) {
                a42 = Double.parseDouble(request.getParameter("T42"));
            }
            if (request.getParameter("T43") != null) {
                a43 = Double.parseDouble(request.getParameter("T43"));
            }
            if (request.getParameter("T44") != null) {
                a44 = Double.parseDouble(request.getParameter("T44"));
            }
            if (request.getParameter("T46") != null) {
                a46 = Double.parseDouble(request.getParameter("T46"));
            }
            if (request.getParameter("T47") != null) {
                a47 = Double.parseDouble(request.getParameter("T47"));
            }
            if (request.getParameter("T48") != null) {
                a48 = Double.parseDouble(request.getParameter("T48"));
            }
            if (request.getParameter("T49") != null) {
                a49 = Double.parseDouble(request.getParameter("T49"));
            }
            if (request.getParameter("T50") != null) {
                a50 = Double.parseDouble(request.getParameter("T50"));
            }
            if (request.getParameter("T51") != null) {
                a51 = Double.parseDouble(request.getParameter("T51"));
            }
            if (request.getParameter("T52") != null) {
                a52 = Double.parseDouble(request.getParameter("T52"));
            }
            if (request.getParameter("T53") != null) {
                a53 = Double.parseDouble(request.getParameter("T53"));
            }
            if (request.getParameter("T54") != null) {
                a54 = Double.parseDouble(request.getParameter("T54"));
            }
            if (request.getParameter("T55") != null) {
                a55 = Double.parseDouble(request.getParameter("T55"));
            }
            if (request.getParameter("T56") != null) {
                a56 = Double.parseDouble(request.getParameter("T56"));
            }
            // out.println("ret  "+a56);
            if (request.getParameter("T57") != null) {
                a57 = Double.parseDouble(request.getParameter("T57"));
            }
            if (request.getParameter("T58") != null) {
                a58 = Double.parseDouble(request.getParameter("T58"));
            }
            if (request.getParameter("T60") != null) {
                a60 = Double.parseDouble(request.getParameter("T60"));
            }
            if (request.getParameter("T61") != null) {
                a61 = Double.parseDouble(request.getParameter("T61"));
            }
            if (request.getParameter("T63") != null) {
                a63 = Double.parseDouble(request.getParameter("T63"));
            }
            if (request.getParameter("T64") != null) {
                a64 = Double.parseDouble(request.getParameter("T64"));
            }
            if (request.getParameter("T65") != null) {
                a65 = Double.parseDouble(request.getParameter("T65"));
            }
            if (request.getParameter("T66") != null) {
                a66 = Double.parseDouble(request.getParameter("T66"));
            }
            if (request.getParameter("T67") != null) {
                a67 = Double.parseDouble(request.getParameter("T67"));
            }

            if (request.getParameter("ot15") != null) {
                ot15 = Double.parseDouble(request.getParameter("ot15"));
            }
            if (request.getParameter("ot16") != null) {
                ot16 = Double.parseDouble(request.getParameter("ot16"));
            }
            System.out.println("test");
            if (request.getParameter("ot17") != null) {
                ot17 = Double.parseDouble(request.getParameter("ot17"));
            }

            if (request.getParameter("due") != null) {
                due = Double.parseDouble(request.getParameter("due"));
            }
            if (request.getParameter("rp1") != null) {
                rp1 = Double.parseDouble(request.getParameter("rp1"));
            }

            if (request.getParameter("rp") != null) {
                rp = Double.parseDouble(request.getParameter("rp"));
            }

            String s = "e";
            String l = "";
            String t = "";
            String a = "";
            String k = "";
            ResultSet r1 = null;
            ResultSet r2 = null;

            System.out.println("");

            System.out.println("b4 db connect");

            db.connect();

            t = session.getAttribute("t").toString();
            out.println("ret no is now " + t);
            session.setAttribute("ret_no", t);

            int count = 0;
            db.connect();
            db.setAutoCommit(false);

//            out.println("Return no :" + t + "   " + t1);
            System.out.println("11:");
            System.out.println("Return no :" + t + "   " + t1);
            String sql = "update vat.master_return_online set VAT_RC_NO='" + t1 + "',RETURN_NO='" + t + "',RC_NO='',RET_NO='',PRD_FROM_MON='" + month1 + "',PRD_TO_MON='" + month2 + "',PRD_FROM_YR='" + yearF + "',PRD_TO_YR='" + yearT + "',FLAG='N',LOG_DT='" + str_date + "',USR_CD='',CIRCLE_CD='" + combo1 + "' where return_no='" + t + "'";
//            String sql = "update vat.master_return_online set VAT_RC_NO='" + t1 + "',RETURN_NO='" + t + "',RC_NO='',RET_NO='',PRD_FROM_MON='" + month1 + "',PRD_TO_MON='" + month2 + "',PRD_FROM_YR='" + yearF + "',PRD_TO_YR='" + yearT + "',FLAG='N',LOG_DT='" + str_date + "' where return_no='" + t + "'";

            int i = db.updateSQL("update vat.master_return_online set VAT_RC_NO='" + t1 + "',RETURN_NO='" + t + "',RC_NO='',RET_NO='',PRD_FROM_MON='" + month1 + "',PRD_TO_MON='" + month2 + "',PRD_FROM_YR='" + yearF + "',PRD_TO_YR='" + yearT + "',FLAG='N' where return_no='" + t + "'");
//            int j = db.updateSQL("update vat.ret_purchase_qtr_details_on set RETURN_NO='" + t + "',P_EXEMPT_RATE_A='" + a1 + "',P_EXEMPT_RATE_B='" + a2 + "',P_4_RATE_A='" + a3 + "',P_4_RATE_B='" + a4 + "',P_12_5_RATE_A='" + a5 + "',P_12_5_RATE_B='" + a6 + "',P_1_RATE_A='" + a7 + "',P_1_RATE_B='" + a8 + "',P_1SCHEDULE_V_A='" + a10 + "',P_1SCHEDULE_V_B='" + a11 + "',P_2SCHEDULE_V_A='" + a13 + "',P_2SCHEDULE_V_B='" + a14 + "',TOT_AMT_IMP_TAX='" + a15 + "',RATE_P_8_A='" + a9 + "',RATE_P_9_B='" + a12 + "',P_4_RATE_PR= '" + rp + "',P_12_5_RATE_PR= '" + rp1 + "' where return_no='" + t + "'");
            int j = db.updateSQL("update vat.ret_purchase_qtr_details_on set RETURN_NO='" + t + "',P_EXEMPT_RATE_A=" + a1 + ",P_EXEMPT_RATE_B=" + a2 + ",P_4_RATE_A=" + a3 + ",P_4_RATE_B=" + a4 + ",P_12_5_RATE_A=" + a5 + ",P_12_5_RATE_B=" + a6 + ",P_1_RATE_A=" + a7 + ",P_1_RATE_B=" + a8 + ",P_1SCHEDULE_V_A=" + a10 + ",P_1SCHEDULE_V_B=" + a11 + ",P_2SCHEDULE_V_A=" + a13 + ",P_2SCHEDULE_V_B=" + a14 + ",TOT_AMT_IMP_TAX=" + a15 + ",RATE_P_8_A=" + a9 + ",RATE_P_9_B=" + a12 + ",P_4_RATE_PR= " + rp + ",P_12_5_RATE_PR= " + rp1 + " where return_no='" + t + "'");

//            int kl = db.updateSQL("update vat.ret_sales_qtr_details_online set  RETURN_NO='" + t + "',S_EXEMPT_RATE_A='" + a16 + "',S_EXEMPT_RATE_B='" + a17 + "',S_0_RATE_A='" + a18 + "',S_0_RATE_B='" + a19 + "',S_TAX_DUE_A='" + a22 + "',S_TAX_DUE_B='" + a23 + "',S_4_RATE_A='" + a24 + "',S_4_RATE_B='" + a25 + "',S_12_5_RATE_A='" + a26 + "',S_12_5_RATE_B='" + a27 + "',S_1SCHEDULE_V_A='" + a31 + "',S_1SCHEDULE_V_B='" + a32 + "',S_2SCHEDULE_V_A='" + a34 + "',S_2SCHEDULE_V_B='" + a35 + "',S_1_RATE_A='" + a28 + "',S_1_RATE_B='" + a29 + "',TOT_AMT_OUT_TAX='" + a36 + "',S_0_RATE_CST_A='" + a20 + "',S_0_RATE_CST_B='" + a21 + "',RATE_S_8_A='" + a30 + "',RATE_S_9_B='" + a33 + "',P_4_RATE_PR='" + ot15 + "',P_12_5_RATE_PR='" + ot16 + "',P_1_RATE_PR='" + ot17 + "',SALES_DUE_RATE='" + due + "' where return_no='" + t + "'");
            int kl = db.updateSQL("update vat.ret_sales_qtr_details_online set  RETURN_NO='" + t + "',S_EXEMPT_RATE_A=" + a16 + ",S_EXEMPT_RATE_B=" + a17 + ",S_0_RATE_A=" + a18 + ",S_0_RATE_B=" + a19 + ",S_TAX_DUE_A=" + a22 + ",S_TAX_DUE_B=" + a23 + ",S_4_RATE_A=" + a24 + ",S_4_RATE_B=" + a25 + ",S_12_5_RATE_A=" + a26 + ",S_12_5_RATE_B=" + a27 + ",S_1SCHEDULE_V_A=" + a31 + ",S_1SCHEDULE_V_B=" + a32 + ",S_2SCHEDULE_V_A=" + a34 + ",S_2SCHEDULE_V_B=" + a35 + ",S_1_RATE_A=" + a28 + ",S_1_RATE_B=" + a29 + ",TOT_AMT_OUT_TAX=" + a36 + ",S_0_RATE_CST_A=" + a20 + ",S_0_RATE_CST_B=" + a21 + ",RATE_S_8_A=" + a30 + ",RATE_S_9_B=" + a33 + ",P_4_RATE_PR=" + ot15 + ",P_12_5_RATE_PR=" + ot16 + ",P_1_RATE_PR=" + ot17 + ",SALES_DUE_RATE=" + due + " where return_no='" + t + "'");

//            int ki = db.updateSQL("update vat.total_output_tax_online set RETURN_NO='" + t + "',SALES_CANCELLED='" + a39 + "',RETURNS_OF_SOLD_GOODS='" + a40 + "',OTH_ADJUSTMENT_ADD='" + a41 + "',OTH_ADJUSTMENT_SUB='" + a42 + "',TOT_ADJUSTMENT='" + a43 + "',TOTAL_OUTPUT_TAX='" + a44 + "' where return_no='" + t + "'");
            int ki = db.updateSQL("update vat.total_output_tax_online set RETURN_NO='" + t + "',SALES_CANCELLED=" + a39 + ",RETURNS_OF_SOLD_GOODS=" + a40 + ",OTH_ADJUSTMENT_ADD=" + a41 + ",OTH_ADJUSTMENT_SUB=" + a42 + ",TOT_ADJUSTMENT=" + a43 + ",TOTAL_OUTPUT_TAX=" + a44 + " where return_no='" + t + "'");

//            int ii = db.updateSQL("update vat.total_input_tax_credit_online set RETURN_NO='" + t + "',STOCK_ON_TRANSITION='" + s1 + "',ITC_CARRIED_FORWARD='" + a46 + "',ITC_CAPITAL_GOODS='" + a47 + "',ITC_DEBIT_NOTE='" + a48 + "',OTH_ADJUSTMENT_ADD='" + a49 + "',TOTAL_ITC='" + a50 + "',ITC_CREDIT_NOTE='" + a51 + "',TAX_CREDIT_REVERSED='" + a52 + "',OTH_ADJUSTMENT_SUB='" + a53 + "',TOT_ITC_DEDUCT='" + a54 + "',TOT_NET_ITC='" + a55 + "' where return_no='" + t + "'");
            int ii = db.updateSQL("update vat.total_input_tax_credit_online set RETURN_NO='" + t + "',STOCK_ON_TRANSITION=" + s1 + ",ITC_CARRIED_FORWARD=" + a46 + ",ITC_CAPITAL_GOODS=" + a47 + ",ITC_DEBIT_NOTE=" + a48 + ",OTH_ADJUSTMENT_ADD=" + a49 + ",TOTAL_ITC=" + a50 + ",ITC_CREDIT_NOTE=" + a51 + ",TAX_CREDIT_REVERSED=" + a52 + ",OTH_ADJUSTMENT_SUB=" + a53 + ",TOT_ITC_DEDUCT=" + a54 + ",TOT_NET_ITC=" + a55 + " where return_no='" + t + "'");

//            int rd = db.updateSQL("update vat.return_details_online set RETURN_NO='" + t + "',TAX_PAYABLE='" + a56 + "',INTEREST_PENALTY_DUES='" + a57 + "',TOTAL_DUES='" + a58 + "',TAX_DEDUCTED_AT_SOURCE='" + a60 + "',BALANCE_EXCESS='" + a61 + "',TAX_PAID='" + a63 + "',BALANCE_DUE='" + a64 + "',CST_ADJUST_LIABILITY='" + a65 + "',REFUND='" + a66 + "',BALANCE_CARRIED_FORWARD='" + a67 + "',ADVANCE_TOTAL='',PAYMENT_STATUS='' where return_no='" + t + "'");
            int rd = db.updateSQL("update vat.return_details_online set RETURN_NO='" + t + "',TAX_PAYABLE=" + a56 + ",INTEREST_PENALTY_DUES=" + a57 + ",TOTAL_DUES=" + a58 + ",TAX_DEDUCTED_AT_SOURCE=" + a60 + ",BALANCE_EXCESS=" + a61 + ",TAX_PAID=" + a63 + ",BALANCE_DUE=" + a64 + ",CST_ADJUST_LIABILITY=" + a65 + ",REFUND=" + a66 + ",BALANCE_CARRIED_FORWARD=" + a67 + ",ADVANCE_TOTAL='',PAYMENT_STATUS='' where return_no='" + t + "'");

            //==========================================CHALLAN UPDATE==============================================
            String off = "update vat.challan set utilized='Y',return_flag='Y' where vat_rc_no='" + t1 + "' and PRD_FROM_MON='" + month1 + "' and PRD_FROM_YR='" + yearF + "' and PRD_TO_MON='" + month2 + "' and PRD_TO_YR='" + yearT + "' and purpose='R' and utilized='N' and return_flag='N' and status_verified='Y' and minor_cd='110'";
            String onl = "update vat.challan_online set utilized='Y',return_flag='Y' where vat_rc_no='" + t1 + "' and PRD_FROM_MON='" + month1 + "' and PRD_FROM_YR='" + yearF + "' and PRD_TO_MON='" + month2 + "' and PRD_TO_YR='" + yearT + "' and purpose='R' and utilized='N' and return_flag='N'  and status_verified='Y' and substr(schema,7,3)='110'";

            System.out.println("CHALLAN TABLE---------------update---------------");
            System.out.println("VAT_RC_NO" + t1);
            System.out.println("period from" + month1 + "/" + yearF + "  period to" + month2 + "/" + yearT);

            System.out.println("-----------------------------------------------");

             //==========================challan histoty===================================
            Date date = new java.util.Date();
            DateFormat df = new SimpleDateFormat("dd-MMM-yy");
            String log_dt = df.format(date);

            String sql1 = "select * from vat.challan_online where vat_rc_no='" + t1 + "' and PRD_FROM_MON='" + month1 + "' and PRD_FROM_YR='" + yearF + "' and PRD_TO_MON='" + month2 + "' and PRD_TO_YR='" + yearT + "' and purpose='R' and status_verified='Y' and utilized='N' and return_flag='N' and substr(schema,7,3)='110'";
            ResultSet rsc = db.execSQL(sql1);

            int chal1 = db.execSQLSel("select * from vat.challan_online where vat_rc_no='" + t1 + "' and PRD_FROM_MON='" + month1 + "' and PRD_FROM_YR='" + yearF + "' and PRD_TO_MON='" + month2 + "' and PRD_TO_YR='" + yearT + "' and purpose='R' and status_verified='Y' and utilized='N' and return_flag='N' and substr(schema,7,3)='110'");
            String sql3 = "select * from vat.challan where vat_rc_no='" + t1 + "' and PRD_FROM_MON='" + month1 + "' and PRD_FROM_YR='" + yearF + "' and PRD_TO_MON='" + month2 + "' and PRD_TO_YR='" + yearT + "' and purpose='R' and utilized='N' and return_flag='N' and status_verified='Y' and minor_cd='110'";
            ResultSet rss = db.execSQL(sql3);
            System.out.println("sql3----------");

            int chal3 = db.execSQLSel("select * from vat.challan where vat_rc_no='" + t1 + "' and PRD_FROM_MON='" + month1 + "' and PRD_FROM_YR='" + yearF + "' and PRD_TO_MON='" + month2 + "' and PRD_TO_YR='" + yearT + "' and purpose='R' and utilized='N' and return_flag='N' and status_verified='Y' and minor_cd='110'");

            System.out.println("chal1=" + chal1 + "and chal3=" + chal3);
            System.out.println("CHALLAN HISTORY---------------update");
            System.out.println("VAT_RC_NO" + t1);
            System.out.println("period from" + month1 + "/" + yearF + "  period to" + month2 + "/" + yearT);

            System.out.println("-----------------------------------------------");

            if (chal1 != 0 && chal3 != 0) {
                System.out.println("PRESENT IN BOTH TABLES");

                while (rsc.next()) {
                    String chal_no = rsc.getString("CHALLAN_NO");
                    String chal_amt = rsc.getString("CHALLAN_AMT");
                    Timestamp time = rsc.getTimestamp("tr_challan_date");
                    DateFormat df1 = new SimpleDateFormat("dd-MMM-yy");
                    String chal_date = df1.format(time);
//                                     Date chal_date=rsc.getDate("tr_challan_date");
                    System.out.println("insert chllan_history for cbt------------");
                    String chalH1 = "insert into vat.challan_history(vat_rc_no,challan_no,challan_amt,challan_date,tr_cd,used_date,module_cd,action) values('" + t1 + "','" + chal_no + "','" + chal_amt + "','" + chal_date + "','CBT','" + log_dt + "','13','Challan Utilized')";
                    db.execSQL(chalH1);
                }

                while (rss.next()) {
                    String chal_no = rss.getString("CHALLAN_NO");
                    String chal_amt = rss.getString("CHALLAN_AMT");
                    Timestamp time = rss.getTimestamp("tr_challan_date");
                    DateFormat df1 = new SimpleDateFormat("dd-MMM-yy");
                    String chal_date = df1.format(time);
                    String tr_cd = rss.getString("TR_CD");
                    System.out.println("insert chllan_history for not cbt------------");
                    System.out.println("Inside chllan------------");

                    String chalH1 = "insert into vat.challan_history(vat_rc_no,challan_no,challan_amt,challan_date,tr_cd,used_date,module_cd,action) values('" + t1 + "','" + chal_no + "','" + chal_amt + "','" + chal_date + "','" + tr_cd + "','" + log_dt + "','13','Challan Utilized')";
                    db.execSQL(chalH1);

                }
            }

            if (chal1 != 0 && chal3 == 0) {
                while (rsc.next()) {
                    String chal_no = rsc.getString("CHALLAN_NO");
                    String chal_amt = rsc.getString("CHALLAN_AMT");
                    Timestamp time = rsc.getTimestamp("tr_challan_date");
                    DateFormat df1 = new SimpleDateFormat("dd-MMM-yy");
                    String chal_date = df1.format(time);
//                                     Date chal_date=rsc.getDate("tr_challan_date");
                    System.out.println("insert vat.chllan_history for cbt------------");
                    String chalH1 = "insert into vat.challan_history(vat_rc_no,challan_no,challan_amt,challan_date,tr_cd,used_date,module_cd,action) values('" + t1 + "','" + chal_no + "','" + chal_amt + "','" + chal_date + "','CBT','" + log_dt + "','13','Challan Utilized')";
                    db.execSQL(chalH1);
                }

            } else if (chal3 != 0 && chal1 == 0) {
                System.out.println("INSERT INTO vat.CHALLAN HISTORY FOR NON CBT");
//                                String sql3="select * from challan where vat_rc_no='" + t1 + "' and PRD_FROM_MON='" + month1 + "' and PRD_FROM_YR='" + yearF + "' and PRD_TO_MON='" + month2 + "' and PRD_TO_YR='" + yearT + "' and purpose='R' and utilized='Y' and return_flag='Y' and status_verified='Y' and minor_cd='110'";
//                                ResultSet rss=db.execSQL(sql3)
                while (rss.next()) {
                    String chal_no = rss.getString("CHALLAN_NO");
                    String chal_amt = rss.getString("CHALLAN_AMT");
                    Timestamp time = rss.getTimestamp("tr_challan_date");
                    DateFormat df1 = new SimpleDateFormat("dd-MMM-yy");
                    String chal_date = df1.format(time);
                    String tr_cd = rss.getString("TR_CD");
                    System.out.println("insert chllan_history for not cbt------------");
                    System.out.println("Inside chllan------------");

                    String chalH1 = "insert into vat.challan_history(vat_rc_no,challan_no,challan_amt,challan_date,tr_cd,used_date,module_cd,action) values('" + t1 + "','" + chal_no + "','" + chal_amt + "','" + chal_date + "','" + tr_cd + "','" + log_dt + "','13','Challan Utilized')";
                    db.execSQL(chalH1);
                    System.out.println("update ending------");

                }
            } else {
                System.out.println("Errror in challan history");
            }

            String u = "";
//            ResultSet rsoff = db.execSQL(off);
//            ResultSet rsonl = db.execSQL(onl);
            out.println("i " + i + " j " + j + " kl " + kl + "  ki " + ki + "  ii " + ii + " rj " + "    rd    " + rd);

            if (i > 0 && j > 0 && kl > 0 && ki > 0 && ii > 0 && rd > 0) {
                db.commit();
                db.close();

                RequestDispatcher rd1 = request.getRequestDispatcher("updatedone.jsp");
                rd1.forward(request, response);

            } else {
                db.rollback();
                db.close();
            }
        } catch (SQLException e) {
            System.out.println("SQL EXCP: " + e.getMessage());
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
