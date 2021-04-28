package tiny;

import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        TinyLanguage parser = new TinyLanguage(System.in);
        ExecuteVisitor v = new ExecuteVisitor();
        HashMap<String, Integer> map = new HashMap<>();
        try {
            SimpleNode e = parser.Prog();
            e.jjtAccept(v,map);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
    }
}
