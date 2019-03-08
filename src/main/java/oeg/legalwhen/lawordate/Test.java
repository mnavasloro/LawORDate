package oeg.legalwhen.lawordate;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vroddon
 */
public class Test extends HttpServlet {

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
        response.setStatus(200);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ServletContext context = getServletContext();
//        
//        String path = context.getResource("/WEB-INF/classes/config.props").getPath();
//        File file = new File(path);
//        System.out.println(file.getAbsolutePath());         
//        FileInputStream fis =  new FileInputStream(file);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(fis)));
//        String txt="";
//        String line = null;
//        while ((line = reader.readLine()) != null) {
//            txt+=line+"\n";
//            // do something with your read line
//        }
//        out.println(txt);
        

/*        
        URL url = context.getResource("/WEB-INF/classes/test.txt");      
        InputStream is = context.getResourceAsStream("/WEB-INF/classes/test.txt");
        if (is == null) {
            out.println("nulo!");
        } else {
            String texto = IOUtils.toString(is, "UTF-8");
            out.println(texto);
        }*/

        out.println("test");  
//        
//        String path = context.getResource("/WEB-INF/classes/config.props").getPath();
//        File file = new File(path);
//        String input = request.getParameter("inputText");
//        System.out.println(Main.eventParse(input));         
//        FileInputStream fis =  new FileInputStream(file);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(fis)));
//        String txt="";
//        String line = null;
//        while ((line = reader.readLine()) != null) {
//            txt+=line+"\n";
//            // do something with your read line
//        }
//        out.println(txt);
        
        
        
        
        
        
        
        /*        if (path2 != null) {
            out.print("<html>\n"
                    + "    <head>\n"
                    + "        <title>test</title>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + " <script src=\"/js/jquery.balloon.js\"></script>"
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

//            String input = request.getParameter("inputText");
            
            response.setStatus(200);
            response.setContentType("text/html;charset=UTF-8");
            ServletContext context = getServletContext();

            String path = context.getResource("/WEB-INF/classes/config.props").getPath();

            //MNL
            Salida withLoD = Main.parseAndTag(input, path);
            String withoutLoD = Main.onlyParse(input, path);
            
            
            String input2 = createHighlights(withLoD.txt);
            
            // add; change color:
            
            out.println("\n<script> $(function() { eval($('#sample1 .sample-script').text()); }); </script>\n<style>\n" +
"          #sample1-demo { font-size: 90%; padding: 0 10px 10px 10px; }\n" +
"          #sample1-demo a { color: #aec05b; text-decoration: underline; }\n" +
"          #sample1-demo img { float: right; border: none; }\n" +
"        </style>\n");
            out.println("<div class='testDiv' id=\"testscroll\">");
//            out.println("<textarea rows=\"7\">");
            out.println(input2);
//            out.println(withLoD.txt);
//            out.println();
            out.println("</div>");
//            out.println("</textarea>");

            out.println("<p>   </p>");

            out.println("    <h2 class=\"fs-title\" >MAP OF REPLACEMENTS</h2>\n" + "<h3 class=\"fs-subtitle\">The replacements done by LawORDate before applying <a href=\"https://github.com/HeidelTime/\">HeidelTime</a> are the following:</h3>");
//            out.println("<textarea rows=\"3\">");           
            out.println(withLoD.toTableWithoutNumber());

            out.println("<br>");

            out.print("   <h2 class=\"fs-title\">Alternative final text</h2>\n"
                    + "    <h3 class=\"fs-subtitle\">Without our LawORDate preprocessing, the result by <a href=\"https://github.com/HeidelTime/\">HeidelTime</a> would have been:</h3>\n");

            out.println("<div class='testDiv' id=\"testscroll\">");
//            out.println("<textarea rows=\"7\">");

            input2 = createHighlights(withoutLoD);
           
            out.println(input2);
            out.println("</div>");
//            out.println("</textarea>");
            out.println("<br>");

            out.print("  </fieldset>\n"
                    + "</form>\n"
                    + "</body>\n"
                    + "</html>\n");
        }*/
        
        
        
        
        
        
        
        
        
        
        
        
        
        

/*        
        URL url = context.getResource("/WEB-INF/classes/test.txt");      
        InputStream is = context.getResourceAsStream("/WEB-INF/classes/test.txt");
        if (is == null) {
            out.println("nulo!");
        } else {
            String texto = IOUtils.toString(is, "UTF-8");
            out.println(texto);
        }*/

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
        return "Test service";
    }// </editor-fold>

}
