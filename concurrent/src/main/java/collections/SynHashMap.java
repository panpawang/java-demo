package collections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class SynHashMap {
//    public static Map m=Collections.synchronizedMap(new HashMap());
    public static Map m= new ConcurrentHashMap();
    
    public static void main(String[] args) {
        m.put("hello", "world");
    }
}
