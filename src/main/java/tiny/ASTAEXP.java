package tiny;

/* Generated By:JJTree: Do not edit this line. ASTAEXP.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTAEXP extends SimpleNode {
  public ASTAEXP(int id) {
    super(id);
  }

  public ASTAEXP(TinyLanguage p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(TinyLanguageVisitor visitor, Object data) {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=8674d5efe07f8e6483553d92d52617e8 (do not edit this line) */
