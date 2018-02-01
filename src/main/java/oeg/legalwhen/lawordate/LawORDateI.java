package oeg.legalwhen.lawordate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mnavas
 */
public class LawORDateI extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. Takes a text as an input. Offers the same text: a) either
     * removing the legal references b)
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String path1 = request.getParameter("sub");
        String path2 = request.getParameter("ttag");

        if (path2 != null) {
            out.print("<html>\n"
                    + "    <head>\n"
                    + "        <title>test</title>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\" " + request.getContextPath() + "/css/main.css\" />\n"
                    + "        \n"
                    + "    <!-- multistep form -->\n"
                    + "<form id=\"msform\">\n"
                    + "  <!-- progressbar -->\n"
                    + "  <ul id=\"progressbar\">\n"
                    + "    <li class=\"active\">Submit the original text full of legal references</li>\n"
                    + "    <li>Get a version optimal for temporal annotation</li>\n"
                    + "    <li>Get back your legal references</li>\n"
                    + "  </ul>\n"
                    + "  <!-- fieldsets -->\n"
                    + "  <fieldset>\n"
                    + "   <h2 class=\"fs-title\">Final text</h2>\n"
                    + "    <h3 class=\"fs-subtitle\">Your original text with real temporal annotations provided by state-of-the-art temporal tagger as <a href=\"https://github.com/HeidelTime/\">HeidelTime</a> after our preprocessing</h3>");

            String input = request.getParameter("inputText");

            response.setStatus(200);
            response.setContentType("text/html;charset=UTF-8");
            ServletContext context = getServletContext();

            String path = context.getResource("/WEB-INF/classes/config.props").getPath();

            //MNL
            Salida withLoD = Main.parseAndTag(input, path);
            String withoutLoD = Main.onlyParse(input, path);
            out.println("<textarea rows=\"7\">");
            out.println(withLoD.txt);
//            out.println();
            out.println("</textarea>");

            out.println("<p>   </p>");

            out.println("    <h2 class=\"fs-title\" >MAP OF REPLACEMENTS</h2>\n" + "<h3 class=\"fs-subtitle\">The replacements done by LawORDate before applying <a href=\"https://github.com/HeidelTime/\">HeidelTime</a> are the following:</h3>");
//            out.println("<textarea rows=\"3\">");           
            out.println(withLoD.toTableWithoutNumber());

            out.println("<br>");

            out.print("   <h2 class=\"fs-title\">Alternative final text</h2>\n"
                    + "    <h3 class=\"fs-subtitle\">Without our LawORDate preprocessing, the result by <a href=\"https://github.com/HeidelTime/\">HeidelTime</a> would have been:</h3>\n");

            out.println("<textarea rows=\"7\">");
            out.println(withoutLoD);
            out.println("</textarea>");
            out.println("<br>");

            out.print("  </fieldset>\n"
                    + "</form>\n"
                    + "</body>\n"
                    + "</html>\n");
        }

        if (path1 != null) {
            out.print("<html>\n"
                    + "    <head>\n"
                    + "        <title>test</title>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\" " + request.getContextPath() + "/css/main.css\" />\n"
                    + "        \n"
                    + "    <!-- multistep form -->\n"
                    + "<form id=\"msform\" method=\"post\" action=\"" + request.getContextPath() + "/LawORDateII\">\n"
                    + "  <!-- progressbar -->\n"
                    + "  <ul id=\"progressbar\">\n"
                    + "    <li class=\"active\">Submit the original text full of legal references</li>\n"
                    + "    <li>Get a version optimal for temporal annotation</li>\n"
                    + "    <li>Get back your legal references</li>\n"
                    + "  </ul>\n"
                    + "  <!-- fieldsets -->\n"
                    + "  <fieldset>\n"
                    + "    <h2 class=\"fs-title\">Replaced version</h2>\n"
                    + "    <h3 class=\"fs-subtitle\">This version of the text is free of legal references misleading temporal taggers</h3>\n");

            out.println("<textarea rows=\"6\">");
            String inputText = request.getParameter("inputText");
            Salida parsedSalida = Main.parseLegalRef(inputText);
            out.println(parsedSalida.txt);
            out.println("</textarea>");
            out.println("<p>   </p>");

            out.println("    <h2 class=\"fs-title\" >MAP OF REPLACEMENTS</h2>\n" + "<h3 class=\"fs-subtitle\">The replacements done are the following:</h3>");
//            out.println("<textarea rows=\"3\">");           
            out.println(parsedSalida.toTable());
//            out.println("</textarea>");

            out.println("    <h2 class=\"fs-title\">NEXT STEP</h2>\n" + "<h3 class=\"fs-subtitle\">Please input your own temporally tagged version of the replaced version of the text:</h3>\n");

            out.print("          <input type=\"text\" name=\"inputText2\" placeholder=\"Tagged text\" />\n"
                    + "          <input type=\"submit\" name=\"next\" value=\"Next\" class=\"button\" />\n"
                    + "  </fieldset>\n"
                    + "</form>\n"
                    + "</body>\n"
                    + "</html>\n");

            ServletContext context = request.getSession().getServletContext();
            context.setAttribute("map", parsedSalida);
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
