package MineSweeperResources;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class MineSweeperScoreboard {
    private static final Map<String, Integer> map = new HashMap<>();

    public static void addScore(String size, Integer i) {
        if (map.containsKey(size)) {
            Integer mapvalue = map.get(size);
            i = (i < mapvalue) ? i : mapvalue;
        }
        map.put(size, i);
    }

    public static Vector<String> getScoreboard() {
        Vector<String> vector = new Vector<>();
        for (String s : map.keySet()) {
            vector.add(s + " in " + map.get(s) + "s");
        }
        return vector;
    }
}
