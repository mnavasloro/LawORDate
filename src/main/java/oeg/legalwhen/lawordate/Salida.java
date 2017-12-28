/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oeg.legalwhen.lawordate;

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
    
}
