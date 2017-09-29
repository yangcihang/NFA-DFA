import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 测试类
 * Created by yang on 17/9/27.
 */
public class Main {
    public static void main(String[] args) {
        NFA nfa = new NFA();
        Set<String> startStateList = new HashSet<>();
        startStateList.add("0");
        nfa.setStartStateList(startStateList);

        Set<String> endStateList = new HashSet<>();
        endStateList.add("10");
        nfa.setEndStateList(endStateList);

        List<String> charList = new ArrayList<>();
        charList.add("#");
        charList.add("a");
        charList.add("b");
        nfa.setCharList(charList);

        Set<String> stateList = new HashSet<>();
        for (int i = 0; i < 11; i++) {
            stateList.add("" + i);
        }
        nfa.setStateList(stateList);

        List<Node> fList = new ArrayList<>();
        fList.add(new Node("0", "1", "#"));
        fList.add(new Node("0", "7", "#"));
        fList.add(new Node("1", "2", "#"));
        fList.add(new Node("1", "4", "#"));
        fList.add(new Node("2", "3", "a"));
        fList.add(new Node("3", "6", "#"));
        fList.add(new Node("4", "5", "b"));
        fList.add(new Node("5", "6", "#"));
        fList.add(new Node("6", "1", "#"));
        fList.add(new Node("6", "7", "#"));
        fList.add(new Node("7", "8", "a"));
        fList.add(new Node("8", "9", "b"));
        fList.add(new Node("9", "10", "b"));
        nfa.setReflectList(fList);

        System.out.println(nfa.toString());
        Util.setNfa(nfa);
        Util.NFA2DFA();
        System.out.println(Util.getDfa());
    }
}
