package oeg.legalwhen.lawordate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Map;

/**
 *
 * @author Maria
 */
public class Salida {
    
    public String txt;
    public Map<String,String> mapa;
    
    public String toString(){
        String output="";
        if(mapa.size()==0)
            return "";
        for(String k : mapa.keySet()){
            output = output + k + "\t---->\t" + mapa.get(k) + "\n";            
        }
        return output;
    }
    
        public String toTable(){
        String output="";
        if(mapa.size()==0)
            return "";
        output="<table>\n" +
"    <thead>\n" +
"        <tr>\n" +
"            <th>Replacement</th>\n" +
"            <th>Original</th>\n" +
"        </tr>\n" +
"    </thead>\n" + 
    "<tbody>\n";
        for(String k : mapa.keySet()){
            output = output + "<tr>\n" +
"            <td>" + k + "</td>\n" +
"            <td>" + mapa.get(k) + "</td>\n" +
"        </tr>\n";            
        }
        return output + " </tbody>\n" +
"</table>";
    }
    
}
