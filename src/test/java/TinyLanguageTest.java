import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.StringReader;

import static java.time.Duration.ofMinutes;

public class TinyLanguageTest {
    String code;
    @Test
    public void AEXP() throws ParseException {
        code = "1356";
        new TinyLanguage(new StringReader(code));
        assertTrue(TinyLanguage.AEXP());

        code = "(8000+565)";
        TinyLanguage.ReInit(new StringReader(code));
        assertTrue(TinyLanguage.AEXP());

        code ="8*665";
        TinyLanguage.ReInit(new StringReader(code));
        assertFalse(TinyLanguage.AEXP());

        code = "(((7+a)+(b-8))+565)";
        TinyLanguage.ReInit(new StringReader(code));
        assertTrue(TinyLanguage.AEXP());

    }

    @Test
    public void Com() throws ParseException {
        code = "a:=8;b:=(90+75)";
        new TinyLanguage(new StringReader(code));
        assertTrue(TinyLanguage.Com());

        code ="if tt then c:=0 else skip";
        TinyLanguage.ReInit(new StringReader(code));
        assertTrue(TinyLanguage.Com());

        code ="while tt do a:= a-1";
        TinyLanguage.ReInit(new StringReader(code));
        assertTrue(TinyLanguage.Com());
    }


    @Test
    public void BEXP() throws ParseException {
        code = "(!(a==b)) ^ (c==7)";
        new TinyLanguage(new StringReader(code));
        assertTrue(TinyLanguage.BEXP());

        code ="a:=4; b:=6; c=1; if ((a + 5) == (b + 6)) then c:=0 else skip";
        TinyLanguage.ReInit(new StringReader(code));
        assertTrue(TinyLanguage.Com());

        code ="if (a == 1) then f:=d;if !(a == 1) then f:=99";
        TinyLanguage.ReInit(new StringReader(code));
        assertFalse(TinyLanguage.Com());


    }

}
