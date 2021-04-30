import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tiny.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TinyLanguageTest {
    String code;
    ExecuteVisitor v = new ExecuteVisitor();
    HashMap<String, Integer> map = new HashMap<>();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        //reset static counters
        System.setOut(originalOut);
        TinyLanguage.Reset();
    }
    //first some tests for parser
    @Test
    public void AEXP1() throws ParseException {
        code = "(8000+abcdef)";
        TinyLanguage.ReInit(new StringReader(code));
        assertEquals(TinyLanguage.AEXP(),3);

    }

    @Test
    public void AEXP2() throws ParseException {

        code = "((7+a)+(b-8))+565";
        TinyLanguage.ReInit(new StringReader(code));
        assertEquals(TinyLanguage.AEXP(),7);

    }
//
//    @Test
//    public void AEXP3() throws ParseException {
//        code = "(abcdef-123456789)";
//        TinyLanguage.ReInit(new StringReader(code));
//        assertEquals(TinyLanguage.AEXP(),3);
//
//    }


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
        SimpleNode e = TinyLanguage.Prog();
        e.jjtAccept(v,map);
        assertEquals(currentState(new String[]{"a","b"},new int[]{8,165}), outContent.toString());
    }

    @Test
    public void testWhile() throws ParseException {
        code ="f:=5;while (!(f==10)^tt) do f:=(f+1)";
        TinyLanguage.ReInit(new StringReader(code));
        SimpleNode e = TinyLanguage.Prog();
        e.jjtAccept(v,map);
        assertEquals(currentState(new String[]{"f"},new int[]{10}), outContent.toString());
    }
    @Test
    public void testIF() throws ParseException {
        code ="b:=8;a:=8;if !(b==8) then while !(a==0) do a:=(a-1) else if(a == b) then a:=9 else skip";
        TinyLanguage.ReInit(new StringReader(code));
        SimpleNode e = TinyLanguage.Prog();
        e.jjtAccept(v,map);
        assertEquals(currentState(new String[]{"a","b"},new int[]{9,8}), outContent.toString());

    }

    @Test
    public void whileIf() throws ParseException {
        code ="b:=5;a:=8;if !(b==8) then while !(a==0) do a:=(a-1) else if(a == b) then a:=9 else skip";
        TinyLanguage.ReInit(new StringReader(code));
        SimpleNode e = TinyLanguage.Prog();
        e.jjtAccept(v,map);
        assertEquals(currentState(new String[]{"a","b"},new int[]{0,5}), outContent.toString());
    }

    @Test
    public void commands() throws ParseException {
        code ="a:=4; b:=6; c:=1; if ((a + 5) == (b + 6)) then c:=0 else skip";
        TinyLanguage.ReInit(new StringReader(code));
        SimpleNode e = TinyLanguage.Prog();
        e.jjtAccept(v,map);
        assertEquals(currentState(new String[]{"a","b","c"},new int[]{4,6,1}), outContent.toString());
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

    @Test(expected = SemanticException.class)
    public void undefinedVariable() throws ParseException {
        code ="while tt do a:=(a-1)";
        TinyLanguage.ReInit(new StringReader(code));
        SimpleNode e = TinyLanguage.Prog();
        e.jjtAccept(v,map);
    }

  static String currentState(String[] variables, int[] values){
        StringBuilder currentState = new StringBuilder("Current State\r\n");
      for (int i = 0; i < values.length; i++) {
          currentState.append(variables[i]).append(": ").append(values[i]).append("\r\n");
      }
      return currentState.toString();
  }
}
