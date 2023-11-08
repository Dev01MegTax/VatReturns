/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportcal;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import user.Dbbean;

/**
 *
 * @author Max
 */
public class jsreport1 extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        System.out.println("test");

        try {

            ServletOutputStream outstream;
            HttpSession session = request.getSession();
            String T68 = request.getParameter("T68").trim();
            System.out.println("tid" + T68);
            String reportName = "report/return.jrxml";
            Dbbean db = new Dbbean();

            db.connect();
            System.out.println("tid :" + T68);
            //String sql="select * from m_issue_reg_online";
            String sql = "select * from vat.master_return_online where return_no='" + T68 + "'";
            System.out.println("SQL:" + sql);

            ResultSet rs = db.execSQL(sql);

            if (!rs.next()) {
                //System.out.println("not there");
                session.setAttribute("message", "INVALID waybill no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/transactionrv.jsp");
                if (dispatcher != null) {
                    dispatcher.forward(request, response);
                }
                db.close();
            } else {
                session.setAttribute("message", "");
                String path1 = getServletContext().getRealPath("/") + reportName;
                System.out.println(T68 + " " + path1);
                db.close();

//		Class.forName("oracle.jdbc.OracleDriver");
                Class.forName("org.postgresql.Driver");

//               Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@10.179.2.84:1521:orcl", "vat", "vat");
//		Connection cn = DriverManager.getConnection("jdbc:oracle:thin://@10.179.0.68:1521/orcl", "vat", "vat");
                Connection cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MegVat", "vat", "vat");

                HashMap params = new HashMap();
//                HashMap params = new HashMap();
                JasperDesign jasperDesignMain = JRXmlLoader.load(getServletContext().getRealPath("/") + "report/return.jrxml");
                JasperReport jasperReportMain = JasperCompileManager.compileReport(jasperDesignMain);
                String sr = getServletContext().getRealPath(File.separator) + "report" + File.separator;
                Map<String, Object> parameters = new HashMap<String, Object>();
                params.put("Return_No", T68);
                JasperPrint jasperprint1 = JasperFillManager.fillReport(jasperReportMain, params, cn);
                List<JasperPrint> jprintlist = new ArrayList<JasperPrint>();
                jprintlist.add(jasperprint1);
                JRPdfExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT_LIST, jprintlist);
                ServletOutputStream output = response.getOutputStream();
                response.setHeader("Content-disposition", "inline; filename=\"Return.pdf\"");
                response.setContentType("application/pdf");
                exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, output);
                exporter.exportReport();

            }
        } catch (Exception e) {
            System.out.println("report : " + e.getLocalizedMessage());
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
