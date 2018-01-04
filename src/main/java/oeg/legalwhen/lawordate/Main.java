package oeg.legalwhen.lawordate;



import de.unihd.dbs.heideltime.standalone.DocumentType;
import de.unihd.dbs.heideltime.standalone.HeidelTimeStandalone;
import de.unihd.dbs.heideltime.standalone.OutputType;
import de.unihd.dbs.heideltime.standalone.POSTagger;
import de.unihd.dbs.uima.annotator.heideltime.resources.Language;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author mnavas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        
       try{
            
            String txt = args[0];
            System.out.println(txt); 
            System.out.println("---------------------------------------------------------------"); 
           Salida parseLegalRef = parseLegalRef(txt);
            System.out.println(parseLegalRef.txt); 
            System.out.println("---------------------------------------------------------------"); 
           System.out.println(unParseLegalRef(parseLegalRef));
            
        }      
        catch(Exception ex){
            System.out.print(ex.toString());
        }
        
    }
    
//    public static String parseLegalRef(String txt){
//        String content = txt;
//        content = content.replaceAll("((([L|l]ey [O|o]rg[á|a]nica)|(LEY ORG[Á|A]NICA)) (\\d*)\\/(\\d*))(,? de (\\d+) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefLeyOrganica");        
//        content = content.replaceAll("([D|d]irectiva (\\d*)\\/(\\d*)\\/(\\w*))( de (\\d*) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefDirectiva");
//        content = content.replaceAll("(RD (\\d*)\\/(\\d*))(,? de (\\d*) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefRD");
//        content = content.replaceAll("(([R|r](eal|EAL) [D|d](ecreto|ECRETO)) (\\d*)\\/(\\d*))(,? de (\\d+) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefRealDecreto");
//        content = content.replaceAll("(([R|r](eal|EAL) [D|d](ecreto|ECRETO) [L|l](egislativo|EGISLATIVO)) (\\d*)\\/(\\d*))(,? de (\\d*) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefRealDecretoLeg");
//        content = content.replaceAll("([L|l]ey (\\d*)\\/(\\d*))(,? de (\\d*) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefLey");
//        content = content.replaceAll("(BOE|[B|b]olet[í|i]n [O|o]ficial del [E|e]stado|BOLETIN OFICIAL DEL ESTADO):? (\\d+)\\/(\\d*)\\/(\\d*)", "RefBOE3");
//        content = content.replaceAll("(BOE|[B|b]olet[í|i]n [O|o]ficial del [E|e]stado|BOLETIN OFICIAL DEL ESTADO)(,? de (\\d+) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefBOE1");
//        content = content.replaceAll("(BOE|[B|b]olet[í|i]n [O|o]ficial del [E|e]stado|BOLETIN OFICIAL DEL ESTADO)( (([N|n][ú|u]m(\\.?|ero)|N[Ú|U]M(\\.?|ERO)) \\d+))(,? de (\\d*) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefBOE2");
//        
//        return content;
//    }
    
    public static Salida parseLegalRef(String txt){
        
        Salida output = new Salida();
        Map<String, String> listaRegex = new HashMap();
        Integer count = 0;
        Map<String, String> mapaRefs = new HashMap();
        String copiatxt = txt;
        
        listaRegex.put("((([L|l]ey [O|o]rg[á|a]nica)|(LEY ORG[Á|A]NICA)) (\\d*)\\/(\\d*))(,? de (\\d+) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?","RefLeyOrganica");
        listaRegex.put("([D|d]irectiva (\\d*)\\/(\\d*)\\/(\\w*))( de (\\d*) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefDirectiva");
        listaRegex.put("(RD (\\d*)\\/(\\d*))(,? de (\\d*) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefRD");
        listaRegex.put("(([R|r](eal|EAL) [D|d](ecreto|ECRETO)) (\\d*)\\/(\\d*))(,? de (\\d+) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefRealDecreto");
        listaRegex.put("(([R|r](eal|EAL) [D|d](ecreto|ECRETO) [L|l](egislativo|EGISLATIVO)) (\\d*)\\/(\\d*))(,? de (\\d*) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefRealDecretoLeg");
        listaRegex.put("([L|l]ey (\\d*)\\/(\\d*))(,? de (\\d*) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefLey");
        listaRegex.put("(BOE|[B|b]olet[í|i]n [O|o]ficial del [E|e]stado|BOLETIN OFICIAL DEL ESTADO)(:?) (\\d+)\\/(\\d*)\\/(\\d*)", "RefB3");
        listaRegex.put("(BOE|[B|b]olet[í|i]n [O|o]ficial del [E|e]stado|BOLETIN OFICIAL DEL ESTADO)((,?) de (\\d+) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)", "RefB1");
        listaRegex.put("(BOE|[B|b]olet[í|i]n [O|o]ficial del [E|e]stado|BOLETIN OFICIAL DEL ESTADO)( (([N|n][ú|u]m(\\.?|ero)|N[Ú|U]M(\\.?|ERO)) \\d+))((,?) de (\\d*) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)", "RefB2");
        
        
        
        Set<String> iterar = listaRegex.keySet();
        
        for(String cadena : iterar){
            
            count = 0;
        
        
        Pattern pText = Pattern.compile(cadena);
        Matcher m =  pText.matcher(copiatxt);
        String shortcut = listaRegex.get(cadena);
        
        
        while(m.find()){
            String original = m.group(0);
            String cambio = shortcut+count.toString()+shortcut;
            mapaRefs.put(cambio, original);
            copiatxt = copiatxt.replaceFirst(original, cambio);
            
        }       
        
        }
        output.mapa=mapaRefs;
        output.txt=copiatxt;
        return output;
    }
    
    
    public static String unParseLegalRef(Salida output){
        
        String txt = output.txt;
        Map<String, String> mapa = output.mapa;
        Set<String> keySet = mapa.keySet();
        for(String a : keySet){
            txt = txt.replaceFirst(a, mapa.get(a));
        }
        
        return txt;
    }
    
    public static Salida parseAndTag(String txt){
        
        Salida s = parseLegalRef(txt);
        String removing = onlyParse(s.txt);
        
        s.txt = removing;
        
        String out = unParseLegalRef(s);
        
        s.txt = out;
        
        return s;
    }
    
    public static String onlyParse(String txt){
        
        try {
            HeidelTimeStandalone heidelTime  = new HeidelTimeStandalone(Language.ENGLISH, DocumentType.NARRATIVES, OutputType.TIMEML,
                    "\\lib\\heideltime-standalone\\config.props", POSTagger.NO, true);    
            return heidelTime.process(txt);
            
        } catch (Exception ex) {
            System.err.print(ex.toString());
        }
        return "";
    }
    
    
    


    }


    