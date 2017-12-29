/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oeg.legalwhen.lawordate;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mnavas
 */
public class TestLawORDate extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           out.print("<html>\n" +
"    <head>\n" +
"        <title>test</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    </head>\n" +
"    <body>\n" +
"<link rel=\"stylesheet\" type=\"text/css\" href=\" css/main.css\" />\n" +
"        \n" +
"    <!-- multistep form -->\n" +
"<form id=\"msform\" method=\"post\" action=\"TestLawORDate2\">\n" +
"  <!-- progressbar -->\n" +
"  <ul id=\"progressbar\">\n" +
"    <li class=\"active\">Submit the original text full of legal references</li>\n" +
"    <li>Get a version optimal for temporal annotation</li>\n" +
"    <li>Get back your legal references</li>\n" +
"  </ul>\n" +
"  <!-- fieldsets -->\n" +
"  <fieldset>\n" +
"    <h2 class=\"fs-title\">Replaced version</h2>\n" +
"    <h3 class=\"fs-subtitle\">This version of the text is free of legal references misleading temporal taggers</h3>\n");

            
            out.println("<textarea>");
            String inputText=request.getParameter("inputText");
            out.println(inputText);
            out.println("</textarea>");
            Salida parsedSalida = Main.parseLegalRef(inputText);
            //MNL
            String unparsed = Main.unParseLegalRef(parsedSalida);
            out.println("<textarea>");
            out.println(parsedSalida.toString());
            out.println("---------------------");
            out.println(unparsed);
            out.println("</textarea>");
            out.println("<br>");            
            out.println("It also includes a map of replacements");
                   
 out.println("Please input now the temporally tagged version of the text:");                   
                   
out.print("          <input type=\"text\" name=\"inputText2\" placeholder=\"Tagged text\" />\n" +
"          <input type=\"submit\" name=\"next\" value=\"Next\" />\n" +
"  </fieldset>\n" +
"</form>\n" +
"</body>\n" +
"</html>\n");
            
            
            
            
            
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Processed Text</title>");            
//            out.println("</head>");
//            out.println("<body>");
            
//            out.println("<h1>Servlet getText at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
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
