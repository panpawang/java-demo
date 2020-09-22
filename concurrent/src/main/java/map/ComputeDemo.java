package map;

import java.util.concurrent.ConcurrentHashMap;

public class ComputeDemo {
    public static class HeavyObject {
        public HeavyObject() {
            System.out.println("HeavyObject created");
        }
    }

    public static void main(String[] args) {
        ConcurrentHashMap<String, HeavyObject> map = new ConcurrentHashMap<>();
        HeavyObject obj = getOrCreate(map, "1");
    }

    public static HeavyObject getOrCreate(ConcurrentHashMap<String, HeavyObject> map, String key) {
        return map.computeIfAbsent(key, k -> new HeavyObject());
    }

    // public static HeavyObject getOrCreate(ConcurrentHashMap<String, HeavyObject>
    // map, String key) {
    // HeavyObject value = map.get(key);
    // if (value == null) {
    // value = new HeavyObject();
    // map.put(key, value);
    // }
    // return value;
    // }
}
