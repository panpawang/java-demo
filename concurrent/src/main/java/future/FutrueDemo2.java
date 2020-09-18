package future;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Executors;

public class FutrueDemo2 {
	public static void main(String args[]) throws InterruptedException {
		ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
		ListenableFuture<String> task = service.submit(new RealData("x"));

		Futures.addCallback(task, new FutureCallback<String>() {
			@Override
			public void onSuccess(String o) {
				System.out.println("异步处理成功,result=" + o);
			}
			@Override
			public void onFailure(Throwable throwable) {
				System.out.println("异步处理失败,e=" + throwable);
			}
		}, MoreExecutors.newDirectExecutorService());

		System.out.println("main task done.....");
		Thread.sleep(3000);
	}
}
