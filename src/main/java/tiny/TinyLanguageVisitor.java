/* Generated By:JavaCC: Do not edit this line. TinyLanguageVisitor.java Version 7.0.10 */
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
/* JavaCC - OriginalChecksum=e6fd987729189667ffac41f7d22ae4d8 (do not edit this line) */
