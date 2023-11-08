/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stlogvalidation;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.dbBean;
/**
 *
 * @author jiten's
 */
public class logValidate extends HttpServlet {
      dbBean db=new dbBean();
     
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        ResultSet rs=null;
       // System.out.println("hghgfgdf");
        String a=request.getParameter("ltp");
        String a1=request.getParameter("lkk");
        String a2=request.getParameter("lmm");
        String l1="";
        String l2="";
        String l3="";



        try {
            // 
            db.connect();
            
            rs=db.execSQL("select user_cd,password,circle_cd from vat.login_st where user_cd='"+a+"'");
           // int i=0;
            
            while(rs.next())
            {
                 l1 = rs.getString(1);
                 l2=rs.getString(2);
                 l3=rs.getString(3);
                // i++;
            }
            
            if(!a.equals(l1)&&!a1.equals(l2)&&!a2.equals(l3))

            {
                 out.println(1);
            }

            if(a.equals(l1)&&a1.equals(l2)&&a2.equals(l3))
            {
            //      
//             response.sendRedirect("stDataView.jsp");
             response.sendRedirect("st_data_view.jsp");
            //    out.println(1);
            }
           
        }
        catch(Exception e){
            System.out.println(e);
        }



         finally { 
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(logValidate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(logValidate.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(logValidate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(logValidate.class.getName()).log(Level.SEVERE, null, ex);
        }
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
