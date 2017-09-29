import java.util.*;

/**
 * NFA转换DFA类
 * Created by yang on 17/9/27.
 */
public class Util {
    //dfa状态集的表示
    private static final ArrayList<String> dfaStateSet = new ArrayList<>
            (Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"));
    private static int flagCount = 0;
    //NFA
    private static NFA nfa;
    //dfa
    private static NFA dfa = new NFA();
    //存储中间关系的集合
    private static List<Set<String>> tStateList = new ArrayList<>();
    //如果求得的集合存在中间关系集合中，记录此时的下标，然后在dfa状态集中查找相应的状态
    private static int flagReflect = 0;


    /**
     * NFA到DFA
     */
    public static void NFA2DFA() {
        dfa.setCharList(new ArrayList<>());
        dfa.setEndStateList(new HashSet<>());
        dfa.setReflectList(new ArrayList<>());
        dfa.setStartStateList(new HashSet<>());
        dfa.setStateList(new HashSet<>());
        dfa.getCharList().addAll(nfa.getCharList());
        setStartStateList();
        //i将dfa的状态集和中间存储的状态集对应
        int i = 0;
        while (i < tStateList.size()) {
            Set<String> temp;
            Set<String> current = tStateList.get(i);

            for (String state : nfa.getCharList()) {
                if (!state.equals(Node.epsilon)) {
                    temp = eClosure(moveTrans(current, state));
                    //是否重复,不重复的话加入到中间状态集合，且将Node加入dfa映射中，重复的话，只将Node加入dfa的映射中
                    if (!isExist(temp)) {
                        tStateList.add(temp);
                        String newDfaChar = dfaStateSet.get(flagCount++);
                        Node node = new Node(dfaStateSet.get(i), newDfaChar, state);
                        dfa.getStateList().add(newDfaChar);
                        dfa.getReflectList().add(node);
                        if (isEndState(temp)) {
                            dfa.getEndStateList().add(newDfaChar);
                        }
                    } else {
                        Node node = new Node(dfaStateSet.get(i), dfaStateSet.get(flagReflect), state);
                        dfa.getReflectList().add(node);
                    }
                }
            }
            i++;
        }
    }

    /**
     * 求e闭包,#表示e
     */
    private static Set<String> eClosure(Set<String> list) {
        Set<String> ans = new HashSet<>(list);
        List<String> temp = new ArrayList<>();
        for (String item : ans) {
            temp.add(item);
        }
        int i = 0;
        while (i < temp.size()) {
            for (Node node : nfa.getReflectList()) {
                if (node.getStart().equals(temp.get(i)) && node.gettChar().equals(Node.epsilon)) {
                    temp.add(node.getEnd());
                    ans.add(node.getEnd());
                }
            }
            i++;
        }
        return ans;
    }

    /**
     * 求转换集合
     *
     * @param state 输入值
     */
    private static Set<String> moveTrans(Set<String> list, String state) {
        Set<String> ans = new HashSet<>();
        for (String item : list) {
            for (Node node : nfa.getReflectList()) {
                if (node.getStart().equals(item) && node.gettChar().equals(state)) {
                    ans.add(node.getEnd());
                }
            }
        }
        return ans;
    }

    /**
     * 获取初始状态集
     */
    private static void setStartStateList() {
        Set<String> sList = eClosure(nfa.getStartStateList());
        String newDfaChar = dfaStateSet.get(flagCount++);
        dfa.getStartStateList().add(newDfaChar);
        dfa.getStateList().add(newDfaChar);
        tStateList.add(sList);
    }

    /**
     * 判断得到的闭包是否在tStateList中存在
     *
     * @param set 新闭包
     */
    private static boolean isExist(Set<String> set) {
        for (int i = 0; i < tStateList.size(); i++) {
            if (set.equals(tStateList.get(i))) {
                //重复的话记录此时的下标，查询此下标对应的dfa状态
                flagReflect = i;
                return true;
            }
        }
        return false;
    }

    /**
     * 设置判断是不是终态
     */
    private static boolean isEndState(Set<String> set) {
        for (String item:set) {
            if (nfa.getEndStateList().contains(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 设置DFA初始状态
     */
    public static void setNfa(NFA nfa) {
        Util.nfa = nfa;
    }

    public static NFA getDfa() {
        return dfa;
    }
}
