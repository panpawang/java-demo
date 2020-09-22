package trace;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * submit吃掉异常
 * 1. 用Future.get()获得异常
 * 2. try-catch
 * @author Geym
 *
 */
public class CatchExceptionMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor pools=new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                0L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        
        for(int i=0;i<5;i++){
            pools.submit(new DivTask(100,i));
        }
    }
}
