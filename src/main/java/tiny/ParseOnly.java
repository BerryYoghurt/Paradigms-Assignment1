package tiny;

import java.util.HashMap;

public class ParseOnly {
    public static void main(String[] args){
        TinyLanguage parser = new TinyLanguage(System.in);
        try {
            parser.Prog();
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
    }
}
