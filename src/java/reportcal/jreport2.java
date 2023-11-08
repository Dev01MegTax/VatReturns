/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportcal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import java.io.OutputStream;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;

/**
 *
 * @author nic
 */
public class jreport2 extends HttpServlet {

    Connection con = null;
    ServletContext context;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, RuntimeException {

        String referer = request.getHeader("Referer");
        if (referer == null) {
            response.sendRedirect("home.jsp");
        }
        PrintWriter out = null;

        // HttpSession session = request.getSession();
        //String t=(String)session.getAttribute("SessionReturnNo");
        String t = request.getParameter("txtRetNo");
        if (!t.equals("")) {
            // response.setHeader("Pragma", "public");
            //response.setHeader("Cache-Control", "public, max-age=3800");

            //    String applicationids[] = request.getParameterValues("T68");
            //  String module = (String) session.getAttribute("T68");
            //String reportName = "report-" + module + ".jrxml";
            HashMap params = new HashMap();
            // java.util.ArrayList al = new java.util.ArrayList();

//            Class.forName("oracle.jdbc.OracleDriver");
            Class.forName("org.postgresql.Driver");

//         Connection cn = DriverManager.getConnection("jdbc:oracle:thin://@10.179.2.84:1521/orcl", "vat", "vat");
//         Connection cn = DriverManager.getConnection("jdbc:oracle:thin://@10.179.0.68:1521/orcl", "vat", "vat");
            Connection cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MegVat", "vat", "vat");

            // Generate the report
            try {

                // al.addAll(Arrays.asList(applicationids));
                params.put("Return_No", t);
                //params.put("SUBREPORT_DIR", getServletContext().getRealPath("/") + "report/");

                JasperDesign jasperDesign = JRXmlLoader.load(getServletContext().getRealPath("/") + "report/report1.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport, params, cn);
                System.out.println(getServletContext().getRealPath("/"));
                // export report to pdf and stream back to browser
                byte[] pdfasbytes = JasperExportManager.exportReportToPdf(jasperprint);

                OutputStream outstream = response.getOutputStream();

                response.setContentType("application/pdf");
                response.setContentLength(pdfasbytes.length);
                response.setHeader("Content-disposition", "inline; filename=\"report2.pdf\"");
                outstream.write(pdfasbytes);

                JRExporter exporter = null;
                exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperprint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outstream);

            } catch (Exception e) {
                System.out.println("error" + e);
            } finally {
                System.out.println("err");

            }
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
            Logger.getLogger(jreport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(jreport.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(jreport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(jreport.class.getName()).log(Level.SEVERE, null, ex);
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
