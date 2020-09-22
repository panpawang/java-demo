package map;

import java.util.concurrent.ConcurrentHashMap;

public class SearchDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 100; i++) {
            map.put(Integer.toString(i), i);
        }
        int found = map.search(Long.MAX_VALUE, (str,i)->{
            if(i%2==0) {
                return i;
            }
            return null;
        });
        System.out.println(found);
    }
}
