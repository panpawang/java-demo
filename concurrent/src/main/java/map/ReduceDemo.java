package map;

import java.util.concurrent.ConcurrentHashMap;

public class ReduceDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 100; i++) {
            map.put(Integer.toString(i), i);
        }
        int count = map.reduceValues(2, (i, j) -> i + j);
        System.out.println(count);
    }
}
