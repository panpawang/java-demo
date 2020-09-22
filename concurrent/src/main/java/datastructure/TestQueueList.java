package datastructure;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestQueueList {
	private static final int MAX_THREADS = 2000;
	private static final int TASK_COUNT = 4000;
	Queue q;
	
	public class HandleQueueThread implements Runnable{
		protected String name;
		java.util.Random rand=new java.util.Random();
		public HandleQueueThread(){
		}
		public HandleQueueThread(String name){
			this.name=name;
		}
		@Override
		public void run() {
			try {
				for(int i=0;i<500;i++)
					handleQueue(rand.nextInt(1000));
				Thread.sleep(rand.nextInt(100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public class CounterPoolExecutor extends ThreadPoolExecutor{
		private AtomicInteger count =new AtomicInteger(0);
		public long startTime=0;
		public String funcname="";
		public CounterPoolExecutor(int corePoolSize, int maximumPoolSize,
				long keepAliveTime, TimeUnit unit,
				BlockingQueue<Runnable> workQueue) {
			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		}
		
		protected void afterExecute(Runnable r, Throwable t) { 
			int l=count.addAndGet(1);
			if(l==TASK_COUNT){
				System.out.println(funcname+" spend time:"+(System.currentTimeMillis()-startTime));
			}
		}
	}
	
	public Object handleQueue(int index){
		q.add(index);
		q.poll();
		return null;
	}
	
	public void initConcurrentLinkedQueue(){
		 q=new ConcurrentLinkedQueue();
		 for(int i=0;i<300;i++)
			 q.add(i);
	}
	
	public void initLinkedBlockingQueue(){
		q=new LinkedBlockingQueue();
		 for(int i=0;i<300;i++)
			 q.add(i);
	}
	
	public void initArrayListBlockingQueue(){
		q=new ArrayBlockingQueue(10000);
		 for(int i=0;i<300;i++)
			 q.add(i);
	}
	
	//@Test
	public void testAddConcurrentLinkedQueue() throws InterruptedException {
		initConcurrentLinkedQueue();
		CounterPoolExecutor exe=new CounterPoolExecutor(MAX_THREADS, MAX_THREADS,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

		long starttime=System.currentTimeMillis();
		exe.startTime=starttime;
		exe.funcname="testAddConcurrentLinkedQueue";
		for(int i=0;i<TASK_COUNT;i++)
			exe.submit(new HandleQueueThread());
		
		Thread.sleep(10000);
	}

	@Test
	public void testLinkedBlockingQueue() throws InterruptedException {
		initLinkedBlockingQueue();
		CounterPoolExecutor exe=new CounterPoolExecutor(MAX_THREADS, MAX_THREADS,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

		long starttime=System.currentTimeMillis();
		exe.startTime=starttime;
		exe.funcname="testLinkedBlockingQueue";
		for(int i=0;i<TASK_COUNT;i++)
			exe.submit(new HandleQueueThread());
		
		Thread.sleep(10000);
	}
	
	//@Test
	public void testinitArrayListBlockingQueue() throws InterruptedException {
		initArrayListBlockingQueue();
		CounterPoolExecutor exe=new CounterPoolExecutor(MAX_THREADS, MAX_THREADS,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

		long starttime=System.currentTimeMillis();
		exe.startTime=starttime;
		exe.funcname="testinitArrayListBlockingQueue";
		for(int i=0;i<TASK_COUNT;i++)
			exe.submit(new HandleQueueThread());
		
		Thread.sleep(10000);
	}
}
