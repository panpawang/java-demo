
package datastructure;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapDemo {
    public static void main(String[] args) {
        Map<Integer, Integer> map=new ConcurrentSkipListMap<Integer, Integer>();
        for(int i=0;i<30;i++){
            map.put(i,i);
        }
        for(Map.Entry<Integer, Integer> entry:map.entrySet()){
            System.out.println(entry.getKey());
        }
    }
}
