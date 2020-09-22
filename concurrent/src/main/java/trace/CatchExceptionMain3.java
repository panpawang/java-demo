package trace;

import java.util.concurrent.*;

/**
 * submit吃掉异常
 * 1. 用Future.get()获得异常
 * 2. try-catch
 * @author Geym
 *
 */
public class CatchExceptionMain3 {	public static void main(String[] args) throws InterruptedException, ExecutionException {
	ThreadPoolExecutor pools=new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            0L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());
	
	for(int i=0;i<5;i++){
		Future re=pools.submit(new DivTask(100,i));
		re.get();
	}
}}
