package oeg.legalwhen.lawordate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
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
            w.println("Please use the POST method...");
            
            String s = testsystem();
            w.println(s);
            
            
            
    }
    
    /**
     */
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            String body =getBody(req);
//            String parsed = Main.parseLegalRef(body);
            Salida parsedSalida = Main.parseLegalRef(body);
            String parsed = parsedSalida.txt;
            PrintWriter w = resp.getWriter();
            resp.setStatus(200);
            resp.setContentType("text/plain;charset=utf-8");
            resp.setCharacterEncoding("utf-8");
            w.println(parsed);
            //MNL
            String unparsed = Main.unParseLegalRef(parsedSalida);
            w.println("---------------------");
            w.println(parsedSalida.toString());
            w.println("---------------------");
            w.println(unparsed);
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


    public static String testsystem(){
        try{
        ProcessBuilder builder = new ProcessBuilder().command("ls", "$home");
        Process process = builder.start();
        process.waitFor(10, TimeUnit.SECONDS);
        int value = process.exitValue();
        if (value != 0) {
            throw new Exception(MessageFormat.format("Código de salida con error (%d)", value));
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
        String result = br.lines().collect(Collectors.joining("\n"));
        br.close();
        String seconds = result.split(" ")[0];
        String cadena = String.format("Segundos desde el inicio del sistema: %.2f", new BigDecimal(seconds));
        return cadena;
        }catch(Exception e)
        {
            return "-";
        }
    }    
    
}