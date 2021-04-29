import org.junit.After;
import org.junit.Test;
import tiny.ParseException;
import tiny.TinyLanguage;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TinyLanguageTest {
    String code;

    @After
    public void Reset(){
        //reset static counters
        TinyLanguage.Reset();
    }

    @Test
    public void AEXP1() throws ParseException {
        code = "(8000+565)";
        TinyLanguage.ReInit(new StringReader(code));
        assertEquals(TinyLanguage.AEXP(),3);

    }

    @Test
    public void AEXP2() throws ParseException {

        code = "f:=(((7+a)+(b-8))+565)";
        TinyLanguage.ReInit(new StringReader(code));
        assertEquals(TinyLanguage.Prog(),1);

    }

    @Test
    public void AEXP3() throws ParseException {
        code = "(abcdef-123456789)";
        TinyLanguage.ReInit(new StringReader(code));
        assertEquals(TinyLanguage.AEXP(),3);

    }


    @Test
    public void BEXP1() throws ParseException {
        code = "(!(a==b) ^ (c==7))";
        TinyLanguage.ReInit(new StringReader(code));
        assertEquals(TinyLanguage.BEXP(),4);
    }

    @Test
    public void BEXP2() throws ParseException {
        code = "!!((a==b)^(tt^ff))";
        TinyLanguage.ReInit(new StringReader(code));
        assertEquals(TinyLanguage.BEXP(),7);
    }


    @Test
    public void commandAssignment() throws ParseException {
        code = "a:=8;b:=(90+75)";
        TinyLanguage.ReInit(new StringReader(code));
        assertEquals(TinyLanguage.Prog(),2);
    }

    @Test
    public void testWhile() throws ParseException {
        code ="while tt do a:=(a-1)";
        TinyLanguage.ReInit(new StringReader(code));
        assertEquals(TinyLanguage.Prog(),2);
    }
    @Test
    public void testIF() throws ParseException {
        code ="if tt then c:=0 else skip";
        TinyLanguage.ReInit(new StringReader(code));
        assertEquals(TinyLanguage.Com(),3);

    }

    @Test
    public void whileIf() throws ParseException {
        code ="if !(b==8) then while !ff do a:=(a-1) else if(a == b) then a:=9 else skip";
        TinyLanguage.ReInit(new StringReader(code));
        assertEquals(TinyLanguage.Prog(),6);
    }

    @Test
    public void commands() throws ParseException {
        code ="a:=4; b:=6; c:=1; if ((a + 5) == (b + 6)) then c:=0 else skip";
        TinyLanguage.ReInit(new StringReader(code));
        assertEquals(TinyLanguage.Prog(),6);
    }

    @Test(expected = ParseException.class)
    public void ifSemiColon() throws ParseException {
        code ="if (a == 1) then f:=d;if !(a == 1) then f:=99";
        TinyLanguage.ReInit(new StringReader(code));
        TinyLanguage.Prog();

    }

    @Test(expected = ParseException.class)
    public void missingPar() throws ParseException {
        code ="c:=(a+b) - 8";
        TinyLanguage.ReInit(new StringReader(code));
        TinyLanguage.Prog();
    }

    @Test(expected = ParseException.class)
    public void multiply() throws ParseException {
        code ="(8*665)";
        TinyLanguage.ReInit(new StringReader(code));
        TinyLanguage.Prog();
    }

    @Test(expected = ParseException.class)
    public void unknownSymbol() throws ParseException {
        code ="8965*";
        TinyLanguage.ReInit(new StringReader(code));
        TinyLanguage.Prog();
    }
}
