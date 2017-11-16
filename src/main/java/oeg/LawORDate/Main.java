package oeg.LawORDate;

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
            System.out.println(parseLegalRef(txt)); 
            
        }      
        catch(Exception ex){
            System.out.print(ex.toString());
        }
        
    }
    
    public static String parseLegalRef(String txt){
        String content = txt;
        content = content.replaceAll("((([L|l]ey [O|o]rg[á|a]nica)|(LEY ORG[Á|A]NICA)) (\\d*)\\/(\\d*))(,? de (\\d+) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefLeyOrganica");        
        content = content.replaceAll("([D|d]irectiva (\\d*)\\/(\\d*)\\/(\\w*))( de (\\d*) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefDirectiva");
        content = content.replaceAll("(RD (\\d*)\\/(\\d*))(,? de (\\d*) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefRD");
        content = content.replaceAll("(([R|r](eal|EAL) [D|d](ecreto|ECRETO)) (\\d*)\\/(\\d*))(,? de (\\d+) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefRealDecreto");
        content = content.replaceAll("(([R|r](eal|EAL) [D|d](ecreto|ECRETO) [L|l](egislativo|EGISLATIVO)) (\\d*)\\/(\\d*))(,? de (\\d*) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefRealDecretoLeg");
        content = content.replaceAll("([L|l]ey (\\d*)\\/(\\d*))(,? de (\\d*) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefLey");
        content = content.replaceAll("(BOE|[B|b]olet[í|i]n [O|o]ficial del [E|e]stado|BOLETIN OFICIAL DEL ESTADO):? (\\d+)\\/(\\d*)\\/(\\d*)", "RefBOE3");
        content = content.replaceAll("(BOE|[B|b]olet[í|i]n [O|o]ficial del [E|e]stado|BOLETIN OFICIAL DEL ESTADO)(,? de (\\d+) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefBOE1");
        content = content.replaceAll("(BOE|[B|b]olet[í|i]n [O|o]ficial del [E|e]stado|BOLETIN OFICIAL DEL ESTADO)( (([N|n][ú|u]m(\\.?|ero)|N[Ú|U]M(\\.?|ERO)) \\d+))(,? de (\\d*) de ([E|e]nero|[F|f]ebrero|[M|m]arzo|[A|a]bril|[M|m]ayo|[J|j]unio|[J|j]ulio|[A|a]gosto|[S|s]emptiembre|[O|o]ctubre|[N|n]oviembre|[D|d]iciembre)( de (\\d\\d\\d\\d))?)?", "RefBOE2");
        
        return content;
    }


    }


    