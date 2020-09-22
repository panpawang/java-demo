package prime;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FindPrimeMain {
    public static ThreadPoolExecutor pool=new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            0L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());
    public static int COUNT=1000;
    public static SyncBitSet bitset=new SyncBitSet(COUNT);
    public static AtomicInteger threadCount=new AtomicInteger(0);
    
    public static void printPrime(){
        for(int i=0;i<COUNT;i++){
            if(bitset.get(i)){
                System.out.print(i+" ");
                if(i%20==0)System.out.println();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        pool.execute(new FindPrime(2));
        while(threadCount.get()!=0){
            Thread.sleep(100);
        }
        printPrime();
    }

}
