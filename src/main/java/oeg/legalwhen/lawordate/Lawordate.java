package oeg.legalwhen.lawordate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;



public class Lawordate extends HttpServlet {
    
    private static final Logger logger = Logger.getLogger(Lawordate.class.getName());

    /**
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            PrintWriter w = resp.getWriter();
            resp.setStatus(200);
            resp.setContentType("text/plain;charset=utf-8");
            resp.setCharacterEncoding("utf-8");
            w.println("Please use the POST method");
                    
    }
    
    /**
     */
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            String body =getBody(req);
            String parsed = Main.parseLegalRef(body);
            PrintWriter w = resp.getWriter();
            resp.setStatus(200);
            resp.setContentType("text/plain;charset=utf-8");
            resp.setCharacterEncoding("utf-8");
            w.println(parsed);
    }
    
    /**
     * Obtains the body from a HTTP request. Period. No more, no less.
     */
    public static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        body = stringBuilder.toString();
        return body;
    }    
}