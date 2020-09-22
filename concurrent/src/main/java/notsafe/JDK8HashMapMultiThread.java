
package notsafe;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * use JDK 7
 * JDK 8不会死循环，只会数据不一致
 * @author Geym
 *
 */
public class JDK8HashMapMultiThread {

    static Map<String,String> map = new HashMap<String,String>();
//    static Map map = new ConcurrentHashMap(10);

    public static class AddThread implements Runnable {
        int start=0;
        public AddThread(int start){
            this.start=start;
        }
        @Override
        public void run() {
            for (int i = start; i < 10000; i++) {
                map.put(Integer.toString(i), Integer.toString(i));
            }
        }
    }

    public static void test() throws Exception{
        Thread t1=new Thread(new AddThread(0));
        Thread t2=new Thread(new AddThread(1));
        t1.start();
        t2.start();
        t1.join();t2.join();
        System.out.println("map size="+map.size());
        System.out.println("table count="+getTableSize());
    }
    public static void main(String[] args) throws Exception {
        
        for(int i=0;i<100;i++){
            test();
            map = new HashMap<String,String>();
        }
    }
    
    public static int getTableSize() throws Exception{
        Field fTable=map.getClass().getDeclaredField("table");
        fTable.setAccessible(true);
        Object[] table=(Object[])fTable.get(map);
        int count=0;
        for(int i=0;i<table.length;i++){
           if(table[i]!=null)count++; 
        }
        fTable.setAccessible(false);
        return count;
    }

}
