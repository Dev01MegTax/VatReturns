
package StReject;
import java.util.Date;
import java.sql.*;
import bean.dbBean;
import static com.crystaldecisions.reports.formulas.SimpleFormulaFunctionArgumentDefinition.date;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sms2.SendSMS;

public class StRejectServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //Set Flag in Master_online_return to R(Rejected)==============================================
        try {
            System.out.println("StRejectServlet-----------------------------------------| ");
         String tin;
        String rto,msg1,TOKEN,month1,year1,month2,year2;
        
           
        tin=request.getParameter("tin").toString();
        rto=request.getParameter("rtno").toString();
        TOKEN=request.getParameter("TOKEN").toString();
        msg1=request.getParameter("textarea").toString().trim();
        month1=request.getParameter("month1").toString();
        year1=request.getParameter("year1").toString();
        month2=request.getParameter("month2").toString();
        year2=request.getParameter("year2").toString();
            System.out.println("==================================================");
      
       
       // String msg2=request.getParameter("msg2").toString();
        System.out.println("Tin is------------------"+tin);
        System.out.println("Return No is------------------"+rto);
        System.out.println("Message is------------------"+msg1);
        System.out.println("token is------------------"+TOKEN);
        System.out.println("Period Between:"+month1+"/"+year1+" TO "+month2+"/"+year2);
        System.out.println("======================================================");
    
        HttpSession session=request.getSession();
        dbBean db=new dbBean();
                           
                        try{
                             db.connect();
                            SimpleDateFormat df2 = new SimpleDateFormat("dd-MMM-yyyy");
                           String s = df2.format(new java.util.Date());
                            String user_cd=session.getAttribute("username").toString();
                           
                            
                            String sql="update vat.master_return_online set FLAG='R' where vat_rc_no='"+tin+"' and return_no='"+rto+"'";
                              int i=db.updateSQL(sql);
                          
                              
                              String sql2="insert into vat.return_process_history(vat_rc_no,return_no,action,remarks,user_cd,log_date)values('"+tin+"','"+rto+"','Rejected','"+msg1+"','"+user_cd+"','"+s+"')";
                            db.execSQL(sql2);
//                            ================Challan Update====================
                            String onl = "update vat.challan_online set utilized='N',return_flag='N' where vat_rc_no='" + tin + "' and PRD_FROM_MON='" + month1 + "' and PRD_FROM_YR='" + year1 + "' and PRD_TO_MON='" + month2 + "' and PRD_TO_YR='" + year2 + "' and purpose='R' and utilized='Y' and return_flag='Y'  and status_verified='Y' and substr(schema,7,3)='110'";
                            String off = "update vat.challan set utilized='N',return_flag='N' where vat_rc_no='" + tin + "' and PRD_FROM_MON='" + month1 + "' and PRD_FROM_YR='" + year1 + "' and PRD_TO_MON='" + month2 + "' and PRD_TO_YR='" + year2 + "' and purpose='R' and utilized='Y' and return_flag='Y'  and status_verified='Y' and minor_cd='110'";
//                           ======================================
                          
                           
                            int r=db.updateSQL(onl);
          
                            int q=db.updateSQL(off);
                            
                            
                                   
                                Date date = new java.util.Date();
                               DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                               String log_dt = df.format(date);

                                String sql1="select * from vat.challan_online where vat_rc_no='" + tin + "' and PRD_FROM_MON='" + month1 + "' and PRD_FROM_YR='" + year1 + "' and PRD_TO_MON='" + month2 + "' and PRD_TO_YR='" + year2 + "' and purpose='R' and status_verified='Y' and utilized='N' and return_flag='N' and substr(schema,7,3)='110'";
                                ResultSet rsc=db.execSQL(sql1);
                            
                                
                                while(rsc.next())
                                {
                                    String chal_no=rsc.getString("CHALLAN_NO");
                                     String chal_amt=rsc.getString("CHALLAN_AMT");
                                         Timestamp time=rsc.getTimestamp("tr_challan_date");
                                         DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
                                       String chal_date = df1.format(time);
                                        System.out.println(chal_date);
                                    
                                    
//                                      Date chal_date=rsc.getDate("tr_challan_date");
                                      System.out.println("insert chllan_history for cbt------------");
                                      String chalH1="insert into vat.challan_history(VAT_RC_NO,CHALLAN_NO,CHALLAN_AMT,CHALLAN_DATE,TR_CD,USED_DATE,MODULE_CD,ACTION) values('" + tin + "','" + chal_no + "','" + chal_amt + "','" + chal_date + "','CBT','" + log_dt + "','13','Challan De-Utilised')";
                                      db.execSQL(chalH1);
                                }
                               
                                  
                                String sql3="select * from vat.challan where vat_rc_no='" + tin + "' and PRD_FROM_MON='" + month1 + "' and PRD_FROM_YR='" + year1 + "' and PRD_TO_MON='" + month2 + "' and PRD_TO_YR='" + year2 + "' and purpose='R' and status_verified='Y'  and utilized='N' and return_flag='N' and minor_cd='110'";
                                ResultSet rss=db.execSQL(sql3);
                                while(rss.next())
                                {
                                     String chal_no=rss.getString("CHALLAN_NO");
                                     String chal_amt=rss.getString("CHALLAN_AMT");
                                         Timestamp time=rss.getTimestamp("tr_challan_date");
                                         DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
                                       String chal_date = df1.format(time);
                                     
                                    
                                     String tr_cd=rsc.getString("TR_CD");
                                      System.out.println("insert chllan_history not cbt------------");
                                       System.out.println("Inside chllan------------");
                                        
                                     String chalH1="insert into vat.challan_history(vat_rc_no,challan_no,challan_amt,challan_date,tr_cd,used_date,module_cd,action) values('" + tin + "','" + chal_no + "','" + chal_amt + "','" + chal_date + "','"+tr_cd+"','" + log_dt + "','13','Challan De-Utilised')";
                                      db.execSQL(chalH1);
                                  
                                }

                            System.out.println("Reject Success!!");
                        db.close();
                        }catch(ClassNotFoundException e){
                        System.out.println("ERROR!!"+e);
                           
                       
                        } catch (SQLException sq) {
                            System.out.println("SQL ERROR!!"+sq);
                            
            }       
                        //----------------------------SMS-------------------------------------
                     try{
                         db.connect();
//                         
                        String mobile="abc";
                         
                         //String msg="Please Re-varify the Return";
                         String sql1="select email,mobile from vat.login_dealer where vat_rc_no='" + tin + "'";
                         ResultSet rs=db.execSQL(sql1);
                         if (rs.next()) {
//                          
                        mobile = "91" + rs.getString(2);
                             System.out.println("mobile no="+mobile);
                        }
                          String msg2 = tin +  msg1 + "-Return no:"+rto;
                        int sms1=SendSMS.send(mobile, msg2);
//                         response.sendRedirect("stDataView.jsp");
                         response.sendRedirect("st_data_view.jsp");
                         db.close();
                     }catch(ClassNotFoundException e){
                         
                         System.out.println("Sms Error"+e);
                     } catch (SQLException sql) {
                         System.out.println("sql Sms Error"+sql);
            }
            //-------------------------------------------------------------
            catch (IOException e) {
                System.out.println("Sms Error");
            }
            //-------------------------------------------------------------
            
                        
                        
                        //-------------------------------------------------------------
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
        processRequest(request, response);
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
        processRequest(request, response);
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
