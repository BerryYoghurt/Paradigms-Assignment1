package tiny;

import java.io.StringReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        TinyLanguage parser = new TinyLanguage(System.in);
        ExecuteVisitor v = new ExecuteVisitor();
        HashMap<String, Integer> map = new HashMap<>();
        try {
            String code ="while tt do a:=(a-1)";
            TinyLanguage.ReInit(new StringReader(code));
            SimpleNode e = TinyLanguage.Prog();
            e.jjtAccept(v,map);
        } catch (ParseException | SemanticException parseException) {
            parseException.printStackTrace();
        }
    }
}
