package tiny;

/* Generated By:JJTree: Do not edit this line. ASTSimpleCom.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTSimpleCom extends SimpleNode {
  public ASTSimpleCom(int id) {
    super(id);
  }

  public ASTSimpleCom(TinyLanguage p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(TinyLanguageVisitor visitor, Object data) {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=8fd69933e4feb625b9b3aa69e2d60f30 (do not edit this line) */
