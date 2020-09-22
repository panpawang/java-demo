package collections;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SynList {
	public static List<String> l=Collections.synchronizedList(new LinkedList<String>());
//    public static List<String> l=Collections.synchronizedList(new ArrayList<String>());
//    public static List<String> l=new Vector();
//    public static List<String> l=new CopyOnWriteArrayList<String>();
    
    public static void main(String[] args) {
        l.add("hello");
    }

}
