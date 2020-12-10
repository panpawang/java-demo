package memory;

/***
 * @description: Demonstrate totalMemory(), freeMemory() and gc()
 * @author rui.chow
 * @date 2020/12/10
 */

public class MemoryDemo {

    public static void main(String[] args) {
        final Runtime r = Runtime.getRuntime();

        long mem1, mem2;

        final Integer[] someInts = new Integer[1000];
        System.out.println("Total Memory is : " + r.totalMemory());

        mem1 = r.freeMemory();
        System.out.println("Initial free memory : " + mem1);

        r.gc();
        mem2 = r.freeMemory();
        System.out.println("Free memory after gc() : " + mem2);

        for (int i = 0; i < 1000; i++) {

            someInts[i] = i;
        }

        mem2 = r.freeMemory();

        System.out.println("Free memory after allocation: " + mem2);
        System.out.println("Memory used by allocation: " + (mem1 - mem2));

        //discard Integers
        for (int i = 0; i < 1000; i++) {

            someInts[i] = null;
        }

        r.gc();

        mem2 = r.freeMemory();
        System.out.println("Free memory after gc() : " + mem2);

    }
}
