package system;


/***
 * @description: Timing program execution
 * @author rui.chow
 * @date 2020/12/10
 */
public class Elapsed {

    public static void main(String[] args) {
        long start, end;

        System.out.println("Timing a for loop from 0 to 100,000,000");

        start = System.currentTimeMillis();

        for (int i = 0; i < 100000000; i++) {
        }

        end = System.currentTimeMillis();

        System.out.println("Elapsed time: " + (end - start));
    }


}
