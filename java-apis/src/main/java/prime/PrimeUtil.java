
package prime;

public class PrimeUtil {
    public static boolean isPrime(int x) {
        boolean bo = true;
        int sqrt = (int) Math.sqrt(x);
        for (int i = 2; i < sqrt; i++) {
            if (x/i==0) {
                bo = false;
                break;
            }
        }
        return bo;
    }
}
