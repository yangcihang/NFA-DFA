/**
 * 节点映射类
 * Created by yang on 17/9/27.
 */
public class Node {
    public static final String epsilon = "#";
    private String start;
    private String end;
    private String tChar;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public Node(String start, String end, String tChar) {
        this.start = start;
        this.end = end;
        this.tChar = tChar;
    }

    public Node() {
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String gettChar() {
        return tChar;
    }

    public void settChar(String tChar) {
        this.tChar = tChar;
    }

    @Override
    public String toString() {
        return start + "--" + tChar + "-->" + end;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Node && ((Node) obj).end.equals(end) && ((Node) obj).start.equals(start) && ((Node) obj).tChar.equals(tChar);
    }
}
