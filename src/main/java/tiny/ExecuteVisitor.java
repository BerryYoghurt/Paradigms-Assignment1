package tiny;


import java.util.Map;

/**data has to be of type map**/
public class ExecuteVisitor extends TinyLanguageDefaultVisitor{
    @Override
    public Object visit(ASTAEXP node, Object data){
        NodeInfo info = (NodeInfo) node.value;
        String value = (String) info.value;
        Map<String, Integer> map = (Map<String, Integer>)data;
        try {
            switch (info.type) {
                case TinyLanguageConstants.NUM:
                    return Integer.parseInt(value);
                case TinyLanguageConstants.VAR:
                    if (map.containsKey(value)) {
                        return map.get(value);
                    } else {
                        throw new SemanticException("Variable accessed without being defined.");
                    }
                case TinyLanguageConstants.ARTHOPRND:
                    if(node.jjtGetNumChildren() != 2)
                        throw new ParseException("Parse error during semantic analysis should not happen");
                    if(value.compareTo("+") == 0){
                        return
                                (Integer) node.jjtGetChild(0).jjtAccept(this,data)
                                + (Integer) node.jjtGetChild(1).jjtAccept(this,data);
                    }else if(value.compareTo("-") == 0){
                        return
                                (Integer) node.jjtGetChild(0).jjtAccept(this,data)
                                - (Integer) node.jjtGetChild(1).jjtAccept(this,data);
                    }
                default:
                    throw new ParseException("Parse exception should not happen");
            }
        }catch (SemanticException | ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object visit(ASTBEXP node, Object data){
        Integer kind = (Integer) node.value;
        try{
            switch (kind){
                case TinyLanguageConstants.TRUE:
                    return true;
                case TinyLanguageConstants.FALSE:
                    return false;
                case TinyLanguageConstants.AND:
                    if(node.jjtGetNumChildren() != 2)
                        throw new ParseException("Parse error during semantic analysis should not happen");
                    return
                            (Boolean)node.jjtGetChild(0).jjtAccept(this,data)
                                    && (Boolean)node.jjtGetChild(1).jjtAccept(this,data);
                case TinyLanguageConstants.EQUALS:
                    if(node.jjtGetNumChildren() != 2)
                        throw new ParseException("Parse error during semantic analysis should not happen");
                    return
                            ((Integer)node.jjtGetChild(0).jjtAccept(this,data))
                            .equals((Integer) node.jjtGetChild(1).jjtAccept(this,data));
                case TinyLanguageConstants.NOT:
                    if(node.jjtGetNumChildren() != 1)
                        throw new ParseException("Parse error during semantic analysis should not happen");
                    return !(Boolean)node.jjtGetChild(0).jjtAccept(this,data);
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object visit(ASTCompoundCom node, Object data) {
        int kind = (Integer)node.value;
        boolean bool = (Boolean) node.jjtGetChild(0).jjtAccept(this, data);
        try {
            switch (kind) {
                case TinyLanguageConstants.IF:
                    if (bool) {
                        return node.jjtGetChild(1).jjtAccept(this, data);
                    } else {
                        return node.jjtGetChild(2).jjtAccept(this, data);
                    }
                case TinyLanguageConstants.WHILE:
                    while ((Boolean) node.jjtGetChild(0).jjtAccept(this, data)) {
                        node.jjtGetChild(1).jjtAccept(this, data);
                    }
                    return null;
                default:
                    throw new ParseException("Ugh");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object visit(ASTSimpleCom node, Object data){
        NodeInfo info = (NodeInfo)node.value;
        Map<String, Integer> map = (Map<String,Integer>)data;
        try{
            switch (info.type) {
                case TinyLanguageConstants.SK:
                    return null;
                case TinyLanguageConstants.ASSIGN:
                    int assigned = (Integer) node.jjtGetChild(0).jjtAccept(this, data);
                    map.put((String) info.value, assigned);
                    return assigned;
                default:
                    throw new ParseException("ughhh");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object visit(ASTProg node, Object data){
        defaultVisit(node, data);
        Map<String, Integer> map = (Map<String, Integer>) data;
        System.out.println("Current State");
        for(String s : map.keySet()){
            System.out.print(s);
            System.out.print(": ");
            System.out.println(map.get(s));
        }
        return null;
    }
}
