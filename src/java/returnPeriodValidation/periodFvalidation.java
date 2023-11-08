/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package returnPeriodValidation;
import java.sql.*;
import bean.dbBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jiten's
 */
public class periodFvalidation extends HttpServlet {
      dbBean db=new dbBean();
      String l="";
      String s="";
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String t1=request.getParameter("Tinn");
        String pM=request.getParameter("quarter1");
        String pY=request.getParameter("year1");
        try
        {
            ResultSet r=null;
            ResultSet r1=null;
            db.connect();
            r=db.execSQL("select max(prd_from_mon) from vat.master_return where vat_rc_no='"+t1+"' ");
            while(r.next())
                 l=r.getString(1);
               
            r1=db.execSQL("select max(prd_from_yr) from vat.master_return where vat_rc_no='"+t1+"' ");
            while(r1.next())
                 s=r1.getString(1);
               
               if(pM.equals(l))
                   out.println("you entered a wfgg");
               else if(pM.length()<l.length())
                   out.println("you");
               
        }
        catch(Exception e)
        {
            out.println("e");
        }
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet periodFvalidation</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet periodFvalidation at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
