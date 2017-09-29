import java.util.List;
import java.util.Set;

/**
 * NFA的表示Model
 * Created by yang on 17/9/27.
 */
public class NFA {
    //状态集，避免重复
    private Set<String> stateList;
    private List<String> charList;
    private List<Node> reflectList;
    private Set<String> startStateList;
    private Set<String> endStateList;


    public void setStateList(Set<String> stateList) {
        this.stateList = stateList;
    }

    public Set<String> getStateList() {
        return stateList;
    }

    public List<String> getCharList() {
        return charList;
    }

    public void setCharList(List<String> charList) {
        this.charList = charList;
    }

    public void setReflectList(List<Node> reflectList) {
        this.reflectList = reflectList;
    }

    public List<Node> getReflectList() {
        return reflectList;
    }

    public void setStartStateList(Set<String> startStateList) {
        this.startStateList = startStateList;
    }

    public Set<String> getStartStateList() {
        return startStateList;
    }

    public void setEndStateList(Set<String> endStateList) {
        this.endStateList = endStateList;
    }

    public Set<String> getEndStateList() {
        return endStateList;
    }

    @Override
    public String toString() {
        String startString = "";
        for (String item : startStateList) {
            startString = startString + "" + item;
        }
        String endString = "";
        for (String item : endStateList) {
            endString += item + " ";
        }
        String charString = "";
        for (String item : charList) {
            charString += item + " ";
        }
        String stateString = "";
        for (String item : stateList) {
            stateString += item + " ";
        }
        String nodeString = "";
        for (Node item : reflectList) {
            nodeString += item.toString() + "\n";
        }
        return "初始状态集为\n" + startString + "\n" + "终态集为\n" + endString + "\n" +
                "字母表为\n" + charString + "\n" + "状态集为\n" + stateString + "\n" + "关系映射为\n" + nodeString;
    }
}
