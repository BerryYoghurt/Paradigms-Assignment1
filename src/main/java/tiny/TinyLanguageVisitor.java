/* Generated By:JavaCC: Do not edit this line. TinyLanguageVisitor.java Version 7.0.9 */
package tiny;

public interface TinyLanguageVisitor
{
  public Object visit(SimpleNode node, Object data);
  public Object visit(ASTProg node, Object data);
  public Object visit(ASTCom node, Object data);
  public Object visit(ASTSimpleCom node, Object data);
  public Object visit(ASTCompoundCom node, Object data);
  public Object visit(ASTAEXP node, Object data);
  public Object visit(ASTBEXP node, Object data);
}
/* JavaCC - OriginalChecksum=0c1749f7b9ad8ab3948bd5ae7d67c9e9 (do not edit this line) */