package tiny;

/**
 * A class to old extra info I need about each node. Some nodes do not need it.*/
public class NodeInfo {
    public int type;
    public Object value;
    NodeInfo(int type, Object value){
        this.type = type;
        this.value = value;
    }
}
