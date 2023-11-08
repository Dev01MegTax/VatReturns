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
public class retNoSlno extends HttpServlet {
   dbBean db=new dbBean();
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String v=request.getParameter("altp");
                       ResultSet r1=null;

                       try{
                           db.connect();
                           r1=db.execSQL("select slno from vat.return_slno_online where circle_cd='"+v+"'");
                       while(r1.next())
                           {
         
                           String l=r1.getString(1);
                           int u=Integer.parseInt(l)+1;
                           String q=Integer.toString(u);
                      //     System.out.println("test 1 "+q);
                           out.println(q);
                           break;
                       }
                          }
                            catch(Exception e){
                               System.out.println("ERROR"+e);
                           }
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet retNoSlno</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet retNoSlno at " + request.getContextPath () + "</h1>");
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
