/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stlogvalidation;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.dbBean;
import java.sql.*;
import java.util.*;
import java.text.*;


/**
 *
 * @author jiten's
 */
public class rejectst1 extends HttpServlet {
   dbBean db=new dbBean();
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
          Calendar currentDate = Calendar.getInstance();
          SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yy");
          String dateNow = formatter.format(currentDate.getTime());
          String s=request.getParameter("ac");

          String s2=request.getParameter("Tinn");
          String s3="reject";
          try {

             db.connect();


             int i=db.updateSQL("insert into  return_confirm_st values('"+s2+"','"+s+"','"+s3+"','"+dateNow+"')");
             //out.println("dhhh");
             if(i<=0)
                 out.println("Data not inserted");
             else if(i>0)
             {
                 db.close();

            }
//             response.sendRedirect("stDataView.jsp");
             response.sendRedirect("st_data_view.jsp");
        }catch(Exception e){
             out.println(e);

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
