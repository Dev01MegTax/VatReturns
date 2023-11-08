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
public class monthValidate extends HttpServlet {
   
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
        String s="17031507060";//request.getParameter("ltp");
        String t="09";//request.getParameter("lmm");
        //int p=Integer.parseInt(t);
        out.println("jhhh");
       // Integer i=new Integer(t);
        
       /* String q="2011";//request.getParameter("lpp");
        dbBean db=new dbBean();
        ResultSet rs=null;
        String h="";
        try
        {
           db.connect();
           rs=db.execSQL("select prd_from_mon from master_return where vat_rc_no='"+s+"'and prd_from_yr='"+q+"'order by prd_from_mon");
           while(rs.next())
           {
               h = rs.getString(1);
               int w=Integer.parseInt(h);
               if(p<w)
               {
                   out.println("Your Period from month entry is invalid");
                   break;
               }
               else if(p>w&&p==p+3)
               {
                   break;
               }
               else if(p>w&&p==p+6)
               {
                   out.println("Your Period from month entry is invalid");
                   break;
               }

           }




        }
        catch(Exception e){
        out.println("error"+e);
        }*/

        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet monthValidate</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet monthValidate at " + request.getContextPath () + "</h1>");
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
